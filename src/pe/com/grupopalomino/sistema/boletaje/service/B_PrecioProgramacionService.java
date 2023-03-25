package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_PrecioProgramacion;

public interface B_PrecioProgramacionService {

	public abstract List<B_PrecioProgramacion> SQL_ObtenerAsientosPrecioPromocionConNroProgramacion(int Nro);
}
