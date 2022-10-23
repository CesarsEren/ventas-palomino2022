package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.access.prepost.PreAuthorize;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('4')")
public class ClienteTDP extends ActionSupport implements SessionAware, ServletResponseAware {

	HttpServletResponse response;
	Map<String, Object> sesion; 
	
	
	
	@Action(value = "/ventatdpd", results = {
			@Result(name = SUCCESS, location = "TransporteDePersonal/ventaTDP/ventastdp.jsp") })
	public String GoventaTDP() {
		return SUCCESS;
	}
	
	
	@Action(value = "/estadocuenta", results = {
			@Result(name = SUCCESS, location = "TransporteDePersonal/reporte/cuentatdpcliente.jsp") })
	public String GoestadocuentaTDP() {
		return SUCCESS;
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response=arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sesion=arg0;
	}
	

}
