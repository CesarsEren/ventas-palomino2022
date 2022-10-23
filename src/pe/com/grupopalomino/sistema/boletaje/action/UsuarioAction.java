package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CiudadesBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ClientesBean;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasService;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CiudadesService;
import pe.com.grupopalomino.sistema.boletaje.service.CiudadesServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesService;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.ValidaDatosUsuario;
import pe.com.grupopalomino.sistema.boletaje.util.*;

@SuppressWarnings("serial")
@ParentPackage("BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','T')")
public class UsuarioAction extends ActionSupport implements ServletRequestAware {

	private SpringSecurityUser usuario = new SpringSecurityUser();

	private String query;
	private int limit, offset, total;
	private List<V_AgenciasWebBean> listaAgencias;
	private Map<String, Object> resultados;
	private HttpServletRequest request;
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	private ClientesService servicecliente = new ClientesServiceI();
	private CiudadesService serviceciudades = new CiudadesServiceI();
	private AgenciasService serviceagencias = new AgenciasServiceI();
	private List<V_CiudadesBean> ciudades = new ArrayList<>();
	private List<V_AgenciasBean> agencias = new ArrayList<>();
	private static final Log log = LogFactory.getLog(UsuarioAction.class);

	@Action(value = "accedelistausuarios", results = {
			@Result(name = SUCCESS, location = "sistema/usuarios/listausuarios.jsp") })
	public String accedeListaUsuarios() {

		return SUCCESS;
	}

	public String ObtenerRol() {
		String rpta = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		log.info("USUARIO LOGEADO " + userName);
		for (String rol : roles) {
			log.info("USUARIO ROL |:  " + rol);
			rpta = rol;
			// isAdminTransporte = rol.equals("ROLE_T")?true:false;
		}
		return rpta;
	}

