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
import org.springframework.security.access.prepost.PreAuthorize;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Clientes_RutaPrecioBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ServiciosBean;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesRutaPrecioService;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesRutaPrecioServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.RutasService;
import pe.com.grupopalomino.sistema.boletaje.service.RutasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ServiciosService;
import pe.com.grupopalomino.sistema.boletaje.service.ServiciosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;


@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1')")
public class ClienteRutasAction extends ActionSupport implements ServletRequestAware{
	
	private static final Log log = LogFactory.getLog(UsuarioAction.class);
	private V_Clientes_RutaPrecioBean clienteruta;
	private ClientesRutaPrecioService serviceclienteruta  = new ClientesRutaPrecioServiceI(); 
	private Map<String, Object> resultados;
	private String query,searchClientes,searchRutas,searchServicio;
	private HttpServletRequest request;
	private int limit, offset, total;
	private List<SpringSecurityUser> listaClientes = new ArrayList<SpringSecurityUser>();
	private List<V_RutasBean> listaRutas = new ArrayList<V_RutasBean>();
	private List<V_ServiciosBean> listaServicio = new ArrayList<V_ServiciosBean>();
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	private RutasService rutaservice =  new RutasServiceI();
	private ServiciosService servicioservice = new ServiciosServiceI();
	
	
	
	@Action(value = "nuevoclienteruta", results = {@Result(name = SUCCESS, location = "sistema/clientesrutas/resultado.jsp"),
												    @Result(name = "ACCESO", location = "sistema/clientesrutas/nuevoclienteruta.jsp"),
												    @Result(name = ERROR, location = "sistema/clientesrutas/nuevoclienteruta.jsp")})
	public String nuevoclienteruta(){
		
		try {
			
			if(request.getMethod().trim().toUpperCase().equals("GET")){
				
				try {
					
					listaClientes = usuarioService.listaUsuarios();
					listaServicio =  servicioservice.ListarServicios();
					listaRutas  =  rutaservice.ListaRutas();
					
				} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
				}
				
				return "ACCESO";
				
			}else if(request.getMethod().trim().toUpperCase().equals("POST")){
				
				int operacion = -1;
				
				operacion =  serviceclienteruta.insertRutaPrecio(clienteruta);
				
				if (operacion == -1){
					
					addActionError("Ocurrio un problema en el Registro de las Rutas del Cliente.");
					return ERROR;
					
				}
				
				
			}
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
		

	@Action(value = "actualizaclienteruta", results = {@Result(name = SUCCESS, location = "sistema/clientesrutas/resultado.jsp"),
			   @Result(name = "ACCESO", location = "sistema/clientesrutas/actualizaclienteruta.jsp"),
			   @Result(name = ERROR, location = "sistema/clientesrutas/actualizaclienteruta.jsp")})
		public String actualizarUsuario(){
		
		if(request.getMethod().trim().toUpperCase().equals("GET")){
		
		try {
		
			int id = Integer.parseInt(request.getParameter("Id"));
			clienteruta = serviceclienteruta.listaClientesRutas(id);
			listaUsuariosActualiza(id);
			
			} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
					return ERROR;
		    }
		
			return "ACCESO";
		}
		else if(request.getMethod().trim().toUpperCase().equals("POST")){
			try {
				
				
					int operacion= -1;
					
					operacion = serviceclienteruta.updateRutaPrecio(clienteruta);
					
					if(operacion == -1){
						
						addActionError("Ocurrio un problema al Actualizar los datos.");
						return ERROR;
					}
					
					if(operacion == 0){
						addActionError("No se actualizaron los datos del Cliente Ruta.");
						return ERROR;
					}
			
		
				} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			}
		}
		
