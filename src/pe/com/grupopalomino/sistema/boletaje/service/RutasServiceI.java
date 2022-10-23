package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_RutasDao;


public class RutasServiceI implements RutasService {

	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_RutasDao dao = factoria.getRutas();
	
	@Override
	public List<V_RutasBean> getRutasNro(int Nro) throws Exception {
		// TODO Auto-generated method stub
		return dao.getRutasNro(Nro);
	}

	@Override
	public List<V_RutasBean> ListaRutas() throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaRutas();
	}

	@Override
	public List<V_RutasBean> ListSQL_RutasXCupon(String codigocupon) throws Exception {
		// TODO Auto-generated method stub
		return dao.ListSQL_RutasXCupon(codigocupon);
	}

}
