
package pe.com.grupopalomino.sistema.boletaje.bean;

import java.io.Serializable;

public class V_DestinosBean implements Serializable{
  
	private static final long serialVersionUID = 5459777975140010813L;
	//atributos
    private int Nro;//numeric7
    private String Origen;//char3
    private String OrigenD;//char30
    private String Destino;//char3
    private String DestinoD;//char30
    private String Agencia;//char3
    private String AgenciaD;//char3
    private double Kilometraje;//numeric12.2
    private String CodLiq;//char2
    private String CodigoWeb;//char4
    private String DireccionOrigen;//char200
    private String DireccionDestino;//char200
    private String Descripcion;////VARIABLE USADA PARA EL MAPEO DE LA DESCRIPCION DEL ORIGEN+DESTINO (OrigenD+DestinoD)

    public int getNro() {
        return Nro;
    }

    public void setNro(int Nro) {
        this.Nro = Nro;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public String getOrigenD() {
        return OrigenD;
    }

    public void setOrigenD(String OrigenD) {
        this.OrigenD = OrigenD;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getDestinoD() {
        return DestinoD;
    }

    public void setDestinoD(String DestinoD) {
        this.DestinoD = DestinoD;
    }

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String Agencia) {
        this.Agencia = Agencia;
    }

    public String getAgenciaD() {
        return AgenciaD;
    }

    public void setAgenciaD(String AgenciaD) {
        this.AgenciaD = AgenciaD;
    }

    public double getKilometraje() {
        return Kilometraje;
    }

    public void setKilometraje(double Kilometraje) {
        this.Kilometraje = Kilometraje;
    }

    public String getCodLiq() {
        return CodLiq;
    }

    public void setCodLiq(String CodLiq) {
        this.CodLiq = CodLiq;
    }

    public String getCodigoWeb() {
        return CodigoWeb;
    }

    public void setCodigoWeb(String CodigoWeb) {
        this.CodigoWeb = CodigoWeb;
    }

    public String getDireccionOrigen() {
        return DireccionOrigen;
    }

    public void setDireccionOrigen(String DireccionOrigen) {
        this.DireccionOrigen = DireccionOrigen;
    }

    public String getDireccionDestino() {
        return DireccionDestino;
    }

    public void setDireccionDestino(String DireccionDestino) {
        this.DireccionDestino = DireccionDestino;
    }

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
    
    
    
    
    
    
}
