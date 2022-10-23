package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.dao.MapperSQL;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.bean.B_BoletosRedBusEliminados;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;

public class B_VentaBoletajeIDao extends MapperSQL implements B_VentaBoletajeDao {

	private static final Log log = LogFactory.getLog(B_VentaBoletajeIDao.class);

	@Override
	public B_VentaBean getBoleto(String serie, String numero) {
		// TODO Auto-generated method stub
		return new B_VentaBean();
	}

	@Override
	public int insertVenta(B_VentaBean bean) throws Exception {
		// TODO Auto-generated method stub

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {			
			resultado = session.insert(ProjectStr + "SQL_RegistroVentaBoletaje", bean);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public B_VentaBean getVentaNroAsientoOcupado(int nroProgramacion, String Asiento) throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nroProgramacion", nroProgramacion);
		parametros.put("asiento", Asiento);
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_Obtiene_Venta_Asiento_Ocupado", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}

	@Override
	public int cuentaAsientosOcupadosPorVenta(int nroProgramacion) throws Exception {

		SqlSession session = sqlMapper.openSession();

		int asientosOcupados = 0;

		try {
			asientosOcupados = (int) session.selectOne(ProjectStr + "SQL_Cuenta_Asiento_Ocupados_Por_Venta",
					nroProgramacion);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return asientosOcupados;
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * B_VentaBoletajeIDao a = new B_VentaBoletajeIDao();
	 * System.out.println(a.cuentaAsientosOcupadosPorVenta(263712)); }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<B_VentaBean> getVentaRealizada(String usuario, int offset, int limit) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		@SuppressWarnings("rawtypes")
		List lista = new ArrayList<B_VentaBean>();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("usuario", usuario);
		parametros.put("offset", offset);
		parametros.put("limit", limit);

		try {
			lista = session.selectList(ProjectStr + "SQL_getVentasRealizadasView", parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public B_VentaBean getVentaReImprimir(String Usuario, int Nro, int Salida) throws Exception {
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("usuario", Usuario);
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_getVentasRealizadasReImprimir", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}

	@Override
	public B_VentaBean getVentaImprimir(String Usuario, int Nro, int Salida) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("usuario", Usuario);
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_getVentasRealizadasImprimir", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}

	@Override
	public int deleteVenta(int Nro, int Salida) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);

		try {
			resultado = session.delete(ProjectStr + "SQL_DeleteVentaBoletaje", parametros);

			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int updateVenta(int Nro, int Salida, String Identidad, String IdentidadD, String DNI, String Nombre,
			String Edad, String Telefono, String Ruc, String Razon, String Origen, String OrigenD, String Destino1,
			String Destino1D, String HoraViaje) throws Exception {

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("Identidad", Identidad);
		parametros.put("IdentidadD", IdentidadD);
		parametros.put("DNI", DNI);
		parametros.put("Nombre", Nombre);
		parametros.put("Edad", Edad);
		parametros.put("Telefono", Telefono);
		parametros.put("Ruc", Ruc);
		parametros.put("Razon", Razon);
		parametros.put("Origen", Origen);
		parametros.put("OrigenD", OrigenD);
		parametros.put("Destino1", Destino1);
		parametros.put("Destino1D", Destino1D);
		parametros.put("HoraViaje", HoraViaje);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateVentaBoletaje", parametros);

			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int updateVentaConfirmada(int Nro, int Salida, String EstadoWeb, String EticketVisa, String Usuario,
			String NumeroTarjeta, String TarjetaHabiente, String Eticket, int Medio_Pago) throws Exception {
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("EstadoWeb", EstadoWeb);
		parametros.put("EticketVisa", EticketVisa);
		parametros.put("Usuario", Usuario);
		parametros.put("NumeroTarjeta", NumeroTarjeta);
		parametros.put("TarjetaHabiente", TarjetaHabiente);
		parametros.put("Eticket", Eticket);
		parametros.put("Medio_Pago", Medio_Pago);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateVentaBoletajeConfirmado", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int deleteVentaNoConfirmada(String EstadoWeb) throws Exception {
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("EstadoWeb", EstadoWeb);

		try {
			resultado = session.delete(ProjectStr + "SQL_DeleteVentaBoletajeXestado", parametros);

			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int updateVentaNoConfirmada(String EstadoWeb) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("EstadoWeb", EstadoWeb);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateVentaBoletajeNoConfirmado", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int ListaCountVentasRealizadas(String usuario) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int Cantidad = 0;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("usuario", usuario);

		try {
			Cantidad = (int) session.selectOne(ProjectStr + "SQL_getCountVentasRealizadasImprimir", parametros);
			return Cantidad;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return Cantidad;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<B_VentaBean> getVentaImprimirVisa(String EticketVisa) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();

		List lista = new ArrayList<B_VentaBean>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("EticketVisa", EticketVisa);
		try {
			lista = session.selectList(ProjectStr + "SQL_getVentasRealizadasVisaImprimir", parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public int updateTiempoVisaVentaNoConfirmada(int Nro, int Salida, String ClienteVisa) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("ClienteVisa", ClienteVisa);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateTiempoVisa_Venta", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public B_VentaBean selectVentaVisa(int Nro, int Salida) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_Select_Venta_Visa", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}
	
	@Override
	public B_VentaBean selectVentaOpenpay(int Nro, int Salida) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_Select_Venta_Openpay", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}
	
	@Override
	public B_VentaBean selectVentaEmbarcar() {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;	
		
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_Select_Venta_Embarcar");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}

	@Override
	public int updateTicketVenta(int Nro, int Salida, String Eticket) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("Eticket", Eticket);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateTicket_Venta", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public B_VentaBean selectImprimirVoucherCliente(int Nro, int Salida) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_Select_Imprimir_Voucher_Cliente_B_Venta",
					parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}

	@Override
	public int updateKilometrajePasajeroVenta() throws Exception {

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateKilometrajePasajero");

			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	public static void main(String[] args) throws Exception {

		// B_VentaBoletajeIDao a = new B_VentaBoletajeIDao();
		// System.out.println(a.cuentaAsientosOcupadosPorVenta(263712));
		List<B_VentaBean> temporal = new B_VentaBoletajeIDao().getVentaImprimirPagoEfectivo("00000021534130");
		for (B_VentaBean temp : temporal) {
			System.out.println(temp.getSalida() + " 1");
			System.out.println(temp.getNombre() + "2");
			System.out.println(temp.getNroDestino() + "3");
			System.out.println(temp.getNroServicio() + "4 ");
			System.out.println(temp.getUsuarioVisa() + "5");
			System.out.println(temp.getRuc() + "6");

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B_VentaBean> SelectListVentaPagoEfectivo(String NroCIP) throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		@SuppressWarnings("rawtypes")
		List lista = new ArrayList<B_VentaBean>();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("NroCIP", NroCIP);

		try {
			lista = session.selectList(ProjectStr + "SQL_SelectListVentaBoletajePagoEfectivo", parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public B_VentaBean SelectVentaDatosPagoEfectivo(int Nro, int Salida, String TokenCIP) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("TokenCIP", TokenCIP);
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_Select_Venta_Datos_PagoEfectivo", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}
	//SQL_Select_Venta_Datos_PagoEfectivo
	@Override
	public B_VentaBean SelectVentaDatosOpenPay(int Nro, int Salida, String TokenCIP) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("TokenCIP", TokenCIP);
		
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_Select_Venta_Datos_OpenPay", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}

	@Override
	public int updateVentaPagoEfectivo(int Nro, int Salida, String NroCIP, String EstadoWeb, String Usuario,
			int NroOrden) throws Exception {
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("NroCIP", NroCIP);
		parametros.put("EstadoWeb", EstadoWeb);
		parametros.put("Usuario", Usuario);
		parametros.put("NroOrden", NroOrden);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateVentaBoletajePagoEfectivo", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public B_VentaBean SelectVentaPagoEfectivo(int Nro, int Salida) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean bean = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		try {
			bean = (B_VentaBean) session.selectOne(ProjectStr + "SQL_Select_Venta_PagoEfectivo", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}

		return bean;
	}

	@Override
	public int updateVentaPagoEfectivoConfirmacion(int Nro, int Salida, String EstadoWeb, int MedioPago, int EstadoCIP)
			throws Exception {
		// TODO Auto-generated method stub

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("EstadoWeb", EstadoWeb);
		parametros.put("MedioPago", MedioPago);
		parametros.put("EstadoCIP", EstadoCIP);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateVentaBoletajePagoEfectivoConfirmacion", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int updateTiempoVentaPagoEfectivo(String NroCIP) throws Exception {
		// TODO Auto-generated method stub

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("NroCIP", NroCIP);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateTiempoPagoEfectivo", parametros);
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
	public List<B_VentaBean> getVentaImprimirPagoEfectivo(String NroCIP) throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		@SuppressWarnings("rawtypes")
		List lista = new ArrayList<B_VentaBean>();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("NroCIP", NroCIP);

		try {
			lista = session.selectList(ProjectStr + "SQL_Select_Venta_Imprimir_PagoEfectivo", parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int deleteVentaExtornoPagoEfectivo(int Nro, int Salida, String Eticket, String FechaHora, int Medio_Pago)
			throws Exception {

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("Eticket", Eticket);
		parametros.put("FechaHora", FechaHora);
		parametros.put("Medio_Pago", Medio_Pago);

		try {
			resultado = session.delete(ProjectStr + "SQL_DeleteVentaExotrnoPagoEfectivo", parametros);

			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int updateTiempoVentaCliente(int Nro, int Salida) throws Exception {

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateTiempoVentaCliente", parametros);
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
	public List<B_VentaBean> SelectVentaAgenteAdmin(String Ruc, int offset, int limit) throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		@SuppressWarnings("rawtypes")
		List lista = new ArrayList<B_VentaBean>();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Ruc", Ruc);
		parametros.put("offset", offset);
		parametros.put("limit", limit);

		try {
			lista = session.selectList(ProjectStr + "SQL_getVentasRealizadasAgenteAdmin", parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public int SelectCountVentaAgenteAdmin(String Ruc) throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		int Cantidad = 0;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Ruc", Ruc);

		try {
			Cantidad = (int) session.selectOne(ProjectStr + "SQL_getCountVentasRealizadasAgenteAdminImprimir",
					parametros);
			return Cantidad;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return Cantidad;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B_VentaBean> getVerificaVentaNoConfirmadaVisa(String Eticket) throws Exception {

		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();

		List<B_VentaBean> lista = new ArrayList<B_VentaBean>();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Eticket", Eticket);

		try {
			lista = session.selectList(ProjectStr + "SQL_Select_Verifica_Venta_No_Confirmada_Visa", parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B_VentaBean> SelectListVentasPendientesVisa() throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		List<B_VentaBean> lista = new ArrayList<B_VentaBean>();

		try {
			lista = session.selectList(ProjectStr + "SQL_Select_Ventas_Pendientes_Visa");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<B_VentaBean> SelectListVentasPendientesEmbarcar() throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		List<B_VentaBean> lista = new ArrayList<B_VentaBean>();

		try {
			lista = session.selectList(ProjectStr + "SQL_Select_Venta_Embarcar");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

	@Override
	public int UpdateVentasVisaAuditoria(String Eticket) throws Exception {
		// TODO Auto-generated method stub

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Eticket", Eticket);

		try {
			resultado = session.update(ProjectStr + "SQL_Update_Ventas_Visa_Auditoria", parametros);

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
	public List<B_VentaBean> SelectListVentasPendientesPagoEfectivo() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<B_VentaBean> lista = new ArrayList<B_VentaBean>();

		try {
			lista = session.selectList(ProjectStr + "SQL_Select_Ventas_Pendientes_PagoEfectivo");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

	@Override
	public int updateVentaPagoEfectivoVerificaConfirmacion(int Nro, int Salida, String EstadoWeb, int MedioPago,
			int EstadoCIP) throws Exception {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Nro", Nro);
		parametros.put("Salida", Salida);
		parametros.put("EstadoWeb", EstadoWeb);
		parametros.put("MedioPago", MedioPago);
		parametros.put("EstadoCIP", EstadoCIP);

		try {
			resultado = session.update(ProjectStr + "SQL_UpdateVentaBoletajePagoEfectivoVerificaConfirmacion",
					parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	/*------------------------>AÑADIDO PARA CUPONES<---------------------------Buscar en MapperVentaBoletaje--------*/
	@Override
	public int SQL_UpdatePrecioXCupon(String nro, String Salida, String precio, String cupon) {
		int resultado = -1;

		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("Nro", nro);
		parametros.put("Salida", Salida);
		parametros.put("precio", precio);
		parametros.put("cupon", cupon);
		try {
			resultado = session.update(ProjectStr + "SQL_UpdatePrecioXCupon", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return resultado;
	}
	/*
	 * public static void main(String[] args) { int resultado = new
	 * V_Ventas_FacturacionIDao().SQL_UpdatePrecioXCupon("3336982","472202",50);
	 * System.out.println("Resultado " +resultado); }
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<B_VentaBean> SelectListVentasPendientesOpenPay() throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		List<B_VentaBean> lista = new ArrayList<B_VentaBean>();

		try {
			lista = session.selectList(ProjectStr + "SQL_Select_Ventas_Pendientes_OpenPay");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B_VentaBean> SelectVentasTelefonicasXUsuario(boolean todos, String usuario, int limit, int offset) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		Map<String, Object> params = new HashMap<>();
		params.put("todos", todos);
		params.put("usuario", usuario);
		params.put("limit", limit);
		params.put("offset", offset);
		List<B_VentaBean> lista = new ArrayList<B_VentaBean>();
		try {
			lista = (List<B_VentaBean>) session.selectList(ProjectStr + "GetVentasTelefonicasXUsuario", params);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

	@Override
	public int SelectCountVentasTelefonicasXUsuario(boolean todos, String usuario) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int Cantidad = 0;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("todos", todos);
		parametros.put("usuario", usuario);
		try {
			Cantidad = (int) session.selectOne(ProjectStr + "GetCounVentasTelefonicasXUsuario", parametros);
			return Cantidad;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return Cantidad;
	}

	@Override
	public int liberarAsientoxNro(int nro, String username) {
		// TODO Auto-generated method stub
		int resultado = -1;
		Map<String, Object> params = new HashMap<>();
		params.put("nro", nro);
		params.put("username", username);
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		try {
			resultado = session.update(ProjectStr + "SQL_LiberarAsiento", params);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public B_VentaBean SelectISVentaRedBus(long nro) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_VentaBean rpta = null;// new B_VentaBean();
		try {
			rpta = (B_VentaBean) session.selectOne(ProjectStr + "SQL_GetIsVentaRedBus", nro);
			return rpta;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return rpta;
	}

	@Override
	public int InserBoletosRedBusLiberados(B_BoletosRedBusEliminados bean) {
		// TODO Auto-generated method stub
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		try {
			resultado = session.insert(ProjectStr + "InsertBoletosRedBusLiberados", bean);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public B_VentaBean SelectISVentaWeb(long nro, String DNI) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		Map<String, Object> params = new HashMap<>();
		params.put("Nro", nro);
		params.put("DNI", DNI);
		B_VentaBean rpta = null;// new B_VentaBean();
		try {
			rpta = (B_VentaBean) session.selectOne(ProjectStr + "SQL_GetIsVentaWeb", params);
			return rpta;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return rpta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B_VentaBean> SelectVentasPagoEfectivoReconfirmar(String eticket) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<B_VentaBean> lista = new ArrayList<B_VentaBean>();
		try {
			lista = (List<B_VentaBean>) session.selectList(ProjectStr + "SQL_GetVentasPagoEfectivoReconfirmar",
					eticket);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

	@Override
	public List<String> getEticketsPorConfirmar() {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<String> lista = new ArrayList<>();
		try {
			lista = (List<String>) session.selectList(ProjectStr + "SQL_getEticketPagoEfectivoPorConfirmar");
			 
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

}
