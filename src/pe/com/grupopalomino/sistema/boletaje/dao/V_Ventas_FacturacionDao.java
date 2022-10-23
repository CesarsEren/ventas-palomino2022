package pe.com.grupopalomino.sistema.boletaje.dao;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;

public interface V_Ventas_FacturacionDao {
	
	public int SQL_UPDATE_VENTAS_FACTURACION_ERROR(Integer nro,String tipoOperacion,String codigoError)throws Exception;
	public int SQL_UPDATE_VENTAS_FACTURACION_IMAGES(Integer nro,String tipoOperacion,String codigohash,byte[] imageBarras,byte[] imageQR)throws Exception;
	public V_Ventas_FacturacionBean SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer nro, String tipoOperacion)throws Exception;
	
}
