package pe.com.grupopalomino.sistema.boletaje.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class SpringSecurityUser implements UserDetails {

	private Integer id;
	private String username;
	private String password;
	private String nombreCompleto;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nivel;
	private String ruc;
	private String razonSocial;
	private String razonPrueba;
	private String correo;
	private String telefono;
	private String estado;
	private double limiteCredito;
	private double montoVentaActual;
	private double montoVentaConfirmada;
	private String codigoAgencia;
	private String descripcionAgencia;
	private String direccion;
	private String representante;
	private String telefono2;
	private String localidad;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private String ocultarDetalleEstadoCuenta;
	private String centroCosto;
	private String realizaVenta;
	private double porcentajeComision;
	private int medio_Pago;
	private String ciudad;
	private String ciudadd;
	private String longitud, latitud;
	private String agencia;
	private String agenciaD;
	private String empresa;
	private String empresaD;

	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	public String getRazonPrueba() {
		return razonPrueba;
	}

	public void setRazonPrueba(String razonPrueba) {
		this.razonPrueba = razonPrueba;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreCompleto() {
		return nombres + " " + apellidoPaterno + " " + apellidoMaterno;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public double getMontoVentaActual() {
		return montoVentaActual;
	}

	public void setMontoVentaActual(double montoVentaActual) {
		this.montoVentaActual = montoVentaActual;
	}

	public double getMontoVentaConfirmada() {
		return montoVentaConfirmada;
	}

	public void setMontoVentaConfirmada(double montoVentaConfirmada) {
		this.montoVentaConfirmada = montoVentaConfirmada;
	}

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public String getDescripcionAgencia() {
		return descripcionAgencia;
	}

	public void setDescripcionAgencia(String descripcionAgencia) {
		this.descripcionAgencia = descripcionAgencia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getOcultarDetalleEstadoCuenta() {
		return ocultarDetalleEstadoCuenta;
	}

	public void setOcultarDetalleEstadoCuenta(String ocultarDetalleEstadoCuenta) {
		this.ocultarDetalleEstadoCuenta = ocultarDetalleEstadoCuenta;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public String getRealizaVenta() {
		return realizaVenta;
	}

	public void setRealizaVenta(String realizaVenta) {
		this.realizaVenta = realizaVenta;
	}

	public double getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	public int getMedio_Pago() {
		return medio_Pago;
	}

	public void setMedio_Pago(int medio_Pago) {
		this.medio_Pago = medio_Pago;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCiudadd() {
		return ciudadd;
	}

	public void setCiudadd(String ciudadd) {
		this.ciudadd = ciudadd;
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

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public SpringSecurityUser SetValuesTouperCaseTransporteDePersonal(SpringSecurityUser user) {

		this.setNombres(user.getNombres().toUpperCase());
		this.setApellidoMaterno(user.getApellidoMaterno().toUpperCase());
		this.setApellidoPaterno(user.getApellidoPaterno().toUpperCase());
		this.setDireccion(user.getDireccion().toUpperCase());
		this.setRazonSocial(user.getRazonSocial().toUpperCase());
		this.setNombreCompleto(user.getNombreCompleto().toUpperCase());
		this.setPassword(user.getPassword().toUpperCase());

		return user;
	}

	public SpringSecurityUser SetValuesTouperCase(SpringSecurityUser user) {

		this.setNombres(user.getNombres().toUpperCase());
		this.setApellidoMaterno(user.getApellidoMaterno().toUpperCase());
		this.setApellidoPaterno(user.getApellidoPaterno().toUpperCase());
		this.setDireccion(user.getDireccion().toUpperCase());
		this.setTelefono2(user.getTelefono2().toUpperCase());
		this.setRepresentante(user.getRepresentante().toUpperCase());
		this.setLocalidad(user.getLocalidad().toUpperCase());
		this.setRazonSocial(user.getRazonSocial().toUpperCase());
		this.setNombreCompleto(user.getNombreCompleto().toUpperCase());
		this.setPassword(user.getPassword().toUpperCase());
		this.setCentroCosto(user.getCentroCosto());
		this.setMedio_Pago(user.getMedio_Pago());
		this.setPorcentajeComision(user.getPorcentajeComision());
		/*
		 * if(user.getCiudad() != null){ String [] separator; separator =
		 * user.getCiudad().split("-"); this.setCiudad(separator[0].toString());
		 * this.setCiudadd(separator[1].toString().toUpperCase()); }
		 */

		if (!user.getAgencia().trim().equals("-1")) {
			String[] separatorAgencia;
			separatorAgencia = user.getAgencia().split(",");

			this.setAgencia(separatorAgencia[0].toString());
			this.setAgenciaD(separatorAgencia[1].toString());
		}
		String[] separator;
		separator = user.getCiudad().split("-");
		this.setCiudad(separator[0].toString());
		this.setCiudadd(separator[1].toString().toUpperCase());

		return user;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void addAuthority(GrantedAuthority authority) {
		authorities.add(authority);
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgenciaD(String agenciaD) {
		this.agenciaD = agenciaD;
	}

	public String getAgenciaD() {
		return agenciaD;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresaD(String empresaD) {
		this.empresaD = empresaD;
	}

	public String getEmpresaD() {
		return empresaD;
	}

	@Override
	public String toString() {
		return "SpringSecurityUser [id=" + id + ", username=" + username + ", password=" + password
				+ ", nombreCompleto=" + nombreCompleto + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", nivel=" + nivel + ", ruc=" + ruc + ", razonSocial="
				+ razonSocial + ", razonPrueba=" + razonPrueba + ", correo=" + correo + ", telefono=" + telefono
				+ ", estado=" + estado + ", limiteCredito=" + limiteCredito + ", montoVentaActual=" + montoVentaActual
				+ ", montoVentaConfirmada=" + montoVentaConfirmada + ", codigoAgencia=" + codigoAgencia
				+ ", descripcionAgencia=" + descripcionAgencia + ", direccion=" + direccion + ", representante="
				+ representante + ", telefono2=" + telefono2 + ", localidad=" + localidad + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
				+ credentialsNonExpired + ", enabled=" + enabled + ", ocultarDetalleEstadoCuenta="
				+ ocultarDetalleEstadoCuenta + ", centroCosto=" + centroCosto + ", realizaVenta=" + realizaVenta
				+ ", porcentajeComision=" + porcentajeComision + ", medio_Pago=" + medio_Pago + ", ciudad=" + ciudad
				+ ", ciudadd=" + ciudadd + ", longitud=" + longitud + ", latitud=" + latitud + ", agencia=" + agencia
				+ ", agenciaD=" + agenciaD + ", empresa=" + empresa + ", empresaD=" + empresaD + ", authorities="
				+ authorities + "]";
	} 
	/*
	@Override
	public String toString() {
		return "SpringSecurityUser [id=" + id + ", username=" + username + ", password=" + password
				+ ", nombreCompleto=" + nombreCompleto + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", nivel=" + nivel + ", ruc=" + ruc + ", razonSocial="
				+ razonSocial + ", correo=" + correo + ", telefono=" + telefono + ", estado=" + estado
				+ ", limiteCredito=" + limiteCredito + ", montoVentaActual=" + montoVentaActual
				+ ", montoVentaConfirmada=" + montoVentaConfirmada + ", codigoAgencia=" + codigoAgencia
				+ ", descripcionAgencia=" + descripcionAgencia + ", direccion=" + direccion + ", representante="
				+ representante + ", telefono2=" + telefono2 + ", localidad=" + localidad + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
				+ credentialsNonExpired + ", enabled=" + enabled + ", ocultarDetalleEstadoCuenta="
				+ ocultarDetalleEstadoCuenta + ", centroCosto=" + centroCosto + ", realizaVenta=" + realizaVenta
				+ ", porcentajeComision=" + porcentajeComision + ", medio_Pago=" + medio_Pago + ", ciudad=" + ciudad
				+ ", ciudadd=" + ciudadd + ", longitud=" + longitud + ", latitud=" + latitud + ", agencia=" + agencia
				+ ", agenciaD=" + agenciaD + ", empresa=" + empresa + ", empresaD=" + empresaD + ", authorities="
				+ authorities + "]";
	}*/

}
