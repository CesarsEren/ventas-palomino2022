package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosMapaBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_DestinosMapaDao;

public class DestinosMapaServiceI implements DestinosMapaService {
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_DestinosMapaDao dao = factoria.getDestinosMapa();

	@Override
	public List<V_DestinosMapaBean> destinosmapa() throws Exception {
		// TODO Auto-generated method stub
		return dao.destinosmapa();
	}

}
