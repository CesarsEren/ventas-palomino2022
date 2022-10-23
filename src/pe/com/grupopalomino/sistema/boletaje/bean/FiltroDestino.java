package pe.com.grupopalomino.sistema.boletaje.bean;

public class FiltroDestino {
	
	private String iddestino;
	private String strdestino;
	
	
	
	
	
	public FiltroDestino(String iddestino, String strdestino) {
		super();
		this.iddestino = iddestino;
		this.strdestino = strdestino;
	}
	
	
	public String getIddestino() {
		return iddestino;
	}
	public void setIddestino(String iddestino) {
		this.iddestino = iddestino;
	}
	public String getStrdestino() {
		return strdestino;
	}
	public void setStrdestino(String strdestino) {
		this.strdestino = strdestino;
	}
	
	
	

}
