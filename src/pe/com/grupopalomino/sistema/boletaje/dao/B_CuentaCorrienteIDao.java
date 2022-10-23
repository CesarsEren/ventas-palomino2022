package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;



public class B_CuentaCorrienteIDao extends MapperSQL implements B_CuentaCorrienteDao {
	
	private static final Log log = LogFactory.getLog(B_CuentaCorrienteIDao.class);
	
	@Override
	public int insertCuentaCorriente(B_CuentaCorrienteBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			log.info("ENTRANDO AL DAO" + bean.getEticketVisa());
			resultado = session.insert(ProjectStr+"SQL_RegistroCuentaCorriente", bean);
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
	public List<B_CuentaCorrienteBean> ListaCuentaCorriente(B_CuentaCorrienteBean bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B_CuentaCorrienteBean> EstadoCuentaCorriente(String RUC,String Tipo,String FechaInicial,String FechaFinal,int offset , int limit,String Usuario) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<B_CuentaCorrienteBean> lista = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("RUC", RUC);
		parametros.put("Tipo", Tipo);
		parametros.put("FechaInicial", FechaInicial);
		parametros.put("FechaFinal", FechaFinal);
		parametros.put("offset", offset);
		parametros.put("limit", limit);
		parametros.put("Usuario", Usuario);
		
		try {
			lista = session.selectList(ProjectStr+"SQL_getEstadoCuentaView",parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int deleteCuentaCorriente(int Nro,int NroBol) throws Exception {
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("NroBol", NroBol);
		try {
			resultado = session.delete(ProjectStr+"SQL_DeleteCuentaCorriente", parametros);
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
	public int updateCuentaCorrienteConfirmada(int Nro, int NroBol,String estado) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("NroBol", NroBol);
		parametros.put("estado", estado);
		try {
			resultado = session.update(ProjectStr+"SQL_UpdateCuentaCorrienteConfirmada", parametros);
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
	public int EstadoCountCuentaCorriente(String RUC, String Tipo, String FechaInicial,
			String FechaFinal,String Usuario) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int Cantidad = 0;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("RUC", RUC);
		parametros.put("Tipo", Tipo);
		parametros.put("FechaInicial", FechaInicial);
		parametros.put("FechaFinal", FechaFinal);
		parametros.put("Usuario", Usuario);
		
		try {
			Cantidad = (int)session.selectOne(ProjectStr+"SQL_getCountEstadoCuentaView",parametros);
			return Cantidad;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return Cantidad;
	}

	@Override
	public double VentasRealizadasCuentaCorriente(String RUC,String Usuario) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		double monto = 0;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("RUC", RUC);
		parametros.put("Usuario", Usuario);
		
		
		try {
			monto = (double)session.selectOne(ProjectStr+"SQL_getVentasrealizadas",parametros);
			return monto;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return monto;
	}

	@Override
	public int deleteCuentaCorrientePagoEfectivo(int NroBol) throws Exception {

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("NroBol", NroBol);
		try {
			resultado = session.delete(ProjectStr+"SQL_DeleteCuentaCorrientePagoEfectivo", parametros);
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
	public B_CuentaCorrienteBean ListaVentaPagoEfectivoCuentaCorriente(int NroBol) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		B_CuentaCorrienteBean bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("NroBol", NroBol);
		
		try {
			bean = (B_CuentaCorrienteBean) session.selectOne(ProjectStr+"SQL_SelectVentaPagoEfectivoCuentaCorriente", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
	}

}
