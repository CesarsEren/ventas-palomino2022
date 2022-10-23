package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_AgenciasBean {
	
	private String Codigo,Detalle,Ciudad,CiudadD,Direccion,Telefono,
	Destino,Color,Intermedio,Sistema,CodLiq,GiroRadial,AgenciaVje,
	ManejaCodSecreto,CodigoWeb,longitud,latitud;
	private int cantidad;

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getDetalle() {
		return Detalle;
	}

	public void setDetalle(String detalle) {
		Detalle = detalle;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	public String getCiudadD() {
		return CiudadD;
	}

	public void setCiudadD(String ciudadD) {
		CiudadD = ciudadD;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getDestino() {
		return Destino;
	}

	public void setDestino(String destino) {
		Destino = destino;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getIntermedio() {
		return Intermedio;
	}

	public void setIntermedio(String intermedio) {
		Intermedio = intermedio;
	}

	public String getSistema() {
		return Sistema;
	}

	public void setSistema(String sistema) {
		Sistema = sistema;
	}

	public String getCodLiq() {
		return CodLiq;
	}

	public void setCodLiq(String codLiq) {
		CodLiq = codLiq;
	}

	public String getGiroRadial() {
		return GiroRadial;
	}

	public void setGiroRadial(String giroRadial) {
		GiroRadial = giroRadial;
	}

	public String getAgenciaVje() {
		return AgenciaVje;
	}

	public void setAgenciaVje(String agenciaVje) {
		AgenciaVje = agenciaVje;
	}

	public String getManejaCodSecreto() {
		return ManejaCodSecreto;
	}

	public void setManejaCodSecreto(String manejaCodSecreto) {
		ManejaCodSecreto = manejaCodSecreto;
	}

	public String getCodigoWeb() {
		return CodigoWeb;
	}

	public void setCodigoWeb(String codigoWeb) {
		CodigoWeb = codigoWeb;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCantidad() {
		return cantidad;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	@Override
	public String toString() {
		return "V_AgenciasBean [Codigo=" + Codigo + ", Detalle=" + Detalle + ", Ciudad=" + Ciudad + ", CiudadD="
				+ CiudadD + ", Direccion=" + Direccion + ", Telefono=" + Telefono + ", Destino=" + Destino + ", Color="
				+ Color + ", Intermedio=" + Intermedio + ", Sistema=" + Sistema + ", CodLiq=" + CodLiq + ", GiroRadial="
				+ GiroRadial + ", AgenciaVje=" + AgenciaVje + ", ManejaCodSecreto=" + ManejaCodSecreto + ", CodigoWeb="
				+ CodigoWeb + ", longitud=" + longitud + ", latitud=" + latitud + ", cantidad=" + cantidad + "]";
	}
	
	

}
