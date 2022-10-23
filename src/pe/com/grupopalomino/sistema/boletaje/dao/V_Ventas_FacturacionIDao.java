package pe.com.grupopalomino.sistema.boletaje.dao;


import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_Ventas_FacturacionIDao extends MapperSQL implements V_Ventas_FacturacionDao{

	private static final Log log = LogFactory.getLog(V_Ventas_FacturacionIDao.class);
	
	@Override
	public int SQL_UPDATE_VENTAS_FACTURACION_ERROR(Integer nro, String tipoOperacion,String codigoError) throws Exception {
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nro", nro);
		parametros.put("tipoOperacion", tipoOperacion);
		parametros.put("codigoError", codigoError);
		
		try {
			resultado = session.update(ProjectStr+"SQL_UPDATE_VENTAS_FACTURACION_ERROR",parametros);
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
	public V_Ventas_FacturacionBean SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer nro, String tipoOperacion)
			throws Exception {
		
			SqlSession session = sqlMapper.openSession();
			
			V_Ventas_FacturacionBean mapa = new V_Ventas_FacturacionBean();
			
			try {
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("nro", nro);
				parametros.put("tipoOperacion", tipoOperacion);
				
				mapa = (V_Ventas_FacturacionBean) session.selectOne(ProjectStr+"SQL_SELECT_VENTAS_IMAGES_FACTURACION",parametros);
				
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			} finally {
				session.close();
			}
			
			return mapa;
	}

	@Override
	public int SQL_UPDATE_VENTAS_FACTURACION_IMAGES(Integer nro, String tipoOperacion, String codigohash,byte[] imageBarras, byte[] imageQR) throws Exception {
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nro", nro);
		parametros.put("tipoOperacion", tipoOperacion);
		parametros.put("codigohash", codigohash);
		parametros.put("imageBarras", imageBarras);
		parametros.put("imageQR", imageQR);
		
		try {
			resultado = session.update(ProjectStr+"SQL_UPDATE_VENTAS_FACTURACION_IMAGES",parametros);
			session.commit();
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return resultado;
	}

		
}
