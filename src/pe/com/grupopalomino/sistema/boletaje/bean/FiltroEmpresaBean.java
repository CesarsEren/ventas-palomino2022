package pe.com.grupopalomino.sistema.boletaje.bean;

public class FiltroEmpresaBean {

	private String idempresa;
	private String strempresa;
	
	public FiltroEmpresaBean(String idempresa, String strempresa) {
		super();
		this.idempresa = idempresa;
		this.strempresa = strempresa;
	}
	
	
	public String getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(String idempresa) {
		this.idempresa = idempresa;
	}
	public String getStrempresa() {
		return strempresa;
	}
	public void setStrempresa(String strempresa) {
		this.strempresa = strempresa;
	}
	
	
	
	
	
	
	
}



