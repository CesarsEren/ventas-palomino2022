package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_PrecioProgramacion;

public interface B_PrecioProgramacionDao {
	
	public List<B_PrecioProgramacion> SQL_ObtenerAsientosPrecioPromocionConNroProgramacion(int Nro);

}
