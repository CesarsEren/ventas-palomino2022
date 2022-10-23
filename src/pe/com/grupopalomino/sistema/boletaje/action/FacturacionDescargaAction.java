package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.access.prepost.PreAuthorize;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
//import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesFacturacionPDF;
import pe.com.grupopalomino.sistema.boletaje.util.GeneraDocumentoFe;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','D','F')")
public class FacturacionDescargaAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private int limit, offset, total;
	private String query;
	private String empresa,fechaemision,serie,numero,tipodocumento,tipoOperacion,ruc;
	private Integer documentos,nro;
	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
	private FacturacionService facturacionservice = new FacturacionServiceI();
	private V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI(); 
	private Map<String, Object> mapaJSONResultadoIDA  = new HashMap<>();
	private Map<String, Object> mapaJSONResultado  = new HashMap<>();
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String mensajeServer = "";
	private boolean respuestaServer = false;
	private static final Log log = LogFactory.getLog(FacturacionDescargaAction.class);
	
	@Action(value = "documentosenviados", results = {@Result(name = SUCCESS, location = "sistema/facturacionelectronica/documentosaprobados.jsp")})
	public String documentosenviados(){
		return SUCCESS;
	}
	
	@Action(value = "cosultadocumentosenviados", results = {@Result(name = SUCCESS, type = "json")})
	public String cosultadocumentos1(){
		
		List<Map<String, Object>> lstDatos = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> ventasMap = new ArrayList<Map<String, Object>>();
		
		try {
				
				ventasMap = facturacionservice.SQL_SELECT_VENTA_DOCUMENTO_ELECTRONICO(empresa, Utils.FormatoFecha(fechaemision.trim().toString()), serie, numero, tipodocumento,ruc,limit,offset);
				
				
				if(ventasMap.size()== 0){
					respuestaServer = false;
					mensajeServer = "No se encontraron resultados.";
		    		return  SUCCESS;	
				}
				
				total = Integer.parseInt(ventasMap.get(0).get("Cantidad").toString());
				
				for (Map<String, Object> map : ventasMap) {
					
					Map<String, Object>  mapa =  new HashMap<>();
		    		mapa.put("id",map.get("Nro").toString());
		    		mapa.put("fechaEmision", map.get("FechaEmisionD"));
		    		mapa.put("empresa", map.get("Empresa"));
		    		mapa.put("documentoElectronico", map.get("DocumentoElectronico").toString());
		    		mapa.put("agencia", map.get("AgenciaD"));
		    		mapa.put("servicio", map.get("Servicio"));
		    		mapa.put("servicioD", map.get("ServicioD"));
		  		 	mapa.put("estado", "S");
		  		 	mapa.put("enviar", "N");
		  		 	mapa.put("todos", map.get("variosservice"));
		  		 	mapa.put("documentos", documentos);
		  		 	mapa.put("descarga", "S");
		  		 	mapa.put("enviado", map.get("Enviado")); 
		  		 	lstDatos.add(mapa);
				}
			
			
	    		mapaJSONResultadoIDA.put("rows", lstDatos);
		    	mapaJSONResultadoIDA.put("total", total);
		    	respuestaServer = true;
	    	
			
		} catch (Exception e) {
			respuestaServer = false;
			mensajeServer = "Ocurrio un error al generar la consulta.";
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	
	@Action(value = "457e5f18b6ae40ca920770214f", results = { 
	@Result(name = SUCCESS, type="stream" ,
			params = { "contentType", "application/zip", "inputName", "inputStream", "contentDisposition", "filename=\"test.zip\"", "bufferSize", "1024" })})
			public void generaXML() throws ParseException {
		
			try {
				
				V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(empresa);				
				Map<String, Object> mapaVentas = new HashMap<>();				
				mapaVentas = facturacionservice.SQL_SELECT_VENTA_APROBADA_FACTURADOR(nro, tipoOperacion);
				
				response.setHeader("Content-Disposition", "attachment;filename="+mapaVentas.get("DocumentoZip").toString());
				response.setHeader("Content-Type", "application/zip");				
				File file = new File(facturacionEmpresa.getRutaEnvioSunat()+mapaVentas.get("DocumentoZip").toString());
				
				if (!file.exists()){
					file = new File(facturacionEmpresa.getRutaEnvioSunatBackup()+mapaVentas.get("DocumentoZip").toString());
				}
				
				FileInputStream inStream = new FileInputStream(file);				 
				ServletOutputStream outStream = response.getOutputStream();
		         
		        byte[] buffer = new byte[4096];
		        int bytesRead = -1;
		         
		        while ((bytesRead = inStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		         
		        inStream.close();
		        outStream.close();  
				
				
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
	}
	
	@Action(value = "457e5f18b6ae40ca920770214c", results = { 
	@Result(name = SUCCESS, type="stream" ,
			params = { "contentType", "application/zip", "inputName", "inputStream", "contentDisposition", "filename=\"test.zip\"", "bufferSize", "1024" })})
			public void generaCDR() throws ParseException {
		
			try {
				
				V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(empresa);				
				Map<String, Object> mapaVentas = new HashMap<>();				
				mapaVentas = facturacionservice.SQL_SELECT_VENTA_APROBADA_FACTURADOR(nro, tipoOperacion);
				
				response.setHeader("Content-Disposition", "attachment;filename="+"R-"+mapaVentas.get("DocumentoZip").toString());
				response.setHeader("Content-Type", "application/zip");				
				File file = new File(facturacionEmpresa.getRutaRespuestaSunat()+"R-"+mapaVentas.get("DocumentoZip").toString());
				
				FileInputStream inStream = new FileInputStream(file);				 
				ServletOutputStream outStream = response.getOutputStream();
		         
		        byte[] buffer = new byte[4096];
		        int bytesRead = -1;
		         
		        while ((bytesRead = inStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		         
		        inStream.close();
		        outStream.close();  
				
				
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
	}
	
	@Action(value = "80ddc3cbfe7d45aa8ae95e87ed", results = { 
	@Result(name = SUCCESS, type="stream" ,
			params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"ticket.pdf\"", "bufferSize", "1024" })})
			public void generaPDFTicket() throws ParseException{
		
		
			com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document(PageSize.A4,(float) 2.5,0,50,0);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			
			try {
				
				PdfWriter.getInstance(documentoPDF, baos);
				documentoPDF.open();
				
				V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(empresa);
				
				if(facturacionEmpresa.getEmpresa().trim().equals("002")){
					
					
					InputStream rutaLogoPalomino = null;//request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
					 try {
						 rutaLogoPalomino = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/palomino2.jpg").openStream();
					 }catch (FileNotFoundException e) { 
						 rutaLogoPalomino = new URL("http://localhost:8080/ventas/_lib/img/palomino.jpg").openStream();
					}
					
					if(rutaLogoPalomino == null) {
						rutaLogoPalomino = new URL("http://localhost:8080/ventas/_lib/img/palomino.jpg").openStream(); 
					}
		               
					
					Image logoPalomino = Image.getInstance(IOUtils.toByteArray(rutaLogoPalomino));
					logoPalomino.scalePercent(12f);
					logoPalomino.setAbsolutePosition(35f, 810f);
					logoPalomino.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
					documentoPDF.add(logoPalomino);
					
					
				}else if (facturacionEmpresa.getEmpresa().trim().equals("004")){
					
					//InputStream rutaLogoWari = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"wari.jpg");
					
					InputStream rutaLogoWari = null;//request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
					 
					rutaLogoWari = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/wari.jpg").openStream();
		                
					if(rutaLogoWari == null) {
						rutaLogoWari = new URL("http://localhost:8080/ventas/_lib/img/wari.jpg").openStream();
			             
					}
					
					Image logoWari = Image.getInstance(IOUtils.toByteArray(rutaLogoWari));
					logoWari.scalePercent(15f);
					logoWari.setAbsolutePosition(55f, 810f);
					logoWari.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
					documentoPDF.add(logoWari);
					
				}
				else if (facturacionEmpresa.getEmpresa().trim().equals("003")){
					
					
					//InputStream rutaLogoCargo = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"cargo.jpg");
					
					InputStream rutaLogoCargo = null;//request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
					 
					rutaLogoCargo = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/cargo.jpg").openStream();
		                
					if(rutaLogoCargo == null) {
						rutaLogoCargo = new URL("http://localhost:8080/ventas/_lib/img/cargo.jpg").openStream();
			             
					}
					
					Image logoCargo = Image.getInstance(IOUtils.toByteArray(rutaLogoCargo));
					logoCargo.scalePercent(16f);
					logoCargo.setAbsolutePosition(60f, 800f);
					logoCargo.setAlignment(Element.ALIGN_LEFT);
					documentoPDF.add(logoCargo);
					
				}
				
				PdfPTable table = new PdfPTable(4);
				table.setWidthPercentage(33f);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				Map<String, Object> mapaVentas = new HashMap<>();
				
				V_Ventas_FacturacionBean  ventaImages = new V_Ventas_FacturacionBean();
				
				mapaVentas = facturacionservice.SQL_SELECT_VENTA_APROBADA_FACTURADOR(nro, tipoOperacion);
				
				ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), tipoOperacion);
				
				
				if(mapaVentas.get("EnvioSunat").toString().trim().equals("1")){
				
					if(ventaImages.getImageQRActualizada().trim().equals("N")){
						
						
						ZipInputStream zisEnvio = new ZipInputStream(new FileInputStream(facturacionEmpresa.getRutaEnvioSunat()+mapaVentas.get("DocumentoZip").toString()));
						ZipEntry entrada;
					    
					    while (null != (entrada = zisEnvio.getNextEntry())){
					    	
					        byte[] buf = new byte[1024];
					        int len;
					        StringBuffer contentXml = new StringBuffer();
					        
					        if(!entrada.isDirectory()) {
					        	
						        while ((len = zisEnvio.read(buf)) > 0) {
						        	contentXml.append(new String(buf, 0, len, "UTF-8"));
						        }
						            
						        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
							    InputSource inputSource = new InputSource();
							    inputSource.setCharacterStream(new StringReader(contentXml.toString()));

							    Document doc = documentBuilder.parse(inputSource);
							    NodeList nodeListhash = doc.getElementsByTagName("ds:DigestValue");
							    Node nodohash = nodeListhash.item(0);
							    
								if (nodohash instanceof org.w3c.dom.Element){
									
										Map<String, Object> respuestaXML = new HashMap<>();
										respuestaXML.put("codehash", nodohash.getTextContent().trim());
										
										ventasfacturacionservice.SQL_UPDATE_VENTAS_FACTURACION_IMAGES(Integer.parseInt(mapaVentas.get("Nro").toString()), tipoOperacion, respuestaXML.get("codehash").toString(),GeneraDocumentoFe.CodigoBarras(facturacionEmpresa, mapaVentas, respuestaXML), GeneraDocumentoFe.CodigoQR(facturacionEmpresa, mapaVentas, respuestaXML));
									
									}
							  }
					     }
					       
						zisEnvio.close();
						
						ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), tipoOperacion);
						
					}
					
				}
				
				
				response.setHeader("Content-Disposition", "attachment;filename="+mapaVentas.get("DocumentoZip").toString().replace(".zip", ".pdf"));
				response.setHeader("Content-Type", "application/pdf");
				
				if(tipoOperacion.trim().equals("E")){
					
					table = FuncionesFacturacionPDF.F_Facturacion_Electronica_Encomiendas_Ticket(table,facturacionEmpresa,mapaVentas,ventaImages);	
					
				}else{
					
					table = FuncionesFacturacionPDF.F_Facturacion_Electronica_Pasajes_Ticket(table,facturacionEmpresa,mapaVentas,ventaImages);
					
				}
				
				documentoPDF.add(table);
				documentoPDF.close();
				
				ServletOutputStream outputStream = response.getOutputStream() ; 
				baos.writeTo(outputStream);
				outputStream.flush(); 
		        outputStream.close(); 
		        baos.flush();
		        baos.close();
				
				
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
		
	}
		@Action(value = "ccdf8a28d6554fe3af3cc0aa84", results = { 
		@Result(name = SUCCESS, type="stream" ,
				params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"normal.pdf\"", "bufferSize", "1024" })})
				public void generaPDFNormal() throws ParseException{
			
			
				com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				
				try {
					
					
					PdfWriter.getInstance(documentoPDF, baos);
					documentoPDF.open();
					
					
					V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(empresa);
					
					Image logo = null;
					
					if(facturacionEmpresa.getEmpresa().trim().equals("002")){
						
						
					//	InputStream rutaLogoPalomino = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
						
						InputStream rutaLogoPalomino = null;//request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
						 
						rutaLogoPalomino = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/palomino.jpg").openStream();
			                
						if(rutaLogoPalomino == null) {
							rutaLogoPalomino = new URL("http://localhost:8080/ventas/_lib/img/palomino.jpg").openStream();
				             
						}
						
						logo = Image.getInstance(IOUtils.toByteArray(rutaLogoPalomino));
						logo.scalePercent(12f);
						logo.setAbsolutePosition(35f, 810f);
						logo.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
						
						
					}else if (facturacionEmpresa.getEmpresa().trim().equals("004")){
						
						//InputStream rutaLogoWari = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"wari.jpg");
						
						InputStream rutaLogoWari = null;//request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
						 
						rutaLogoWari = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/wari.jpg").openStream();
			                
						if(rutaLogoWari == null) {
							rutaLogoWari = new URL("http://localhost:8080/ventas/_lib/img/wari.jpg").openStream();
				             
						}
						
						logo = Image.getInstance(IOUtils.toByteArray(rutaLogoWari));
						logo.scalePercent(15f);
						logo.setAbsolutePosition(55f, 810f);
						logo.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
						
					}
					else if (facturacionEmpresa.getEmpresa().trim().equals("003")){
						
						
					//	InputStream rutaLogoCargo = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"cargo.jpg");
						InputStream rutaLogoCargo = null;//request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
						 
						rutaLogoCargo = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/cargo.jpg").openStream();
			                
						if(rutaLogoCargo == null) {
							rutaLogoCargo = new URL("http://localhost:8080/ventas/_lib/img/cargo.jpg").openStream();
				             
						}
						
						logo = Image.getInstance(IOUtils.toByteArray(rutaLogoCargo));
						logo.scalePercent(16f);
						logo.setAbsolutePosition(60f, 800f);
						logo.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
						
					}
					
					Map<String, Object> mapaVentas = new HashMap<>();
					
					V_Ventas_FacturacionBean  ventaImages = new V_Ventas_FacturacionBean();
					
					mapaVentas = facturacionservice.SQL_SELECT_VENTA_APROBADA_FACTURADOR(nro, tipoOperacion);
					
					ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), tipoOperacion);
					
					if(mapaVentas.get("EnvioSunat").toString().trim().equals("1")){
					
						if(ventaImages.getImageQRActualizada().trim().equals("N")){
							
							
							ZipInputStream zisEnvio = new ZipInputStream(new FileInputStream(facturacionEmpresa.getRutaEnvioSunat()+mapaVentas.get("DocumentoZip").toString()));
							ZipEntry entrada;
						    
						    while (null != (entrada = zisEnvio.getNextEntry())){
						    	
						        byte[] buf = new byte[1024];
						        int len;
						        StringBuffer contentXml = new StringBuffer();
						        
						        if(!entrada.isDirectory()) {
						        	
							        while ((len = zisEnvio.read(buf)) > 0) {
							        	contentXml.append(new String(buf, 0, len, "UTF-8"));
							        }
							            
							        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
								    InputSource inputSource = new InputSource();
								    inputSource.setCharacterStream(new StringReader(contentXml.toString()));

								    Document doc = documentBuilder.parse(inputSource);
								    NodeList nodeListhash = doc.getElementsByTagName("ds:DigestValue");
								    Node nodohash = nodeListhash.item(0);
								    
									if (nodohash instanceof org.w3c.dom.Element){
										
											Map<String, Object> respuestaXML = new HashMap<>();
											respuestaXML.put("codehash", nodohash.getTextContent().trim());
											
											ventasfacturacionservice.SQL_UPDATE_VENTAS_FACTURACION_IMAGES(Integer.parseInt(mapaVentas.get("Nro").toString()), tipoOperacion, respuestaXML.get("codehash").toString(), null, GeneraDocumentoFe.CodigoQR(facturacionEmpresa, mapaVentas, respuestaXML));
										
										}
								  }
						     }
						       
							zisEnvio.close();
							
							ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), tipoOperacion);
							
						}
						
					}
					
					
					response.setHeader("Content-Disposition", "attachment;filename="+mapaVentas.get("DocumentoZip").toString().replace(".zip", ".pdf"));
					response.setHeader("Content-Type", "application/pdf");
					
					
					PdfPTable table = new PdfPTable(7);
					table.setWidthPercentage(110f);
					
					table = FuncionesFacturacionPDF.F_Facturacion_Electronica_FormatoGrande(table, facturacionEmpresa, mapaVentas, ventaImages, logo);
					
					documentoPDF.add(table);
					 
					documentoPDF.close();
					ServletOutputStream outputStream = response.getOutputStream() ; 
					baos.writeTo(outputStream);
					 
				 	outputStream.flush(); 
			        outputStream.close(); 
			        baos.flush();
			        baos.close();
					
					
				} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
				}
			
			
		}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getSerie() {
		return serie;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNumero() {
		return numero;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getTipodocumento() {
		return tipodocumento;
	}
	
	public void setMapaJSONResultado(Map<String, Object> mapaJSONResultado) {
		this.mapaJSONResultado = mapaJSONResultado;
	}
	public Map<String, Object> getMapaJSONResultado() {
		return mapaJSONResultado;
	}
	public void setNro(Integer nro) {
		this.nro = nro;
	}
	public Integer getNro() {
		return nro;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	
	public void setMapaJSONResultadoIDA(Map<String, Object> mapaJSONResultadoIDA) {
		this.mapaJSONResultadoIDA = mapaJSONResultadoIDA;
	}
	public Map<String, Object> getMapaJSONResultadoIDA() {
		return mapaJSONResultadoIDA;
	}
	public void setFechaemision(String fechaemision) {
		this.fechaemision = fechaemision;
	}
	public String getFechaemision() {
		return fechaemision;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRuc() {
		return ruc;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getLimit() {
		return limit;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getOffset() {
		return offset;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal() {
		return total;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return query;
	}
	public void setMensajeServer(String mensajeServer) {
		this.mensajeServer = mensajeServer;
	}
	public String getMensajeServer() {
		return mensajeServer;
	}
	public void setRespuestaServer(boolean respuestaServer) {
		this.respuestaServer = respuestaServer;
	}
	public boolean isRespuestaServer() {
		return respuestaServer;
	}
}
