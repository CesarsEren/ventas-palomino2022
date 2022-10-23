package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso3Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso4Form;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
public class ListasBootstrapTableAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> mapaJSONResultado;
	
	@SuppressWarnings("unchecked")
	@Action(value = "listapasajerosagregadosida", results = {@Result(name = SUCCESS, type = "json")})
	public String listaPasajerosAgregadosIda(){
		
		mapaJSONResultado = new HashMap<String, Object>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		
		List<VentaPaso3Form> listaPasajeros = (List<VentaPaso3Form>) session.get("listaPasajeros");
		
		if(listaPasajeros != null){
			for (VentaPaso3Form ventaPaso3Form : listaPasajeros) {
				Map<String, Object> boleto  = new HashMap<String, Object>();
				boleto.put("id", ventaPaso3Form.getId());
				boleto.put("precio", ventaPaso3Form.getPrecio());
				boleto.put("nroAsiento", ventaPaso3Form.getNumeroAsiento());
				boleto.put("nombre", ventaPaso3Form.getNombrePasajero());
				boleto.put("embarque", ventaPaso3Form.getComboEmbarque());
				boleto.put("destinobajada", ventaPaso3Form.getComboDestinoBajada());
				
				rows.add(boleto);
			}
		}
		
		int total = listaPasajeros != null ? listaPasajeros.size() : 0;
		
		mapaJSONResultado.put("rows", rows);
		mapaJSONResultado.put("total", total);
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "listapasajerosagregadosvuelta", results = {@Result(name = SUCCESS, type = "json")})
	public String listaPasajerosAgregadoVuelta(){
		
		mapaJSONResultado = new HashMap<String, Object>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		
		List<VentaPaso4Form> listaPasajeros = (List<VentaPaso4Form>) session.get("listaPasajerosTabla");
		
		if(listaPasajeros != null){
			for (VentaPaso4Form ventaPaso4Form : listaPasajeros) {
				Map<String, Object> boleto  = new HashMap<String, Object>();
				boleto.put("id", ventaPaso4Form.getId());
				boleto.put("precio", ventaPaso4Form.getPrecio());
				boleto.put("nroAsiento", ventaPaso4Form.getNumeroAsiento());
				boleto.put("nombre", ventaPaso4Form.getNombrePasajero());
				boleto.put("embarque", ventaPaso4Form.getComboEmbarque());
				boleto.put("destinobajada", ventaPaso4Form.getComboDestinoBajada());
				
				rows.add(boleto);
			}
		}
		
		int total = listaPasajeros != null ? listaPasajeros.size() : 0;
		
		mapaJSONResultado.put("rows", rows);
		mapaJSONResultado.put("total", total);
		
		return SUCCESS;
	}
	
	public Map<String, Object> getMapaJSONResultado() {
		return mapaJSONResultado;
	}
	
	public void setMapaJSONResultado(Map<String, Object> mapaJSONResultado) {
		this.mapaJSONResultado = mapaJSONResultado;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
