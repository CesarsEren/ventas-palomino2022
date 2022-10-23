package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosClienteWebBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_DestinosClienteWebDao;

public class DestinosClienteWebServiceI implements DestinosClienteWebService {
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_DestinosClienteWebDao dao = factoria.getDestinosClientesWeb();

	@Override
	public List<V_DestinosClienteWebBean> obtieneOrigenClientes(String Ruc) throws Exception {
		return dao.obtieneOrigenClientes(Ruc); 
	}

	@Override
	public V_DestinosClienteWebBean verificaDestinoCliente(String Ruc) throws Exception {
		return dao.verificaDestinoCliente(Ruc);
	}

	@Override
	public List<V_DestinosClienteWebBean> obtieneDestinoClientes(String Origen,String Ruc) throws Exception {
		return dao.obtieneDestinoClientes(Origen,Ruc); 
	}

}
