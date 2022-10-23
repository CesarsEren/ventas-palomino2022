package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import pe.com.grupopalomino.sistema.boletaje.bean.B_BoletosRedBusEliminados;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Cliente_SMS_Web;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.schedule.EliminaVentasNoConfirmadas;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativosService;
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
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterface;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterfaceI;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesFacturacionPDF;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmail;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('V','1')")
public class VentaTelefonicaAction extends ActionSupport implements SessionAware, ServletResponseAware ,ServletRequestAware{

	HttpServletResponse response;
	HttpServletRequest request;
	Map<String, Object> sesion;
	boolean todos = true;// consulta total
	String usuario;// consulta por usuario
	int offset;// para listar tablas
	int limit;// para listar tablas
	int nro;// nro par aliberar asiento

	String motivo;
	String codemesayuda;

	
	@Action(value = "/tableroventatelefonica", results = {
			@Result(name = SUCCESS, location = "Ventatelefonica/tablero/reportes.jsp") })
	public String GoTableroVentaTelefonica() {
		return SUCCESS;
	}

	@Action(value = "/liberarasientos", results = {
			@Result(name = SUCCESS, location = "Ventatelefonica/opciones/liberarasientos.jsp") })
	public String liberarasientos() {
		return SUCCESS;
	}

	public SpringSecurityUser ObtenerPrincipal() {
		SpringSecurityUser usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return usuario;
	}

	String DNI;
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	//SQL_GetVentasPagoEfectivoReconfirmar
	String Eticket;
	 
	@Action(value = "/listaPagoEfectivo", results = { @Result(name = SUCCESS, type = "json") })
	public String GetListaPafoEfectoWeb() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String,Object>rsp = new HashMap<>();
		rsp.put("rows", serviceventas.SelectVentasPagoEfectivoReconfirmar(Eticket));
		stack.push(rsp);
		return SUCCESS;
	}
