package pe.com.grupopalomino.sistema.boletaje.bean;

import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_VersionesAppBean {
	
	private int id ;
	private String Plataforma;
	private String Fecha;
	private String Version_App;
	private String Critico;
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setPlataforma(String plataforma) {
		Plataforma = plataforma;
	}
	public String getPlataforma() {
		return Plataforma;
	}
	
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	 public String getFecha() {
		return Fecha;
	}
	 public void setVersion_App(String version_App) {
		Version_App = version_App;
	}
	 public String getVersion_App() {
		return Version_App;
	}
	 public void setCritico(String critico) {
		Critico = critico;
	}
	 public String getCritico() {
		return Critico;
	}
	
	 public void  set_B_VersionesAppBean (B_VersionesAppBean bean){
		 
		 this.setPlataforma(bean.getPlataforma());
		 this.setFecha(Utils.FormatoFecha(bean.getFecha()));
		 this.setVersion_App(bean.getVersion_App());
		 this.setCritico(bean.getCritico()); 
		 
		 
	 }
	

}
