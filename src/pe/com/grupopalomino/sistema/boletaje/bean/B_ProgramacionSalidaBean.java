
package pe.com.grupopalomino.sistema.boletaje.bean;

import java.util.Date;

public class B_ProgramacionSalidaBean {
    //atributos
    private int Nro;//numeric7
    private int Destino;//numeric7
    private String DestinoD;//char63
    private String Fecha;
    private String Servicio;//char2
    private String ServicioD;//char30
    private String Agencia1;//char3
    private String Agencia1D;//char30
    private String Hora1;//char5
    private String Agencia2;//char3
    private String Agencia2D;//char30
    private String Hora2;//char5
    private String Agencia3;//char3
    private String Agencia3D;//char30
    private String Hora3;//char5
    private double Precio1;//numeric12,2
    private double Precio2;//numeric12,2
    private String Bus;//char3
    private String Empresa;//char3
    private String EmpresaD;//char30
    private String Serie;//char3
    private String Correlativo;//char10
    private String NroAutorizacion;//char15
    private Date FechaImpresion;
    private int Desde;//numeric10
    private int Hasta;//numeric10
    private int Pasajero;//numeric10
    private int Asientos;//numeric4
    private double PasajeroVacio;//numeric8,2
    private String Comentario;//char1000
    private String Observacion;//char2000
    private String Terramoza;//char4
    private String TerramozaD;//char30
    private String TerramozaDni;//char15
    private int NroHojaE;//char8
    private int NroHojaM1;//char8
    private int NroHojaM2;//char8
    private String Factura;//char11
    private String Piloto;//char4
    private String PilotoD;//char30
    private String PilotoB;//char15
    private String PilotoPos;//char1
    private String CoPiloto;//char4
    private String CoPilotoD;//char30
    private String CoPilotoB;//char15
    private String CoPilotoPos;//char1
    private String CoPiloto2;//char4
    private String CoPilotoD2;//char30
    private String CoPilotoB2;//char15
    private String EstadoChofer;//char1
    private String EstadoCopiloto;//char1
    private String Hora4;//char5
    private String Agencia4;//char3
    private String Agencia4D;//char30
    private String PlanillaChofer;//char1
    private String Hora5;//char5
    private String Agencia5;//char3
    private String Agencia5D;//char30
    private String Hora6;//char5
    private String Agencia6;//char3
    private String Agencia6D;//char30
    private String Hora7;//char5
    private String Agencia7;//char3
    private String Agencia7D;//char30
    private String Hora8;//char5
    private String Agencia8;//char3
    private String Agencia8D;//char30
    private String HRutaNumero;//char7
    private String HCRutaNumero;//char7
    private int GrupoItinerario;
    
    /////////////////////////////////////////////////////
    /*ESTA VARIABLE SE CREO PARA EL MAPEO DE LA LISTA DE EMBARQUE EN LA PAGINA DE RESERVA DE BOLETO(NO USARLA)*/
    private String Agencia;
    private String AgenciaD;
    private String Hora;
    private String FechaPartida;
    private String FechaLlegada;
    private String codigo;
 // VARIABLES PARA MOSTRAR SI HAY PRECIOS CON PROMOCIONES
    private String Descuento1;
    private String Descuento2;
    
    public String getAgenciaD() {
		return AgenciaD;
	}

