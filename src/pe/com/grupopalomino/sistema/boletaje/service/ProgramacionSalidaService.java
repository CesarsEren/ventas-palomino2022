package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.Precio_Desde;

public interface ProgramacionSalidaService {

	public abstract List<B_ProgramacionSalidaBean> getSalidas(String fecha,String rol,String origenAgencia,String destino,Integer promocionWeb) throws Exception;
	public abstract List<B_ProgramacionSalidaBean> getSalidasAgencias(int Nro,String origenAgencia) throws Exception;
	public abstract B_ProgramacionSalidaBean getSalidasNro(int Nro) throws Exception;
	public abstract List<B_ProgramacionSalidaBean> getSalidasCiudades(String fecha,String origenCiudad,String destinoCiudad) throws Exception;
	public abstract B_ProgramacionSalidaBean getSalidasPrecioPromocion(int Nro,String destino,Integer promocionWeb) throws Exception;
	List<Precio_Desde> getPrecioDesde(Integer programacion) throws Exception;
}
