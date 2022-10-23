package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_Promo_RetornoCBean {
	
	private Integer id;
	private String codigo;
	private String descripcion;
	private Integer web;
	private Integer escritorio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getWeb() {
		return web;
	}
	public void setWeb(Integer web) {
		this.web = web;
	}
	public Integer getEscritorio() {
		return escritorio;
	}
	public void setEscritorio(Integer escritorio) {
		this.escritorio = escritorio;
	}
	
	@Override
	public String toString() {
		return "V_Promo_RetornoCBean [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", web=" + web
				+ ", escritorio=" + escritorio + "]";
	}


}
