package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_SubCategoriaFalla {

	private int idsubcategoria;
	private int idcategoria;
	private String detalle;
	boolean estado;

	public int getIdsubcategoria() {
		return idsubcategoria;
	}

	public void setIdsubcategoria(int idsubcategoria) {
		this.idsubcategoria = idsubcategoria;
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public V_SubCategoriaFalla(int idsubcategoria, int idcategoria, String detalle, boolean estado) {
		super();
		this.idsubcategoria = idsubcategoria;
		this.idcategoria = idcategoria;
		this.detalle = detalle;
		this.estado = estado;
	}

	public V_SubCategoriaFalla() {
		// TODO Auto-generated constructor stub
	}
}
