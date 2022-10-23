package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_AgenciasWebDao;

public class AgenciasWebServiceI implements AgenciasWebService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_AgenciasWebDao dao = factoria.getAgenciasWeb();
	
	@Override
	public List<V_AgenciasWebBean> listaAgenciasWeb(int limit, int offset, String query) throws Exception {
		return dao.listaAgenciasWeb(limit, offset, query);
	}

	@Override
	public V_AgenciasWebBean nuevaAgencia(V_AgenciasWebBean bean) throws Exception {
		return dao.nuevaAgencia(bean);
	}

	@Override
	public V_AgenciasWebBean actualizarAgencia(V_AgenciasWebBean bean) throws Exception {
		return dao.actualizarAgencia(bean);
	}

	@Override
	public V_AgenciasWebBean cambiaEstadoAgencia(V_AgenciasWebBean bean) throws Exception {
		return dao.cambiaEstadoAgencia(bean);
	}

	@Override
	public V_AgenciasWebBean obtieneAgenciaXid(int id) throws Exception {
		return dao.obtieneAgenciaXid(id);
	}

	@Override
	public int cuentaAgenciasTotales() throws Exception {
		return dao.cuentaAgenciasTotales();
	}

	@Override
	public V_AgenciasWebBean obtieneAgenciaXCodigo(String Codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.obtieneAgenciaXCodigo(Codigo); 
	}

	@Override
	public int actualizarMontoVentaActual(String Codigo, double montoVentaActual) throws Exception {
		// TODO Auto-generated method stub
		return dao.actualizarMontoVentaActual(Codigo, montoVentaActual); 
	}

	@Override
	public int actualizarMontoVentaConfirmada(String Codigo, double montoVentaConfirmada) throws Exception {
		// TODO Auto-generated method stub
		return dao.actualizarMontoVentaConfirmada(Codigo, montoVentaConfirmada); 
	}

	@Override
	public List<V_AgenciasWebBean> ListaAgenciaxCodigo(String Codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaAgenciaxCodigo(Codigo);
	}

	@Override
	public V_AgenciasWebBean ListaAgenciaxRuc(String ruc) throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaAgenciaxRuc(ruc); 
	}

}
