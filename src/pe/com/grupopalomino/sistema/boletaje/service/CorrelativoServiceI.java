package pe.com.grupopalomino.sistema.boletaje.service;


import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.dao.B_CorrelativosDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;


public class CorrelativoServiceI implements CorrelativosService {

	

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_CorrelativosDao dao = factoria.getCorrelativos();
	
	
	
	@Override
	public B_Correlativos generaCorrelativo() throws Exception {
		// TODO Auto-generated method stub
		return dao.generaCorrelativo();
	}

}
