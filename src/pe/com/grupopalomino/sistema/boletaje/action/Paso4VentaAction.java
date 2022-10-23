package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.context.SecurityContextHolder;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;

import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.Cupon;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CroquisBusBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ServiciosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.dao.B_CuponDao;
import pe.com.grupopalomino.sistema.boletaje.dao.CuponIDao;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso2Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso3Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso4Form;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativosService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteServiceI;
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
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesGeneralesUtils;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
// @PreAuthorize("hasAnyRole('1','3','SMS')")
public class Paso4VentaAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private VentaPaso4Form paso4Form = new VentaPaso4Form();
	private List<VentaPaso4Form> listaPasajerosTabla = new ArrayList<VentaPaso4Form>();
	private String mensajeServer;
	private boolean errorserver = false;
	private CorrelativosService servicecorrelativo = new CorrelativoServiceI();
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private VariosService servicevarios = new VariosServiceI();
	private UsuariosWebService serviceusuario = new UsuariosWebServiceI();
	private CuentaCorrienteService servicecuentacorriente = new CuentaCorrienteServiceI();
	private List<Map<String, Object>> listaPasajerosIdaVueltaMapa = new ArrayList<Map<String, Object>>();
	private String searchPasajero = "";
	private String idPasajero;
	private String numeroAsiento;
	private String destinobajadaVuelta;
	private String embarqueVuelta;
	private String idTabla;
	private String numeroDocumento;
	private double precio;
	private List<ListaPreVenta> ListaPreventaIdaVuelta = new ArrayList<ListaPreVenta>();
	private static final Log log = LogFactory.getLog(Paso4VentaAction.class);

	private B_CuponDao servicecupon = new CuponIDao();
	private ServiciosService serviceservicio = new ServiciosServiceI();
	private RutasService servicerutasXcupon = new RutasServiceI();
	private Cupon cuponbean = new Cupon();

	@SuppressWarnings("unchecked")
	@Action(value = "/pasajeroidavuelta", results = { @Result(name = "success", type = "json") })
	public String listaPasajerosIdaVuelta() {

		try {

			listaPasajerosIdaVueltaMapa.clear();

			/*************************************************************
			 * CARGAMOS LOS PASAJEROS PARA LA VENTA DE IDA Y VUELTA
			 ***************************************************************************/

			if (session.get("listaPasajerosIdaVuelta") != null) {

				List<VentaPaso4Form> pasajeros = (session.get("listaPasajerosIdaVuelta") == null)
						? new ArrayList<VentaPaso4Form>()
						: (List<VentaPaso4Form>) session.get("listaPasajerosIdaVuelta");

				if (searchPasajero != null && !searchPasajero.trim().equals("")) {
					for (VentaPaso4Form bean : pasajeros) {

						if (bean.getNombrePasajero().contains(searchPasajero.toUpperCase())) {
							Map<String, Object> mapaJSONResultadoPasajero = new HashMap<>();
							mapaJSONResultadoPasajero.put("id", bean.getId());
							mapaJSONResultadoPasajero.put("text", bean.getNombrePasajero());
							mapaJSONResultadoPasajero.put("visible", bean.isAsientoasignado());
							listaPasajerosIdaVueltaMapa.add(mapaJSONResultadoPasajero);
						}
					}

				} else {

					for (VentaPaso4Form bean : pasajeros) {
						Map<String, Object> mapaJSONResultadoPasajero = new HashMap<>();
						mapaJSONResultadoPasajero.put("id", bean.getId());
						mapaJSONResultadoPasajero.put("text", bean.getNombrePasajero());
						mapaJSONResultadoPasajero.put("visible", bean.isAsientoasignado());
						listaPasajerosIdaVueltaMapa.add(mapaJSONResultadoPasajero);
					}
				}

			} else {

				List<VentaPaso4Form> data = (session.get("ListaAuxiliar") == null) ? new ArrayList<VentaPaso4Form>()
						: (List<VentaPaso4Form>) session.get("ListaAuxiliar");

				for (VentaPaso4Form ventaPaso4Form : data) {
					ventaPaso4Form.setAsientoasignado(false);
				}

				for (VentaPaso4Form bean : data) {
					Map<String, Object> mapaJSONResultadoPasajero = new HashMap<>();
					mapaJSONResultadoPasajero.put("id", bean.getId());
					mapaJSONResultadoPasajero.put("text", bean.getNombrePasajero());
					mapaJSONResultadoPasajero.put("visible", bean.isAsientoasignado());
					listaPasajerosIdaVueltaMapa.add(mapaJSONResultadoPasajero);
				}

				session.put("listaPasajerosIdaVuelta", data);
			}

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/asientopasajero", results = { @Result(name = SUCCESS, type = "json") })
	public String AgregarPasajeroAsiento() {

		String mensajeTransaccion = "";

		try {

			double montoPasajeroIda = 0.0;

			List<VentaPaso3Form> listaPasajeroMontoIda = new ArrayList<VentaPaso3Form>();

			listaPasajeroMontoIda = (session.get("listaPasajeros") == null ? new ArrayList<VentaPaso3Form>()
					: (List<VentaPaso3Form>) session.get("listaPasajeros"));

			for (VentaPaso3Form listaMonto : listaPasajeroMontoIda) {
				montoPasajeroIda = montoPasajeroIda + Double.parseDouble(listaMonto.getPrecio());
			}

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

			VentaInterface ventadatos = new VentaInterfaceI();
			int minValorVuelta = (int) session.get("minValorVuelta");
			double PrecioActual = 0.0;
			double montoVentaActual = 0.0;
			VentaPaso2Form paso2FormVUELTA = (VentaPaso2Form) session.get("paso2FormVUELTA");
			List<V_CroquisBusBean> listaCroquisBusVuelta = (List<V_CroquisBusBean>) session
					.get("listaCroquisBusVuelta");

			List<VentaPaso4Form> listaPasajerosIdaVuelta = (List<VentaPaso4Form>) session
					.get("listaPasajerosIdaVuelta");

			if (embarqueVuelta == null || embarqueVuelta.trim().isEmpty() || embarqueVuelta.trim().equals("-1")) {
				errorserver = true;
				mensajeServer = "Por favor, seleccione un embarque.";
				return SUCCESS;
			}

			if (idPasajero == null || idPasajero.trim().isEmpty()) {
				errorserver = true;
				mensajeServer = "Por favor, seleccione un pasajero.";
				return SUCCESS;
			}

			if (usuario != null) {

				if (usuario.getRealizaVenta().trim().equals("N")) {
					errorserver = true;
					mensajeServer = "Estimado usuario usted no tiene permisos para realizar Ventas,consultar con el Administrador de Sistemas.";
					return SUCCESS;
				}

				V_Varios PrecioMaximoPermitido = servicevarios.Get_Precio_Maximo_Asiento();

				if (PrecioMaximoPermitido != null) {
					paso4Form.setPrecio(String.valueOf(precio));
					paso4Form.setPrecioMaximo(PrecioMaximoPermitido.getMaximoBoletoPrecio());
					paso4Form.setNumeroAsiento(numeroAsiento);
					if (Double.parseDouble(paso4Form.getPrecio()) > PrecioMaximoPermitido.getMaximoBoletoPrecio()) {
						errorserver = true;
						mensajeServer = "El precio Maximo de Boleto es de "
								+ PrecioMaximoPermitido.getMaximoBoletoPrecio();
						return SUCCESS;
					}
				}
				PrecioActual = ventadatos.VerificaPrecioActualVuelta(listaCroquisBusVuelta, paso2FormVUELTA, paso4Form,
						minValorVuelta, usuario);
				if (PrecioActual > 0) {
					errorserver = true;
					mensajeServer = "El Precio Ingresado No debe ser menor al Precio Actual :" + PrecioActual;
					return SUCCESS;
				}

				// VALIDAMOS EL SI LA VENA A REALIZAR SUPERA EL LIMITE DE
				// CREDITO(SOLO PARA LAS AGENCIAS)

				SpringSecurityUser ventausuario = serviceusuario.limiteCreditoUsuario(usuario.getUsername());

				if (ventausuario != null) {
					double monto = 0.0;
					double montoavender = 0.0;

					List<VentaPaso4Form> listaPasajeroMonto = new ArrayList<VentaPaso4Form>();

					listaPasajeroMonto = (session.get("listaPasajerosTabla") == null ? new ArrayList<VentaPaso4Form>()
							: (List<VentaPaso4Form>) session.get("listaPasajerosTabla"));

					for (VentaPaso4Form listaMonto : listaPasajeroMonto) {
						montoavender = montoavender + Double.parseDouble(listaMonto.getPrecio());
					}
					montoavender = montoavender + Double.parseDouble(paso4Form.getPrecio());

					montoavender = montoavender + montoPasajeroIda;

					monto = servicecuentacorriente.VentasRealizadasCuentaCorriente(ventausuario.getRuc().trim(),
							ventausuario.getUsername());
					if ((montoavender + monto) > ventausuario.getLimiteCredito()) {
						errorserver = true;
						mensajeServer = "Usted No puede agregar mas pasajeros, por exceder su Limite de Crédito, por favor Consulte con nuestras Agencias.";
						return SUCCESS;
					}

					/*
					 * montoVentaActual = ventausuario.getMontoVentaActual();
					 * if((ventausuario.getMontoVentaActual()+
					 * Double.parseDouble(paso4Form.getPrecio())) >
					 * ventausuario.getLimiteCredito()){ errorserver = true;
					 * mensajeServer =
					 * "Usted No puede realizar la venta por exceder su Limite de Crédito, por favor Consulte con nuestras Agencias."
					 * ; return SUCCESS; }
					 */
				}
			}

			for (VentaPaso4Form pasajero : listaPasajerosIdaVuelta) {
				if (pasajero.getId().trim().equals(idPasajero.trim())) {
					if (pasajero.isAsientoasignado()) {
						errorserver = true;
						mensajeServer = "El pasajero ya esta asignado al número de Asiento: "
								+ pasajero.getNumeroAsiento();
						return SUCCESS;
					} else {
						pasajero.setNumeroAsiento(numeroAsiento.trim());
						pasajero.setComboEmbarque(embarqueVuelta.trim());
						pasajero.setComboDestinoBajada(destinobajadaVuelta.trim());
						pasajero.setPrecio(String.valueOf(precio));
						pasajero.setMontoVentaActual(montoVentaActual);
						pasajero.setAsientoasignado(true);
						idTabla = pasajero.getId();
						listaPasajerosTabla.add(pasajero);

						if (session.get("listaPasajerosTabla") == null) {
							session.put("listaPasajerosTabla", listaPasajerosTabla);

						} else {
							List<VentaPaso4Form> listaPasajerosTabla = (List<VentaPaso4Form>) session
									.get("listaPasajerosTabla");
							listaPasajerosTabla.add(pasajero);
							session.put("listaPasajerosTabla", listaPasajerosTabla);
						}
					}
					mensajeTransaccion = GeneraPreventaVuelta(pasajero);
					if (!(mensajeTransaccion.trim().equals(""))) {

						errorserver = true;
						mensajeServer = mensajeTransaccion;
						return SUCCESS;
					}
				}
			}
			session.put("listaPasajerosIdaVuelta", listaPasajerosIdaVuelta);
			return SUCCESS;

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked" })
	public String GeneraPreventaVuelta(VentaPaso4Form paso4Form) {

		String mensajeResultado = "";
		boolean resultado = true;
		int operacion = -1;
		VentaInterface ventadatos = new VentaInterfaceI();
		SpringSecurityUser usuario = null;
		/**************************************************************************************************************************/
		B_Correlativos correlativo = new B_Correlativos();
		B_VentaBean ventaVuelta = new B_VentaBean();
		int minValorVuelta = (int) session.get("minValorVuelta");
		VentaPaso2Form paso2FormVUELTA = (VentaPaso2Form) session.get("paso2FormVUELTA");
		List<V_CroquisBusBean> listaCroquisBusVuelta = (List<V_CroquisBusBean>) session.get("listaCroquisBusVuelta");

		try {
			// FECHA DEL SERVIDOR (BD)
			String FechaEmision = FuncionesGeneralesUtils.F_ObtenerFechaServidor();

			if (session.get("ListaPreventaIdaVuelta") != null) {
				ListaPreventaIdaVuelta = (List<ListaPreVenta>) session.get("ListaPreventaIdaVuelta");

			}

			if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
				if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
					if (SecurityContextHolder.getContext().getAuthentication()
							.getPrincipal() instanceof SpringSecurityUser) {
						usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
								.getPrincipal();
					}
				}
			}

			/******************************************************************************************************************************************************************************************************************/
			// REGISTRAMOS LA VENTA DE VUELTA

			resultado = ventadatos.GeneraDatosClienteVentaVuelta(paso4Form);

			if (resultado) {

				resultado = ventadatos.GeneraDatosPasajeroventaVuelta(paso4Form);

				if (resultado) {

					resultado = ventadatos.VerificaDisponibilidadAsientoVuelta(paso2FormVUELTA, paso4Form);

					if (!(resultado)) {

						mensajeResultado = "El Asiento " + paso4Form.getNumeroAsiento()
								+ " ya se encuentra Reservado, por favor seleccione otro";
						CleanPasajeroAgregado(paso4Form);

						return mensajeResultado;
					}
					ventaVuelta = ventadatos.DatosEstaticosVentaVuelta(ventaVuelta, paso4Form, usuario);
					correlativo = servicecorrelativo.generaCorrelativo();

					if (correlativo != null) {

						ventaVuelta = ventadatos.DatosDinamicosVentaVuelta(ventaVuelta, correlativo, FechaEmision,
								paso2FormVUELTA, paso4Form, listaCroquisBusVuelta, minValorVuelta, usuario);

						// VALIDANDO EL PRECIO INGRESADO DESDE LA INTERFAZ DEL
						// USUSARIO (VALIDO SOLO PARA LAS AGENCIAS)

						if (usuario != null) {
							
							
							
							ventaVuelta.setPrecioAct(Double.parseDouble(paso4Form.getPrecio()));
							ventaVuelta.setPrecio(Double.parseDouble(paso4Form.getPrecio()));
						}

						operacion = serviceventa.insertVenta(ventaVuelta);

						if (operacion != -1) {
							ListaPreVenta preventa = new ListaPreVenta();
							preventa.SetValuesPreVentaIda(paso4Form.getId(), ventaVuelta.getNro(),
									ventaVuelta.getSalida(), ventaVuelta.getNombre(), ventaVuelta.getPrecioAct(),
									correlativo.getCorrelativoCtaCte(), correlativo.getCorrelativoEnc(),
									correlativo.getCorrelativoEnc(), FechaEmision);
							preventa.setRuc(paso4Form.getRuc());
							preventa.setAsiento(Integer.parseInt(ventaVuelta.getAsiento()));
							preventa.setDestinoD(ventaVuelta.getDestinoD());
							preventa.setHora1(ventaVuelta.getHoraViaje());
							preventa.setFechaViaje(ventaVuelta.getFechaViaje());		
							ListaPreventaIdaVuelta.add(preventa);

							// Actualizamos la el Monto Actual de Venta del
							// Usuario
							/*
							 * if(usuario != null){ operacion =
							 * serviceusuario.updateMontoVentaActual(usuario.
							 * getUsername(),
							 * paso4Form.getMontoVentaActual()+ventaVuelta.
							 * getPrecioAct()); }
							 */

							if (operacion == -1) {
								mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 005')";
								CleanPasajeroAgregado(paso4Form);
								return mensajeResultado;
							}

						} else {
							mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 004')";
							CleanPasajeroAgregado(paso4Form);
							return mensajeResultado;
						}
					} else {
						mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 003')";
						CleanPasajeroAgregado(paso4Form);
						return mensajeResultado;
					}
				} else {

					mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 002')";
					CleanPasajeroAgregado(paso4Form);
					return mensajeResultado;
				}
			} else {
				mensajeResultado = "Ocurrio un problema al Agregar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 001')";
				CleanPasajeroAgregado(paso4Form);
				return mensajeResultado;
			}
			session.put("ListaPreventaIdaVuelta", ListaPreventaIdaVuelta);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			mensajeResultado = e.getMessage();
		}
		return mensajeResultado;
	}

	@SuppressWarnings("unchecked")
	private void CleanPasajeroAgregado(VentaPaso4Form paso4Form) {

		List<VentaPaso4Form> listaPasajerosIdaVuelta = (session.get("listaPasajerosIdaVuelta") == null
				? new ArrayList<VentaPaso4Form>() : (List<VentaPaso4Form>) session.get("listaPasajerosIdaVuelta"));

		for (VentaPaso4Form pasajero : listaPasajerosIdaVuelta) {
			if (pasajero.getId().trim().equals(paso4Form.getId())) {
				paso4Form.setNumeroAsiento("");
				paso4Form.setComboEmbarque("");
				paso4Form.setComboDestinoBajada("");
				paso4Form.setPrecio("");
				paso4Form.setAsientoasignado(false);
			}
		}
		session.put("listaPasajerosIdaVuelta", listaPasajerosIdaVuelta);

	}

	@SuppressWarnings("unchecked")
	@Action(value = "/quitarpasajerovuelta", results = { @Result(name = SUCCESS, type = "json") })
	public String quitarPasajeroVuelta() {

		String mensajeTransaccion = "";
		List<VentaPaso4Form> listaPasajerosTabla = (List<VentaPaso4Form>) session.get("listaPasajerosTabla");
		List<VentaPaso4Form> listaPasajerosIdaVuelta = (List<VentaPaso4Form>) session.get("listaPasajerosIdaVuelta");

		for (VentaPaso4Form pasajero : listaPasajerosTabla) {
			if (pasajero.getId().trim().equals(numeroDocumento.trim())) {
				pasajero.setAsientoasignado(false);
				listaPasajerosTabla.remove(pasajero);

				for (VentaPaso4Form pasajeroSelect2 : listaPasajerosIdaVuelta) {
					if (pasajeroSelect2.getId().trim().equals(numeroDocumento.trim())) {
						pasajeroSelect2.setAsientoasignado(false);
						// ELIMINAMOS LA PREVENTA
						mensajeTransaccion = EliminaPreVentaVuelta(pasajeroSelect2);
						if (!(mensajeTransaccion.trim().equals(""))) {
							errorserver = true;
							mensajeServer = mensajeTransaccion;
							return SUCCESS;
						}
						session.put("listaPasajerosIdaVuelta", listaPasajerosIdaVuelta);
						break;
					}
				}
				session.put("listaPasajerosTabla", listaPasajerosTabla);
				break;
			}
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String EliminaPreVentaVuelta(VentaPaso4Form pasajero) {

		int operacion = -1;
		List<ListaPreVenta> ListaPreventaIdaVuelta = (List<ListaPreVenta>) session.get("ListaPreventaIdaVuelta");
		String mensajeResultado = "";
		// SpringSecurityUser usuario = null;

		/*
		 * if(!(SecurityContextHolder.getContext().getAuthentication().
		 * getPrincipal() instanceof String)){
		 * if(SecurityContextHolder.getContext().getAuthentication().
		 * getPrincipal() instanceof SpringSecurityUser){ usuario=
		 * (SpringSecurityUser)
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		 * ; } }
		 */

		try {

			for (ListaPreVenta preventa : ListaPreventaIdaVuelta) {

				if (pasajero.getId().trim().equals(preventa.getIdPasajero().trim())) {

					operacion = serviceventa.deleteVenta(preventa.getNro(), preventa.getSalida());

					if (operacion == -1) {
						mensajeResultado = "Ocurrio un Problema al Eliminar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 001')";
						return mensajeResultado;
					} else {
						/*
						 * if(usuario!= null){
						 * 
						 * SpringSecurityUser ventausuario =
						 * serviceusuario.limiteCreditoUsuario(usuario.
						 * getUsername()); operacion =
						 * serviceusuario.updateMontoVentaActual(usuario.
						 * getUsername(),(ventausuario.getMontoVentaActual())-(
						 * preventa.getPrecioAct()));
						 * 
						 * if(operacion == -1){ mensajeResultado =
						 * "Ocurrio un Problema al Eliminar al Pasajero.(Consulte con Administrador de Sistemas 'ERROR 002')"
						 * ; return mensajeResultado; } }
						 */
						ListaPreventaIdaVuelta.remove(preventa);
						break;
					}
				}
			}
			session.put("ListaPreventaIdaVuelta", ListaPreventaIdaVuelta);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return mensajeResultado;
	}

	private String cuppon;

	public String getCuppon() {
		return cuppon;
	}

	public void setCuppon(String cuppon) {
		this.cuppon = cuppon;
	}

	@Action(value = "comprobarcupponret", results = { @Result(name = SUCCESS, type = "json") })
	public String comprobarcuppon() {
		try {

			VentaPaso1Form paso1FormIda = (VentaPaso1Form) session.get("paso1Form");
			VentaPaso2Form paso2FormIda = (VentaPaso2Form) session.get("paso2FormIDA");
			String paracupon = paso1FormIda.isIdaVuelta() ? "RETORNO" : "IDA";
			String fechaida = paso1FormIda.getFechaIda();// .replace("/", "-");
			String fechavuelta = paso1FormIda.getFechaVuelta();
			String[] fechaarray = fechaida.split("/");
			fechaida = fechaarray[2] + "-" + fechaarray[1] + "-" + fechaarray[0];

			String[] fechaarrayret = fechavuelta.split("/");
			fechavuelta = fechaarrayret[2] + "-" + fechaarrayret[1] + "-" + fechaarrayret[0];

			Map<String, Object> parameteres = new HashMap<>();
			parameteres.put("codigocupon", this.cuppon);
			parameteres.put("paracupon", paracupon);
			parameteres.put("fechaida", fechaida);
			parameteres.put("fecharetorno", fechavuelta);

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
					cuponbean = temp;
					session.put("Cuponactivo", cuponbean);
					return SUCCESS;
				}
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		errorserver = true;
		mensajeServer = "Cupon no Existe";
		return SUCCESS;
	}

	// ListaPreventaIdaVuelta
	@Action(value = "aplicarcuponret", results = { @Result(name = SUCCESS, type = "json") })
	public String AplicarCupon() {
		ListaPreventaIdaVuelta = (List<ListaPreVenta>) session.get("ListaPreventaIdaVuelta");

		// serviceventa.SQL_UpdatePrecioXCupon(nro, Salida, precio);
		cuponbean = (Cupon) session.get("Cuponactivo");
		// cuponbean.setDescuento(1-cuponbean.getDescuento());
		System.out.println("Cupon : " + cuponbean.getDetalle());

		actualizarprecio();
		return SUCCESS;
	}

	double total = 0;
	double totalcupon = 0;

	@SuppressWarnings("unchecked")
	public String actualizarprecio() {
		// System.out.println(cuponbean.getDescuento());
		// session.put("listaPasajeros", listaPasajeros);

		String mensajeAplicarCupon = "";
		double Precioboleto;
		try {

			List<ListaPreVenta> ListaPreventaIdaVuelta = (List<ListaPreVenta>) session.get("ListaPreventaIdaVuelta");
			int operacion = -1;

			for (ListaPreVenta preventa : ListaPreventaIdaVuelta) {
				total += preventa.getPrecioAct();
				Precioboleto = preventa.getPrecioAct() * (1 - cuponbean.getDescuento());
				totalcupon += Math.round(Precioboleto);
				// Math.round(totalcupon);
				operacion = serviceventa.SQL_UpdatePrecioXCupon("" + preventa.getNro(), "" + preventa.getSalida(),
						Math.round(Precioboleto), cuponbean.getDetalle());
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

	public void setIdTabla(String idTabla) {
		this.idTabla = idTabla;
	}

	public String getIdTabla() {
		return idTabla;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setSearchPasajero(String searchPasajero) {
		this.searchPasajero = searchPasajero;
	}

	public String getSearchPasajero() {
		return searchPasajero;
	}

	public void setListaPasajerosIdaVueltaMapa(List<Map<String, Object>> listaPasajerosIdaVueltaMapa) {
		this.listaPasajerosIdaVueltaMapa = listaPasajerosIdaVueltaMapa;
	}

	public List<Map<String, Object>> getListaPasajerosIdaVueltaMapa() {
		return listaPasajerosIdaVueltaMapa;
	}

	public void setIdPasajero(String idPasajero) {
		this.idPasajero = idPasajero;
	}

	public String getIdPasajero() {
		return idPasajero;
	}

	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public String getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setDestinobajadaVuelta(String destinobajadaVuelta) {
		this.destinobajadaVuelta = destinobajadaVuelta;
	}

	public String getDestinobajadaVuelta() {
		return destinobajadaVuelta;
	}

	public void setEmbarqueVuelta(String embarqueVuelta) {
		this.embarqueVuelta = embarqueVuelta;
	}

	public String getEmbarqueVuelta() {
		return embarqueVuelta;
	}

	public void setPaso4Form(VentaPaso4Form paso4Form) {
		this.paso4Form = paso4Form;
	}

	public VentaPaso4Form getPaso4Form() {
		return paso4Form;
	}

	public void setErrorserver(boolean errorserver) {
		this.errorserver = errorserver;
	}

	public boolean isErrorserver() {
		return errorserver;
	}

	public void setMensajeServer(String mensajeServer) {
		this.mensajeServer = mensajeServer;
	}

	public String getMensajeServer() {
		return mensajeServer;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setListaPasajerosTabla(List<VentaPaso4Form> listaPasajerosTabla) {
		this.listaPasajerosTabla = listaPasajerosTabla;
	}

	public List<VentaPaso4Form> getListaPasajerosTabla() {
		return listaPasajerosTabla;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public double getTotal() {
		return total;
	}

	public double getTotalcupon() {
		return Math.round(totalcupon);
	}

	public Cupon getcuponbean() {
		return cuponbean;
	}

	public void setcuponbean(Cupon cuponbean) {
		this.cuponbean = cuponbean;
	}
}
