package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosAgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_DestinosAgenciasWebDao;


public class DestinosAgenciasWebServiceI implements DestinosAgenciasWebService {
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_DestinosAgenciasWebDao dao = factoria.getDestinosAgenciasWeb();
	@Override
	public V_DestinosAgenciasWebBean obtieneDestinosAgencias(String origen, String destino) throws Exception {
		// TODO Auto-generated method stub
		return dao.obtieneDestinosAgencias(origen, destino); 
	}
	@Override
	public List<V_DestinosAgenciasWebBean> ListaDestinosAgencias() throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaDestinosAgencias();
	}

	

}