	@Action(value = "listausuarios", results = { @Result(name = SUCCESS, type = "json") })
	public String listaUsuarios() {
		String rol = ObtenerRol();
		boolean isAdminTransporte = false;
		isAdminTransporte = rol.equals("ROLE_T");
		try {
			resultados = new HashMap<String, Object>();
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			List<SpringSecurityUser> listaUsuarios = isAdminTransporte
					? usuarioService.listaUsuariosTransporteDePersonal(query, limit, offset)
					: usuarioService.listaUsuarios(query, limit, offset);

			for (SpringSecurityUser usuario : listaUsuarios) {
				Map<String, Object> parametros = new HashMap<String, Object>();
				log.info("USUARIO ROL DENTRO|:  " + usuario.getNivel());

				parametros.put("NomUsuario", usuario.getUsername());
				parametros.put("Usuario", usuario.getNombreCompleto());
				parametros.put("Nivel", usuario.getNivel());
				parametros.put("correo", usuario.getCorreo());
				parametros.put("estado", usuario.getEstado());
				parametros.put("id", usuario.getId());
				parametros.put("ruc", usuario.getRuc());
				parametros.put("limitecredito", usuario.getLimiteCredito());
				rows.add(parametros);
			}
			int tot = isAdminTransporte ? usuarioService.cuentaUsuariosTotalesTransporteDePersonal()
					: usuarioService.cuentaUsuariosTotales();
			log.info("TOTAL DE REGISTRAR :| " + tot);
			// SE CREO EL ROL PARA TRANSPORTE DE PERSONAL
			total = tot; // rows.size();//
			resultados.put("rows", rows);
			resultados.put("total", total);

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "filtrausuarios", results = { @Result(name = SUCCESS, type = "json") })
	public String filtraListaUsuarios() {

		return SUCCESS;
	}

	@SuppressWarnings("static-access")
	@Action(value = "nuevousuario", results = { @Result(name = SUCCESS, location = "sistema/usuarios/resultado.jsp"),
			@Result(name = "ACCESO", location = "sistema/usuarios/nuevousuario.jsp"),
			@Result(name = ERROR, location = "sistema/usuarios/nuevousuario.jsp") })
	public String nuevoUsuario() {
		String Password = "";

		String rol = ObtenerRol();
		boolean isAdminTransporte = false;

		isAdminTransporte = rol.equals("ROLE_T");

		if (request.getMethod().trim().toUpperCase().equals("GET")) {

			try {

				ciudades = serviceciudades.SelectCiudades();
				agencias = serviceagencias.SQL_SELECT_AGENCIAS();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info(Utils.printStackTraceToString(e));
			}

			return "ACCESO";

		} else if (request.getMethod().trim().toUpperCase().equals("POST")) {

			log.info("" + usuario.toString());
			log.info("" + usuario.toString());
			log.info("" + usuario.toString());
			usuario.setRazonSocial(usuario.getRazonPrueba());
			try {

				ValidaDatosUsuario validaUsuario = new ValidaDatosUsuario(usuario);

				if (isAdminTransporte) {
					if (validaUsuario.esUsuarioTransporteDePersonalValido().getError()) {
						for (String mensaje : validaUsuario.esUsuarioTransporteDePersonalValido().getMensajeError()) {
							addActionError(mensaje);
						}
						ciudades = serviceciudades.SelectCiudades();
						agencias = serviceagencias.SQL_SELECT_AGENCIAS();
						return ERROR;
					}
				} else {
					if (validaUsuario.esUsuarioValido().getError()) {
						for (String mensaje : validaUsuario.esUsuarioValido().getMensajeError()) {
							addActionError(mensaje);
						}
						ciudades = serviceciudades.SelectCiudades();
						agencias = serviceagencias.SQL_SELECT_AGENCIAS();

						return ERROR;
					}
				}

				// SpringSecurityUser user = new SpringSecurityUser();
				V_ClientesBean beancliente = null;

				beancliente = servicecliente.listClientesRUC(usuario.getRuc());

				if (beancliente == null) {
					beancliente = new V_ClientesBean();
					beancliente.setRuc(usuario.getRuc());
					beancliente.setRazon(usuario.getRazonSocial().trim().toUpperCase());
					beancliente.setDireccion(usuario.getDireccion().trim().toUpperCase());

					beancliente.setCredito(isAdminTransporte ? "C" : null);

					beancliente.setResponsable(null);
					beancliente.setCorreo(null);
					beancliente.setTelefono(null);
					beancliente.setTipo(null);
					servicecliente.InertClientes(beancliente);
				}
				usuario.setRazonSocial(beancliente.getRazon());
				// user = usuarioService.verificaRucExistente(usuario.getRuc());
				// if(user == null){

				// Verificamos si la sesión que Inicio es Adminitrador de
				// Transporte de PERSONAL
				// si ? el usuario es su correo : Se genera un Usuario Random
				// Seteamos los valores a Mayusculas.
				if (isAdminTransporte) {
					// usuario.setUsername( );
					usuario = usuario.SetValuesTouperCaseTransporteDePersonal(usuario);
				} else {
					usuario = usuario.SetValuesTouperCase(usuario);
					usuario.setUsername(GeneraUserName(usuario));
				}

				Password = usuario.getPassword();
				usuarioService.nuevoUsuario(usuario);

				GenerarEmail email = new GenerarEmail();

				Map<String, String> parametros = new HashMap<String, String>();
				parametros.put("subject", "ACCESO - GRUPO PALOMINO");
				parametros.put("to", usuario.getCorreo());				
				parametros.put("header", "Generacion de usuario y clave del sistema ventas en linea de Grupo Palomino");

				StringBuilder sb = new StringBuilder();
				sb.append("Usuario: " + usuario.getUsername());
				sb.append("<br/>");
				sb.append("Password: " + Password);

				parametros.put("body", sb.toString());

				email.enviarCorreoAdjunto(parametros, null);

				// }else{
				// addActionError("El Ruc ya se encuentra registrado en un
				// Usuario del sistema.");
				// return ERROR;
				// }

			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}

			return SUCCESS;
		}

		return ERROR;
	}

	@Action(value = "verusuario", results = { @Result(name = SUCCESS, location = "sistema/usuarios/verusuario.jsp"),
			@Result(name = ERROR, location = "sistema/usuarios/listausuarios.jsp") })
	public String obtenerUsuario() {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			usuario = usuarioService.obtieneUsuarioXid(id);

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			return ERROR;
		}

		return SUCCESS;
	}

