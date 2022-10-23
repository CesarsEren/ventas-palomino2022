package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.PagoEfectivo;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSConsultarCIPRequestMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSConsultarCIPResponseMod1;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.visarespuesta.ConsultaEticketVisa;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1')")
public class ConsultasAction extends ActionSupport {
	
	private String eticket;
	private Map<String, String> rows;
	private String eticketPagoefectivo;
	private Map<String, String> rowsPagoefectivo;
	//private HttpServletRequest request;
	private static final Logger log = Logger.getLogger(ConsultasAction.class);

	@Action(value = "accedeconsultavisa", results = {@Result(name = SUCCESS, location = "sistema/consultas/consultavisa.jsp")})
	public String accedeConsultaVisa(){
		
		return SUCCESS;
	}
	
	@Action(value = "accedeconsultapagoefectivo", results = {@Result(name = SUCCESS, location = "sistema/consultas/consultapagoefectivo.jsp")})
	public String accedeConsultaPagoEfectivo(){
		
		return SUCCESS;
	}
	
	@Action(value = "consultavisa", results = {@Result(name = SUCCESS, type = "json")})
	public String consultaVisa(){
		
		rows = ConsultaEticketVisa.obtieneRespuestaVisa(eticket);
		
		System.out.println(rows);
		log.info(rows); 
		return SUCCESS;
	}
	
	@Action(value = "consultapagoefectivo", results = {@Result(name = SUCCESS, type = "json")})
	public String consultaPagoEfectivo(){
		
		try {
			
			rowsPagoefectivo = ConsultaPagoEfectivo(eticketPagoefectivo);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} 
		return SUCCESS;
	}
	
