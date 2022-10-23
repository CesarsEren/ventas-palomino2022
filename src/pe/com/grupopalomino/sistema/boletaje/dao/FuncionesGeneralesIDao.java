package pe.com.grupopalomino.sistema.boletaje.dao;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import pe.com.grupopalomino.sistema.boletaje.util.Utils;



public class FuncionesGeneralesIDao extends MapperSQL implements FuncionesGeneralesDao {

	private static final Log log = LogFactory.getLog(FuncionesGeneralesIDao.class);
	@Override
	public String FechaServidor() throws Exception {
		// TODO Auto-generated method stub
		String fechaServer = "";
		SqlSession session = sqlMapper.openSession();
		
		try {
			fechaServer = (String) session.selectOne(ProjectStr+"SQL_ObtenerFechaServidor");
			
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return fechaServer;
	}

	@Override
	public String ValidaRangoFecha(String fechaInicial, String fechaFinal) throws Exception {
		// TODO Auto-generated method stub
		
		String Resultado = "";
		SqlSession session = sqlMapper.openSession();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("fechaInicial", fechaInicial);
			parametros.put("fechaFinal", fechaFinal);
			
			Resultado = (String) session.selectOne(ProjectStr+"SQL_ValidaRangoFecha",parametros);
			return Resultado;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		
		return Resultado;
	}

}
