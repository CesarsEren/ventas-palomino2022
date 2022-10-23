package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.bean.B_EncomiendasBean;
import pe.com.grupopalomino.sistema.boletaje.dao.B_EncomiendasDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;


public class EncomiendasServiceI implements EncomiendasService{
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_EncomiendasDao dao = factoria.getEncomiendas();

	@Override
	public B_EncomiendasBean getEncomiendaSerieNumero(String serie, String numero,String documento,String empresa) throws Exception {
		// TODO Auto-generated method stub
		return dao.getEncomiendaSerieNumero(serie, numero,documento,empresa); 
	}

}
