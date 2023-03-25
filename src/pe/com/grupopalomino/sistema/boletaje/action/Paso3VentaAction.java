package pe.com.grupopalomino.sistema.boletaje.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.bean.B_PrecioProgramacion;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Clientes_RutaPrecioBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CroquisBusBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Promo_RetornoCBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ServiciosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.formviews.ComboIdentidad;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso2Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso3Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso4Form;
import pe.com.grupopalomino.sistema.boletaje.service.B_PrecioProgramacionService;
import pe.com.grupopalomino.sistema.boletaje.service.B_PrecioProgramacionServiceI;
//import pe.com.grupopalomino.sistema.boletaje.service.ClientesRutaPrecioService;
//import pe.com.grupopalomino.sistema.boletaje.service.ClientesRutaPrecioServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativosService;
import pe.com.grupopalomino.sistema.boletaje.service.CroquisService;
import pe.com.grupopalomino.sistema.boletaje.service.CroquisServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaService;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Promo_RetornoCService;
import pe.com.grupopalomino.sistema.boletaje.service.Promo_RetornoCServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.RutasService;
import pe.com.grupopalomino.sistema.boletaje.service.RutasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ServiciosService;
import pe.com.grupopalomino.sistema.boletaje.service.ServiciosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterface;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterfaceI;
import pe.com.grupopalomino.sistema.boletaje.util.ErrorValidacion;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesGeneralesUtils;
import pe.com.grupopalomino.sistema.boletaje.util.Regex;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.util.ValidaDatosPasajero;

import pe.com.grupopalomino.sistema.boletaje.dao.CuponIDao;
import pe.com.grupopalomino.sistema.boletaje.dao.B_CuponDao;