		return SUCCESS;
	}
	@Action(value = "accedelistaclienteruta", results = {@Result(name = SUCCESS, location = "sistema/clientesrutas/listaclienteruta.jsp")})
	public String accedeConsultaPagoEfectivo(){
		
		return SUCCESS;
	}
	
	@Action(value = "listaclientesruta", results = {@Result(name = SUCCESS, type = "json")})
	public String listaUsuarios(){ 
		try {
			resultados = new HashMap<String, Object>();
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			List<V_Clientes_RutaPrecioBean> listaclienteruta = serviceclienteruta.listaClientesRutas(query, limit, offset);
			
			for (V_Clientes_RutaPrecioBean clienteruta : listaclienteruta) {
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("Cliente", clienteruta.getRuc());
				parametros.put("Id", clienteruta.getId());
				parametros.put("nroRuta", clienteruta.getNroRuta());
				parametros.put("Ruta", clienteruta.getDescripcionRuta());
				parametros.put("nroServicio", clienteruta.getNroServicio());
				parametros.put("Servicio", clienteruta.getDescripcionServicio());
				parametros.put("Precio1", clienteruta.getPrecio1());
				parametros.put("Precio2", clienteruta.getPrecio2());
				rows.add(parametros);
			}
			
			total = serviceclienteruta.cuentaClienteRutaPrecio();
			
			resultados.put("rows", rows);
			resultados.put("total", total);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	public void listaUsuariosActualiza(int Id){
		
		V_Clientes_RutaPrecioBean bean = new V_Clientes_RutaPrecioBean();
		
		try {
			
			List<SpringSecurityUser> Clientes = new ArrayList<SpringSecurityUser>();
			List<V_ServiciosBean> servicios = new ArrayList<V_ServiciosBean>();
			List<V_RutasBean> rutas = new ArrayList<V_RutasBean>();
			List<SpringSecurityUser> listaAuxiliarClientes = new ArrayList<SpringSecurityUser>();
			List<V_ServiciosBean> listaAuxiliarServicios = new ArrayList<V_ServiciosBean>();
			List<V_RutasBean> listaAuxiliarRutas = new ArrayList<V_RutasBean>();
			
			bean = serviceclienteruta.listaClientesRutas(Id);
			
			
			Clientes = usuarioService.listaUsuarios();
			servicios = servicioservice.ListarServicios();
			rutas = rutaservice.ListaRutas();
			
			for (SpringSecurityUser listaUser : Clientes) {
				
				if(listaUser.getRuc().trim().equals(bean.getRuc().trim())){
					
					if(listaAuxiliarClientes.size()==0){
						
						listaAuxiliarClientes.add(listaUser);	
					}
				}
			}
			
			for (SpringSecurityUser listaUser : Clientes) {
				
				if(!listaUser.getRuc().trim().equals(bean.getRuc().trim())){
				
					listaAuxiliarClientes.add(listaUser);
				}
				
			}
			
			/**************************************************************************/
			
			for (V_ServiciosBean listaServicio : servicios) {
				
				if(listaServicio.getCodigo().trim().equals(bean.getNroServicio().trim())){
					
					if(listaAuxiliarServicios.size()==0){
						
						listaAuxiliarServicios.add(listaServicio);	
					}
				}
			}
			
			for (V_ServiciosBean listaServicio : servicios) {
				
				if(!listaServicio.getCodigo().trim().equals(bean.getNroServicio().trim())){
				
					listaAuxiliarServicios.add(listaServicio);
				}
				
			}
			
			/**************************************************************************/
			
			for (V_RutasBean listarutas : rutas) {
				
				if(listarutas.getNro()== Integer.parseInt(bean.getNroServicio())){
					
					if(listaAuxiliarRutas.size()==0){
						
						listaAuxiliarRutas.add(listarutas);	
					}
				}
			}
			
			for (V_RutasBean listaServicio : rutas) {
				
				if(listaServicio.getNro()!= Integer.parseInt(bean.getNroServicio())){
				
					listaAuxiliarRutas.add(listaServicio);
				}
				
			}
			
			
			listaClientes = listaAuxiliarClientes;
			listaServicio = listaAuxiliarServicios;
			listaRutas = listaAuxiliarRutas;
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		
	}
	
	
	public void setClienteruta(V_Clientes_RutaPrecioBean clienteruta) {
		this.clienteruta = clienteruta;
	}
	public V_Clientes_RutaPrecioBean getClienteruta() {
		return clienteruta;
	}
	public void setResultados(Map<String, Object> resultados) {
		this.resultados = resultados;
	}
	public Map<String, Object> getResultados() {
		return resultados;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return query;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal() {
		return total;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getLimit() {
		return limit;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getOffset() {
		return offset;
	}
	
	public void setSearchClientes(String searchClientes) {
		this.searchClientes = searchClientes;
	}
	public String getSearchClientes() {
		return searchClientes;
	}
	public void setSearchRutas(String searchRutas) {
		this.searchRutas = searchRutas;
	}
	public String getSearchRutas() {
		return searchRutas;
	}
	public void setSearchServicio(String searchServicio) {
		this.searchServicio = searchServicio;
	}
	public String getSearchServicio() {
		return searchServicio;
	}
	
	public void setListaClientes(List<SpringSecurityUser> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public List<SpringSecurityUser> getListaClientes() {
		return listaClientes;
	}
	public void setListaRutas(List<V_RutasBean> listaRutas) {
		this.listaRutas = listaRutas;
	}
	public List<V_RutasBean> getListaRutas() {
		return listaRutas;
	}
	public void setListaServicio(List<V_ServiciosBean> listaServicio) {
		this.listaServicio = listaServicio;
	}
	public List<V_ServiciosBean> getListaServicio() {
		return listaServicio;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
}
