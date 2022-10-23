package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Cliente_SMS_Web;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;

public interface UsuariosWebService {

	public int cuentaUsuariosTotales() throws Exception;
	public int cuentaUsuariosTotalesTransporteDePersonal()throws Exception;
	
	public SpringSecurityClient nuevoClienteSMS(SpringSecurityClient cliente) throws Exception; 
	public SpringSecurityClient loginClienteSMS(String codigo) throws Exception;
	public SpringSecurityClient actualizaClienteSMS(SpringSecurityClient cliente) throws Exception;
	public SpringSecurityClient obtieneClienteSMS(String email) throws Exception;
	public SpringSecurityUser verificaAgenciaExistente(String codigoAgencia) throws Exception;
	public SpringSecurityUser limiteCreditoUsuario(String username) throws Exception;
	public int updateMontoVentaActual(String username,double montoVentaActual)throws Exception;
	public int updateMontoVentaConfirmada(String username,double montoVentaConfirmada)throws Exception;
	public SpringSecurityUser obtieneUsuarioXid(int id) throws Exception;
	public SpringSecurityUser cambiaEstadoUsuario(SpringSecurityUser bean) throws Exception;
	public SpringSecurityUser verificaRucExistente (String Ruc) throws Exception;
	public SpringSecurityUser actualizaUsuario(SpringSecurityUser bean) throws Exception;
	public SpringSecurityUser nuevoUsuario(SpringSecurityUser bean) throws Exception;
	public SpringSecurityUser nuevoUsuarioTransporteDePersonal(SpringSecurityUser bean) throws Exception; 
	public List<SpringSecurityUser> listaUsuarios(String query, Integer limit, Integer offset) throws Exception;
	public List<SpringSecurityUser> listaUsuariosTransporteDePersonal(String query, Integer limit, Integer offset) throws Exception;
	public List<SpringSecurityUser> listaUsuariosRuc (String Ruc) throws Exception;
	public List<SpringSecurityUser> listaUsuarios () throws Exception;
	public SpringSecurityClient confirmacionClienteSMS(String token) throws Exception;
	public int updateCuentaSMS(String username,String password) throws Exception;
	public int updateTokenSMS(String username,String tokencuenta,int confirmadocuenta) throws Exception;
	public List<SpringSecurityUser> listaUsuariosProgramacionPago() throws Exception;
	public List<SpringSecurityUser> listaUsuariosAgentes() throws Exception;
	public List<SpringSecurityUser> listaUsuariosAgentesCiudad(String ciudad) throws Exception;
	public List<Map<String, Object>> SQL_SELECT_AGENCIAS_USUARIOS() throws Exception;
	public List<Map<String, Object>> SQL_SELECT_AGENCIAS_POR_USUARIOS(String username) throws Exception; 
	public List<V_Cliente_SMS_Web> ListaUsuariosVentaTelefonica() throws Exception;
}
