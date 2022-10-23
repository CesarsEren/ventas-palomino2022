package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.context.SecurityContextHolder;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosAgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosClienteWebBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosAgenciasWebService;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosAgenciasWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosClienteWebService;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosClienteWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.ErrorValidacion;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.util.ValidaDatosPaso1Form;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
//@PreAuthorize("hasAnyRole('1','3','SMS','C')")
public class Paso1VentaAction extends ActionSupport implements SessionAware, ServletResponseAware {

	private Map<String, Object> session;
	private VentaPaso1Form paso1Form;
	private String searchOrigen = "", searchDestino = "",origenCiudad,destinoCiudad;
	private List<Map<String, Object>> listaOrigenCombo = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listaDestinoCombo = new ArrayList<Map<String, Object>>();
	private DestinosClienteWebService serviceCliente = new DestinosClienteWebServiceI();
	private DestinosAgenciasWebService destinoAgenciasService = new DestinosAgenciasWebServiceI();
	VentaBoletaService venta = new VentaBoletaServiceI();
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private List<V_Varios> listacantidadPasajeros = new ArrayList<V_Varios>();
	private static final Log log = LogFactory.getLog(Paso1VentaAction.class);
	private HttpServletResponse response;
	
	private void limpiaDatosVenta(){
		
		session.remove("listaOrigenCombo");
		session.remove("listaDestinoCombo");
		session.remove("paso1Form");
		session.remove("paso2FormIDA");
		session.remove("paso2FormVUELTA");
		session.remove("listaCroquisBusIda");
		session.remove("listaCroquisBusVuelta");
		session.remove("minValorIda");
		session.remove("minValorVuelta");
		session.remove("listaPasajeros");
		session.remove("listaPasajerosTabla");
		session.remove("listaPasajerosIdaVuelta");
		session.remove("ListaAuxiliar");
		session.remove("ListaPreventaIda");
		session.remove("ListaPreventaIdaVuelta");
		
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "ventapaso1", results = {@Result(name = SUCCESS, location = "ventaida/ventaboletos.jsp")})
	public String ventaPaso1(){
		
	//	SpringSecurityUser usuario = null;
		
		paso1Form = new VentaPaso1Form();
		
		try {
			
			List<ListaPreVenta> ListaPreventaIda = session.get("ListaPreventaIda") == null ? new ArrayList<ListaPreVenta>():(List<ListaPreVenta>)session.get("ListaPreventaIda"); 
			List<ListaPreVenta> ListaPreventaIdaVuelta = session.get("ListaPreventaIdaVuelta") == null ? new ArrayList<ListaPreVenta>(): (List<ListaPreVenta>) session.get("ListaPreventaIdaVuelta");
		
			for (ListaPreVenta preventa : ListaPreventaIda) {
				serviceventa.deleteVenta(preventa.getNro(),preventa.getSalida());
			}
			
			for (ListaPreVenta preventa : ListaPreventaIdaVuelta) {
				serviceventa.deleteVenta(preventa.getNro(),preventa.getSalida());
			}
			
			
			limpiaDatosVenta();
			
			if(session.get("listacantidadPasajeros")== null){
				
				listacantidadPasajeros = Utils.getListaCantidadPasajeros();
				session.put("listacantidadPasajeros", listacantidadPasajeros);
				
			}else{
				listacantidadPasajeros = (List<V_Varios>) session.get("listacantidadPasajeros"); 
				
			}
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/listacantidadpasajeros", results = { @Result(name = SUCCESS, type = "json")})
	public String listaCantidadPasajeros(){
		
		listacantidadPasajeros = Utils.getListaCantidadPasajeros();
		
		//TODO: CAMBIAR A http://www.grupopalomino.com.pe CUANDO SE PASE A PRODUCCION
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET");
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "venta", results = {@Result(name = SUCCESS, location = "ventaida/ventaboletos.jsp")})
	public String LimpiarDatosCaducida(){
			
		// TODO: AQUI SE EJECUTARA EL PROCESO DE ELIMINACION PARA LAS VENTAS NO CONFIRMADAS (TIEMPO CULMINADO DEL TIMER)
		
		
		try {
			
			List<ListaPreVenta> ListaPreventaIda = session.get("ListaPreventaIda") == null ? new ArrayList<ListaPreVenta>():(List<ListaPreVenta>)session.get("ListaPreventaIda"); 
			List<ListaPreVenta> ListaPreventaIdaVuelta = session.get("ListaPreventaIdaVuelta") == null ? new ArrayList<ListaPreVenta>(): (List<ListaPreVenta>) session.get("ListaPreventaIdaVuelta");
			
			for (ListaPreVenta preventa : ListaPreventaIda) {
				serviceventa.deleteVenta(preventa.getNro(),preventa.getSalida());
			}
			
			for (ListaPreVenta preventa : ListaPreventaIdaVuelta) {
				serviceventa.deleteVenta(preventa.getNro(),preventa.getSalida());
			}
			
			limpiaDatosVenta();
			
			if(session.get("listacantidadPasajeros")== null){
				
				listacantidadPasajeros = Utils.getListaCantidadPasajeros();
				session.put("listacantidadPasajeros", listacantidadPasajeros);
				
			}else{
				listacantidadPasajeros = (List<V_Varios>) session.get("listacantidadPasajeros"); 
				
			}
			
			venta.updateVentaNoConfirmada("E");
			venta.deleteVentaNoConfirmada("E");
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	} 
	
	@Action(value = "/destinoscombo", results = { @Result(name = "success", type = "json") })
	public String listaOrigenCombo() {
		
		try {
		
			listaOrigenCombo.clear();
			
			/************************************************************* CARGAMOS LOS ORIGENES ***************************************************************************/
			
			if(SecurityContextHolder.getContext().getAuthentication().getName()!=null){
				
				if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
					
					if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
						
						SpringSecurityUser usuario = null;
						
						usuario =  (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
						
						List<V_DestinosClienteWebBean> beanCliente = new ArrayList<>();
						
						beanCliente = serviceCliente.obtieneOrigenClientes(usuario.getRuc());
						
						if(beanCliente.size()>0){
							
									for (V_DestinosClienteWebBean bean : beanCliente) {
										Map<String, Object> mapaJSONResultadoOrigen  = new HashMap<>();
										mapaJSONResultadoOrigen.put("id", bean.getOrigen());
										mapaJSONResultadoOrigen.put("text", bean.getOrigenD());
										listaOrigenCombo.add(mapaJSONResultadoOrigen);
									}
									
									for (V_DestinosClienteWebBean bean : beanCliente) {
										Map<String, Object> mapaJSONResultadoDestino  = new HashMap<>();
										mapaJSONResultadoDestino.put("id", bean.getOrigen());
										mapaJSONResultadoDestino.put("text", bean.getOrigenD());
										listaDestinoCombo.add(mapaJSONResultadoDestino);
									}
									
										return SUCCESS;
						}
					}
				}
			}
			
			List<V_DestinosAgenciasWebBean> dataOrigen = destinoAgenciasService.ListaDestinosAgencias();
			
			for (V_DestinosAgenciasWebBean bean : dataOrigen) {
					if(bean.getOrigen().trim().substring(0, 3).equals(origenCiudad)){
						Map<String, Object> mapaJSONResultadoOrigen  = new HashMap<>();
						mapaJSONResultadoOrigen.put("id", bean.getOrigen());
						mapaJSONResultadoOrigen.put("text", bean.getOrigenD());
						listaOrigenCombo.add(mapaJSONResultadoOrigen);
					}
				}
			
			for (V_DestinosAgenciasWebBean bean : dataOrigen) {
				if(!bean.getOrigen().trim().substring(0, 3).equals(origenCiudad)){
					Map<String, Object> mapaJSONResultadoOrigen  = new HashMap<>();
					mapaJSONResultadoOrigen.put("id", bean.getOrigen());
					mapaJSONResultadoOrigen.put("text", bean.getOrigenD());
					listaOrigenCombo.add(mapaJSONResultadoOrigen);
				}
			}
			
			//********************************************************************************************
			
			for (V_DestinosAgenciasWebBean bean : dataOrigen){
				if(bean.getOrigen().length()== 3 && bean.getOrigen().trim().equals(destinoCiudad)){
						Map<String, Object> mapaJSONResultadoDestino  = new HashMap<>();
						mapaJSONResultadoDestino.put("id", bean.getOrigen());
						mapaJSONResultadoDestino.put("text", bean.getCiudadD());
						listaDestinoCombo.add(mapaJSONResultadoDestino);
						break;
				}
			}
			for (V_DestinosAgenciasWebBean bean : dataOrigen){
				if(bean.getOrigen().length()== 3 && !bean.getOrigen().trim().equals(destinoCiudad)){
					Map<String, Object> mapaJSONResultadoDestino  = new HashMap<>();
					mapaJSONResultadoDestino.put("id", bean.getOrigen());
					mapaJSONResultadoDestino.put("text", bean.getCiudadD());
					listaDestinoCombo.add(mapaJSONResultadoDestino);
				}
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		//TODO: CAMBIAR A http://www.grupopalomino.com.pe CUANDO SE PASE A PRODUCCION
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		//response.addHeader("Access-Control-Allow-Methods", "GET");
		
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "paso2", results = {@Result(name = SUCCESS, location = "ventaida/ventaboletospaso2.jsp"), 
										@Result(name = ERROR, location = "ventaida/ventaboletos.jsp"),
										@Result(name = "REDIRECCIONPASO1", type="redirectAction", location="ventapaso1")})
	public String irAPaso2() throws Exception{
		
		SpringSecurityUser usuario =  null;
		
		if(session.get("paso1Form") != null){
			paso1Form = (VentaPaso1Form) session.get("paso1Form");
		}
		
		if(paso1Form == null){
			
			return "REDIRECCIONPASO1";
		}
		
		if(SecurityContextHolder.getContext().getAuthentication().getName()!=null){
			
			if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
				
				if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
					usuario =  (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					paso1Form.setRolUser(usuario.getNivel());
				}
				if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityClient){
					
					SpringSecurityClient client =  null;
					client =  (SpringSecurityClient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					paso1Form.setRolUser(client.getNivel());
				}
				
			}else{
				paso1Form.setRolUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			}
		}
		
		ValidaDatosPaso1Form validador = new ValidaDatosPaso1Form(paso1Form);
		ErrorValidacion error = validador.validaPaso1Form();
		
		if(error.getError()){
			
			for (String mensajeError : error.getMensajeError()) {
				addActionError(mensajeError);
			}
			
			listacantidadPasajeros = (session.get("listacantidadPasajeros")==null)? new ArrayList<>(): (List<V_Varios>) session.get("listacantidadPasajeros");
			return ERROR;
			
		}
		
		session.put("paso1Form", paso1Form);		
		return SUCCESS;
		
	}
	
	public VentaPaso1Form getPaso1Form() {
		return paso1Form;
	}
	
	public void setPaso1Form(VentaPaso1Form paso1Form) {
		this.paso1Form = paso1Form;
	}
	
	public String getSearchDestino() {
		return searchDestino;
	}
	
	public void setSearchDestino(String searchDestino) {
		this.searchDestino = searchDestino;
	}
	
	public String getSearchOrigen() {
		return searchOrigen;
	}
	
	public void setSearchOrigen(String searchOrigen) {
		this.searchOrigen = searchOrigen;
	}
	
	public List<Map<String, Object>> getListaOrigenCombo() {
		return listaOrigenCombo;
	}
	
	public void setListaOrigenCombo(List<Map<String, Object>> listaOrigenCombo) {
		this.listaOrigenCombo = listaOrigenCombo;
	}
	
	public List<Map<String, Object>> getListaDestinoCombo() {
		return listaDestinoCombo;
	}
	
	public void setListaDestinoCombo(List<Map<String, Object>> listaDestinoCombo) {
		this.listaDestinoCombo = listaDestinoCombo;
	}
	public void setListacantidadPasajeros(List<V_Varios> listacantidadPasajeros) {
		this.listacantidadPasajeros = listacantidadPasajeros;
	}
	public List<V_Varios> getListacantidadPasajeros() {
		return listacantidadPasajeros;
	}
	public void setOrigenCiudad(String origenCiudad) {
		this.origenCiudad = origenCiudad;
	}
	public String getOrigenCiudad() {
		return origenCiudad;
	}
	public void setDestinoCiudad(String destinoCiudad) {
		this.destinoCiudad = destinoCiudad;
	}
	public String getDestinoCiudad() {
		return destinoCiudad;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
