package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBeanDetalle;
import pe.com.grupopalomino.sistema.boletaje.bean.B_EncomiendasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_MotivoBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosCorrelativoBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_EmpresasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_TipoDocumentosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.service.AtencionReclamosService;
import pe.com.grupopalomino.sistema.boletaje.service.AtencionReclamosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.EmpresaService;
import pe.com.grupopalomino.sistema.boletaje.service.EmpresaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.EncomiendasService;
import pe.com.grupopalomino.sistema.boletaje.service.EncomiendasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ReclamosCorrelativoService;
import pe.com.grupopalomino.sistema.boletaje.service.ReclamosCorrelativoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.util.ErrorValidacion;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesPDF;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmailReclamos;
import pe.com.grupopalomino.sistema.boletaje.util.Regex;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.util.ValidaAtencionDatosReclamos;
 


@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
public class ReclamosAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	B_AtencionReclamosBean reclamos = new B_AtencionReclamosBean();
	AtencionReclamosService service = new AtencionReclamosServiceI(); 
	ReclamosCorrelativoService servicecorrelativo = new ReclamosCorrelativoServiceI();  
	String mensajeServer = "";
	boolean respuestaServer = false;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private static final Logger log = Logger.getLogger(ReclamosAction.class);
	private EncomiendasService serviceencomiendas = new EncomiendasServiceI();
	private List<V_EmpresasBean> listaComboEmpresa = new ArrayList<V_EmpresasBean>();
	private String RUC;
	private String nro,periodo,correo,empresa;
	private List<B_MotivoBean> listaMotivos = new ArrayList<B_MotivoBean>();
	private List<V_TipoDocumentosBean> listaTipoDocumentos = new ArrayList<V_TipoDocumentosBean>();
	private EmpresaService serviceempresa = new EmpresaServiceI();
	
	
	@Action(value = "/reclamos",  results =
			{@Result(name = SUCCESS, location = "reclamos/pagereclamos.jsp")})
	public String reclamos()  {
		
		try {
			listaComboEmpresa = serviceempresa.listaEmpresas();
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/imprimirreclamos",  results =
			@Result(name = SUCCESS, type="stream" ,params = {"contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024"}))
	public String imprimirreclamos()  {
		
		try {
			
			reclamos = service.selecReclamos(reclamos.getNro(),reclamos.getPeriodo(),reclamos.getEmpresa(),"S");
			
			B_AtencionReclamosBeanDetalle detalle = null;
			detalle = service.selecReclamosDetalle(reclamos.getSerie(), reclamos.getNumero(), reclamos.getDocumento(),reclamos.getEmpresa());
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment;filename=Reclamo-N°-"+reclamos.getNro()+".pdf");
			
			ServletOutputStream outputStream = response.getOutputStream() ; 
			ByteArrayOutputStream baos = obtieneBAOS(reclamos,detalle);
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
		
		return SUCCESS;
	}
	
	@Action(value = "/reenviocorreo",  results =
	{@Result(name = SUCCESS, type = "json")})
	public String reclamoconfirmado()  {
		
				try {
					
					if(correo.trim().isEmpty()){
						respuestaServer = false;
						mensajeServer = "Por favor ingrese su correo.";
						return SUCCESS;
						
					}
					if(!correo.trim().matches(Regex.EMAIL)){
						respuestaServer = false;
						mensajeServer = "El email ingresado no tiene el formato correcto.";
						return SUCCESS;
					}
					
					reclamos = service.selecReclamos(Integer.parseInt(nro),Integer.parseInt(periodo),empresa,"S"); 
					//GenerarEmailReclamos.enviarCorreoAdjuntoReclamos(GenerarEmailReclamos.parametrosReclamoCLiente(reclamos,correo,null),null);
					//"jquintanilla@grupopalomino.com.pe" reclamos.getEmail()
					GenerarEmailReclamos.enviarCorreoAtencionReclamos(GenerarEmailReclamos.parametrosAtencionReclamos(reclamos,"jquintanilla@grupopalomino.com.pe",null),null);
					respuestaServer = true;
					mensajeServer = "se ha enviado la solicitud a su correo, por favor verificar.";
				} catch (Exception e) {
					respuestaServer = false;
					mensajeServer = "Ocurrio un problema al enviar la solicitud a su correo, por favor intente más tarde.";
					log.info(Utils.printStackTraceToString(e));
				}
		
		return SUCCESS;
	}
	
	@Action(value = "/enviarreclamo", results = {@Result( name = SUCCESS,  type="json")})
	public String enviarreclamo() throws Exception { 
		try {
			
			int operacion = -1 ;
			
			reclamos.setValuesReclamos(reclamos);
			ValidaAtencionDatosReclamos validador = new ValidaAtencionDatosReclamos(reclamos);
			ErrorValidacion error = validador.ValidaReclamos();
			
			if(error.getError()){
				for (String mensajeError : error.getMensajeError()) {
					mensajeServer = mensajeError;
				}
				respuestaServer = false;
				return SUCCESS;
			}
			
			B_EncomiendasBean encomiendas = new B_EncomiendasBean();
			
			encomiendas = serviceencomiendas.getEncomiendaSerieNumero(reclamos.getSerie(), reclamos.getNumero(),reclamos.getDocumento(),reclamos.getEmpresa());
			if(encomiendas == null){
				mensajeServer = "El documento ingresado, no existe";
				return SUCCESS;
			}
			
			
			List<V_EmpresasBean> ListaEmpresa = new ArrayList<V_EmpresasBean>();
			ListaEmpresa = serviceempresa.listaEmpresas();
			
			for (V_EmpresasBean bean : ListaEmpresa){
				if(bean.getCodigo().trim().equals(reclamos.getEmpresa())){
					reclamos.setEmpresaD(bean.getRazon());
				}
			}
			
			
			/* GENERAMOS EL CORRELATIVO DEL LIBRO DE RECLAMACIONES */
			
			B_ReclamosCorrelativoBean bean = new B_ReclamosCorrelativoBean();
			
			bean = servicecorrelativo.selecReclamosCorrelativo(reclamos.getEmpresa(),"S");
			
			if(bean == null){
				
				respuestaServer = false;
				mensajeServer = "Ocurrio un problema interno,su solicitud no ha sido procesada, por favor enviar un correo a ventatelefonica@grupopalomino.com.pe";
				return SUCCESS;
			}
						
			//reclamos.setNro(bean.getCorrelativoRec());
			//reclamos.setPeriodo(bean.getPeriodo());
			reclamos.setUsuario("LIBRO DE RECLAMACIONES WEB - CLIENTES");
			reclamos.setAgencia("372");
			reclamos.setAgenciaD("LIBRO DE RECLAMACIONES WEB - CLIENTES");
			reclamos.setNro(bean.getCorrelativoAteRec());
			reclamos.setPeriodo(bean.getPeriodo());
			reclamos.setAtencionReclamos("S");
			reclamos.setAtendido("N");
			reclamos.setDetalleAtencion("");
			operacion = service.registraReclamos(reclamos);
			
			if (operacion == -1){
				respuestaServer = false;
				mensajeServer = "Ocurrio un problema al enviar su reclamo, por favor intentelo más tarde.";
				return SUCCESS;
			}
			
			/*CONSULTAMOS LA TRANSACCION PARA ENVIARLA POR CORREO*/
			
			reclamos = service.selecReclamos(reclamos.getNro(),bean.getPeriodo(),reclamos.getEmpresa(),"S");
			
			B_AtencionReclamosBeanDetalle detalle = null;
			
			detalle = service.selecReclamosDetalle(reclamos.getSerie(), reclamos.getNumero(), reclamos.getDocumento(),reclamos.getEmpresa());
			
			reclamos.setFechaEmision(detalle.getFechaEmision());
			reclamos.setDocumentoD(detalle.getDocumentoD());
			reclamos.setDestinoD(detalle.getDestinoD());
			reclamos.setAgenciaD(detalle.getAgenciaD()); 
			reclamos.setTipoReclamoD((reclamos.getTipoReclamo().trim().equals("R")?"RECLAMO":"QUEJA"));
			
	
			/*VariosService servicevarios = new VariosServiceI(); 
			V_Varios beanvarios = new  V_Varios();
			
			beanvarios = servicevarios.Select_Varios();
			
			GenerarEmailReclamos.enviarCorreoAdjuntoReclamos(GenerarEmailReclamos.parametrosReclamoCLiente(reclamos,reclamos.getEmail(),null),null);
			GenerarEmailReclamos.enviarCorreoAdjuntoReclamos(GenerarEmailReclamos.parametrosReclamoCLiente(reclamos,beanvarios.getCorreoReclamos(),beanvarios.getCorreoReclamosCC()),obtieneBAOS(reclamos,detalle));*/
			
			VariosService servicevarios = new VariosServiceI(); 
			V_Varios beanvarios = new  V_Varios();
			
			beanvarios = servicevarios.Select_Varios();
			
			String  CorreoReclamoPasajesCC = null;
			String  CorreoReclamoEncomiendasCC = null;
			
			if(beanvarios.getCorreoReclamoPasajesCC()!= null){
				CorreoReclamoPasajesCC = beanvarios.getCorreoReclamoPasajesCC();
			}
			if(beanvarios.getCorreoReclamoEncomiendasCC()!= null){
				CorreoReclamoEncomiendasCC = beanvarios.getCorreoReclamoEncomiendasCC();
			}
			
			reclamos.setResponsable(beanvarios.getResponsableAtencionReclamos());
			//GenerarEmail.enviarCorreoAdjuntoReclamos(GenerarEmail.parametrosReclamoCLiente(reclamos,reclamos.getEmail(),null),null);
			//GenerarEmail.enviarCorreoAdjuntoReclamos(GenerarEmail.parametrosReclamoCLiente(reclamos,beanvarios.getCorreoReclamos(),beanvarios.getCorreoReclamosCC()),obtieneBAOS(reclamos,detalle));
			//"jquintanilla@grupopalomino.com.pe" reclamos.getEmail()
			GenerarEmailReclamos.enviarCorreoAtencionReclamos(GenerarEmailReclamos.parametrosAtencionReclamos(reclamos,"jquintanilla@grupopalomino.com.pe",null),null);
			GenerarEmailReclamos.enviarCorreoAtencionReclamos(GenerarEmailReclamos.parametrosAtencionReclamos(reclamos,(reclamos.getServicio().trim().equals("B")?beanvarios.getCorreoReclamoPasajes():beanvarios.getCorreoReclamoEncomiendas()),
													 (reclamos.getServicio().trim().equals("B")?CorreoReclamoPasajesCC:CorreoReclamoEncomiendasCC)),obtieneBAOS(reclamos,detalle));
			
			respuestaServer = true;
			mensajeServer = "Su reclamo ha sido enviado satisfactoriamente";
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	
	private ByteArrayOutputStream obtieneBAOS(B_AtencionReclamosBean reclamos,B_AtencionReclamosBeanDetalle detalle){
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			
			BaseFont BebasNeueRegular = BaseFont.createFont(FuncionesPDF.class.getResource("BebasNeueRegular.ttf").toString(), BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			String rutaLogo = "";
			float absoluteY= 0 ,absoluteX=0,scalePercent=0;
			Font Fuente = new Font(BebasNeueRegular, 19f, Font.NORMAL,BaseColor.BLACK);
			Font FuenteCabecera = new Font(FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
			Font FuenteCabeceraEmpresa = new Font(FontFamily.HELVETICA,14,Font.BOLD,BaseColor.WHITE);
			Font FuenteDetalle = new Font(FontFamily.HELVETICA,10,Font.NORMAL,BaseColor.BLACK);
			
			Document documento = new Document();
			PdfWriter.getInstance(documento, baos);
			documento.open();
			
			if (reclamos.getEmpresa().trim().equals("002")) {
				
				rutaLogo = "Expreso_Internacional_Palomino_2.png";
				scalePercent = 18f;
				absoluteX = 415f;
				absoluteY = 760f;
				
			}else if(reclamos.getEmpresa().trim().equals("004")){
				
				rutaLogo = "Transporte_Wari.png";
				scalePercent = 18f;
				absoluteX = 427f;
				absoluteY = 760f;
				
			}else if (reclamos.getEmpresa().trim().equals("003")){
				
				rutaLogo = "Wari-Cargo-logo.png";
				scalePercent = 13f;
				absoluteX = 445f;
				absoluteY = 745f;
			}
			
			InputStream rutapalomino = request.getServletContext().getResourceAsStream("_lib/img"+File.separator+rutaLogo);
			
			Image logopalomino = Image.getInstance(IOUtils.toByteArray(rutapalomino));
			logopalomino.scalePercent(scalePercent);
			logopalomino.setAbsolutePosition(absoluteX, absoluteY);    //770
			logopalomino.setAlignment(Element.ALIGN_LEFT);
			
			documento.add(logopalomino);
			documento.add(Chunk.NEWLINE);
			
			
			Paragraph titulo = new Paragraph("ATENCIÓN DE RECLAMOS N° "+reclamos.getNro(),new Font(Fuente));
			titulo.setAlignment(Element.ALIGN_CENTER);
			
			documento.add(titulo);
			documento.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(12);
			float[] medidaCeldas = {0.75f,0.75f,0.55f,1.8f,0.70f,0.70f,0.90f,0.55f,0.70f,0.70f,0.90f,0.55f};
			table.setWidths(medidaCeldas);
			
			table = FuncionesPDF.ReporteAtencionReclamos(table, reclamos,FuenteCabeceraEmpresa,FuenteCabecera,FuenteDetalle,detalle);
					
			documento.add(table);
		    
			documento.close();
		} 
		catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			
		}
		
		return baos;
	}
	
	public void setReclamos(B_AtencionReclamosBean reclamos) {
		this.reclamos = reclamos;
	}
	public B_AtencionReclamosBean getReclamos() {
		return reclamos;
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
	
	public void setListaComboEmpresa(List<V_EmpresasBean> listaComboEmpresa) {
		this.listaComboEmpresa = listaComboEmpresa;
	}
	public List<V_EmpresasBean> getListaComboEmpresa() {
		return listaComboEmpresa;
	}
	public void setListaMotivos(List<B_MotivoBean> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}
	public List<B_MotivoBean> getListaMotivos() {
		return listaMotivos;
	}
	
	public void setRUC(String rUC) {
		RUC = rUC;
	}
	public String getRUC() {
		return RUC;
	}
	
	public void setNro(String nro) {
		this.nro = nro;
	}
	public String getNro() {
		return nro;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getPeriodo() {
		return periodo;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getEmpresa() {
		return empresa;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	public List<V_TipoDocumentosBean> getListaTipoDocumentos() {
		return listaTipoDocumentos;
	}

	public void setListaTipoDocumentos(List<V_TipoDocumentosBean> listaTipoDocumentos) {
		this.listaTipoDocumentos = listaTipoDocumentos;
	}
	

}
