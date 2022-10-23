package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Clientes_RutaPrecioBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_Clientes_RutaPrecioDao;

public class ClientesRutaPrecioServiceI implements ClientesRutaPrecioService {

	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_Clientes_RutaPrecioDao dao = factoria.getClienteRutaPrecio();
	
	
	@Override
	public int insertRutaPrecio(V_Clientes_RutaPrecioBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertRutaPrecio(bean);
	}

	@Override
	public int updateRutaPrecio(V_Clientes_RutaPrecioBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateRutaPrecio(bean); 
	}

	@Override
	public List<V_Clientes_RutaPrecioBean> listaClientesRutas(String query , int  limit,int offset) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaClientesRutas(query ,limit,offset);
	}

	@Override
	public int cuentaClienteRutaPrecio() throws Exception {
		// TODO Auto-generated method stub
		return dao.cuentaClienteRutaPrecio(); 
	}

	@Override
	public V_Clientes_RutaPrecioBean listaClientesRutas(int Id) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaClientesRutas(Id);
	}

	@Override
	public V_Clientes_RutaPrecioBean listaClientesRutasVerificacion(String Ruc, int NroRuta, String NroServicio)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.listaClientesRutasVerificacion(Ruc, NroRuta, NroServicio);
	}

}
