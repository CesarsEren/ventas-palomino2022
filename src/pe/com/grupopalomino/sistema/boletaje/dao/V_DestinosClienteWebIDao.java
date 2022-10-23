package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosClienteWebBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_DestinosClienteWebIDao extends MapperSQL implements V_DestinosClienteWebDao{
	
	private static final Log log = LogFactory.getLog(V_DestinosClienteWebIDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<V_DestinosClienteWebBean> obtieneOrigenClientes(String Ruc) throws Exception {
		
		
		SqlSession session = sqlMapper.openSession();
		List<V_DestinosClienteWebBean> lista = new ArrayList<V_DestinosClienteWebBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Ruc", Ruc);
		
		try {
			lista =  session.selectList(ProjectStr+"SQL_ObtieneOrigenClienteWeb",parametros);
			
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
		
	}

	@Override
	public V_DestinosClienteWebBean verificaDestinoCliente(String Ruc) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		V_DestinosClienteWebBean bean = new V_DestinosClienteWebBean();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Ruc", Ruc);
		
		try {
			bean =  (V_DestinosClienteWebBean) session.selectOne(ProjectStr+"SQL_VerificaDestinosClienteWeb",parametros);
			
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
	public List<V_DestinosClienteWebBean> obtieneDestinoClientes(String Origen,String Ruc) throws Exception {
		
		SqlSession session = sqlMapper.openSession();
		List<V_DestinosClienteWebBean> lista = new ArrayList<V_DestinosClienteWebBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Origen", Origen);
		parametros.put("Ruc", Ruc);
		
		try {
			lista =  session.selectList(ProjectStr+"SQL_ObtieneDestinoClienteWeb",parametros);
			
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

}
