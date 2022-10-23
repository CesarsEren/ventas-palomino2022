package pe.com.grupopalomino.sistema.boletaje.spring.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class SpringSecurityClient implements UserDetails,Serializable {
	
	private Integer id;
	private String username;
	private String password;
	private String confirm_password;
	private String confirm_email;
	private String nombreCompleto;
	private String identidad;
	private String numerodocumento;
	private String nivel;
	private String estado;
	private String fechacreacion;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String email;
	
	private String token;
	
	private int confirmado;
	
	private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
	
	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(int confirmado) {
		this.confirmado = confirmado;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public String getConfirm_email() {
		return confirm_email;
	}
	public void setConfirm_email(String confirm_email) {
		this.confirm_email = confirm_email;
	}
	public String getNombreCompleto() {
		return getNombres() + " " + getApellidos();
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public void setIdentidad(String identidad) {
		this.identidad = identidad;
	}
	public String getIdentidad() {
		return identidad;
	}
	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}
	public String getNumerodocumento() {
		return numerodocumento;
	}
	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public String getFechacreacion() {
		return fechacreacion;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void SetvaluesClient(SpringSecurityClient client){
		this.setNombres(client.getNombres().trim().toUpperCase());
		this.setApellidos(client.getApellidos().trim().toUpperCase()); 
		this.setNivel("SMS");
		this.setUsername(client.getEmail()); 
		this.setToken(UUID.randomUUID().toString().replace("-", "")); 
		if(client.getIdentidad().trim().equals("0")){
			this.setIdentidad("D.N.I");
		}else if(client.getIdentidad().trim().equals("1")){
			this.setIdentidad("Pasaporte");
		}else if(client.getIdentidad().trim().equals("2")){
			this.setIdentidad("Carnet Extranjeria");
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void addAuthority(GrantedAuthority authority){
		authorities.add(authority);
	}

}
