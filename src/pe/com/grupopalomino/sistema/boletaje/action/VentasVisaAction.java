package pe.com.grupopalomino.sistema.boletaje.action;

import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.Reader;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.xml.internal.fastinfoset.sax.Properties;
import com.visural.common.web.client.WebClient;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.formviews.TarjetaCredito;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaOpenpay;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativosService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterface;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterfaceI;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesFacturacionPDF;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesPDF;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmail;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.visa.GeneraTicketVisa;
import pe.com.grupopalomino.sistema.boletaje.visa.rest.config.Configuration;
import pe.com.grupopalomino.sistema.boletaje.visa.rest.config.IndexService;
import pe.com.grupopalomino.sistema.boletaje.visa.rest.config.SessionClass;
import pe.com.grupopalomino.sistema.boletaje.visa.rest.config.TransactionClass.TransaccionAprobada;
import pe.com.grupopalomino.sistema.boletaje.visa.rest.config.TransactionClass.TransaccionDenegada;
import pe.com.grupopalomino.sistema.boletaje.visarespuesta.ConsultaEticketVisa;

@SuppressWarnings("serial")
@ParentPackage("BoletajePalomino03")
public class VentasVisaAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware {

	private static final Logger log = Logger.getLogger(VentasVisaAction.class);
	private String ETICKET = "", pedidoID = "";
	private TarjetaCredito tarjetaCredito;
	private Map<String, String> respuestaVisa;
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private CuentaCorrienteService servicecuentacorriente = new CuentaCorrienteServiceI();
	private String EnvioCorreo;
	private boolean respuestaServer = true;
	private String mensajeServer;
	private String NroVoucher;
	private VentaOpenpay openpayForm;	
	private Map<String, Object> session;	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String medioPago;
	private String ESTADO = "";
	private CorrelativosService servicecorrelativo = new CorrelativoServiceI();
	IndexService indexService = new IndexService();

	// @SuppressWarnings("unchecked")

	String transactionToken;

	public void setTransactionToken(String transactionToken) {
		this.transactionToken = transactionToken;
	}

	public String getTransactionToken() {
		return transactionToken;
	}

	@Action(value = "/PagarVisa", results = { @Result(name = { "success" }, location = "ventavisa/resultadovisa.jsp") })
	public String PagarVisa() throws Exception {
		this.session.remove("ListaPreventaLast");
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> rptaVisa = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		log.info(
				"****************************************** INGRESANDO LA RESPUESTA DE PAGAR VISA ******************************************");
		IndexService indexService = new IndexService();
		String token = null;
		String urlSession = String.valueOf(Configuration.URL_API_AUTORIZACION) + this.merchantId;
		byte b;
		int i;
		Cookie[] arrayOfCookie;
		for (i = (arrayOfCookie = this.request.getCookies()).length, b = 0; b < i;) {
			Cookie c = arrayOfCookie[b];
			if (c.getName().equals("token"))
				token = c.getValue();
			b++;
		}
		String requestBody = "{\"channel\": \"web\",\"captureType\": \"manual\",\"countable\": \"true\",\"order\": {\"amount\": \""
				+ this.importe + "\"," + "\"tokenId\": \"" + this.transactionToken + "\"," + "\"currency\": \""
				+ this.currency + "\"," + "\"purchaseNumber\": " + this.purchaseNumber + "}" + "}";
		log.info("Respuesta JSON : " + requestBody);
		Gson gson = new Gson();
		try {
			Map<String, Object> mapRpta = indexService.postRequest(urlSession, requestBody, token);
			token = this.sessionvisa;
			if (((Boolean) mapRpta.get("error")).booleanValue()) {
				rptaVisa.put("respuestaServer", Boolean.valueOf(false));
				TransaccionDenegada denegada = (TransaccionDenegada) gson.fromJson((String) mapRpta.get("data"),
						TransaccionDenegada.class);
				System.out.println("<br> TransacciDenegada <hr>");
				System.out.println("<br> Nde pedido: " + this.purchaseNumber);
				System.out.println("<br> Importe: " + this.importe);
				System.out.println("<br> Moneda: " + this.currency);
				System.out.println("<br> Brand: " + denegada.getData().getBRAND());
				System.out.println("<br> Descripci" + denegada.getData().getACTION_DESCRIPTION());
				rptaVisa.put("pedidoID", this.purchaseNumber);
				rptaVisa.put("Importe", this.importe);
				rptaVisa.put("Moneda", this.currency);
				
				//codigo JCHC
				
				
				Long long_ = this.purchaseNumber;
				//List<B_VentaBean> listaVenta = this.serviceventa.getVerificaVentaNoConfirmadaVisa(token);
				
				String descripcion = denegada.getData().getACTION_DESCRIPTION();					
				GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros("desarrolladorweb@grupopalomino.com.pe", descripcion + long_),null);
				
				//
				
				boolean repetido = descripcion.contains("duplicado");
				if (!repetido) {
					String dia = denegada.getData().getTRANSACTION_DATE();
					String[] formato = new String[6];
					formato[0] = dia.substring(0, 2);
					formato[1] = dia.substring(2, 4);
					formato[2] = dia.substring(4, 6);
					formato[3] = dia.substring(6, 8);
					formato[4] = dia.substring(8, 10);
					formato[5] = dia.substring(10, 12);
					rptaVisa.put("Fecha", String.valueOf(formato[2]) + "-" + formato[1] + "-" + formato[0]);
					rptaVisa.put("Hora", String.valueOf(formato[3]) + ":" + formato[4] + ":" + formato[5]);
				} else {
					rptaVisa.put("Fecha", "00/00/00");
					rptaVisa.put("Hora", "00:00:00");
				}
				rptaVisa.put("mensajeServer", denegada.getData().getACTION_DESCRIPTION());
			} else {
				rptaVisa.put("respuestaServer", Boolean.valueOf(true));
				TransaccionAprobada aprobada = (TransaccionAprobada) gson.fromJson((String) mapRpta.get("data"),
						TransaccionAprobada.class);
				System.out.println("<br> TransacciAprobada <hr>");
				System.out.println("<br> Nde pedido: " + this.purchaseNumber);
				System.out.println("<br> Brand: " + aprobada.getDataMap().getBRAND());
				System.out.println("<br> Card: " + aprobada.getDataMap().getCARD());
				System.out.println("<br> mensajeServer | Descripci" + aprobada.getDataMap().getACTION_DESCRIPTION());
				System.out.println("<br> Importe: " + this.importe);
				System.out.println("<br> Moneda: " + this.currency);
				System.out.println("<br> Transaction id: " + aprobada.getOrder().getTransactionId());
				this.ETICKET = aprobada.getOrder().getTokenId();
				Long long_ = this.purchaseNumber;
				String NumeroTarjeta = aprobada.getDataMap().getCARD(); 
				String TarjetaHabiente = "";
				VentaInterfaceI ventaInterfaceI = new VentaInterfaceI();
				String userCliente = "";
				B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
				B_CuentaCorrienteBean cuentacorrienteVerifica = new B_CuentaCorrienteBean();
				int operacion = -1;
				List<B_VentaBean> listaVenta = this.serviceventa.getVerificaVentaNoConfirmadaVisa(token);
				if (listaVenta.size() == 0) {
					log.info(
							"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");
					operacion = this.serviceventa.UpdateVentasVisaAuditoria(token);
					log.info("VALOR DE OPERACION DE LAS VENTAS DE AUDITORIA -->" + operacion);
					log.info(
							"************************ VOLVIENDO LAS VENTAS DE LA AUDITORIA A PENDIENTES ************************");
					log.info("token --> :" + token);
					this.mensajeServer = "Ocurrio un problema Interno, se le enviara un correo dentro de 15 minutos, indicando el estado de su compra, de no presenciar ninguna respuesta en su cuenta, por favor enviar un mensaje a ventatelefonica@grupopalomino.com.pe indicando la incidencia. Gracias.";
					this.ESTADO = "DENEGADO";
					return "ERRORINTERNO";
				}
				for (B_VentaBean venta : listaVenta) {
					userCliente = venta.getUsuarioVisa();
					operacion = this.serviceventa.updateVentaConfirmada(venta.getNro(), venta.getSalida(), "C",
							"" + long_, venta.getUsuarioVisa(), NumeroTarjeta, TarjetaHabiente.toUpperCase(),
							this.ETICKET, 1);
					if (operacion != -1) {
						cuentacorrienteVerifica = this.servicecuentacorriente
								.ListaVentaPagoEfectivoCuentaCorriente(venta.getNro());
						if (cuentacorrienteVerifica == null) {

							log.info("PREPARANDO EL REGISTRO EN LA CUENTA CORRIENTE VISA  -- NVENTA --> "
									+ venta.getNro());
							B_Correlativos correlativos = new B_Correlativos();
							correlativos = this.servicecorrelativo.generaCorrelativo();
							cuentacorriente = ventaInterfaceI.DatosEstaticosCuentaCorriente(cuentacorriente);
							cuentacorriente = ventaInterfaceI.DatosDinamicosCuentaCorrienteVisa(venta, cuentacorriente,
									correlativos, "" + long_);
							operacion = this.servicecuentacorriente.insertCuentaCorriente(cuentacorriente);

							log.info("MENSAJE" + venta.toString());
						} else {
							log.info("LA VENTA YA EXISTE EN LA CUENTA CORRIENTE VISA -- NVENTA --> " + venta.getNro());
						}
						if (operacion == -1) {
							log.info(
									"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");
							log.info("TICKET --> :" + this.ETICKET);
							this.mensajeServer = "Ha ocurrido un error al intentar recuperar su ticket, para verificar el estado de su transaccion, por favor enviar un correo a ventatelefonica@grupopalomino.com.pe";
							this.ESTADO = "DENEGADO";
							return "ERRORINTERNO";
						}
						continue;
					}

					log.info(
							"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");
					log.info("TICKET --> :" + this.ETICKET);
					this.mensajeServer = "Ha ocurrido un error al intentar recuperar su ticket, para verificar el estado de su transaccion, por favor enviar un correo a ventatelefonica@grupopalomino.com.pe";
					this.ESTADO = "DENEGADO";
					return "ERRORINTERNO";
				}
				log.info("CORREO DEL CLIENTE --> " + userCliente.trim());
				log.info("VENTA REALIZADA EXITOSAMENTE");
				this.respuestaServer = true;
				this.mensajeServer = "La Operacion se realizo Exitosamente.";
				List<B_VentaBean> ventaVisa = new ArrayList<>();
				ventaVisa = this.serviceventa.getVentaImprimirVisa(("" + long_).trim().equals("") ? "" : "" + long_);
				for (B_VentaBean item : ventaVisa) {
					String empresaD = CodigoDeEmpresaCondicionado(item.getNroDestinocodigo(),
							"" + item.getNroServiciocodigo());
					item.setEmpresa(empresaD);
					// GENERAR BOLETO
					facturacionservice.USP_GENERARBOLETO(item.getNro() + "", "4127", item.getRuc() == null ? 16 : 17,
							empresaD);
					// GENERAR CODIGO QR
					consumirservicio("" + item.getNro(), "B");
				}
				GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(userCliente.trim(), "" + long_),
						DescargaPDFTicket(ventaVisa));
				log.info(
						"****************************************** OPERACION DE VISA GENERADO CORRECTAMENTE --> TICKET "
								+ this.ETICKET + " ******************************************");
				String dia = aprobada.getDataMap().getTRANSACTION_DATE();
				String[] formato = new String[6];
				formato[0] = dia.substring(0, 2);
				formato[1] = dia.substring(2, 4);
				formato[2] = dia.substring(4, 6);
				formato[3] = dia.substring(6, 8);
				formato[4] = dia.substring(8, 10);
				formato[5] = dia.substring(10, 12);
				rptaVisa.put("Fecha", String.valueOf(formato[2]) + "-" + formato[1] + "-" + formato[0]);
				rptaVisa.put("Hora", String.valueOf(formato[3]) + ":" + formato[4] + ":" + formato[5]);
				rptaVisa.put("pedidoID", this.purchaseNumber);
				rptaVisa.put("Card", aprobada.getDataMap().getCARD());
				rptaVisa.put("mensajeServer", aprobada.getDataMap().getACTION_DESCRIPTION());
				rptaVisa.put("Importe", this.importe);
				rptaVisa.put("Moneda", this.currency);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		data.put("rptaVisa", rptaVisa);
		stack.push(data);
		return "success";
	}

