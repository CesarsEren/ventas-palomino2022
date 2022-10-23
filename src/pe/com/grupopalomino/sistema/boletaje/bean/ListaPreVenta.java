package pe.com.grupopalomino.sistema.boletaje.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ListaPreVenta implements Serializable {

	private String idPasajero;
	private int Nro;
	private int Salida;
	private String Nombre;
	private double PrecioAct;
	private String FechaEmision;
	private String FechaViaje;
	private String Hora1;
	private int NroCC;
	private int NroDocCC;
	private int NroAplic;
	private String EnvioCorreo;
	private String ruc;
	private int asiento;
	private String DestinoD;
	private String Empresa;//codeempresa

	public String getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}
	
	public String getHora1() {
		return Hora1;
	}
	
	public void setHora1(String hora1) {
		Hora1 = hora1;
	}
	
	public String getFechaViaje() {
		return FechaViaje;
	}
	
	public void setFechaViaje(String fechaViaje) {
		FechaViaje = fechaViaje;
	}
	
	public String getDestinoD() {
		return DestinoD;
	}

	public void setDestinoD(String destinoD) {
		DestinoD = destinoD;
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public void setIdPasajero(String idPasajero) {
		this.idPasajero = idPasajero;
	}

	public String getIdPasajero() {
		return idPasajero;
	}

	public void setNro(int nro) {
		Nro = nro;
	}

	public int getNro() {
		return Nro;
	}

	public void setSalida(int salida) {
		Salida = salida;
	}

	public int getSalida() {
		return Salida;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setPrecioAct(double precioAct) {
		PrecioAct = precioAct;
	}

	public double getPrecioAct() {
		return PrecioAct;
	}

	public void setNroCC(int nroCC) {
		NroCC = nroCC;
	}

	public int getNroCC() {
		return NroCC;
	}

	public void setNroDocCC(int nroDocCC) {
		NroDocCC = nroDocCC;
	}

	public int getNroDocCC() {
		return NroDocCC;
	}

	public void setFechaEmision(String fechaEmision) {
		FechaEmision = fechaEmision;
	}

	public String getFechaEmision() {
		return FechaEmision;
	}

	public void setNroAplic(int nroAplic) {
		NroAplic = nroAplic;
	}

	public int getNroAplic() {
		return NroAplic;
	}

	public void setEnvioCorreo(String envioCorreo) {
		EnvioCorreo = envioCorreo;
	}

	public String getEnvioCorreo() {
		return EnvioCorreo;
	}

	public void SetValuesPreVentaIda(String IdPasajero, int Nro, int Salida, String Nombre, double PrecioAct, int NroCC,
			int NroDocCC, int NroAplic, String FechaEmision) {
		this.setIdPasajero(IdPasajero);
		this.setNro(Nro);
		this.setSalida(Salida);
		this.setNombre(Nombre);
		this.setPrecioAct(PrecioAct);
		this.setNroCC(NroCC);
		this.setNroDocCC(NroDocCC);
		this.setNroAplic(NroAplic);
		this.setFechaEmision(FechaEmision);
	}

}
