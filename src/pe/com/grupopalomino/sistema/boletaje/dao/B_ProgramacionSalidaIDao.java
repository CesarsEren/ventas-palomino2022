package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.Precio_Desde;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_ProgramacionSalidaIDao extends MapperSQL implements B_ProgramacionSalidaDao {

	private static final Log log = LogFactory.getLog(B_ProgramacionSalidaIDao.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<B_ProgramacionSalidaBean> getSalidas(String fecha,String rol,String origenAgencia,String destino,Integer promocionWeb) throws Exception {
		// TODO Auto-generated method stub
				SqlSession session = sqlMapper.openSession();
				@SuppressWarnings("rawtypes")
				List lista = new ArrayList<B_ProgramacionSalidaBean>();
				
				Map<String, Object> parametros = new HashMap<>();
				parametros.put("Fecha", fecha);
				parametros.put("rol", rol);
				parametros.put("origenAgencia", origenAgencia);
				parametros.put("destino", destino);
				parametros.put("promocionWeb", promocionWeb);
				
				try {
					lista = session.selectList(ProjectStr+"SQL_getProgramacionSalidaView",parametros);
					
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
	public List<Precio_Desde> getPrecioDesde(Integer programacion) throws Exception {
		// TODO Auto-generated method stub
				SqlSession session = sqlMapper.openSession();
				@SuppressWarnings("rawtypes")
				List lista = new ArrayList<Precio_Desde>();
				
				Map<String, Object> parametros = new HashMap<>();
				parametros.put("programacion", programacion);
				
				
				try {
					lista = session.selectList(ProjectStr+"SQL_getProgramacionPdesdeView",parametros);
					
					return lista;
				} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
				} finally {
					session.close();
				}
				return null;
	}

	@Override
	public B_ProgramacionSalidaBean getSalidasNro(int Nro) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_ProgramacionSalidaBean bean= new B_ProgramacionSalidaBean();
		
		try {
			bean = (B_ProgramacionSalidaBean)session.selectOne(ProjectStr+"SQL_getProgramacionSalidaViewNro",Nro);
			
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
	public List<B_ProgramacionSalidaBean> getSalidasAgencias(int Nro,String origenAgencia) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		@SuppressWarnings("rawtypes")
		List lista = new ArrayList<B_ProgramacionSalidaBean>();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("Nro", Nro);
		parametros.put("origenAgencia", origenAgencia);
		
		try {
			lista = session.selectList(ProjectStr+"SQL_getProgramacionSalidaAgenciasView",parametros);
			
			return lista;
		} catch (Exception e) {
			log.info("SALIDA QUE GENERA ERROR -->"+ Nro); 
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<B_ProgramacionSalidaBean> getSalidasCiudades(String fecha, String origenCiudad,String destinoCiudad)
			throws Exception {
		
		SqlSession session = sqlMapper.openSession();
		List<B_ProgramacionSalidaBean> lista = new ArrayList<B_ProgramacionSalidaBean>();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("Fecha", fecha);
		parametros.put("origenCiudad", origenCiudad);
		parametros.put("destinoCiudad", destinoCiudad);
		
		try {
			lista = session.selectList(ProjectStr+"SQL_getProgramacionSalidaCiudades",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}
	
	@Override
	public B_ProgramacionSalidaBean getSalidasPrecioPromocion(int Nro,String destino,Integer promocionWeb) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		B_ProgramacionSalidaBean bean= new B_ProgramacionSalidaBean();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("Nro", Nro);
		parametros.put("destino", destino);
		parametros.put("promocionWeb", promocionWeb);
		
		try {
			bean = (B_ProgramacionSalidaBean)session.selectOne(ProjectStr+"SQL_getProgramacionSalidaPrecioPromocion",parametros);
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}

	

}
