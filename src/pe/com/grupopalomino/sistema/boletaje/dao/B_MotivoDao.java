package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.B_MotivoBean;

public interface B_MotivoDao {
	
	public List<B_MotivoBean> MuestraMotivos(String MotivoReclamo) throws Exception;

}
