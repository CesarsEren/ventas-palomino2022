package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_EmpresasBean;

public interface V_EmpresaDao {
	
	public List<V_EmpresasBean> listaEmpresas() throws Exception;

}
	