package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBeanDetalle;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;


public class B_AtencionReclamosIDao extends MapperSQL implements B_AtencionReclamosDao{

	private static final Log log = LogFactory.getLog(B_AtencionReclamosIDao.class);
	@Override
	public int registraReclamos(B_AtencionReclamosBean bean) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			resultado = session.insert(ProjectStr+"SQL_RegistroAtencionReclamos", bean);
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
	public B_AtencionReclamosBean selecReclamos(int Nro,int Periodo,String Empresa,String AtencionReclamos) throws Exception {
		
			SqlSession session = sqlMapper.openSession();
			B_AtencionReclamosBean bean = null;
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Nro", Nro);
			parametros.put("Periodo", Periodo);
			parametros.put("Empresa", Empresa);
			parametros.put("AtencionReclamos", AtencionReclamos);
			
			try {
				bean =  (B_AtencionReclamosBean) session.selectOne(ProjectStr+"SQL_ObtenerAtencionReclamos",parametros);
				return bean;
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			} finally {
				session.close();
			}
			return null;
	}
	@Override
	public B_AtencionReclamosBeanDetalle selecReclamosDetalle(String Serie, String Numero, String Documento,String Empresa) throws Exception {
		// TODO Auto-generated method stub
		

		SqlSession session = sqlMapper.openSession();
		B_AtencionReclamosBeanDetalle bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Serie", Serie);
		parametros.put("Numero", Numero);
		parametros.put("Documento", Documento);
		parametros.put("Empresa", Empresa);
		
		try {
			bean =  (B_AtencionReclamosBeanDetalle) session.selectOne(ProjectStr+"SQL_ObtenerAtencionReclamosDetalle",parametros);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<B_AtencionReclamosBean> selecReclamos(String query, Integer limit, Integer offset,String tipo,String agencia,String servicio) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<B_AtencionReclamosBean> bean = new ArrayList<>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("offset", offset);
		parametros.put("limit", limit);
		parametros.put("tipo", tipo);
		parametros.put("agencia", agencia);
		parametros.put("servicio", servicio);
		
		
		try {
			bean =  (List<B_AtencionReclamosBean>) session.selectList(ProjectStr+"SQL_ObtenerAtencionListaReclamos",parametros);
			//return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}
	@Override
	public int selectReclamosTotales(String tipo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int reclamosTotales = 0;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("tipo", tipo);
		
		try {
			reclamosTotales = (int) session.selectOne(ProjectStr+"SQL_list_count_atencionreclamos_web",parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return reclamosTotales;
	}
	@Override
	public int updateReclamos(Integer Id,String detalle) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int resultado = -1; 
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("Id", Id);
		parametros.put("detalle", detalle);
		
		try {
			resultado = session.update(ProjectStr+"SQL_update_atencion_reclamos", parametros);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<B_AtencionReclamosBean> selecReclamosReporte(String tiporeclamo) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<B_AtencionReclamosBean> bean = new ArrayList<>();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("tiporeclamo", tiporeclamo);
		
		try {
			bean =  (List<B_AtencionReclamosBean>) session.selectList(ProjectStr+"SQL_ListaAtencionReclamosReporte",parametros);
			//return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<B_AtencionReclamosBean> selecReclamosReporte(String query, Integer limit, Integer offset)
			throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<B_AtencionReclamosBean> bean = new ArrayList<>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("offset", offset);
		parametros.put("limit", limit);
		
		try {
			bean =  (List<B_AtencionReclamosBean>) session.selectList(ProjectStr+"SQL_ListaAtencionReclamosReporteTabla",parametros);
			//return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<B_AtencionReclamosBean> selecReclamosDni(String query, Integer limit, Integer offset, String tipo,
			String agencia, String servicio, String dni) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<B_AtencionReclamosBean> bean = new ArrayList<>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("offset", offset);
		parametros.put("limit", limit);
		parametros.put("tipo", tipo);
		parametros.put("agencia", agencia);
		parametros.put("servicio", servicio);
		parametros.put("dni", dni);
		
		
		try {
			bean =  (List<B_AtencionReclamosBean>) session.selectList(ProjectStr+"SQL_ObtenerAtencionListaReclamosDni",parametros);
			//return bean;
		} catch (Exception e) {
			
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> ObtenerAtencionListaReclamosRpte() {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<Map<String,Object>> bean = new ArrayList<>();		
		try {
			bean =  (List<Map<String,Object>>) session.selectList(ProjectStr+"SQL_ObtenerAtencionListaReclamosRpte");
			//return bean;
		} catch (Exception e) {			
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}

	 
	

}
