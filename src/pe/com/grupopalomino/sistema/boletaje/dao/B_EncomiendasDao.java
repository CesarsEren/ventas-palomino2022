package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_EncomiendasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Encomiendas_Detalles;



public interface B_EncomiendasDao {
	
	public B_EncomiendasBean  getEncomiendaSerieNumero (String serie,String numero,String documento,String empresa) throws Exception;
	public List<B_Encomiendas_Detalles> SQL_Obtiene_DetalleEncomienda(int nro);
}
