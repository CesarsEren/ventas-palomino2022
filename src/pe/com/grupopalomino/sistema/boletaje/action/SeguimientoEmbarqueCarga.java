package pe.com.grupopalomino.sistema.boletaje.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.access.prepost.PreAuthorize;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@ParentPackage("BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','S')")
public class SeguimientoEmbarqueCarga extends ActionSupport implements ServletRequestAware{


	private String query;
	private int limit, offset, total;
	
	@Action(value = "accedelistausuarios", results = {
			@Result(name = SUCCESS, location = "sistema/usuarios/listausuarios.jsp") })
	public String accedePageSeguimientoCarga()
	{
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

}
