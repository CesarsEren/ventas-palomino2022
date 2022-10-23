package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.opensymphony.xwork2.ActionSupport;

import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.util.Encryptor;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmail;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmailInformes;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
public class RecuperaCuenta extends ActionSupport implements SessionAware,ServletRequestAware {
	
	private static final Log log = LogFactory.getLog(RecuperaCuenta.class);
	private String correo = "";
	private String nuevopass = "";
	private String confirpass= "";
	private SpringSecurityClient cliente = new SpringSecurityClient();
	private UsuariosWebService service = new  UsuariosWebServiceI();
	private boolean resultado = false;
	private String mensaje = "";
	private String token = "";
	private String password = "";
	private String confirmpassword="";
	private String username="";
	private Map<String, Object> session;
	private HttpServletRequest request;
	private boolean cambiopagina = false;
	
	@Action(value = "recuperaclave" , results={@Result(name = SUCCESS, type= "json")})
	public String recuperacontrasena(){
		
		try {
			
			if(!correo.trim().equals("")){
				
				cliente= service.obtieneClienteSMS(correo);
				int respuesta = -1;
				String tokencuenta = UUID.randomUUID().toString().replace("-", ""); 
				if(cliente!= null){
					
					//linkconfirmacion = "http://ventas.grupopalomino.com.pe:8080/ventastest/accedeconfirmacion?token="+cliente.getToken()+"";//+"&pass="+cliente.getPassword()+"";
					
					Map<String, String> parametros = new HashMap<String, String>();
					parametros.put("to",correo);
					parametros.put("subject","Restaura tu contraseña");
					parametros.put("header", "Hola, "+cliente.getNombres());
					parametros.put("body","<p>Recientemente, alguien solicitó cambiar la contraseña de tu cuenta de "
							  +"Tienda Virtual. Si solicitaste el cambio, puedes crear una nueva contraseña <br/> aquí:</p> <br/><br/>"
							  +"<a href ='https://ventas.grupopalomino.com.pe:8443/ventas/cambiocuenta?token="+tokencuenta+"'"
							 // +"<a href ='http://172.16.20.52:9020/ventasgrupopalomino/cambiocuenta?token="+tokencuenta+"'"
							  +"style='background: #33AD45;"
							  +"background-image: -webkit-linear-gradient(top, #33AD45, #33AD45);"
							  +"background-image: -moz-linear-gradient(top, #33AD45, #33AD45);"
							  +"background-image: -ms-linear-gradient(top, #33AD45, #33AD45);"
							  +"background-image: -o-linear-gradient(top, #33AD45, #33AD45);"
							  +"background-image: linear-gradient(to bottom, #33AD45, #33AD45);"
							  +"-webkit-border-radius: 9;"
							  +"-moz-border-radius: 9;"
							  +"border-radius: 5px;"
							  +"text-shadow: 1px 1px 3px #666666;"
							  +"font-family: Arial;"
							  +"color: #ffffff;"
							  +"font-size: 20px;"
							  +"padding: 10px 20px 10px 20px; text-decoration: none;'  target='_blank'>RESTABLECER CONTRASEÑA</a><br/><br/>");
					
					GenerarEmailInformes.enviarCorreoAdjunto(parametros, null);
					
					respuesta = service.updateTokenSMS(correo, tokencuenta, 0);
					
					if(respuesta == -1){
						mensaje = "Ocurrio un problema en la actualizacion de su cuenta, por favor intentelo más trade.";	
						return SUCCESS;
					}
					resultado = true;
					mensaje = "se ha enviado una confirmacion a su correo, por favor verifique.";
					session.put("correo", correo);
				}else{
					mensaje = "la cuenta ingresada no existe, por favor verifique.";
					return SUCCESS;
				}
			}else{
				mensaje = "Por favor, ingrese su correo.";
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	
	@Action(value = "/cambiocuenta", results={@Result(name = SUCCESS, location = "sistema/clientes/paginacuenta.jsp"),
											  @Result(name = ERROR, location = "error/error404.jsp")})
	public String accederecuperacontrasena(){
		
		try {
			
			int respuesta = -1;
			if(!token.trim().equals("")){ 
				
				respuesta = service.updateTokenSMS("", token, -1);
				
				if(respuesta == -1){
					cambiopagina = false;
					session.put("cambiopagina",cambiopagina);
					addActionError("Ha ocurrido un problema en el cambio de contraseña, por favor intentelo más tarde.");
					return SUCCESS;
				}
				if(respuesta == 0){
					cambiopagina = false;
					session.put("cambiopagina",cambiopagina);
					addActionError("El vinculo ha expirado y/o es invalido.");
					return SUCCESS;
					
				}
				if(respuesta == 1){
					cambiopagina = true;
					session.put("cambiopagina",cambiopagina);
					addActionMessage("El Link ha sido confirmado Ingrese su nueva contraseña.");
					return SUCCESS;
					
				}
			}else{
				
				return ERROR;
			}
			
		} catch (Exception e) {
			
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
		
	}
	
	@Action(value = "/cambiocuentapp", results = {
			@Result(name = SUCCESS, location = "sistema/clientes/paginacuentapp.jsp"),
			@Result(name = ERROR, location = "error/error404.jsp"),
			@Result(name = "ERRORCUENTA", type="redirectAction", location="salir")})
	public String accederecuperacontrasenapp() {

		try {

			int respuesta = -1;
			if (!token.trim().equals("")) {
				
				String separador[] = token.split("/");
				correo = Encryptor.DescifrarBase64(separador[1].toString());
				respuesta = service.updateTokenSMS("", separador[0], -1);

				if (respuesta == -1) {
					cambiopagina = false;
					addActionError("Ha ocurrido un problema en el cambio de contraseña, por favor intentelo más tarde.");
					return SUCCESS;
				}
				if (respuesta == 0) {
					cambiopagina = false;
					addActionError("El vinculo ha expirado y/o es invalido.");
					return SUCCESS;

				}
				if (respuesta == 1) {
					cambiopagina = true;
					addActionMessage("El Link ha sido confirmado Ingrese su nueva contraseña.");
					return SUCCESS;

				}
			} else {

				return ERROR;
			}

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			return "ERRORCUENTA";
		}

		return SUCCESS;

	}
	
	@Action(value = "/verificacuenta", results={@Result(name = SUCCESS,type="redirectAction", location="accedepago"),
												@Result(name = ERROR, location = "sistema/clientes/paginacuenta.jsp")})
	public String redireccionalogin(){
		
		try {
			
			int resultado = -1 ;
			if(password.trim().equals("")|| confirmpassword.trim().equals("")){
				cambiopagina = true;
				session.put("cambiopagina",cambiopagina);
				addActionError("Debe ingresar una nueva clave y/o confirmar su clave");
				return ERROR;
			}
			if(!password.trim().equals(confirmpassword.trim())){
				addActionError("Las claves no coinciden, por favor verifique");
				cambiopagina = true;
				session.put("cambiopagina",cambiopagina);
				return ERROR;
			}
			if(password.trim().length() < 8){
				addActionError("La clave debe tener al menos 8 caracteres.");
				cambiopagina = true;
				session.put("cambiopagina",cambiopagina);
				return ERROR;
				
			}
			
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			setPassword(encoder.encode(password)); 
			resultado = service.updateCuentaSMS(username, getPassword());
			
			if(resultado == -1){
				addActionError("Ocurrio un problema en la actualizacion de sus datos, por favor intentelo más tarde.");
				return ERROR;
			}
			 
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		authenticateUserAndSetSession(request);
		return SUCCESS;
		
	}
	
	@Action(value = "/verificacuentapp", results = {
			@Result(name = SUCCESS, location = "sistema/clientes/paginacuentapp.jsp"),
			@Result(name = ERROR, type = "redirectAction", location = "salir") })
	public String verificacuentapp() {

		try {

			int resultado = -1;
			cambiopagina = true;
			correo = username;

			if (password.trim().equals("") || confirmpassword.trim().equals("")) {
				addActionError("Debe ingresar una nueva clave y/o confirmar su clave");
				return SUCCESS;
			}
			if (!password.trim().equals(confirmpassword.trim())) {
				addActionError("Las claves no coinciden, por favor verifique");
				return SUCCESS;
			}
			if (password.trim().length() < 8) {
				addActionError("La clave debe tener al menos 8 caracteres.");
				return SUCCESS;

			}
			if (username == null || username.trim().equals("")) {
				log.info("ERROR AL CAMBIAR LA CUENTA DESDE EL APP (username vacio) -->" + username);
				return ERROR;
			}

			cambiopagina = true;
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			setPassword(encoder.encode(password));
			resultado = service.updateCuentaSMS(username, getPassword());

			if (resultado == 0) {
				log.info("ERROR AL CAMBIAR LA CUENTA DESDE EL APP (NO SE ACTUALIZO LA CUENTA) RESULTADO -->"
						+ resultado);
				return ERROR;

			}

			if (resultado == -1) {
				cambiopagina = false;
				addActionError("Ocurrio un problema en la actualizacion de sus datos, por favor intentelo más tarde.");
				return SUCCESS;
			}
			cambiopagina = false;
			addActionMessage("Su cuenta ha sido restablecida correctamente.");

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			return ERROR;
		}

		return SUCCESS;

	}
	
	private void authenticateUserAndSetSession(HttpServletRequest request) {
		try {
			System.out.println(request.getParameter("username"));
			System.out.println(request.getParameter("password"));
			request.login(request.getParameter("username"), request.getParameter("password"));
		} catch (ServletException e) {
			log.info(Utils.printStackTraceToString(e));
		}
    }
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setNuevopass(String nuevopass) {
		this.nuevopass = nuevopass;
	}
	public String getNuevopass() {
		return nuevopass;
	}
	public void setConfirpass(String confirpass) {
		this.confirpass = confirpass;
	}
	public String getConfirpass() {
		return confirpass;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	public boolean isResultado() {
		return resultado;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getToken() {
		return token;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setCambiopagina(boolean cambiopagina) {
		this.cambiopagina = cambiopagina;
	}
	public boolean isCambiopagina() {
		return cambiopagina;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

}
