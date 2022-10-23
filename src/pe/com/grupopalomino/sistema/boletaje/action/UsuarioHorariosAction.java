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
import pe.com.grupopalomino.sistema.boletaje.bean.V_UsuarioHorariosBean;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosHorariosService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosHorariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1')")
public class UsuarioHorariosAction extends ActionSupport implements ServletRequestAware {
	
	
	private HttpServletRequest request;
	private int limit, offset, total;
	private String query;
	private V_UsuarioHorariosBean usuariohorarios;
	private Map<String, Object> resultados;
	private List<V_UsuarioHorariosBean> listaUsuarioHorarios = new ArrayList<V_UsuarioHorariosBean>();
	private UsuariosHorariosService service = new UsuariosHorariosServiceI(); 
	private static final Log log = LogFactory.getLog(UsuarioHorariosAction.class);
	private List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	
	@Action(value = "x", results = {@Result(name = SUCCESS, location = "sistema/usuarioshorarios/nuevousuariohorarios.jsp")})
	public String accedeConsultaPagoEfectivo(){
		
		return SUCCESS;
	}
	
	@Action(value = "accedelistausuarioshorarios", results = {@Result(name = SUCCESS, location = "sistema/usuarioshorarios/listausuariohorarios.jsp")})
	public String accedelistausuarioshorarios(){
		
		
		try {
			
			listaUsuarios = usuarioService.listaUsuarios();
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "listausuariohorarios", results = {@Result(name = SUCCESS, type = "json")})
	public String listaUsuarios(){ 
		try {
			resultados = new HashMap<String, Object>();
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			List<V_UsuarioHorariosBean> listaclienteruta = service.listaUsuarios(query, limit, offset);
			
			for (V_UsuarioHorariosBean usuariohorario : listaclienteruta) {
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("Id", usuariohorario.getId());
				parametros.put("Usuario", usuariohorario.getUsuario());
				parametros.put("Lunes", usuariohorario.getLunesDesde());
				parametros.put("Martes", usuariohorario.getMartesDesde());
				parametros.put("Miercoles", usuariohorario.getMiercolesDesde());
				parametros.put("Jueves", usuariohorario.getJuevesDesde());
				parametros.put("Viernes", usuariohorario.getViernesDesde());
				parametros.put("Sabado", usuariohorario.getSabadoDesde());
				parametros.put("Domingo", usuariohorario.getDomingoDesde());
				rows.add(parametros);
			}
			
			total = service.UsuariosHorariosCount();
			
			resultados.put("rows", rows);
			resultados.put("total", total);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	
	
	@Action(value = "nuevousuariohorarios", results = {@Result(name = SUCCESS, location = "sistema/usuarioshorarios/resultado.jsp"),
	@Result(name = "ACCESO", location = "sistema/usuarioshorarios/nuevousuariohorarios.jsp"),
	@Result(name = ERROR, location = "sistema/usuarioshorarios/nuevousuariohorarios.jsp")})
	public String nuevousuariohorarios(){

		try {
		
			if(request.getMethod().trim().toUpperCase().equals("GET")){
				
				listaUsuarios = usuarioService.listaUsuarios();
			
			try {
			//listaClientes = usuarioService.listaUsuarios();
			//listaServicio =  servicioservice.ListarServicios();
			//listaRutas  =  rutaservice.ListaRutas();
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(Utils.printStackTraceToString(e));
			}
			
			return "ACCESO";
			
			}else if(request.getMethod().trim().toUpperCase().equals("POST")){
			
			int operacion = -1;
			
			operacion =  service.insertUsuariosHorarios(usuariohorarios);
			
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
	
	@Action(value = "actualizausuariohorarios", results = {@Result(name = SUCCESS, location = "sistema/usuarioshorarios/resultado.jsp"),
			   @Result(name = "ACCESO", location = "sistema/usuarioshorarios/actualizausuariohorarios.jsp"),
			   @Result(name = ERROR, location = "sistema/usuarioshorarios/actualizausuariohorarios.jsp")})
		public String actualizarUsuarioHorarios(){
		
		if(request.getMethod().trim().toUpperCase().equals("GET")){
		
		try {
		
			int id = Integer.parseInt(request.getParameter("Id"));
			usuariohorarios = service.listaUsuarioHorarioXid(id);
			
			SpringSecurityUser bean = new SpringSecurityUser();
			bean = usuarioService.limiteCreditoUsuario(usuariohorarios.getUsuario().trim());
			listaUsuarios.add(bean);
			
			} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
					return ERROR;
		    }
		
			return "ACCESO";
		}
		else if(request.getMethod().trim().toUpperCase().equals("POST")){
			try {
				
				
					int operacion= -1;
					
					operacion = service.updateUsuariosHorarios(usuariohorarios);
					
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

	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getOffset() {
		return offset;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getLimit() {
		return limit;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal() {
		return total;
	}
	public void setResultados(Map<String, Object> resultados) {
		this.resultados = resultados;
	}
	public Map<String, Object> getResultados() {
		return resultados;
	}
	
	public void setUsuariohorarios(V_UsuarioHorariosBean usuariohorarios) {
		this.usuariohorarios = usuariohorarios;
	}
	public V_UsuarioHorariosBean getUsuariohorarios() {
		return usuariohorarios;
	}
	public void setListaUsuarioHorarios(List<V_UsuarioHorariosBean> listaUsuarioHorarios) {
		this.listaUsuarioHorarios = listaUsuarioHorarios;
	}
	public List<V_UsuarioHorariosBean> getListaUsuarioHorarios() {
		return listaUsuarioHorarios;
	}
	
	public void setListaUsuarios(List<SpringSecurityUser> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public List<SpringSecurityUser> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return query;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		
	}

	

}
