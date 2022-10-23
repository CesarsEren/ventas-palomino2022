package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.ByteArrayOutputStream;    
import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;

import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.service.AtencionReclamosService;
import pe.com.grupopalomino.sistema.boletaje.service.AtencionReclamosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesFacturacionPDF;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesPDF;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;



@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','3','2','R')")
public class ReportesAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {

	private Map<String, Object> session;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private CuentaCorrienteService servicecuentacorriente = new CuentaCorrienteServiceI();
	private UsuariosWebService serviceusuario = new UsuariosWebServiceI();
	private AtencionReclamosService service = new AtencionReclamosServiceI();
	private B_VentaBean venta = new B_VentaBean();
	private Integer Nrovoucher,Salida;
	private String FechaInicial,FechaFinal,Tipo,Usuario,tiporeclamo,tiporeclamoTexto;
	private String EnvioCorreo;
	private boolean respuestaserver = false;
	private String mensajeServer ;
	private List<V_Varios> listacantidadPasajeros = new ArrayList<V_Varios>();
	private static final Log log = LogFactory.getLog(ReportesAction.class);
	/************************************************************************************/
	private FacturacionService facturacionservice = new FacturacionServiceI();
	private V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI();
	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
	/*************************************************************************************/
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "cancelarvoucherida",results = {@Result (name = SUCCESS , location = "ventaida/ventaboletos.jsp")})
	public String cancelarVoucherIda(){
		Limipiar_SessionIda();
		
		if(session.get("listacantidadPasajeros")== null){
			
			listacantidadPasajeros = Utils.getListaCantidadPasajeros();
			session.put("listacantidadPasajeros", listacantidadPasajeros);
			
		}else{
			listacantidadPasajeros = (List<V_Varios>) session.get("listacantidadPasajeros"); 
			
		}
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	@Action(value = "cancelarvouchervuelta",results = {@Result (name = SUCCESS ,  location = "ventaida/ventaboletos.jsp")})
	public String cancelarVoucherVuelta(){
		Limipiar_SessionIda();
		Limipiar_SessionVuelta();
		
		if(session.get("listacantidadPasajeros")== null){
			
			listacantidadPasajeros = Utils.getListaCantidadPasajeros();
			session.put("listacantidadPasajeros", listacantidadPasajeros);
			
		}else{
			listacantidadPasajeros = (List<V_Varios>) session.get("listacantidadPasajeros"); 
			
		}
		return SUCCESS;
	}
	
	@SuppressWarnings({"unchecked"})
	@Action(value = "generarvoucherIdaSTOP", results = { 
			@Result(name = SUCCESS, type="stream" ,params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" })
			})
			public void generarvoucherIda() throws ParseException{
				
				
				try {
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition","attachment;filename=Voucher - "+sdf1.format(new Date())+".pdf");
					Document documento = new Document();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					
					int contador = 0;
					
					
				  List<ListaPreVenta> ListaPreventaIda	= ((session.get("ListaPreventaIda"))== null ? new ArrayList<ListaPreVenta>() :(List<ListaPreVenta>) session.get("ListaPreventaIda"));
				  SpringSecurityUser usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				  B_VentaBean bean =  new B_VentaBean();
				  
				  if(ListaPreventaIda.size()>0){
					  
					  PdfWriter.getInstance(documento, baos);
					  documento.open();  
				  }
				  
				  	
				  for (ListaPreVenta venta : ListaPreventaIda) {
					  
					  if(usuario!=null){
						  
						  bean =  serviceventa.getVentaImprimir(usuario.getUsername(),venta.getNro(),venta.getSalida());
						  
						  if(bean != null){
							  
							  //InputStream rutaIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
							  
							  InputStream rutaIda = null;
								
								if(bean.getTipoEmpresa()== 1){
								
									rutaIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
									
								}else if(bean.getTipoEmpresa()== 2){
									
									rutaIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Poseidon.jpg");
									
								}
								
								//GENERAMOS EL VOUCHER DE IDA   ___________________________________________________________________________________________________________________________________
								Image logoIda = Image.getInstance(IOUtils.toByteArray(rutaIda));
								logoIda.scalePercent(22f);
								logoIda.setAbsolutePosition(20f, 610f);
								logoIda.setAlignment(Element.ALIGN_LEFT);
								 
								documento.add(logoIda);
										
								PdfPTable tableIda = new PdfPTable(35);
								
								tableIda=FuncionesPDF.F_Reporte_Ventas(tableIda, bean.getDestinoD().trim(), bean.getOrigen().trim(), bean.getServicio().trim(), bean.getNombre().trim(),
																		bean.getDNI(),bean.getNro(), bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje().substring(0, 10),
																		bean.getHoraViaje(),"N",bean.getFechaEmision(),bean.getPrecioAct(),bean.getRuc(),bean.getRazon(),bean.getHoraCompra(),"",null,null,null,null,bean.getUsuario(),bean.getDestinoRutaD());
										
								documento.add(tableIda);
							    
							    //InputStream rutaplantillafooterIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");
								
								InputStream rutaplantillafooterIda = null;
								
								if(bean.getTipoEmpresa()== 1){
								
									rutaplantillafooterIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");
									
								}else if(bean.getTipoEmpresa()== 2){
									
									rutaplantillafooterIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base_Poseidon.jpg");
									
								}
								
								Image logofooterIda = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterIda));
								logofooterIda.scalePercent(23f);
								logofooterIda.setAbsolutePosition(5f, 100f);
								logofooterIda.setAlignment(Element.ALIGN_LEFT);
								
								documento.add(logofooterIda);
								contador = contador +1;
								
								if(contador != ListaPreventaIda.size()){
									documento.newPage();
								}
							}
					  	}
					}
				  
					documento.close();
					ServletOutputStream outputStream = response.getOutputStream() ; 
					baos.writeTo(outputStream);
					
					response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\""); 
			        response.setContentType("application/pdf"); 
			        outputStream.flush(); 
			        outputStream.close(); 
			        baos.flush();
			        baos.close();
			        Limipiar_SessionIda();
			    } catch (Exception e) {
			    	Limipiar_SessionIda();
			    	log.info(Utils.printStackTraceToString(e));
				}
				
			}
	@SuppressWarnings({"unchecked"})
	@Action(value = "generarvoucherIda", results = { 
	@Result(name = SUCCESS, type="stream" ,params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" })})
	public ByteArrayOutputStream DescargaPDFTicket() {
		Rectangle tamaño = new Rectangle(250,800);
		com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document();
		documentoPDF.setPageSize(tamaño);
		documentoPDF.setMargins(0,0.5f,0,0.5f);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment;filename=Voucher - "+sdf1.format(new Date())+".pdf");
			//Document documento = new Document();
			//ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			List<ListaPreVenta> ListaPreventaIda = ((session.get("ListaPreventaIda"))== null ? new ArrayList<ListaPreVenta>() :(List<ListaPreVenta>) session.get("ListaPreventaIda"));
			List<String> ListEmpresaIda = ((session.get("ListEmpresaIda"))== null ? new ArrayList<String>() :(List<String>) session.get("ListEmpresaIda"));
			
			if (ListaPreventaIda.size() > 0) {
				PdfWriter.getInstance(documentoPDF, baos);
				documentoPDF.open();
			}
			int contador = 0;
			//String em = session.get("Empresa")==null?"002":session.get("ListEmpresaIda").toString();			
			for (ListaPreVenta item: ListaPreventaIda) {
				String id = ""+item.getNro();
				String em = ListEmpresaIda.get(contador);
				V_Varios_FacturacionBean facturacionEmpresa = variosservice.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(em);

				InputStream rutaLogoPalomino;
				if (em.equals("002")) {
					rutaLogoPalomino = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "palomino.jpg");
				} else {
					rutaLogoPalomino = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "Transporte_Wari.png");
				}
				Image logoPalomino = Image.getInstance(IOUtils.toByteArray(rutaLogoPalomino));
				logoPalomino.scalePercent(12f);
				//logoPalomino.setAbsolutePosition(35f, 810f);
				logoPalomino.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
				documentoPDF.add(logoPalomino);

				PdfPTable table = new PdfPTable(4);
				table.setWidthPercentage(100f);
				table.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);

				Map<String, Object> mapaVentas = new HashMap<>();

				V_Ventas_FacturacionBean ventaImages = new V_Ventas_FacturacionBean();

				mapaVentas = facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.parseInt(id), "B");

				ventaImages = ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()),"B");

				// VERIFICAR SI PONER O NO +-
				/*
				 * if (ope.trim().equals("E")) { table =
				 * FuncionesFacturacionPDF.
				 * F_Facturacion_Electronica_Encomiendas_Ticket(table,
				 * facturacionEmpresa, mapaVentas, ventaImages); } else {
				 */
				table = FuncionesFacturacionPDF.F_Facturacion_Electronica_Pasajes_Ticket(table, facturacionEmpresa,
						mapaVentas, ventaImages);

				documentoPDF.add(table);
				contador++;
				if (contador != ListaPreventaIda.size()) {
					documentoPDF.newPage();
				}
			}
			
		        
			documentoPDF.close();
			ServletOutputStream outputStream = response.getOutputStream() ; 
			baos.writeTo(outputStream);
			response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\""); 
	        response.setContentType("application/pdf"); 	
	        outputStream.close();
	        outputStream.flush();
			baos.flush();
			baos.close();

			
			
			   Limipiar_SessionIda();
		} catch (Exception e) {

			   Limipiar_SessionIda();
			log.info(Utils.printStackTraceToString(e));
		}
		session.remove("ListEmpresaIda");
		session.remove("ListEmpresaAll");  
		
		return baos;
	}
	
	@SuppressWarnings({ "unchecked"})
	@Action(value = "/generarvoucherIdaVuelta", results = { 
			@Result(name = SUCCESS, type="stream" ,params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" })})
	public String DescargaPDFTicketIdaVuelta() {
		Rectangle tamaño = new Rectangle(250,800);
		com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document();
		documentoPDF.setPageSize(tamaño);
		documentoPDF.setMargins(0,0.5f,0,0.5f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment;filename=Voucher - "+sdf1.format(new Date())+".pdf");
			
			List<ListaPreVenta> ListaPreventaAll = new ArrayList<>();
			List<ListaPreVenta> ListaPreventaIda = ((session.get("ListaPreventaIda"))== null ? new ArrayList<ListaPreVenta>() :(List<ListaPreVenta>) session.get("ListaPreventaIda"));
			List<ListaPreVenta> ListaPreventaIdaVuelta = ((session.get("ListaPreventaIdaVuelta"))== null ? new ArrayList<ListaPreVenta>() :(List<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));
			ListaPreventaAll.addAll(ListaPreventaIda);
			ListaPreventaAll.addAll(ListaPreventaIdaVuelta);		
			List<String> ListEmpresaAll = ((session.get("ListEmpresaAll"))== null ? new ArrayList<String>() :(List<String>) session.get("ListEmpresaAll"));
			
			if (ListEmpresaAll.size() > 0) {
				PdfWriter.getInstance(documentoPDF, baos);
				documentoPDF.open();
			}
			int contador = 0;

			for (ListaPreVenta item : ListaPreventaAll) {
				String id = ""+item.getNro();
				String em = ListEmpresaAll.get(contador);
				/**/
				V_Varios_FacturacionBean facturacionEmpresa = variosservice
						.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(em);
				InputStream rutaLogoPalomino;
				if (em.equals("002")) {
					rutaLogoPalomino = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "palomino.jpg");
				} else {
					rutaLogoPalomino = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "Transporte_Wari.png");
				}
				Image logoPalomino = Image.getInstance(IOUtils.toByteArray(rutaLogoPalomino));
				logoPalomino.scalePercent(12f);
				//logoPalomino.setAbsolutePosition(35f, 810f);
				logoPalomino.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
				documentoPDF.add(logoPalomino);

				PdfPTable table = new PdfPTable(4);
				table.setWidthPercentage(100f);
				table.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);

				Map<String, Object> mapaVentas = new HashMap<>();

				V_Ventas_FacturacionBean ventaImages = new V_Ventas_FacturacionBean();

				mapaVentas = facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.parseInt(id), "B");

				ventaImages = ventasfacturacionservice
						.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), "B");

				table = FuncionesFacturacionPDF.F_Facturacion_Electronica_Pasajes_Ticket(table, facturacionEmpresa,
						mapaVentas, ventaImages);

				documentoPDF.add(table);
				contador++;
				if (contador != ListEmpresaAll.size()) {
					documentoPDF.newPage();
				}
			}
			documentoPDF.close();
			
			ServletOutputStream outputStream = response.getOutputStream() ; 
			baos.writeTo(outputStream);
			response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\""); 
	        response.setContentType("application/pdf"); 	
	        outputStream.close();
	        outputStream.flush();
	        
			baos.flush();
			baos.close();
			Limipiar_SessionVuelta();
		} catch (Exception e) {
			Limipiar_SessionVuelta();
			log.info(Utils.printStackTraceToString(e));
		}
		session.remove("ListEmpresaIda");
		session.remove("ListEmpresaAll");
		return SUCCESS;
	}
	
	@SuppressWarnings({ "unchecked"})
	@Action(value = "/generarvoucherIdaVueltaSTOP", results = { 
			@Result(name = SUCCESS, type="stream" ,params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" })})
			public String generarvoucherIdaVuelta() throws ParseException{
				
		
		try {
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment;filename=Voucher - "+sdf1.format(new Date())+".pdf");
			Document documento = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int contador = 0;
		
			 List<ListaPreVenta> ListaPreventaIda	= ((session.get("ListaPreventaIda"))== null ? new ArrayList<ListaPreVenta>() :(List<ListaPreVenta>) session.get("ListaPreventaIda"));
			 List<ListaPreVenta> ListaPreventaIdaVuelta	= ((session.get("ListaPreventaIdaVuelta"))== null ? new ArrayList<ListaPreVenta>() :(List<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));
			 SpringSecurityUser usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 B_VentaBean bean =  new B_VentaBean();
			  if(ListaPreventaIda.size()>0 && ListaPreventaIdaVuelta.size()>0){
				  
				  PdfWriter.getInstance(documento, baos);
				  documento.open();  
				  
				  
				  for (ListaPreVenta venta : ListaPreventaIda) {
					  
					  
					  if(usuario!=null){
						  bean =  serviceventa.getVentaImprimir(usuario.getUsername(),venta.getNro(),venta.getSalida());
						  
						  if(bean != null){
							  
							  //InputStream rutaIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
							  
							  InputStream rutaIda = null;
								
								if(bean.getTipoEmpresa()== 1){
								
									rutaIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
									
								}else if(bean.getTipoEmpresa()== 2){
									
									rutaIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Poseidon.jpg");
									
								}
								
								//GENERAMOS EL VOUCHER DE IDA   ___________________________________________________________________________________________________________________________________
								Image logoIda = Image.getInstance(IOUtils.toByteArray(rutaIda));
								logoIda.scalePercent(22f);
								logoIda.setAbsolutePosition(20f, 610f);
								logoIda.setAlignment(Element.ALIGN_LEFT);
								
								documento.add(logoIda);
										
								PdfPTable tableIda = new PdfPTable(35);
								
								tableIda=FuncionesPDF.F_Reporte_Ventas(tableIda, bean.getDestinoD().trim(), bean.getOrigen().trim(), bean.getServicio().trim(), bean.getNombre().trim(),
																		bean.getDNI(),bean.getNro(), bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje().substring(0, 10),
																		bean.getHoraViaje(),"N",bean.getFechaEmision(),bean.getPrecioAct(),bean.getRuc(),bean.getRazon(),bean.getHoraCompra(),"",null,null,null,null,bean.getUsuario(),bean.getDestinoRutaD());
										
								documento.add(tableIda);
							    
							    //InputStream rutaplantillafooterIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");
								
								InputStream rutaplantillafooterIda = null;
								
								if(bean.getTipoEmpresa()== 1){
								
									rutaplantillafooterIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");
									
								}else if(bean.getTipoEmpresa()== 2){
									
									rutaplantillafooterIda = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base_Poseidon.jpg");
									
								}
								
								Image logofooterIda = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterIda));
								logofooterIda.scalePercent(23f);
								logofooterIda.setAbsolutePosition(5f, 100f);
								logofooterIda.setAlignment(Element.ALIGN_LEFT);
								
								documento.add(logofooterIda);
								contador = contador +1;
								
								if(contador != ListaPreventaIda.size()){
									documento.newPage();
								}
							}
						}
					}
				  
				  documento.newPage();
				  contador = 0;
				  
				  for (ListaPreVenta ventaVuelta : ListaPreventaIdaVuelta) { 
						
				    	if(usuario!=null){
				    		
				    		bean =  serviceventa.getVentaImprimir(usuario.getUsername(),ventaVuelta.getNro(),ventaVuelta.getSalida());
							
							if(bean != null){
								
								
								//PdfWriter.getInstance(documento, baos);
								//InputStream rutaVuelta = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
								
								InputStream rutaVuelta = null;
								
								if(bean.getTipoEmpresa()== 1){
								
									rutaVuelta = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
									
								}else if(bean.getTipoEmpresa()== 2){
									
									rutaVuelta = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Poseidon.jpg");
									
								}
								
								
								//GENERAMOS EL VOUCHER DE IDA   ___________________________________________________________________________________________________________________________________
								Image logoVuelta = Image.getInstance(IOUtils.toByteArray(rutaVuelta));
								logoVuelta.scalePercent(22f);
								logoVuelta.setAbsolutePosition(20f, 610f);
								logoVuelta.setAlignment(Element.ALIGN_LEFT);
								
								documento.add(logoVuelta);
										
								PdfPTable tableVuelta = new PdfPTable(35);
								
								
								tableVuelta=FuncionesPDF.F_Reporte_Ventas(tableVuelta, bean.getDestinoD().trim(), bean.getOrigen().trim(), bean.getServicio().trim(), bean.getNombre().trim(),
																		bean.getDNI(),bean.getNro(), bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje().substring(0, 10),
																		bean.getHoraViaje(),"N",bean.getFechaEmision(),bean.getPrecioAct(),bean.getRuc(),bean.getRazon(),bean.getHoraCompra(),"",null,null,null,null,bean.getUsuario(),bean.getDestinoRutaD()); 
										
								documento.add(tableVuelta);
							    
							    //InputStream rutaplantillafooterVuelta = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");
								
								InputStream rutaplantillafooterVuelta = null;
								
								if(bean.getTipoEmpresa()== 1){
								
									rutaplantillafooterVuelta = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");
									
								}else if(bean.getTipoEmpresa()== 2){
									
									rutaplantillafooterVuelta = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base_Poseidon.jpg");
									
								}
								
								Image logofooterVuelta = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterVuelta));
								logofooterVuelta.scalePercent(23f);
								logofooterVuelta.setAbsolutePosition(5f, 100f);
								logofooterVuelta.setAlignment(Element.ALIGN_LEFT);
								
								documento.add(logofooterVuelta);
								
								contador = contador +1;
								
								if(contador != ListaPreventaIdaVuelta.size()){
									documento.newPage();
								}
							}
				    	}
				   }
				}
			
				documento.close();
				ServletOutputStream outputStream = response.getOutputStream() ; 
				baos.writeTo(outputStream);
				response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\""); 
		        response.setContentType("application/pdf"); 
		        outputStream.flush(); 
		        outputStream.close(); 
		        baos.flush();
		        baos.close();
		        Limipiar_SessionIda();
		        Limipiar_SessionVuelta();
		        
		        return SUCCESS;
		} catch (Exception e) {
			Limipiar_SessionIda();
	        Limipiar_SessionVuelta();
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS; 
			
	}
	
	@Action(value = "/reimprimirvoucher",results = 
	{@Result(name = SUCCESS,type = "stream",params =
			{ "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" })} ) 
			
	public void ReimprimirVoucher()throws ParseException{
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=voucher'"+sdf1.format(new Date())+"'.pdf");
		Document documento = new Document();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try{
			//SpringSecurityUser usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if((Nrovoucher!= null && Nrovoucher != 0) && (Salida !=null && Salida != 0)){
				
					
					//if(usuario!= null){
						
						
						B_VentaBean bean =  new B_VentaBean();
						
						bean =  serviceventa.getVentaReImprimir(Usuario.trim(),Nrovoucher, Salida);
						
						
						if(bean != null){
							
						
						PdfWriter.getInstance(documento, baos);
						//InputStream rutapalomino = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
						
						InputStream rutapalomino = null;
						
						if(bean.getTipoEmpresa()== 1){
						
							rutapalomino = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
							
						}else if(bean.getTipoEmpresa()== 2){
							
							rutapalomino = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Poseidon.jpg");
							
						}
						
						documento.open();
						
						Image logopalomino = Image.getInstance(IOUtils.toByteArray(rutapalomino));
						logopalomino.scalePercent(22f);
						logopalomino.setAbsolutePosition(20f, 610f);
						logopalomino.setAlignment(Element.ALIGN_LEFT);
						
						documento.add(logopalomino);

						PdfPTable table = new PdfPTable(35);
						
						
						table=FuncionesPDF.F_Reporte_Ventas(table, bean.getDestinoD().trim(), bean.getOrigen().trim(), bean.getServicio().trim(), bean.getNombre().trim(),
						bean.getDNI(),bean.getNro(), bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje(),
						bean.getHoraViaje(),"Y",bean.getFechaEmision(),bean.getPrecioAct(),bean.getRuc(),bean.getRazon(),bean.getHoraCompra(),"",null,null,null,null,bean.getUsuario(),bean.getDestinoRutaD()); 
						documento.add(table);
						
						
						
						//InputStream rutaplantillafooter = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");
						
						InputStream rutaplantillafooter = null;
						
						if(bean.getTipoEmpresa()== 1){
						
							rutaplantillafooter = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");
							
						}else if(bean.getTipoEmpresa()== 2){
							
							rutaplantillafooter = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base_Poseidon.jpg");
							
						}
						
						Image logofooter = Image.getInstance(IOUtils.toByteArray(rutaplantillafooter));
						logofooter.scalePercent(23f);
						logofooter.setAbsolutePosition(5f, 100f); 
						logofooter.setAlignment(Element.ALIGN_LEFT);
						
						documento.add(logofooter);
						
						documento.close();
						ServletOutputStream outputStream = response.getOutputStream() ; 
						baos.writeTo(outputStream);
						
						response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\""); 
						response.setContentType("application/pdf"); 
						outputStream.flush(); 
						outputStream.close(); 
						baos.flush();
						baos.close();
						
					}
				}
			//}
			
		}catch(Exception e){
			log.info(Utils.printStackTraceToString(e));
		}
		
	}
	@Action(value = "imprimirestadocuenta", results = {
	@Result(name = SUCCESS, type="stream" ,params = 
		    { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" })
			})
			public void EstadoCuentaPDF() throws ParseException{
				
				
				try {
					
					BaseFont BebasNeueRegular = BaseFont.createFont(FuncionesPDF.class.getResource("BebasNeueRegular.ttf").toString(), BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
					
					
					Font Fuente = new Font(BebasNeueRegular, 17f, Font.NORMAL,BaseColor.BLACK);
					Font FuenteLabel = new Font(BebasNeueRegular, 11f, Font.NORMAL);
					Font FuenteCabecera = new Font(BebasNeueRegular, 9f, Font.NORMAL);
					Font FuenteDetalle = new Font(BebasNeueRegular, 7f, Font.NORMAL);
					
					SpringSecurityUser usuario = null;
					
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
				    simbolo.setGroupingSeparator(',');
				    DecimalFormat formateador = new DecimalFormat("###,###.##",simbolo);
				    double monto = 0.0 ;
				    
				    if(Usuario!=null && !Usuario.trim().equals("-1")){
				    	
				    	usuario = serviceusuario.obtieneUsuarioXid(Integer.parseInt(Usuario.trim()));
				    	
				    }else{
				    	
				    	if(SecurityContextHolder.getContext().getAuthentication().getName()!=null){
				    		
				    		usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
							
							usuario = serviceusuario.limiteCreditoUsuario(usuario.getUsername()); // (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
							
						}
				    	
				    }
				    
				    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition","attachment;filename=Estado Cuenta'"+sdf1.format(new Date())+"'.pdf");
					Document documento = new Document();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					
					PdfWriter.getInstance(documento, baos);
					InputStream rutapalomino = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"logo_reportes.png");
					
					documento.open();
					
					Image logopalomino = Image.getInstance(IOUtils.toByteArray(rutapalomino));
					logopalomino.scalePercent(5f);
					logopalomino.setAbsolutePosition(485f, 770f);
					logopalomino.setAlignment(Element.ALIGN_LEFT);
					
					documento.add(logopalomino);
					
					Paragraph titulo = new Paragraph("REPORTE DE ESTADO DE CUENTA",new Font(Fuente));
					titulo.setAlignment(Element.ALIGN_CENTER);
					
					documento.add(titulo);
					
					documento.add(Chunk.NEWLINE);
					
					Paragraph Cliente = new Paragraph("AGENTE : " +usuario.getRazonSocial(),new Font(FuenteLabel));
					Cliente.setAlignment(Element.ALIGN_LEFT); 
					
					documento.add(Cliente);
					 
					//documento.add(Chunk.NEWLINE);
				    
				    List<SpringSecurityUser> usuarioAdminAgencia = null;
					List<B_CuentaCorrienteBean> ListaCuenta = new ArrayList<>();
					
					if(usuario!=null){
						
						
						if((!FechaInicial.trim().equals("")) && (!FechaFinal.trim().equals(""))){
							
							FechaInicial = Utils.FormatoFechaReporteEstadouenta(FechaInicial);
							FechaFinal = Utils.FormatoFechaReporteEstadouenta(FechaFinal);
							
						}else{
							FechaInicial = null;
							FechaFinal = null;
							
						}
						
						if(Usuario!=null && Usuario.trim().equals("-1")){
							
							ListaCuenta = servicecuentacorriente.EstadoCuentaCorriente((usuario.getRuc() == null)? "" :usuario.getRuc().trim(),Tipo,FechaInicial,FechaFinal,0,0,"N");
						 	
						 	String Inicial="", Final="";
						 	
						 	if(FechaInicial==null && FechaFinal==null){
								
						 		Inicial = ListaCuenta.get(ListaCuenta.size()-1).getFechaEmision();
						 		Final = (ListaCuenta.get(0).getFechaEmision());
								
							}else{
								
								Inicial = Utils.FormatoFechaReporteISO(FechaInicial);
								Final = Utils.FormatoFechaReporteISO(FechaFinal);
							}
						 
						 	Paragraph Ruc = new Paragraph("N° RUC : " +usuario.getRuc() ,new Font(FuenteLabel));
							Ruc.setAlignment(Element.ALIGN_LEFT); 
							documento.add(Ruc);
							
							//documento.add(Chunk.NEWLINE);
							
							Paragraph Estado = new Paragraph("Desde :  " +Inicial+"   Hasta :  "+Final,new Font(FuenteLabel));
							Estado.setAlignment(Element.ALIGN_LEFT); 
							documento.add(Estado);
							
							Paragraph Linea = new Paragraph("_____________________________________________________________________________________________" ,new Font(FuenteLabel));
							Linea.setAlignment(Element.ALIGN_LEFT); 
							documento.add(Linea);
							
							documento.add(Chunk.NEWLINE);
							 
							 usuarioAdminAgencia = serviceusuario.listaUsuariosRuc(usuario.getRuc().trim());
						 
						 if(usuarioAdminAgencia!=null){
							 
							 double Total = 0.0;
							 
							 for (SpringSecurityUser listaUsuarios : usuarioAdminAgencia) {
								 
								 
								 ListaCuenta = servicecuentacorriente.EstadoCuentaCorriente((usuario.getRuc() == null)? "" :usuario.getRuc().trim(),Tipo,FechaInicial,FechaFinal,0,0,listaUsuarios.getUsername());
								 
								 double venta = 0.0;
								 if(ListaCuenta.size()>0){
									 
									for (B_CuentaCorrienteBean bean : ListaCuenta) {
										
										venta = venta + bean.getSaldo();
										
									}
									 
									 	//monto = servicecuentacorriente.VentasRealizadasCuentaCorriente(listaUsuarios.getRuc().trim(),listaUsuarios.getUsername());
									 	
									 	Total = Total += venta; //ListaCuenta.get(0).getSaldo();
									 
									 	Paragraph User = new Paragraph("Usuario: " + (!listaUsuarios.getCentroCosto().trim().equals("")?listaUsuarios.getCentroCosto().trim()+ " - ":"") +listaUsuarios.getUsername() ,new Font(FuenteLabel));
									 	User.setAlignment(Element.ALIGN_LEFT); 
										documento.add(User);
										Paragraph Credito = new Paragraph("Limite Crédito: " +listaUsuarios.getLimiteCredito() ,new Font(FuenteLabel));
										Credito.setAlignment(Element.ALIGN_LEFT); 
										documento.add(Credito);
										Paragraph Disponible = new Paragraph("Disponible: " +formateador.format(listaUsuarios.getLimiteCredito()- venta) ,new Font(FuenteLabel));
										Disponible.setAlignment(Element.ALIGN_LEFT); 
										documento.add(Disponible);
										Paragraph Vacio = new Paragraph("         ",new Font(FuenteLabel));
										Vacio.setAlignment(Element.ALIGN_LEFT); 
										documento.add(Vacio);
										
									 
										PdfPTable table = new PdfPTable(8);
										float[] medidaCeldas = {0.75f,0.75f,0.55f,1.8f,0.70f,0.70f,0.90f,0.55f};
										table.setWidths(medidaCeldas);
										table=FuncionesPDF.F_Reporte_EstadoCuenta(table,ListaCuenta,FuenteCabecera,FuenteDetalle,usuario.getOcultarDetalleEstadoCuenta().trim(),"Y",usuario.getPorcentajeComision());
									    documento.add(table);
									    
									    Paragraph Linea2 = new Paragraph("_____________________________________________________________________________________________" ,new Font(FuenteLabel));
									    Linea2.setAlignment(Element.ALIGN_LEFT); 
										documento.add(Linea2);
										
										
									    documento.add(Chunk.NEWLINE);
									 
									 
								 }
							}
							 
							 PdfPTable tableTotales = new PdfPTable(8);
							 float[] medidaCeldas = {0.75f,0.75f,0.55f,1.8f,0.70f,0.70f,0.90f,0.55f};
							 tableTotales.setWidths(medidaCeldas);
							 tableTotales = FuncionesPDF.TotalesAdminAgente(tableTotales, Total, FuenteCabecera, FuenteDetalle,usuario.getPorcentajeComision());
							 documento.add(tableTotales);
							 
						 	}
							
						}else{
							
							ListaCuenta = servicecuentacorriente.EstadoCuentaCorriente((usuario.getRuc() == null)? "" :usuario.getRuc().trim(),Tipo,FechaInicial,FechaFinal,0,0,usuario.getUsername());	
							
							//monto = servicecuentacorriente.VentasRealizadasCuentaCorriente(usuario.getRuc().trim(),usuario.getUsername());
							
							for (B_CuentaCorrienteBean bean : ListaCuenta) {
								monto = monto + bean.getSaldo();
							}
							
							Paragraph Ruc = new Paragraph("N° RUC : " +usuario.getRuc() + "                                                "
									+"                                                                                                                      "
									+ "                               Linea de Credito: S/. "+formateador.format(usuario.getLimiteCredito()) ,new Font(FuenteLabel));
							Ruc.setAlignment(Element.ALIGN_LEFT); 
							documento.add(Ruc);
							
							if(FechaInicial==null && FechaFinal==null){
								
								FechaInicial = ListaCuenta.get(ListaCuenta.size()-1).getFechaEmision();
								FechaFinal = (ListaCuenta.get(0).getFechaEmision());
								
							}else{
								
								FechaInicial = Utils.FormatoFechaReporteISO(FechaInicial);
								FechaFinal = Utils.FormatoFechaReporteISO(FechaFinal);
							}
							
							
							Paragraph Estado = new Paragraph("Desde :  " +FechaInicial+"   Hasta :  "+FechaFinal+"                                                                             "
									+ "                                               "
									+ "                                               "
									+ "Disponible : S/."+formateador.format(usuario.getLimiteCredito()- monto) ,new Font(FuenteLabel));
							Estado.setAlignment(Element.ALIGN_LEFT); 
							documento.add(Estado);
							
							Paragraph User = new Paragraph("Usuario: "+(!usuario.getCentroCosto().trim().equals("")?usuario.getCentroCosto().trim()+  " - ":"") +usuario.getUsername() ,new Font(FuenteLabel));
						 	User.setAlignment(Element.ALIGN_LEFT); 
							documento.add(User);
							
							Paragraph Linea = new Paragraph("        " ,new Font(FuenteLabel));
							Linea.setAlignment(Element.ALIGN_LEFT); 
							documento.add(Linea);
							
							//documento.add(Chunk.NEWLINE);
							
							PdfPTable table = new PdfPTable(8);
							float[] medidaCeldas = {0.75f,0.75f,0.55f,1.8f,0.70f,0.70f,0.90f,0.55f};
							table.setWidths(medidaCeldas);
							table=FuncionesPDF.F_Reporte_EstadoCuenta(table,ListaCuenta,FuenteCabecera,FuenteDetalle,usuario.getOcultarDetalleEstadoCuenta().trim(),"N",usuario.getPorcentajeComision());
						    documento.add(table);
							
						}
						
					    documento.close();
						ServletOutputStream outputStream = response.getOutputStream() ; 
						baos.writeTo(outputStream);
							
						response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\""); 
				        response.setContentType("application/pdf"); 
				        outputStream.flush(); 
				        outputStream.close(); 
				        baos.flush();
				        baos.close();
						
						
					}
					
				} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
				}
			}
	
	@Action(value = "imprimiratencionreclamosreporte", results = {
			@Result(name = SUCCESS, type="stream" ,params = 
		    {"contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" })})
			public void AtencionReclamosPDF() throws ParseException{
						
						try {
							
							BaseFont BebasNeueRegular = BaseFont.createFont(FuncionesPDF.class.getResource("BebasNeueRegular.ttf").toString(), BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
							
							
							Font Fuente = new Font(BebasNeueRegular, 17f, Font.NORMAL,BaseColor.BLACK);
							//Font FuenteLabel = new Font(BebasNeueRegular, 11f, Font.NORMAL);
							Font FuenteCabecera = new Font(BebasNeueRegular, 9f, Font.NORMAL);
							FuenteCabecera.setSize((float) 7.5);
							Font FuenteDetalle = new Font(BebasNeueRegular, 7f, Font.NORMAL);
							FuenteDetalle.setSize((float) 6.5);
							
							System.out.println(tiporeclamo);
							
						    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
							response.setContentType("application/pdf");
							response.setHeader("Content-Disposition","attachment;filename=Atencion Reclamos'"+sdf1.format(new Date())+"'.pdf");
							Document documento = new Document(PageSize.A4.rotate(),(float) 4,4,0,0);
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							
							PdfWriter.getInstance(documento, baos);
							documento.open();
							
							Paragraph titulo = new Paragraph("REPORTE DE ATENCION RECLAMOS",new Font(Fuente));
							titulo.setAlignment(Element.ALIGN_CENTER);
							
							documento.add(titulo);
							documento.add(Chunk.NEWLINE);
							
							
							Paragraph tipo = new Paragraph("TIPO : "+ tiporeclamoTexto,new Font(FuenteCabecera));
							tipo.setAlignment(Element.ALIGN_LEFT);
							
							documento.add(tipo);
							
							documento.add(Chunk.NEWLINE);
							
							List<B_AtencionReclamosBean> reclamos = new ArrayList<>();
							
							//System.out.println("TIPO DE RECLAMO  ---->  "+tiporeclamo);
							
							
							reclamos =  service.selecReclamosReporte(tiporeclamo);
							
							PdfPTable table = new PdfPTable(14);
							float[] medidaCeldas = {0.22f,1f,0.50f,0.75f,0.50f,0.40f,0.90f,0.35f,0.50f,0.50f,0.50f,0.70f,0.50f,0.50f};
							table.setWidths(medidaCeldas);
							table=FuncionesPDF.F_Reporte_AtencionReclamos(table, reclamos, FuenteCabecera, FuenteDetalle);
							
							documento.add(table);
						
					  	
						    documento.close();
							ServletOutputStream outputStream = response.getOutputStream() ; 
							baos.writeTo(outputStream);
								
							response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\""); 
					        response.setContentType("application/pdf"); 
					        outputStream.flush(); 
					        outputStream.close(); 
					        baos.flush();
					        baos.close();
							
						} catch (Exception e) {
							log.info(Utils.printStackTraceToString(e));
						}
			}
	
	public void Limipiar_SessionIda(){
		
		session.remove("ListaPreventaIda");
    	session.remove("listaPasajeros");
		
	}
	public void Limipiar_SessionVuelta(){
		session.remove("listaPasajeros");
		session.remove("ListaPreventaIdaVuelta");
		session.remove("listaPasajerosTabla");
		session.remove("listaPasajerosIdaVuelta");
		session.remove("ListaAuxiliar");
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setVenta(B_VentaBean venta) {
		this.venta = venta;
	}
	
	public B_VentaBean getVenta() {
		return venta;
	}
	
	public void setNrovoucher(Integer nrovoucher) {
		Nrovoucher = nrovoucher;
	}
	public Integer getNrovoucher() {
		return Nrovoucher;
	}
	
	public void setSalida(Integer salida) {
		Salida = salida;
	}
	public Integer getSalida() {
		return Salida;
	}
	public String getFechaInicial() {
		return FechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		FechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return FechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		FechaFinal = fechaFinal;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public void setEnvioCorreo(String envioCorreo) {
		EnvioCorreo = envioCorreo;
	}
	public String getEnvioCorreo() {
		return EnvioCorreo;
	}
	public void setRespuestaserver(boolean respuestaserver) {
		this.respuestaserver = respuestaserver;
	}
	
	public void setMensajeServer(String mensajeServer) {
		this.mensajeServer = mensajeServer;
	}
	public String getMensajeServer() {
		return mensajeServer;
	}
	
	public boolean isRespuestaserver() {
		return respuestaserver;
	}
	public void setListacantidadPasajeros(List<V_Varios> listacantidadPasajeros) {
		this.listacantidadPasajeros = listacantidadPasajeros;
	}
	public List<V_Varios> getListacantidadPasajeros() {
		return listacantidadPasajeros;
	}
	
	public void setUsuario(String usuario) {
		Usuario = usuario;
	} 
	public String getUsuario() {
		return Usuario;
	}
	public void setTiporeclamo(String tiporeclamo) {
		this.tiporeclamo = tiporeclamo;
	}
	public String getTiporeclamo() {
		return tiporeclamo;
	}
	
	public void setTiporeclamoTexto(String tiporeclamoTexto) {
		this.tiporeclamoTexto = tiporeclamoTexto;
	}
	public String getTiporeclamoTexto() {
		return tiporeclamoTexto;
	}
	
	public static void main(String[] args) {
		
		try {
			
			DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
		    simbolo.setDecimalSeparator('.');
		    simbolo.setGroupingSeparator(',');
		    DecimalFormat formateador = new DecimalFormat("###,###.##",simbolo);
			// Imprime esto con cuatro decimales, es decir: 7,1234
			System.out.println (formateador.format (7945.12));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