import pe.com.grupopalomino.sistema.boletaje.bean.Cupon;;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
// @PreAuthorize("hasAnyRole('1','3','SMS')")
public class Paso3VentaAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private VentaPaso3Form paso3Form = new VentaPaso3Form();

	/************************* LISTAS **************************************/
	private List<VentaPaso3Form> listaPasajeros = new ArrayList<VentaPaso3Form>();
	private List<VentaPaso4Form> listaPasajerosVuelta;
	private List<V_CroquisBusBean> listaCroquisBusVuelta;
	private List<V_RutasBean> listaComboDestinoBajadaVuelta = new ArrayList<V_RutasBean>();
	private List<ComboIdentidad> listaComboIdentidadVuelta = new ArrayList<ComboIdentidad>();
	private List<B_ProgramacionSalidaBean> listaComoEmbarqueVuelta = new ArrayList<B_ProgramacionSalidaBean>();
	private int minValorVuelta;
	private String numeroDocumento = "";
	private String cuppon = "";
	private List<String> mensajeServer = new ArrayList<String>();
	private boolean errorserver = false;
	private List<ListaPreVenta> ListaPreventaIda = new ArrayList<ListaPreVenta>();

	/****************
	 * LISTAS DE SESSIONES AUXILIARES PARA IR DEL PASO3 AL PASO4
	 ****/
	private List<V_RutasBean> listaComboDestinoBajada = new ArrayList<V_RutasBean>();
	private List<ComboIdentidad> listaComboIdentidad = new ArrayList<ComboIdentidad>();
	private List<B_ProgramacionSalidaBean> listaComoEmbarque = new ArrayList<B_ProgramacionSalidaBean>();
	private List<V_CroquisBusBean> listaCroquisBusIda;
	/**************** LISTAS DE SESSIONES PARA IR DEL PASO3 AL PASO4 ****/
	private Cupon Cuponbean = new Cupon();

	/************************* SERVICES *************************************/
	private ServiciosService serviceservicio = new ServiciosServiceI();
	private CroquisService serviceCroquis = new CroquisServiceI();
	private ProgramacionSalidaService servicesalidas = new ProgramacionSalidaServiceI();
	private CuentaCorrienteService servicecuentacorriente = new CuentaCorrienteServiceI();
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private CorrelativosService servicecorrelativo = new CorrelativoServiceI();
	// private ClientesRutaPrecioService serviceclienteruta = new
	// ClientesRutaPrecioServiceI();
	private VariosService servicevarios = new VariosServiceI();
	private UsuariosWebService serviceusuario = new UsuariosWebServiceI();
	private Promo_RetornoCService servicepromociones = new Promo_RetornoCServiceI();
	private B_CuponDao servicecupon = new CuponIDao();
	private static final Log log = LogFactory.getLog(Paso3VentaAction.class);
	private RutasService servicerutasXcupon = new RutasServiceI();

	@Action(value = "comprobarcuppon", results = { @Result(name = SUCCESS, type = "json") })
	public String comprobarcuppon() {
		try {

			VentaPaso1Form paso1FormIda = (VentaPaso1Form) session.get("paso1Form");
			VentaPaso2Form paso2FormIda = (VentaPaso2Form) session.get("paso2FormIDA");
			String paracupon = paso1FormIda.isIdaVuelta() ? "RETORNO" : "IDA";
			String fechaida = paso1FormIda.getFechaIda();// .replace("/", "-");
			String[] fechaarray = fechaida.split("/");
			fechaida = fechaarray[2] + "-" + fechaarray[1] + "-" + fechaarray[0];

			// String fechavuelta = paso1FormIda.getFechaVuelta();

			Map<String, Object> parameteres = new HashMap<>();
			parameteres.put("codigocupon", this.cuppon);
			parameteres.put("paracupon", paracupon);
			parameteres.put("fechaida", fechaida);
			parameteres.put("fecharetorno", " ");

			Cupon temp = servicecupon.validarcupon(parameteres);
			if (temp != null) {
				// Verificamos si el Servicio esta registrado
				boolean ServicioOK = false;
				List<V_ServiciosBean> serviciosXcupon = serviceservicio.ListarDetalledeServiciosXCupon(cuppon);
				for (int i = 0; i < serviciosXcupon.size(); i++) {
					if (paso2FormIda.getNroServicio().equals(serviciosXcupon.get(i).getCodigo())) {
						ServicioOK = true;
					}
				}

				boolean RutaOK = false;
				List<V_RutasBean> rutasXcupon = servicerutasXcupon.ListSQL_RutasXCupon(cuppon);
				// verificamos si la ruta esta registrada
				for (int i = 0; i < rutasXcupon.size(); i++) {
					if (paso2FormIda.getNroDestino().equals("" + rutasXcupon.get(i).getNro())) {
						RutaOK = true;
					}
				}
				if (RutaOK && ServicioOK) {
					errorserver = false;
					Cuponbean = temp;
					session.put("Cuponactivo", Cuponbean);
					return SUCCESS;
				}
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		errorserver = true;
		mensajeServer.add("Cupon no Existe");
		return SUCCESS;
	}

	@Action(value = "aplicarcupon", results = { @Result(name = SUCCESS, type = "json") })
	public String AplicarCupon() {
		listaPasajeros = obtieneBoletosSesion();
		// serviceventa.SQL_UpdatePrecioXCupon(nro, Salida, precio);
		Cuponbean = (Cupon) session.get("Cuponactivo");
		// Cuponbean.setDescuento(1-Cuponbean.getDescuento());
		System.out.println("Cupon : " + Cuponbean.getDetalle());

		actualizarprecio();
		return SUCCESS;
	}

	double total = 0;
	double totalcupon = 0;

	public double getTotal() {
		return total;
	}

	public double getTotalcupon() {
		return totalcupon;
	}

	@SuppressWarnings("unchecked")
	public String actualizarprecio() {
		// System.out.println(Cuponbean.getDescuento());
		// session.put("listaPasajeros", listaPasajeros);

		String mensajeAplicarCupon = "";
		double Precioboleto;
		try {

			List<ListaPreVenta> ListaPreventaIda = (List<ListaPreVenta>) session.get("ListaPreventaIda");
			int operacion = -1;

			for (ListaPreVenta preventa : ListaPreventaIda) {
				total += preventa.getPrecioAct();
				Precioboleto = preventa.getPrecioAct() * (1 - Cuponbean.getDescuento());
				totalcupon += Math.round(Precioboleto);

				operacion = serviceventa.SQL_UpdatePrecioXCupon("" + preventa.getNro(), "" + preventa.getSalida(),
						Math.round(Precioboleto), Cuponbean.getDetalle());
				if (operacion == -1) {
					mensajeAplicarCupon = "Ocurrio un Problema al aplicar el cupon el pasajero.)";
					return mensajeAplicarCupon;
				}
			}

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			mensajeAplicarCupon = e.getMessage();
		}
		return mensajeAplicarCupon;
	}

	public String actualizarPasajero() {

		String mensajeTransaccion = "";
		listaPasajeros = obtieneBoletosSesion();

		ValidaDatosPasajero validador = new ValidaDatosPasajero(paso3Form);

		ErrorValidacion errorValidacion = validador.esPasajeroValido();

		if (errorValidacion.getError()) {
			mensajeServer = errorValidacion.getMensajeError();
			errorserver = true;

			return SUCCESS;
		}

		for (VentaPaso3Form formIda : listaPasajeros) {
			if (formIda.getId().trim().equals(paso3Form.getId().trim())) {
				formIda.setValuesFromView(paso3Form);
				mensajeTransaccion = ActualizaPreVenta(paso3Form);
				if (!(mensajeTransaccion.trim().equals(""))) {
					mensajeServer.add(mensajeTransaccion);
					errorserver = true;
					return SUCCESS;
				}
				break;
			}
		}

		session.put("listaPasajeros", listaPasajeros);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "agregarpasajero", results = { @Result(name = SUCCESS, type = "json") })
	public String agregarPasajero() {
		
		List<B_PrecioProgramacion> lsprecioprogramacionIDA = (List<B_PrecioProgramacion>) session.get("lsprecioprogramacionIDA");
		
		// System.out.println("Ingresado ");
		try {
			int minValorIda = (int) session.get("minValorIda");
			double PrecioActual = 0.0;
			VentaPaso2Form paso2FormIda = (VentaPaso2Form) session.get("paso2FormIDA");
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

			String mensajeTransaccion = "";
			VentaInterface ventadatos = new VentaInterfaceI();

			List<V_CroquisBusBean> listaCroquisBusIda = (List<V_CroquisBusBean>) session.get("listaCroquisBusIda");
			listaPasajeros = obtieneBoletosSesion();

			VentaPaso1Form paso1 = (VentaPaso1Form) session.get("paso1Form");

			if (paso1 != null) {
				if (Integer.parseInt(paso1.getCantidadMaximaPasajeros()) == listaPasajeros.size()) {
					errorserver = true;
					mensajeServer.add("No puede agregar mas pasajeros. La cantidad maxima que selecciono es "
							+ paso1.getCantidadMaximaPasajeros());

					return SUCCESS;
				}
			}

			ValidaDatosPasajero validador = new ValidaDatosPasajero(paso3Form);
			ErrorValidacion error = validador.esPasajeroValido();

			if (error.getError()) {

				for (String mensaje : error.getMensajeError()) {
					errorserver = true;
					mensajeServer.add(mensaje);
				}

				return SUCCESS;
			}

			for (VentaPaso3Form formIda : listaPasajeros) {
				if (formIda.getNumeroDocumentoIdentidad().trim()
						.equals(paso3Form.getNumeroDocumentoIdentidad().trim())) {
					errorserver = true;
					mensajeServer.add("El pasajero ya se encuentra agregado");
					return SUCCESS;
				} else if (formIda.getNumeroAsiento().trim().equals(paso3Form.getNumeroAsiento().trim())) {
					errorserver = true;
					mensajeServer.add("El asiento ya ha sido seleccionado.");
					return SUCCESS;
				}
			}

			if (!paso3Form.getNumeroAsiento().trim().matches(Regex.ASIENTOS_VALIDOS)) {
				errorserver = true;
				mensajeServer.add("Seleccione un asiento valido.");
				return SUCCESS;
			}

			if (usuario != null) {

				if (usuario.getRealizaVenta().trim().equals("N")) {
					errorserver = true;
					mensajeServer.add(
							"Estimado usuario usted no tiene permisos para realizar Ventas,consultar con el Administrador de Sistemas.");
					return SUCCESS;
				}

				V_Varios PrecioMaximoPermitido = servicevarios.Get_Precio_Maximo_Asiento();

				if (PrecioMaximoPermitido != null) {
					paso3Form.setPrecioMaximo(PrecioMaximoPermitido.getMaximoBoletoPrecio());
					if (Double.parseDouble(paso3Form.getPrecio()) > PrecioMaximoPermitido.getMaximoBoletoPrecio()) {
						errorserver = true;
						mensajeServer.add(
								"El precio maximo del Boleto es de " + PrecioMaximoPermitido.getMaximoBoletoPrecio());
						return SUCCESS;
					}
				}
				PrecioActual = ventadatos.VerificaPrecioActual(listaCroquisBusIda, paso2FormIda, paso3Form, minValorIda,
						usuario);

				if (PrecioActual > 0) {
					boolean flag = true;

					

					//log.info("lsprecioprogramacionIDA : "+lsprecioprogramacionIDA.toString());

					if (lsprecioprogramacionIDA != null) {
						if (!lsprecioprogramacionIDA.isEmpty()) {
							for (B_PrecioProgramacion b_precioproomocional : lsprecioprogramacionIDA)
								if (paso3Form.getNumeroAsiento().trim()
										.equals(b_precioproomocional.getAsiento().toString().trim())) {
									if (Double.parseDouble(paso3Form.getPrecio()) >= b_precioproomocional.getPrecio()) {
										log.info("Asiento con precio promoción vendido asiento:"
												+ b_precioproomocional.getAsiento() + " Nro programacion :"
												+ paso2FormIda.getNroProgramacion());
										paso3Form.setPrecio(b_precioproomocional.getPrecio().toString());
										flag = false;
									} else {
										errorserver = true;
										mensajeServer.add("El Precio Ingresado No debe ser menor al Precio Actual :"
												+ PrecioActual);
										return SUCCESS;
									}
								}
						}

					}
					if (flag) {

						errorserver = true;
						mensajeServer.add("El Precio Ingresado No debe ser menor al Precio Actual :" + PrecioActual);
						return SUCCESS;
					}
				}

				SpringSecurityUser ventausuario = serviceusuario.limiteCreditoUsuario(usuario.getUsername());

				if (ventausuario != null) {
					double monto = 0.0;
					double montoavender = 0.0;

					List<VentaPaso3Form> listaPasajeroMonto = new ArrayList<VentaPaso3Form>();

					listaPasajeroMonto = (session.get("listaPasajeros") == null ? new ArrayList<VentaPaso3Form>()
							: (List<VentaPaso3Form>) session.get("listaPasajeros"));

					for (VentaPaso3Form listaMonto : listaPasajeroMonto) {
						montoavender = montoavender + Double.parseDouble(listaMonto.getPrecio());
					}

					montoavender = montoavender + Double.parseDouble(paso3Form.getPrecio());

					// TODO: AQUI ME QUEDE PARA HACER EL CALCULO DEL MONTO DE
					// USUARIO
					monto = servicecuentacorriente.VentasRealizadasCuentaCorriente(ventausuario.getRuc().trim(),
							ventausuario.getUsername());

					if ((montoavender + monto) > ventausuario.getLimiteCredito()) {
						errorserver = true;
						mensajeServer.add(
								"Usted No puede agregar mas pasajeros, por exceder su Limite de Crédito, por favor Consulte con nuestras Agencias.");
						return SUCCESS;

					}
					// paso3Form.setMontoVentaActual(ventausuario.getMontoVentaActual());

					/*
					 * if((ventausuario.getMontoVentaActual() +
					 * Double.parseDouble(paso3Form.getPrecio())) >
					 * ventausuario.getLimiteCredito()){ errorserver = true; mensajeServer.add(
					 * "Usted No puede realizar la venta por exceder su Limite de Crédito, por favor Consulte con nuestras Agencias."
					 * ); return SUCCESS; }
					 */
				}
			}

			VentaPaso3Form boleto = new VentaPaso3Form();
			boleto.setIdFromView(paso3Form);
			boleto.setValuesFromView(paso3Form);

			listaPasajeros.add(boleto);
			session.put("listaPasajeros", listaPasajeros);

			// SE REALIZA LA PREVENTA
			mensajeTransaccion = GeneraPreVenta(boleto, lsprecioprogramacionIDA);
			if (!(mensajeTransaccion.trim().equals(""))) {
				errorserver = true;
				mensajeServer.add(mensajeTransaccion);
				return SUCCESS;
			}

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String GeneraPreVenta(VentaPaso3Form paso3Form, List<B_PrecioProgramacion> b_PrecioProgramacions) {
		//log.info("b_PrecioProgramacions : "+b_PrecioProgramacions.toString());
		String mensajeResultado = "";
		boolean resultado = true;
		try {

			int operacion = -1;

			VentaInterface ventadatos = new VentaInterfaceI();
			B_VentaBean Preventa = new B_VentaBean();
			List<V_CroquisBusBean> listaCroquisBusIda = (List<V_CroquisBusBean>) session.get("listaCroquisBusIda");
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

			VentaPaso2Form paso2FormIda = (VentaPaso2Form) session.get("paso2FormIDA");
			int minValorIda = (int) session.get("minValorIda");
			String FechaEmision = FuncionesGeneralesUtils.F_ObtenerFechaServidor();

			if (session.get("ListaPreventaIda") != null) {
				ListaPreventaIda = (List<ListaPreVenta>) session.get("ListaPreventaIda");
			}

			B_Correlativos correlativo = new B_Correlativos();

			resultado = ventadatos.GeneraDatosClienteVenta(paso3Form);

			if (resultado) {

				resultado = ventadatos.GeneraDatosPasajeroventa(paso3Form);

				if (resultado) {

					resultado = ventadatos.VerificaDisponibilidadAsientoIda(paso2FormIda, paso3Form);

					if (!(resultado)) {

						mensajeResultado = "El Asiento " + paso3Form.getNumeroAsiento()
								+ " ya se encuentra Reservado, por favor seleccione otro";
						RemovePasajeroTransaccionError(paso3Form.getId().trim());
						return mensajeResultado;
					}

					if (!(FechaEmision.trim().equals(""))) {

						correlativo = servicecorrelativo.generaCorrelativo();

						if (correlativo != null) {

							Preventa = ventadatos.DatosEstaticosVenta(Preventa, paso3Form, usuario);

							Preventa = ventadatos.DatosDinamicosVenta(Preventa, correlativo, FechaEmision, paso2FormIda,
									paso3Form, listaCroquisBusIda, minValorIda, usuario, b_PrecioProgramacions);

							// VALIDANDO EL PRECIO INGRESADO DESDE LA INTERFAZ
							// DEL USUSARIO (VALIDO SOLO PARA LAS AGENCIAS)

							if (usuario != null) {

								String[] precio = paso3Form.getPrecio().split(Pattern.quote("."));
								paso3Form.setPrecio(precio[0] + ".00");

								Preventa.setPrecioAct(Double.parseDouble(paso3Form.getPrecio()));
								Preventa.setPrecio(Double.parseDouble(paso3Form.getPrecio()));

							}
							System.out.println("preventa: " + Preventa.toString());
							System.out.println("preventa: " + paso3Form.toString());
							operacion = serviceventa.insertVenta(Preventa);

							if (operacion != -1) {

								ListaPreVenta preventa = new ListaPreVenta();
								preventa.SetValuesPreVentaIda(paso3Form.getId(), Preventa.getNro(),
										Preventa.getSalida(), Preventa.getNombre(), Preventa.getPrecioAct(),
										correlativo.getCorrelativoCtaCte(), correlativo.getCorrelativoEnc(),
										correlativo.getCorrelativoEnc(), FechaEmision);
								preventa.setRuc(paso3Form.getRuc());
								preventa.setAsiento(Integer.parseInt(Preventa.getAsiento()));
								preventa.setDestinoD(Preventa.getDestinoD());
								preventa.setHora1(Preventa.getHoraViaje());
								preventa.setFechaViaje(Preventa.getFechaViaje());
								ListaPreventaIda.add(preventa);

								/*
								 * if(usuario !=null){ //ACTUALIZANDO EL MONTO ACTUAL DE LAS VENTAS DEL USUARIO
								 * operacion = serviceusuario.updateMontoVentaActual(usuario .getUsername(),
								 * paso3Form.getMontoVentaActual()+Preventa. getPrecioAct());
								 * 
								 * }
								 */

								if (operacion == -1) {
									mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 006')";
									RemovePasajeroTransaccionError(paso3Form.getId());
									return mensajeResultado;
								}

							} else {
								mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 005')";
								RemovePasajeroTransaccionError(paso3Form.getId());
								return mensajeResultado;
							}

						} else {
							mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 004')";
							RemovePasajeroTransaccionError(paso3Form.getId());
							return mensajeResultado;
						}

					} else {
						mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 003')";
						RemovePasajeroTransaccionError(paso3Form.getId());
						return mensajeResultado;
					}

				} else {
					mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 002')";
					RemovePasajeroTransaccionError(paso3Form.getId());
					return mensajeResultado;
				}

			} else {
				mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 001')";
				RemovePasajeroTransaccionError(paso3Form.getId());
				return mensajeResultado;
			}

			session.put("ListaPreventaIda", ListaPreventaIda);

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			mensajeResultado = e.getMessage();
		}
		return mensajeResultado;
	}

	@SuppressWarnings("unchecked")
	private void RemovePasajeroTransaccionError(String idPasajero) {
		List<VentaPaso3Form> listaPasajeros = (session.get("listaPasajeros") == null ? new ArrayList<VentaPaso3Form>()
				: (List<VentaPaso3Form>) session.get("listaPasajeros"));

		for (VentaPaso3Form pasajero : listaPasajeros) {
			if (pasajero.getId().trim().equals(idPasajero.trim())) {
				listaPasajeros.remove(pasajero);
				break;
			}
		}
		session.put("listaPasajeros", listaPasajeros);
	}

	@SuppressWarnings("unchecked")
	public String ActualizaPreVenta(VentaPaso3Form paso3Form) {
		String mensajeActualizar = "";
		try {

			int operacion = -1;
			List<ListaPreVenta> ListaPreventaIda = (List<ListaPreVenta>) session.get("ListaPreventaIda");

			for (ListaPreVenta preventa : ListaPreventaIda) {

				if (paso3Form.getId().trim().equals(preventa.getIdPasajero())) {

					String[] destino = paso3Form.getComboDestinoBajada().split(",");
					String[] embarque = paso3Form.getComboEmbarque().split("-");
					String[] documento = paso3Form.getComboIdentidad().split("-");

					operacion = serviceventa.updateVenta(preventa.getNro(), preventa.getSalida(), documento[0].trim(),
							documento[1], paso3Form.getNumeroDocumentoIdentidad().trim(),
							paso3Form.getNombrePasajero().trim(), paso3Form.getEdad().trim(), paso3Form.getTelefono(),
							paso3Form.getRuc(), paso3Form.getRazonSocial().trim(), embarque[0].trim(),
							embarque[1].trim(), destino[0], destino[1], embarque[2]);
					if (operacion == -1) {

						mensajeActualizar = "Ocurrio un Problema am Actualizar el pasajero.(Consulte con Administrador de Sistemas 'ERROR 001')";
						return mensajeActualizar;
					}
				}
			}

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			mensajeActualizar = e.getMessage();

		}
		return mensajeActualizar;
	}

	@Action(value = "obtenerpasajero", results = { @Result(name = SUCCESS, type = "json") })
	public String obtenerPasajero() {

		listaPasajeros = obtieneBoletosSesion();
		for (VentaPaso3Form formIda : listaPasajeros) {
			if (formIda.getNumeroDocumentoIdentidad().trim().equals(numeroDocumento)) {
				paso3Form = formIda;
			}
		}

		session.put("listaPasajeros", listaPasajeros);

		return SUCCESS;
	}

	@Action(value = "/quitarpasajero", results = { @Result(name = SUCCESS, type = "json") })
	public String quitarPasajero() {

		listaPasajeros = obtieneBoletosSesion();

		for (VentaPaso3Form formIda : listaPasajeros) {
			if (formIda.getNumeroDocumentoIdentidad().trim().equals(numeroDocumento)) {
				listaPasajeros.remove(formIda);
				EliminaPreVenta(formIda);
				break;
			}
		}

		session.put("listaPasajeros", listaPasajeros);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String EliminaPreVenta(VentaPaso3Form pasajero) {

		int operacion = -1;
		List<ListaPreVenta> ListaPreventaIda = (List<ListaPreVenta>) session.get("ListaPreventaIda");
		String mensajeResultado = "";
		// SpringSecurityUser usuario = null;

		/*
		 * if(SecurityContextHolder.getContext().getAuthentication().getName()!= null){
		 * if(!(SecurityContextHolder.getContext().getAuthentication(). getPrincipal()
		 * instanceof String)){
		 * if(SecurityContextHolder.getContext().getAuthentication(). getPrincipal()
		 * instanceof SpringSecurityUser){ usuario= (SpringSecurityUser)
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal() ; } } }
		 */

		try {

			for (ListaPreVenta preventa : ListaPreventaIda) {

				if (pasajero.getId().trim().equals(preventa.getIdPasajero())) {

					operacion = serviceventa.deleteVenta(preventa.getNro(), preventa.getSalida());

					if (operacion == -1) {
						mensajeResultado = "Ocurrio un Problema al Eliminar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 001')";
						return mensajeResultado;
					} else {

						/*
						 * if(usuario!= null){ SpringSecurityUser ventausuario =
						 * serviceusuario.limiteCreditoUsuario(usuario. getUsername()); operacion =
						 * serviceusuario.updateMontoVentaActual(usuario.
						 * getUsername(),(ventausuario.getMontoVentaActual()) -
						 * (preventa.getPrecioAct()));
						 * 
						 * if(operacion==-1){ mensajeResultado =
						 * "Ocurrio un Problema al Eliminar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 002')"
						 * ; return mensajeResultado; } }
						 */
						ListaPreventaIda.remove(preventa);
						break;
					}
				}
			}
			session.put("ListaPreventaIda", ListaPreventaIda);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			mensajeResultado = e.getMessage();
		}

		return mensajeResultado;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "paso4", results = { @Result(name = SUCCESS, location = "ventaida/ventaboletospaso4.jsp"),
			@Result(name = ERROR, location = "ventaida/ventaboletospaso3.jsp"),
			@Result(name = "REDIRECCIONPASO1", type = "redirectAction", location = "ventapaso1") })
	public String irAPaso4() {

		VentaPaso1Form paso1Form = (VentaPaso1Form) session.get("paso1Form");

		listaPasajeros = obtieneBoletosSesion();

		if (listaPasajeros == null || listaPasajeros.isEmpty()) {

			return "REDIRECCIONPASO1";
		}

		if (listaPasajeros.size() == 0) {

			VentaPaso2Form paso2FormIDA = (VentaPaso2Form) session.get("paso2FormIDA");

			addActionError("Debe Ingresar al menos 1 Pasajero.");
			listaComboIdentidad.addAll(Utils.getListaComboIdentidad());
			listaComboDestinoBajada.addAll(
					Utils.getListaComboDestinoBajada(paso1Form.getDestinoIda(), paso1Form.getDestinoDescripcion()));
			listaComoEmbarque.addAll(Utils.getListaComboEmbarque(paso2FormIDA.getNroProgramacion(),
					paso1Form.getOrigenIda().substring(0, 3)));

			/*
			 * listaComboDestinoBajada = (session.get("listaComboDestinoBajada")== null)?
			 * new ArrayList<V_RutasBean>() :
			 * (List<V_RutasBean>)session.get("listaComboDestinoBajada");
			 * listaComboIdentidad = (session.get("listaComboIdentidad")==null)? new
			 * ArrayList<ComboIdentidad>():
			 * (List<ComboIdentidad>)session.get("listaComboIdentidad"); listaComoEmbarque =
			 * (session.get("listaComoEmbarque")==null)? new
			 * ArrayList<B_ProgramacionSalidaBean>():
			 * (List<B_ProgramacionSalidaBean>)session.get("listaComoEmbarque") ;
			 */
			listaCroquisBusIda = (session.get("listaCroquisBusIda") == null) ? new ArrayList<V_CroquisBusBean>()
					: (List<V_CroquisBusBean>) session.get("listaCroquisBusIda");
			return ERROR;
		} else {
			getDetalleBusVuelta(paso1Form);
		}

		return SUCCESS;
	}
	
	private List<B_PrecioProgramacion> lsprecioprogramacionVUELTA = new ArrayList<>();
	private B_PrecioProgramacionService b_PrecioProgramacionService = new B_PrecioProgramacionServiceI();

	private void getDetalleBusVuelta(VentaPaso1Form paso1Form) {

		// int cantidadAsientos = 0;
		V_ServiciosBean servicioBean = new V_ServiciosBean();

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

		try {
			// cantidadAsientos =
			// serviceCroquis.cantidadAsientosPorBus(paso2FormVUELTA.getNroBus());
			listaCroquisBusVuelta = serviceCroquis.obtieneCroquisPorNumeroBus(
					paso2FormVUELTA.getNroBus().trim().equals("") ? "000" : paso2FormVUELTA.getNroBus().trim());

			if (listaCroquisBusVuelta.size() == 0) {
				servicioBean = serviceservicio.getServicioCodigo(paso2FormVUELTA.getNroServicio());
				if (servicioBean != null) {

					listaCroquisBusVuelta = serviceCroquis.obtieneCroquisPorNumeroBus(servicioBean.getBusPlantilla());
				}
			}
			if (paso1Form.isIdaVuelta()) {
				lsprecioprogramacionVUELTA = b_PrecioProgramacionService.SQL_ObtenerAsientosPrecioPromocionConNroProgramacion(paso2FormVUELTA.getNroProgramacion());
			}

			if (listaCroquisBusVuelta.size() > 0) {

				minValorVuelta = listaCroquisBusVuelta.get(0).getTTop();

				// VALIDO PARA LAS PROMOCIONES DE RETORNO
				V_Promo_RetornoCBean promocion = servicepromociones.SQL_SELECT_TIPO_PROMOCION_RETORNO();

				B_ProgramacionSalidaBean bean = servicesalidas.getSalidasPrecioPromocion(
						paso2FormVUELTA.getNroProgramacion(), paso1Form.getOrigenIda(),
						(promocion == null ? 0 : promocion.getWeb()));
				V_Clientes_RutaPrecioBean beanclienteRuta = null;

				if (bean != null) {

					if (usuario != null) {

						// beanclienteRuta =
						// serviceclienteruta.listaClientesRutasVerificacion(usuario.getRuc(),bean.getDestino(),
						// bean.getServicio());

					}
					for (int x = 0; x < listaCroquisBusVuelta.size(); x++) {
						if (minValorVuelta >= 1920) {

							if (beanclienteRuta != null) {
								listaCroquisBusVuelta.get(x).setPrecio(beanclienteRuta.getPrecio1());
							} else {

								listaCroquisBusVuelta.get(x).setPrecio(bean.getPrecio1());
							}

						} else {
							if (listaCroquisBusVuelta.get(x).getTTop() < 1920) {

								if (beanclienteRuta != null) {

									listaCroquisBusVuelta.get(x).setPrecio(beanclienteRuta.getPrecio1());
								} else {
									listaCroquisBusVuelta.get(x).setPrecio(bean.getPrecio1());
								}

							} else {

								if (beanclienteRuta != null) {
									listaCroquisBusVuelta.get(x).setPrecio(beanclienteRuta.getPrecio2());

								} else {
									listaCroquisBusVuelta.get(x).setPrecio(bean.getPrecio2());

								}
							}
						}
					}
					  
					if (paso1Form.isIdaVuelta()) {
						if (lsprecioprogramacionVUELTA != null) {
							if (!lsprecioprogramacionVUELTA.isEmpty()) {
								
								
								for (V_CroquisBusBean v_croquisbean : listaCroquisBusVuelta) {

									for (B_PrecioProgramacion b_PrecioProgramacion : lsprecioprogramacionVUELTA) {

										if (b_PrecioProgramacion.getAsiento().toString()
												.equals(v_croquisbean.getAsiento().trim())) {

											log.info("Precio Programación :" + b_PrecioProgramacion.getPrecio() + " "
													+ b_PrecioProgramacion.getAsiento());
											v_croquisbean.setPrecio(b_PrecioProgramacion.getPrecio());
											v_croquisbean.setPromocion(true);
										}
									}
								}
								session.put("lsprecioprogramacionVUELTA", lsprecioprogramacionVUELTA);
							}
						}
					}
				}
			}

			List<B_VentaBean> lstAsientosOcupados = serviceCroquis
					.listaAsientosOcupadosPorProgramacionSalida(paso2FormVUELTA.getNroProgramacion());

			for (V_CroquisBusBean cbb : listaCroquisBusVuelta) {
				if (cbb.getAsiento().trim().equals("T1") || cbb.getAsiento().trim().equals("T2")
						|| cbb.getAsiento().trim().equals("T3") || cbb.getAsiento().trim().equals("T4")
						|| cbb.getAsiento().trim().equals("T5") || cbb.getAsiento().trim().equals("T6")
						|| cbb.getAsiento().trim().equals("T7") || cbb.getAsiento().trim().equals("E")
						|| cbb.getAsiento().trim().equals("B")) {
					cbb.setVisible("false");
				}
			}

			for (B_VentaBean ventaAsiento : lstAsientosOcupados) {
				for (V_CroquisBusBean croquisAsiento : listaCroquisBusVuelta) {
					if (croquisAsiento.getAsiento().trim().equals(ventaAsiento.getAsiento().trim())) {
						croquisAsiento.setVisible("false");
						break;
					}
				}
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		// session.remove("ListaPreventaIda");
		session.remove("ListaPreventaIdaVuelta");
		listaComboDestinoBajadaVuelta
				.addAll(Utils.getListaComboDestinoBajada(paso1Form.getOrigenIda(), paso1Form.getOrigenDescripcion()));
		listaComoEmbarqueVuelta.addAll(Utils.getListaComboEmbarque(paso2FormVUELTA.getNroProgramacion(), ""));
		session.put("listaCroquisBusVuelta", listaCroquisBusVuelta);
		session.put("minValorVuelta", minValorVuelta);
		GeneraListaPasajeroAuxiliar(listaPasajeros);
		// System.out.println(cantidadAsientos);
	}

	public void GeneraListaPasajeroAuxiliar(List<VentaPaso3Form> Listapasajero) {

		if (Listapasajero.size() > 0) {

			List<VentaPaso4Form> ListaAuxiliarPasajeros = new ArrayList<VentaPaso4Form>();

			for (VentaPaso3Form pasajero : Listapasajero) {

				VentaPaso4Form newpasajero = new VentaPaso4Form();
				newpasajero.setId(pasajero.getId());
				newpasajero.setNumeroAsiento("");
				newpasajero.setPrecio("");
				newpasajero.setNombrePasajero(pasajero.getNombrePasajero());
				newpasajero.setEdad(pasajero.getEdad());
				newpasajero.setTelefono(pasajero.getTelefono());
				newpasajero.setRuc(pasajero.getRuc());
				newpasajero.setRazonSocial(pasajero.getRazonSocial());
				newpasajero.setNumeroDocumentoIdentidad(pasajero.getNumeroDocumentoIdentidad());
				newpasajero.setComboDestinoBajada("");
				newpasajero.setComboEmbarque("");
				newpasajero.setComboIdentidad(pasajero.getComboIdentidad());
				newpasajero.setAsientoasignado(pasajero.isAsientoasignado());
				ListaAuxiliarPasajeros.add(newpasajero);

			}

			session.put("ListaAuxiliar", ListaAuxiliarPasajeros);
		}

	}

	public void setListaComboIdentidadVuelta(List<ComboIdentidad> listaComboIdentidadVuelta) {
		this.listaComboIdentidadVuelta = listaComboIdentidadVuelta;
	}

	public List<ComboIdentidad> getListaComboIdentidadVuelta() {
		return listaComboIdentidadVuelta;
	}

	public void setListaComoEmbarqueVuelta(List<B_ProgramacionSalidaBean> listaComoEmbarqueVuelta) {
		this.listaComoEmbarqueVuelta = listaComoEmbarqueVuelta;
	}

	public List<V_RutasBean> getListaComboDestinoBajadaVuelta() {
		return listaComboDestinoBajadaVuelta;
	}

	public void setListaComboDestinoBajadaVuelta(List<V_RutasBean> listaComboDestinoBajadaVuelta) {
		this.listaComboDestinoBajadaVuelta = listaComboDestinoBajadaVuelta;
	}

	public List<B_ProgramacionSalidaBean> getListaComoEmbarqueVuelta() {
		return listaComoEmbarqueVuelta;
	}

	public void setListaCroquisBusVuelta(List<V_CroquisBusBean> listaCroquisBusVuelta) {
		this.listaCroquisBusVuelta = listaCroquisBusVuelta;
	}

	public List<V_CroquisBusBean> getListaCroquisBusVuelta() {
		return listaCroquisBusVuelta;
	}

	public void setMinValorVuelta(int minValorVuelta) {
		this.minValorVuelta = minValorVuelta;
	}

	public int getMinValorVuelta() {
		return minValorVuelta;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	@SuppressWarnings("unchecked")
	private List<VentaPaso3Form> obtieneBoletosSesion() {

		return session.get("listaPasajeros") != null ? (ArrayList<VentaPaso3Form>) session.get("listaPasajeros")
				: new ArrayList<VentaPaso3Form>();
	}

	public void setListaPasajeros(List<VentaPaso3Form> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}

	public List<VentaPaso3Form> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajerosVuelta(List<VentaPaso4Form> listaPasajerosVuelta) {
		this.listaPasajerosVuelta = listaPasajerosVuelta;
	}

	public List<VentaPaso4Form> getListaPasajerosVuelta() {
		return listaPasajerosVuelta;
	}

	public void setError(boolean errorserver) {
		this.errorserver = errorserver;
	}

	public boolean getError() {
		return errorserver;
	}

	public void setMensajeServer(List<String> mensajeServer) {
		this.mensajeServer = mensajeServer;
	}

	public List<String> getMensajeServer() {
		return mensajeServer;
	}

	public void setPaso3Form(VentaPaso3Form paso3Form) {
		this.paso3Form = paso3Form;
	}

	public VentaPaso3Form getPaso3Form() {
		return paso3Form;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setListaComboIdentidad(List<ComboIdentidad> listaComboIdentidad) {
		this.listaComboIdentidad = listaComboIdentidad;
	}

	public List<ComboIdentidad> getListaComboIdentidad() {
		return listaComboIdentidad;
	}

	public void setListaComboDestinoBajada(List<V_RutasBean> listaComboDestinoBajada) {
		this.listaComboDestinoBajada = listaComboDestinoBajada;
	}

	public List<V_RutasBean> getListaComboDestinoBajada() {
		return listaComboDestinoBajada;
	}

	public void setListaComoEmbarque(List<B_ProgramacionSalidaBean> listaComoEmbarque) {
		this.listaComoEmbarque = listaComoEmbarque;
	}

	public List<B_ProgramacionSalidaBean> getListaComoEmbarque() {
		return listaComoEmbarque;
	}

	public void setListaCroquisBusIda(List<V_CroquisBusBean> listaCroquisBusIda) {
		this.listaCroquisBusIda = listaCroquisBusIda;
	}

	public List<V_CroquisBusBean> getListaCroquisBusIda() {
		return listaCroquisBusIda;
	}

	public String getCuppon() {
		return cuppon;
	}

	public void setCuppon(String cuppon) {
		this.cuppon = cuppon;
	}

	public Cupon getCuponbean() {
		return Cuponbean;
	}

	public void setCuponbean(Cupon cuponbean) {
		Cuponbean = cuponbean;
	}

}
