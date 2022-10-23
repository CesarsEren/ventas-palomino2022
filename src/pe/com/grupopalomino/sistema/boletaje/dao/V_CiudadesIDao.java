package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CiudadesBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_CiudadesIDao extends MapperSQL implements V_CiudadesDao {

	private static final Log log = LogFactory.getLog(V_CiudadesIDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<V_CiudadesBean> SelectCiudades(String Tipo) throws Exception {

		SqlSession session = sqlMapper.openSession();
		List<V_CiudadesBean> lista = new ArrayList<>();

		lista = session.selectList(ProjectStr + "SQL_LIST_CIUDADES_DISPONIBLES", Tipo);

		try {

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V_CiudadesBean> SelectCiudades() throws Exception {

		SqlSession session = sqlMapper.openSession();
		List<V_CiudadesBean> lista = new ArrayList<>();

		lista = session.selectList(ProjectStr + "SQL_LIST_CIUDADES");

		try {

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V_CiudadesBean> SelectCiudadesTransporteDePersonal(String query, Integer limit, Integer offset)
			throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<V_CiudadesBean> lista = new ArrayList<>();
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put("offset", offset);
		parametros.put("limit", limit);
		lista = session.selectList(ProjectStr + "SQL_LIST_CIUDADES_TRANSPORTE_DE_PERSONAL", parametros);

		try {

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return lista;
	}

	@Override
	public int CountCiudades() throws Exception {
		int rpta = 0;
		SqlSession session = sqlMapper.openSession();
		try {
			rpta = (Integer) session.selectOne(ProjectStr + "SQL_GET_COUNT_CIUDADES");

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return rpta;

	}

	@Override
	public int CambiarEstado(String codigo) {
		// TODO Auto-generated method stub
 
		int rpta = 0;
		SqlSession session = sqlMapper.openSession();
		try {
			rpta = session.update(ProjectStr + "SQL_UPDATE_ESTADO_CIUDAD_TRANSPORTE_DE_PERSONAL", codigo);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e)); 
		} finally {
			session.close();
		}
		return rpta;
	}

	@Override
	public int InsertCiudad(String detalle) {
		// TODO Auto-generated method stub
		int rpta = 0;
		SqlSession session = sqlMapper.openSession();
		try {
			rpta = session.insert(ProjectStr + "SQL_INSERT_CIUDAD_TRANSPORTE_DE_PERSONAL", detalle);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return rpta;
	}

}
