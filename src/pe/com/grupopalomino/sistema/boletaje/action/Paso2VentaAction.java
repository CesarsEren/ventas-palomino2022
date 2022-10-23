package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.context.SecurityContextHolder;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Clientes_RutaPrecioBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CroquisBusBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Promo_RetornoCBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ServiciosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.Precio_Desde;
import pe.com.grupopalomino.sistema.boletaje.formviews.ComboIdentidad;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso2Form;
//import pe.com.grupopalomino.sistema.boletaje.service.ClientesRutaPrecioService;
//import pe.com.grupopalomino.sistema.boletaje.service.ClientesRutaPrecioServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CroquisService;
import pe.com.grupopalomino.sistema.boletaje.service.CroquisServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaService;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Promo_RetornoCService;
import pe.com.grupopalomino.sistema.boletaje.service.Promo_RetornoCServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ServiciosService;
import pe.com.grupopalomino.sistema.boletaje.service.ServiciosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.ErrorValidacion;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.util.ValidaDatosPaso2Form;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
//@PreAuthorize("hasAnyRole('1','3','SMS','C')")
public class Paso2VentaAction extends ActionSupport implements SessionAware, ServletRequestAware {
	
	private HttpServletRequest request;
	private Map<String, Object> session;
	private Map<String, Object> mapaJSONResultadoIDA  = new HashMap<>();
	private Map<String, Object> mapaJSONResultadoVUELTA  = new HashMap<>();
	private Map<String, Object> mapaJSONResultadoPRECIOS  = new HashMap<>();
	private VentaPaso2Form paso2FormIDA 	= new VentaPaso2Form();
	private VentaPaso2Form paso2FormVUELTA 	= new VentaPaso2Form();
	private int minValorIda;
	private double Precio;
	private String top_yAsiento;
	private String  nroAsiento;
	private List<V_CroquisBusBean> listaCroquisBusIda;
	private List<V_RutasBean> listaComboDestinoBajada = new ArrayList<V_RutasBean>();
	private List<ComboIdentidad> listaComboIdentidad = new ArrayList<ComboIdentidad>();
	private List<B_ProgramacionSalidaBean> listaComoEmbarque = new ArrayList<B_ProgramacionSalidaBean>();
	private ProgramacionSalidaService service= new ProgramacionSalidaServiceI();
	private ServiciosService serviceservicio= new ServiciosServiceI(); 
	private CroquisService serviceCroquis = new CroquisServiceI();
	private VariosService servicevarios = new VariosServiceI();
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	//private ClientesRutaPrecioService serviceclienteruta = new ClientesRutaPrecioServiceI();
	private ProgramacionSalidaService servicesalidas= new ProgramacionSalidaServiceI();
	private Promo_RetornoCService servicepromociones = new Promo_RetornoCServiceI(); 
	private static final Log log = LogFactory.getLog(Paso2VentaAction.class);
	private List<V_AgenciasBean> listaAsientoOcupadoAgencia = new ArrayList<V_AgenciasBean>();
	private List<V_AgenciasBean> listaAsientoReservadoAgencia = new ArrayList<V_AgenciasBean>();
	private List<V_AgenciasBean> listaComidas = new ArrayList<V_AgenciasBean>();
	
	
	@Action(value = "/ListaProgramacionSalidaGridIda", results = { @Result(name = "success", type = "json") })
	public String ListaProgramacionSalidaGridIda() {
		
		try {
			
			if(session.get("paso1Form")!=null){
				
				VentaPaso1Form paso1Form = (VentaPaso1Form) session.get("paso1Form");
				
				//SpringSecurityUser usuario = null;
				
				if((paso1Form.getFechaIda() != null && ! paso1Form.getFechaIda().trim().equals("")) && (paso1Form.getDestinoIda() !=null && ! paso1Form.getDestinoIda().trim().equals(""))){
					
					//System.out.println(paso1Form.getOrigenVuelta());
					//System.out.println(paso1Form.getDestinoVuelta());
					
					List<B_ProgramacionSalidaBean>  data =service.getSalidas(Utils.FormatoFecha(paso1Form.getFechaIda()),paso1Form.getRolUser(),paso1Form.getOrigenIda(),paso1Form.getDestinoIda(),0);
					List<Map<String, Object>> lstAuxiliar = new ArrayList<>();
					List<Map<String, Object>> lstPrexAsiento = new ArrayList<>();
					
					
					/**
					 ESTO SE COMENTO POR LO SIGUIENTE:
					 	- NO SE LLEGO A AGREGAR A NINGUN CLIENTE PARA ESTE PROCESO.
					 
					 DESCRIPCION DEL PROCESO
					 	- SE REQUIERE QUE UN CLIENTE SE PUEDA ABASTECER SU COMPRA DE PASAJES EN UNA DETERMINADA RUTA, PRECIO Y SERVICIO
					 	  SEGÚN ESOS PARAMETROS SE CONFIGURABA AL CLIENTE.
					 	   
					 **/
					
					/*if(SecurityContextHolder.getContext().getAuthentication().getName()!= null){
						if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
							if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
								usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
							}
						}
					}*/
					
					/*if(usuario != null){
						
						V_Clientes_RutaPrecioBean beanclienteRuta = new V_Clientes_RutaPrecioBean();
						
							for (B_ProgramacionSalidaBean bean : data) {
								
								Map<String, Object> mapaAuxiliar = new HashMap<>();
							
								beanclienteRuta = serviceclienteruta.listaClientesRutasVerificacion(usuario.getRuc(),paso1Form.getNroDestinoIDA(), bean.getServicio());
								
								if(beanclienteRuta != null){
									bean.setPrecio1(beanclienteRuta.getPrecio1());
									bean.setPrecio2(beanclienteRuta.getPrecio2()); 
								}
								mapaAuxiliar.put("nro", bean.getNro());
								mapaAuxiliar.put("servicio", bean.getServicio());
								mapaAuxiliar.put("servicioD", bean.getServicioD());
								mapaAuxiliar.put("agencia1D", bean.getAgencia1D());
								mapaAuxiliar.put("hora1", bean.getHora1());
								mapaAuxiliar.put("agencia2D", bean.getAgencia2D());
								mapaAuxiliar.put("hora2", bean.getHora2());
								mapaAuxiliar.put("agencia3D", bean.getAgencia3D());
								mapaAuxiliar.put("hora3",bean.getHora3());
								mapaAuxiliar.put("agencia4D", bean.getAgencia4D());
								mapaAuxiliar.put("hora4", bean.getHora4());
								mapaAuxiliar.put("precio1", bean.getPrecio1());
								mapaAuxiliar.put("precio2", bean.getPrecio2());
								mapaAuxiliar.put("bus", bean.getBus());
								mapaAuxiliar.put("descuento1", bean.getDescuento1());
								mapaAuxiliar.put("descuento2", bean.getDescuento2());
								lstAuxiliar.add(mapaAuxiliar);
							}
					}*/ //else{
						
						for (B_ProgramacionSalidaBean bean : data) {
							Map<String, Object> mapaAuxiliar = new HashMap<>();
							mapaAuxiliar.put("nro", bean.getNro());
							mapaAuxiliar.put("destino", bean.getDestino());
							mapaAuxiliar.put("servicio", bean.getServicio());
							mapaAuxiliar.put("servicioD", bean.getServicioD());
							mapaAuxiliar.put("agencia1D", bean.getAgencia1D());
							mapaAuxiliar.put("hora1", bean.getHora1());
							mapaAuxiliar.put("agencia2D", bean.getAgencia2D());
							mapaAuxiliar.put("hora2", bean.getHora2());
							mapaAuxiliar.put("agencia3D", bean.getAgencia3D());
							mapaAuxiliar.put("hora3",bean.getHora3());
							mapaAuxiliar.put("agencia4D", bean.getAgencia4D());
							mapaAuxiliar.put("hora4", bean.getHora4());
							mapaAuxiliar.put("agencia5D", bean.getAgencia5D());
							mapaAuxiliar.put("hora5", bean.getHora5());
							mapaAuxiliar.put("precio1", bean.getPrecio1());
							
							if(bean.getServicio().equals("16")) { mapaAuxiliar.put("precio1", bean.getPrecio1() + "<BR>" +bean.getPrecio2()); }
							else { mapaAuxiliar.put("precio1", bean.getPrecio1()); }
							
							if(bean.getServicio().equals("16")) {	mapaAuxiliar.put("precio2", " - ");	}
							else { mapaAuxiliar.put("precio2", bean.getPrecio2()); }					
							
							mapaAuxiliar.put("bus", bean.getBus());
							mapaAuxiliar.put("descuento1", bean.getDescuento1());
							mapaAuxiliar.put("descuento2", bean.getDescuento2());
							lstAuxiliar.add(mapaAuxiliar);
							List<Precio_Desde> data2 =service.getPrecioDesde(bean.getNro());
							for (Precio_Desde bean2 : data2) {
								Map<String, Object> mapaAuxiliar2 = new HashMap<>();
								mapaAuxiliar2.put("Nro", bean2.getNro());
								mapaAuxiliar2.put("Asiento", bean2.getAsiento());
								mapaAuxiliar2.put("Preciodesde", bean2.getPrecio());
								lstPrexAsiento.add(mapaAuxiliar2);
								log.info(lstPrexAsiento.toString());
							}
							
							
							
						}
						
						
					//}
					
					mapaJSONResultadoIDA.put("rows", lstAuxiliar);
					mapaJSONResultadoIDA.put("total", data.size());
					mapaJSONResultadoPRECIOS.put("PrecioDesde", lstPrexAsiento);
					
				}
				
			}
				
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}
	
	@Action(value = "/ListaProgramacionSalidaGridIdaVuelta", results = { @Result(name = "success", type = "json") })
	public String ListaProgramacionSalidaGridIdaVuelta() {
		try {
			
			if(session.get("paso1Form")!=null){
				
				VentaPaso1Form paso1Form = (VentaPaso1Form) session.get("paso1Form");
				V_Promo_RetornoCBean promocionretorno = servicepromociones.SQL_SELECT_TIPO_PROMOCION_RETORNO();
				
				//SpringSecurityUser usuario = null;
				
				List<B_ProgramacionSalidaBean>  data =service.getSalidas(Utils.FormatoFecha(paso1Form.getFechaVuelta()),paso1Form.getRolUser(),paso1Form.getDestinoIda(),paso1Form.getOrigenIda().substring(0,3),(promocionretorno == null ? 0 :promocionretorno.getWeb()));
				List<Map<String, Object>> lstAuxiliar = new ArrayList<>();
				
				/*if(SecurityContextHolder.getContext().getAuthentication().getName()!= null){
					if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
						if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
							usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
						}
					}
				}*/
				
				/*if(usuario != null){
					
					V_Clientes_RutaPrecioBean beanclienteRuta = new V_Clientes_RutaPrecioBean();
					
					for (B_ProgramacionSalidaBean bean : data) {
						
						Map<String, Object> mapaAuxiliar = new HashMap<>();
						
						beanclienteRuta = serviceclienteruta.listaClientesRutasVerificacion(usuario.getRuc(),paso1Form.getNroDestinoVUELTA(), bean.getServicio());
						
						if(beanclienteRuta != null){
							bean.setPrecio1(beanclienteRuta.getPrecio1());
							bean.setPrecio2(beanclienteRuta.getPrecio2()); 
						}
						
						mapaAuxiliar.put("nro", bean.getNro());
						mapaAuxiliar.put("servicio", bean.getServicio());
						mapaAuxiliar.put("servicioD", bean.getServicioD());
						mapaAuxiliar.put("agencia1D", bean.getAgencia1D());
						mapaAuxiliar.put("hora1", bean.getHora1());
						mapaAuxiliar.put("agencia2D", bean.getAgencia2D());
						mapaAuxiliar.put("hora2", bean.getHora2());
						mapaAuxiliar.put("agencia3D", bean.getAgencia3D());
						mapaAuxiliar.put("hora3",bean.getHora3());
						mapaAuxiliar.put("agencia4D", bean.getAgencia4D());
						mapaAuxiliar.put("hora4", bean.getHora4());
						mapaAuxiliar.put("precio1", bean.getPrecio1());
						mapaAuxiliar.put("precio2", bean.getPrecio2());
						mapaAuxiliar.put("bus", bean.getBus());
						mapaAuxiliar.put("descuento1", bean.getDescuento1());
						mapaAuxiliar.put("descuento2", bean.getDescuento2());
						lstAuxiliar.add(mapaAuxiliar);
						
					}
					
					
				}*/ ///else{
					
					for (B_ProgramacionSalidaBean bean : data) {
						Map<String, Object> mapaAuxiliar = new HashMap<>();
						mapaAuxiliar.put("nro", bean.getNro());
						mapaAuxiliar.put("destino", bean.getDestino());
						mapaAuxiliar.put("servicio", bean.getServicio());
						mapaAuxiliar.put("servicioD", bean.getServicioD());
						mapaAuxiliar.put("agencia1D", bean.getAgencia1D());
						mapaAuxiliar.put("hora1", bean.getHora1());
						mapaAuxiliar.put("agencia2D", bean.getAgencia2D());
						mapaAuxiliar.put("hora2", bean.getHora2());
						mapaAuxiliar.put("agencia3D", bean.getAgencia3D());
						mapaAuxiliar.put("hora3",bean.getHora3());
						mapaAuxiliar.put("agencia4D", bean.getAgencia4D());
						mapaAuxiliar.put("hora4", bean.getHora4());
						mapaAuxiliar.put("agencia5D", bean.getAgencia5D());
						mapaAuxiliar.put("hora5", bean.getHora5());
						mapaAuxiliar.put("precio1", bean.getPrecio1());
						mapaAuxiliar.put("precio2", bean.getPrecio2());
						mapaAuxiliar.put("bus", bean.getBus());
						mapaAuxiliar.put("descuento1", bean.getDescuento1());
						mapaAuxiliar.put("descuento2", bean.getDescuento2());
						lstAuxiliar.add(mapaAuxiliar);
					}
					
				//}
				
				mapaJSONResultadoVUELTA.put("rows", lstAuxiliar);
				mapaJSONResultadoVUELTA.put("total", data.size());
				
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}
	
	@Action(value = "paso3", results = {@Result(name = SUCCESS, location = "ventaida/ventaboletospaso3.jsp"), 
										@Result(name = ERROR, location = "ventaida/ventaboletospaso2.jsp"),
										@Result(name = INPUT, location = "ventaida/ventaboletos.jsp")})
	public String irAPaso3(){
		
		String method = request.getMethod();
		System.out.println(method);
		
		VentaPaso1Form paso1Form = (VentaPaso1Form) session.get("paso1Form");
		
		if(paso1Form == null){
			
			return INPUT;
		}
		
		if(session.get("paso2Form")!= null){
			
			VentaPaso2Form paso2 = (VentaPaso2Form) session.get("paso2Form");
			
			log.info("RECIBIENDO DATOS .."+ paso2);  
			
			paso2FormIDA.setDescripcionServicioIda(paso2.getDescripcionServicioIda());
			paso2FormIDA.setHora1Ida(paso2.getHora1Ida());
			paso2FormIDA.setNroBus(paso2.getNroBus());
			paso2FormIDA.setNroDestino(paso2.getNroDestino());
			paso2FormIDA.setNroProgramacion(paso2.getNroProgramacion()); 
			paso2FormIDA.setNroServicio(paso2.getNroServicio()); 
			session.remove("paso2Form");
		}

		if(!paso1Form.isIdaVuelta()){
			ValidaDatosPaso2Form validador = new ValidaDatosPaso2Form(paso2FormIDA);
			ErrorValidacion error = validador.validaPaso2FormIda();
			
			if(error.getError()){
				
				for (String mensaje : error.getMensajeError()) {
					addActionError(mensaje);
				}
				
				return ERROR;
			}
		}
		else{
			
			ValidaDatosPaso2Form validador = new ValidaDatosPaso2Form(paso2FormIDA,paso2FormVUELTA);
			ErrorValidacion error = validador.validaPaso2FormIdaVuelta();
			
			
			if(error.getError()){
				
				for (String mensaje : error.getMensajeError()) {
					addActionError(mensaje);
					
				}
				
				return ERROR;
			}
			
			session.put("paso2FormVUELTA", paso2FormVUELTA);
			
		}
		
		getDetalleBus(paso1Form);
		
		session.remove("listaPasajeros");
		session.remove("listaPasajerosTabla");
		session.remove("listaPasajerosIdaVuelta");
		session.remove("ListaAuxiliar");
		session.remove("ListaPreventaIda");
		session.remove("ListaPreventaIdaVuelta");
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "regresapaso2", results = {@Result(name = SUCCESS, location = "ventaida/ventaboletospaso2.jsp")})
	public String regresaPaso2(){
		
		if(session.get("ListaPreventaIda")!= null){
			
			List<ListaPreVenta> ListaPreventaIda = session.get("ListaPreventaIda") == null ? new ArrayList<ListaPreVenta>():(List<ListaPreVenta>)session.get("ListaPreventaIda");
			
			for (ListaPreVenta preventa : ListaPreventaIda) {
				try {
					serviceventa.deleteVenta(preventa.getNro(),preventa.getSalida());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			session.remove("ListaPreventaIda");
			session.remove("listaPasajeros");
		}
		
		return SUCCESS;
	}
	
	private void getDetalleBus(VentaPaso1Form paso1Form){
		
		int cantidadAsientosOcupados = 0;
		int cantidadAsientosReservados = 0;
		int capacidadBus = 0 ;
		String mostrarComida ="" ;
		V_ServiciosBean servicioBean = new V_ServiciosBean();
		SpringSecurityUser usuario = null;
		
		if(SecurityContextHolder.getContext().getAuthentication().getName()!= null){
			if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
				if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
					usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				}
			}
		}
		
		try {
			capacidadBus = serviceCroquis.cantidadAsientosPorBus((paso2FormIDA.getNroBus().trim().equals("")? "-1" : paso2FormIDA.getNroBus().trim()));
			listaCroquisBusIda = serviceCroquis.obtieneCroquisPorNumeroBus((paso2FormIDA.getNroBus().trim().equals("")? "000" : paso2FormIDA.getNroBus().trim()));
			
			paso2FormIDA.setCapacidadBus(capacidadBus); 
			if(listaCroquisBusIda.size()==0){
				servicioBean=	serviceservicio.getServicioCodigo(paso2FormIDA.getNroServicio());
				if(servicioBean!=null){
					
					listaCroquisBusIda = serviceCroquis.obtieneCroquisPorNumeroBus(servicioBean.getBusPlantilla());
					
					if(capacidadBus == 0){
						
						if(usuario != null){
							if(usuario.getNivel().equals("C")){
								capacidadBus = serviceCroquis.cantidadAsientosPorBus(servicioBean.getBusPlantilla());
								paso2FormIDA.setCapacidadBus(capacidadBus);
								
								if(paso2FormIDA.getNroBus().trim().equals("")){
									
									paso2FormIDA.setNroBus(servicioBean.getBusPlantilla());
								}
							}
							
						}
						
					}
					
				}
			}
			
			if(listaCroquisBusIda.size()>0){
				
				minValorIda =  listaCroquisBusIda.get(0).getTTop();
				
				B_ProgramacionSalidaBean bean = servicesalidas.getSalidasPrecioPromocion(paso2FormIDA.getNroProgramacion(),paso1Form.getDestinoIda(),0);
				V_Clientes_RutaPrecioBean beanclienteRuta = null;
				
				List<Map<String, Object>> lstPrexAsiento = new ArrayList<>();
				List<Precio_Desde> data2 =service.getPrecioDesde(paso2FormIDA.getNroProgramacion());
				for (Precio_Desde bean2 : data2) {
					Map<String, Object> mapaAuxiliar2 = new HashMap<>();
					mapaAuxiliar2.put("Nro", bean2.getNro());
					mapaAuxiliar2.put("Asiento", bean2.getAsiento());
					mapaAuxiliar2.put("Preciodesde", bean2.getPrecio());
					lstPrexAsiento.add(mapaAuxiliar2);
					log.info(lstPrexAsiento.toString());
				}
				
				if(bean != null){
					if(usuario != null){
						
						//beanclienteRuta = serviceclienteruta.listaClientesRutasVerificacion(usuario.getRuc(),bean.getDestino(), bean.getServicio());
						
					}
					for (int x=0; x < listaCroquisBusIda.size();x++) {
						if(minValorIda>=1920){
							if(usuario != null){
								if(beanclienteRuta != null){
									listaCroquisBusIda.get(x).setPrecio(beanclienteRuta.getPrecio1());
									int asi=x+1;																		
									if (lstPrexAsiento.size()>0) {
										for (int i = 0; i < lstPrexAsiento.size(); i++) {
											log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
											if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
												Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
												listaCroquisBusIda.get(x).setPrecio(valor);
												log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
											}
										}										
									}
									
								}else{
									
									listaCroquisBusIda.get(x).setPrecio(bean.getPrecio1());
									int asi=x+1;																		
									if (lstPrexAsiento.size()>0) {
										for (int i = 0; i < lstPrexAsiento.size(); i++) {
											log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
											if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
												Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
												listaCroquisBusIda.get(x).setPrecio(valor);
												log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
											}
										}										
									}
								}	
								
							}else{
								
								listaCroquisBusIda.get(x).setPrecio(bean.getPrecio1());
								int asi=x+1;																		
								if (lstPrexAsiento.size()>0) {
									for (int i = 0; i < lstPrexAsiento.size(); i++) {
										log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
										if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
											Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
											listaCroquisBusIda.get(x).setPrecio(valor);
											log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
										}
									}										
								}
							}
							
						}else{
							if(listaCroquisBusIda.get(x).getTTop()<1920){
								
								if(usuario != null){
									if(beanclienteRuta != null){
										
										listaCroquisBusIda.get(x).setPrecio(beanclienteRuta.getPrecio1());
										int asi=x+1;																		
										if (lstPrexAsiento.size()>0) {
											for (int i = 0; i < lstPrexAsiento.size(); i++) {
												log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
												if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
													Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
													listaCroquisBusIda.get(x).setPrecio(valor);
													log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
												}
											}										
										}
										
									}else{
										listaCroquisBusIda.get(x).setPrecio(bean.getPrecio1());
										int asi=x+1;																		
										if (lstPrexAsiento.size()>0) {
											for (int i = 0; i < lstPrexAsiento.size(); i++) {
												log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
												if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
													Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
													listaCroquisBusIda.get(x).setPrecio(valor);
													log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
												}
											}										
										}
									}
									
								}else{
									
									listaCroquisBusIda.get(x).setPrecio(bean.getPrecio1());
									int asi=x+1;																		
									if (lstPrexAsiento.size()>0) {
										for (int i = 0; i < lstPrexAsiento.size(); i++) {
											log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
											if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
												Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
												listaCroquisBusIda.get(x).setPrecio(valor);
												log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
											}
										}										
									}
									
								}
								
							}else{
								
								if(usuario != null){
									
									if(beanclienteRuta != null){
										listaCroquisBusIda.get(x).setPrecio(beanclienteRuta.getPrecio2());
										int asi=x+1;																		
										if (lstPrexAsiento.size()>0) {
											for (int i = 0; i < lstPrexAsiento.size(); i++) {
												log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
												if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
													Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
													listaCroquisBusIda.get(x).setPrecio(valor);
													log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
												}
											}										
										}
										
									}else{
										listaCroquisBusIda.get(x).setPrecio(bean.getPrecio2());
										int asi=x+1;																		
										if (lstPrexAsiento.size()>0) {
											for (int i = 0; i < lstPrexAsiento.size(); i++) {
												log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
												if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
													Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
													listaCroquisBusIda.get(x).setPrecio(valor);
													log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
												}
											}										
										}
									}
									
								}else{
									listaCroquisBusIda.get(x).setPrecio(bean.getPrecio2());
									int asi=x+1;																		
									if (lstPrexAsiento.size()>0) {
										for (int i = 0; i < lstPrexAsiento.size(); i++) {
											log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
											if(asi==Integer.parseInt(lstPrexAsiento.get(i).get("Asiento").toString())) {												
												Float valor=Float.parseFloat(lstPrexAsiento.get(i).get("Preciodesde").toString());
												listaCroquisBusIda.get(x).setPrecio(valor);
												log.info("Asiento>=1920:"+lstPrexAsiento.get(i).get("Asiento"));
											}
										}										
									}
									
								}
							}
					    }
					}
				}
			}
			
			List<B_VentaBean> lstAsientosOcupados = serviceCroquis.listaAsientosOcupadosPorProgramacionSalida(paso2FormIDA.getNroProgramacion());
			
			
			if(usuario!=null){
				if(usuario.getNivel().trim().equals("C")){
					
					//cantidadAsientosOcupados = serviceventa.cuentaAsientosOcupadosPorVenta(paso2FormIDA.getNroProgramacion()); //lstAsientosOcupados.size();
					
					listaAsientoOcupadoAgencia = Utils.getListaVentasOcupadoXAgencias(paso2FormIDA.getNroProgramacion());
					
					
					for (V_AgenciasBean bean : listaAsientoOcupadoAgencia) {
						
						cantidadAsientosOcupados += bean.getCantidad();
					}
					
					listaAsientoReservadoAgencia = Utils.getListaVentasReservadoXAgencias(paso2FormIDA.getNroProgramacion());
					
					for (V_AgenciasBean bean : listaAsientoReservadoAgencia) { 
						cantidadAsientosReservados += bean.getCantidad();
					}
					
					paso2FormIDA.setCantidadAsientosOcupados(cantidadAsientosOcupados);
					paso2FormIDA.setCantidadAsientosReservados(cantidadAsientosReservados);
					
					// TODO: aqui se agregara lo faltante de comida
					//String x = servbicevarios.metodo(paso2FormIDA.getNroProgramacion());
					
					 mostrarComida = servicevarios.Select_Varios().getMostrarComida();
					
					if(mostrarComida != null && !mostrarComida.trim().equals("")){
						
						if(mostrarComida.trim().equals("S")){
							
							listaComidas = (Utils.getListaComidas(paso2FormIDA.getNroProgramacion()) == null ? new ArrayList<V_AgenciasBean>() : Utils.getListaComidas(paso2FormIDA.getNroProgramacion()));
							paso2FormIDA.setMostrarComida(true); 
						}else{
							
							listaComidas = new ArrayList<V_AgenciasBean>();
							paso2FormIDA.setMostrarComida(false); 
						}
						
					}else{
						
						listaComidas = new ArrayList<V_AgenciasBean>();
						paso2FormIDA.setMostrarComida(false); 
					}
				}
			}
			
			
			for(V_CroquisBusBean cbb : listaCroquisBusIda){
				if(cbb.getAsiento().trim().equals("T1") || cbb.getAsiento().trim().equals("T2") ||
				   cbb.getAsiento().trim().equals("T3") || cbb.getAsiento().trim().equals("T4") ||
				   cbb.getAsiento().trim().equals("E") || cbb.getAsiento().trim().equals("B")){
					cbb.setVisible("false");
				}
			}
			
			for (B_VentaBean ventaAsiento : lstAsientosOcupados) {
				for (V_CroquisBusBean croquisAsiento : listaCroquisBusIda) {
					if(croquisAsiento.getAsiento().trim().equals(ventaAsiento.getAsiento().trim())){
						croquisAsiento.setVisible("false");
						break;
					}
				}
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		listaComboIdentidad.clear();
		listaComboIdentidad.addAll(Utils.getListaComboIdentidad());
		listaComboDestinoBajada.addAll(Utils.getListaComboDestinoBajada(paso1Form.getDestinoIda(),paso1Form.getDestinoDescripcion()));
		listaComoEmbarque.addAll(Utils.getListaComboEmbarque(paso2FormIDA.getNroProgramacion(),paso1Form.getOrigenIda().substring(0, 3)));
		
		//SESIONES AGREGADAS 2016-06-20 (CUANDO SE GENERA UN MENSAJE DE VALIDACION PARA IR DEL PASO 3 AL PASO CUATRO SE INICIALIZA TODO EL PASO 3)
		/*session.put("listaComboIdentidad", listaComboIdentidad); 
		session.put("listaComboDestinoBajada", listaComboDestinoBajada);
		session.put("listaComoEmbarque", listaComoEmbarque);*/
		//session.put("variavle", x);
		/////////////////////////////////////////////////////////////////////////
		session.put("paso2FormIDA", paso2FormIDA);
		session.put("listaCroquisBusIda", listaCroquisBusIda);
		session.put("minValorIda", minValorIda);
		/*session.put("cantidadAsientosOcupados", cantidadAsientosOcupados);
		session.put("cantidadAsientosReservados", cantidadAsientosReservados);*/
		
	}
	
	
	public VentaPaso2Form getPaso2FormIDA() {
		return paso2FormIDA;
	}
	
	public void setPaso2FormIDA(VentaPaso2Form paso2FormIDA) {
		this.paso2FormIDA = paso2FormIDA;
	}
	
	public VentaPaso2Form getPaso2FormVUELTA() {
		return paso2FormVUELTA;
	}
	
	public void setPaso2FormVUELTA(VentaPaso2Form paso2FormVUELTA) {
		this.paso2FormVUELTA = paso2FormVUELTA;
	}
	
	public List<ComboIdentidad> getListaComboIdentidad() {
		return listaComboIdentidad;
	}
	
	public void setListaComboIdentidad(List<ComboIdentidad> listaComboIdentidad) {
		this.listaComboIdentidad = listaComboIdentidad;
	}
	
	public List<V_RutasBean> getListaComboDestinoBajada() {
		return listaComboDestinoBajada;
	}
	
	public void setListaComboDestinoBajada(List<V_RutasBean> listaComboDestinoBajada) {
		this.listaComboDestinoBajada = listaComboDestinoBajada;
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

	public Map<String, Object> getMapaJSONResultadoIDA() {
		return mapaJSONResultadoIDA;
	}
	public void setMapaJSONResultadoIDA(Map<String, Object> mapaJSONResultadoIDA) {
		this.mapaJSONResultadoIDA = mapaJSONResultadoIDA;
	}
	
	public Map<String, Object> getMapaJSONResultadoVUELTA() {
		return mapaJSONResultadoVUELTA;
	}
	
	public void setMinValor(int minValor) {
		this.minValorIda = minValor;
	}
	public int getMinValor() {
		return minValorIda;
	}
	public void setPrecio(double precio) {
		Precio = precio;
	}
	public double getPrecio() {
		return Precio;
	}
	public void setTop_yAsiento(String top_yAsiento) {
		this.top_yAsiento = top_yAsiento;
	}
	public String getTop_yAsiento() {
		return top_yAsiento;
	}
	public void setNroAsiento(String nroAsiento) {
		this.nroAsiento = nroAsiento;
	}
	
	public String getNroAsiento() {
		return nroAsiento;
	}
	public void setListaAsientoOcupadoAgencia(List<V_AgenciasBean> listaAsientoOcupadoAgencia) {
		this.listaAsientoOcupadoAgencia = listaAsientoOcupadoAgencia;
	}
	public List<V_AgenciasBean> getListaAsientoOcupadoAgencia() {
		return listaAsientoOcupadoAgencia;
	}
	public void setListaAsientoReservadoAgencia(List<V_AgenciasBean> listaAsientoReservadoAgencia) {
		this.listaAsientoReservadoAgencia = listaAsientoReservadoAgencia;
	}
	public List<V_AgenciasBean> getListaAsientoReservadoAgencia() {
		return listaAsientoReservadoAgencia;
	}
	public void setListaComidas(List<V_AgenciasBean> listaComidas) {
		this.listaComidas = listaComidas;
	}
	public List<V_AgenciasBean> getListaComidas() {
		return listaComidas;
	}
	
	public void setMapaJSONResultadoVUELTA(Map<String, Object> mapaJSONResultadoVUELTA) {
		this.mapaJSONResultadoVUELTA = mapaJSONResultadoVUELTA;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
