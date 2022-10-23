package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_UsuarioHorariosBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_UsuarioHorariosDao;

public class UsuariosHorariosServiceI implements UsuariosHorariosService {
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_UsuarioHorariosDao dao = factoria.getUsuariosHorarios();
	

	@Override
	public int insertUsuariosHorarios(V_UsuarioHorariosBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertUsuariosHorarios(bean);
	}

	@Override
	public int deleteUsuariosHorarios(int Id, String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteUsuariosHorarios(Id, Usuario);
	}

	@Override
	public int updateUsuariosHorarios(V_UsuarioHorariosBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateUsuariosHorarios(bean);
	}

	@Override
	public List<V_UsuarioHorariosBean> listaUsuarios(String query, Integer limit, Integer offset) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuarios(query, limit, offset); 
	}

	@Override
	public V_UsuarioHorariosBean listaUsuarioHorarioXid(int Id) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuarioHorarioXid(Id); 
	}

	@Override
	public V_UsuarioHorariosBean listaUsuarioHorario(String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuarioHorario(Usuario); 
	}

	@Override
	public int UsuariosHorariosCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.UsuariosHorariosCount();
	}

	@Override
	public V_UsuarioHorariosBean VerificaUsuarioHorario(String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.VerificaUsuarioHorario(Usuario); 
	}

}
