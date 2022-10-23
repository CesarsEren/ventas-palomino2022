package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_FacturacionIDao extends MapperSQL implements B_FacturacionDao {
	
	private static final Log log = LogFactory.getLog(B_FacturacionIDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> SQL_SELECT_VENTA_ENVIO_FACTURADOR(String empresa, String fechaEmision,
			Integer documentos,int limit,int offset) {
		
		SqlSession session = sqlMapper.openSession();
		
		List<Map<String, Object>> mapa = new ArrayList<Map<String, Object>>();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("empresa", empresa);
			parametros.put("fechaEmision", fechaEmision);
			parametros.put("documentos", documentos);
			parametros.put("limit", limit);
			parametros.put("offset", offset);
			
			mapa = (List<Map<String, Object>>) session.selectList(ProjectStr+"SQL_SELECT_VENTA_ENVIO_FACTURADOR",parametros);
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		
		return mapa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> SQL_SELECT_VENTA_FACTURADOR(Integer nro, String tipoOperacion) {
		
		SqlSession session = sqlMapper.openSession();
		
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("nro", nro);
			parametros.put("tipoOperacion", tipoOperacion);
			//Bus_Carga
			mapa = (Map<String, Object>) session.selectOne(ProjectStr+"SQL_SELECT_VENTA_FACTURADOR",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}finally {
			session.close();
		}
		return mapa;
	}

	@Override
	public int SQL_UPDATE_VENTA_FACTURADOR(Integer nro, String tipoOperacion, Integer envioSunat,String respuestaSunat) {
		
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		int resultado = -1;
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("nro", nro);
			parametros.put("tipoOperacion", tipoOperacion);
			parametros.put("envioSunat", envioSunat);
			parametros.put("respuestaSunat", respuestaSunat);			
			
			resultado = session.update(ProjectStr+"SQL_UPDATE_VENTA_FACTURADOR",parametros);
			
			session.commit();
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally {
			session.close();
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> SQL_SELECT_VENTA_DOCUMENTO_ELECTRONICO(String empresa, String fechaEmision, String serie,
			String numero, String tipodocumento,String ruc, Integer limit, Integer offset) {
		SqlSession session = sqlMapper.openSession();
		
		List<Map<String, Object>> mapa = new ArrayList<Map<String, Object>>();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("empresa", empresa);
			parametros.put("fechaEmision", fechaEmision);
			parametros.put("serie", serie);
			parametros.put("numero", numero);
			parametros.put("tipodocumento", tipodocumento);
			parametros.put("ruc", ruc);
			parametros.put("limit", limit);
			parametros.put("offset", offset);
			
			mapa = (List<Map<String, Object>>) session.selectList(ProjectStr+"SQL_SELECT_VENTA_DOCUMENTO_ELECTRONICO",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}finally {
			session.close();
		}
		return mapa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> SQL_SELECT_VENTA_APROBADA_FACTURADOR(Integer nro, String tipoOperacion) {
		SqlSession session = sqlMapper.openSession();
		
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("nro", nro);
			parametros.put("tipoOperacion", tipoOperacion);
			
			mapa = (Map<String, Object>) session.selectOne(ProjectStr+"SQL_SELECT_VENTA_APROBADA_FACTURADOR",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}finally {
			session.close();
		}
		return mapa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> SQL_SELECT_VENTA_DESCARGA_FACTURADOR_SQL(String empresa, String fechaEmision,
			String serie, String numero, String tipodocumento, String ruc, double monto) {
		
		SqlSession session = sqlMapper.openSession();
		
		List<Map<String, Object>> mapa = new ArrayList<Map<String, Object>>();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("empresa", empresa);
			parametros.put("fechaEmision", fechaEmision);
			parametros.put("serie", serie);
			parametros.put("numero", numero);
			parametros.put("tipodocumento", tipodocumento);
			parametros.put("ruc", ruc);
			parametros.put("monto", monto);
			
			mapa = (List<Map<String, Object>>) session.selectList(ProjectStr+"SQL_SELECT_VENTA_DESCARGA_FACTURADOR_SQL",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}finally {
			session.close();
		}
		return mapa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer nro, String tipoOperacion) {
		
		SqlSession session = sqlMapper.openSession();
		
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("nro", nro);
			parametros.put("tipoOperacion", tipoOperacion);			
			mapa = (Map<String, Object>) session.selectOne(ProjectStr+"SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR",parametros);			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}finally {
			session.close();
		}
		return mapa;
	}

	@Override
	public int USP_GENERARBOLETO(String nro, String terminal, int documento,String EmpresaID) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		int resultado = -1; 
		try { 
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("nro", nro);  
			parametros.put("terminal", terminal);
			parametros.put("documento", documento);
			parametros.put("EmpresaID", EmpresaID);
			resultado = session.update(ProjectStr+"SQL_GENERAR_BOLETO",parametros); 
			session.commit(); 
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally {
			session.close();
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> SQL_SELECT_DOCUMENTO_ELECTRONICO(String emp, String nro, String tipo) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		Map<String,Object> resultado = new HashMap<>(); 
		try { 
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("nro", nro);  
			parametros.put("emp", emp);
			parametros.put("tipo", tipo);
			resultado = (Map<String, Object>) session.selectOne(ProjectStr+"SQL_SELECT_DOCUMENTO_ELECTRONICO",parametros); 
			session.commit(); 
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public Map<String, Object> SQL_UPDATE_DOCUMENTO(String ruc, String razon, String dni, String nombre,String nro,String tipo) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		int resultado = -1; 
		try { 
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("ruc", ruc);  
			parametros.put("razon", razon);
			parametros.put("dni", dni);
			parametros.put("nombre", nombre);
			parametros.put("nro", nro);
			parametros.put("tipo", tipo);
			resultado = session.update(ProjectStr+"SQL_UPDATE_DOCUMENTO",parametros); 
			session.commit(); 
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally {
			session.close();
		}
		Map<String,Object> mapa = new HashMap<>();
		mapa.put("resultado",resultado);
		return mapa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> SQL_SELECT_VENTA_ENVIO_FACTURADOR_AUTOMATICO(String empresa, String fechaEmision,
			Integer documentos, int limit, int offset) {
		// TODO Auto-generated method stub
	SqlSession session = sqlMapper.openSession();
		
		List<Map<String, Object>> mapa = new ArrayList<Map<String, Object>>();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("empresa", empresa);
			parametros.put("fechaEmision", fechaEmision);
			parametros.put("documentos", documentos);
			parametros.put("limit", limit);
			parametros.put("offset", offset);
			
			mapa = (List<Map<String, Object>>) session.selectList(ProjectStr+"SQL_SELECT_VENTA_ENVIO_FACTURADOR_AUTOMATICO",parametros);
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		
		return mapa;
		}

}
