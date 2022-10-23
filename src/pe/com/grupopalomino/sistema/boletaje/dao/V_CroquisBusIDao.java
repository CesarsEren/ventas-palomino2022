package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CroquisBusBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_CroquisBusIDao extends MapperSQL implements V_CroquisBusDao {

	private static final Log log = LogFactory.getLog(V_CroquisBusIDao.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V_CroquisBusBean> obtieneCroquisPorNumeroBus(String bus) throws Exception {
		
		SqlSession session = sqlMapper.openSession();
		List<V_CroquisBusBean> lista = new ArrayList<>();
		
		try {
			lista = session.selectList(ProjectStr+"SQL_ObtieneCroquisBus",bus);
			
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int cantidadAsientosPorBus(String codigoBus) throws Exception {
		
		SqlSession session = sqlMapper.openSession();
		int cantidadAsientos = -1;		
		
		try {
			
			cantidadAsientos = (int) session.selectOne(ProjectStr+"SQL_Obtiene_Cantidad_Asientos_Bus", codigoBus);
			
			return cantidadAsientos;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return 0;
	}
	
	@Override
	@SuppressWarnings("all")
	public List<B_VentaBean> listaAsientosOcupadosPorProgramacionSalida(int nroProgramacion) throws Exception {
		
		SqlSession session = sqlMapper.openSession();
		List<B_VentaBean> lstVentas = new ArrayList<>();
		
		try {
			lstVentas = session.selectList(ProjectStr+"SQL_ObtieneAsientosOcupados_Por_NRO_Programacion", nroProgramacion);
			return lstVentas;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return null;
	}
	
	@Override
	public B_VentaBean obtieneDatosAsientoOcupado(int nroProgramacion, String asiento) throws Exception {
		
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nroProgramacion", nroProgramacion);
		parametros.put("asiento", asiento);
		
		
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr+"SQL_Obtiene_Datos_Asiento_Ocupado", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
	}

	public static void main(String[] args) {
		V_CroquisBusIDao a = new V_CroquisBusIDao();
		try {
			System.out.println(a.obtieneDatosAsientoOcupado(262985, "55"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
