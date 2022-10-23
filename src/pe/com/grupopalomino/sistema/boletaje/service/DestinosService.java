package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosBean;

public interface DestinosService {
	
	public abstract List<V_DestinosBean> getDestinos() throws Exception;
	public abstract V_DestinosBean getDestinoVuelta(String destino, String origen) throws Exception;
	public abstract List<V_DestinosBean> getDestinosXorigen(String origen) throws Exception;
	
	
	public abstract List<V_DestinosBean> getDestinos(String ciudad) throws Exception;
	public abstract V_DestinosBean getDestinoNro(int Nro) throws Exception;
	public abstract List<V_DestinosBean> getDestinosTodos() throws Exception;
	public abstract List<V_DestinosBean> getDestinosID(String origen) throws Exception;
	public abstract List<V_DestinosBean> getDestinosXId(String ids) throws Exception;
	public V_DestinosBean getDestinoXIdaYVuelta(String origen, String destino) throws Exception;

}
