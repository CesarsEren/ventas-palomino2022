package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_CategoriaFalla {
	private int idcategoria;
	private String detalle;
	private boolean estado;

	public V_CategoriaFalla() {
		// TODO Auto-generated constructor stub
	}

	public V_CategoriaFalla(int idcategoria, String detalle, boolean estado) {
		super();
		this.idcategoria = idcategoria;
		this.detalle = detalle;
		this.estado = estado;
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
}
