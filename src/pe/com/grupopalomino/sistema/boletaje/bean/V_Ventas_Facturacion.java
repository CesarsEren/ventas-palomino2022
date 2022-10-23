package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_Ventas_Facturacion {
	
	private Integer id;
	private Integer nro;
	private String tipoOperacion;
	private String codigoHash;
	private String codigoBarras;
	private String image;
	private String imagenCB;
	private String codigoError;
	
	public String getImagenCB() {
		return imagenCB;
	}
	public void setImagenCB(String imageCB) {
		this.imagenCB = imageCB;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNro() {
		return nro;
	}
	public void setNro(Integer nro) {
		this.nro = nro;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getCodigoHash() {
		return codigoHash;
	}
	public void setCodigoHash(String codigoHash) {
		this.codigoHash = codigoHash;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	
	@Override
	public String toString() {
		return "V_Ventas_Facturacion [id=" + id + ", nro=" + nro + ", tipoOperacion=" + tipoOperacion + ", codigoHash="
				+ codigoHash + ", codigoBarras=" + codigoBarras + ", image=" + image + ", codigoError=" + codigoError
				+ "]";
	}

}
