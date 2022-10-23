package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_CategoriaFalla;

public interface V_CategoriaFallasDao {
	public abstract List<V_CategoriaFalla> ListarCategoriaFallas(int activo)throws Exception;
	public abstract int InsertCategoriaFalla(String detalle)throws Exception;
	public abstract int UpdateCategoriaFalla(int id , boolean estado)throws Exception;

}
