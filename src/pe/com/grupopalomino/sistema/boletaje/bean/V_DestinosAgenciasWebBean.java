package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_DestinosAgenciasWebBean {
	
	private String Ruc;
	private Integer Nro;
	private String Origen;
	private String OrigenD;
	private String AgenciaOrigen;
	private String AgenciaOrigenD;
	private String Destino;
	private String DestinoD;
	private String AgenciaDestino;
	private String AgenciaDestinoD;
	private String CiudadD;
	
	public String getRuc() {
		return Ruc;
	}
	public void setRuc(String ruc) {
		Ruc = ruc;
	}
	public Integer getNro() {
		return Nro;
	}
	public void setNro(Integer nro) {
		Nro = nro;
	}
	public String getOrigen() {
		return Origen;
	}
	public void setOrigen(String origen) {
		Origen = origen;
	}
	public String getOrigenD() {
		return OrigenD;
	}
	public void setOrigenD(String origenD) {
		OrigenD = origenD;
	}
	public String getAgenciaOrigen() {
		return AgenciaOrigen;
	}
	public void setAgenciaOrigen(String agenciaOrigen) {
		AgenciaOrigen = agenciaOrigen;
	}
	public String getAgenciaOrigenD() {
		return AgenciaOrigenD;
	}
	public void setAgenciaOrigenD(String agenciaOrigenD) {
		AgenciaOrigenD = agenciaOrigenD;
	}
	public String getDestino() {
		return Destino;
	}
	public void setDestino(String destino) {
		Destino = destino;
	}
	public String getDestinoD() {
		return DestinoD;
	}
	public void setDestinoD(String destinoD) {
		DestinoD = destinoD;
	}
	public String getAgenciaDestino() {
		return AgenciaDestino;
	}
	public void setAgenciaDestino(String agenciaDestino) {
		AgenciaDestino = agenciaDestino;
	}
	public String getAgenciaDestinoD() {
		return AgenciaDestinoD;
	}
	public void setAgenciaDestinoD(String agenciaDestinoD) {
		AgenciaDestinoD = agenciaDestinoD;
	}
	
	public void setCiudadD(String ciudadD) {
		CiudadD = ciudadD;
	}
	public String getCiudadD() {
		return CiudadD;
	}
	@Override
	public String toString() {
		return "V_DestinosAgenciasWebBean [Ruc=" + Ruc + ", Nro=" + Nro + ", Origen=" + Origen + ", OrigenD=" + OrigenD
				+ ", AgenciaOrigen=" + AgenciaOrigen + ", AgenciaOrigenD=" + AgenciaOrigenD + ", Destino=" + Destino
				+ ", DestinoD=" + DestinoD + ", AgenciaDestino=" + AgenciaDestino + ", AgenciaDestinoD="
				+ AgenciaDestinoD + ", CiudadD=" + CiudadD + "]";
	}
	

	
	

}
