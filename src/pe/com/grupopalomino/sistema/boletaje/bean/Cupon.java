package pe.com.grupopalomino.sistema.boletaje.bean;

import java.util.Date;

public class Cupon {

    private int idcupon;
    private String codigocupon;
    private String Detalle;
    private double Descuento;
    private String FechaCreacion;
    private String tipocupon;
    private String paracupon;
    private Date FechaVigInicio;
    private Date FechaVigFin;
    private Date FechaComIdaIni;
    private Date FechaComIdaFin;
    private Date FechaCompVuIni;
    private Date FechaCompVuFin;
    private String horainicio;
    private String horafin;
    private boolean activo;
    
    public Cupon() {
		// TODO Auto-generated constructor stub
	}
    
    
	public Cupon(int idcupon, String codigocupon, String detalle, Double descuento, String fechaCreacion,
			String tipocupon, String paracupon, Date fechaVigInicio, Date fechaVigFin, Date fechaComIdaIni,
			Date fechaComIdaFin, Date fechaCompVuIni, Date fechaCompVuFin, String horainicio, String horafin,
			boolean activo) {
		super();
		this.idcupon = idcupon;
		this.codigocupon = codigocupon;
		Detalle = detalle;
		Descuento = descuento;
		FechaCreacion = fechaCreacion;
		this.tipocupon = tipocupon;
		this.paracupon = paracupon;
		FechaVigInicio = fechaVigInicio;
		FechaVigFin = fechaVigFin;
		FechaComIdaIni = fechaComIdaIni;
		FechaComIdaFin = fechaComIdaFin;
		FechaCompVuIni = fechaCompVuIni;
		FechaCompVuFin = fechaCompVuFin;
		this.horainicio = horainicio;
		this.horafin = horafin;
		this.activo = activo;
	}
	public int getIdcupon() {
		return idcupon;
	}
	public void setIdcupon(int idcupon) {
		this.idcupon = idcupon;
	}
	public String getCodigocupon() {
		return codigocupon;
	}
	public void setCodigocupon(String codigocupon) {
		this.codigocupon = codigocupon;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public double getDescuento() {
		return Descuento;
	}
	public void setDescuento(double descuento) {
		Descuento = descuento;
	}
	public String getFechaCreacion() {
		return FechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}
	public String getTipocupon() {
		return tipocupon;
	}
	public void setTipocupon(String tipocupon) {
		this.tipocupon = tipocupon;
	}
	public String getParacupon() {
		return paracupon;
	}
	public void setParacupon(String paracupon) {
		this.paracupon = paracupon;
	}
	public Date getFechaVigInicio() {
		return FechaVigInicio;
	}
	public void setFechaVigInicio(Date fechaVigInicio) {
		FechaVigInicio = fechaVigInicio;
	}
	public Date getFechaVigFin() {
		return FechaVigFin;
	}
	public void setFechaVigFin(Date fechaVigFin) {
		FechaVigFin = fechaVigFin;
	}
	public Date getFechaComIdaIni() {
		return FechaComIdaIni;
	}
	public void setFechaComIdaIni(Date fechaComIdaIni) {
		FechaComIdaIni = fechaComIdaIni;
	}
	public Date getFechaComIdaFin() {
		return FechaComIdaFin;
	}
	public void setFechaComIdaFin(Date fechaComIdaFin) {
		FechaComIdaFin = fechaComIdaFin;
	}
	public Date getFechaCompVuIni() {
		return FechaCompVuIni;
	}
	public void setFechaCompVuIni(Date fechaCompVuIni) {
		FechaCompVuIni = fechaCompVuIni;
	}
	public Date getFechaCompVuFin() {
		return FechaCompVuFin;
	}
	public void setFechaCompVuFin(Date fechaCompVuFin) {
		FechaCompVuFin = fechaCompVuFin;
	}
	public String getHorainicio() {
		return horainicio;
	}
	public void setHorainicio(String horainicio) {
		this.horainicio = horainicio;
	}
	public String getHorafin() {
		return horafin;
	}
	public void setHorafin(String horafin) {
		this.horafin = horafin;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
    
}