//00000029411116
	@Action(value = "/ReconfirmarPagoEfectivo", results = { @Result(name = SUCCESS, type = "json") })
	public String liberarPagoEfectivo() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> rsp = new HashMap<>();
		//SpringSecurityUser usuario = ObtenerPrincipal();
		List<B_VentaBean> ventasPagoEfectivo = serviceventas.SelectVentasPagoEfectivoReconfirmar(Eticket);
		if (!ventasPagoEfectivo.isEmpty()) {
			try {
				System.out.println(""+Eticket);
				System.out.println(""+Eticket);
				System.out.println(""+Eticket);
				System.out.println(""+Eticket);
				System.out.println(""+Eticket);
				System.out.println(""+Eticket); 
				activomanualCodigoCIP(Eticket,ventasPagoEfectivo.get(0).getUsuarioVisa());
			} catch (Exception e) {
				e.printStackTrace();
			} 
			rsp.put("mensaje", "Los Asientos fueron Reconfirmados <br> enviado a <strong>"+ventasPagoEfectivo.get(0).getUsuarioVisa()+"<br>");
			rsp.put("msgserver", "Reconfirmado");
			stack.push(rsp);
		} else {
			rsp.put("mensaje", "No se encontraron asientos Relacionados a ese CIP");
			rsp.put("msgserver", "Error");
			stack.push(rsp);
		}
		return SUCCESS;
	}
	
	@Action(value = "/liberarasientoWeb", results = { @Result(name = SUCCESS, type = "json") })
	public String liberarasientoWeb() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> rsp = new HashMap<>();
		SpringSecurityUser usuario = ObtenerPrincipal();
		boolean encontrado = serviceventas.SelectISVentaWeb(nro,DNI);
		if (encontrado) {
			B_BoletosRedBusEliminados bean = new B_BoletosRedBusEliminados();
			bean.setCodigoMesaAyuda(codemesayuda);
			bean.setMotivo(motivo);
			bean.setNro("" + nro);
			bean.setUsuario(ObtenerPrincipal().getUsername()); 
			serviceventas.liberarAsientoxNro(nro, usuario.getUsername());
			rsp.put("mensaje", "EL Asiento fue liberado <br> Palomino Web");
			rsp.put("msgserver", "Liberado");
			stack.push(rsp);
		} else {
			rsp.put("mensaje", "EL Asiento no fue encontrado como compra de Palomino Web");
			rsp.put("msgserver", "Error");
			stack.push(rsp);
		}
		return SUCCESS;
	}
	
	@Action(value = "/liberarasientoRB", results = { @Result(name = SUCCESS, type = "json") })
	public String liberarasientoRB() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> rsp = new HashMap<>();
		SpringSecurityUser usuario = ObtenerPrincipal();
		boolean encontrado = serviceventas.SelectISVentaRedBus(nro);
		if (encontrado) {
			B_BoletosRedBusEliminados bean = new B_BoletosRedBusEliminados();
			bean.setCodigoMesaAyuda(codemesayuda);
			bean.setMotivo(motivo);
			bean.setNro("" + nro);
			bean.setUsuario(ObtenerPrincipal().getUsername());
			serviceventas.InserBoletosRedBusLiberados(bean);
			serviceventas.liberarAsientoxNro(nro, usuario.getUsername());
			rsp.put("mensaje", "EL Asiento fue liberado");
			rsp.put("msgserver", "Liberado");
			stack.push(rsp);
		} else {
			rsp.put("mensaje", "EL Asiento no fue encontrado como compra de RedBus");
			rsp.put("msgserver", "Error");
			stack.push(rsp);
		}
		return SUCCESS;
	}

	@Action(value = "/liberarasiento", results = { @Result(name = SUCCESS, type = "json") })
	public String liberarasiento() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> rsp = new HashMap<>();
		SpringSecurityUser usuario = ObtenerPrincipal();
		serviceventas.liberarAsientoxNro(nro, usuario.getUsername());
		rsp.put("mensaje", "EL Asiento fue liberado");
		rsp.put("msgserver", "Liberado");
		stack.push(rsp);
		return SUCCESS;
	}

	@Action(value = "/reconfirmarCIP", results = {
			@Result(name = SUCCESS, location = "Ventatelefonica/opciones/reconfirmarcip.jsp") })
	public String reconfirmarCIP() {
		return SUCCESS;
	}

	UsuariosWebService serviceusuario = new UsuariosWebServiceI();
	VentaBoletaService serviceventas = new VentaBoletaServiceI();

	@Action(value = "/cbousventelefonica", results = { @Result(name = SUCCESS, type = "json") })
	public String listar_croquisTDP() throws Exception {
		ValueStack stack = ActionContext.getContext().getValueStack();
		List<Map<String, Object>> res1 = new ArrayList<>();
		Map<String, Object> res2 = new HashMap<>();
		List<V_Cliente_SMS_Web> usuarios = serviceusuario.ListaUsuariosVentaTelefonica();
		for (V_Cliente_SMS_Web user : usuarios) {
			Map<String, Object> resU = new HashMap<String, Object>();
			resU.put("id", user.getId());
			resU.put("text", user.getUsername());
			res1.add(resU);
		}
		res2.put("usventatelefonica", res1);
		stack.push(res2);
		return SUCCESS;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Action(value = "/listventasventelefonicas", results = { @Result(name = SUCCESS, type = "json") })
	public String listventasventelefonicas() throws Exception {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> res1 = new HashMap<>();
		Map<String, Object> res2 = new HashMap<>();

		List<B_VentaBean> ventas = serviceventas.SelectVentasTelefonicasXUsuario(todos, usuario, limit, offset);
		int TOTAL = serviceventas.SelectCountVentasTelefonicasXUsuario(todos, usuario);
		res1.put("rows", ventas);
		res1.put("total", TOTAL);
		res2.put("usventatelefonica", res1);
		stack.push(res2);
		return SUCCESS;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public boolean isTodos() {
		return todos;
	}

	/*
	 * @Action(value = "/croquis", results = { @Result(name = SUCCESS, type =
	 * "json")}) public String listar_croquisTDP(){
	 * 
	 * return SUCCESS; }
	 */

	@Override
	public void setServletResponse(HttpServletResponse response1) {
		// TODO Auto-generated method stub
		response = response1;
	}

	@Override
	public void setSession(Map<String, Object> sesion1) {
		// TODO Auto-generated method stub
		sesion = sesion1;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getCodemesayuda() {
		return codemesayuda;
	}

	public void setCodemesayuda(String codemesayuda) {
		this.codemesayuda = codemesayuda;
	}
	public String getEticket() {
		return Eticket;
	}
	public void setEticket(String eticket) {
		Eticket = eticket;
	}
	private static final Logger log = Logger.getLogger(VentaTelefonicaAction.class);
	VentaBoletaService venta = new VentaBoletaServiceI();
	private CorrelativosService servicecorrelativo = new CorrelativoServiceI();
	private CuentaCorrienteService servicecuentacorriente = new CuentaCorrienteServiceI();
	
	public void activomanualCodigoCIP(String CodigoCIP,String Correo) throws Exception{ 
		//List<B_VentaBean> ventasPagoEfectivo = new ArrayList<>();  
		try { 
				int operacion = -1; 
				//for (B_VentaBean pago : ventasPagoEfectivo) { 
					//log.info("VENTAS EXPIRADAS POR VERIFICAR PAGOEFECTIVO CIP -->  "+CodigoCIP); 
					Map<String, String> resultados = new HashMap<>(); 
					resultados.put("estado", "593");
					//resultados = ConsultasAction.ConsultaPagoEfectivo(CodigoCIP); 
					log.info("ESTADO SERVICIO PAGOEFECTIVO  --> ESTADO : "+resultados.get("estado")+" CIP : "+ CodigoCIP);
					
					if (resultados.get("estado").trim().equals("593")){
						
						List<B_VentaBean> ventaPagada = new ArrayList<B_VentaBean>();
						ventaPagada = venta.SelectListVentaPagoEfectivo(CodigoCIP);
						VentaInterface ventadatos = new VentaInterfaceI();
						
						for (B_VentaBean bean : ventaPagada) {
							
							B_Correlativos correlativos = new B_Correlativos();
		        			
		        			correlativos = servicecorrelativo.generaCorrelativo();
		        			
		        			if(correlativos != null){
		        			
		        				B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
				        		
				        		operacion = venta.updateVentaPagoEfectivoVerificaConfirmacion(bean.getNro(),bean.getSalida(), B_VentaBean.ESTADO_CONFIRMADO, 2,Integer.parseInt(resultados.get("estado").trim()));
				        		
				        		if(operacion != -1){
				        			
				        			B_CuentaCorrienteBean beanverifica = new B_CuentaCorrienteBean();
				        			
				        			beanverifica = servicecuentacorriente.ListaVentaPagoEfectivoCuentaCorriente(bean.getNro());
				        			
				        			if(beanverifica == null){
				        				cuentacorriente =  ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente); 
					        			cuentacorriente = ventadatos.DatosDinamicosCuentaCorrientePagoEfectivo(bean, cuentacorriente, correlativos);
					        			operacion = servicecuentacorriente.insertCuentaCorriente(cuentacorriente);
				        			}else{
				        				
				        				log.info("VENTA EXPIRADA YA EXISTE EN LA CUENTACORRIENTE  CIP --> " + bean.getEticket());
				        			}
				        			
				        			if(operacion !=-1){
				        				log.info("PROCESO DE TRANSACCION DE VENTA EXPIRADA TERMINADA CON EXITO  CIP --> " + bean.getEticket());
				        			}else{
				        				log.info("ERROR AL ELIMINAR EN LA CUENTA CORRIENTE CIP--> " + bean.getEticket());
				        			}
				        		}else{
				        			log.info("ERROR AL ACTUALIZAR EN LA VENTA EXPIRADA  CIP--> " + bean.getEticket());
				        		}
		        				
		        			}else{
				        		log.info("ERROR AL GENERAR CORRELATIVOS CIP--> " + bean.getEticket());
				        	}
						}
						
						List<B_VentaBean> ventaPagoEfectivo = new ArrayList<B_VentaBean>(); 
			        	
			        	ventaPagoEfectivo = venta.getVentaImprimirPagoEfectivo(CodigoCIP);
			        	//log.info("VENTA EXPIRADA Y CONFIRMADA PAGOEFECTIVO CIP --> " + CodigoCIP); 
			        	//GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(resultados.get("usuarioEmail").toString(),ventaPagoEfectivo.get(0).getEticketVisa()), Utils.BaosPagoEfectivo(ventaPagoEfectivo));
            	        for(B_VentaBean item : ventaPagoEfectivo ){
            	        	String empresaD  = CodigoDeEmpresaCondicionado(item.getNroDestino(), ""+item.getNroServicio());
            	        	item.setEmpresa(empresaD);
            	            //GENERAR BOLETO
            		        facturacionservice.USP_GENERARBOLETO(item.getNro() + "", "4127",item.getRuc() == null ? 16 : 17,empresaD);		          
            		        //GENERAR CODIGO QR
            			    consumirservicio(""+item.getNro(), "B");	        	
            	        }	
			        	GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(Correo,ventaPagoEfectivo.get(0).getEticketVisa()), DescargaPDFTicket(ventaPagoEfectivo));
		        	 
					} 
				 
		} catch (Exception e) {
			log.info("****************** ENTRO AL TRY CATCH DE  LAS VENTAS QUE ESTÁN FUERA DE HORA DE PAGOEFECTIVO ******************");
			log.info(Utils.printStackTraceToString(e));
		}
	}
	

	public void consumirservicio(String nro, String tipo) {
		try {
			URL url = new URL(URL_BASE + "?nro=" + nro + "&tipoOperacion=" + tipo);
			System.out.println(URL_BASE);
			System.out.println(URL_BASE + "?nro=" + nro + "&tipoOperacion=" + tipo);
			// ?nro=3337445&tipoOperacion=B
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
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
	private String CodigoDeEmpresaCondicionado(String nroruta,String servicioCodigo) {
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
		} else if (servicioCodigo.equals("09")) {// SERVICIO // INTIPLUS
			respuesta = "002";
		} else {// SERIVICIO INKA CLASS POR DEFECTO
			List<String> rutaswari = new ArrayList<>();
			rutaswari.add("4");// LIMA - ABANCAY
			rutaswari.add("10");// ABANCAY - LIMA
			rutaswari.add("5");// LIMA - FERREÑAFE
			rutaswari.add("15");// FERREÑAFE - LIMA
			rutaswari.add("20");// LIMA - PUQUIO
			rutaswari.add("21");// PUQUIO - LIMA
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
	
	static String URL_BASE = "https://ventas.grupopalomino.com.pe:8443/wspalomino/palomino/fe/generadocumento"; 
	
	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
	private V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI();

	private FacturacionService facturacionservice = new FacturacionServiceI();

	public ByteArrayOutputStream DescargaPDFTicket(List<B_VentaBean> ventaVisa) {
		Rectangle tamaño = new Rectangle(250,800);
		com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document();
		documentoPDF.setPageSize(tamaño);
		documentoPDF.setMargins(0,0.5f,0,0.5f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try { 
			if (ventaVisa.size() > 0) {
				PdfWriter.getInstance(documentoPDF, baos);
				documentoPDF.open();
			}
			int contador = 0;

			for (B_VentaBean item: ventaVisa) {
				String id = ""+item.getNro();
				String em = item.getEmpresa();
				/**/
				V_Varios_FacturacionBean facturacionEmpresa = variosservice
						.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(em);

				InputStream rutaLogoPalomino;
				if (em.equals("002")) {
					rutaLogoPalomino = 
							new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/palomino.jpg").openStream();
					/*request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "palomino.jpg");*/
				} else {
					rutaLogoPalomino = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/Transporte_Wari.png").openStream();
							/*request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "Transporte_Wari.png");*/
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

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
}
