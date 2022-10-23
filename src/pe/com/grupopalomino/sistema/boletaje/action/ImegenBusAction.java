package pe.com.grupopalomino.sistema.boletaje.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import com.opensymphony.xwork2.ActionSupport;

import pe.com.grupopalomino.sistema.boletaje.bean.B_ImagenesBus;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;



@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1')")
public class ImegenBusAction extends ActionSupport {
	
	
	private static final Log log = LogFactory.getLog(ImegenBusAction.class);
	private  B_ImagenesBus imagen = new B_ImagenesBus();
	
	
	@Action(value = "registraimagenbus",results = {
			@Result(name = SUCCESS , location = "sistema/usuarios/listausuarios.jsp"),
			@Result(name = ERROR , location = "sistema/usuarios/listausuarios.jsp")})
	public String registraImagenBus (){
		
		try {
			
			
			
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	
	@Action(value = "paginabus",results = {
			@Result(name = SUCCESS , location = "sistema/imagenbus/nuevoimagen.jsp"),
			@Result(name = ERROR , location = "sistema/usuarios/listausuarios.jsp")})
	public String paginaBus (){
		
		return SUCCESS;
		
		
	}
	
	public void setImagenbean(B_ImagenesBus imagen) {
		this.imagen = imagen;
	}
	public B_ImagenesBus getImagenbean() {
		return imagen;
	}

}
