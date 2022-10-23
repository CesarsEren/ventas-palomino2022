
package pe.com.grupopalomino.sistema.boletaje.bean;

public class B_ProgramacionRutaBusBean {
    //atributos
    private int Nro;//numeric7
    private String Ciudad;//char3
    private String CiudadD;//char30
    private String Hora1;//char5
    private String Dia;//char1
    private String Hora2;//char5
    private String Hora3;//char5

    public int getNro() {
        return Nro;
    }

    public void setNro(int Nro) {
        this.Nro = Nro;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getCiudadD() {
        return CiudadD;
    }

    public void setCiudadD(String CiudadD) {
        this.CiudadD = CiudadD;
    }

    public String getHora1() {
        return Hora1;
    }

    public void setHora1(String Hora1) {
        this.Hora1 = Hora1;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
    }

    public String getHora2() {
        return Hora2;
    }

    public void setHora2(String Hora2) {
        this.Hora2 = Hora2;
    }

    public String getHora3() {
        return Hora3;
    }

    public void setHora3(String Hora3) {
        this.Hora3 = Hora3;
    }
    
}
