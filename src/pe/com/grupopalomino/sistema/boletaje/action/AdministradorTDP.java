package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.access.prepost.PreAuthorize;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import pe.com.grupopalomino.sistema.boletaje.bean.V_CiudadesBean;
import pe.com.grupopalomino.sistema.boletaje.service.CiudadesService;
import pe.com.grupopalomino.sistema.boletaje.service.CiudadesServiceI;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('T')")
public class AdministradorTDP extends ActionSupport implements SessionAware, ServletResponseAware{
 
	//Variables
	private String query;
	private int limit, offset, total;
	private Map<String, Object> resultados = new HashMap<>();
	//MODELS 
	V_CiudadesBean ciudad = new V_CiudadesBean();
	 
	//SERVICE
	private CiudadesService ciudadesservice= new CiudadesServiceI();
	
	HttpServletResponse response;
	Map<String, Object> sesion;
	
	private static final Logger log = Logger.getLogger(AdministradorTDP.class);
	
	@Action(value = "/rutastdp", results = {
			@Result(name = SUCCESS, location = "TransporteDePersonal/config/rutastdp.jsp") })
	public String GorutasTDP() {
		return SUCCESS;
	}
	
	@Action(value = "/programaciontdp", results = {
			@Result(name = SUCCESS, location = "TransporteDePersonal/config/programaciones/programaciontsalidadp.jsp") })
	public String GoprogramacionTDP() {
		log.info("ingreso aqui");
		return SUCCESS;
	}
	
	@Action(value = "/registroprogramacion", results = {
			@Result(name = SUCCESS, location = "TransporteDePersonal/config/programaciones/Formprogramacion.jsp") })
	public String GoprogramacionFormTDP() {
		log.info("ingreso aqui2");
		return SUCCESS;
	}
	
	@Action(value = "/usuariostdp", results = {
			@Result(name = SUCCESS, location = "TransporteDePersonal/config/usuariostdp.jsp") })
	public String GousuariosTDP() {
		return SUCCESS;
	}
	
	@Action(value = "/ciudadestdp", results = {
			@Result(name = SUCCESS, location = "TransporteDePersonal/config/ciudades/ciudadestdp.jsp") })
	public String GodestinosTDP() {
		return SUCCESS;
	}
	
	
	@Action(value = "/listarciudades", results = { @Result(name = SUCCESS, type = "json")})
	public String listar_CiudadesTDP() throws Exception{
		List<V_CiudadesBean> listaciudades = new ArrayList<>();
		listaciudades=ciudadesservice.SelectCiudadesTransporteDePersonal(query,limit,offset); 
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		for(V_CiudadesBean v:listaciudades)
		{
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("Codigo", v.getCodigo());
			parametros.put("Detalle", v.getDetalle()); 
			parametros.put("Estado", v.isTDP());
			rows.add(parametros);
		}
		total = ciudadesservice.CountCiudades();
		//resultados
		resultados.put("rows", rows);
		resultados.put("total", total);
		ValueStack stack = ActionContext.getContext().getValueStack();
		 
		Map<String,Object>resultados = new HashMap<>();
		resultados.put("resultados", this.resultados);
		stack.push(resultados);
		return SUCCESS;
	} 
	

	@Action(value = "/listarrutas", results = { @Result(name = SUCCESS, type = "json")})
	public String listarrutas() throws Exception{
		List<V_CiudadesBean> listaciudades = new ArrayList<>();
		listaciudades=ciudadesservice.SelectCiudadesTransporteDePersonal(query,limit,offset); 
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		for(V_CiudadesBean v:listaciudades)
		{
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("Codigo", v.getCodigo());
			parametros.put("Detalle", v.getDetalle()); 
			parametros.put("Estado", v.isTDP());
			rows.add(parametros);
		}
		total = ciudadesservice.CountCiudades();
		//resultados
		resultados.put("rows", rows);
		resultados.put("total", total);
		ValueStack stack = ActionContext.getContext().getValueStack();
		 
		Map<String,Object>resultados = new HashMap<>();
		resultados.put("resultados", this.resultados);
		stack.push(resultados);
		return SUCCESS;
	} 
	
	@Action(value = "/CambiarEstadoCiudadTDP", results = { @Result(name = SUCCESS, location = "TransporteDePersonal/config/ciudades/ciudadestdp.jsp")})
	
	public String CambiarEstadoCiudadTDP() throws Exception{
		ciudadesservice.CambiarEstado(ciudad.getCodigo());
	return SUCCESS;
	}
	
@Action(value = "/RegistrarCiudadTDP", results = { @Result(name = SUCCESS, location = "TransporteDePersonal/config/ciudades/ciudadestdp.jsp")})
	
	public String RegistrarCiudadTDP() throws Exception{
		ciudadesservice.InsertCiudad(ciudad.getDetalle());
	return SUCCESS;
	}

	@Action(value = "/croquis", results = { @Result(name = SUCCESS, type = "json")})
	public String listar_croquisTDP(){
		
		return SUCCESS;
	}
	 
	@Override
	public void setServletResponse(HttpServletResponse response1) {
		// TODO Auto-generated method stub
		response = response1; 
	}
	@Override
	public void setSession(Map<String, Object> sesion1) {
		// TODO Auto-generated method stub
		sesion = sesion1;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	} 
	public V_CiudadesBean getCiudad() {
		return ciudad;
	}
	public void setCiudad(V_CiudadesBean ciudad) {
		this.ciudad = ciudad;
	}	
	
}
