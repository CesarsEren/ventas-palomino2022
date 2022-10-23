package pe.com.grupopalomino.sistema.boletaje.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.opensymphony.xwork2.ActionSupport;

import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesService;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.ErrorValidacion;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmail;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.util.ValidaDatosCliente;
 
@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
public class ClienteAction extends ActionSupport implements SessionAware,ServletRequestAware {

	private String password;
	private String username;
	private String token;
	private Map<String, Object> session;
	private List<String> mensajeServer = new ArrayList<String>();
	private List<Map<String, String>> rows;
	private boolean errorserver = false;
	private SpringSecurityClient cliente = new SpringSecurityClient();
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	private ClientesService clientesService = new ClientesServiceI();
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private boolean logincompras = false;
	private HttpServletRequest request;
	private static final Log log = LogFactory.getLog(ClienteAction.class);
	
	@Action(value = "accedeverhistorial", results = {@Result(name = "YALOGUEADO", location = "sistema/clientes/verhistorial.jsp"),
													@Result(name = SUCCESS,type="redirectAction", location="salir")})
	public String accedeVerHistorial(){
		
		try {
			
			if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){ 
				UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				if(usuario instanceof SpringSecurityClient){
					
					return "YALOGUEADO";
				}
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "paginaregistrocliente", results = {@Result(name = SUCCESS,location = "sistema/clientes/_form.jsp")})
	public String verPaginaRegistroCliente(){
		
		return SUCCESS;
		
	}
	@Action(value = "paginamiscompras", results = {@Result(name = SUCCESS,location = "sistema/clientes/paginamiscompras.jsp"),
												  @Result(name = "YALOGUEADO",type="redirectAction", location="accedeverhistorial")})
	public String verPaginaMisCompras(){
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		try {
			
			if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
				
				UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				if(usuario instanceof SpringSecurityClient){
					//SpringSecurityClient cliente = (SpringSecurityClient) usuario;
					
					return "YALOGUEADO";
				}
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	
	@Action(value = "miscompras", results = {@Result(name = SUCCESS ,location = "sistema/clientes/verhistorial.jsp"),
											 @Result(name = ERROR,location = "sistema/clientes/paginamiscompras.jsp"),
											 @Result(name = "YALOGUEADO",type="redirectAction", location="salir")})
	public String verMisCompras(){
		
		
		try {
			
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal()); 
			if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
				
				UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				if(usuario instanceof SpringSecurityUser){
					
					return "YALOGUEADO";
				}
			}
			
			
			if(username.trim().equals("") || password.trim().equals("")) {
				
				addActionError("Ingreso un Usuario y/o Password."); 
				return ERROR;
				
			}else{
				
				SpringSecurityClient cliente = new SpringSecurityClient();
				
				cliente = usuarioService.obtieneClienteSMS(username);
				
				if(cliente!=null){
					
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					setPassword(password);
					
					if(encoder.matches(getPassword().trim(),cliente.getPassword().trim())){
						//logincompras = true;
						//session.put("logincompras", logincompras);
						authenticateUserAndSetSession(request);
						return SUCCESS;
						
					}else{
						addActionError("El correo electrónico o la contraseña no son válidos."); 
						return ERROR;
					}
				}else{
					addActionError("El correo electrónico o la contraseña no son válidos."); 
					return ERROR;	
					
				}
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		
		return SUCCESS;
		
	}
	
	@Action(value = "verhistorial", results = {@Result(name = SUCCESS, type = "json")})
	public String verHistorial(){
		
		String username = "";
		
		rows = new ArrayList<Map<String, String>>();
		
		if(SecurityContextHolder.getContext().getAuthentication().getName()!= null){
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityClient){
				SpringSecurityClient usuarioSpringSecurity = (SpringSecurityClient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				username = usuarioSpringSecurity.getUsername();
				
				rows = clientesService.listaHistorialVentas(username);
			}
		}
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "accederegistrocliente", results = {@Result(name = SUCCESS, location = "sistema/clientes/registrarcliente.jsp"),
														@Result(name = ERROR, type="redirectAction", location="salir"),
														@Result(name = "paginainicio",type="redirectAction", location="salir")})
	public String accedeRegistroCliente(){
		
		try {
			
			VentaPaso1Form paso1Form = (session.get("paso1Form")== null ? null : (VentaPaso1Form) session.get("paso1Form"));
			
			if(paso1Form != null){
				
				if(!paso1Form.isIdaVuelta()){
					
					List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda")== null ? new ArrayList<ListaPreVenta>():  (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
					
					for (ListaPreVenta bean : ListaPreventaIda) {
						
						B_VentaBean venta =   serviceventa.selectVentaVisa(bean.getNro(), bean.getSalida());
						
						if(venta != null){
							
							serviceventa.updateTiempoVentaCliente(venta.getNro(), venta.getSalida());
							
						}else{
							log.info("VENTA NO EXISTE EN LA BD (IDA) --> SE REDIRECCIONO A LA PAGINA DE PALOMINO"); 
							return ERROR;
						}
					}
					
				}else{
					
					List<ListaPreVenta> ListaPreventaIdaVuelta = (session.get("ListaPreventaIdaVuelta")== null ? new ArrayList<ListaPreVenta>():  (ArrayList<ListaPreVenta>) session.get("ListaPreventaIdaVuelta"));
					
					for (ListaPreVenta bean : ListaPreventaIdaVuelta) {
						
						B_VentaBean venta =   serviceventa.selectVentaVisa(bean.getNro(), bean.getSalida());
						
						if(venta != null){
							
							serviceventa.updateTiempoVentaCliente(venta.getNro(), venta.getSalida());
							
						}else{
							log.info("VENTA NO EXISTE EN LA BD (IDA Y VUELTA) --> SE REDIRECCIONO A LA PAGINA DE PALOMINO");
							return ERROR;
						}
					}
					
				}
				
			}else{
				log.info("SE ACABO EL TIEMPO DE LA SESSION DEL APLICATIVO --> SE REDIRECCIONO A LA PAGINA DE PALOMINO");
				return "paginainicio" ;
				
			}
			
			
		} catch (Exception e) {
			
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "transacctiontimeout", results = {@Result(name = ERROR, location = "sistema/clientes/registrarcliente.jsp")})
	public String transaccionTimeOut(){
		addActionError("Su Compra no se pudo realizar por tiempo de Culminacion y/ó Error del Sistema");
		return ERROR;
	}
	
	
	@Action(value = "accedeconfirmacion", results = {@Result(name = SUCCESS, location = "sistema/clientes/clienteconfirmado.jsp")})
	public String accedeConfirmacion(){
		
		SpringSecurityClient cliente = new SpringSecurityClient();
		
		try {
			
			if(token != null && !token.trim().equals("")){
				cliente = usuarioService.confirmacionClienteSMS(token.trim());
				if(cliente != null){
					addActionMessage("Su cuenta ha sido confirmada,ya tiene acceso al sistema");
				}else{
					addActionError("Usted no ha confirmado el link enviado a su correo y/o su cuenta no existe.");
				}
			}else{
				addActionError("Hubo un problema en la confirmacion de su cuenta, por favor vuelva a intertarlo.");
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}
	
	@Action(value = "accedeconfirmacioncuenta", results = {@Result(name = SUCCESS, location = "sistema/clientes/clienteconfirmadoapp.jsp")})
	public String accedeConfirmacionapp(){
		
		SpringSecurityClient cliente = new SpringSecurityClient();
		
		try {
			
			if(token != null && !token.trim().equals("")){
				cliente = usuarioService.confirmacionClienteSMS(token.trim());
				if(cliente != null){
					addActionMessage("Su cuenta ha sido confirmada,ya tiene acceso al sistema");
				}else{
					addActionError("Usted no ha confirmado el link enviado a su correo y/o su cuenta no existe.");
				}
			}else{
				addActionError("Hubo un problema en la confirmacion de su cuenta, por favor vuelva a intertarlo mas tarde.");
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "accederegistroconfirmacion",results = { @Result(name = SUCCESS,type = "json"),
	@Result(name = ERROR, type = "json")})
	public String accedeRegistroconfirmacion(){
		
		
		String linkconfirmacion ;
		
		List<ListaPreVenta> ListaPreventaIda = (session.get("ListaPreventaIda")== null ? new ArrayList<ListaPreVenta>():  (ArrayList<ListaPreVenta>) session.get("ListaPreventaIda"));
		
		if(ListaPreventaIda.size()==0){
			
			mensajeServer.add("Usted no ha agregado Pasajeros en la lista, por favor vuelva al paso 1 de la Venta.");
			errorserver = true;
			return ERROR;
		}
		password = cliente.getPassword();
		ValidaDatosCliente validador = new ValidaDatosCliente(cliente);
		ErrorValidacion error = validador.esClienteValido();
		
		if(error.getError()){
			for (String mensaje : error.getMensajeError()) {
				mensajeServer.add(mensaje);
				errorserver = true;
			}
			return ERROR;
		}
		cliente.SetvaluesClient(cliente);
		
		try {
			
			usuarioService.nuevoClienteSMS(cliente);
			
			
			//linkconfirmacion = "http://192.168.0.102:8080/ventasgrupopalomino/accedeconfirmacion?token="+cliente.getToken()+"";//"&pass="+cliente.getPassword()+"";
			//linkconfirmacion = "http://172.16.20.52:8080/ventasgrupopalomino/accedeconfirmacion?token="+cliente.getToken()+"";//"&pass="+cliente.getPassword()+"";
			linkconfirmacion = "https://ventas.grupopalomino.com.pe:8443/ventas/accedeconfirmacion?token="+cliente.getToken()+"";//+"&pass="+cliente.getPassword()+"";
			
			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("to", cliente.getEmail());
			parametros.put("subject", "Confirmación de cuenta de usuario en la tienda virtual de Grupo Palomino.");
			parametros.put("header", "");
			parametros.put("body", "Usted debera confirmar su cuenta por medio de este link enviado "+linkconfirmacion+" <br/> <br/> Su usuario es: "+ cliente.getEmail()
									+" <br/> Su contraseña es: " + password);
			
			
			GenerarEmail.enviarCorreoAdjunto(parametros, null);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		mensajeServer.add("se ha enviado una confirmacion a su cuenta de correo, por favor verifique");
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
	


	public String getPassword() {
		return password;
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
	
	public List<String> getMensajeServer() {
		return mensajeServer;
	}

	public void setMensajeServer(List<String> mensajeServer) {
		this.mensajeServer = mensajeServer;
	}

	public boolean isErrorserver() {
		return errorserver;
	}

	public void setErrorserver(boolean errorserver) {
		this.errorserver = errorserver;
	}
	public void setCliente(SpringSecurityClient cliente) {
		this.cliente = cliente;
	}
	public SpringSecurityClient getCliente() {
		return cliente;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
	public List<Map<String, String>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}
	
	public void setLogincompras(boolean logincompras) {
		this.logincompras = logincompras;
	}
	public boolean isLogincompras() {
		return logincompras;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	} 
	
}
