package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_BoletosRedBusEliminados;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.dao.B_VentaBoletajeDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;

public class VentaBoletaServiceI implements VentaBoletaService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_VentaBoletajeDao dao = factoria.getVenta();

	@Override
	public int insertVenta(B_VentaBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertVenta(bean);
	}

	@Override
	public B_VentaBean getVentaNroAsientoOcupado(int nroProgramacion, String Asiento) throws Exception {
		// TODO Auto-generated method stub
		return dao.getVentaNroAsientoOcupado(nroProgramacion, Asiento);
	}

	@Override
	public int cuentaAsientosOcupadosPorVenta(int nroProgramacion) throws Exception {
		return dao.cuentaAsientosOcupadosPorVenta(nroProgramacion);
	}

	@Override
	public List<B_VentaBean> getVentaRealizada(String usuario, int offset, int limit) throws Exception {
		// TODO Auto-generated method stub
		return dao.getVentaRealizada(usuario, offset, limit);
	}

	@Override
	public B_VentaBean getVentaReImprimir(String Usuario, int Nro, int Salida) throws Exception {
		// TODO Auto-generated method stub
		return dao.getVentaReImprimir(Usuario, Nro, Salida);
	}

	@Override
	public B_VentaBean getVentaImprimir(String Usuario, int Nro, int Salida) throws Exception {
		// TODO Auto-generated method stub
		return dao.getVentaImprimir(Usuario, Nro, Salida);
	}

	@Override
	public int deleteVenta(int Nro, int Salida) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteVenta(Nro, Salida);
	}

	@Override
	public int updateVenta(int Nro, int Salida, String Identidad, String IdentidadD, String DNI, String Nombre,
			String Edad, String Telefono, String Ruc, String Razon, String Origen, String OrigenD, String Destino1,
			String Destino1D, String HoraViaje) throws Exception {

		return dao.updateVenta(Nro, Salida, Identidad, IdentidadD, DNI, Nombre, Edad, Telefono, Ruc, Razon, Origen,
				OrigenD, Destino1, Destino1D, HoraViaje);
	}

	@Override
	public int updateVentaConfirmada(int Nro, int Salida, String EstadoWeb, String EticketVisa, String Usuario,
			String NumeroTarjeta, String TarjetaHabiente, String Eticket, int Medio_Pago) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateVentaConfirmada(Nro, Salida, EstadoWeb, EticketVisa, Usuario, NumeroTarjeta, TarjetaHabiente,
				Eticket, Medio_Pago);
	}

	@Override
	public int deleteVentaNoConfirmada(String EstadoWeb) throws Exception {
		return dao.deleteVentaNoConfirmada(EstadoWeb);
	}

	@Override
	public int updateVentaNoConfirmada(String EstadoWeb) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateVentaNoConfirmada(EstadoWeb);
	}

	@Override
	public int ListaCountVentasRealizadas(String usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaCountVentasRealizadas(usuario);
	}

	@Override
	public List<B_VentaBean> getVentaImprimirVisa(String EticketVisa) throws Exception {
		// TODO Auto-generated method stub
		return dao.getVentaImprimirVisa(EticketVisa);
	}

	@Override
	public int updateTiempoVisaVentaNoConfirmada(int Nro, int Salida, String ClienteVisa) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateTiempoVisaVentaNoConfirmada(Nro, Salida, ClienteVisa);
	}

	@Override
	public B_VentaBean selectVentaVisa(int Nro, int Salida) {
		// TODO Auto-generated method stub
		return dao.selectVentaVisa(Nro, Salida);
	}
	
	@Override
	public B_VentaBean selectVentaOpenpay(int Nro, int Salida) {
		// TODO Auto-generated method stub
		return dao.selectVentaOpenpay(Nro, Salida);
	}
	
	

	@Override
	public int updateTicketVenta(int Nro, int Salida, String Eticket) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateTicketVenta(Nro, Salida, Eticket);
	}

	@Override
	public B_VentaBean selectImprimirVoucherCliente(int Nro, int Salida) {
		// TODO Auto-generated method stub
		return dao.selectImprimirVoucherCliente(Nro, Salida);
	}

	@Override
	public int updateKilometrajePasajeroVenta() throws Exception {
		// TODO Auto-generated method stub
		return dao.updateKilometrajePasajeroVenta();
	}

	@Override
	public List<B_VentaBean> SelectListVentaPagoEfectivo(String NroCIP) throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectListVentaPagoEfectivo(NroCIP);
	}

	@Override
	public B_VentaBean SelectVentaDatosPagoEfectivo(int Nro, int Salida, String TokenCIP) throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectVentaDatosPagoEfectivo(Nro, Salida, TokenCIP);
	}
	
	@Override
	public B_VentaBean SelectVentaDatosOpenPay(int Nro, int Salida, String TokenCIP) throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectVentaDatosOpenPay(Nro, Salida, TokenCIP);
	}
	
	
	
	@Override
	public int updateVentaPagoEfectivo(int Nro, int Salida, String NroCIP, String EstadoWeb, String Usuario,
			int NroOrden) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateVentaPagoEfectivo(Nro, Salida, NroCIP, EstadoWeb, Usuario, NroOrden);
	}

	@Override
	public B_VentaBean SelectVentaPagoEfectivo(int Nro, int Salida) throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectVentaPagoEfectivo(Nro, Salida);
	}

	@Override
	public int updateVentaPagoEfectivoConfirmacion(int Nro, int Salida, String EstadoWeb, int MedioPago, int EstadoCIP)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.updateVentaPagoEfectivoConfirmacion(Nro, Salida, EstadoWeb, MedioPago, EstadoCIP);
	}

	@Override
	public int updateTiempoVentaPagoEfectivo(String NroCIP) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateTiempoVentaPagoEfectivo(NroCIP);
	}

	@Override
	public List<B_VentaBean> getVentaImprimirPagoEfectivo(String NroCIP) throws Exception {
		// TODO Auto-generated method stub
		return dao.getVentaImprimirPagoEfectivo(NroCIP);
	}

	@Override
	public int deleteVentaExtornoPagoEfectivo(int Nro, int Salida, String Eticket, String FechaHora, int Medio_Pago)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteVentaExtornoPagoEfectivo(Nro, Salida, Eticket, FechaHora, Medio_Pago);
	}

	@Override
	public int updateTiempoVentaCliente(int Nro, int Salida) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateTiempoVentaCliente(Nro, Salida);
	}

	@Override
	public List<B_VentaBean> SelectVentaAgenteAdmin(String Ruc, int offset, int limit) throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectVentaAgenteAdmin(Ruc, offset, limit);
	}

	@Override
	public int SelectCountVentaAgenteAdmin(String Ruc) throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectCountVentaAgenteAdmin(Ruc);
	}

	@Override
	public List<B_VentaBean> getVerificaVentaNoConfirmadaVisa(String Eticket) throws Exception {
		// TODO Auto-generated method stub
		return dao.getVerificaVentaNoConfirmadaVisa(Eticket);
	}

	@Override
	public List<B_VentaBean> SelectListVentasPendientesVisa() throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectListVentasPendientesVisa();
	}
	
	@Override
	public List<B_VentaBean> SelectListVentasPendientesEmbarcar() throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectListVentasPendientesEmbarcar();
	}
	
	@Override
	public int UpdateVentasVisaAuditoria(String Eticket) throws Exception {
		// TODO Auto-generated method stub
		return dao.UpdateVentasVisaAuditoria(Eticket);
	}

	@Override
	public List<B_VentaBean> SelectListVentasPendientesPagoEfectivo() throws Exception {
		// TODO Auto-generated method stub
		return dao.SelectListVentasPendientesPagoEfectivo();
	}

	@Override
	public int updateVentaPagoEfectivoVerificaConfirmacion(int Nro, int Salida, String EstadoWeb, int MedioPago,
			int EstadoCIP) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateVentaPagoEfectivoVerificaConfirmacion(Nro, Salida, EstadoWeb, MedioPago, EstadoCIP);
	}

	@Override
	public int SQL_UpdatePrecioXCupon(String nro, String Salida, double precio, String cupon) throws Exception {
		// TODO Auto-generated method stub
		return dao.SQL_UpdatePrecioXCupon(nro, Salida, "" + precio, cupon);
	}

	@Override
	public List<B_VentaBean> SelectVentasTelefonicasXUsuario(boolean todos, String usuario, int limit, int offset) {
		// TODO Auto-generated method stub
		return dao.SelectVentasTelefonicasXUsuario(todos, usuario, limit, offset);
	}

	@Override
	public int SelectCountVentasTelefonicasXUsuario(boolean todos, String usuario) {
		// TODO Auto-generated method stub
		return dao.SelectCountVentasTelefonicasXUsuario(todos, usuario);
	}

	@Override
	public int liberarAsientoxNro(int nro, String username) {
		// TODO Auto-generated method stub
		return dao.liberarAsientoxNro(nro, username);
	}

	@Override
	public int InserBoletosRedBusLiberados(B_BoletosRedBusEliminados bean) {
		// TODO Auto-generated method stub
		return dao.InserBoletosRedBusLiberados(bean);
	}

	@Override
	public boolean SelectISVentaRedBus(long nro) {
		// TODO Auto-generated method stub
		B_VentaBean rpta = null;
		rpta = dao.SelectISVentaRedBus(nro);
		return !(rpta == null);
	}

	@Override
	public boolean SelectISVentaWeb(long nro, String DNI) {
		// TODO Auto-generated method stub
		B_VentaBean rpta = null;
		rpta = dao.SelectISVentaWeb(nro, DNI);
		return !(rpta == null);
	}

	@Override
	public List<B_VentaBean> SelectVentasPagoEfectivoReconfirmar(String eticket) {
		// TODO Auto-generated method stub
		return dao.SelectVentasPagoEfectivoReconfirmar(eticket);
	}

	@Override
	public List<String> getEticketsPorConfirmar() {
		return dao.getEticketsPorConfirmar();
	}

	@Override
	public List<B_VentaBean> SelectListVentasPendientesOpenPay() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
