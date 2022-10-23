package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.FuncionesGeneralesDao;


public class FuncionesGeneralesServiceI implements FuncionesGeneralesService {

	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	FuncionesGeneralesDao dao = factoria.getFunciones();
	
	@Override
	public String FechaServidor() throws Exception {
		// TODO Auto-generated method stub
		return dao.FechaServidor();
	}

	@Override
	public String ValidaRangoFecha(String fechaInicial, String fechaFinal) throws Exception {
		// TODO Auto-generated method stub
		return dao.ValidaRangoFecha(fechaInicial, fechaFinal);
	}

	

}
