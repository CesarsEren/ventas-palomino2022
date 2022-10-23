package pe.com.grupopalomino.sistema.boletaje.dao;


import pe.com.grupopalomino.sistema.boletaje.bean.V_ComputerBean;

public interface V_ComputerDao {

	public V_ComputerBean listComputer (String Serie)throws Exception;
}
