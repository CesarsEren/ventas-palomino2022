package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.itextpdf.text.Document;
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
import pe.com.grupopalomino.sistema.boletaje.util.Encryptor;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesFacturacionPDF;
import pe.com.grupopalomino.sistema.boletaje.util.GeneraDocumentoFe;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@ParentPackage(value = "BoletajePalomino03")
public class FacturacionDescargaClienteAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String mensajeServer = "";
	private boolean respuestaServer = false;
	private String em,fechaemision,serie,numero,tipodocumento,ope,ruc,id;
	private double monto;
	private Map<String, Object> rows  = new HashMap<>();
	private FacturacionService facturacionservice = new FacturacionServiceI();
	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
	private V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI();
	private static final Log log = LogFactory.getLog(FacturacionDescargaClienteAction.class);
	
	
	@Action(value = "59f788970fNTlmNzg4OTcwZmY0NDlmZmE2ZWFiYmEyNDM4YWJlMmMxNTIwNjMzMDM4OTU1", results = {@Result(name = SUCCESS, location = "facturacionelectronicaclientes/documentosclientes.jsp")})
	public String documentosenviados(){
		return SUCCESS;
	}
	

	@Action(value = "59f788970fNTlmNzg4OTcwZm", results = {@Result(name = SUCCESS, type = "json")})
	public String cosultadocumentos1(){
		
		List<Map<String, Object>> lstDatos = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> ventasMap = new ArrayList<Map<String, Object>>();
		
		try {
			
			if(fechaemision == null || fechaemision.trim().isEmpty()){
				respuestaServer = false;
				mensajeServer = "Debe ingresar una fecha de emision.";
	    		return  SUCCESS;
			}
			if(tipodocumento == null){
				respuestaServer = false;
				mensajeServer = "Debe seleccionar un tipo de documento.";
	    		return  SUCCESS;
			}
			if(serie == null || serie.trim().isEmpty()){
				respuestaServer = false;
				mensajeServer = "Debe ingresar la serie del documento.";
	    		return  SUCCESS;
			}
			if(numero == null || numero.trim().isEmpty()){
				respuestaServer = false;
				mensajeServer = "Debe ingresar el numero del documento.";
	    		return  SUCCESS;
			}
			
			if (!(serie.substring(0,1).trim().toUpperCase().equals("F"))){
				if (!(serie.substring(0,1).trim().toUpperCase().equals("B"))){
					respuestaServer = false;
					mensajeServer = "Debe de anteponer la letra F ó B.";
		    		return  SUCCESS;
				}
			}
			
			if(serie.substring(0,1).trim().toUpperCase().equals("F")){
				if(!tipodocumento.trim().equals("01")&& !tipodocumento.trim().equals("03")&& !tipodocumento.trim().equals("05")&& !tipodocumento.trim().equals("06")){
					respuestaServer = false;
					mensajeServer = "Tipo de documento no permitido para una factura.";
		    		return  SUCCESS;	
				}
				
			}
			if(serie.substring(0,1).trim().toUpperCase().equals("B")){
				if(!tipodocumento.trim().equals("02") && !tipodocumento.trim().equals("04")){
					respuestaServer = false;
					mensajeServer = "Tipo de documento no permitido para una boleta.";
		    		return  SUCCESS;
				}
			}
			
			if(tipodocumento.trim().equals("01") || tipodocumento.trim().equals("03")|| 
			   tipodocumento.trim().equals("05") || tipodocumento.trim().equals("06")){
				
				if(ruc == null || ruc.trim().isEmpty()){
					respuestaServer = false;
					mensajeServer = "Debe ingresar un número de ruc.";
		    		return  SUCCESS;
				}
			}
			
			ventasMap = facturacionservice.SQL_SELECT_VENTA_DESCARGA_FACTURADOR_SQL(em, Utils.FormatoFecha(fechaemision.toString()), serie.substring(1,4), numero, tipodocumento, ruc, monto);
				
				
				if(ventasMap.size()== 0){
					respuestaServer = false;
					mensajeServer = "No se encontraron resultados.";
		    		return  SUCCESS;	
				}
				
				for (Map<String, Object> map : ventasMap) {
					
					Map<String, Object>  mapa =  new HashMap<>();
		    		mapa.put("id",Encryptor.CifrarBase64(map.get("Nro").toString()));
		    		mapa.put("fechaEmision", map.get("FechaEmisionD"));
		    		mapa.put("em", Encryptor.CifrarBase64(map.get("Empresa").toString()));
		    		mapa.put("documentoElectronico", map.get("DocumentoElectronico").toString());
		    		mapa.put("agencia", map.get("AgenciaD"));
		    		mapa.put("tipo", map.get("Servicio").toString());
		    		mapa.put("servicio", Encryptor.CifrarBase64(map.get("Servicio").toString()));
		    		mapa.put("data1", Encryptor.CifrarBase64(Utils.URL_PDF_TICKET_CLIENTES));
		    		mapa.put("data2", Encryptor.CifrarBase64(Utils.URL_XML_CLIENTES));
		    		mapa.put("data3", Encryptor.CifrarBase64(Utils.URL_PDF_NROMAL_CLIENTES));
		    		mapa.put("enviado", map.get("Enviado"));
		  		 	mapa.put("estado", "S");
		  		 	lstDatos.add(mapa);
				}
			
			rows.put("rows", lstDatos);
	    	respuestaServer = true;
	    	
		} catch (Exception e) {
			respuestaServer = false;
			mensajeServer = "Ocurrio un error al generar la consulta.";
    		log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
		
	}
	
			@Action(value = "ZDNzYzRyZzRkM2QwY3VtM3QwczNsM2N0cjBuMWMwc3htbGNsMTNudDNz", results = { 
			@Result(name = SUCCESS, type="stream" ,
					params = { "contentType", "application/zip", "inputName", "inputStream", "contentDisposition", "filename=\"test.zip\"", "bufferSize", "1024" })})
					public void generaXML() throws ParseException{
				
					String number = UUID.randomUUID().toString();
					
					response.setHeader("Content-Disposition", "attachment;filename="+number.toString()+".zip");
					response.setHeader("Content-Type", "application/zip");
					
					try {
						
						V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(Encryptor.DescifrarBase64(em));
						
						Map<String, Object> mapaVentas = new HashMap<>();
						
						//mapaVentas = facturacionservice.USP_SELECT_VENTA_APROBADA_X_NRO_FACTURADOR_SQL(Integer.parseInt(Encryptor.DescifrarBase64(id)) ,Encryptor.DescifrarBase64(ope));
						
						mapaVentas = facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.parseInt(Encryptor.DescifrarBase64(id)) ,Encryptor.DescifrarBase64(ope));
						
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
			
			@Action(value = "ZDNzYzRyZzRkM2QwY3VtM3QwczNsM2N0cjBuMWMwc3htbGNsMTNudDNc", results = { 
			@Result(name = SUCCESS, type="stream" ,
					params = { "contentType", "application/zip", "inputName", "inputStream", "contentDisposition", "filename=\"test.zip\"", "bufferSize", "1024" })})
					public void generacdr() throws ParseException{
				
					String number = UUID.randomUUID().toString();
					
					
					
					try {
						
						V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(Encryptor.DescifrarBase64(em));
						
						Map<String, Object> mapaVentas = new HashMap<>();
						
						//mapaVentas = facturacionservice.USP_SELECT_VENTA_APROBADA_X_NRO_FACTURADOR_SQL(Integer.parseInt(Encryptor.DescifrarBase64(id)) ,Encryptor.DescifrarBase64(ope));
						
						mapaVentas = facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.parseInt(Encryptor.DescifrarBase64(id)) ,Encryptor.DescifrarBase64(ope));
						
						response.setHeader("Content-Disposition", "attachment;filename=R-"+mapaVentas.get("DocumentoZip").toString()+".zip");
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
	
			@Action(value = "ZDNzYzRyZzRkM2QwY3VtM3QwczNsM2N0cjBuMWMwc3QxY2szdGNsMTNudDNz", results = { 
			@Result(name = SUCCESS, type="stream" ,
					params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"ticket.pdf\"", "bufferSize", "1024" })})
					public void generaPDFTicket() throws ParseException{
				
					String number = UUID.randomUUID().toString();
					
					response.setHeader("Content-Disposition", "attachment;filename="+number.toString()+".pdf");
					response.setHeader("Content-Type", "application/pdf");
					
					
					com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document(PageSize.A4,(float) 2.5,0,50,0);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					
					
					try {
						
						PdfWriter.getInstance(documentoPDF, baos);
						documentoPDF.open();
						
						V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(Encryptor.DescifrarBase64(em));
						
						if(facturacionEmpresa.getEmpresa().trim().equals("002")){
							
							
							InputStream rutaLogoPalomino = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
							
							Image logoPalomino = Image.getInstance(IOUtils.toByteArray(rutaLogoPalomino));
							logoPalomino.scalePercent(12f);
							logoPalomino.setAbsolutePosition(35f, 810f);
							logoPalomino.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
							documentoPDF.add(logoPalomino);
							
							
						}else if (facturacionEmpresa.getEmpresa().trim().equals("004")){
							
							InputStream rutaLogoWari = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"wari.jpg");
							
							Image logoWari = Image.getInstance(IOUtils.toByteArray(rutaLogoWari));
							logoWari.scalePercent(15f);
							logoWari.setAbsolutePosition(55f, 810f);
							logoWari.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
							documentoPDF.add(logoWari);
							
						}
						else if (facturacionEmpresa.getEmpresa().trim().equals("003")){
							
							
							InputStream rutaLogoCargo = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"cargo.jpg");
							
							Image logoCargo = Image.getInstance(IOUtils.toByteArray(rutaLogoCargo));
							logoCargo.scalePercent(16f);
							logoCargo.setAbsolutePosition(60f, 800f);
							logoCargo.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
							documentoPDF.add(logoCargo);
							
						}
						
						
						PdfPTable table = new PdfPTable(4);
						table.setWidthPercentage(33f);
						table.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
						
						
						Map<String, Object> mapaVentas = new HashMap<>();
						
						V_Ventas_FacturacionBean  ventaImages = new V_Ventas_FacturacionBean();
						
						
						mapaVentas = facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.parseInt(Encryptor.DescifrarBase64(id)), Encryptor.DescifrarBase64(ope));
						
						ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), Encryptor.DescifrarBase64(ope));
						
						
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

									    org.w3c.dom.Document doc = documentBuilder.parse(inputSource);
									    NodeList nodeListhash = doc.getElementsByTagName("ds:DigestValue");
									    Node nodohash = nodeListhash.item(0);
									    
										if (nodohash instanceof org.w3c.dom.Element){
											
												Map<String, Object> respuestaXML = new HashMap<>();
												respuestaXML.put("codehash", nodohash.getTextContent().trim());
												
												ventasfacturacionservice.SQL_UPDATE_VENTAS_FACTURACION_IMAGES(Integer.parseInt(mapaVentas.get("Nro").toString()), Encryptor.DescifrarBase64(ope).toString(), respuestaXML.get("codehash").toString(), null, GeneraDocumentoFe.CodigoQR(facturacionEmpresa, mapaVentas, respuestaXML));
											
											}
									  }
							     }
							       
								zisEnvio.close();
								
								ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), Encryptor.DescifrarBase64(ope).toString());
								
							}
							
						}
						
						
						
						if(Encryptor.DescifrarBase64(ope).trim().equals("E")){
							
							table = FuncionesFacturacionPDF.F_Facturacion_Electronica_Encomiendas_Ticket(table,facturacionEmpresa,mapaVentas,ventaImages);	
							
						}else{
							
							table = FuncionesFacturacionPDF.F_Facturacion_Electronica_Pasajes_Ticket(table, facturacionEmpresa, mapaVentas, ventaImages);
							
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
	
			@Action(value = "ZDNzYzRyZzRkM2QwY3VtM3QwczNsM2N0cjBuMWMwc24wcm00bGNsMTNudDNz", results = { 
			@Result(name = SUCCESS, type="stream" ,
					params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"normal.pdf\"", "bufferSize", "1024" })})
					public void generaPDFNormal() throws ParseException{
		
					
				String number = UUID.randomUUID().toString();
				
				response.setHeader("Content-Disposition", "attachment;filename="+number.toString()+".pdf");
				response.setHeader("Content-Type", "application/pdf");
				
				Document documentoPDF = new Document();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				try {
					
					PdfWriter.getInstance(documentoPDF, baos);
					documentoPDF.open();
					
					V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(Encryptor.DescifrarBase64(em));
					
					Image logo = null;
					
					if(facturacionEmpresa.getEmpresa().trim().equals("002")){
						
						InputStream rutaLogoPalomino = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"palomino.jpg");
						logo = Image.getInstance(IOUtils.toByteArray(rutaLogoPalomino));
						logo.scalePercent(12f);
						logo.setAbsolutePosition(35f, 810f);
						logo.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
						
						
					}else if (facturacionEmpresa.getEmpresa().trim().equals("004")){
						
						InputStream rutaLogoWari = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"wari.jpg");
						logo = Image.getInstance(IOUtils.toByteArray(rutaLogoWari));
						logo.scalePercent(15f);
						logo.setAbsolutePosition(55f, 810f);
						logo.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
						
					}
					else if (facturacionEmpresa.getEmpresa().trim().equals("003")){
						
						InputStream rutaLogoCargo = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"cargo.jpg");
						logo = Image.getInstance(IOUtils.toByteArray(rutaLogoCargo));
						logo.scalePercent(16f);
						logo.setAbsolutePosition(60f, 800f);
						logo.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
						
					}
					
					Map<String, Object> mapaVentas = new HashMap<>();
					
					V_Ventas_FacturacionBean  ventaImages = new V_Ventas_FacturacionBean();
					
					//mapaVentas = facturacionservice.USP_SELECT_VENTA_APROBADA_X_NRO_FACTURADOR_SQL(Integer.parseInt(Encryptor.DescifrarBase64(id)),Encryptor.DescifrarBase64(ope));
					
					mapaVentas = facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.parseInt(Encryptor.DescifrarBase64(id)), Encryptor.DescifrarBase64(ope));
					
					ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()),Encryptor.DescifrarBase64(ope));
					
					
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

								    org.w3c.dom.Document doc = documentBuilder.parse(inputSource);
								    NodeList nodeListhash = doc.getElementsByTagName("ds:DigestValue");
								    Node nodohash = nodeListhash.item(0);
								    
									if (nodohash instanceof org.w3c.dom.Element){
										
											Map<String, Object> respuestaXML = new HashMap<>();
											respuestaXML.put("codehash", nodohash.getTextContent().trim());
											
											ventasfacturacionservice.SQL_UPDATE_VENTAS_FACTURACION_IMAGES(Integer.parseInt(mapaVentas.get("Nro").toString()), Encryptor.DescifrarBase64(ope).toString(), respuestaXML.get("codehash").toString(), null, GeneraDocumentoFe.CodigoQR(facturacionEmpresa, mapaVentas, respuestaXML));										
										}
								  }
						        
						     }
						       
							zisEnvio.close();
							
							ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), Encryptor.DescifrarBase64(ope).toString());
							
						}
						
					}
					
					
					
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
	public void setRespuestaServer(boolean respuestaServer) {
		this.respuestaServer = respuestaServer;
	}
	public boolean isRespuestaServer() {
		return respuestaServer;
	}
	
	public void setMensajeServer(String mensajeServer) {
		this.mensajeServer = mensajeServer;
	}
	public String getMensajeServer() {
		return mensajeServer;
	}
	public void setEm(String em) {
		this.em = em;
	}
	public String getEm() {
		return em;
	}
	
	public void setFechaemision(String fechaemision) {
		this.fechaemision = fechaemision;
	}
	public String getFechaemision() {
		return fechaemision;
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
	public void setOpe(String ope) {
		this.ope = ope;
	}
	public String getOpe() {
		return ope;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRows(Map<String, Object> rows) {
		this.rows = rows;
	}
	public Map<String, Object> getRows() {
		return rows;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getMonto() {
		return monto;
	}
	

}
