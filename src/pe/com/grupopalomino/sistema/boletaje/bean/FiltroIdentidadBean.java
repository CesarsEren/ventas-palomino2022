package pe.com.grupopalomino.sistema.boletaje.bean;

public class FiltroIdentidadBean {
	
	private String ididentidad;
	
	private String stridentidad;

	public FiltroIdentidadBean(String ididentidad, String stridentidad) {
		super();
		this.ididentidad = ididentidad;
		this.stridentidad = stridentidad;
	}

	public String getIdidentidad() {
		return ididentidad;
	}

	public void setIdidentidad(String ididentidad) {
		this.ididentidad = ididentidad;
	}

	public String getStridentidad() {
		return stridentidad;
	}

	public void setStridentidad(String stridentidad) {
		this.stridentidad = stridentidad;
	}
	
	

}
