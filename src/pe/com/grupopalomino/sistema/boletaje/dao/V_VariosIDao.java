package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_VariosIDao extends MapperSQL implements V_VariosDao {

	private static final Log log = LogFactory.getLog(V_VariosIDao.class);

	@Override
	public V_Varios Get_Precio_Maximo_Asiento() throws Exception {
		SqlSession session = sqlMapper.openSession();
		V_Varios bean = new V_Varios();
		try {
			bean = (V_Varios) session.selectOne(ProjectStr + "SQL_ObtenerPrecioMaximoAsiento");
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public V_Varios Get_Cantidad_Maximo_Pasajeros() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		V_Varios bean = new V_Varios();
		try {
			bean = (V_Varios) session.selectOne(ProjectStr + "SQL_ObtenerCantidaMaximoPasajeros");
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public V_Varios Select_Varios() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		V_Varios bean = new V_Varios();
		try {
			bean = (V_Varios) session.selectOne(ProjectStr + "SQL_Select_Varios");
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> Select_NroBusesconFallas() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<String> buses = new ArrayList<>();
		try {
			buses = session.selectList(ProjectStr + "SQL_ObtenerBusesconFallas");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return buses;
	}

	/*
	 * public static void main(String[]args)throws Exception { for(String temp :
	 * new V_VariosIDao().Select_NroBusesconFallas()) {
	 * System.out.println(temp); } }
	 */

}
