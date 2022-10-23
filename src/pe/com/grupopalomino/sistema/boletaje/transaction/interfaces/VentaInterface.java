package pe.com.grupopalomino.sistema.boletaje.transaction.interfaces;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CroquisBusBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosBean;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso2Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso3Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso4Form;
import pe.com.grupopalomino.sistema.boletaje.spring.security.*;

public interface VentaInterface {
	
	
	// METODOS DE SOBREESCRITURA PARA LA TRANSACCION DE LA RESERVA DE BOLETOS
	public boolean GeneraDatosClienteVenta (VentaPaso3Form  paso3Form)throws Exception;
	public boolean GeneraDatosPasajeroventa (VentaPaso3Form paso3Form)throws Exception;
	public boolean VerificaDisponibilidadAsientoIda(VentaPaso2Form paso2Form,VentaPaso3Form paso3Form)throws Exception;
	public B_VentaBean DatosEstaticosVenta(B_VentaBean ventaAmodificar,VentaPaso3Form paso3Form,SpringSecurityUser usuario)throws Exception;
	public B_VentaBean DatosDinamicosVenta(B_VentaBean venta,B_Correlativos correlativo,String FechaEmision,VentaPaso2Form paso2Form,VentaPaso3Form paso3Form,List<V_CroquisBusBean> listaCroquisBus,int minValor,SpringSecurityUser usuario)throws Exception;
	public B_CuentaCorrienteBean DatosEstaticosCuentaCorriente(B_CuentaCorrienteBean cuentacorriente)throws Exception;
	public V_DestinosBean ObtenerDatosDestinos(int nroDestino)throws Exception;
	public B_ProgramacionSalidaBean ObtenerProgramacion (int nroProgramacion)throws Exception;
	public B_CuentaCorrienteBean DatosDinamicosCuentaCorriente(ListaPreVenta venta,/*B_VentaBean venta,*/B_CuentaCorrienteBean cuentacorrienteBean,/*B_Correlativos correlativo,*/VentaPaso1Form paso1Form,VentaPaso2Form paso2Form,SpringSecurityUser usuario,String IdaVuelta,String EticketVisa,String userCliente)throws Exception;
	
	//METODO NUEVO AGREGADO PARA OPTIMIZAR LA TRANSACCION DE VISA - 2016-12-13
	public B_CuentaCorrienteBean DatosDinamicosCuentaCorrienteVisa(B_VentaBean venta,B_CuentaCorrienteBean cuentacorrienteBean,B_Correlativos correlativo,String EticketVisa)throws Exception;
	
	//METODOS PARA LA VENTA DE VUELTA
	public boolean GeneraDatosClienteVentaVuelta (VentaPaso4Form  paso4Form)throws Exception;
	public boolean GeneraDatosPasajeroventaVuelta (VentaPaso4Form  paso4Form)throws Exception;
	public boolean VerificaDisponibilidadAsientoVuelta(VentaPaso2Form paso2Form,VentaPaso4Form paso4Form)throws Exception;
	public B_VentaBean DatosEstaticosVentaVuelta(B_VentaBean ventaAmodificar,VentaPaso4Form paso4Form,SpringSecurityUser usuario)throws Exception;
	public B_VentaBean DatosDinamicosVentaVuelta(B_VentaBean venta,B_Correlativos correlativo,String FechaEmision,VentaPaso2Form paso2Form,VentaPaso4Form paso4Form,List<V_CroquisBusBean> listaCroquisBus,int minValor,SpringSecurityUser usuario)throws Exception;
	public B_CuentaCorrienteBean DatosDinamicosCuentaCorrienteVuelta(ListaPreVenta venta,/*B_VentaBean venta,*/B_CuentaCorrienteBean cuentacorrienteBean,/*B_Correlativos correlativo,*/VentaPaso1Form paso1Form,VentaPaso2Form paso2Form,SpringSecurityUser usuario,String IdaVuelta,String EticketVisa,String userCliente)throws Exception;
	
	// VALIDACION DEL PRECIO REAL
	
	public double VerificaPrecioActual(List<V_CroquisBusBean> listaCroquisBus,VentaPaso2Form paso2Form,VentaPaso3Form paso3Form,int minValor,SpringSecurityUser usuario)throws Exception;
	public double VerificaPrecioActualVuelta(List<V_CroquisBusBean> listaCroquisBus,VentaPaso2Form paso2Form,VentaPaso4Form paso4Form,int minValor,SpringSecurityUser usuario)throws Exception;
	
	// TRANSACCION DE LA CUENTA CORRIENTE (PAGO EFECTIVO)
	
	public B_CuentaCorrienteBean DatosDinamicosCuentaCorrientePagoEfectivo(B_VentaBean venta,B_CuentaCorrienteBean cuentacorrienteBean,B_Correlativos correlativo)throws Exception;
}
