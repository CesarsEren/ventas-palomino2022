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
import pe.com.grupopalomino.sistema.boletaje.bean.V_ProgramacionPagosBean;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionPagoService;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionPagoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;



@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1')")
public class ProgramacionPagosAction extends ActionSupport implements ServletRequestAware  {
	
	private List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	private V_ProgramacionPagosBean programacionpagos = new V_ProgramacionPagosBean();
	private static final Log log = LogFactory.getLog(ProgramacionPagosAction.class);
	private ProgramacionPagoService service = new ProgramacionPagoServiceI();
	private HttpServletRequest request;
	private Map<String, Object> resultados;
	private int limit, offset, total;
	private String query;
	
	
	@Action(value = "accedelistaprogramacionpagos", results = {@Result(name = SUCCESS, location = "sistema/programacionpagos/listaprogramacionpagos.jsp")})
	public String accedelistausuarioshorarios(){
		
		
		try {
			
			listaUsuarios = usuarioService.listaUsuariosProgramacionPago();
			
		} catch (Exception e) {
			//log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "listaprogramacionpagos", results = {@Result(name = SUCCESS, type = "json")})
	public String listaUsuarios(){ 
		try {
			resultados = new HashMap<String, Object>();
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			List<V_ProgramacionPagosBean> listaclienteruta = service.listaProgramacionPagos(query, limit, offset);
			
			for (V_ProgramacionPagosBean usuariohorario : listaclienteruta) {
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("Id", usuariohorario.getId());
				parametros.put("Usuario", usuariohorario.getUsuario());
				parametros.put("Periodo", usuariohorario.getPeriodo());
				parametros.put("Enero",  Utils.FormatoFechaPagosEstandar(usuariohorario.getEnero()));
				parametros.put("Febrero", Utils.FormatoFechaPagosEstandar(usuariohorario.getFebrero()));
				parametros.put("Marzo", Utils.FormatoFechaPagosEstandar(usuariohorario.getMarzo()));
				parametros.put("Abril", Utils.FormatoFechaPagosEstandar(usuariohorario.getAbril()));
				parametros.put("Mayo", Utils.FormatoFechaPagosEstandar(usuariohorario.getMayo()));
				parametros.put("Junio", Utils.FormatoFechaPagosEstandar(usuariohorario.getJunio()));
				parametros.put("Julio", Utils.FormatoFechaPagosEstandar(usuariohorario.getJulio()));
				parametros.put("Agosto", Utils.FormatoFechaPagosEstandar(usuariohorario.getAgosto()));
				parametros.put("Septiembre", Utils.FormatoFechaPagosEstandar(usuariohorario.getSeptiembre()));
				parametros.put("Octubre", Utils.FormatoFechaPagosEstandar(usuariohorario.getOctubre()));
				parametros.put("Noviembre", Utils.FormatoFechaPagosEstandar(usuariohorario.getNoviembre()));
				parametros.put("Diciembre", Utils.FormatoFechaPagosEstandar(usuariohorario.getDiciembre()));
				rows.add(parametros);
			}
			
			total = service.V_ProgramacionPagosCount();
			
			resultados.put("rows", rows);
			resultados.put("total", total);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "nuevoprogramacionpagos", results = {@Result(name = SUCCESS, location = "sistema/programacionpagos/resultado.jsp"),
			@Result(name = "ACCESO", location = "sistema/programacionpagos/nuevoprogramacionpagos.jsp"),
			@Result(name = ERROR, location = "sistema/programacionpagos/nuevoprogramacionpagos.jsp")})
			public String nuevousuariohorarios(){

				try {
				
					if(request.getMethod().trim().toUpperCase().equals("GET")){
						
						listaUsuarios = usuarioService.listaUsuariosProgramacionPago();
					
					try {
					
					
					} catch (Exception e) {
						log.info(Utils.printStackTraceToString(e));
					}
					
					return "ACCESO";
					
					}else if(request.getMethod().trim().toUpperCase().equals("POST")){
					
					int operacion = -1;
					
					V_ProgramacionPagosBean bean = new V_ProgramacionPagosBean();
					
					bean = service.listaProgramacionPagos(programacionpagos.getUsuario(), programacionpagos.getPeriodo());
					
					if(bean!=null){
						listaUsuarios = usuarioService.listaUsuariosProgramacionPago();
						
						addActionError("El Cliente "+bean.getUsuario()+ " ya tiene una Programacion de Pagos en ese Periodo.");
						return ERROR;	
						
					}
					
					
					V_ProgramacionPagosBean beanformato = new V_ProgramacionPagosBean();
					
					beanformato.setPeriodo(programacionpagos.getPeriodo());
					beanformato.setUsuario(programacionpagos.getUsuario());
					beanformato.setEnero(Utils.FormatoFechaPagosIso(programacionpagos.getEnero()));
					beanformato.setFebrero(Utils.FormatoFechaPagosIso(programacionpagos.getFebrero()));
					beanformato.setMarzo(Utils.FormatoFechaPagosIso(programacionpagos.getMarzo()));
					beanformato.setAbril(Utils.FormatoFechaPagosIso(programacionpagos.getAbril()));
					beanformato.setMayo(Utils.FormatoFechaPagosIso(programacionpagos.getMayo()));
					beanformato.setJunio(Utils.FormatoFechaPagosIso(programacionpagos.getJunio()));
					beanformato.setJulio(Utils.FormatoFechaPagosIso(programacionpagos.getJulio()));
					beanformato.setAgosto(Utils.FormatoFechaPagosIso(programacionpagos.getAgosto()));
					beanformato.setSeptiembre(Utils.FormatoFechaPagosIso(programacionpagos.getSeptiembre()));
					beanformato.setOctubre(Utils.FormatoFechaPagosIso(programacionpagos.getOctubre()));
					beanformato.setNoviembre(Utils.FormatoFechaPagosIso(programacionpagos.getNoviembre()));
					beanformato.setDiciembre(Utils.FormatoFechaPagosIso(programacionpagos.getDiciembre()));
					
					operacion =  service.insertProgramacionPago(beanformato);
					
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
	
	@Action(value = "actualizaprogramacionpagos", results = {@Result(name = SUCCESS, location = "sistema/programacionpagos/resultado.jsp"),
			   @Result(name = "ACCESO", location = "sistema/programacionpagos/actualizaprogramacionpagos.jsp"),
			   @Result(name = ERROR, location = "sistema/programacionpagos/actualizaprogramacionpagos.jsp")})
		public String actualizarUsuarioHorarios(){
		
		if(request.getMethod().trim().toUpperCase().equals("GET")){
		
		try {
		
			int id = Integer.parseInt(request.getParameter("Id"));
			programacionpagos = service.listaProgramacionPagosXid(id);
			
			programacionpagos.setPeriodo(programacionpagos.getPeriodo());
			programacionpagos.setUsuario(programacionpagos.getUsuario()); 
			programacionpagos.setEnero(Utils.FormatoFechaPagosEstandar(programacionpagos.getEnero()));
			programacionpagos.setFebrero(Utils.FormatoFechaPagosEstandar(programacionpagos.getFebrero()));
			programacionpagos.setMarzo(Utils.FormatoFechaPagosEstandar(programacionpagos.getMarzo()));
			programacionpagos.setAbril(Utils.FormatoFechaPagosEstandar(programacionpagos.getAbril()));
			programacionpagos.setMayo(Utils.FormatoFechaPagosEstandar(programacionpagos.getMayo()));
			programacionpagos.setJunio(Utils.FormatoFechaPagosEstandar(programacionpagos.getJunio()));
			programacionpagos.setJulio(Utils.FormatoFechaPagosEstandar(programacionpagos.getJulio()));
			programacionpagos.setAgosto(Utils.FormatoFechaPagosEstandar(programacionpagos.getAgosto()));
			programacionpagos.setSeptiembre(Utils.FormatoFechaPagosEstandar(programacionpagos.getSeptiembre()));
			programacionpagos.setOctubre(Utils.FormatoFechaPagosEstandar(programacionpagos.getOctubre()));
			programacionpagos.setNoviembre(Utils.FormatoFechaPagosEstandar(programacionpagos.getNoviembre()));
			programacionpagos.setDiciembre(Utils.FormatoFechaPagosEstandar(programacionpagos.getDiciembre()));
			
			SpringSecurityUser bean = new SpringSecurityUser();
			
			
			bean.setRuc(programacionpagos.getUsuario());
			bean.setRazonSocial(programacionpagos.getRazonSocial());
			
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
					
					
					V_ProgramacionPagosBean beanformato = new V_ProgramacionPagosBean();
					
					beanformato.setPeriodo(programacionpagos.getPeriodo());
					beanformato.setUsuario(programacionpagos.getUsuario());
					beanformato.setEnero(Utils.FormatoFechaPagosIso(programacionpagos.getEnero()));
					beanformato.setFebrero(Utils.FormatoFechaPagosIso(programacionpagos.getFebrero()));
					beanformato.setMarzo(Utils.FormatoFechaPagosIso(programacionpagos.getMarzo()));
					beanformato.setAbril(Utils.FormatoFechaPagosIso(programacionpagos.getAbril()));
					beanformato.setMayo(Utils.FormatoFechaPagosIso(programacionpagos.getMayo()));
					beanformato.setJunio(Utils.FormatoFechaPagosIso(programacionpagos.getJunio()));
					beanformato.setJulio(Utils.FormatoFechaPagosIso(programacionpagos.getJulio()));
					beanformato.setAgosto(Utils.FormatoFechaPagosIso(programacionpagos.getAgosto()));
					beanformato.setSeptiembre(Utils.FormatoFechaPagosIso(programacionpagos.getSeptiembre()));
					beanformato.setOctubre(Utils.FormatoFechaPagosIso(programacionpagos.getOctubre()));
					beanformato.setNoviembre(Utils.FormatoFechaPagosIso(programacionpagos.getNoviembre()));
					beanformato.setDiciembre(Utils.FormatoFechaPagosIso(programacionpagos.getDiciembre()));
					
					operacion = service.updateProgramacionPago(beanformato);
					
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
	
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return query;
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
	
	public void setListaUsuarios(List<SpringSecurityUser> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public List<SpringSecurityUser> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public void setProgramacionpagos(V_ProgramacionPagosBean programacionpagos) {
		this.programacionpagos = programacionpagos;
	}
	public V_ProgramacionPagosBean getProgramacionpagos() {
		return programacionpagos;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		
	}
	
	
	public static void main(String[] args) {
			
		System.out.println(Utils.FormatoFechaPagosIso("11/10/2016,15/11/2016,30/11/2016"));
		System.out.println(Utils.FormatoFechaPagosEstandar("2016-11-10,2016-11-15,2016-11-30"));
		
	}

}
