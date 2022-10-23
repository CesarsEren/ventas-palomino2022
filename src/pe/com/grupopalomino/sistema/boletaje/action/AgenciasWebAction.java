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

import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasWebService;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1')")
public class AgenciasWebAction extends ActionSupport implements ServletRequestAware{
	
	private String query;
	private int limit, offset, total;
	private V_AgenciasWebBean agencia = new V_AgenciasWebBean();
	private Map<String, Object> resultados;
	private HttpServletRequest request;
	private AgenciasWebService agenciasWebService = new AgenciasWebServiceI();
	private static final Log log = LogFactory.getLog(AgenciasWebAction.class);

	@Action(value = "accedelistaagencias", results = {@Result(name = SUCCESS, location = "sistema/agencias/listaagencias.jsp")})
	public String accedeListaUsuarios(){
		
		return SUCCESS;
	}
	
	@Action(value = "listaagencias", results = {@Result(name = SUCCESS, type = "json")})
	public String listaUsuarios(){ 
		try {
			resultados = new HashMap<String, Object>();
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			List<V_AgenciasWebBean> listaAgencias = agenciasWebService.listaAgenciasWeb(offset, limit, query);
			
			for (V_AgenciasWebBean agencia : listaAgencias) {
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("id", agencia.getId());
				parametros.put("razonSocial", agencia.getRazonSocial());
				parametros.put("ruc", agencia.getRuc());
				parametros.put("correo", agencia.getCorreo());
				parametros.put("telefono", agencia.getTelefono());
				parametros.put("limiteCredito", agencia.getLimiteCredito());
				parametros.put("estado", agencia.getEstado());
				rows.add(parametros);
			}
			
			total = agenciasWebService.cuentaAgenciasTotales();
			
			resultados.put("rows", rows);
			resultados.put("total", total);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	

	@Action(value = "nuevaagencia", results = {@Result(name = SUCCESS, location = "sistema/agencias/resultado.jsp"),
											   @Result(name = "ACCESO", location = "sistema/agencias/nuevaagencia.jsp"),
											   @Result(name = ERROR, location = "sistema/agencias/nuevaagencia.jsp")})
	public String nuevoUsuario(){
		
		if(request.getMethod().trim().toUpperCase().equals("GET")){
			
			return "ACCESO";
		}
		else if(request.getMethod().trim().toUpperCase().equals("POST")){
			
			try {
				agencia.setValuesAgencias(agencia);
				agenciasWebService.nuevaAgencia(agencia);
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
			
			return SUCCESS;
		}
		
		return ERROR;
	}
	

	@Action(value = "actualizaagencia", results = {@Result(name = SUCCESS, location = "sistema/agencias/resultado.jsp"),
												   @Result(name = "ACCESO", location = "sistema/agencias/actualizaagencia.jsp"),
												   @Result(name = ERROR, location = "sistema/agencias/actualizaagencia.jsp")})
	public String actualizarAgencia(){
		
		if(request.getMethod().trim().toUpperCase().equals("GET")){
			
			try {
				
				int id = Integer.parseInt(request.getParameter("id"));
				agencia = agenciasWebService.obtieneAgenciaXid(id);
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
				return ERROR;
			}
			
			return "ACCESO";
		}
		else if(request.getMethod().trim().toUpperCase().equals("POST")){
			try {
				agencia.setValuesAgencias(agencia);
				agenciasWebService.actualizarAgencia(agencia);
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
		}
		
		return SUCCESS;
	}
	

	@Action(value = "veragencia", results = {@Result(name = SUCCESS, location = "sistema/agencias/veragencia.jsp"),
											 @Result(name = ERROR, location = "sistema/agencias/listaagencias.jsp")})
	public String obtenerAgencia(){
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			agencia = agenciasWebService.obtieneAgenciaXid(id);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	@Action(value = "cambiaestadoagencia", results = {@Result(name = SUCCESS, location = "sistema/agencias/listaagencias.jsp")})
	public String cambiaEstadoAgencia(){
		
		try {
			agenciasWebService.cambiaEstadoAgencia(agencia);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}

	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public V_AgenciasWebBean getAgencia() {
		return agencia;
	}
	public void setAgencia(V_AgenciasWebBean agencia) {
		this.agencia = agencia;
	}
	public Map<String, Object> getResultados() {
		return resultados;
	}
	public void setResultados(Map<String, Object> resultados) {
		this.resultados = resultados;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request; 
	}
	
}
