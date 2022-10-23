package pe.com.grupopalomino.sistema.boletaje.bean;

import java.io.Serializable;

@SuppressWarnings("serial")

public class V_Varios implements Serializable{
	
	private double PasajeDscto;
	private double PasajeDsctoR;
	private double MaximoBoletoPrecio;
	private String PostergadoNivelUsu;
	private String AnuladoNivelUsu;
	private String ReservaUsuario1;
	private String ReservaUsuario2;
	private String ReservaUsuario3;
	private Integer RecorridoMinimoChofer;
	private Integer KiloMetrajeMin;
	private String KiloMetrajeVig;
	private String BloquearNombre;
	private Integer DiasAsientoCero;
	private int cantidadMaximaPasajeros;
	private String RutaNoReserva;
	private double Igv;
	private String Pass;
	private String mostrarComida;
	private int TiempoCaducidadVisa;
	private int TiempoCaducidadPagoEfectivo;
	private int TiempoCaducidadExtraPagoEfectivo;
	private String PagoEfectivoCodServ;
	private String PagoEfectivoMedioPago;
	private String UrlPagoEfectivo;
	private String PagoEfectivoMoneda;
	private String PagoEfectivoEmailComercio;
	private String CorreoReclamos;
	private String CorreoReclamosCC;
	private String CorreoPreguntasFrecuentes;
	private String CorreoPreguntasFrecuentesCC;
	private String ResponsableAtencionReclamos;
	private String CorreoReclamoPasajes;
	private String CorreoReclamoEncomiendas;
	private String CorreoReclamoPasajesCC;
	private String CorreoReclamoEncomiendasCC;
	
