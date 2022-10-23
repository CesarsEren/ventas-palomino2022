package pe.com.grupopalomino.sistema.boletaje.formviews;

public class ComboIdentidad {

	private int id;
	private String tipoDocumento;
	private String descripcionDocumento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}
	
	@Override
	public String toString() {
		return "ComboIdentidad [id=" + id + ", tipoDocumento=" + tipoDocumento + ", descripcionDocumento="
				+ descripcionDocumento + "]";
	}
	
}
