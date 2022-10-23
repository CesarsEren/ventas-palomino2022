package pe.com.grupopalomino.sistema.boletaje.bean;

public class FiltroTipoBean {

	private String idtipo;
	private String strtipo;
	
	
	public FiltroTipoBean(String idtipo, String strtipo) {
		super();
		this.idtipo = idtipo;
		this.strtipo = strtipo;
	}
	public String getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(String idtipo) {
		this.idtipo = idtipo;
	}
	public String getStrtipo() {
		return strtipo;
	}
	public void setStrtipo(String strtipo) {
		this.strtipo = strtipo;
	}
	
	
}
