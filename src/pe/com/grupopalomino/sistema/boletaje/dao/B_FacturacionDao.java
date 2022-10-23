package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import java.util.Map;

public interface B_FacturacionDao {
	
	public List<Map<String, Object>> SQL_SELECT_VENTA_ENVIO_FACTURADOR (String empresa, String fechaEmision,Integer documentos,int limit,int offset);
	public List<Map<String, Object>> SQL_SELECT_VENTA_DOCUMENTO_ELECTRONICO(String empresa, String fechaEmision,String serie,String numero,String tipodocumento,String ruc, Integer limit, Integer offset);
	public List<Map<String, Object>> SQL_SELECT_VENTA_DESCARGA_FACTURADOR_SQL(String empresa, String fechaEmision,String serie,String numero,String tipodocumento,String ruc, double monto);
	public Map<String, Object> SQL_SELECT_VENTA_FACTURADOR (Integer nro, String tipoOperacion);
	public Map<String, Object> SQL_SELECT_VENTA_APROBADA_FACTURADOR (Integer nro, String tipoOperacion);
	public Map<String, Object> SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR (Integer nro, String tipoOperacion);
	public int SQL_UPDATE_VENTA_FACTURADOR (Integer nro, String tipoOperacion,Integer envioSunat,String respuestaSunat);
	public int USP_GENERARBOLETO(String nro, String terminal, int documento, String EmpresaID);
	public Map<String, Object> SQL_SELECT_DOCUMENTO_ELECTRONICO(String emp, String nro, String tipo);
	public Map<String, Object> SQL_UPDATE_DOCUMENTO(String ruc, String razon, String dni, String nombre,String nro,String tipo);
	public List<Map<String, Object>> SQL_SELECT_VENTA_ENVIO_FACTURADOR_AUTOMATICO(String empresa, String fechaEmision,
			Integer documentos, int limit, int offset); 
}