package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Clientes_RutaPrecioBean;

public interface ClientesRutaPrecioService {
	
	public int insertRutaPrecio(V_Clientes_RutaPrecioBean bean )throws Exception;
	public int updateRutaPrecio(V_Clientes_RutaPrecioBean bean )throws Exception;
	public int cuentaClienteRutaPrecio()throws Exception;
	public List<V_Clientes_RutaPrecioBean> listaClientesRutas (String query , int  limit,int offset)throws Exception;
	public V_Clientes_RutaPrecioBean listaClientesRutas (int Id)throws Exception;
	public V_Clientes_RutaPrecioBean listaClientesRutasVerificacion (String Ruc,int NroRuta,String NroServicio)throws Exception;

}
