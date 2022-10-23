package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_ServiciosBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_ServiciosDao;

public class ServiciosServiceI implements ServiciosService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_ServiciosDao dao = factoria.getServicios();
	
	@Override
	public V_ServiciosBean getServicioCodigo(String Codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getServicioCodigo(Codigo);
	}

	@Override
	public List<V_ServiciosBean> ListarServicios() throws Exception {
		// TODO Auto-generated method stub
		return dao.ListarServicios();
	}

	@Override
	public List<V_ServiciosBean> ListarDetalledeServiciosXCupon(String detalle) throws Exception {
		// TODO Auto-generated method stub
		return dao.ListarDetalledeServiciosXCupon(detalle);
	}

}