	/*
	 * @Action(value = "/PagarVisa", results = { @Result(name = SUCCESS,
	 * location = "ventavisa/resultadovisa.jsp") }) public String PagarVisa()
	 * throws Exception {
	 * 
	 * session.remove("ListaPreventaLast");
	 * 
	 * ValueStack stack = ActionContext.getContext().getValueStack();
	 * Map<String, Object> rptaVisa = new HashMap<>(); Map<String, Object> data
	 * = new HashMap<>();
	 * 
	 * log.info(
	 * "****************************************** INGRESANDO LA RESPUESTA DE PAGAR VISA ******************************************"
	 * );
	 * 
	 * IndexService indexService = new IndexService();
	 * 
	 * String token = null; // Generar Autorización String urlSession =
	 * Configuration.URL_API_AUTORIZACION + merchantId;
	 * 
	 * for (Cookie c : request.getCookies()) { if (c.getName().equals("token"))
	 * { token = c.getValue(); } }
	 * 
	 * String requestBody = "{" + "\"channel\": \"web\"" + "," +
	 * "\"captureType\": \"manual\"" + "," + "\"countable\": \"true\"" + "," +
	 * "\"order\": {" + "\"amount\": \"" + importe + "\"," + "\"tokenId\": \"" +
	 * transactionToken + "\"," + "\"currency\": \"" + currency + "\"," +
	 * "\"purchaseNumber\": " + purchaseNumber + "}" + "}"; log.info(
	 * "Respuesta JSON : " + requestBody);
	 * 
	 * Gson gson = new Gson(); Map<String, Object> mapRpta; token =
	 * this.sessionvisa; try { mapRpta = indexService.postRequest(urlSession,
	 * requestBody, token); PrintWriter out = response.getWriter(); if
	 * ((boolean) mapRpta.get("error")) { rptaVisa.put("respuestaServer",
	 * false); TransaccionDenegada denegada = gson.fromJson((String)
	 * mapRpta.get("data"), TransaccionDenegada.class); System.out.println(
	 * "<br> Transacción Denegada <hr>"); System.out.println(
	 * "<br> Número de pedido: " + purchaseNumber); System.out.println(
	 * "<br> Importe: " + importe); System.out.println("<br> Moneda: " +
	 * currency); System.out.println("<br> Brand: " +
	 * denegada.getData().getBRAND()); System.out.println("<br> Descripción: " +
	 * denegada.getData().getACTION_DESCRIPTION());
	 * 
	 * rptaVisa.put("pedidoID", purchaseNumber); rptaVisa.put("Importe",
	 * importe); rptaVisa.put("Moneda", currency); String descripcion =
	 * denegada.getData().getACTION_DESCRIPTION(); boolean repetido =
	 * descripcion.contains("duplicado"); if (!repetido) { String dia =
	 * denegada.getData().getTRANSACTION_DATE(); String[] formato = new
	 * String[6]; formato[0] = dia.substring(0, 2);// AÑO formato[1] =
	 * dia.substring(2, 4);// MES formato[2] = dia.substring(4, 6);// DIA
	 * formato[3] = dia.substring(6, 8);// HORA formato[4] = dia.substring(8,
	 * 10);// MINUTO formato[5] = dia.substring(10, 12);// SEGUNDO
	 * rptaVisa.put("Fecha", formato[2] + "-" + formato[1] + "-" + formato[0]);
	 * rptaVisa.put("Hora", formato[3] + ":" + formato[4] + ":" + formato[5]); }
	 * else { rptaVisa.put("Fecha", "00/00/00"); rptaVisa.put("Hora",
	 * "00:00:00"); } rptaVisa.put("mensajeServer",
	 * denegada.getData().getACTION_DESCRIPTION()); } else {
	 * rptaVisa.put("respuestaServer", true); TransaccionAprobada aprobada =
	 * gson.fromJson((String) mapRpta.get("data"), TransaccionAprobada.class);
	 * 
	 * System.out.println("<br> Transacción Aprobada <hr>"); System.out.println(
	 * "<br> Número de pedido: " + purchaseNumber); System.out.println(
	 * "<br> Brand: " + aprobada.getDataMap().getBRAND()); System.out.println(
	 * "<br> Card: " + aprobada.getDataMap().getCARD()); System.out .println(
	 * "<br> mensajeServer | Descripción: " +
	 * aprobada.getDataMap().getACTION_DESCRIPTION()); System.out.println(
	 * "<br> Importe: " + importe); System.out.println("<br> Moneda: " +
	 * currency); System.out.println("<br> Transaction id: " +
	 * aprobada.getOrder().getTransactionId()); // rptaVisa.put("",
	 * purchaseNumber); ETICKET = aprobada.getOrder().getTokenId(); String
	 * EticketVisa = "" + purchaseNumber; String NumeroTarjeta = "" +
	 * aprobada.getDataMap().getCARD(); String TarjetaHabiente = "";
	 * 
	 * VentaInterface ventadatos = new VentaInterfaceI(); String userCliente =
	 * ""; B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
	 * B_CuentaCorrienteBean cuentacorrienteVerifica = new
	 * B_CuentaCorrienteBean();
	 * 
	 * int operacion = -1; List<B_VentaBean> listaVenta =
	 * serviceventa.getVerificaVentaNoConfirmadaVisa(token);
	 * 
	 * if (listaVenta.size() == 0) {
	 * 
	 * log.info(
	 * "************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************"
	 * );
	 * 
	 * operacion = serviceventa.UpdateVentasVisaAuditoria(token);
	 * 
	 * log.info("VALOR DE OPERACION DE LAS VENTAS DE AUDITORIA -->" +
	 * operacion); log.info(
	 * "************************ VOLVIENDO LAS VENTAS DE LA AUDITORIA A PENDIENTES ************************"
	 * ); log.info("token --> :" + token); mensajeServer =
	 * B_VentaBean.MENSAJE_ERROR_INTERNO; ESTADO = "DENEGADO"; return
	 * "ERRORINTERNO"; } for (B_VentaBean venta : listaVenta) { userCliente =
	 * venta.getUsuarioVisa(); operacion =
	 * serviceventa.updateVentaConfirmada(venta.getNro(), venta.getSalida(),
	 * B_VentaBean.ESTADO_CONFIRMADO, EticketVisa, venta.getUsuarioVisa(),
	 * NumeroTarjeta, TarjetaHabiente.toUpperCase(), ETICKET, 1); if (operacion
	 * != -1) { cuentacorrienteVerifica = servicecuentacorriente
	 * .ListaVentaPagoEfectivoCuentaCorriente(venta.getNro()); if
	 * (cuentacorrienteVerifica == null) { log.info(
	 * "PREPARANDO EL REGISTRO EN LA CUENTA CORRIENTE VISA  -- N° VENTA --> "+
	 * venta.getNro()); B_Correlativos correlativos = new B_Correlativos();
	 * correlativos = servicecorrelativo.generaCorrelativo(); cuentacorriente =
	 * ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
	 * cuentacorriente = ventadatos.DatosDinamicosCuentaCorrienteVisa(venta,
	 * cuentacorriente, correlativos, EticketVisa); operacion =
	 * servicecuentacorriente.insertCuentaCorriente(cuentacorriente); } else {
	 * log.info(
	 * "LA VENTA YA EXISTE EN LA CUENTA CORRIENTE VISA -- N° VENTA --> " +
	 * venta.getNro()); } if (operacion == -1) { log.info(
	 * "************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************"
	 * ); log.info("TICKET --> :" + ETICKET); mensajeServer =
	 * B_VentaBean.MENSAJE_ERROR; ESTADO = "DENEGADO"; return "ERRORINTERNO"; }
	 * } else { log.info(
	 * "************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************"
	 * ); log.info("TICKET --> :" + ETICKET); mensajeServer =
	 * B_VentaBean.MENSAJE_ERROR; ESTADO = "DENEGADO"; return "ERRORINTERNO"; }
	 * }
	 * 
	 * log.info("CORREO DEL CLIENTE --> " + userCliente.trim()); log.info(
	 * "VENTA REALIZADA EXITOSAMENTE"); respuestaServer = true; mensajeServer =
	 * "La Operacion se realizo Exitosamente.";
	 * 
	 * List<B_VentaBean> ventaVisa = new ArrayList<B_VentaBean>(); ventaVisa =
	 * serviceventa.getVentaImprimirVisa((EticketVisa.trim().equals("")) ? "" :
	 * EticketVisa);
	 * 
	 * 
	 * GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(userCliente.trim
	 * (), EticketVisa),DescargaPDFTicket(ventaVisa));
	 * 
	 * log.info(
	 * "****************************************** OPERACION DE VISA GENERADO CORRECTAMENTE --> TICKET "
	 * + ETICKET + " ******************************************");
	 * 
	 * String dia = aprobada.getDataMap().getTRANSACTION_DATE(); String[]
	 * formato = new String[6];
	 * 
	 * formato[0] = dia.substring(0, 2);// AÑO formato[1] = dia.substring(2,
	 * 4);// MES formato[2] = dia.substring(4, 6);// DIA formato[3] =
	 * dia.substring(6, 8);// HORA formato[4] = dia.substring(8, 10);// MINUTO
	 * formato[5] = dia.substring(10, 12);// SEGUNDO rptaVisa.put("Fecha",
	 * formato[2] + "-" + formato[1] + "-" + formato[0]); rptaVisa.put("Hora",
	 * formato[3] + ":" + formato[4] + ":" + formato[5]);
	 * 
	 * rptaVisa.put("pedidoID", purchaseNumber); rptaVisa.put("Card",
	 * aprobada.getDataMap().getCARD()); rptaVisa.put("mensajeServer",
	 * aprobada.getDataMap().getACTION_DESCRIPTION()); rptaVisa.put("Importe",
	 * importe); rptaVisa.put("Moneda", currency);
	 * 
	 * } } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * data.put("rptaVisa", rptaVisa); stack.push(data); return SUCCESS; }
	 */

