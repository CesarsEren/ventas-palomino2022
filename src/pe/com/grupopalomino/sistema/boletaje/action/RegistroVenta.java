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
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso2Form;
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
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterface;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterfaceI;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesFacturacionPDF;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesPDF;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmail;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','3','2')")
public class RegistroVenta extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

	// SERVICES
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private CuentaCorrienteService servicecuentacorriente = new CuentaCorrienteServiceI();
	// private UsuariosWebService serviceusuario = new UsuariosWebServiceI();
	/************************************************************************************/
	private FacturacionService facturacionservice = new FacturacionServiceI();
	private V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI();
	/*************************************************************************************/
	// VARIABLES DE SERVIDOR
	private boolean respuestaServer = true;
	private String mensajeServer;
	private Map<String, Object> session;
	private HttpServletRequest request;
	private static final Log log = LogFactory.getLog(RegistroVenta.class);
	private String EnvioCorreo;
	//static String URL_BASE = "http://172.16.10.3:8080/wspalominotest/palomino/fe/generadocumento";
	//static String URL_BASE = "http://172.16.10.3:8080/wspalomino/palomino/fe/generadocumento";
	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();

	List<String> ids = new ArrayList<>();
	
	private HttpServletResponse servletResponse;

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}
	/*
	 * protected HttpServletRequest servletRequest;
	 * 
	 * public void setServletRequest(HttpServletRequest servletRequest) {
	 * this.servletRequest = servletRequest; }
	 */

	List<String>listempresaida = new ArrayList<>();
	@SuppressWarnings("unchecked")
	@Action(value = "/registraventaida", results = { @Result(name = SUCCESS, type = "json") })
	public String RegistraVentaIda() {

		try {

			List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
					? new ArrayList<ListaPreVenta>() : (List<ListaPreVenta>) (session.get("ListaPreventaIda")));
			VentaInterface ventadatos = new VentaInterfaceI();
			B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
			VentaPaso1Form paso1Form = (VentaPaso1Form) session.get("paso1Form");
			VentaPaso2Form paso2FormIda = (VentaPaso2Form) session.get("paso2FormIDA");
			int operacion = -1;

			SpringSecurityUser usuario = null;

			if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {

				if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
					if (SecurityContextHolder.getContext().getAuthentication()
							.getPrincipal() instanceof SpringSecurityUser) {
						usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
								.getPrincipal();
					}
				}
			}

			log.info("COMENZANDO A GENERAR LA VENTA DE AGENTE AUTORIZADO (IDA): USUARIO -->" + usuario.getUsername());
			if (ListaPreventaIda.size() > 0) {

				for (ListaPreVenta ventaIda : ListaPreventaIda) {

					operacion = serviceventa.updateVentaConfirmada(ventaIda.getNro(), ventaIda.getSalida(),
							B_VentaBean.ESTADO_CONFIRMADO, "-1", null, null, null, null, usuario.getMedio_Pago());

					if (operacion != -1) {

						log.info("ACTUALIZANDO LA VENTA Y REGISTRANDO EN LA CUENTA CORRIENTE (IDA) "
								+ usuario.getUsername() + " NRO VENTA:" + ventaIda.getNro());

						cuentacorriente = ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
						cuentacorriente = ventadatos.DatosDinamicosCuentaCorriente(ventaIda,
								/* ventaIda */cuentacorriente/* , correlativo */, paso1Form, paso2FormIda, usuario, "N",
								null, null);
						operacion = servicecuentacorriente.insertCuentaCorriente(cuentacorriente);

						if (operacion == -1) {

							log.info(
									"OCURRIO UN PROBLEMA AL REGISTRAR EN LA CUENTA CORRIENTE (IDA) --> Nro Cuenta Corriente:"
											+ ventaIda.getNroCC() + " Numero Venta : " + ventaIda.getNro());
							respuestaServer = false;
							mensajeServer = "Ocurrio un Problema en el Registro de la Cuenta Corriente "
									+ "(por favor envie un mensaje al siguiente correo mesadeayuda@grupopalomino.com.pe)";
							return SUCCESS;
						}
						// Genera Boleto
						
						listempresaida.add(CodigoDeEmpresaCondicionado(paso1Form, paso2FormIda));
						
						
facturacionservice.USP_GENERARBOLETO(ventaIda.getNro() + "", "4127",ventaIda.getRuc() == null ? 16 : 17,CodigoDeEmpresaCondicionado(paso1Form, paso2FormIda));
						// generar codigo de barras codehash y QrCode
						consumirservicio(ventaIda.getNro() + "", "B");
						ids.add(ventaIda.getNro() + "");
					} else {
						log.info("OCURRIO UN PROBLEMA AL REGISTRAR EN LA VENTA (IDA) --> Nro Venta :"
								+ ventaIda.getNro());
						respuestaServer = false;
						mensajeServer = "Ocurrio un Problema en el Registro de la Venta "
								+ "(por favor envie un mensaje al siguiente correo mesadeayuda@grupopalomino.com.pe)";
						return SUCCESS;
					}
				}
				session.put("ListEmpresaIda",listempresaida);
				respuestaServer = true;

				if (EnvioCorreo != null) {

					/*GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(EnvioCorreo.trim(), null),
							obtieneBAOS(ListaPreventaIda, null));*/
					
					GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(EnvioCorreo.trim(), null),
							DescargaPDFTicket(CodigoDeEmpresaCondicionado(paso1Form, paso2FormIda), ids, "B"));
					//obtieneBAOS(ListaPreventaIda, null);

				}

				log.info("VENTA REALIZADA CORRECTAMENTE DEL USUARIO (IDA) : " + usuario.getUsername()
						+ " FECHA DE EMISION : " + ListaPreventaIda.get(0).getFechaEmision());
				mensajeServer = "La Operacion se realizo Exitosamente.";
				return SUCCESS;

			} else {
				log.info("OCURRIO UN PROBLERMA EN LA LISTA DE LA PREVENTA NO HAY DATOS (IDA)" + " USUARIO : "
						+ usuario.getUsername());
				respuestaServer = false;
				mensajeServer = "No se pudo Realizar la Transaccion verifique los Datos del Pasajero."
						+ "(por favor envie un mensaje al siguiente correo mesadeayuda@grupopalomino.com.pe)";
				return SUCCESS;
			}

		} catch (Exception e) {
			Limpiar_Session_Venta_Ida();
			log.info(Utils.printStackTraceToString(e));
			respuestaServer = false;
			mensajeServer = e.getMessage();
			return SUCCESS;
		}

	}
	List<String> allids = new ArrayList<>();
	List<String>listempresaall = new ArrayList<>();
	@SuppressWarnings("unchecked")
	@Action(value = "/registroventaidavuelta", results = { @Result(name = SUCCESS, type = "json") })
	public String RegistroIdaVuelta() {

		try {

			List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
					? new ArrayList<ListaPreVenta>() : (List<ListaPreVenta>) (session.get("ListaPreventaIda")));
			List<ListaPreVenta> ListaPreventaIdaVuelta = (session.get("ListaPreventaIdaVuelta") == null
					? new ArrayList<ListaPreVenta>() : (List<ListaPreVenta>) (session.get("ListaPreventaIdaVuelta")));
			VentaInterface ventadatos = new VentaInterfaceI();
			B_CuentaCorrienteBean cuentacorrienteIda = new B_CuentaCorrienteBean();
			B_CuentaCorrienteBean cuentacorrienteVuelta = new B_CuentaCorrienteBean();
			VentaPaso1Form paso1Form = (VentaPaso1Form) session.get("paso1Form");
			VentaPaso2Form paso2FormIda = (VentaPaso2Form) session.get("paso2FormIDA");
			VentaPaso2Form paso2FormVUELTA = (VentaPaso2Form) session.get("paso2FormVUELTA");

			SpringSecurityUser usuario = null;

			if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {

				if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
					if (SecurityContextHolder.getContext().getAuthentication()
							.getPrincipal() instanceof SpringSecurityUser) {
						usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
								.getPrincipal();
					}
				}
			}

			int operacion = -1;

			log.info("COMENZANDO A GENERAR LA VENTA DE AGENTE AUTORIZADO  (IDA Y VUELTA): USUARIO -->"
					+ usuario.getUsername());
			if ((ListaPreventaIda.size() > 0) && (ListaPreventaIdaVuelta.size() > 0)) {

				for (ListaPreVenta ventaIda : ListaPreventaIda) {

					operacion = serviceventa.updateVentaConfirmada(ventaIda.getNro(), ventaIda.getSalida(),
							B_VentaBean.ESTADO_CONFIRMADO, "-1", null, null, null, null, usuario.getMedio_Pago());

					if (operacion != -1) {

						log.info("ACTUALIZANDO LA VENTA Y REGISTRANDO EN LA CUENTA CORRIENTE (IDA) (IDA Y VUELTA) "
								+ usuario.getUsername() + " NRO VENTA: " + ventaIda.getNro());
						cuentacorrienteIda = ventadatos.DatosEstaticosCuentaCorriente(cuentacorrienteIda);
						cuentacorrienteIda = ventadatos.DatosDinamicosCuentaCorriente(
								ventaIda, /* ventaIda */cuentacorrienteIda/* , correlativo */, paso1Form, paso2FormIda,
								usuario, "N", null, null);
						operacion = servicecuentacorriente.insertCuentaCorriente(cuentacorrienteIda);

						if (operacion == -1) {
							log.info(
									"OCURRIO UN PROBLEMA AL REGISTRAR EN LA CUENTA CORRIENTE (IDA) (IDA Y VUELTA) --> Nro Cuenta Corriente:"
											+ ventaIda.getNroCC() + " Numero Venta : " + ventaIda.getNro());
							respuestaServer = false;
							mensajeServer = "Ocurrio un Problema en el Registro de la Cuenta Corriente "
									+ "(por favor envie un mensaje al siguiente correo mesadeayuda@grupopalomino.com.pe)";
							return SUCCESS;
						}
						// Genera Boleto
						listempresaall.add(CodigoDeEmpresaCondicionado(paso1Form, paso2FormIda));
facturacionservice.USP_GENERARBOLETO(ventaIda.getNro() + "", "4127",ventaIda.getRuc() == null ? 16 : 17,CodigoDeEmpresaCondicionado(paso1Form, paso2FormIda));
						consumirservicio(ventaIda.getNro() + "", "B"); // Data =
						allids.add(ventaIda.getNro() + "");
						
					} else {
						log.info("OCURRIO UN PROBLEMA AL REGISTRAR EN LA VENTA (IDA) (IDA Y VUELTA) --> Nro Venta :"
								+ ventaIda.getNro());
						respuestaServer = false;
						mensajeServer = "Ocurrio un Problema en el Registro de la Venta "
								+ "(por favor envie un mensaje al siguiente correo mesadeayuda@grupopalomino.com.pe)";
						return SUCCESS;
						// break;

					}
				}

				/*****************************************************************************************************************************************************************************************************************/

				for (ListaPreVenta ventaVuelta : ListaPreventaIdaVuelta) {

					operacion = serviceventa.updateVentaConfirmada(ventaVuelta.getNro(), ventaVuelta.getSalida(),
							B_VentaBean.ESTADO_CONFIRMADO, "-1", null, null, null, null, usuario.getMedio_Pago());

					if (operacion != -1) {

						log.info("ACTUALIZANDO LA VENTA Y REGISTRANDO EN LA CUENTA CORRIENTE (VUELTA) (IDA Y VUELTA)"
								+ usuario.getUsername() + " NRO VENTA: " + ventaVuelta.getNro());
						cuentacorrienteVuelta = ventadatos.DatosEstaticosCuentaCorriente(cuentacorrienteVuelta);
						cuentacorrienteVuelta = ventadatos.DatosDinamicosCuentaCorrienteVuelta(ventaVuelta,
								/* ventaIda */cuentacorrienteVuelta/*
																	 * ,
																	 * correlativo
																	 */, paso1Form, paso2FormVUELTA, usuario, "Y", null,
								null);
						operacion = servicecuentacorriente.insertCuentaCorriente(cuentacorrienteVuelta);

						if (operacion == -1) {
							log.info(
									"OCURRIO UN PROBLEMA AL REGISTRAR EN LA CUENTA CORRIENTE (VUELTA) (IDA Y VUELTA) --> Nro Cuenta Corriente:"
											+ ventaVuelta.getNroCC() + " Numero Venta : " + ventaVuelta.getNro());
							respuestaServer = false;
							mensajeServer = "Ocurrio un Problema en el Registro de la Cuenta Corriente "
									+ "(por favor envie un mensaje al siguiente correo mesadeayuda@grupopalomino.com.pe)";
							return SUCCESS;
						}
						listempresaall.add(CodigoDeEmpresaCondicionado(paso1Form, paso2FormIda));
						facturacionservice.USP_GENERARBOLETO(ventaVuelta.getNro() + "", "4127",
								ventaVuelta.getRuc() == null ? 16 : 17,
								CodigoDeEmpresaCondicionado(paso1Form, paso2FormIda));
						consumirservicio(ventaVuelta.getNro() + "", "B");
						facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(ventaVuelta.getNro(), "B");
						allids.add(ventaVuelta.getNro() + "");
						 
					}

					if (operacion == -1) {
						log.info("OCURRIO UN PROBLEMA AL REGISTRAR EN LA VENTA (VUELTA) (IDA Y VUELTA) --> Nro Venta :"
								+ ventaVuelta.getNro());
						respuestaServer = false;
						mensajeServer = "Ocurrio un Problema en el Registro de la Venta "
								+ "(por favor envie un mensaje al siguiente correo mesadeayuda@grupopalomino.com.pe)";
						break;
					}
				}
				if (EnvioCorreo != null) {
					/*GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(EnvioCorreo.trim(), null),
							obtieneBAOS(ListaPreventaIda, ListaPreventaIdaVuelta));*/
					GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(EnvioCorreo.trim(), null),
							DescargaPDFTicketIdaVuelta(CodigoDeEmpresaCondicionado(paso1Form, paso2FormIda), allids, "B"));
				}

				log.info("VENTA REALIZADA CORRECTAMENTE DEL USUARIO (IDA Y VUELTA) : " + usuario.getUsername());
				respuestaServer = true;
				mensajeServer = "La Operacion se realizo Exitosamente.";
				
				session.put("ListEmpresaAll", listempresaall); 
				// Limpiar_Session_Venta_Ida();
				// Limpiar_Session_Venta_Vuelta();
				return SUCCESS;

			} else {
				log.info("OCURRIO UN PROBLERMA EN LA LISTA DE LA PREVENTA NO HAY DATOS (IDA Y VUELTA)" + " USUARIO : "
						+ usuario.getUsername());
				respuestaServer = false;
				mensajeServer = "No se pudo Realizar la Transaccion verifique los Datos del Pasajero."
						+ "(por favor envie un mensaje al siguiente correo mesadeayuda@grupopalomino.com.pe)";
				return SUCCESS;
			}

		} catch (Exception e) {
			Limpiar_Session_Venta_Ida();
			Limpiar_Session_Venta_Vuelta();
			log.info(Utils.printStackTraceToString(e));
			respuestaServer = false;
			mensajeServer = e.getMessage();
			return SUCCESS;
		}
	}

	private ByteArrayOutputStream obtieneBAOS(List<ListaPreVenta> listaIda, List<ListaPreVenta> listaVuelta) {
		
		SpringSecurityUser usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		B_VentaBean bean = new B_VentaBean();

		try {

			Document documento = new Document();
			if (listaIda.size() > 0) {
				PdfWriter.getInstance(documento, baos);
				documento.open();

			}

			int contador = 0;

			for (ListaPreVenta listaventa : listaIda) {

				bean = serviceventa.getVentaImprimir(usuario.getUsername(), listaventa.getNro(),
						listaventa.getSalida());

				if (bean != null) {

					// InputStream rutaIda =
					// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");

					InputStream rutaIda = null;
					log.info("nro " + bean.getNro());
					log.info("nro " + bean.getNro());
					log.info("DESTINO D" + bean.getDestinoD());
					log.info("ORIGEN " + bean.getOrigen());
					log.info("DESTINOS " + bean.getDestinoRutaD());
					log.info("Empresa " + bean.getTipoEmpresa());

					if (bean.getDestinoD().trim().equals("CUSCO-LIMA")
							|| bean.getDestinoD().trim().equals("LIMA-CUSCO")) {
						rutaIda = request.getServletContext()
								.getResourceAsStream("_lib/img" + File.separator + "Embarque_Wari.jpg");
						log.info("entroó wari");
					} else if (bean.getTipoEmpresa() == 1) {
						rutaIda = request.getServletContext()
								.getResourceAsStream("_lib/img" + File.separator + "Embarque_Palomino.jpg");

						log.info("entroó PALOMINO");
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
							bean.getRazon(), bean.getHoraCompra(), "", null, null, null, null, bean.getUsuario(),
							bean.getDestinoRutaD());

					documento.add(table);

					// InputStream rutaplantillafooterIda =
					// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");

					InputStream rutaplantillafooterIda = null;

					if (bean.getTipoEmpresa() == 1) {

						rutaplantillafooterIda = request.getServletContext()
								.getResourceAsStream("_lib/img" + File.separator + "Embarque_Base.jpg");

					}

					Image logofooterIda = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterIda));
					logofooterIda.scalePercent(23f);
					logofooterIda.setAbsolutePosition(5f, 100f);
					logofooterIda.setAlignment(Element.ALIGN_LEFT);

					documento.add(logofooterIda);
					contador = contador + 1;

					if (contador != listaIda.size()) {
						documento.newPage();
					}

				}

			}

			if (listaVuelta != null) {

				documento.newPage();
				contador = 0;

				for (ListaPreVenta listaventa : listaVuelta) {

					bean = serviceventa.getVentaImprimir(usuario.getUsername(), listaventa.getNro(),
							listaventa.getSalida());

					if (bean != null) {

						// InputStream rutaIda =
						// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");

						InputStream rutaIda = null;

						if (bean.getDestinoD().trim().equals("CUSCO-LIMA")
								|| bean.getDestinoD().trim().equals("LIMA-CUSCO")) {
							rutaIda = request.getServletContext()
									.getResourceAsStream("_lib/img" + File.separator + "Embarque_Wari.jpg");
						} else if (bean.getTipoEmpresa() == 1) {

							rutaIda = request.getServletContext()
									.getResourceAsStream("_lib/img" + File.separator + "Embarque_Palomino.jpg");

						} else if (bean.getTipoEmpresa() == 2) {

							rutaIda = request.getServletContext()
									.getResourceAsStream("_lib/img" + File.separator + "Embarque_Poseidon.jpg");

						}

						// GENERAMOS EL VOUCHER DE VUELTA
						// ___________________________________________________________________________________________________________________________________
						Image logoVuelta = Image.getInstance(IOUtils.toByteArray(rutaIda));
						logoVuelta.scalePercent(22f);
						logoVuelta.setAbsolutePosition(20f, 610f);
						logoVuelta.setAlignment(Element.ALIGN_LEFT);

						documento.add(logoVuelta);

						PdfPTable table = new PdfPTable(35);

						table = FuncionesPDF.F_Reporte_Ventas(table, bean.getDestinoD().trim(), bean.getOrigen().trim(),
								bean.getServicio().trim(), bean.getNombre().trim(), bean.getDNI(), bean.getNro(),
								bean.getAsiento().trim(), bean.getOrigenD().trim(),
								bean.getFechaViaje().substring(0, 10), bean.getHoraViaje(), "N", bean.getFechaEmision(),
								bean.getPrecioAct(), bean.getRuc(), bean.getRazon(), bean.getHoraCompra(), "", null,
								null, null, null, bean.getUsuario(), bean.getDestinoRutaD());

						documento.add(table);

						// InputStream rutaplantillafooterVuelta =
						// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Base.jpg");

						InputStream rutaplantillafooterVuelta = null;

						if (bean.getTipoEmpresa() == 1) {

							rutaplantillafooterVuelta = request.getServletContext()
									.getResourceAsStream("_lib/img" + File.separator + "Embarque_Base.jpg");

						} else if (bean.getTipoEmpresa() == 2) {

							rutaplantillafooterVuelta = request.getServletContext()
									.getResourceAsStream("_lib/img" + File.separator + "Embarque_Base_Poseidon.jpg");

						}

						Image logofooterVuelta = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterVuelta));
						logofooterVuelta.scalePercent(23f);
						logofooterVuelta.setAbsolutePosition(5f, 100f);
						logofooterVuelta.setAlignment(Element.ALIGN_LEFT);

						documento.add(logofooterVuelta);
						contador = contador + 1;

						if (contador != listaIda.size()) {
							documento.newPage();
						}

					}
				}
			}

			documento.close();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));

		}

		return baos;

	}

	public ByteArrayOutputStream DescargaPDFTicket(String em, List<String> ids, String ope) {
		
		com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document(PageSize.A4, (float) 2.5, 0, 50, 0);
		
		//com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document(PageSize.LETTER);//, (float) 2.5, 0, 50, 0);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try { 
			if (ids.size() > 0) {
				PdfWriter.getInstance(documentoPDF, baos);
				documentoPDF.open();
			}
			int contador = 0;

			for (String id : ids) {
				/**/
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

				mapaVentas = facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.parseInt(id), ope);

				ventaImages = ventasfacturacionservice
						.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), ope);

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
				if (contador != ids.size()) {
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

	private String CodigoDeEmpresaCondicionado(VentaPaso1Form paso1Form, VentaPaso2Form paso2FormIda) {
		/*
		 * SERVICIOS DE TRANSPORTES WARI
		 * 
		 * 18 10 ABANCAY - LIMA 001 LIMA 84 4 LIMA - ABANCAY 002 ABANCAY 155 5
		 * LIMA - FERREÑAFE 005 FERREÑAFE 193 15 FERREÑAFE - LIMA 001 LIMA 226
		 * 20 LIMA - PUQUIO 012 PUQUIO 235 21 PUQUIO - LIMA 001 LIMA
		 */
		String respuesta = "002";
		// Primero se consulta por Tipo de Servicio
		if (paso2FormIda.getNroServicio().equals("02"))// CHASQUI BUS
		{
			respuesta = "004";
		} else if (paso2FormIda.getNroServicio().equals("09")) {// SERVICIO
																// INTIPLUS
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
				if (paso2FormIda.getNroDestino().equals(nroRuta)) {
					respuesta = "004";
					break;
				}
			}
		}
		return respuesta;
	}
	public ByteArrayOutputStream DescargaPDFTicketIdaVuelta(String em, List<String> allids, String ope) {
		// em = "002";
		// contador
		// String number = UUID.randomUUID().toString();

		com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document(PageSize.A4, (float) 2.5, 0, 50, 0);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			if (allids.size() > 0) {
				PdfWriter.getInstance(documentoPDF, baos);
				documentoPDF.open();
			}
			int contador = 0;

			for (String id : allids) {
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

				mapaVentas = facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.parseInt(id), ope);

				ventaImages = ventasfacturacionservice
						.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.parseInt(mapaVentas.get("Nro").toString()), ope);

				table = FuncionesFacturacionPDF.F_Facturacion_Electronica_Pasajes_Ticket(table, facturacionEmpresa,
						mapaVentas, ventaImages);

				documentoPDF.add(table);
				contador++;
				if (contador != allids.size()) {
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

	public void setEnvioCorreo(String envioCorreo) {
		EnvioCorreo = envioCorreo;
	}

	public String getEnvioCorreo() {
		return EnvioCorreo;
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
			conn.setRequestProperty("Content-Type","application/json");
			/*
										 * conn.setRequestProperty("apikey",
										 * ""); conn.setRequestProperty(
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
}
