package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ServiciosBean;

public interface V_ServiciosDao {
	
	public abstract V_ServiciosBean getServicioCodigo(String Codigo) throws Exception;
	public List<V_ServiciosBean> ListarServicios() throws Exception;
	public List<V_ServiciosBean> ListarDetalledeServiciosXCupon(String detalle) throws Exception;

}
