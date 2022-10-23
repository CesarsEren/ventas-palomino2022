package pe.com.grupopalomino.sistema.boletaje.formviews;

public class ComboDestinoBajada {
	
	private String codigo;
	private String descripcion;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "ComboDestinoBajada [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
}