	@Action(value = "consultapagoefectivoVentaTelefonica", results = {@Result(name = SUCCESS, type = "json")})
	public String consultapagoefectivoVentaTelefonica(){
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String,Object>rsp= new HashMap<>();
		
		try { 
			rowsPagoefectivo = ConsultaPagoEfectivo(eticketPagoefectivo);  
			if(rowsPagoefectivo.isEmpty()){
				rsp.put("estado","000");
			rsp.put("msgserver","NO ENCONTRADO");
			rsp.put("mensaje","Nro de CIP no Encontrado");
			}else
			{
				String estadoCIP = rowsPagoefectivo.get("estado");
				if(estadoCIP.equals("593"))
				{
					rsp.put("cip",rowsPagoefectivo.get("nroCIP"));
					rsp.put("estado",estadoCIP);
					rsp.put("msgserver","PAGADO");
					rsp.put("mensaje","Presione OK para ver los boletos relacionados a"+rowsPagoefectivo.get("nroCIP")); 
				}else
				{
					rsp.put("estado",estadoCIP);
					rsp.put("msgserver","SIN REGISTRO DE PAGO");
					rsp.put("mensaje","no podemos mostrarle los boletos relacionados");
				}
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		stack.push(rsp);
		return SUCCESS;
	}
	
	public static Map<String, String> ConsultaPagoEfectivo(NodeList nlSolpago){
		
		Map<String, String> resultados = new HashMap<String, String>();
		
		try {
			
			NodeList nliDResSolPago = ((Element)nlSolpago.item(0)).getElementsByTagName("NumeroOrdenPago");
			NodeList nliDResEstado = ((Element)nlSolpago.item(0)).getElementsByTagName("Estado");
			NodeList nliDResTotal = ((Element)nlSolpago.item(0)).getElementsByTagName("Total");
			NodeList nliDResEmitido = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaEmision");
			NodeList nliDResExpirado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaExpirado");
			NodeList nliDResExtornado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaAnulado");
			NodeList nliDResCancelado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaCancelado");
			NodeList nliDResNombreUsuario = ((Element)nlSolpago.item(0)).getElementsByTagName("UsuarioNombre");
			NodeList nliDResApellidoUsuario = ((Element)nlSolpago.item(0)).getElementsByTagName("UsuarioApellidos");
			NodeList nliDResConceptoPago = ((Element)nlSolpago.item(0)).getElementsByTagName("ConceptoPago");
			
			resultados.put("nroCIP", nliDResSolPago.item(0).getTextContent().trim());
			resultados.put("estado", nliDResEstado.item(0).getTextContent().trim());
			resultados.put("monto", nliDResTotal.item(0).getTextContent().trim());
			resultados.put("fechaEmision",(nliDResEmitido.item(0).getTextContent().trim().equals("")? "" :Utils.FormatoFechaReporte(nliDResEmitido.item(0).getTextContent().trim().substring(0, 10))+" "+nliDResEmitido.item(0).getTextContent().trim().substring(10, 19)));
			resultados.put("fechaExpirado",(nliDResExpirado.item(0).getTextContent().trim().equals("")? "" :Utils.FormatoFechaReporte(nliDResExpirado.item(0).getTextContent().trim().substring(0, 10))+" "+nliDResExpirado.item(0).getTextContent().trim().substring(10, 19)));
			resultados.put("fechaExtornado",(nliDResExtornado.item(0).getTextContent().trim().equals("")? "" : Utils.FormatoFechaReporte(nliDResExtornado.item(0).getTextContent().trim().substring(0, 10))+" "+nliDResExtornado.item(0).getTextContent().trim().substring(10, 19)));
			resultados.put("fechaCancelado",(nliDResCancelado.item(0).getTextContent().trim().equals("")? "" : Utils.FormatoFechaReporte(nliDResCancelado.item(0).getTextContent().trim().substring(0, 10))+" "+nliDResCancelado.item(0).getTextContent().trim().substring(10, 19)));
			resultados.put("usuario", nliDResNombreUsuario.item(0).getTextContent().trim()+" "+nliDResApellidoUsuario.item(0).getTextContent().trim());
			resultados.put("conceptoPago", nliDResConceptoPago.item(0).getTextContent().trim());
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return resultados;
		
		
	}
	
	public static Map<String, String> ConsultaPagoEfectivo(String eticketPagoefectivo){
		
		NodeList nlSolpago = null;
		
		Map<String, String> resultados = new HashMap<String, String>();
		
		try {
			
			PagoEfectivo.CodServ =  "PAL";
			
			//PagoEfectivo.PublicPath = "C://LLAVES//PAGOEFECTIVO//SPE_PublicKey.1pz";
			//PagoEfectivo.PrivatePath = "C://LLAVES//PAGOEFECTIVO//PAL_PrivateKey.1pz";
			PagoEfectivo.PublicPath = "/var/www/ventas/pagoefectivo/keys/produccion/SPE_PublicKey.1pz";//request.getServletContext().getInitParameter("PublicPath"); 
			PagoEfectivo.PrivatePath = "/var/www/ventas/pagoefectivo/keys/produccion/PAL_PrivateKey.1pz";//request.getServletContext().getInitParameter("PrivatePath"); 
			PagoEfectivo.UrlPECriptography = "https://cip.pagoefectivo.pe/PagoEfectivoWSCrypto/WSCrypto.asmx";//request.getServletContext().getInitParameter("UrlPECriptography");
			PagoEfectivo.UrlPEService = "http://cip.pagoefectivo.pe/PagoEfectivoWSGeneralv2/Service.asmx";//request.getServletContext().getInitParameter("UrlPEService"); 
			
			log.info("********************************************************************************************");
			log.info("INGRESANDO AL PROCESO DE CONSULTA DE PAGOEFECTIVO -->" +PagoEfectivo.PrivatePath);
			
			BEWSConsultarCIPRequestMod1 requestPagoEfectivo = new BEWSConsultarCIPRequestMod1(); 
			//El capi puede ser asignado directamente del web.xml 
			requestPagoEfectivo.setCodServ(PagoEfectivo.CodServ);
			 
			
			log.info("TICKET" + eticketPagoefectivo);
			//Los cips asignados manualmente (más de 1 enviar separados por coma)
			requestPagoEfectivo.setCIPS(eticketPagoefectivo); 

			//Ejecutamos la Consulta de la clase PagoEfectivo(API) 
			BEWSConsultarCIPResponseMod1 responsePagoEfectivo = PagoEfectivo.ConsultarCIP(requestPagoEfectivo);
			String XmlResponse = responsePagoEfectivo.getXML(); 
			
			log.info("RESPUESTA XML" + XmlResponse); 
			
			// LE DAMOS FORMA DE UN MAPA AL XML
			
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder(); 
			Document doc = db.parse(new ByteArrayInputStream(XmlResponse.getBytes("UTF-8")));
			
			nlSolpago = doc.getElementsByTagName("ConfirSolPago");
			
			
			if(nlSolpago.getLength()>0) { 
				//String fecha = "2016-10-06 19:41:09"; 
				//System.out.println(Utils.FormatoFechaReporte(fecha.substring(0,10))+" "+fecha.substring(10,19));
				
			    //rowsPagoefectivo = ConsultaPagoEfectivo(nlSolpago);
				NodeList nliDResSolPago = ((Element)nlSolpago.item(0)).getElementsByTagName("NumeroOrdenPago");
				NodeList nliDResEstado = ((Element)nlSolpago.item(0)).getElementsByTagName("Estado");
				NodeList nliDResTotal = ((Element)nlSolpago.item(0)).getElementsByTagName("Total");
				NodeList nliDResEmitido = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaEmision");
				NodeList nliDResExpirado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaExpirado");
				NodeList nliDResExtornado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaAnulado");
				NodeList nliDResCancelado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaCancelado");
				NodeList nliDResNombreUsuario = ((Element)nlSolpago.item(0)).getElementsByTagName("UsuarioNombre");
				NodeList nliDResApellidoUsuario = ((Element)nlSolpago.item(0)).getElementsByTagName("UsuarioApellidos");
				NodeList nliDResConceptoPago = ((Element)nlSolpago.item(0)).getElementsByTagName("ConceptoPago");
				NodeList nliDResUsuarioEmail = ((Element)nlSolpago.item(0)).getElementsByTagName("UsuarioEmail");
				
				resultados.put("nroCIP", nliDResSolPago.item(0).getTextContent().trim());
				resultados.put("estado", nliDResEstado.item(0).getTextContent().trim());
				resultados.put("monto", nliDResTotal.item(0).getTextContent().trim());
				resultados.put("fechaEmision",(nliDResEmitido.item(0).getTextContent().trim().equals("")? "" :Utils.FormatoFechaReporte(nliDResEmitido.item(0).getTextContent().trim().substring(0, 10))+" "+nliDResEmitido.item(0).getTextContent().trim().substring(10, 19)));
				resultados.put("fechaExpirado",(nliDResExpirado.item(0).getTextContent().trim().equals("")? "" :Utils.FormatoFechaReporte(nliDResExpirado.item(0).getTextContent().trim().substring(0, 10))+" "+nliDResExpirado.item(0).getTextContent().trim().substring(10, 19)));
				resultados.put("fechaExtornado",(nliDResExtornado.item(0).getTextContent().trim().equals("")? "" : Utils.FormatoFechaReporte(nliDResExtornado.item(0).getTextContent().trim().substring(0, 10))+" "+nliDResExtornado.item(0).getTextContent().trim().substring(10, 19)));
				resultados.put("fechaCancelado",(nliDResCancelado.item(0).getTextContent().trim().equals("")? "" : Utils.FormatoFechaReporte(nliDResCancelado.item(0).getTextContent().trim().substring(0, 10))+" "+nliDResCancelado.item(0).getTextContent().trim().substring(10, 19)));
				resultados.put("usuario", nliDResNombreUsuario.item(0).getTextContent().trim()+" "+nliDResApellidoUsuario.item(0).getTextContent().trim());
				resultados.put("conceptoPago", nliDResConceptoPago.item(0).getTextContent().trim());
				resultados.put("usuarioEmail", nliDResUsuarioEmail.item(0).getTextContent().trim());
			}
			
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return resultados;
		
	}
	
	public String getEticket() {
		return eticket;
	}
	public void setEticket(String eticket) {
		this.eticket = eticket;
	}
	public void setEticketPagoefectivo(String eticketPagoefectivo) {
		this.eticketPagoefectivo = eticketPagoefectivo;
	}
	public String getEticketPagoefectivo() {
		return eticketPagoefectivo;
	}
	public void setRowsPagoefectivo(Map<String, String> rowsPagoefectivo) {
		this.rowsPagoefectivo = rowsPagoefectivo;
	}
	public Map<String, String> getRowsPagoefectivo() {
		return rowsPagoefectivo;
	}
	public void setRows(Map<String, String> rows) {
		this.rows = rows;
	}
	public Map<String, String> getRows() {
		return rows;
	}
	/*
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		
	}*/

	
}
