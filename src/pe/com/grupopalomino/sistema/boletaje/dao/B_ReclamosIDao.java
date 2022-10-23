package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.HashMap; 
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBeanDetalle;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;


public class B_ReclamosIDao extends MapperSQL implements B_ReclamosDao{

	private static final Log log = LogFactory.getLog(B_ReclamosIDao.class);
	@Override
	public int registraReclamos(B_ReclamosBean bean) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			resultado = session.insert(ProjectStr+"SQL_RegistroReclamos", bean);
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
	public B_ReclamosBean selecReclamos(int Nro,int Periodo,String Empresa) throws Exception {
		
			SqlSession session = sqlMapper.openSession();
			B_ReclamosBean bean = null;
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Nro", Nro);
			parametros.put("Periodo", Periodo);
			parametros.put("Empresa", Empresa);
			
			try {
				bean =  (B_ReclamosBean) session.selectOne(ProjectStr+"SQL_ObtenerReclamos",parametros);
				return bean;
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			} finally {
				session.close();
			}
			return null;
	}
	@Override
	public B_ReclamosBeanDetalle selecReclamosDetalle(String Serie, String Numero, String Documento,String Empresa) throws Exception {
		// TODO Auto-generated method stub
		

		SqlSession session = sqlMapper.openSession();
		B_ReclamosBeanDetalle bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Serie", Serie);
		parametros.put("Numero", Numero);
		parametros.put("Documento", Documento);
		parametros.put("Empresa", Empresa);
		
		try {
			bean =  (B_ReclamosBeanDetalle) session.selectOne(ProjectStr+"SQL_ObtenerReclamosDetalle",parametros);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
		
	}
	

	
	

}
