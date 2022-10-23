package pe.com.grupopalomino.sistema.boletaje.action;


import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.opensymphony.xwork2.ActionSupport;
import com.visural.common.web.client.Platform;
import com.visural.common.web.client.UserAgent;
import com.visural.common.web.client.WebClient;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_EmpresasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ProgramacionPagosBean;
//import pe.com.grupopalomino.sistema.boletaje.bean.V_ProgramacionPagosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_UsuarioHorariosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.service.EmpresaService;
import pe.com.grupopalomino.sistema.boletaje.service.EmpresaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionPagoService;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionPagoServiceI;
//import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionPagoService;
//import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionPagoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosHorariosService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosHorariosServiceI;
//import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
//import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
//import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;


@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
public class LoginAction extends ActionSupport implements SessionAware,ServletRequestAware {
	
	private static final Logger log = Logger.getLogger(LoginAction.class);
	private Map<String, Object> sesion;
	private String codigo, telefono, mensajeServer;
	private boolean error;
	private HttpServletRequest request;
	private List<V_Varios> listacantidadPasajeros = new ArrayList<V_Varios>();
	private List<V_EmpresasBean> listaComboEmpresa = new ArrayList<V_EmpresasBean>();
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private UsuariosHorariosService servicehorarios = new UsuariosHorariosServiceI();
	private ProgramacionPagoService serviceprogramacion = new ProgramacionPagoServiceI();
	private EmpresaService serviceempresa = new EmpresaServiceI();
	
	private String ETICKET = "";

