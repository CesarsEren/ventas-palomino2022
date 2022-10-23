package pe.com.grupopalomino.sistema.boletaje.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;


import pe.com.grupopalomino.sistema.boletaje.bean.V_ClientesBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_ClientesIDao extends MapperSQL implements V_ClientesDao {

	private static final Log log = LogFactory.getLog(V_ClientesIDao.class);

	@Override
	public V_ClientesBean listClientesRUC(String RUC) throws Exception {
		SqlSession session = sqlMapper.openSession(); /* Inicia comunicación MyBatis */
		V_ClientesBean bean = null;
		try {
			bean = (V_ClientesBean) session.selectOne(ProjectStr + "SQL_ObtenerClienteRUC", RUC);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int InertClientes(V_ClientesBean bean) throws Exception {
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			resultado = session.insert(ProjectStr + "SQL_RegistroCliente", bean);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> listaHistorialVentas(String username) {
		SqlSession session = sqlMapper.openSession();
		
		List<Map<String, String>> resultado = new ArrayList<Map<String, String>>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfTotal = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		try {
			resultado = session.selectList(ProjectStr + "SQL_Lista_Historial_Compras", username);
			
			for (Map<String, String> map : resultado) {
				map.put("FechaViaje", sdf.format(map.get("FechaViaje")));
				map.put("AUDI_fecha", sdfTotal.format(map.get("AUDI_fecha")));
			}
			
			return resultado;
		} 
		catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		finally {
			session.close();
		}
		
		return resultado;
	}
	
}
