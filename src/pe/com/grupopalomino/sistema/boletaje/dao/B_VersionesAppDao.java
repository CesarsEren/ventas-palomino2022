package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VersionesAppBean;

public interface B_VersionesAppDao {
	
	public int insertVerionesApp(B_VersionesAppBean bean)throws Exception;
	public List<B_VersionesAppBean> listaVersionesApp(int offset,int limit)throws Exception;
	public int listaVersionesAppTotal()throws Exception;
	public int ActualizaVersionesApp(B_VersionesAppBean bean)throws Exception;
	public int EliminarVersionesApp(int id)throws Exception;
	public B_VersionesAppBean selectVersionesApp(int id)throws Exception;
	

}