	public void setAgenciaD(String agenciaD) {
		AgenciaD = agenciaD;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getFechaPartida() {
		return FechaPartida;
	}

	public void setFechaPartida(String fechaPartida) {
		FechaPartida = fechaPartida;
	}

	public String getFechaLlegada() {
		return FechaLlegada;
	}

	public void setFechaLlegada(String fechaLlegada) {
		FechaLlegada = fechaLlegada;
	}

    public int getNro() {
        return Nro;
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setNro(int Nro) {
        this.Nro = Nro;
    }

    public int getDestino() {
        return Destino;
    }

    public void setDestino(int Destino) {
        this.Destino = Destino;
    }

    public String getDestinoD() {
        return DestinoD;
    }

    public void setDestinoD(String DestinoD) {
        this.DestinoD = DestinoD;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    public String getServicioD() {
        return ServicioD;
    }

    public void setServicioD(String ServicioD) {
        this.ServicioD = ServicioD;
    }

    public String getAgencia1() {
        return Agencia1;
    }

    public void setAgencia1(String Agencia1) {
        this.Agencia1 = Agencia1;
    }

    public String getAgencia1D() {
        return Agencia1D;
    }

    public void setAgencia1D(String Agencia1D) {
        this.Agencia1D = Agencia1D;
    }

    public String getHora1() {
        return Hora1;
    }

    public void setHora1(String Hora1) {
        this.Hora1 = Hora1;
    }

    public String getAgencia2() {
        return Agencia2;
    }

    public void setAgencia2(String Agencia2) {
        this.Agencia2 = Agencia2;
    }

    public String getAgencia2D() {
        return Agencia2D;
    }

    public void setAgencia2D(String Agencia2D) {
        this.Agencia2D = Agencia2D;
    }

    public String getHora2() {
        return Hora2;
    }

    public void setHora2(String Hora2) {
        this.Hora2 = Hora2;
    }

    public String getAgencia3() {
        return Agencia3;
    }

    public void setAgencia3(String Agencia3) {
        this.Agencia3 = Agencia3;
    }

    public String getAgencia3D() {
        return Agencia3D;
    }

    public void setAgencia3D(String Agencia3D) {
        this.Agencia3D = Agencia3D;
    }

    public String getHora3() {
        return Hora3;
    }

    public void setHora3(String Hora3) {
        this.Hora3 = Hora3;
    }

    public double getPrecio1() {
        return Precio1;
    }

    public void setPrecio1(double Precio1) {
        this.Precio1 = Precio1;
    }

    public double getPrecio2() {
        return Precio2;
    }

    public void setPrecio2(double Precio2) {
        this.Precio2 = Precio2;
    }

    public String getBus() {
        return Bus;
    }

    public void setBus(String Bus) {
        this.Bus = Bus;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    public String getEmpresaD() {
        return EmpresaD;
    }

    public void setEmpresaD(String EmpresaD) {
        this.EmpresaD = EmpresaD;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public String getCorrelativo() {
        return Correlativo;
    }

    public void setCorrelativo(String Correlativo) {
        this.Correlativo = Correlativo;
    }

    public String getNroAutorizacion() {
        return NroAutorizacion;
    }

    public void setNroAutorizacion(String NroAutorizacion) {
        this.NroAutorizacion = NroAutorizacion;
    }

    public Date getFechaImpresion() {
        return FechaImpresion;
    }

    public void setFechaImpresion(Date FechaImpresion) {
        this.FechaImpresion = FechaImpresion;
    }

    public int getDesde() {
        return Desde;
    }

    public void setDesde(int Desde) {
        this.Desde = Desde;
    }

    public int getHasta() {
        return Hasta;
    }

    public void setHasta(int Hasta) {
        this.Hasta = Hasta;
    }

    public int getPasajero() {
        return Pasajero;
    }

    public void setPasajero(int Pasajero) {
        this.Pasajero = Pasajero;
    }

    public int getAsientos() {
        return Asientos;
    }

    public void setAsientos(int Asientos) {
        this.Asientos = Asientos;
    }

    public double getPasajeroVacio() {
        return PasajeroVacio;
    }

    public void setPasajeroVacio(double PasajeroVacio) {
        this.PasajeroVacio = PasajeroVacio;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getTerramoza() {
        return Terramoza;
    }

    public void setTerramoza(String Terramoza) {
        this.Terramoza = Terramoza;
    }

    public String getTerramozaD() {
        return TerramozaD;
    }

    public void setTerramozaD(String TerramozaD) {
        this.TerramozaD = TerramozaD;
    }

    public String getTerramozaDni() {
        return TerramozaDni;
    }

    public void setTerramozaDni(String TerramozaDni) {
        this.TerramozaDni = TerramozaDni;
    }

    public int getNroHojaE() {
        return NroHojaE;
    }

    public void setNroHojaE(int NroHojaE) {
        this.NroHojaE = NroHojaE;
    }

    public int getNroHojaM1() {
        return NroHojaM1;
    }

    public void setNroHojaM1(int NroHojaM1) {
        this.NroHojaM1 = NroHojaM1;
    }

    public int getNroHojaM2() {
        return NroHojaM2;
    }

    public void setNroHojaM2(int NroHojaM2) {
        this.NroHojaM2 = NroHojaM2;
    }

    public String getFactura() {
        return Factura;
    }

    public void setFactura(String Factura) {
        this.Factura = Factura;
    }

    public String getPiloto() {
        return Piloto;
    }

    public void setPiloto(String Piloto) {
        this.Piloto = Piloto;
    }

    public String getPilotoD() {
        return PilotoD;
    }

    public void setPilotoD(String PilotoD) {
        this.PilotoD = PilotoD;
    }

    public String getPilotoB() {
        return PilotoB;
    }

    public void setPilotoB(String PilotoB) {
        this.PilotoB = PilotoB;
    }

    public String getPilotoPos() {
        return PilotoPos;
    }

    public void setPilotoPos(String PilotoPos) {
        this.PilotoPos = PilotoPos;
    }

    public String getCoPiloto() {
        return CoPiloto;
    }

    public void setCoPiloto(String CoPiloto) {
        this.CoPiloto = CoPiloto;
    }

    public String getCoPilotoD() {
        return CoPilotoD;
    }

    public void setCoPilotoD(String CoPilotoD) {
        this.CoPilotoD = CoPilotoD;
    }

    public String getCoPilotoB() {
        return CoPilotoB;
    }

    public void setCoPilotoB(String CoPilotoB) {
        this.CoPilotoB = CoPilotoB;
    }

    public String getCoPilotoPos() {
        return CoPilotoPos;
    }

    public void setCoPilotoPos(String CoPilotoPos) {
        this.CoPilotoPos = CoPilotoPos;
    }

    public String getCoPiloto2() {
        return CoPiloto2;
    }

    public void setCoPiloto2(String CoPiloto2) {
        this.CoPiloto2 = CoPiloto2;
    }

    public String getCoPilotoD2() {
        return CoPilotoD2;
    }

    public void setCoPilotoD2(String CoPilotoD2) {
        this.CoPilotoD2 = CoPilotoD2;
    }

    public String getCoPilotoB2() {
        return CoPilotoB2;
    }

    public void setCoPilotoB2(String CoPilotoB2) {
        this.CoPilotoB2 = CoPilotoB2;
    }

    public String getEstadoChofer() {
        return EstadoChofer;
    }

    public void setEstadoChofer(String EstadoChofer) {
        this.EstadoChofer = EstadoChofer;
    }

    public String getEstadoCopiloto() {
        return EstadoCopiloto;
    }

    public void setEstadoCopiloto(String EstadoCopiloto) {
        this.EstadoCopiloto = EstadoCopiloto;
    }

    public String getHora4() {
        return Hora4;
    }

    public void setHora4(String Hora4) {
        this.Hora4 = Hora4;
    }

    public String getAgencia4() {
        return Agencia4;
    }

    public void setAgencia4(String Agencia4) {
        this.Agencia4 = Agencia4;
    }

    public String getAgencia4D() {
        return Agencia4D;
    }

    public void setAgencia4D(String Agencia4D) {
        this.Agencia4D = Agencia4D;
    }

    public String getPlanillaChofer() {
        return PlanillaChofer;
    }

    public void setPlanillaChofer(String PlanillaChofer) {
        this.PlanillaChofer = PlanillaChofer;
    }

    public String getHora5() {
        return Hora5;
    }

    public void setHora5(String Hora5) {
        this.Hora5 = Hora5;
    }

    public String getAgencia5() {
        return Agencia5;
    }

    public void setAgencia5(String Agencia5) {
        this.Agencia5 = Agencia5;
    }

    public String getAgencia5D() {
        return Agencia5D;
    }

    public void setAgencia5D(String Agencia5D) {
        this.Agencia5D = Agencia5D;
    }

    public String getHora6() {
        return Hora6;
    }

    public void setHora6(String Hora6) {
        this.Hora6 = Hora6;
    }

    public String getAgencia6() {
        return Agencia6;
    }

    public void setAgencia6(String Agencia6) {
        this.Agencia6 = Agencia6;
    }

    public String getAgencia6D() {
        return Agencia6D;
    }

    public void setAgencia6D(String Agencia6D) {
        this.Agencia6D = Agencia6D;
    }

    public String getHora7() {
        return Hora7;
    }

    public void setHora7(String Hora7) {
        this.Hora7 = Hora7;
    }

    public String getAgencia7() {
        return Agencia7;
    }

    public void setAgencia7(String Agencia7) {
        this.Agencia7 = Agencia7;
    }

    public String getAgencia7D() {
        return Agencia7D;
    }

    public void setAgencia7D(String Agencia7D) {
        this.Agencia7D = Agencia7D;
    }

    public String getHora8() {
        return Hora8;
    }

    public void setHora8(String Hora8) {
        this.Hora8 = Hora8;
    }

    public String getAgencia8() {
        return Agencia8;
    }

    public void setAgencia8(String Agencia8) {
        this.Agencia8 = Agencia8;
    }

    public String getAgencia8D() {
        return Agencia8D;
    }

    public void setAgencia8D(String Agencia8D) {
        this.Agencia8D = Agencia8D;
    }

    public String getHRutaNumero() {
        return HRutaNumero;
    }

    public void setHRutaNumero(String HRutaNumero) {
        this.HRutaNumero = HRutaNumero;
    }

    public String getHCRutaNumero() {
        return HCRutaNumero;
    }

    public void setHCRutaNumero(String HCRutaNumero) {
        this.HCRutaNumero = HCRutaNumero;
    }

    public int getGrupoItinerario() {
        return GrupoItinerario;
    }

    public void setGrupoItinerario(int GrupoItinerario) {
        this.GrupoItinerario = GrupoItinerario;
    }

	public String getAgencia() {
		return Agencia;
	}

	public void setAgencia(String agencia) {
		Agencia = agencia;
	}
    
	public void setDescuento1(String descuento1) {
		Descuento1 = descuento1;
	}
	public String getDescuento1() {
		return Descuento1;
	}
	public void setDescuento2(String descuento2) {
		Descuento2 = descuento2;
	}
	public String getDescuento2() {
		return Descuento2;
	}
    
}
