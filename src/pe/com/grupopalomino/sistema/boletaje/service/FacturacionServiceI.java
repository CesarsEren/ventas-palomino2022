package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import java.util.Map;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.B_FacturacionDao;

public class FacturacionServiceI implements FacturacionService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_FacturacionDao dao = factoria.getVentaFacturacion();

	@Override
	public List<Map<String, Object>> SQL_SELECT_VENTA_ENVIO_FACTURADOR(String empresa, String fechaEmision,
			Integer documentos, int limit, int offset) {
		return dao.SQL_SELECT_VENTA_ENVIO_FACTURADOR(empresa, fechaEmision, documentos, limit, offset);
	}

	@Override
	public Map<String, Object> SQL_SELECT_VENTA_FACTURADOR(Integer nro, String tipoOperacion) {
		return dao.SQL_SELECT_VENTA_FACTURADOR(nro, tipoOperacion);
	}

	@Override
	public int SQL_UPDATE_VENTA_FACTURADOR(Integer nro, String tipoOperacion, Integer envioSunat,
			String respuestaSunat) {
		return dao.SQL_UPDATE_VENTA_FACTURADOR(nro, tipoOperacion, envioSunat, respuestaSunat);
	}

	@Override
	public List<Map<String, Object>> SQL_SELECT_VENTA_DOCUMENTO_ELECTRONICO(String empresa, String fechaEmision,
			String serie, String numero, String tipodocumento, String ruc, Integer limit, Integer offset) {
		return dao.SQL_SELECT_VENTA_DOCUMENTO_ELECTRONICO(empresa, fechaEmision, serie, numero, tipodocumento, ruc,
				limit, offset);
	}

	@Override
	public Map<String, Object> SQL_SELECT_VENTA_APROBADA_FACTURADOR(Integer nro, String tipoOperacion) {
		return dao.SQL_SELECT_VENTA_APROBADA_FACTURADOR(nro, tipoOperacion);
	}

	@Override
	public List<Map<String, Object>> SQL_SELECT_VENTA_DESCARGA_FACTURADOR_SQL(String empresa, String fechaEmision,
			String serie, String numero, String tipodocumento, String ruc, double monto) {
		return dao.SQL_SELECT_VENTA_DESCARGA_FACTURADOR_SQL(empresa, fechaEmision, serie, numero, tipodocumento, ruc,
				monto);
	}

	@Override
	public Map<String, Object> SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer nro, String tipoOperacion) {
		return dao.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(nro, tipoOperacion);
	}

	@Override
	public int USP_GENERARBOLETO(String nro, String terminal, int documento, String EmpresaID) {
		// TODO Auto-generated method stub
		return dao.USP_GENERARBOLETO(nro, terminal, documento, EmpresaID);
	}

	@Override
	public Map<String, Object> SQL_SELECT_DOCUMENTO_ELECTRONICO(String emp, String nro, String tipo) {
		// TODO Auto-generated method stub
		return dao.SQL_SELECT_DOCUMENTO_ELECTRONICO(emp, nro, tipo);
	}

	@Override
	public Map<String, Object> SQL_UPDATE_DOCUMENTO(String ruc, String razon, String dni, String nombre ,String nro,String tipo) {
		// TODO Auto-generated method stub
		return dao.SQL_UPDATE_DOCUMENTO(ruc,razon,dni,nombre,nro,tipo) ;
	}

	@Override
	public List<Map<String, Object>> SQL_SELECT_VENTA_ENVIO_FACTURADOR_AUTOMATICO(String empresa, String fechaEmision,
			Integer documentos, int limit, int offset) {
		// TODO Auto-generated method stub
		return dao.SQL_SELECT_VENTA_ENVIO_FACTURADOR_AUTOMATICO(empresa,fechaEmision,documentos,limit,offset);
	}

}
