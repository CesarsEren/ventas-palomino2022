package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_AgenciasWebIDao extends MapperSQL implements V_AgenciasWebDao {

	private static final Log log = LogFactory.getLog(V_AgenciasWebIDao.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V_AgenciasWebBean> listaAgenciasWeb(int limit, int offset, String query) throws Exception {
		SqlSession session = sqlMapper.openSession();
		List<V_AgenciasWebBean> listaAgencias = new ArrayList<V_AgenciasWebBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("limit", limit);
		parametros.put("offset", offset);
		
		try {
			listaAgencias = session.selectList(ProjectStr+"SQL_list_agencias_web", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V_AgenciasWebBean> listaAgenciasWeb() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List<V_AgenciasWebBean> listaAgencias = new ArrayList<V_AgenciasWebBean>();
		
		try {
			listaAgencias = session.selectList(ProjectStr+"");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
	}

	@Override
	public V_AgenciasWebBean nuevaAgencia(V_AgenciasWebBean bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		
		try {
			
			bean.setDireccion(bean.getDireccion().toUpperCase());
			bean.setPersonaContacto(bean.getPersonaContacto().toUpperCase()); 
			bean.setRazonSocial(bean.getRazonSocial().toUpperCase()); 
			
			session.insert(ProjectStr+"SQL_insert_agencia_web", bean);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally{
			session.close();
		}
		
		return bean;
	}

	@Override
	public V_AgenciasWebBean actualizarAgencia(V_AgenciasWebBean bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		
		try {
			session.update(ProjectStr+"SQL_update_agencia_web", bean);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally{
			session.close();
		}
		
		return bean;
	}
	
	@Override
	public V_AgenciasWebBean obtieneAgenciaXid(int id) throws Exception {
		SqlSession session = sqlMapper.openSession();
		V_AgenciasWebBean bean = null;
		
		try {
			bean = (V_AgenciasWebBean) session.selectOne(ProjectStr+"SQL_obtiene_agencia_web_x_id", id);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
	}
	
	@Override
	public int cuentaAgenciasTotales() throws Exception {
		SqlSession session = sqlMapper.openSession();
		Integer total = 0;
		
		try {
			total = (Integer) session.selectOne(ProjectStr+"SQL_count_agencia_web");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return total;
	}

	@Override
	public V_AgenciasWebBean cambiaEstadoAgencia(V_AgenciasWebBean bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		
		try {
			session.update(ProjectStr+"SQL_update_cambia_estado_agencia_web", bean.getId());
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally{
			session.close();
		}
		
		return bean;
	}
	
	public static void main(String[] args) throws Exception {
		V_AgenciasWebIDao a=  new V_AgenciasWebIDao();
		a.cuentaAgenciasTotales();
	}

	@Override
	public V_AgenciasWebBean obtieneAgenciaXCodigo(String Codigo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		V_AgenciasWebBean bean = null;
		
		try {
			bean = (V_AgenciasWebBean) session.selectOne(ProjectStr+"SQL_obtiene_agencia_web_x_Codigo", Codigo);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
	}

	@Override
	public int actualizarMontoVentaActual(String Codigo, double montoVentaActual) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Codigo", Codigo);
		parametros.put("montoVentaActual", montoVentaActual);
		
		try {
			resultado = session.update(ProjectStr+"SQL_update_monto_venta_actual_agencia_web",parametros);
			session.commit();
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return resultado;
	}

	@Override
	public int actualizarMontoVentaConfirmada(String Codigo, double montoVentaConfirmada) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Codigo", Codigo);
		parametros.put("montoVentaConfirmada", montoVentaConfirmada);
		
		try {
			resultado = session.update(ProjectStr+"SQL_update_monto_venta_confirmada_agencia_web",parametros);
			session.commit();
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V_AgenciasWebBean> ListaAgenciaxCodigo(String Codigo) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<V_AgenciasWebBean> listaAgencias = new ArrayList<V_AgenciasWebBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Codigo", Codigo);
		
		try {
			listaAgencias = session.selectList(ProjectStr+"SQL_LIST_AGENCIA_X_CODIGO_AGENCIA_WEB", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
	}

	@Override
	public V_AgenciasWebBean ListaAgenciaxRuc(String ruc) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		V_AgenciasWebBean bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("ruc", ruc);
		
		try {
			bean = (V_AgenciasWebBean) session.selectOne(ProjectStr+"SQL_LIST_AGENCIA_X_RUC_AGENCIA_WEB", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally{
			session.close();
		}
		
		return bean;
	}

	
}
