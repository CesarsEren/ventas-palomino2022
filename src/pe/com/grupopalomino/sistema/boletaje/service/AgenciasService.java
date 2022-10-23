package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;

public interface AgenciasService {

	public V_AgenciasBean listAgencias(String Codigo) throws Exception;
	public List<V_AgenciasBean> getListaAsientosOcupadoXAgencia(int nroProgramacion)throws Exception;
	public List<V_AgenciasBean> getListaAsientosReservadoXAgencia(int nroProgramacion)throws Exception;
	public List<V_AgenciasBean> getListaComida(int nroProgramacion)throws Exception;
	public List<V_AgenciasBean> getListaAgenciasDisponibles(String ciudad,String tipo)throws Exception;
	public List<V_AgenciasBean> SQL_SELECT_AGENCIAS()throws Exception;
}
