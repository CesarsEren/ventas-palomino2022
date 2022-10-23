package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.BEGenRequest;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.BEnotificacionResponse;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.BEpaymentResponse;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.PagoEfectivo;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativosService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterface;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterfaceI;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesFacturacionPDF;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesPDF;
//import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmailInformes;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmail;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage("BoletajePalomino03")
public class VentasPagoEfectivo extends ActionSupport
		implements /* ServletResponseAware, */ SessionAware, ServletRequestAware {

	private static final Logger log = Logger.getLogger(VentasPagoEfectivo.class);
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private VariosService servicevarios = new VariosServiceI();
	private CuentaCorrienteService servicecuentacorriente = new CuentaCorrienteServiceI();
	private CorrelativosService servicecorrelativo = new CorrelativoServiceI();
	private Map<String, Object> session;
	// private HttpServletResponse response;
	private HttpServletRequest request;
	private String UrlPagoEfectivo;

	//static String URL_BASE = "http://172.16.10.3:8080/wspalomino/palomino/fe/generadocumento";
	

	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
	private V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI();

	private FacturacionService facturacionservice = new FacturacionServiceI();

	@SuppressWarnings({ "unchecked", "unused" })
	@Action(value = "/generarcip", results = {
			@Result(name = SUCCESS, location = "ventapagoefectivo/ventaboletoefectivo.jsp"),
			@Result(name = "ERRORCIP", type = "redirectAction", location = "salir"),
			@Result(name = ERROR, type = "redirectAction", location = "salir") })
	public String generaCIP() {
		try {
			log.info("GENERANDO DEL CIP");
			log.info("********************************************************************************************");

			double sumaTotal = 0.0;
			String numero_Orden = "";
			String FechaExpirar = "";
			PagoEfectivo.CodServ = request.getServletContext().getInitParameter("CodigoServicio");// "PAL";
			PagoEfectivo.PublicPath = request.getServletContext().getInitParameter("PublicPath");
			PagoEfectivo.PrivatePath = request.getServletContext().getInitParameter("PrivatePath");
			PagoEfectivo.UrlPECriptography = request.getServletContext().getInitParameter("UrlPECriptography");
			log.info("UrlPECriptography -->" + PagoEfectivo.UrlPECriptography);
			PagoEfectivo.UrlPEService = request.getServletContext().getInitParameter("UrlPEService");

			BEGenRequest requestPagoEfectivo = new BEGenRequest();

			UUID number = UUID.randomUUID();

			System.out.println(number.toString().substring(0,7));

			Calendar calendario = Calendar.getInstance();
			calendario.setTime(new Date());

			String TokenCIP = "";
			TokenCIP = (UUID.randomUUID().toString()).substring(0, 10) + calendario.getTimeInMillis();

			VentaPaso1Form paso1Form = (session.get("paso1Form") == null ? null
					: (VentaPaso1Form) session.get("paso1Form"));
			B_CuentaCorrienteBean cuentacorrienteVuelta = new B_CuentaCorrienteBean();
			VentaInterface ventadatos = new VentaInterfaceI();
			B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
			int operacion = -1;

			if (paso1Form != null) {

				if (paso1Form.isIdaVuelta() || paso1Form.isIdaVuelta() == false) {

					if (!paso1Form.isIdaVuelta()) {

						log.info("ENTRO TODO OK  " + paso1Form.isIdaVuelta());

						List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda") == null
								? new ArrayList<ListaPreVenta>()
								: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
						if (ListaPreventaIda.size() > 0) {

							for (ListaPreVenta ventaIda : ListaPreventaIda) {

								B_VentaBean venta = serviceventa.SelectVentaPagoEfectivo(ventaIda.getNro(),
										ventaIda.getSalida());

								if (venta != null) {

									log.info("NRO  OK -->" + ventaIda.getNro());

									venta = serviceventa.SelectVentaDatosPagoEfectivo(venta.getNro(), venta.getSalida(),TokenCIP);

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

						} else {

							log.info("TAMANIO DE LA PREVENTA (IDA)" + ListaPreventaIda.size());
							return ERROR;

						}
						
					} else {

						log.info("ENTRO TODO OK  " + paso1Form.isIdaVuelta());

						List<ListaPreVenta> ListaPreventaIdaVuelta = (session.get("ListaPreventaIdaVuelta") == null
								? new ArrayList<ListaPreVenta>()
								: (ArrayList<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));

						if (ListaPreventaIdaVuelta.size() > 0) {

							for (ListaPreVenta PreVentaIdaVuelta : ListaPreventaIdaVuelta) {

								B_VentaBean venta = serviceventa.SelectVentaPagoEfectivo(PreVentaIdaVuelta.getNro(),
										PreVentaIdaVuelta.getSalida());

								if (venta != null) {

									log.info("NRO  OK -->" + venta.getNro());

									venta = serviceventa.SelectVentaDatosPagoEfectivo(venta.getNro(), venta.getSalida(),TokenCIP);

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

						if (ListaPreventaIda.size() > 0) {
							for (ListaPreVenta PreVentaIda : ListaPreventaIda) {

								B_VentaBean venta = serviceventa.SelectVentaPagoEfectivo(PreVentaIda.getNro(),PreVentaIda.getSalida());

								if (venta != null) {
									log.info("NRO  OK -->" + venta.getNro());
									venta = serviceventa.SelectVentaDatosPagoEfectivo(venta.getNro(), venta.getSalida(),TokenCIP);

									if (venta != null) {
										sumaTotal += venta.getPrecioAct();
										numero_Orden = String.valueOf(venta.getNro());
										FechaExpirar = venta.getFechaExpiracion();

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

			SpringSecurityClient cliente = new SpringSecurityClient();
			UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (usuario instanceof SpringSecurityClient) {
				cliente = (SpringSecurityClient) usuario;

			} else {

				log.info("ERROR AL OBTENER DATOS DEL CLIENTE SPRINGSECURITY --> "
						+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				return ERROR;

			}
			log.info("NUMERO DE ORDEN -->" + numero_Orden);
			log.info("TOTAL  -->" + sumaTotal);
			log.info("FECHA A EXPIRAR -->" + FechaExpirar);

			V_Varios varios = new V_Varios();
			varios = servicevarios.Select_Varios();

			
			log.info("MONEDA (1:SOLES , 2:DOLARES) --> " + varios.getPagoEfectivoMoneda());
			log.info("EMAIL COMERCIO --> " + varios.getPagoEfectivoEmailComercio());
			// Datos a Trasnformar en XML por el API
			requestPagoEfectivo.setMoneda(varios.getPagoEfectivoMoneda());
			requestPagoEfectivo.setMonto(String.valueOf(sumaTotal) + "0"); // "150.00"
																			// //
																			// Total
			requestPagoEfectivo.setMedio_pago(varios.getPagoEfectivoMedioPago()); // "1,2";//
																					// MetodosPago
			requestPagoEfectivo.setCod_servicio(varios.getPagoEfectivoCodServ());// "PAL";//
																					// CodServicio
			requestPagoEfectivo.setNumero_orden(number.toString().substring(0, 8) + new Date().getTime()); // CodTransaccion
			requestPagoEfectivo.setConcepto_pago("Orden " + numero_Orden + " Palomino"); // ConceptoPago
			requestPagoEfectivo.setEmail_comercio(varios.getPagoEfectivoEmailComercio());// EmailComercio
			requestPagoEfectivo.setFecha_expirar(FechaExpirar);// "28/09/2016
																// 20:10:12";//
																// FechaAExpirar
			requestPagoEfectivo.setUsuario_nombre(cliente.getNombres());// "Gary";//
																		// ////
																		// UsuarioNombre
			requestPagoEfectivo.setUsuario_apellidos(cliente.getApellidos());// "Mora
																				// Nonato";//"
																				// //
																				// UsuarioApellidos
			requestPagoEfectivo.setUsuario_email(cliente.getUsername());// "garygmoran@gmail.com";////
																		// //
																		// UsuarioEmail

			BEpaymentResponse responsePagoEfectivo = PagoEfectivo.GenerarCIP(requestPagoEfectivo);
			log.info("XML -->" + responsePagoEfectivo.Xml);
			// log.info("TOKEN -->"+responsePagoEfectivo.Token);
			log.info("ESTADO -->" + responsePagoEfectivo.Estado);

			String xml = responsePagoEfectivo.Xml;

			switch (Integer.parseInt(responsePagoEfectivo.Estado)) {
			case 1: // Se generó la solicitud correctamente

				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = db.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
				String numeroCip = "";
				String token = "";
				NodeList nlSolpago = doc.getElementsByTagName("ConfirSolPago");
				if (nlSolpago.getLength() > 0) {
					/**
					 * numeroCip sera el id con el cual se relacionará la
					 * transacción en pagoEfectivo, se usará este para
					 * consultar, eliminar o actualizar dicho CIP*
					 */
					NodeList nlCIP = ((Element) nlSolpago.item(0)).getElementsByTagName("CIP");
					if (nlCIP.getLength() > 0) {
						NodeList nlNumeroCIP = ((Element) nlCIP.item(0)).getElementsByTagName("NumeroOrdenPago");
						numeroCip = nlNumeroCIP.item(0).getTextContent();
					}

					log.info("NUMERO DE CIP --> " + numeroCip);

					// Del mismo modo recuperar el campo Token
					NodeList nlToken = ((Element) nlSolpago.item(0)).getElementsByTagName("Token");
					token = nlToken.item(0).getTextContent();
					// guardarEnBD(numeroCip,...);

					List<B_VentaBean> ventaCIP = new ArrayList<B_VentaBean>();

					ventaCIP = serviceventa.SelectListVentaPagoEfectivo(TokenCIP);

					for (B_VentaBean venta : ventaCIP) {

						operacion = serviceventa.updateVentaPagoEfectivo(venta.getNro(), venta.getSalida(), numeroCip,
								B_VentaBean.ESTADO_RESERVADO, cliente.getUsername(), Integer.parseInt(numero_Orden));

						if (operacion == -1) {

							log.info("ERROR AL ACTUALIZAR LA VENTA DE PAGOEFECTIVO VALOR OPERACION --> " + operacion
									+ " NRO. VENTA --> " + venta.getNro());
							return ERROR;
						}

					}
				}

				// ACTUALIZAMOS EL TIEMPO DE LA VENTA DE PAGOEFECTIVO
				operacion = serviceventa.updateTiempoVentaPagoEfectivo(numeroCip.trim());

				if (operacion == -1) {
					return ERROR;
				}
				UrlPagoEfectivo = varios.getUrlPagoEfectivo().trim() + token;

				break;

			default:

				Limpiar_Session_Venta_Ida();
				Limpiar_Session_Venta_Vuelta();
				return "ERRORCIP";
			}

			Limpiar_Session_Venta_Ida();
			Limpiar_Session_Venta_Vuelta();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			log.info("ENTRO AL CATCH --> ERROR AL GENERAR EL CIP (REDIRECCIONANDO A LA PAGINA DE INICIO)");
			return "ERRORCIP";
		}

		return SUCCESS;

	}

	@Action(value = "/modalpagoefectivo", results = {
			@Result(name = SUCCESS, location = "ventapagoefectivo/modalpagoefectivo.jsp") })
	public String modalPagoEfectivo() {
		return SUCCESS;
	}

	@Action(value = "/pagoefectivo", results = {
	        @Result(name = SUCCESS, type = "json")})
	    public String pagoEfectivo() {

	        try {

	            BEnotificacionResponse notificacionresponse = new BEnotificacionResponse();
	            int operacion = -1;

	            log.info("********************************************************************************************");
	            log.info("INGRESANDO LA RESPUESTA DE PAGOEFECTIVO ...");

	            String tramaEncriptada = "";
	            if (request.getParameter("version").toString().compareTo("2") == 0) {

	                
	                //  el parametro data trae la información encriptada de la
	            	//	transacción en un XML como String *
	                 
	                tramaEncriptada = request.getParameter("data");

	                PagoEfectivo.PublicPath = request.getServletContext().getInitParameter("PublicPath");
	                PagoEfectivo.PrivatePath = request.getServletContext().getInitParameter("PrivatePath");
	                PagoEfectivo.UrlPECriptography = request.getServletContext().getInitParameter("UrlPECriptography");

	                // Desencriptamos la trama 
	                notificacionresponse = PagoEfectivo.DesenciptarData(tramaEncriptada);

	                log.info("TRAMA XML DESENCRYPTADA..." + notificacionresponse.Xml);

	            }

	            log.info("INGRESANDO A LEER EL XML ...");

	            //Nos preparamos para leer el XML ( 
	            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	            Document doc = db.parse(new ByteArrayInputStream(notificacionresponse.Xml.getBytes("UTF-8")));

	            String NroCip = "";
	            int Estado = 0;

	            NodeList nlSolpago = doc.getElementsByTagName("ConfirSolPago");
	            if (nlSolpago.getLength() > 0) {
	                
	                //  Obtenemos el idResSolpago de la trama XML enviada, este id es
	                //	el identificador de la transacción a actualizar*
	                 
	                NodeList nliDResSolPago = ((Element) nlSolpago.item(0)).getElementsByTagName("NumeroOrdenPago");
	                NroCip = nliDResSolPago.item(0).getTextContent();

	                
	                //  Obtenemos el estado de la transacción*
	                 
	                NodeList nlEstado = ((Element) nlSolpago.item(0)).getElementsByTagName("Estado");
	                String valueEstado = nlEstado.item(0).getTextContent();
	                Estado = Integer.parseInt(valueEstado);

	                NodeList nlUsuarioEmail = ((Element) nlSolpago.item(0)).getElementsByTagName("UsuarioEmail");
	                String usuarioEmail = nlUsuarioEmail.item(0).getTextContent();

	                NodeList nlFechaExtornado = ((Element) nlSolpago.item(0)).getElementsByTagName("FechaAnulado");
	                String fechaextornado = nlFechaExtornado.item(0).getTextContent();

	                //Dependiendo del estado se procedera a actualizar, los estados pueden ser: 
	                switch (Estado) {
	                    case 592: //Actualizar la transacción a Generada  
	                        //(esto pasa cuando extorna, de pagado 593 pasa a generado 592) 
	                        //ExtornarTransaccion(cip);

	                        try {

	                            log.info("********INGRESANDO AL PROCESO DE EXTORNO DEL CIP******** -->" + NroCip);

	                            List<B_VentaBean> ventaRechazada = new ArrayList<B_VentaBean>();

	                            ventaRechazada = serviceventa.SelectListVentaPagoEfectivo(NroCip);

	                            if (ventaRechazada != null) {

	                                for (B_VentaBean bean : ventaRechazada) {

	                                    operacion = serviceventa.updateVentaPagoEfectivoConfirmacion(bean.getNro(), bean.getSalida(), B_VentaBean.ESTADO_RESERVADO, 2, Estado);

	                                    operacion = serviceventa.deleteVentaExtornoPagoEfectivo(bean.getNro(), bean.getSalida(), NroCip, fechaextornado, 2);

	                                    log.info("VALOR DE OPERACION AL ELIMIAR LA VENTA --> " + operacion);

	                                    if (operacion == -1) {

	                                        log.info("ERROR AL ELIMINAR EN LA CUENTA CORRIENTE (EXTORNO)!! .. CIP--> " + NroCip);
	                                    } else {
	                                        log.info("PROCESO DE EXTORNO TERMINADA CON EXITO..!!  CIP -->" + NroCip);
	                                    }

	                                }
	                            } else {
	                                log.info("LA VENTA NO EXISTE EN PROCESO DE EXTORNO ..!!  CIP -->" + NroCip);

	                            }

	                        } catch (Exception e) {
	                            log.info(Utils.printStackTraceToString(e));

	                        }

	                        break;

	                    case 593: //Se realizó el pago de la transacción 
	                        //PagarTransaccion(cip)

	                        log.info("********INGRESANDO AL PROCESO DE TRANSACCION DEL CIP******** --> " + NroCip);

	                        List<B_VentaBean> ventaPagada = new ArrayList<B_VentaBean>();
	                        VentaInterface ventadatos = new VentaInterfaceI();

	                        try {

	                            ventaPagada = serviceventa.SelectListVentaPagoEfectivo(NroCip);

	                            log.info("RESULTADO DE LA CONSULTA DE LA VENTA CON EL CIP : " + NroCip + " B_VENTA : " + ventaPagada + "");

	                            if (ventaPagada.size() > 0) {

	                                for (B_VentaBean bean : ventaPagada) {

	                                    B_Correlativos correlativos = new B_Correlativos();

	                                    correlativos = servicecorrelativo.generaCorrelativo();

	                                    if (correlativos != null) {

	                                        B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();

	                                        operacion = serviceventa.updateVentaPagoEfectivoConfirmacion(bean.getNro(), bean.getSalida(), B_VentaBean.ESTADO_CONFIRMADO, 2, Estado);

	                                        if (operacion != -1) {

	                                            B_CuentaCorrienteBean beanverifica = new B_CuentaCorrienteBean();

	                                            beanverifica = servicecuentacorriente.ListaVentaPagoEfectivoCuentaCorriente(bean.getNro());

	                                            if (beanverifica == null) {
	                                                cuentacorriente = ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
	                                                cuentacorriente = ventadatos.DatosDinamicosCuentaCorrientePagoEfectivo(bean, cuentacorriente, correlativos);
	                                                operacion = servicecuentacorriente.insertCuentaCorriente(cuentacorriente);
	                                            } else {

	                                                log.info("VENTA YA EXISTE EN LA CUENTACORRIENTE..!!  CIP --> " + NroCip);
	                                            }

	                                            if (operacion != -1) {
	                                                log.info("PROCESO DE TRANSACCION TERMINADA CON EXITO..!!  CIP --> " + NroCip);
	                                            } else {
	                                                log.info("ERROR AL ELIMINAR EN LA CUENTA CORRIENTE (TRANSACCION)!! .. CIP--> " + NroCip);
	                                            }
	                                        } else {
	                                            log.info("ERROR AL ACTUALIZAR EN LA VENTA (TRANSACCION)!! .. CIP--> " + NroCip);
	                                        }

	                                    } else {
	                                        log.info("ERROR AL GENERAR CORRELATIVOS (TRANSACCION)!! .. CIP--> " + NroCip);
	                                    }

	                                }

	                                List<B_VentaBean> ventaPagoEfectivo = new ArrayList<B_VentaBean>();
	                                ventaPagoEfectivo = serviceventa.getVentaImprimirPagoEfectivo(NroCip);
	                                
	                    	        for(B_VentaBean item : ventaPagoEfectivo ){
	                    	        	String empresaD  = CodigoDeEmpresaCondicionado(item.getNroDestino(), ""+item.getNroServicio());
	                    	        	item.setEmpresa(empresaD);
	                    	            //GENERAR BOLETO
	                    		        facturacionservice.USP_GENERARBOLETO(item.getNro() + "", "4127",item.getRuc() == null ? 16 : 17,empresaD);		          
	                    		        //GENERAR CODIGO QR
	                    			    consumirservicio(""+item.getNro(), "B");	        	
	                    	        }	                                
	                    	        
	                                log.info("ENTRANDO PARA IMPRIMIR EL VOUCHER  --> " + NroCip);
	                                GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(usuarioEmail.trim(),ventaPagoEfectivo.get(0).getEticketVisa()),DescargaPDFTicket(ventaPagoEfectivo));
	                                log.info("VOUCHER ENVIADO POR CORREO --> " + NroCip);

	                            } else {
	                                log.info("LA VENTA NO EXISTE ERROR PROCESO DE TRANSACCION  CIP --> !! " + NroCip);
	                            }
	                        } catch (Exception e) {
	                            log.info(Utils.printStackTraceToString(e));
	                        }
	                        break;
	                    case 595: //Expiró la transacción despues de generada como CIP 
	                        //ExpirarTransaccion(cip) 
	                        log.info("********INGRESANDO AL PROCESO DE EXPIRACION DEL CIP******** -->" + NroCip);
	                        try {
	                            List<B_VentaBean> ventaExpirada = new ArrayList<B_VentaBean>();
	                            ventaExpirada = serviceventa.SelectListVentaPagoEfectivo(NroCip);
	                            if (ventaExpirada != null) {
	                                for (B_VentaBean bean : ventaExpirada) {
	                                    operacion = serviceventa.updateVentaPagoEfectivoConfirmacion(bean.getNro(), bean.getSalida(), B_VentaBean.ESTADO_RESERVADO, 2, Estado);
	                                    operacion = serviceventa.deleteVenta(bean.getNro(), bean.getSalida());
	                                    log.info("VALOR DE OPERACION PROCESO DE EXPIRACION -->" + operacion);
	                                    log.info("PROCESO DE EXPIRACION TERMINADA CON EXITO..!!  CIP --> " + NroCip);

	                                }
	                            } else {
	                                log.info("LA VENTA NO EXISTE EN PROCESO DE EXPIRACION ..!!  CIP -->" + NroCip);
	                            }
	                        } catch (Exception e) {
	                            log.info(Utils.printStackTraceToString(e));
	                        }
	                        break;
	                }
	            }

	        } catch (Exception e) {
	            log.info(Utils.printStackTraceToString(e));
	        }

	        return SUCCESS;

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
		} else if (servicioCodigo.equals("16")) {// SERVICIO // INKA PLUS                     
			respuesta = "002";
		} else if (servicioCodigo.equals("18")) {// SERVICIO // KILLA PLUS                                         
			respuesta = "002";
		} else {// SERIVICIO INKA CLASS POR DEFECTO
			List<String> rutaswari = new ArrayList<>();
			rutaswari.add("4");// LIMA - ABANCAY
			rutaswari.add("10");// ABANCAY - LIMA
			rutaswari.add("2");// LIMA - CUSCO
			rutaswari.add("6");// CUSCO - LIMA
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
	
	private ByteArrayOutputStream obtieneBAOS(List<B_VentaBean> venta) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			com.itextpdf.text.Document documento = new com.itextpdf.text.Document();

			if (venta.size() > 0) {
				PdfWriter.getInstance(documento, baos);
				documento.open();

			}

			int contador = 0;

			for (B_VentaBean bean : venta) {

				// InputStream rutaIda =
				// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");

				InputStream rutaIda = null;

				if(bean.getDestinoD().trim().equals("CUSCO-LIMA") || bean.getDestinoD().trim().equals("LIMA-CUSCO"))
				{
					rutaIda = request.getServletContext()
							.getResourceAsStream("_lib/img" + File.separator + "Embarque_Wari.jpg"); 
					log.info("entroó wari");
				}else if (bean.getTipoEmpresa() == 1) {

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
				logoIda.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);

				documento.add(logoIda);

				PdfPTable table = new PdfPTable(35);

				table = FuncionesPDF.F_Reporte_Ventas(table, bean.getDestinoD().trim(), bean.getOrigen().trim(),
						bean.getServicio().trim(), bean.getNombre().trim(), bean.getDNI(), bean.getNro(),
						bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje().substring(0, 10),
						bean.getHoraViaje(), "N", bean.getFechaEmision(), bean.getPrecioAct(), bean.getRuc(),
						bean.getRazon(), bean.getHoraCompra(), "Tarjeta", "Pago Efectivo", bean.getTarjetaHabiente(),
						"", bean.getEticketVisa(), null, bean.getDestinoRutaD());

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
				logofooterIda.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);

				documento.add(logofooterIda);
				contador = contador + 1;

				if (contador != venta.size()) {
					documento.newPage();
				}
			}
			documento.close();
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

	public void setUrlPagoEfectivo(String urlPagoEfectivo) {
		UrlPagoEfectivo = urlPagoEfectivo;
	}

	public String getUrlPagoEfectivo() {
		return UrlPagoEfectivo;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public void consumirservicio(String nro, String tipo) {
		try {
			URL url = new URL(Utils.URL_BASE  + "?nro=" + nro + "&tipoOperacion=" + tipo);
			System.out.println(Utils.URL_BASE );
			System.out.println(Utils.URL_BASE  + "?nro=" + nro + "&tipoOperacion=" + tipo);
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

	public ByteArrayOutputStream DescargaPDFTicket(String em, List<String> ids, String ope) {

		Rectangle tamaño = new Rectangle(250,800);
		com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document();
		documentoPDF.setPageSize(tamaño);
		documentoPDF.setMargins(0,0.5f,0,0.5f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try { 
			if (ids.size() > 0) {
				PdfWriter.getInstance(documentoPDF, baos);
				documentoPDF.open();
			}
			int contador = 0;

			for (String id : ids) {
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

	public static void main(String[] args) {

		VentaBoletaService serviceventa = new VentaBoletaServiceI();

		List<B_VentaBean> ventaPagada = new ArrayList<B_VentaBean>();

		try {

			ventaPagada = serviceventa.SelectListVentaPagoEfectivo("00000009961901");

			System.out.println("RESULTADO " + (ventaPagada.size() > 0 ? "OK" : "NO"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
