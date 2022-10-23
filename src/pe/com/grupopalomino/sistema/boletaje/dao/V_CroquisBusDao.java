package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CroquisBusBean;

public interface V_CroquisBusDao {

	public int cantidadAsientosPorBus(String bus) throws Exception;
	public List<V_CroquisBusBean> obtieneCroquisPorNumeroBus(String bus) throws Exception;
	public List<B_VentaBean> listaAsientosOcupadosPorProgramacionSalida(int nroProgramacion) throws Exception;
	public B_VentaBean obtieneDatosAsientoOcupado(int nroProgramacion, String asiento) throws Exception;
	
}