	@Action(value = "/accedepagoSTOP", results = { @Result(name = SUCCESS, location = "ventavisa/detallevisa.jsp") })
	public String accedePago_v2() {

		try {
			String token = indexService.generarToken();
			email = "ventatelefonica1@grupopalomino.com.pe";
			String merchantId = "522591303";// request.getParameter("merchantId");
			String currency = "PEN";// request.getParameter("currency");
			importe = "90.0";
			String urlSession = Configuration.URL_API_SESSION + merchantId;
			String requestBody = "{" + "\"amount\": \"90.0\"," + "\"channel\": \"web\"" + "}";

			Gson gson = new Gson();
			Map<String, Object> mapRpta = indexService.postRequest(urlSession, requestBody, token);

			String session = null;

			if ((boolean) mapRpta.get("error")) {

			} else {
				SessionClass sc = gson.fromJson((String) mapRpta.get("data"), SessionClass.class);
				session = sc.getSessionKey();
			}

			this.json = requestBody;
			this.sessionvisa = session;
			this.merchantId = merchantId;
			this.currency = currency;
			// request.setAttribute("json", requestBody);
			// request.setAttribute("session", session);
			// request.setAttribute("merchantId", merchantId);
			// request.setAttribute("currency", currency);*

			Cookie tokenCookie = new Cookie("token", token);
			response.addCookie(tokenCookie);

			this.token = token;

			// request.setAttribute("token", token);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date hoy = new Date();
		purchaseNumber = hoy.getTime() / 100000;
		purchase = purchaseNumber.toString();
		return SUCCESS;
	}

	Long purchaseNumber;

	String email;
	String apellido, nombre;
	String purchase;
	String json;
	String sessionvisa;
	String merchantId;
	String currency;
	String importe;
	String token;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public Long getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(Long purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}

	public String getPurchase() {
		return purchase;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getSessionvisa() {
		return sessionvisa;
	}

	public void setSessionvisa(String sessionvisa) {
		this.sessionvisa = sessionvisa;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	// MODIFICAR PARA PRODUCCIÓN
	List<ListaPreVenta> ListaPreventaIdaLast = new ArrayList<>();
	List<ListaPreVenta> ListaPreventaIdaVueltaLast = new ArrayList<>();
	boolean idaVuelta = false;

	public boolean isIdaVuelta() {
		return idaVuelta;
	}

	public void setIdaVuelta(boolean idaVuelta) {
		this.idaVuelta = idaVuelta;
	}

	public List<ListaPreVenta> getListaPreventaIdaLast() {
		return ListaPreventaIdaLast;
	}

	public List<ListaPreVenta> getListaPreventaIdaVueltaLast() {
		return ListaPreventaIdaVueltaLast;
	}

	public void setListaPreventaIdaLast(List<ListaPreVenta> listaPreventaIdaLast) {
		ListaPreventaIdaLast = listaPreventaIdaLast;
	}

	public void setListaPreventaIdaVueltaLast(List<ListaPreVenta> listaPreventaIdaVueltaLast) {
		ListaPreventaIdaVueltaLast = listaPreventaIdaVueltaLast;
	}

	int offset;
	int limit;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}

	@Action(value = "DetallePasajerosVisa", results = { @Result(name = SUCCESS, type = "json") })
	public String GetDetallePasajerosVisa() {
		ListaPreventaIdaLast = (ArrayList<ListaPreVenta>) session.get("ListaPreventaLast");

		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> resultados = new HashMap<>();
		Map<String, Object> resultados2 = new HashMap<>();
		if (offset == 0 || limit == 0) {
			limit = 10;
			offset = 0;
		}

		if (limit > ListaPreventaIdaLast.size()) {
			limit = ListaPreventaIdaLast.size();
		}

		List<ListaPreVenta> rows = new ArrayList<>();
		int total = ListaPreventaIdaLast.size();
		ListaPreventaIdaLast.size();
		for (int i = offset; i < limit; i++) {
			rows.add(ListaPreventaIdaLast.get(i));
			System.out.print(ListaPreventaIdaLast.get(i).getNombre());
		}
		// resultados
		resultados.put("rows", rows);
		resultados.put("total", total);
		// getDestinoTDP
		resultados2.put("resultados", resultados);
		stack.push(resultados2);
		session.remove("ListaPreventaLast");
		return SUCCESS;
	}

	public SpringSecurityUser ObtenerPrincipal() {
		SpringSecurityUser usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return usuario;
	}

	public static void main(String[] args) {
		long long_ = 15955276;// 15955190;
		VentasVisaAction web = new VentasVisaAction();
		try {
			List<B_VentaBean> ventaVisa;
			ventaVisa = web.serviceventa.getVentaImprimirVisa(("" + long_).trim().equals("") ? "" : "" + long_);
			for (B_VentaBean item : ventaVisa) {
				String empresaD = web.CodigoDeEmpresaCondicionado(item.getNroDestinocodigo(),
						"" + item.getNroServiciocodigo());
				item.setEmpresa(empresaD);
				// GENERAR BOLETO
				web.facturacionservice.USP_GENERARBOLETO(item.getNro() + "", "4127", item.getRuc() == null ? 16 : 17,
						empresaD);
				// GENERAR CODIGO QR
				web.consumirservicio("" + item.getNro(), "B");
			}
			GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros("desarrolladorweb@grupopalomino.com.pe", "" + long_),
					web.DescargaPDFTicket(ventaVisa));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 * 
		 * Date fechaInicial; try { fechaInicial =
		 * dateFormat.parse("2020-03-05"); Date fechaFinal =
		 * dateFormat.parse("2020-03-07"); int dias = (int)
		 * ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
		 * 
		 * System.out.println("Hay " + dias + " dias de diferencia"); } catch
		 * (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}

	@SuppressWarnings("unchecked")
	@Action(value = "/accedepago", results = { 
			@Result(name = SUCCESS, location = "ventavisa/detallevisa.jsp"), // ventavisa/ventaboletovisa2.jsp"),
			@Result(name = "NOLOGUEADO", type = "redirectAction", location = "accederegistrocliente"),
			@Result(name = "PAGOEFECTIVO", type = "redirectAction", location = "generarcip"),
			@Result(name = "OPENPAY", type = "redirectAction", location = "generarcargo"),
			@Result(name = ERROR, type = "redirectAction", location = "salir") })
	public String accedePago() {
		SpringSecurityClient cliente = null; // (UserDetails)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String ClienteVisa = "";
		double sumaTotal = 0.0;
		try {
			VentaPaso1Form paso1Form = (session.get("paso1Form") == null ? null
					: (VentaPaso1Form) session.get("paso1Form"));

			if (paso1Form != null) {
				if (paso1Form.isIdaVuelta() || paso1Form.isIdaVuelta() == false) {
					idaVuelta = false;
					if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
						if (!paso1Form.isIdaVuelta()) {
							session.put("medioPago", medioPago);
						} else {
							session.put("medioPago", medioPago);
						}
						return "NOLOGUEADO";
					} else {
						if (!paso1Form.isIdaVuelta()) {
							if (session.get("medioPago") == null) {
								session.put("medioPago", medioPago);
							}
						} else {
							if (session.get("medioPago") == null) {
								session.put("medioPago", medioPago);
							}
						}
					}

					if (!paso1Form.isIdaVuelta()) {

						String medioPago = (session.get("medioPago") == null ? "" : (String) session.get("medioPago"));

						if (medioPago.trim().equals("")) {
							log.info("VALOR DE MEDIO DE PAGO VACIO");
							return ERROR;
						}

						if (medioPago.trim().equals("2")) {
							session.remove("medioPago");
							return "PAGOEFECTIVO";
						}
						
						if (medioPago.trim().equals("3")) {
							session.remove("medioPago");
							return "OPENPAY";
						}
					} else {

						String medioPago = (session.get("medioPago") == null ? "" : (String) session.get("medioPago"));
												
						if (medioPago.trim().equals("")) {
							log.info("VALOR DE MEDIO DE PAGO VACIO");
							return ERROR;

						}

						if (medioPago.trim().equals("2")) {
							session.remove("medioPago");
							return "PAGOEFECTIVO";
						}
						
						if (medioPago.trim().equals("3")) {
							session.remove("medioPago");
							return "OPENPAY";
						}
					}
					session.remove("medioPago");

					/*
					 * String ClienteVisa = ""; if(usuario instanceof
					 * SpringSecurityClient){ SpringSecurityClient cliente =
					 * (SpringSecurityClient) usuario; ClienteVisa =
					 * cliente.getUsername(); }
					 */
					SpringSecurityClient user = new SpringSecurityClient();
					if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {

						if (!(SecurityContextHolder.getContext().getAuthentication()
								.getPrincipal() instanceof String)) {

							if (SecurityContextHolder.getContext().getAuthentication()
									.getPrincipal() instanceof SpringSecurityClient) {

								cliente = (SpringSecurityClient) SecurityContextHolder.getContext().getAuthentication()
										.getPrincipal();

								ClienteVisa = cliente.getUsername();
								user = cliente;
							}
						}
					}

					if (cliente == null) {
						log.info("NO ES UNA INSTANCIA DE SpringSecurityClient -->"
								+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
						return ERROR;

					}

					/* PROCESO DE VISA */

					List<ListaPreVenta> ListaPreventaLastTemp = new ArrayList<>();
					if (paso1Form.isIdaVuelta()) {

						log.info("Entro a ida vuelta - Accediendo Pagina de Visa " + paso1Form.isIdaVuelta());

						List<ListaPreVenta> ListaPreventaIdaVuelta = (session.get("ListaPreventaIdaVuelta") == null
								? new ArrayList<ListaPreVenta>()
								: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));

						if (ListaPreventaIdaVuelta.size() > 0) {

							for (ListaPreVenta PreVentaIdaVuelta : ListaPreventaIdaVuelta) {
								
								B_VentaBean venta = serviceventa.selectVentaVisa(PreVentaIdaVuelta.getNro(),
										PreVentaIdaVuelta.getSalida());

								if (venta != null) {
									sumaTotal += venta.getPrecioAct();
									serviceventa.updateTiempoVisaVentaNoConfirmada(venta.getNro(), venta.getSalida(),
											ClienteVisa);
								} else {
									log.info("VENTA NO EXISTE INGRESANDO ACCEDEPAGO ListaPreventaIdaVuelta (IDA VUELTA)--> " + PreVentaIdaVuelta.getNro());
									return ERROR;
								}
							}
							
						} else {
							log.info("TAMANIO DE LA LISTA ListaPreventaIdaVuelta (IDA Y VUELTA) -->"
									+ ListaPreventaIdaVuelta.size());
							return ERROR;
						}

						log.info("suma total : " + sumaTotal);
						List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
								? new ArrayList<ListaPreVenta>()
								: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));

						if (ListaPreventaIda.size() > 0) {

							for (ListaPreVenta PreVentaIda : ListaPreventaIda) {

								B_VentaBean venta = serviceventa.selectVentaVisa(PreVentaIda.getNro(),
										PreVentaIda.getSalida());

								if (venta != null) {
									sumaTotal += venta.getPrecioAct();
									serviceventa.updateTiempoVisaVentaNoConfirmada(venta.getNro(), venta.getSalida(),
											ClienteVisa);
								} else {
									log.info("VENTA NO EXISTE INGRESANDO ACCEDEPAGO ListaPreventaIda (IDA VUELTA)--> "
											+ PreVentaIda.getNro());
									return ERROR;
								}
							}
						} else {
							log.info("TAMANIO DE LA LISTA ListaPreventaIda (IDA Y VUELTA) -->"
									+ ListaPreventaIda.size());
							return ERROR;
						}
						log.info("suma total : " + sumaTotal);
					} else {

						log.info("Entró a ida  - Accediendo Pagina de Visa " + paso1Form.isIdaVuelta());
						List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
								? new ArrayList<ListaPreVenta>()
								: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
						if (ListaPreventaIda.size() > 0) {
							for (ListaPreVenta PreVentaIda : ListaPreventaIda) {
								B_VentaBean venta = serviceventa.selectVentaVisa(PreVentaIda.getNro(),
										PreVentaIda.getSalida());
								if (venta != null) {
									sumaTotal += venta.getPrecioAct();
									serviceventa.updateTiempoVisaVentaNoConfirmada(venta.getNro(), venta.getSalida(),
											ClienteVisa);
								} else {
									log.info("VENTA NO EXISTE INGRESANDO ACCEDEPAGO ListaPreventaIda (IDA)--> "
											+ PreVentaIda.getNro());
									return ERROR;
								}
							}
						} else {
							log.info("TAMANIO DE LA LISTA ListaPreventaIda (IDA) -->" + ListaPreventaIda.size());
							return ERROR;
						}
					}

					String token = indexService.generarToken();

					if (paso1Form.isIdaVuelta()) {

						List<ListaPreVenta> ListaPreventaIdaVuelta = (session.get("ListaPreventaIdaVuelta") == null
								? new ArrayList<ListaPreVenta>()
								: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));
						ListaPreventaLastTemp.addAll(ListaPreventaIdaVuelta);
					} else {

						List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
								? new ArrayList<ListaPreVenta>()
								: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
						ListaPreventaLastTemp.addAll(ListaPreventaIda);
						session.put("ListaPreventaLast", ListaPreventaLastTemp);
					}

					System.out.println("ListaPreventaLastTemp => " + ListaPreventaLastTemp.toString());

					try {
						email = cliente.getEmail();// "ventatelefonica1@grupopalomino.com.pe";
						// String merchantId = "522591303";//
						// request.getParameter("merchantId");
						// 734499
						String merchantId = Configuration.MERCHANT_ID;
						String currency = "PEN";// request.getParameter("currency");
						importe = "" + sumaTotal;
						String urlSession = Configuration.URL_API_SESSION + merchantId;
						ListaPreventaLastTemp.get(0).getFechaEmision();
						String[] ubicacion = ListaPreventaLastTemp.get(0).getDestinoD().split("-");
						String FechaViaje = ListaPreventaLastTemp.get(0).getFechaViaje();
						String Fecha[] = FechaViaje.split("-");

						// SimpleDateFormat dateFormat = new
						// SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						// SimpleDateFormat formatter6=new
						// SimpleDateFormat("MMM/dd/yyyy");
						Date date1 = formatter1.parse(Fecha[0] + "-" + Fecha[1] + "-" + Fecha[2]);
						Date hoy = new Date();
						// Fecha=user.getFechacreacion().split("-");
						// Date
						// fechacreacionusuario=formatter1.parse(user.getFechacreacion());
						Date fechacreacionusuario = formatter1.parse(user.getFechacreacion());

						int dias = (int) ((date1.getTime() - hoy.getTime()) / 86400000);
						System.out.println("DIAS DE SERVICIOS" + dias);
						int diasdesdecreacion = (int) ((hoy.getTime() - fechacreacionusuario.getTime()) / 86400000);
						System.out.println("DIAS DE CREACIÓN" + diasdesdecreacion);
						if (dias == 0 || dias == 1) {
							dias = 24;
						} else {
							dias = dias * 24;
						}

						String requestBody = "{" + "\"amount\": \"" + sumaTotal + "\","

								+ "\"antifraud\":{" + "\"merchantDefineData\":{" + "\"MDD4\":\"" + user.getUsername()
								+ "\","
								// +"\"MDD6\":32," +"\"MDD6\":"+dias+","
								+ "\"MDD18\":\"" + ListaPreventaLastTemp.get(0).getDestinoD() + "\"," + "\"MDD19\":\""
								+ ubicacion[0] + "\"," + "\"MDD20\":\"" + ubicacion[1] + "\"," + "\"MDD30\":\""
								+ user.getNumerodocumento() + "\","
								// +"\"MDD32\":\"175\","
								+ "\"MDD32\":\"" + user.getId() + "\"," + "\"MDD63\":\""
								+ user.getIdentidad().replace(".", "") + "\"," + "\"MDD65\":\""
								+ user.getNumerodocumento() + "\"," + "\"MDD75\":\"Registrado\"," // +"\"MDD77\":1120"
								+ "\"MDD77\":" + diasdesdecreacion + "" + "}" + "}," + "\"channel\": \"web\"" + "}";

						System.out.println("JSON ENVIADO" + requestBody);

						Gson gson = new Gson();
						Map<String, Object> mapRpta = indexService.postRequest(urlSession, requestBody, token);

						String sessiondevisa = null;

						if ((boolean) mapRpta.get("error")) {

						} else {
							SessionClass sc = gson.fromJson((String) mapRpta.get("data"), SessionClass.class);
							sessiondevisa = sc.getSessionKey();
						}
						this.json = requestBody;
						this.sessionvisa = sessiondevisa;
						this.merchantId = merchantId;
						this.currency = currency;
						Cookie tokenCookie = new Cookie("token", token);
						response.addCookie(tokenCookie);
						this.token = token;

						token = sessiondevisa;
						ListaPreventaLastTemp = new ArrayList<>();
						if (paso1Form.isIdaVuelta()) {
							List<ListaPreVenta> ListaPreventaIdaVuelta = (session.get("ListaPreventaIdaVuelta") == null
									? new ArrayList<ListaPreVenta>()
									: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));
							ListaPreventaLastTemp.addAll(ListaPreventaIdaVuelta);

							for (ListaPreVenta PreVentaIdaVuelta : ListaPreventaIdaVuelta) {

								B_VentaBean venta = serviceventa.selectVentaVisa(PreVentaIdaVuelta.getNro(),
										PreVentaIdaVuelta.getSalida());

								if (venta != null) {
									// serviceventa.updateTicketVenta(PreVentaIdaVuelta.getNro(),PreVentaIdaVuelta.getSalida(),
									// ETICKET);
									serviceventa.updateTicketVenta(PreVentaIdaVuelta.getNro(),
											PreVentaIdaVuelta.getSalida(), token);

								} else {
									log.info(
											"VENTA NO EXISTE ACTUALIZANDO EL TICKET ListaPreventaIdaVuelta (IDA Y VUELTA)--> "
													+ PreVentaIdaVuelta.getNro());
									return ERROR;
								}
							}

							List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
									? new ArrayList<ListaPreVenta>()
									: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
							ListaPreventaLastTemp.addAll(ListaPreventaIda);
							session.put("ListaPreventaLast", ListaPreventaLastTemp);

							for (ListaPreVenta PreVentaIda : ListaPreventaIda) {

								B_VentaBean venta = serviceventa.selectVentaVisa(PreVentaIda.getNro(),
										PreVentaIda.getSalida());

								if (venta != null) {
									// serviceventa.updateTicketVenta(PreVentaIda.getNro(),
									// PreVentaIda.getSalida(), ETICKET);
									serviceventa.updateTicketVenta(PreVentaIda.getNro(), PreVentaIda.getSalida(),
											token);

								} else {
									log.info(
											"VENTA NO EXISTE ACTUALIZANDO EL TICKET ListaPreventaIda (IDA Y VUELTA)--> "
													+ PreVentaIda.getNro());
									return ERROR;
								}
							}
							// ListaPreventaLastTemp.clear();
						} else {

							List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
									? new ArrayList<ListaPreVenta>()
									: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
							ListaPreventaLastTemp.addAll(ListaPreventaIda);
							session.put("ListaPreventaLast", ListaPreventaLastTemp);

							// ListaPreventaIdaLast.addAll((ArrayList<ListaPreVenta>)
							// session.get("ListaPreventaIda"));
							// List<ListaPreVenta> ListaIda =
							// (ArrayList<ListaPreVenta>)session.get("ListaPreventaIda");

							for (ListaPreVenta PreVentaIda : ListaPreventaIda) {
								B_VentaBean venta = serviceventa.selectVentaVisa(PreVentaIda.getNro(),
										PreVentaIda.getSalida());
								if (venta != null) {
									// serviceventa.updateTicketVenta(PreVentaIda.getNro(),
									// PreVentaIda.getSalida(), ETICKET);
									serviceventa.updateTicketVenta(PreVentaIda.getNro(), PreVentaIda.getSalida(),
											token);
								} else {
									log.info("VENTA NO EXISTE ACTUALIZANDO EL TICKET ListaPreventaIda (IDA)--> "
											+ PreVentaIda.getNro());
									return ERROR;
								}
							}

						}

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Date hoy = new Date();
					purchaseNumber = hoy.getTime() / 100000;
					purchase = purchaseNumber.toString();

					// ETICKET = GeneraTicketVisa.obtieneEticket(sumaTotal);
					// session.put("ETICKET", ETICKET);
					session.put("sumaTotal", sumaTotal);
					// log.info("TICKET GENERADO EN ACCEDEPAGO --> " + ETICKET +
					// " TOTAL :" + sumaTotal);
					log.info("GENERANDO TOKEN DE VISA--> " + token + " TOTAL :" + sumaTotal);
					log.info("GENERANDO TOKEN DE VISA--> " + token + " TOTAL :" + sumaTotal);

				} else {
					log.info("LA PROPIEDAD IDA Y VUELTA NO ES VERDADERO NI FALSO" + paso1Form.isIdaVuelta());
					return ERROR;

				}

			} else {
				log.info("PASO1FORM ES NULL " + paso1Form);
				return ERROR;
			}
			Limpiar_Session_Venta_Ida();
			Limpiar_Session_Venta_Vuelta();
			// session.remove("ListEmpresaIda");
			// session.remove("ListEmpresaAll");

		} catch (Exception e) {
			log.info("ENTRO AL CATH ACCEDEPAGO -->REDIRECCIONANDO A LA PAGINA PRINCIPAL DE PALOMINO");
			log.info(Utils.printStackTraceToString(e));
			return ERROR;
		}
		return SUCCESS;
	}
	/*
	 * List<VentaPaso3Form> listaPasajeros = new ArrayList<>();
	 * 
	 * public List<VentaPaso3Form> getListaPasajeros() { return listaPasajeros;
	 * } public void setListaPasajeros(List<VentaPaso3Form> listaPasajeros) {
	 * this.listaPasajeros = listaPasajeros; } Cupon cupon = new Cupon();
	 * 
	 * public Cupon getCupon() { return cupon; }
	 * 
	 * public void setCupon(Cupon cupon) { this.cupon = cupon; }
	 * 
	 * @Action(value = "DetalleComprobante", results = {@Result(name = SUCCESS,
	 * type = "json")}) public String DetalleComprobante() { listaPasajeros =
	 * (session.get("listaPasajeros") == null ? new ArrayList<VentaPaso3Form>():
	 * (List<VentaPaso3Form>) session.get("listaPasajeros")); cupon =
	 * (Cupon)session.get("Cuponactivo"); return SUCCESS; }
	 */

	@Action(value = "/pago", results = { @Result(name = SUCCESS, location = "ventavisa/resultadocompra.jsp"),
			@Result(name = ERROR, location = "ventavisa/errorVisa.jsp"),
			@Result(name = "ERRORINTERNO", location = "ventavisa/errorInterno.jsp") })
	public String pagoVisa() {
		try {
			int operacion = -1;
			List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());

			log.info(
					"****************************************** INGRESANDO LA RESPUESTA DE VISA ******************************************");
			log.info("PARAMETROS DE VISA --> " + parameterNames.toString());

			ETICKET = request.getParameter("eticket");

			log.info("VALOR DEL TICKET A CONSULTAR --> " + ETICKET);

			if (ETICKET != null && !(ETICKET.trim().isEmpty())) {

				respuestaVisa = ConsultaEticketVisa.obtieneRespuestaVisa(ETICKET);

				log.info("RESPUESTA DE VISA : XML -->  " + respuestaVisa);
				log.info("RESPUESTA DE VISA : RESPUESTA -->  " + respuestaVisa.get("respuesta"));
				log.info("TICKET GENERADO   : TICKET    -->  " + ETICKET);

				if (respuestaVisa.get("respuesta").equals(ConsultaEticketVisa.RESPUESTA_APROBADA)) {

					String EticketVisa = respuestaVisa.get("pedidoID");
					String NumeroTarjeta = respuestaVisa.get("pan");
					String TarjetaHabiente = respuestaVisa.get("nombre_th");
					VentaInterface ventadatos = new VentaInterfaceI();
					String userCliente = "";
					B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
					B_CuentaCorrienteBean cuentacorrienteVerifica = new B_CuentaCorrienteBean();

					List<B_VentaBean> listaVenta = serviceventa.getVerificaVentaNoConfirmadaVisa(ETICKET);

					if (listaVenta.size() == 0) {

						log.info(
								"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");

						operacion = serviceventa.UpdateVentasVisaAuditoria(ETICKET);

						log.info("VALOR DE OPERACION DE LAS VENTAS DE AUDITORIA -->" + operacion);
						log.info(
								"************************ VOLVIENDO LAS VENTAS DE LA AUDITORIA A PENDIENTES ************************");
						log.info("TICKET --> :" + ETICKET);
						mensajeServer = B_VentaBean.MENSAJE_ERROR_INTERNO;
						ESTADO = "DENEGADO";
						return "ERRORINTERNO";

					}

					for (B_VentaBean venta : listaVenta) {

						userCliente = venta.getUsuarioVisa();

						operacion = serviceventa.updateVentaConfirmada(venta.getNro(), venta.getSalida(),
								B_VentaBean.ESTADO_CONFIRMADO, EticketVisa, venta.getUsuarioVisa(), NumeroTarjeta,
								TarjetaHabiente.toUpperCase(), ETICKET, 1);

						if (operacion != -1) {

							cuentacorrienteVerifica = servicecuentacorriente
									.ListaVentaPagoEfectivoCuentaCorriente(venta.getNro());

							if (cuentacorrienteVerifica == null) {

								log.info("PREPARANDO EL REGISTRO EN LA CUENTA CORRIENTE VISA  -- N° VENTA --> "
										+ venta.getNro());

								B_Correlativos correlativos = new B_Correlativos();
								correlativos = servicecorrelativo.generaCorrelativo();

								cuentacorriente = ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
								cuentacorriente = ventadatos.DatosDinamicosCuentaCorrienteVisa(venta, cuentacorriente,
										correlativos, EticketVisa);
								operacion = servicecuentacorriente.insertCuentaCorriente(cuentacorriente);

							} else {

								log.info("LA VENTA YA EXISTE EN LA CUENTA CORRIENTE VISA -- N° VENTA --> "
										+ venta.getNro());

							}

							if (operacion == -1) {

								log.info(
										"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");
								log.info("TICKET --> :" + ETICKET);
								mensajeServer = B_VentaBean.MENSAJE_ERROR;
								ESTADO = "DENEGADO";
								return "ERRORINTERNO";

							}

						} else {

							log.info(
									"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");
							log.info("TICKET --> :" + ETICKET);
							mensajeServer = B_VentaBean.MENSAJE_ERROR;
							ESTADO = "DENEGADO";
							return "ERRORINTERNO";

						}

					}

					log.info("CORREO DEL CLIENTE --> " + userCliente.trim());
					log.info("VENTA REALIZADA EXITOSAMENTE");
					respuestaServer = true;
					mensajeServer = "La Operacion se realizo Exitosamente.";

					List<B_VentaBean> ventaVisa = new ArrayList<B_VentaBean>();
					ventaVisa = serviceventa.getVentaImprimirVisa((EticketVisa.trim().equals("")) ? "" : EticketVisa);
					GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(userCliente.trim(), EticketVisa),
							obtieneBAOS(ventaVisa));
					log.info(
							"****************************************** OPERACION DE VISA GENERADO CORRECTAMENTE --> TICKET "
									+ ETICKET + " ******************************************");
					return SUCCESS;

				} else {
					tarjetaCredito = new TarjetaCredito();
					tarjetaCredito.setCod_accion(Integer.parseInt(respuestaVisa.get("cod_accion")));
					ESTADO = respuestaVisa.get("estado");
					log.info("ERROR AL GENERAR EL PAGO : DESCRIPCION ERROR :" + tarjetaCredito.getDescripcionError());
					log.info(
							"****************************************** OPERACION DE VISA GENERADO CON ERRORES --> TICKET "
									+ ETICKET + " ******************************************");
					return ERROR;
				}
			} else {
				log.info(
						"**************************** ERROR CUANDO EL TICKET DE VISA ES NULL ****************************");
				log.info("TICKET VACIO --> :" + ETICKET);
				mensajeServer = B_VentaBean.MENSAJE_ERROR;
				ESTADO = "DENEGADO";
				return "ERRORINTERNO";
			}

		} catch (Exception e) {

			try {

				log.info(
						"**************************** ENTRO AL TRY CATH (VOLVIENDO PENDIENTE LA VENTA) ****************************");

				List<B_VentaBean> listaVenta = serviceventa.getVerificaVentaNoConfirmadaVisa(ETICKET);

				for (B_VentaBean bean : listaVenta) {

					serviceventa.updateVentaConfirmada(bean.getNro(), bean.getSalida(), B_VentaBean.ESTADO_PENDIENTE,
							null, bean.getUsuarioVisa(), null, null, ETICKET, 1);

				}

			} catch (Exception e2) {
				log.info(
						"**************************** ENTRO AL TRY CATH AL ACTUALIZAR LA VENTA A PENDIENTE ****************************");
				log.info(Utils.printStackTraceToString(e2));
			}

			log.info("**************************** OCURRIO UN ERROR ENTRO AL TRY CATH ****************************");
			log.info("TICKET --> :" + ETICKET);
			mensajeServer = B_VentaBean.MENSAJE_ERROR_INTERNO;
			ESTADO = "DENEGADO";
			log.info(Utils.printStackTraceToString(e));
		}

		return "ERRORINTERNO";
	}

	// @SuppressWarnings("static-access")
	@Action(value = "generarvoucherVisa", results = {
			@Result(name = SUCCESS, type = "stream", params = { "contentType", "application/pdf", "inputName",
					"inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" }) })
	public void GeneraVoucher() {

		try {

			List<B_VentaBean> ventaVisa = new ArrayList<B_VentaBean>();

			ventaVisa = serviceventa.getVentaImprimirVisa((pedidoID.trim().equals("")) ? "" : pedidoID);

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment;filename=Voucher - " + sdf1.format(new Date()) + ".pdf");

			ServletOutputStream outputStream = response.getOutputStream();

			ByteArrayOutputStream baos = obtieneBAOS(ventaVisa);

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

	// @SuppressWarnings("static-access")
	@Action(value = "canjeavoucher", results = {
			@Result(name = SUCCESS, type = "stream", params = { "contentType", "application/pdf", "inputName",
					"inputStream", "contentDisposition", "filename=\"test.pdf\"", "bufferSize", "1024" }) })
	public void GeneraVoucherSinCanjear() {

		try {

			B_VentaBean venta = new B_VentaBean();

			String[] Separador = NroVoucher.split("-");

			venta = serviceventa.selectImprimirVoucherCliente(Integer.parseInt(Separador[0]),
					Integer.parseInt(Separador[1]));

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment;filename=Voucher - " + sdf1.format(new Date()) + ".pdf");

			ServletOutputStream outputStream = response.getOutputStream();

			ByteArrayOutputStream baos = obtieneBAOSVenta(venta);

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

	private ByteArrayOutputStream obtieneBAOS(List<B_VentaBean> ventaVisa) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			Document documento = new Document();
			if (ventaVisa.size() > 0) {
				PdfWriter.getInstance(documento, baos);
				documento.open();

			}

			int contador = 0;

			for (B_VentaBean bean : ventaVisa) {

				// InputStream rutaIda =
				// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
				InputStream rutaIda = null;

				if (bean.getTipoEmpresa() == 1) {

					rutaIda = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "Embarque_Palomino.jpg");

				} else if (bean.getTipoEmpresa() == 2) {

					rutaIda = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "Embarque_Poseidon.jpg");

				}

				// GENERAMOS EL VOUCHER DE IDA
				// ___________________________________________________________________________________________________________________________________
				Image logoIda = Image.getInstance(IOUtils.toByteArray(rutaIda));
				logoIda.scalePercent(22f);
				logoIda.setAbsolutePosition(20f, 610f);
				logoIda.setAlignment(Element.ALIGN_LEFT);

				documento.add(logoIda);

				PdfPTable table = new PdfPTable(35);

				table = FuncionesPDF.F_Reporte_Ventas(table, bean.getDestinoD().trim(), bean.getOrigen().trim(),
						bean.getServicio().trim(), bean.getNombre().trim(), bean.getDNI(), bean.getNro(),
						bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje().substring(0, 10),
						bean.getHoraViaje(), "N", bean.getFechaEmision(), bean.getPrecioAct(), bean.getRuc(),
						bean.getRazon(), bean.getHoraCompra(), "Tarjeta", "VISA", bean.getTarjetaHabiente(), "",
						bean.getEticketVisa(), null, bean.getDestinoRutaD());

				documento.add(table);

				// InputStream rutaplantillafooterIda =
				// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Plantilla_Base_Tarjeta.jpg");

				InputStream rutaplantillafooterIda = null;

				if (bean.getTipoEmpresa() == 1) {

					rutaplantillafooterIda = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "Plantilla_Base_Tarjeta.jpg");

				} else if (bean.getTipoEmpresa() == 2) {

					rutaplantillafooterIda = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "Plantilla_Base_Tarjeta_Poseidon.jpg");
				}

				Image logofooterIda = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterIda));
				logofooterIda.scalePercent(23f);
				logofooterIda.setAbsolutePosition(5f, 75f);
				logofooterIda.setAlignment(Element.ALIGN_LEFT);

				documento.add(logofooterIda);
				contador = contador + 1;

				if (contador != ventaVisa.size()) {
					documento.newPage();
				}
			}
			documento.close();
		} catch (Exception e) {
			Utils.printStackTraceToString(e);
		}

		return baos;
	}

	private ByteArrayOutputStream obtieneBAOSVenta(B_VentaBean bean) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			Document documento = new Document();

			PdfWriter.getInstance(documento, baos);
			documento.open();

			// InputStream rutaIda =
			// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");

			InputStream rutaIda = null;

			if (bean.getTipoEmpresa() == 1) {

				rutaIda = request.getServletContext()
						.getResourceAsStream("_lib/img" + File.separator + "Embarque_Palomino.jpg");

			} else if (bean.getTipoEmpresa() == 2) {

				rutaIda = request.getServletContext()
						.getResourceAsStream("_lib/img" + File.separator + "Embarque_Poseidon.jpg");

			}

			// GENERAMOS EL VOUCHER DE IDA
			// ___________________________________________________________________________________________________________________________________
			Image logoIda = Image.getInstance(IOUtils.toByteArray(rutaIda));
			logoIda.scalePercent(22f);
			logoIda.setAbsolutePosition(20f, 610f);
			logoIda.setAlignment(Element.ALIGN_LEFT);

			documento.add(logoIda);

			PdfPTable table = new PdfPTable(35);

			table = FuncionesPDF.F_Reporte_Ventas(table, bean.getDestinoD().trim(), bean.getOrigen().trim(),
					bean.getServicio().trim(), bean.getNombre().trim(), bean.getDNI(), bean.getNro(),
					bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje().substring(0, 10),
					bean.getHoraViaje(), "N", bean.getFechaEmision(), bean.getPrecioAct(), bean.getRuc(),
					bean.getRazon(), bean.getHoraCompra(), "Tarjeta", "VISA", bean.getTarjetaHabiente(), "",
					bean.getEticketVisa(), null, bean.getDestinoRutaD());

			documento.add(table);

			// InputStream rutaplantillafooterIda =
			// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Plantilla_Base_Tarjeta.jpg");

			InputStream rutaplantillafooterIda = null;

			if (bean.getTipoEmpresa() == 1) {

				rutaplantillafooterIda = request.getServletContext()
						.getResourceAsStream("_lib/img" + File.separator + "Plantilla_Base_Tarjeta.jpg");

			} else if (bean.getTipoEmpresa() == 2) {

				rutaplantillafooterIda = request.getServletContext()
						.getResourceAsStream("_lib/img" + File.separator + "Plantilla_Base_Tarjeta_Poseidon.jpg");
			}

			Image logofooterIda = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterIda));
			logofooterIda.scalePercent(23f);
			logofooterIda.setAbsolutePosition(5f, 75f);
			logofooterIda.setAlignment(Element.ALIGN_LEFT);

			documento.add(logofooterIda);

			documento.close();
		} catch (Exception e) {
			Utils.printStackTraceToString(e);
		}

		return baos;
	}

	private FacturacionService facturacionservice = new FacturacionServiceI();
	private V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI();
	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();

	public ByteArrayOutputStream DescargaPDFTicket(List<B_VentaBean> ventaVisa) {
		Rectangle tamaño = new Rectangle(250, 800);
		com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document();
		documentoPDF.setPageSize(tamaño);
		documentoPDF.setMargins(0, 0.5f, 0, 0.5f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			if (ventaVisa.size() > 0) {
				PdfWriter.getInstance(documentoPDF, baos);
				documentoPDF.open();
			}
			int contador = 0;

			for (B_VentaBean item : ventaVisa) {
				String id = "" + item.getNro();
				String em = item.getEmpresa();
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
				// logoPalomino.setAbsolutePosition(35f, 810f);
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
				if (contador != ventaVisa.size()) {
					documentoPDF.newPage();
				}
			}
			documentoPDF.close();
			baos.flush();
			baos.close();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return baos;
	}

	public void Limpiar_Session_Venta_Ida() {

		session.remove("ListaPreventaIda");
		session.remove("listaPasajeros");

	}

	public void Limpiar_Session_Venta_Vuelta() {

		session.remove("ListaPreventaIdaVuelta");
		session.remove("ListaAuxiliar");
		session.remove("listaPasajerosTabla");
		session.remove("listaPasajerosIdaVuelta");

	}

	public String getETICKET() {
		return ETICKET;
	}

	public void setETICKET(String eTICKET) {
		ETICKET = eTICKET;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setNroVoucher(String nroVoucher) {
		NroVoucher = nroVoucher;
	}

	public String getNroVoucher() {
		return NroVoucher;
	}

	public Map<String, String> getRespuestaVisa() {
		return respuestaVisa;
	}

	public void setRespuestaVisa(Map<String, String> respuestaVisa) {
		this.respuestaVisa = respuestaVisa;
	}

	public TarjetaCredito getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setEnvioCorreo(String envioCorreo) {
		EnvioCorreo = envioCorreo;
	}

	public String getEnvioCorreo() {
		return EnvioCorreo;
	}

	public String getPedidoID() {
		return pedidoID;
	}

	public void setPedidoID(String pedidoID) {
		this.pedidoID = pedidoID;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	public String getMedioPago() {
		return medioPago;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	private String CodigoDeEmpresaCondicionado(String nroruta, String servicioCodigo) {
		/*
		 * SERVICIOS DE TRANSPORTES WARI
		 * 
		 * 18 10 ABANCAY - LIMA 001 LIMA 84 4 LIMA - ABANCAY 002 ABANCAY 155 5
		 * LIMA - FERREÑAFE 005 FERREÑAFE 193 15 FERREÑAFE - LIMA 001 LIMA 226
		 * 20 LIMA - PUQUIO 012 PUQUIO 235 21 PUQUIO - LIMA 001 LIMA
		 */
		String respuesta = "002";
		// Primero se consulta por Tipo de Servicio
		if (servicioCodigo.equals("02"))// CHASQUI BUS
		{
			respuesta = "004";
		} else if (servicioCodigo.equals("16")) {// SERVICIO // INKA PLUS                     
			respuesta = "002";
		} else if (servicioCodigo.equals("18")) {// SERVICIO // KILLA PLUS                                         
			respuesta = "002";
		} 
		else {// SERIVICIO INKA CLASS POR DEFECTO
			List<String> rutaswari = new ArrayList<>();
			rutaswari.add("4");// LIMA - ABANCAY
			rutaswari.add("10");// ABANCAY - LIMA			
			rutaswari.add("20");// LIMA - PUQUIO
			rutaswari.add("21");// PUQUIO - LIMA
			//rutaswari.add("5");// LIMA - FERREÑAFE
			//rutaswari.add("15");// FERREÑAFE - LIMA
			//rutaswari.add("20");// LIMA - PUQUIO
			//rutaswari.add("21");// PUQUIO - LIMA
			// CONSULTA POR RUTAS
			for (String nroRuta : rutaswari) {
				if (nroruta.equals(nroRuta)) {
					respuesta = "004";
					break;
				}
			}
		}
		return respuesta;
	}

	//static String URL_BASE = "http://172.16.10.3:8080/wspalominotest/palomino/fe/generadocumento";
	//static String URL_BASE = "http://172.16.10.3:8080/wspalomino/palomino/fe/generadocumento";
	public void consumirservicio(String nro, String tipo) {
		System.out.println("INGRESO AL WEBSERVICE");
		try {
			URL url = new URL(Utils.URL_BASE  + "?nro=" + nro + "&tipoOperacion=" + tipo);
			System.out.println(Utils.URL_BASE );
			System.out.println(Utils.URL_BASE  + "?nro=" + nro + "&tipoOperacion=" + tipo);
			// ?nro=3337445&tipoOperacion=B
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			/*
			 * conn.setRequestProperty("apikey", ""); conn.setRequestProperty(
			 * "codigocomercio", "");
			 */
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder respuesta = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				respuesta.append(output);
			}
			System.out.println(respuesta);
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//action openpay
	@SuppressWarnings({ "unused","unchecked"})
	@Action(value = "/generarcargo", results = {
			@Result(name = SUCCESS, location = "VentaOpenpay/openPay.jsp"),
			@Result(name = "ERRORCIP", type = "redirectAction", location = "salir")})
	public String generarcargo() throws Exception {		
		log.info("GENERANDO CARGO OPENPAY");
		log.info("********************************************************************************************");
		SpringSecurityClient cliente = null; // (UserDetails)
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date());
		double sumaTotal = 0.0;
		double igv = 0.0;
		double subTotal = 0.0;
		String numero_Orden = "";
		String FechaExpirar = "";
		String TokenCIP = "";
		String Asiento = "";
		
		TokenCIP = (UUID.randomUUID().toString()).substring(0, 10) + calendario.getTimeInMillis();		
		//this.ETICKET = TokenCIP.toString();
		session.put("TokenCIP", TokenCIP);
	
		
		UUID number = UUID.randomUUID();
		System.out.println(number.toString().substring(0,7));		
		log.info("Entrando a ver el paso1Form ");
		VentaPaso1Form paso1Form = (session.get("paso1Form") == null ? null
				: (VentaPaso1Form) session.get("paso1Form"));
		B_CuentaCorrienteBean cuentacorrienteVuelta = new B_CuentaCorrienteBean();
		VentaInterface ventadatos = new VentaInterfaceI();
		B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
		int operacion = -1;
		log.info("Paso el paso1Form ");
		log.info("ENTRO A GENERAR TOCKEN OPEN PAY " + TokenCIP.toString());
		//log.info("ENTRO A PASO1FORM " + paso1Form.toString());		
		UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (usuario instanceof SpringSecurityClient) {
			cliente = (SpringSecurityClient) usuario;

		} else {

			log.info("ERROR AL OBTENER DATOS DEL CLIENTE SPRINGSECURITY --> "
					+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return ERROR;

		}
		
		List<ListaPreVenta> ListaPreventaLastTemp = new ArrayList<>();
		if (paso1Form.isIdaVuelta()) {

			List<ListaPreVenta> ListaPreventaIdaVuelta = (session.get("ListaPreventaIdaVuelta") == null
					? new ArrayList<ListaPreVenta>()
					: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));
			ListaPreventaLastTemp.addAll(ListaPreventaIdaVuelta);
		} else {

			List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
					? new ArrayList<ListaPreVenta>()
					: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
			ListaPreventaLastTemp.addAll(ListaPreventaIda);
			session.put("ListaPreventaLast", ListaPreventaLastTemp);
		}
		
		if (paso1Form != null) {

			if (paso1Form.isIdaVuelta() || paso1Form.isIdaVuelta() == false) {

				if (!paso1Form.isIdaVuelta()) {

					log.info("ENTRO TODO OK  " + paso1Form.isIdaVuelta());

					List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
							? new ArrayList<ListaPreVenta>()
							: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
					if (ListaPreventaIda.size() > 0) {

						for (ListaPreVenta ventaIda : ListaPreventaIda) {

							B_VentaBean venta = serviceventa.selectVentaOpenpay(ventaIda.getNro(),ventaIda.getSalida());
							log.info("Datos Venta -->" + venta.toString());
							if (venta != null) {

								log.info("NRO  OK -->" + ventaIda.getNro());
								log.info("TokenCIP -->" + TokenCIP);
								log.info("Salida -->" + venta.getSalida());
								log.info("Salida -->" + venta.getAsiento());
								Asiento=venta.getAsiento();
								
								
								venta = serviceventa.SelectVentaDatosOpenPay(venta.getNro(), venta.getSalida(),	TokenCIP);
								log.info("Respuesta Datos OpenPAy  -->" + venta.toString());

								if (venta != null) {
									sumaTotal += venta.getPrecioAct();
									numero_Orden = String.valueOf(venta.getNro());
									FechaExpirar = venta.getFechaExpiracion();

								} else {
									log.info("ERROR AL OBTENER LOS DATOS DE LA VENTA (IDA) -->" + ventaIda.getNro());
									return ERROR;
								}
							} else {
								log.info("LA VENTA NO EXISTE (IDA) -->" + ventaIda.getNro());
								return ERROR;

							}

						}					
						

						System.out.println("ListaPreventaLastTemp => " + ListaPreventaLastTemp.toString());
						
						
						
						igv=sumaTotal*0.18;
						subTotal=sumaTotal - igv;
						
						session.put("emailopenpay", cliente.getEmail());
						session.put("emailopenpay", cliente.getNombreCompleto());
						session.put("asientoopenpay", Asiento);						
						session.put("subtotalopenpay", subTotal);
						session.put("igvopenpay", igv);
						session.put("totalopenpay", sumaTotal);
						session.put("numordenopenpay", numero_Orden);
						session.put("expiraopenpay", FechaExpirar);
						session.put("tokenopenpay", TokenCIP);
						
						request.setAttribute("totalopenpay", sumaTotal);
						request.setAttribute("numordenopenpay", numero_Orden);
						request.setAttribute("expiraopenpay", FechaExpirar);
						//request.getRequestDispatcher("VentaOpenpay/openPay.jsp").forward(request, response);
						
						log.info("totalopenpay -->" + sumaTotal);
						log.info("totalopenpay -->" + numero_Orden);
						log.info("totalopenpay -->" + FechaExpirar);

					} else {

						log.info("TAMANIO DE LA PREVENTA (IDA)" + ListaPreventaIda.size());
						return ERROR;

					}
					
				} else {

					log.info("ENTRO TODO OK  " + paso1Form.isIdaVuelta());

					List<ListaPreVenta> ListaPreventaIdaVuelta = (session.get("ListaPreventaIdaVuelta") == null
							? new ArrayList<ListaPreVenta>()
							: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));
					ListaPreventaLastTemp.addAll(ListaPreventaIdaVuelta);
					session.put("ListaPreventaLast", ListaPreventaLastTemp);

					if (ListaPreventaIdaVuelta.size() > 0) {

						for (ListaPreVenta PreVentaIdaVuelta : ListaPreventaIdaVuelta) {

							B_VentaBean venta = serviceventa.selectVentaOpenpay(PreVentaIdaVuelta.getNro(),
									PreVentaIdaVuelta.getSalida());

							if (venta != null) {

								log.info("NRO  OK -->" + venta.getNro());
								log.info("TokenCIP -->" + TokenCIP);
								log.info("Salida -->" + venta.getSalida());	
								Asiento=venta.getAsiento();
								venta = serviceventa.SelectVentaDatosOpenPay(venta.getNro(), venta.getSalida(),TokenCIP);
								log.info("Respuesta Datos OpenPAy  -->" + venta.toString());
								if (venta != null) {
									sumaTotal += venta.getPrecioAct();
									numero_Orden = String.valueOf(venta.getNro());
									FechaExpirar = venta.getFechaExpiracion();

								} else {
									log.info("ERROR AL OBTENER LOS DATOS DE LA VENTA (IDA VUELTA - IDAVUELTA) -->"
											+ PreVentaIdaVuelta.getNro());
									return ERROR;
								}
							} else {
								log.info("LA VENTA NO EXISTE (IDA VUELTA - IDAVUELTA) -->"
										+ PreVentaIdaVuelta.getNro());
								return ERROR;
							}
						}

					} else {

						log.info("TAMANIO DE LA PREVENTA (IDA VUELTA - IDAVUELTA)" + ListaPreventaIdaVuelta.size());
						return ERROR;

					}

					List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
							? new ArrayList<ListaPreVenta>()
							: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
					ListaPreventaLastTemp.addAll(ListaPreventaIda);
					session.put("ListaPreventaLast", ListaPreventaLastTemp);

					if (ListaPreventaIda.size() > 0) {
						for (ListaPreVenta PreVentaIda : ListaPreventaIda) {

							B_VentaBean venta = serviceventa.selectVentaOpenpay(PreVentaIda.getNro(),
									PreVentaIda.getSalida());

							if (venta != null) {
								log.info("NRO  OK -->" + venta.getNro());
								venta = serviceventa.SelectVentaDatosOpenPay(venta.getNro(), venta.getSalida(),TokenCIP);

								if (venta != null) {
									sumaTotal += venta.getPrecioAct();
									numero_Orden = String.valueOf(venta.getNro());
									FechaExpirar = venta.getFechaExpiracion();
									Asiento=venta.getAsiento();

								} else {
									log.info("ERROR AL OBTENER LOS DATOS DE LA VENTA (IDA VUELTA - IDA) -->"
											+ PreVentaIda.getNro());
									return ERROR;
								}
							} else {
								log.info("LA VENTA NO EXISTE (IDA VUELTA - IDA) -->" + PreVentaIda.getNro());
								return ERROR;
							}
						}
						igv=sumaTotal*0.18;
						subTotal=sumaTotal - igv;						
						
						session.put("emailopenpay", cliente.getEmail());
						session.put("nombreopenpay", cliente.getNombreCompleto());			
						session.put("asientoopenpay", Asiento);					
						session.put("subtotalopenpay", subTotal);
						session.put("igvopenpay", igv);
						session.put("totalopenpay", sumaTotal);
						session.put("numordenopenpay", numero_Orden);
						session.put("expiraopenpay", FechaExpirar);
						session.put("tokenopenpay", TokenCIP);
						
						log.info("totalopenpay -->" + sumaTotal);
						log.info("totalopenpay -->" + numero_Orden);
						log.info("totalopenpay -->" + FechaExpirar);
						request.setAttribute("totalopenpay", sumaTotal);
						request.setAttribute("numordenopenpay", numero_Orden);
						request.setAttribute("expiraopenpay", FechaExpirar);
						
						

					} else {

						log.info("TAMANIO DE LA PREVENTA (IDA VUELTA - IDA)" + ListaPreventaIda.size());
						return ERROR;

					}
				}
				

			} else {
				log.info("EL VALOR DE IDA Y VUELTA NO ES TRUE NI FALSE (GENERANDO EL CIP)-->"
						+ paso1Form.isIdaVuelta());
				return ERROR;
			}

		} else {
			log.info("PASO1FORM ES NULL SE ACABO EN TIEMPO DE SESION DEL APLICATIVO (GENERANDO EL CIP)");
			return ERROR;
		}		
		
		return SUCCESS;
		
	}
	
	
	
	
	@Action(value = "pagaropenpay", results = { @Result(name = SUCCESS, location = "VentaOpenpay/resultadocompra.jsp"),
			@Result(name = "ERROROPEN", location = "VentaOpenpay/errorOpen.jsp")})
	public String Pagar() throws Exception {
		
		HttpServletRequest req = (HttpServletRequest) request;		
		System.out.println(req.toString());		
		String Eticket=getETICKET();
		HttpServletRequest request=ServletActionContext.getRequest();
		SpringSecurityClient cliente = null; // (UserDetails)
		String tarjeta=request.getParameter("tarjeta");
		String mes=request.getParameter("mes");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellidos");
		String anio=request.getParameter("anio");
		String Telefono=request.getParameter("telefono");
		String Correo=request.getParameter("correo");
		String monto=request.getParameter("monto");
		String ccv=request.getParameter("ccv");
		String deviceSessionId=request.getParameter("deviceSessionId");
		
		
		
		
		System.out.println(request.toString());
		System.out.println(tarjeta.toString());
		session.put("tarjeta", tarjeta.toString());
		System.out.println(mes.toString());
		System.out.println(anio.toString());
		System.out.println(Telefono.toString());
		System.out.println(Correo.toString());
		System.out.println(monto.toString());
		session.put("pagado", monto.toString());
		System.out.println(ccv.toString());
		System.out.println(nombre.toString());
		System.out.println(deviceSessionId.toString());
		
		session.put("nombre", nombre.toString() + "," + apellido.toString());
		
		
		System.out.println(apellido.toString());
		
		HashMap<String,String> paramsMap=new HashMap<>();
		 paramsMap.put ("card_number", tarjeta.toString());
		 paramsMap.put ("holder_name", nombre.toString());
		 paramsMap.put ("expiration_year", anio.toString());
		 paramsMap.put ("expiration_month", mes.toString());
		 paramsMap.put ("cvv2", ccv.toString());		 
		FormBody.Builder builder = new FormBody.Builder();
		for (String key : paramsMap.keySet()) {
		                // Agregar información del formulario
		   builder.add(key, paramsMap.get(key));
		}
		
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "\r\n{\r\n\"card_number\":\""+tarjeta.toString()+"\",\r\n\"holder_name\":\""+nombre.toString()+"\",\r\n\"expiration_year\":\""+anio.toString()+"\",\r\n\"expiration_month\":\""+mes.toString()+"\",\r\n\"cvv2\":\""+ccv.toString()+"\"\r\n}\r\n\r\n");
		Request request1 = new Request.Builder()
		  .url("https://api.openpay.pe/v1/mairmpmgrdhx0pvqo2i2/tokens")
		  .method("POST", body)
		  .addHeader("MERCHANT_ID", "mairmpmgrdhx0pvqo2i2")
		  .addHeader("Authorization", "Basic cGtfN2YxMWRkZjNiNzliNGUxY2JlMjM5MjU3YjRhNTI4Yzg6")
		  .addHeader("Content-Type", "application/json")
		  .build();		
		//mairmpmgrdhx0pvqo2i2
		//Llave privada sk_fd99b618a09348678ff74225c50474a9
		//Llave pública pk_7f11ddf3b79b4e1cbe239257b4a528c8
		
	
		Response response = client.newCall(request1).execute();		
		//String jsonData = response.body().string();
		//ResponseEntity.ok(response.body());	    
		System.out.println(response.body().string());		
		
		Headers responseHeaders = response.headers();
		System.out.println(responseHeaders.toString());	
		System.out.println(responseHeaders.get("resource-id"));	
		
		UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (usuario instanceof SpringSecurityClient) {
			cliente = (SpringSecurityClient) usuario;

		} else {

			log.info("ERROR AL OBTENER DATOS DEL CLIENTE SPRINGSECURITY --> "
					+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return ERROR;

		}
		System.out.println("valida resource-id");
		if(responseHeaders.get("resource-id")==null) {
			return "ERROROPEN";
		} 
		
		String validador=responseHeaders.get("resource-id").toString();
		System.out.println("valida resource-id" + validador);
		
		if(!validador.isEmpty()) {			
			String Token=responseHeaders.get("resource-id");
			System.out.println("Inicio de Cargo");
			//kR1MiQhz2otdIuUlQkbEyitIqVMiI16f		
			RequestBody body1 = RequestBody.create(mediaType, "\r\n{\r\n\"source_id\" : \""+Token+"\",\r\n\"device_session_id\": \""+deviceSessionId+"\",\r\n    \"method\": \"card\",\r\n    \"amount\":"+monto.toString()+",\r\n\"currency\": \"PEN\",\r\n    \"description\": \"Cargo con Cuotas\",\r\n    \"use_card_points\": \"false\",\r\n    \"redirect_url\": \"https://api.grupopalomino.com.pe/openpay/clases/openPayConfirmacion.php\",\r\n    \"capture\": true,    \r\n    \"customer\" : {\r\n        \"name\" : \""+nombre.toString()+"\",\r\n        \"last_name\" : \""+apellido.toString()+"\",\r\n        \"phone_number\" : \""+Telefono.toString()+"\",\r\n        \"email\" : \""+Correo.toString()+"\"\r\n    }\r\n}");
			Request request2 = new Request.Builder()
			  .url("https://api.openpay.pe/v1/mairmpmgrdhx0pvqo2i2/charges")
			  .method("POST", body1)
			  .addHeader("Authorization", "Basic c2tfZmQ5OWI2MThhMDkzNDg2NzhmZjc0MjI1YzUwNDc0YTk6")
			  .addHeader("Content-Type", "application/json")
			  .build();
			Response response1 = client.newCall(request2).execute();
			System.out.println(response1.body().string());			
			
			Headers responseHeaders1 = response1.headers();		
			System.out.println(responseHeaders1.toString());
				
				if(responseHeaders1.get("error_code")!=null) {
					return "ERROROPEN";
				}
			
				if(!responseHeaders1.get("resource-id").isEmpty()) {
					System.out.println(responseHeaders1.get("resource-id"));
					System.out.println(responseHeaders1.get("error_code"));
					System.out.println(responseHeaders1.get("openpay-request-id"));
					
					if(responseHeaders1.get("op-error-code")!=null) {
						return "ERROROPEN";
					}
					
										
					
					String elticket=session.get("TokenCIP").toString();
					
					
					try {
					String EticketVisa = responseHeaders1.get("resource-id");
					session.put("iddelpedido", responseHeaders1.get("resource-id"));
					String NumeroTarjeta = tarjeta.toString();
					String TarjetaHabiente = cliente.getNombreCompleto();
					log.info(
							"************************ Instanciando Venta ************************");
					VentaInterface ventadatos = new VentaInterfaceI();
					String userCliente = "";
					log.info(
							"************************ Instanciando Cuenta Corriente ************************");
					B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
					B_CuentaCorrienteBean cuentacorrienteVerifica = new B_CuentaCorrienteBean();
					int operacion = -1;
					log.info(
							"************************ Ingresando a Listar la Venta " + elticket.toString() + " ************************");
					List<B_VentaBean> listaVenta = serviceventa.getVerificaVentaNoConfirmadaVisa(elticket.toString());
	
					if (listaVenta.size() == 0) {
	
						log.info(
								"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");
	
						operacion = serviceventa.UpdateVentasVisaAuditoria(elticket.toString());
	
						log.info("VALOR DE OPERACION DE LAS VENTAS DE AUDITORIA -->" + operacion);
						log.info(
								"************************ VOLVIENDO LAS VENTAS DE LA AUDITORIA A PENDIENTES ************************");
						log.info("TICKET --> :" + elticket.toString());
						mensajeServer = B_VentaBean.MENSAJE_ERROR_INTERNO;
						ESTADO = "DENEGADO";
						return "ERRORINTERNO";
	
					}
					
					for (B_VentaBean venta : listaVenta) {
	
						userCliente = venta.getUsuarioVisa();
						
						//cliente.getEmail();
						//cliente.getNombreCompleto();
						
	
						operacion = serviceventa.updateVentaConfirmada(venta.getNro(), venta.getSalida(),
								B_VentaBean.ESTADO_CONFIRMADO, "0", cliente.getEmail(), NumeroTarjeta,
								TarjetaHabiente.toUpperCase(), EticketVisa, 3);
	
						if (operacion != -1) {
	
							cuentacorrienteVerifica = servicecuentacorriente
									.ListaVentaPagoEfectivoCuentaCorriente(venta.getNro());
	
							if (cuentacorrienteVerifica == null) {
	
								log.info("PREPARANDO EL REGISTRO EN LA CUENTA CORRIENTE OPEN PAY  -- N° VENTA --> "
										+ venta.getNro());
	
								B_Correlativos correlativos = new B_Correlativos();
								correlativos = servicecorrelativo.generaCorrelativo();
								
	
								cuentacorriente = ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
								cuentacorriente = ventadatos.DatosDinamicosCuentaCorrienteVisa(venta, cuentacorriente,
										correlativos, EticketVisa);
								operacion = servicecuentacorriente.insertCuentaCorriente(cuentacorriente);
								
								log.info("CORREO DEL CLIENTE --> " + cliente.getEmail());
								log.info("VENTA REALIZADA EXITOSAMENTE");
								respuestaServer = true;
								mensajeServer = "La Operacion se realizo Exitosamente.";
				
								List<B_VentaBean> ventaVisa = new ArrayList<B_VentaBean>();
								ventaVisa = serviceventa.getVentaImprimirVisa((EticketVisa.trim().equals("")) ? "" : EticketVisa);								
								
								for (B_VentaBean item : ventaVisa) {
									String empresaD = CodigoDeEmpresaCondicionado(item.getNroDestinocodigo(),
											"" + item.getNroServiciocodigo());
									item.setEmpresa(empresaD);
									// GENERAR BOLETO
									facturacionservice.USP_GENERARBOLETO(item.getNro() + "", "4127", item.getRuc() == null ? 16 : 17,
											empresaD);
									// GENERAR CODIGO QR
									consumirservicio("" + item.getNro(), "B");
								}
								GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(userCliente.trim(), "" + EticketVisa.toString()),
										DescargaPDFTicket(ventaVisa));
								
								log.info(
										"****************************************** OPERACION DE OPEN PAY SE HA GENERADO CORRECTAMENTE --> TICKET "
												+ Eticket + " ******************************************");
	
							} else {
	
								log.info("LA VENTA YA EXISTE EN LA CUENTA CORRIENTE OPEN PAY -- N° VENTA --> "
										+ venta.getNro());
	
							}
	
							if (operacion == -1) {
	
								log.info(
										"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");
								log.info("TICKET --> :" + ETICKET);
								mensajeServer = B_VentaBean.MENSAJE_ERROR;
								ESTADO = "DENEGADO";
								return "ERRORINTERNO";
	
							}
	
						} else {
	
							log.info(
									"************************ ERROR AL OBTENER LOS DATOS DE LA VENTA EN LA BD ************************");
							log.info("TICKET --> :" + elticket.toString());
							mensajeServer = B_VentaBean.MENSAJE_ERROR;
							ESTADO = "DENEGADO";
							return "ERRORINTERNO";
	
						}
	
					}	
						log.info("CORREO DEL CLIENTE --> " + cliente.getEmail());
						log.info("VENTA REALIZADA EXITOSAMENTE");
						respuestaServer = true;
						mensajeServer = "La Operacion se realizo Exitosamente.";
		
						List<B_VentaBean> ventaVisa = new ArrayList<B_VentaBean>();
						ventaVisa = serviceventa.getVentaImprimirVisa((EticketVisa.trim().equals("")) ? "" : EticketVisa);
						GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(userCliente.trim(), EticketVisa),
								obtieneBAOS(ventaVisa));
						log.info(
								"****************************************** OPERACION DE OPEN PAY SE HA GENERADO CORRECTAMENTE --> TICKET "
										+ Eticket + " ******************************************");
						
					} catch (Exception e) {								
							log.info(e.getMessage());
					}		
					return SUCCESS;
				}
				else {		
					
					return "ERROROPEN";
				}				
		} else {			
			return "ERROROPEN";
		}
		
	}
}
