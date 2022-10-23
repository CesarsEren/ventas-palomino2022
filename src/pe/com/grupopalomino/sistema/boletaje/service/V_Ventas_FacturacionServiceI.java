package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_Ventas_FacturacionDao;

public class V_Ventas_FacturacionServiceI implements V_Ventas_FacturacionService {
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_Ventas_FacturacionDao dao = factoria.getVentasFacturacion();
	

	@Override
	public int SQL_UPDATE_VENTAS_FACTURACION_ERROR(Integer nro, String tipoOperacion,String codigoError) throws Exception {
		return dao.SQL_UPDATE_VENTAS_FACTURACION_ERROR(nro, tipoOperacion,codigoError);
	}


	@Override
	public V_Ventas_FacturacionBean SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer nro, String tipoOperacion)throws Exception {
		return dao.SQL_SELECT_VENTAS_IMAGES_FACTURACION(nro, tipoOperacion);
	}


	@Override
	public int SQL_UPDATE_VENTAS_FACTURACION_IMAGES(Integer nro, String tipoOperacion, String codigohash,byte[] imageBarras, byte[] imageQR) throws Exception {
		return dao.SQL_UPDATE_VENTAS_FACTURACION_IMAGES(nro, tipoOperacion, codigohash, imageBarras, imageQR);
	}

}
