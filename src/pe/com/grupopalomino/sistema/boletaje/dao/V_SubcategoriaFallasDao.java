package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;


import pe.com.grupopalomino.sistema.boletaje.bean.V_SubCategoriaFalla;

public interface V_SubcategoriaFallasDao {
	public abstract List<V_SubCategoriaFalla> ListarSubCategoriaFallas(int idcategoria,int estado)throws Exception;
	public abstract int InsertSubCategoriaFalla(int idcategoria,String detalle)throws Exception;
	public abstract int UpdateSubCategoriaFalla(int idsubcategoria, boolean estado)throws Exception;

}
