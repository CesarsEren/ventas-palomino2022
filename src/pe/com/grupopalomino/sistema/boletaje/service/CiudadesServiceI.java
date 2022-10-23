package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CiudadesBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_CiudadesDao;

public class CiudadesServiceI implements CiudadesService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_CiudadesDao dao = factoria.getCiudades();

	@Override
	public List<V_CiudadesBean> SelectCiudades(String Tipo) throws Exception {
		return dao.SelectCiudades(Tipo);
	}

	@Override
	public List<V_CiudadesBean> SelectCiudades() throws Exception {
		return dao.SelectCiudades();
	}

	@Override
	public List<V_CiudadesBean> SelectCiudadesTransporteDePersonal(String query, Integer limit, Integer offset)
			throws Exception {
		return dao.SelectCiudadesTransporteDePersonal(query, limit, offset);
	}

	@Override
	public int CountCiudades() throws Exception {
		// TODO Auto-generated method stub
		return dao.CountCiudades();
	}

	@Override
	public int CambiarEstado(String codigo) {
		// TODO Auto-generated method stub
		return dao.CambiarEstado(codigo);
	}

	@Override
	public int InsertCiudad(String detalle) {
		// TODO Auto-generated method stub
		return dao.InsertCiudad(detalle);
	}
	
	

}
