package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;


import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;

public interface RutasService {
	
	public abstract List<V_RutasBean> getRutasNro(int Nro) throws Exception;
	public abstract List<V_RutasBean> ListaRutas() throws Exception;
	public abstract List<V_RutasBean> ListSQL_RutasXCupon(String codigocupon)throws Exception;

}
