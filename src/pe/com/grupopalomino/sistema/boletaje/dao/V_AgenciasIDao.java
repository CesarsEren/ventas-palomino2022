package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_AgenciasIDao extends MapperSQL implements V_AgenciasDao{

	private static final Log log = LogFactory.getLog(V_AgenciasIDao.class);
	
	@Override
	public V_AgenciasBean listAgencias(String Codigo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession(); /*Inicia comunicación MyBatis*/
		V_AgenciasBean bean = null;
		try {
			bean =  (V_AgenciasBean) session.selectOne(ProjectStr+"SQL_ObtenerAgencias",Codigo);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_AgenciasBean> getListaAsientosOcupadoXAgencia(int nroProgramacion) throws Exception {
		// TODO Auto-generated method stub
				SqlSession session = sqlMapper.openSession();
				List lista = new ArrayList<V_AgenciasBean>();
				try {
					lista = session.selectList(ProjectStr+"SQL_getListaAsientoOcupadoXAgenciaView",nroProgramacion);
					return lista;
				} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
				} finally {
					session.close();
				}
				return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_AgenciasBean> getListaAsientosReservadoXAgencia(int nroProgramacion) throws Exception {
		// TODO Auto-generated method stub
				SqlSession session = sqlMapper.openSession();
				List lista = new ArrayList<V_AgenciasBean>();
				try {
					lista = session.selectList(ProjectStr+"SQL_getListaAsientoReservadoXAgenciaView",nroProgramacion);
					return lista;
				} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
				} finally {
					session.close();
				}
				return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_AgenciasBean> getListaComida(int nroProgramacion) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<V_AgenciasBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_ObtenerComidas",nroProgramacion);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V_AgenciasBean> getListaAgenciasDisponibles(String ciudad,String tipo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List <V_AgenciasBean>lista = new ArrayList<V_AgenciasBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("ciudad", ciudad);
		parametros.put("tipo", tipo);
		
		try {
			lista = session.selectList(ProjectStr+"SQL_ObtenerAgenciasDisponibles",parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V_AgenciasBean> SQL_SELECT_AGENCIAS() throws Exception {
		
		SqlSession session = sqlMapper.openSession();
		List <V_AgenciasBean>lista = new ArrayList<V_AgenciasBean>();
		
		try {
			lista = session.selectList(ProjectStr+"SQL_SELECT_AGENCIAS");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

}
