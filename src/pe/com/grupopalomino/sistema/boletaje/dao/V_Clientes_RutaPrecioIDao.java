package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Clientes_RutaPrecioBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_Clientes_RutaPrecioIDao extends MapperSQL implements V_Clientes_RutaPrecioDao{
	
	private static final Log log = LogFactory.getLog(V_Clientes_RutaPrecioIDao.class);

	@Override
	public int insertRutaPrecio(V_Clientes_RutaPrecioBean bean) throws Exception {
		// TODO Auto-generated method stub

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			resultado = session.insert(ProjectStr+"SQL_InsertRutaPrecioCliente", bean);
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
	public int updateRutaPrecio(V_Clientes_RutaPrecioBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
	
		try {
			resultado = session.update(ProjectStr+"SQL_UpdateRutaPrecioCliente",bean);
			
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
	public List<V_Clientes_RutaPrecioBean> listaClientesRutas(String query , int  limit,int offset) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<V_Clientes_RutaPrecioBean> listaAgencias = new ArrayList<V_Clientes_RutaPrecioBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("limit", limit);
		parametros.put("offset", offset);
		
		try {
			listaAgencias = session.selectList(ProjectStr+"SQL_SelectRutaPrecioCliente", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
	}

	@Override
	public int cuentaClienteRutaPrecio() throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		int clienterutasTotales = 0;
		
		try {
			clienterutasTotales = (int) session.selectOne(ProjectStr+"SQL_SelectRutaClienteCount");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return clienterutasTotales;
	}

	@Override
	public V_Clientes_RutaPrecioBean listaClientesRutas(int Id) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		V_Clientes_RutaPrecioBean listaAgencias = new V_Clientes_RutaPrecioBean();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Id", Id);
		
		try {
			listaAgencias = (V_Clientes_RutaPrecioBean) session.selectOne(ProjectStr+"SQL_SelectRutaPrecioClienteId", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
	}

	@Override
	public V_Clientes_RutaPrecioBean listaClientesRutasVerificacion(String Ruc, int NroRuta, String NroServicio)
			throws Exception {
		// TODO Auto-generated method stub
			SqlSession session = sqlMapper.openSession();
			V_Clientes_RutaPrecioBean listaAgencias = new V_Clientes_RutaPrecioBean();
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Ruc", Ruc);
			parametros.put("NroRuta", NroRuta);
			parametros.put("NroServicio", NroServicio);
			
			try {
				listaAgencias = (V_Clientes_RutaPrecioBean) session.selectOne(ProjectStr+"SQL_SelectRutaPrecioClienteVerificacion", parametros);
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			} finally{
				session.close();
			}
			
			return listaAgencias;
	}

}
