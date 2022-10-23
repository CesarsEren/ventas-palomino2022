package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.V_ClientesBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_ClientesDao;


public class ClientesServiceI implements ClientesService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_ClientesDao dao = factoria.getClientes();
	
	@Override
	public V_ClientesBean listClientesRUC(String RUC) throws Exception {
		return dao.listClientesRUC(RUC);
	}

	@Override
	public int InertClientes(V_ClientesBean bean) throws Exception {
		return dao.InertClientes(bean);
	}

	@Override
	public List<Map<String, String>> listaHistorialVentas(String username) {
		return dao.listaHistorialVentas(username);
	}

}
