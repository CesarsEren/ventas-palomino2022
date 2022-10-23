package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Cliente_SMS_Web;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_Usuarios_WebDao;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;

public class UsuariosWebServiceI implements UsuariosWebService {

	private DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	private V_Usuarios_WebDao dao = factoria.getUsuarioWeb();

	@Override
	public SpringSecurityUser cambiaEstadoUsuario(SpringSecurityUser bean) throws Exception {
		return dao.cambiaEstadoUsuario(bean);
	}

	@Override
	public SpringSecurityUser actualizaUsuario(SpringSecurityUser bean) throws Exception {
		return dao.actualizaUsuario(bean);
	}

	@Override
	public SpringSecurityUser nuevoUsuario(SpringSecurityUser bean) throws Exception {
		return dao.nuevoUsuario(bean);
	}

	@Override
	public List<SpringSecurityUser> listaUsuarios(String query, Integer limit, Integer offset) throws Exception {
		return dao.listaUsuarios(query, limit, offset);
	}

	@Override
	public int cuentaUsuariosTotales() throws Exception {
		return dao.cuentaUsuariosTotales();
	}

	@Override
	public SpringSecurityUser obtieneUsuarioXid(int id) throws Exception {
		return dao.obtieneUsuarioXid(id);
	}

	@Override
	public SpringSecurityClient nuevoClienteSMS(SpringSecurityClient cliente) throws Exception {
		return dao.nuevoClienteSMS(cliente);
	}

	@Override
	public SpringSecurityClient loginClienteSMS(String codigo) throws Exception {
		return dao.loginClienteSMS(codigo);
	}

	@Override
	public SpringSecurityClient actualizaClienteSMS(SpringSecurityClient cliente) throws Exception {
		return dao.actualizaClienteSMS(cliente);
	}

	@Override
	public SpringSecurityClient obtieneClienteSMS(String email) throws Exception {
		return dao.obtieneClienteSMS(email);
	}

	@Override
	public SpringSecurityUser verificaAgenciaExistente(String codigoAgencia) throws Exception {
		// TODO Auto-generated method stub
		return dao.verificaAgenciaExistente(codigoAgencia);
	}

	@Override
	public SpringSecurityUser limiteCreditoUsuario(String username) throws Exception {
		// TODO Auto-generated method stub
		return dao.limiteCreditoUsuario(username);
	}

	@Override
	public int updateMontoVentaActual(String username, double montoVentaActual) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateMontoVentaActual(username, montoVentaActual);
	}

	@Override
	public int updateMontoVentaConfirmada(String username, double montoVentaConfirmada) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateMontoVentaConfirmada(username, montoVentaConfirmada);
	}

	@Override
	public SpringSecurityUser verificaRucExistente(String Ruc) throws Exception {
		// TODO Auto-generated method stub
		return dao.verificaRucExistente(Ruc);
	}

	@Override
	public SpringSecurityClient confirmacionClienteSMS(String token) throws Exception {
		// TODO Auto-generated method stub
		return dao.confirmacionClienteSMS(token);
	}

	@Override
	public int updateCuentaSMS(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateCuentaSMS(username, password);
	}

	@Override
	public int updateTokenSMS(String username, String tokencuenta, int confirmadocuenta) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateTokenSMS(username, tokencuenta, confirmadocuenta);
	}

	@Override
	public List<SpringSecurityUser> listaUsuarios() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuarios();
	}

	@Override
	public List<SpringSecurityUser> listaUsuariosRuc(String Ruc) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuariosRuc(Ruc);
	}

	@Override
	public List<SpringSecurityUser> listaUsuariosProgramacionPago() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuariosProgramacionPago();
	}

	@Override
	public List<SpringSecurityUser> listaUsuariosAgentes() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuariosAgentes();
	}

	@Override
	public List<SpringSecurityUser> listaUsuariosAgentesCiudad(String ciudad) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuariosAgentesCiudad(ciudad);
	}

	@Override
	public List<Map<String, Object>> SQL_SELECT_AGENCIAS_USUARIOS() throws Exception {
		// TODO Auto-generated method stub
		return dao.SQL_SELECT_AGENCIAS_USUARIOS();
	}

	@Override
	public int cuentaUsuariosTotalesTransporteDePersonal() throws Exception {
		// TODO Auto-generated method stub
		return dao.cuentaUsuariosTotalesTransporteDePersonal();
	}

	@Override
	public List<SpringSecurityUser> listaUsuariosTransporteDePersonal(String query, Integer limit, Integer offset)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuariosTransporteDePersonal(query, limit, offset);
	}

	@Override
	public SpringSecurityUser nuevoUsuarioTransporteDePersonal(SpringSecurityUser bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.nuevoUsuarioTransporteDePersonal(bean);
	}

	@Override
	public List<V_Cliente_SMS_Web> ListaUsuariosVentaTelefonica() throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaUsuariosVentaTelefonica();
	}

	@Override
	public List<Map<String, Object>> SQL_SELECT_AGENCIAS_POR_USUARIOS(String username) throws Exception {
		// TODO Auto-generated method stub
		return dao.SQL_SELECT_AGENCIAS_POR_USUARIOS(username); 
	}

}
