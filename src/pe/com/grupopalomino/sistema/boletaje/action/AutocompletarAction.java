package pe.com.grupopalomino.sistema.boletaje.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ClientesBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_PasajerosBean;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesService;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.PasajerosService;
import pe.com.grupopalomino.sistema.boletaje.service.PasajerosServiceI;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
public class AutocompletarAction extends ActionSupport{
	
	private ClientesService servicecliente = new ClientesServiceI();
	private PasajerosService servicepasajero = new PasajerosServiceI();
	V_PasajerosBean beanpasajero = new V_PasajerosBean();
	V_ClientesBean beancliente = new V_ClientesBean(); 
	private String RUC;
	private String DNI;
	private String Codigo;
	boolean respuestaAjaxPasajero=false;
	boolean respuestaAjaxCliente=false;
	private static final Log log = LogFactory.getLog(AutocompletarAction.class);
	
	
	@Action(value = "/ClientesRUC", results = {
	@Result(name = SUCCESS,type="json") })
	public String ListarClientesXRUC() throws Exception {
			
			try {
				
				beancliente = servicecliente.listClientesRUC(RUC);
				
				if(beancliente != null){
					
					respuestaAjaxCliente=true;
				}
				
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
			
			return SUCCESS;
	}
	
	@Action(value = "/PasajerosDNI", results = {
	@Result(name = SUCCESS,type="json") })
	public String ListarPasajeroXDNI() throws Exception {
		
		try {
			
			beanpasajero = servicepasajero.listPasajerosDNI(DNI.trim(),Codigo.trim());
			
			if(beanpasajero!=null){
				respuestaAjaxPasajero=true;
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
		
		
	}

	
	public void setBeanpasajero(V_PasajerosBean beanpasajero) {
		this.beanpasajero = beanpasajero;
	}
	public V_PasajerosBean getBeanpasajero() {
		return beanpasajero;
	}
	public void setBeancliente(V_ClientesBean beancliente) {
		this.beancliente = beancliente;
	}
	public V_ClientesBean getBeancliente() {
		return beancliente;
	}
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getDNI() {
		return DNI;
	}
	
	public void setRUC(String rUC) {
		RUC = rUC;
	}
	public String getRUC() {
		return RUC;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getCodigo() {
		return Codigo;
	}
	
	public void setRespuestaAjaxCliente(boolean respuestaAjaxCliente) {
		this.respuestaAjaxCliente = respuestaAjaxCliente;
	}
	
	public boolean isRespuestaAjaxCliente() {
		return respuestaAjaxCliente;
	}
	
	public void setRespuestaAjaxPasajero(boolean respuestaAjaxPasajero) {
		this.respuestaAjaxPasajero = respuestaAjaxPasajero;
	}

	public boolean isRespuestaAjaxPasajero() {
		return respuestaAjaxPasajero;
	}
		

}
