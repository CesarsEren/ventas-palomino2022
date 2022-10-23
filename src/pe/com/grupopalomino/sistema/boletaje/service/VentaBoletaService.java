package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_BoletosRedBusEliminados;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;

public interface VentaBoletaService {
	
	public int SQL_UpdatePrecioXCupon(String nro, String Salida, double precio,String cupon) throws Exception;
	public int insertVenta(B_VentaBean bean )throws Exception;
	public int deleteVenta(int Nro,int Salida )throws Exception;
	public int deleteVentaNoConfirmada(String EstadoWeb)throws Exception;
	public int updateVenta(int Nro,int Salida,String Identidad,String IdentidadD,String DNI,String Nombre,String Edad,String Telefono
			,String Ruc,String Razon,String Origen,String OrigenD,String Destino1,String Destino1D,String HoraViaje)throws Exception;
	public int updateKilometrajePasajeroVenta()throws Exception;
	public B_VentaBean getVentaNroAsientoOcupado(int nroProgramacion, String Asiento )throws Exception;
	public List<B_VentaBean> getVentaRealizada(String usuario,int offset, int limit)throws Exception;
	public int cuentaAsientosOcupadosPorVenta(int nroProgramacion) throws Exception;
	public B_VentaBean getVentaReImprimir( String Usuario,int Nro,int Salida)throws Exception;
	public B_VentaBean getVentaImprimir( String Usuario,int Nro,int Salida)throws Exception;
	public List<B_VentaBean> getVentaImprimirVisa(String EticketVisa)throws Exception; 
	public int updateVentaConfirmada(int Nro,int Salida,String EstadoWeb,String EticketVisa,String Usuario,String NumeroTarjeta,String TarjetaHabiente,String Eticket,int Medio_Pago)throws Exception;
	public int updateVentaNoConfirmada(String EstadoWeb)throws Exception;
	public int updateTiempoVisaVentaNoConfirmada(int Nro,int Salida,String ClienteVisa)throws Exception;
	public int updateTicketVenta(int Nro,int Salida,String Eticket)throws Exception;
	public List<B_VentaBean> SelectListVentaPagoEfectivo(String NroCIP)throws Exception;
	public B_VentaBean SelectVentaDatosPagoEfectivo(int Nro,int Salida,String TokenCIP)throws Exception;
	public B_VentaBean SelectVentaDatosOpenPay(int Nro,int Salida,String TokenCIP)throws Exception;
	public int updateVentaPagoEfectivo(int Nro,int Salida,String NroCIP,String EstadoWeb,String Usuario,int NroOrden)throws Exception;
	public B_VentaBean SelectVentaPagoEfectivo(int Nro,int Salida)throws Exception;
	public int updateVentaPagoEfectivoConfirmacion(int Nro,int Salida,String EstadoWeb,int MedioPago,int EstadoCIP)throws Exception;
	public int updateVentaPagoEfectivoVerificaConfirmacion(int Nro,int Salida,String EstadoWeb,int MedioPago,int EstadoCIP)throws Exception;
	public int updateTiempoVentaPagoEfectivo(String NroCIP)throws Exception;
	public List<B_VentaBean> getVerificaVentaNoConfirmadaVisa(String Eticket)throws Exception;
	public int updateTiempoVentaCliente(int Nro,int Salida)throws Exception;
	public List<B_VentaBean> getVentaImprimirPagoEfectivo(String NroCIP)throws Exception;
	public int deleteVentaExtornoPagoEfectivo(int Nro,int Salida,String Eticket,String FechaHora,int Medio_Pago)throws Exception;
	public B_VentaBean selectVentaVisa(int Nro,int Salida);
	public B_VentaBean selectVentaOpenpay(int Nro,int Salida);
	public B_VentaBean selectImprimirVoucherCliente(int Nro,int Salida);
	public int ListaCountVentasRealizadas(String usuario)throws Exception;
	public List<B_VentaBean> SelectVentaAgenteAdmin(String Ruc,int offset,int limit)throws Exception;
	public int SelectCountVentaAgenteAdmin(String Ruc)throws Exception;
	public List<B_VentaBean> SelectListVentasPendientesVisa()throws Exception;
	public List<B_VentaBean> SelectListVentasPendientesEmbarcar()throws Exception;
	public List<B_VentaBean> SelectListVentasPendientesPagoEfectivo()throws Exception;
	public List<B_VentaBean> SelectListVentasPendientesOpenPay()throws Exception;	
	public int UpdateVentasVisaAuditoria(String Eticket)throws Exception;		
	public List<B_VentaBean> SelectVentasTelefonicasXUsuario(boolean todos, String usuario,int limit,int offset);
	public int SelectCountVentasTelefonicasXUsuario(boolean todos, String usuario);
	public int liberarAsientoxNro(int nro, String username);
	
	public int InserBoletosRedBusLiberados(B_BoletosRedBusEliminados bean);
	public boolean SelectISVentaRedBus(long nro);
	public boolean SelectISVentaWeb(long nro,String DNI);
	public List<B_VentaBean> SelectVentasPagoEfectivoReconfirmar(String eticket);
	
	public List<String> getEticketsPorConfirmar();
}
