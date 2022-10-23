package pe.com.grupopalomino.sistema.boletaje.dao;


import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_UsuarioHorariosBean;

public interface V_UsuarioHorariosDao {
	
	public int insertUsuariosHorarios(V_UsuarioHorariosBean bean)throws Exception;
	public int deleteUsuariosHorarios(int Id, String Usuario)throws Exception;
	public int updateUsuariosHorarios(V_UsuarioHorariosBean bean)throws Exception;
	public List<V_UsuarioHorariosBean> listaUsuarios(String query, Integer limit, Integer offset) throws Exception;
	public V_UsuarioHorariosBean listaUsuarioHorario(String Usuario)throws Exception;
	public V_UsuarioHorariosBean listaUsuarioHorarioXid(int Id)throws Exception;
	public int UsuariosHorariosCount()throws Exception;
	public V_UsuarioHorariosBean VerificaUsuarioHorario(String Usuario)throws Exception;

}
