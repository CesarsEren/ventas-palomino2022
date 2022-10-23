package pe.com.grupopalomino.sistema.boletaje.bean;



public class V_DestinosMapaBean {
	private String type;
	private String Id;
	private String Destino;
	private String DestinoD;
	private String hc_key;
	private String href;
	private String img;
	private String coordenadas;
	private String disponible;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getId() {
		return Id;
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
	public String getHc_key() {
		return hc_key;
	}
	public void setHc_key(String hc_key) {
		this.hc_key = hc_key;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}
	public String getDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	@Override
	public String toString() {
		return "V_DestinosMapa [type=" + type + ", id=" + Id + ", Destino=" + Destino + ", DestinoD=" + DestinoD
				+ ", hc_key=" + hc_key + ", href=" + href + ", img=" + img + ", coordenadas=" + coordenadas
				+ ", disponible=" + disponible + "]";
	}

}

 

