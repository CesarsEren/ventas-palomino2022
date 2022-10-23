package pe.com.grupopalomino.sistema.boletaje.bean;

public class FiltroComidaBean {
	
	private String idcomida;
	private String strcomida;
	
	
	
	public FiltroComidaBean(String idcomida, String strcomida) {
		super();
		this.idcomida = idcomida;
		this.strcomida = strcomida;
	}
	
	
	public String getIdcomida() {
		return idcomida;
	}
	public void setIdcomida(String idcomida) {
		this.idcomida = idcomida;
	}
	public String getStrcomida() {
		return strcomida;
	}
	public void setStrcomida(String strcomida) {
		this.strcomida = strcomida;
	}
	
	
	
	

}
