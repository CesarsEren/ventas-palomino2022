package pe.com.grupopalomino.sistema.boletaje.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1')")
public class ReporteGerenciaAction extends ActionSupport{

	
	private static final long serialVersionUID = -7872487440373838341L;

	@Action(value = "reporteventas", results = {@Result(name = SUCCESS, location = "sistema/reportesgerencia/reportegrafico.jsp")})
	public String reporteventas(){
		return SUCCESS;
	}
	
	
	
	
}