	public double getPasajeDscto() {
		return PasajeDscto;
	}
	public void setPasajeDscto(double pasajeDscto) {
		PasajeDscto = pasajeDscto;
	}
	public double getPasajeDsctoR() {
		return PasajeDsctoR;
	}
	public void setPasajeDsctoR(double pasajeDsctoR) {
		PasajeDsctoR = pasajeDsctoR;
	}
	public double getMaximoBoletoPrecio() {
		return MaximoBoletoPrecio;
	}
	public void setMaximoBoletoPrecio(double maximoBoletoPrecio) {
		MaximoBoletoPrecio = maximoBoletoPrecio;
	}
	public String getPostergadoNivelUsu() {
		return PostergadoNivelUsu;
	}
	public void setPostergadoNivelUsu(String postergadoNivelUsu) {
		PostergadoNivelUsu = postergadoNivelUsu;
	}
	public String getAnuladoNivelUsu() {
		return AnuladoNivelUsu;
	}
	public void setAnuladoNivelUsu(String anuladoNivelUsu) {
		AnuladoNivelUsu = anuladoNivelUsu;
	}
	public String getReservaUsuario1() {
		return ReservaUsuario1;
	}
	public void setReservaUsuario1(String reservaUsuario1) {
		ReservaUsuario1 = reservaUsuario1;
	}
	public String getReservaUsuario2() {
		return ReservaUsuario2;
	}
	public void setReservaUsuario2(String reservaUsuario2) {
		ReservaUsuario2 = reservaUsuario2;
	}
	public String getReservaUsuario3() {
		return ReservaUsuario3;
	}
	public void setReservaUsuario3(String reservaUsuario3) {
		ReservaUsuario3 = reservaUsuario3;
	}
	public Integer getRecorridoMinimoChofer() {
		return RecorridoMinimoChofer;
	}
	public void setRecorridoMinimoChofer(Integer recorridoMinimoChofer) {
		RecorridoMinimoChofer = recorridoMinimoChofer;
	}
	public Integer getKiloMetrajeMin() {
		return KiloMetrajeMin;
	}
	public void setKiloMetrajeMin(Integer kiloMetrajeMin) {
		KiloMetrajeMin = kiloMetrajeMin;
	}
	public String getKiloMetrajeVig() {
		return KiloMetrajeVig;
	}
	public void setKiloMetrajeVig(String kiloMetrajeVig) {
		KiloMetrajeVig = kiloMetrajeVig;
	}
	public String getBloquearNombre() {
		return BloquearNombre;
	}
	public void setBloquearNombre(String bloquearNombre) {
		BloquearNombre = bloquearNombre;
	}
	public Integer getDiasAsientoCero() {
		return DiasAsientoCero;
	}
	public void setDiasAsientoCero(Integer diasAsientoCero) {
		DiasAsientoCero = diasAsientoCero;
	}
	public String getRutaNoReserva() {
		return RutaNoReserva;
	}
	public void setRutaNoReserva(String rutaNoReserva) {
		RutaNoReserva = rutaNoReserva;
	}
	public double getIgv() {
		return Igv;
	}
	public void setIgv(double igv) {
		Igv = igv;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public void setCantidadMaximaPasajeros(int cantidadMaximaPasajeros) {
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
	}
	public int getCantidadMaximaPasajeros() {
		return cantidadMaximaPasajeros;
	}
	public String getMostrarComida() {
		return mostrarComida;
	}
	public void setMostrarComida(String mostrarComida) {
		this.mostrarComida = mostrarComida;
	}
	public int getTiempoCaducidadVisa() {
		return TiempoCaducidadVisa;
	}
	public void setTiempoCaducidadVisa(int tiempoCaducidadVisa) {
		TiempoCaducidadVisa = tiempoCaducidadVisa;
	}
	public int getTiempoCaducidadPagoEfectivo() {
		return TiempoCaducidadPagoEfectivo;
	}
	public void setTiempoCaducidadPagoEfectivo(int tiempoCaducidadPagoEfectivo) {
		TiempoCaducidadPagoEfectivo = tiempoCaducidadPagoEfectivo;
	}
	public int getTiempoCaducidadExtraPagoEfectivo() {
		return TiempoCaducidadExtraPagoEfectivo;
	}
	public void setTiempoCaducidadExtraPagoEfectivo(int tiempoCaducidadExtraPagoEfectivo) {
		TiempoCaducidadExtraPagoEfectivo = tiempoCaducidadExtraPagoEfectivo;
	}
	public String getPagoEfectivoCodServ() {
		return PagoEfectivoCodServ;
	}
	public void setPagoEfectivoCodServ(String pagoEfectivoCodServ) {
		PagoEfectivoCodServ = pagoEfectivoCodServ;
	}
	public String getPagoEfectivoMedioPago() {
		return PagoEfectivoMedioPago;
	}
	public void setPagoEfectivoMedioPago(String pagoEfectivoMedioPago) {
		PagoEfectivoMedioPago = pagoEfectivoMedioPago;
	}
	public String getUrlPagoEfectivo() {
		return UrlPagoEfectivo;
	}
	public void setUrlPagoEfectivo(String urlPagoEfectivo) {
		UrlPagoEfectivo = urlPagoEfectivo;
	}
	public String getPagoEfectivoMoneda() {
		return PagoEfectivoMoneda;
	}
	public void setPagoEfectivoMoneda(String pagoEfectivoMoneda) {
		PagoEfectivoMoneda = pagoEfectivoMoneda;
	}
	public String getPagoEfectivoEmailComercio() {
		return PagoEfectivoEmailComercio;
	}
	public void setPagoEfectivoEmailComercio(String pagoEfectivoEmailComercio) {
		PagoEfectivoEmailComercio = pagoEfectivoEmailComercio;
	}
	
	public void setCorreoReclamos(String correoReclamos) {
		CorreoReclamos = correoReclamos;
	}
	public String getCorreoReclamos() {
		return CorreoReclamos;
	}
	public void setCorreoReclamosCC(String correoReclamosCC) {
		CorreoReclamosCC = correoReclamosCC;
	}
	public String getCorreoReclamosCC() {
		return CorreoReclamosCC;
	}
	public void setCorreoPreguntasFrecuentes(String correoPreguntasFrecuentes) {
		CorreoPreguntasFrecuentes = correoPreguntasFrecuentes;
	}
	public String getCorreoPreguntasFrecuentes() {
		return CorreoPreguntasFrecuentes;
	}
	public void setCorreoPreguntasFrecuentesCC(String correoPreguntasFrecuentesCC) {
		CorreoPreguntasFrecuentesCC = correoPreguntasFrecuentesCC;
	}
	public String getCorreoPreguntasFrecuentesCC() {
		return CorreoPreguntasFrecuentesCC;
	}
	public void setResponsableAtencionReclamos(String responsableAtencionReclamos) {
		ResponsableAtencionReclamos = responsableAtencionReclamos;
	}
	public String getResponsableAtencionReclamos() {
		return ResponsableAtencionReclamos;
	}
	public void setCorreoReclamoEncomiendas(String correoReclamoEncomiendas) {
		CorreoReclamoEncomiendas = correoReclamoEncomiendas;
	}
	public String getCorreoReclamoEncomiendas() {
		return CorreoReclamoEncomiendas;
	}
	public void setCorreoReclamoPasajes(String correoReclamoPasajes) {
		CorreoReclamoPasajes = correoReclamoPasajes;
	}
	public String getCorreoReclamoPasajes() {
		return CorreoReclamoPasajes;
	}
	public void setCorreoReclamoPasajesCC(String correoReclamoPasajesCC) {
		CorreoReclamoPasajesCC = correoReclamoPasajesCC;
	}
	public String getCorreoReclamoPasajesCC() {
		return CorreoReclamoPasajesCC;
	}
	public void setCorreoReclamoEncomiendasCC(String correoReclamoEncomiendasCC) {
		CorreoReclamoEncomiendasCC = correoReclamoEncomiendasCC;
	}
	public String getCorreoReclamoEncomiendasCC() {
		return CorreoReclamoEncomiendasCC;
	}

}
