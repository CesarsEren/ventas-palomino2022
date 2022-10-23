
package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_UsuariosRBean {
    public V_UsuariosRBean(String Usuario, String Password) {
        this.Usuario = Usuario;
        this.Password = Password;
    }
    public V_UsuariosRBean() {
    }
    //atributos
    private String CodUsuario; //char 4   
    private String Usuario;//char 25
    private String NomUsuario;//char 50
    private String Nivel;//char1
    private String Ruc;//char11
    private String Razon;//char50
    private String Correo;//char100
    private String Password;//char10

    public String getCodUsuario() {
        return CodUsuario;
    }

    public void setCodUsuario(String CodUsuario) {
        this.CodUsuario = CodUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNomUsuario() {
        return NomUsuario;
    }

    public void setNomUsuario(String NomUsuario) {
        this.NomUsuario = NomUsuario;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String Ruc) {
        this.Ruc = Ruc;
    }

    public String getRazon() {
        return Razon;
    }

    public void setRazon(String Razon) {
        this.Razon = Razon;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
	@Override
	public String toString() {
		return "V_UsuariosRBean [CodUsuario=" + CodUsuario + ", Usuario=" + Usuario + ", NomUsuario=" + NomUsuario
				+ ", Nivel=" + Nivel + ", Ruc=" + Ruc + ", Razon=" + Razon + ", Correo=" + Correo + ", Password="
				+ Password + "]";
	}
    
}