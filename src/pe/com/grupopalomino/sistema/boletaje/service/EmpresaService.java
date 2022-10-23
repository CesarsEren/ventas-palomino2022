package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_EmpresasBean;

public interface EmpresaService {

	public List<V_EmpresasBean> listaEmpresas() throws Exception;
}
