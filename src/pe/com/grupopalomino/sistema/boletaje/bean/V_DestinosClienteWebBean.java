package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_DestinosClienteWebBean {
	
	private int Nro;
	private String Origen;//char3
    private String OrigenD;//char30
    private String Destino;//char3
    private String DestinoD;//char30
	private String Ruc;
	
	
	public void setNro(int nro) {
		Nro = nro;
	}
	public int getNro() {
		return Nro;
	}
	public void setOrigen(String origen) {
		Origen = origen;
	}
	public String getOrigen() {
		return Origen;
	}
	public void setOrigenD(String origenD) {
		OrigenD = origenD;
	}
	public String getOrigenD() {
		return OrigenD;
	}
	public void setDestino(String destino) {
		Destino = destino;
	}
	public String getDestino() {
		return Destino;
	}
	public void setDestinoD(String destinoD) {
		DestinoD = destinoD;
	}
	public String getDestinoD() {
		return DestinoD;
	}
	public void setRuc(String ruc) {
		Ruc = ruc;
	}
	public String getRuc() {
		return Ruc;
	}
	
	

}
