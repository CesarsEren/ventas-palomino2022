package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_MenusBean {
    //atributos
    private String Menu;//char50
    private String Descripcion;//char50
    private String Niveles;//char30
    private String Orden;//char3

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String Menu) {
        this.Menu = Menu;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getNiveles() {
        return Niveles;
    }

    public void setNiveles(String Niveles) {
        this.Niveles = Niveles;
    }

    public String getOrden() {
        return Orden;
    }

    public void setOrden(String Orden) {
        this.Orden = Orden;
    }
}