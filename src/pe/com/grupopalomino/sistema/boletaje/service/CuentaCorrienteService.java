package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;

public interface CuentaCorrienteService {

	public int insertCuentaCorriente(B_CuentaCorrienteBean bean )throws Exception;
	public int deleteCuentaCorriente(int Nro,int NroBol)throws Exception;
	public int deleteCuentaCorrientePagoEfectivo(int NroBol)throws Exception;
	public List<B_CuentaCorrienteBean> ListaCuentaCorriente(B_CuentaCorrienteBean bean )throws Exception;
	public List<B_CuentaCorrienteBean> EstadoCuentaCorriente(String RUC,String Tipo,String FechaInicial,String FechaFinal,int offset , int limit,String Usuario)throws Exception;
	public int EstadoCountCuentaCorriente(String RUC,String Tipo,String FechaInicial,String FechaFinal,String Usuario)throws Exception;
	public double VentasRealizadasCuentaCorriente(String RUC,String Usuario)throws Exception;
	public int updateCuentaCorrienteConfirmada(int Nro,int NroBol,String estado)throws Exception;
	public B_CuentaCorrienteBean ListaVentaPagoEfectivoCuentaCorriente(int NroBol)throws Exception;
}
