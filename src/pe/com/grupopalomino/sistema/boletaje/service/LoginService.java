package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_UsuariosRBean;

public interface LoginService {
	
	public V_UsuariosRBean cambiaEstadoUsuario(V_UsuariosRBean bean) throws Exception;
	public V_UsuariosRBean actualizaUsuario(V_UsuariosRBean bean) throws Exception;
	public V_UsuariosRBean nuevoUsuario(V_UsuariosRBean bean) throws Exception;
	public List<V_UsuariosRBean> listaUsuarios(String query) throws Exception;

}