	@Action(value = "actualizausuario", results = {
			@Result(name = SUCCESS, location = "sistema/usuarios/resultado.jsp"),
			@Result(name = "ACCESO", location = "sistema/usuarios/actualizausuario.jsp"),
			@Result(name = ERROR, location = "sistema/usuarios/actualizausuario.jsp") })
	public String actualizarUsuario() {

		String rol = ObtenerRol();
		boolean isAdminTransporte = false;

		isAdminTransporte = rol.equals("ROLE_T");

		if (request.getMethod().trim().toUpperCase().equals("GET")) {

			try {
				int id = Integer.parseInt(request.getParameter("id"));
				usuario = usuarioService.obtieneUsuarioXid(id);
				ciudades = serviceciudades.SelectCiudades();
				agencias = serviceagencias.SQL_SELECT_AGENCIAS();
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
				return ERROR;
			}

			return "ACCESO";
		} else if (request.getMethod().trim().toUpperCase().equals("POST")) {
			try {

				usuario.setRazonSocial(usuario.getRazonPrueba());

				ValidaDatosUsuario validaUsuario = new ValidaDatosUsuario(usuario);
				String Password = "";
				if (isAdminTransporte) {
					if (validaUsuario.esUsuarioTransporteDePersonalValido().getError()) {
						for (String mensaje : validaUsuario.esUsuarioTransporteDePersonalValido().getMensajeError()) {
							addActionError(mensaje);
						}
						ciudades = serviceciudades.SelectCiudades();
						agencias = serviceagencias.SQL_SELECT_AGENCIAS();
						return ERROR;
					} 
				} else {
					if (validaUsuario.esUsuarioValido().getError()) {
						for (String mensaje : validaUsuario.esUsuarioValido().getMensajeError()) {
							addActionError(mensaje);
						}
						ciudades = serviceciudades.SelectCiudades();
						agencias = serviceagencias.SQL_SELECT_AGENCIAS();
						return ERROR;
					}
				}

				usuario = usuario.SetValuesTouperCase(usuario);
				Password = usuario.getPassword();
				usuarioService.actualizaUsuario(usuario);

				Map<String, String> parametros = new HashMap<String, String>();
				parametros.put("subject", "ACCESO - GRUPO PALOMINO");
				parametros.put("to", usuario.getCorreo());
				parametros.put("header", "Hola," + usuario.getNombres());
				parametros.put("password", "Password :" + Password);

				StringBuilder sb = new StringBuilder();
				sb.append("alguien solicitó cambiar la contraseña de tu cuenta del sistema ventas en linea de Grupo Palomino.");
				sb.append("<br/>");
				sb.append("<br/>");
				sb.append("Nueva Clave: " + "<b>" + Password + "</b>");

				parametros.put("body", sb.toString());
				GenerarEmail.enviarCorreoAdjunto(parametros, null);

			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
		}

		return SUCCESS;
	}

	@Action(value = "cambiaestadousuario", results = {
			@Result(name = SUCCESS, location = "sistema/usuarios/listausuarios.jsp") })
	public String cambiaEstadoUsuario() {

		try {
			usuarioService.cambiaEstadoUsuario(usuario);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/ciudades", results = { @Result(name = SUCCESS, type = "json") })
	public String listaCantidadPasajeros() {
		try {
			ciudades = serviceciudades.SelectCiudades();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	private String GeneraUserName(SpringSecurityUser bean) {
		UUID number = UUID.randomUUID();
		int tamanio = 0;

		if (bean.getApellidoPaterno().trim().length() == 1) {
			tamanio = 1;
		} else if (bean.getApellidoPaterno().trim().length() == 2) {
			tamanio = 2;
		} else if (bean.getApellidoPaterno().trim().length() == 3) {
			tamanio = 3;
		} else {
			tamanio = 4;
		}
		// System.out.println((bean.getNombres().charAt(0)+
		// (bean.getApellidoPaterno()!= null &&
		// bean.getApellidoPaterno().length()>0 ?
		// bean.getApellidoPaterno().substring(0, tamanio) :
		// number.toString().replace("-", "").substring(0, 3))+
		// (bean.getApellidoMaterno() != null &&
		// bean.getApellidoMaterno().length()> 0 ?
		// bean.getApellidoMaterno().charAt(0)+number.toString().replace("-",
		// "").substring(0, 2).toUpperCase()+"WEB" :
		// number.toString().replace("-", "").substring(0, 3)+"WEB")));
		// return (bean.getNombres().charAt(0)+(bean.getApellidoPaterno()!= null
		// && bean.getApellidoPaterno().length()>0 ?
		// (bean.getApellidoPaterno().substring(0, tamanio)): ""+
		// (bean.getApellidoMaterno()== null ? "" :
		// bean.getApellidoMaterno().charAt(0)+number.toString().replace("-",
		// "").substring(0, 2)).toUpperCase())+"WEB" ;
		return (bean.getNombres().trim().charAt(0)
				+ (bean.getApellidoPaterno().trim() != null && bean.getApellidoPaterno().trim().length() > 0
						? bean.getApellidoPaterno().trim().substring(0, tamanio)
						: number.toString().replace("-", "").substring(0, 3).toUpperCase())
				+ (bean.getApellidoMaterno() != null && bean.getApellidoMaterno().trim().length() > 0
						? bean.getApellidoMaterno().trim().charAt(0)
								+ number.toString().replace("-", "").substring(0, 2).toUpperCase() + "WEB"
						: number.toString().replace("-", "").substring(3, 3).toUpperCase() + "WEB"));
	}

	public SpringSecurityUser getUsuario() {
		return usuario;
	}

	public void setUsuario(SpringSecurityUser usuario) {
		this.usuario = usuario;
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

	public Map<String, Object> getResultados() {
		return resultados;
	}

	public void setResultados(Map<String, Object> resultados) {
		this.resultados = resultados;
	}

	public void setListaAgencias(List<V_AgenciasWebBean> listaAgencias) {
		this.listaAgencias = listaAgencias;
	}

	public List<V_AgenciasWebBean> getListaAgencias() {
		return listaAgencias;
	}

	public void setCiudades(List<V_CiudadesBean> ciudades) {
		this.ciudades = ciudades;
	}

	public List<V_CiudadesBean> getCiudades() {
		return ciudades;
	}

	public void setAgencias(List<V_AgenciasBean> agencias) {
		this.agencias = agencias;
	}

	public List<V_AgenciasBean> getAgencias() {
		return agencias;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public static void main(String[] args) {

		UUID number = UUID.randomUUID();

		System.out.println(number.toString());
		System.out.println(number.toString().replace("-", "").substring(0, 3).toUpperCase());

	}
}
