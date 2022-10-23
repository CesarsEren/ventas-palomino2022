package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.bean.V_ComputerBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_ComputerDao;

public class ComputerServiceI implements ComputerService{

	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_ComputerDao dao = factoria.getTerminal();
	@Override
	public V_ComputerBean listComputer(String Serie) throws Exception {
		// TODO Auto-generated method stub
		return dao.listComputer(Serie);
	}

}
