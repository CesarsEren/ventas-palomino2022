package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasWebBean;

public interface V_AgenciasWebDao {

	public int cuentaAgenciasTotales() throws Exception;
	public List<V_AgenciasWebBean> listaAgenciasWeb() throws Exception;
	public List<V_AgenciasWebBean> listaAgenciasWeb(int limit, int offset, String query) throws Exception;
	public V_AgenciasWebBean nuevaAgencia(V_AgenciasWebBean bean) throws Exception;
	public V_AgenciasWebBean actualizarAgencia(V_AgenciasWebBean bean) throws Exception;
	public int actualizarMontoVentaActual(String Codigo, double montoVentaActual) throws Exception;
	public int actualizarMontoVentaConfirmada(String Codigo, double montoVentaConfirmada) throws Exception;
	public V_AgenciasWebBean cambiaEstadoAgencia(V_AgenciasWebBean bean) throws Exception;
	public V_AgenciasWebBean obtieneAgenciaXid(int id) throws Exception;
	public V_AgenciasWebBean obtieneAgenciaXCodigo(String Codigo) throws Exception;
	public List<V_AgenciasWebBean> ListaAgenciaxCodigo(String Codigo)throws Exception;
	public V_AgenciasWebBean ListaAgenciaxRuc(String ruc)throws Exception;
	
}