	@Action(value = "/inicio", results = {@Result( name = SUCCESS, location = "login.jsp")})
	public String inicio(){
		String logout = ServletActionContext.getRequest().getParameter("logout");
		
		try {
			request.logout();
			limpiaDatosVenta();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		if(logout != null){
			addActionMessage("Ha terminado su sesion en el sistema.");
		}
		
		return SUCCESS;
	}
	@Action(value = "/horario", results = {@Result( name = SUCCESS, location = "login.jsp")})
	public String usuarionopermitido(){
		
		
		try {
			request.logout();
			
			String mensaje = (String) sesion.get("mensajevalidacion");
			
			limpiaDatosVenta();
			sesion.remove("mensajevalidacion");
			addActionError(mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/programacion" ,results = {@Result (name= SUCCESS, location = "login.jsp")})
	public String programacionpago(){
		
		
		try {
			request.logout();
			
			String mensaje = (String) sesion.get("mensajevalidacion");
			
			limpiaDatosVenta();
			sesion.remove("mensajevalidacion");
			addActionError(mensaje);
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	
	@SuppressWarnings("unchecked")
//	@PreAuthorize("hasAnyRole('1','3','SMS','C')")
	@Action(value = "principal", results = {@Result(name = SUCCESS, location = "ventaida/ventaboletos.jsp"),
											@Result(name = "RECLAMOS", location = "reclamosinformes/pagereclamos.jsp"),
											@Result(name = "FACTURACION", location = "sistema/facturacionelectronica/documentosaprobados.jsp"),
											@Result(name = "YALOGUEADO", type="redirectAction", location="accedepago"),
											@Result(name = "VERIFICAHORARIO", type="redirectAction", location="horario"),
											@Result(name = "VERIFICAPROGRAMACION", type="redirectAction", location="programacion")})
	public String principal(){
		
		try {
			//int operacion = -1;
			if(sesion.get("listaPasajeros") != null){
				
				UserDetails usuario = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				if(usuario instanceof SpringSecurityClient){
					return "YALOGUEADO";
				}
			}
			
			if(SecurityContextHolder.getContext().getAuthentication().getName()!=null){
				if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
					if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
						SpringSecurityUser usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
						
						if(usuario!=null){
							if(usuario.getNivel().trim().equals("R")||usuario.getNivel().trim().equals("E")){
								
								listaComboEmpresa = serviceempresa.listaEmpresas();
								return "RECLAMOS";
							}
						}
						if(usuario!=null){
							if(usuario.getNivel().trim().equals("D")||usuario.getNivel().trim().equals("F")){
								return "FACTURACION";
							}
						}
					}
				}
			}
			
			String userAgent = request.getHeader("User-Agent");
			//UserAg
			//String browserName = requestProvider.get().getHeader("user-agent");
			System.out.println(userAgent);
			 HttpServletRequest req = (HttpServletRequest) request;
		        WebClient client = WebClient.detect(req);
		        if (client.getUserAgent().equals(UserAgent.IE)) {
		            // do something for IE only
		        	System.out.println(client.getUserAgent()); 
		        } else if (client.getUserAgent().equals(UserAgent.FIREFOX) &&
		                 client.getPlatform().equals(Platform.LINUX)) {
		            // etc.
		        }
		    	System.out.println(client.getUserAgent()+" -VERSION : "+client.getFullVersion()+ "S.O "+client.getPlatform()+" - " + client.getMajorVersion()); 
			//System.out.println(userAgent); 
			if(sesion.get("listacantidadPasajeros")== null){
				
				listacantidadPasajeros = Utils.getListaCantidadPasajeros();
				sesion.put("listacantidadPasajeros", listacantidadPasajeros);
				
			}else{
				listacantidadPasajeros = (List<V_Varios>) sesion.get("listacantidadPasajeros"); 
				
			}
			
			List<ListaPreVenta> ListaPreventaIda = sesion.get("ListaPreventaIda") == null ? new ArrayList<ListaPreVenta>():(List<ListaPreVenta>)sesion.get("ListaPreventaIda"); 
			List<ListaPreVenta> ListaPreventaIdaVuelta = sesion.get("ListaPreventaIdaVuelta") == null ? new ArrayList<ListaPreVenta>(): (List<ListaPreVenta>) sesion.get("ListaPreventaIdaVuelta");
			
			for (ListaPreVenta preventa : ListaPreventaIda) {
				serviceventa.deleteVenta(preventa.getNro(),preventa.getSalida());
				
			}
		
			for (ListaPreVenta preventa : ListaPreventaIdaVuelta) {
				serviceventa.deleteVenta(preventa.getNro(),preventa.getSalida());
			}
			
			SpringSecurityUser usuario =  null;
			
			// VALIDACION DE INGRESO EN HORARIOS AL SISTEMA
			
			if(SecurityContextHolder.getContext().getAuthentication().getName()!=null){
				if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
					if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
						usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
						
						V_UsuarioHorariosBean beanHorarios = new V_UsuarioHorariosBean();
						V_UsuarioHorariosBean beanvalido = new V_UsuarioHorariosBean();
						
						beanHorarios = servicehorarios.listaUsuarioHorario(usuario.getUsername().trim());
						
						if(beanHorarios!=null){
							
							beanvalido = servicehorarios.VerificaUsuarioHorario(beanHorarios.getUsuario().trim());
							
							if(beanvalido != null){
								
								sesion.put("mensajevalidacion", beanvalido.getMensajeValidacion());
								return "VERIFICAHORARIO";
							}
						}
					}
				}
			}
			
			// VALIDACION DE PROGRAMACION DE PAGOS
			
						V_ProgramacionPagosBean beanprogramacion = null;
						
						if(SecurityContextHolder.getContext().getAuthentication().getName()!=null){
							
							if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
								
								if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
									
									 usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
									 
									 beanprogramacion = serviceprogramacion.VerificaProgramacionPago(usuario.getRuc());
									 
									 if(beanprogramacion!=null){
										 if(beanprogramacion.getTipoMensaje() == 5 ){
											 sesion.put("mensajevalidacion", beanprogramacion.getMensajeValidacion());
											 return "VERIFICAPROGRAMACION";
										}else{
											addActionError(beanprogramacion.getMensajeValidacion());
											 
										 }
									}
								}
							}
						}
							
		
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		limpiaDatosVenta();
		//limpiaValoresSesion();
		
		return SUCCESS;
	}
	
	@Action(value = "loginerror", results = {@Result(name = SUCCESS, location = "login.jsp")})
	public String loginError(){
		
		limpiaValoresSesion();
		addActionError("Sus datos no son los correctos o su cuenta esta deshabilitada.");
		
		return SUCCESS;
	}
	
	@Action(value = "loginsms", results = {@Result(name = SUCCESS, location = "loginsms.jsp")})
	public String loginSMS(){
		
		return SUCCESS;
	}
	
	@Action(value = "salir", results = {@Result(name = SUCCESS, location = "inicio.jsp")})
	public String salirVisa(){
		
		try {
			request.logout();
			limpiaDatosVenta();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	
	}
	
	@Action(value = "accesodenegado", results = {@Result(name = "ACCESODENEGADO", location = "error/accesodenegado.jsp")})
	public String accesoDenegado(){
		
		return "ACCESODENEGADO";
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isError() {
		return error;
	}
	public String getMensajeServer() {
		return mensajeServer;
	}
	public void setMensajeServer(String mensajeServer) {
		this.mensajeServer = mensajeServer;
	}
	public void setListacantidadPasajeros(List<V_Varios> listacantidadPasajeros) {
		this.listacantidadPasajeros = listacantidadPasajeros;
	}
	public List<V_Varios> getListacantidadPasajeros() {
		return listacantidadPasajeros;
	}

	@Override
	public void setSession(Map<String, Object> sesion) {
		this.sesion = sesion;
	}
	
	private void limpiaValoresSesion(){
		
		sesion.remove("lstBoletos");
		
	}
	private void limpiaDatosVenta(){
		sesion.remove("listaOrigenCombo");
		sesion.remove("listaDestinoCombo");
		sesion.remove("paso1Form");
		sesion.remove("paso2FormIDA");
		sesion.remove("paso2FormVUELTA");
		sesion.remove("listaCroquisBusIda");
		sesion.remove("listaCroquisBusVuelta");
		sesion.remove("minValorIda");
		sesion.remove("minValorVuelta");
		sesion.remove("listaPasajeros");
		sesion.remove("listaPasajerosTabla");
		sesion.remove("listaPasajerosIdaVuelta");
		sesion.remove("ListaAuxiliar");
		sesion.remove("ListaPreventaIda");
		sesion.remove("ListaPreventaIdaVuelta");
		sesion.remove("Cuponactivo");
	}
	
	public String getETICKET() {
		return ETICKET;
	}
	public void setETICKET(String eTICKET) {
		ETICKET = eTICKET;
	}
	
	public void setListaComboEmpresa(List<V_EmpresasBean> listaComboEmpresa) {
		this.listaComboEmpresa = listaComboEmpresa;
	}
	public List<V_EmpresasBean> getListaComboEmpresa() {
		return listaComboEmpresa;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request= request;
		
	}

}
