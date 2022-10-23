package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CroquisBusBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_CroquisBusDao;

public class CroquisServiceI implements CroquisService {

DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	
	V_CroquisBusDao croquisBusDao = factoria.getCroquisBusDao();
	
	@Override
	public int cantidadAsientosPorBus(String bus) throws Exception {
		return croquisBusDao.cantidadAsientosPorBus(bus);
	}
	@Override
	public List<V_CroquisBusBean> obtieneCroquisPorNumeroBus(String bus) throws Exception {
		return croquisBusDao.obtieneCroquisPorNumeroBus(bus);
	}
	@Override
	public List<B_VentaBean> listaAsientosOcupadosPorProgramacionSalida(int nroProgramacion) throws Exception {
		return croquisBusDao.listaAsientosOcupadosPorProgramacionSalida(nroProgramacion);
	}
	@Override
	public B_VentaBean obtieneDatosAsientoOcupado(int nroProgramacion, String asiento) throws Exception {
		return croquisBusDao.obtieneDatosAsientoOcupado(nroProgramacion, asiento);
	}
	
}
