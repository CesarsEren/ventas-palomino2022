package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_UsuariosRBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_Usuarios_WebDao;


public class LoginServiceI implements LoginService {

	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_Usuarios_WebDao dao = factoria.getUsuarioWeb();
	@Override
	public V_UsuariosRBean cambiaEstadoUsuario(V_UsuariosRBean bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public V_UsuariosRBean actualizaUsuario(V_UsuariosRBean bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public V_UsuariosRBean nuevoUsuario(V_UsuariosRBean bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<V_UsuariosRBean> listaUsuarios(String query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
