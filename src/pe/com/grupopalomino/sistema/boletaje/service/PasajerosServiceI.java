package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.bean.V_PasajerosBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_PasajerosDao;

public class PasajerosServiceI implements PasajerosService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_PasajerosDao dao = factoria.getPasajeros();
	
	@Override
	public V_PasajerosBean listPasajerosDNI(String DNI,String Codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.listPasajerosDNI(DNI,Codigo);
	}

	@Override
	public int InsertPasajeros(V_PasajerosBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.InsertPasajeros(bean);
	}

}
