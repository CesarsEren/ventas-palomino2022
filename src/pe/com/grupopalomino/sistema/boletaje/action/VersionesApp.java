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
import pe.com.grupopalomino.sistema.boletaje.bean.B_VersionesAppBean;
import pe.com.grupopalomino.sistema.boletaje.service.VersionesAppService;
import pe.com.grupopalomino.sistema.boletaje.service.VersionesAppServiceI;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1')")
public class VersionesApp extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private static final Log log = LogFactory.getLog(VersionesApp.class);
	private String query;
	private int limit, offset, total;
	private B_VersionesAppBean version = new B_VersionesAppBean();
	private VersionesAppService serviceversion = new VersionesAppServiceI();
	private Map<String, Object> resultados;
	
	@Action(value = "accedelistaversionesapp",results = {@Result(name = SUCCESS,location = "sistema/versionesapp/listaversionapp.jsp")})
	public String paginaVersionesAPP(){
		
		return SUCCESS;
		
	}
	
	@Action(value = "listaversiones", results = {@Result(name = SUCCESS, type = "json")})
	public String listaversiones(){ 
		try {
			resultados = new HashMap<String, Object>();
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			List<B_VersionesAppBean> listaversiones = serviceversion.listaVersionesApp(offset, limit);
			
			for (B_VersionesAppBean versiones : listaversiones) {
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("id", versiones.getId());
				parametros.put("plataforma", versiones.getPlataforma());
				parametros.put("fecha", versiones.getFecha());
				parametros.put("version", versiones.getVersion_App());
				parametros.put("critico", versiones.getCritico());
				rows.add(parametros);
			}
			
			total = serviceversion.listaVersionesAppTotal();
			
			resultados.put("rows", rows);
			resultados.put("total", total);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "nuevoversionapp",results = {@Result(name = SUCCESS, location = "sistema/versionesapp/resultado.jsp"),
			   @Result(name = "ACCESO", location = "sistema/versionesapp/nuevoversionapp.jsp"),
			   @Result(name = ERROR, location = "sistema/versionesapp/nuevoversionapp.jsp")})
	public String nuevoversionAPP(){
		
		
		if(request.getMethod().trim().toUpperCase().equals("GET")){
			
			return "ACCESO";
		}
		
		else if(request.getMethod().trim().toUpperCase().equals("POST")){
			
			try {
				
				
				int operacion = -1;
				version.set_B_VersionesAppBean(version);
				
				operacion = serviceversion.insertVerionesApp(version);
				
				
				if(operacion == -1){
					
					log.info("Ocurrio un problema al Registar las versiones del App"); 
					
				}
						
				
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
			return SUCCESS;
		}
		
		return ERROR;
		
	}
	
	@Action(value = "eliminaversiones",results = {@Result (name = SUCCESS,location = "sistema/versionesapp/listaversionapp.jsp")})
	public String EliminarVersion(){
		
		
		try {
			
			int operacion = -1;
			
			operacion = serviceversion.EliminarVersionesApp(version.getId());
			
			if(operacion == -1){
				
				addActionError("Ocurrio un Problema al Eliminar la Versión."); 
				
			}
			
			return SUCCESS;
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}
	
	
	@Action(value = "actualizaversiones", results = {
			@Result(name = SUCCESS, location = "sistema/versionesapp/resultado.jsp"),
			@Result(name = "ACCESO", location = "sistema/versionesapp/actualizaversionapp.jsp"),
			@Result(name = ERROR, location = "sistema/versionesapp/actualizaversionapp.jsp")})
	public String actualizarAgencia() {

		if (request.getMethod().trim().toUpperCase().equals("GET")) {

			try {

				int id = Integer.parseInt(request.getParameter("id"));
				version = serviceversion.selectVersionesApp(id);  
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
				return ERROR;
			}

			return "ACCESO";
		} else if (request.getMethod().trim().toUpperCase().equals("POST")) {
			try {
				
				int operacion = -1;
				
				version.setFecha(Utils.FormatoFechaPagosIso(version.getFecha()));
				
				operacion =  serviceversion.ActualizaVersionesApp(version);
				
				if(operacion == -1){
					
					addActionError("Ha ocurrido un problema con la actualizacion de las Versiones del App.");
					return ERROR;
				}
				 
				
				
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
		}

		return SUCCESS;
	}
	
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
		
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return query;
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
	
	public void setVersion(B_VersionesAppBean version) {
		this.version = version;
	}
	public B_VersionesAppBean getVersion() {
		return version;
	}
	
	
	

}
