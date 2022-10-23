package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_VariosDao;

public class VariosServiceI implements VariosService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_VariosDao dao = factoria.getVarios();

	@Override
	public V_Varios Get_Precio_Maximo_Asiento() throws Exception {
		// TODO Auto-generated method stub
		return dao.Get_Precio_Maximo_Asiento();
	}

	@Override
	public V_Varios Get_Cantidad_Maximo_Pasajeros() throws Exception {
		// TODO Auto-generated method stub
		return dao.Get_Cantidad_Maximo_Pasajeros();
	}

	@Override
	public V_Varios Select_Varios() throws Exception {
		// TODO Auto-generated method stub
		return dao.Select_Varios();
	}

	@Override
	public List<String> Select_NroBusesconFallas() throws Exception {
		// TODO Auto-generated method stub
		return dao.Select_NroBusesconFallas();
	}

}
