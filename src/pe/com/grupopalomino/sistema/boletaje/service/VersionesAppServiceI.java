package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List; 
import pe.com.grupopalomino.sistema.boletaje.bean.B_VersionesAppBean;
import pe.com.grupopalomino.sistema.boletaje.dao.B_VersionesAppDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;


public class VersionesAppServiceI  implements VersionesAppService{

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_VersionesAppDao dao = factoria.getVersionesApp();
	
	@Override
	public int insertVerionesApp(B_VersionesAppBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertVerionesApp(bean); 
	}

	@Override
	public List<B_VersionesAppBean> listaVersionesApp(int offset,int limit) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaVersionesApp(offset, limit);
	}

	@Override
	public B_VersionesAppBean selectVersionesApp(int id) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectVersionesApp(id); 
	}

	@Override
	public int listaVersionesAppTotal() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaVersionesAppTotal();
	}

	@Override
	public int ActualizaVersionesApp(B_VersionesAppBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.ActualizaVersionesApp(bean);
	}

	@Override
	public int EliminarVersionesApp(int id) throws Exception {
		// TODO Auto-generated method stub
		return dao.EliminarVersionesApp(id); 
	}

}
