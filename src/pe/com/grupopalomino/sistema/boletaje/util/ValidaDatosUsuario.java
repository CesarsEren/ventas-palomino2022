package pe.com.grupopalomino.sistema.boletaje.util;

import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;

public class ValidaDatosUsuario {

	private SpringSecurityUser usuario;
	
	public ValidaDatosUsuario(SpringSecurityUser usuario) {
		this.usuario = usuario;
	}
	
public ErrorValidacion esUsuarioTransporteDePersonalValido(){
		
		ErrorValidacion errorValidacion = new ErrorValidacion();
		errorValidacion.setError(false);
		
		
		
		if(usuario.getNombres() == null || usuario.getNombres().trim().isEmpty()){
			errorValidacion.addMensaje("Los nombres no pueden estar vacios.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		
		if(usuario.getNivel().trim().equals("C")){
			if(!usuario.getNombres().trim().matches(Regex.RAZONSOCIAL)){
				errorValidacion.addMensaje("Los nombres no tienen el formato correcto.");
				errorValidacion.setError(true);
				
				return errorValidacion;
			}
			
		}else{
			if(!usuario.getNombres().trim().matches(Regex.NOMBRES)){
				errorValidacion.addMensaje("Los nombres no tienen el formato correcto.");
				errorValidacion.setError(true);
				
				return errorValidacion;
			}
		}
		
		if(usuario.getNombres().length() > 60){
			errorValidacion.addMensaje("Los nombres no pueden superar los 60 caracteres.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
	/*	if(usuario.getApellidoPaterno() == null || usuario.getApellidoPaterno().trim().isEmpty()){
			errorValidacion.addMensaje("El apellido paterno no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!usuario.getApellidoPaterno().trim().matches(Regex.NOMBRES)){
			errorValidacion.addMensaje("El apellido paterno no tiene el formato correcto.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}*/
		if(usuario.getApellidoPaterno().length() > 60){
			errorValidacion.addMensaje("El apellido paterno no puede superar los 60 caracteres.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
	/*	if(usuario.getApellidoMaterno() == null || usuario.getApellidoMaterno().trim().isEmpty()){
			errorValidacion.addMensaje("El apellido materno no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!usuario.getApellidoMaterno().trim().matches(Regex.NOMBRES)){
			errorValidacion.addMensaje("El apellido materno no tiene el formato correcto.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}*/
		if(usuario.getApellidoMaterno().length() > 60){
			errorValidacion.addMensaje("El apellido materno no puede superar los 60 caracteres.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(usuario.getRuc()==null || usuario.getRuc().trim().isEmpty()){
			errorValidacion.addMensaje("El numero de ruc no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!usuario.getRuc().trim().matches(Regex.NUMERO)){ 
			errorValidacion.addMensaje("El numero de ruc solo puede contener numeros.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(usuario.getRuc().length()>11){
			errorValidacion.addMensaje("El numero de ruc no puede superar los 11 digitos.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		
		/*if(usuario.getRazonSocial()==null || usuario.getRazonSocial().trim().isEmpty()){
			errorValidacion.addMensaje("La razon social no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}*/
		if(usuario.getTelefono()==null || usuario.getTelefono().trim().isEmpty()){
			errorValidacion.addMensaje("El telefono no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(!usuario.getTelefono().trim().matches(Regex.TELEFONO)){
			errorValidacion.addMensaje("El telefono solo puede contener numeros.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(usuario.getCorreo()==null || usuario.getCorreo().trim().isEmpty()){
			errorValidacion.addMensaje("El correo no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		
		if(!usuario.getCorreo().trim().matches(Regex.EMAIL)){
			errorValidacion.addMensaje("El correo no tiene el formato correcto.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(usuario.getNivel()== null || usuario.getNivel().trim().isEmpty()){
			errorValidacion.addMensaje("Debe seleccionar un Nivel del usuario.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(usuario.getEstado()==null || usuario.getEstado().trim().isEmpty()){
			errorValidacion.addMensaje("Debe seleccionar un Estado del usuario.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		} 
		
		if(usuario.getDireccion()==null  || usuario.getDireccion().trim().isEmpty()){
			errorValidacion.addMensaje("La direccion no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(usuario.getPassword()== null || usuario.getPassword().trim().isEmpty()){
			errorValidacion.addMensaje("El password no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		
		
		
		return errorValidacion;
	}

	public ErrorValidacion esUsuarioValido(){
		
		ErrorValidacion errorValidacion = new ErrorValidacion();
		errorValidacion.setError(false);
		
		
		
		if(usuario.getNombres() == null || usuario.getNombres().trim().isEmpty()){
			errorValidacion.addMensaje("Los nombres no pueden estar vacios.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		
		if(usuario.getNivel().trim().equals("C")){
			if(!usuario.getNombres().trim().matches(Regex.RAZONSOCIAL)){
				errorValidacion.addMensaje("Los nombres no tienen el formato correcto.");
				errorValidacion.setError(true);
				
				return errorValidacion;
			}
			
		}else{
			if(!usuario.getNombres().trim().matches(Regex.NOMBRES)){
				errorValidacion.addMensaje("Los nombres no tienen el formato correcto.");
				errorValidacion.setError(true);
				
				return errorValidacion;
			}
		}
		
		if(usuario.getNombres().length() > 60){
			errorValidacion.addMensaje("Los nombres no pueden superar los 60 caracteres.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
	/*	if(usuario.getApellidoPaterno() == null || usuario.getApellidoPaterno().trim().isEmpty()){
			errorValidacion.addMensaje("El apellido paterno no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!usuario.getApellidoPaterno().trim().matches(Regex.NOMBRES)){
			errorValidacion.addMensaje("El apellido paterno no tiene el formato correcto.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}*/
		if(usuario.getApellidoPaterno().length() > 60){
			errorValidacion.addMensaje("El apellido paterno no puede superar los 60 caracteres.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
	/*	if(usuario.getApellidoMaterno() == null || usuario.getApellidoMaterno().trim().isEmpty()){
			errorValidacion.addMensaje("El apellido materno no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!usuario.getApellidoMaterno().trim().matches(Regex.NOMBRES)){
			errorValidacion.addMensaje("El apellido materno no tiene el formato correcto.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}*/
		if(usuario.getApellidoMaterno().length() > 60){
			errorValidacion.addMensaje("El apellido materno no puede superar los 60 caracteres.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(usuario.getRuc()==null || usuario.getRuc().trim().isEmpty()){
			errorValidacion.addMensaje("El numero de ruc no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!usuario.getRuc().trim().matches(Regex.NUMERO)){ 
			errorValidacion.addMensaje("El numero de ruc solo puede contener numeros.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(usuario.getRuc().length()>11){
			errorValidacion.addMensaje("El numero de ruc no puede superar los 11 digitos.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		
		/*if(usuario.getRazonSocial()==null || usuario.getRazonSocial().trim().isEmpty()){
			errorValidacion.addMensaje("La razon social no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}*/
		if(usuario.getTelefono()==null || usuario.getTelefono().trim().isEmpty()){
			errorValidacion.addMensaje("El telefono no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(!usuario.getTelefono().trim().matches(Regex.TELEFONO)){
			errorValidacion.addMensaje("El telefono solo puede contener numeros.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(usuario.getCorreo()==null || usuario.getCorreo().trim().isEmpty()){
			errorValidacion.addMensaje("El correo no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		
		if(!usuario.getCorreo().trim().matches(Regex.EMAIL)){
			errorValidacion.addMensaje("El correo no tiene el formato correcto.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(usuario.getNivel()== null || usuario.getNivel().trim().isEmpty()){
			errorValidacion.addMensaje("Debe seleccionar un Nivel del usuario.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(usuario.getEstado()==null || usuario.getEstado().trim().isEmpty()){
			errorValidacion.addMensaje("Debe seleccionar un Estado del usuario.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!(usuario.getNivel().trim().equals("C"))){
			if(usuario.getLimiteCredito()<= 0){
				errorValidacion.addMensaje("El limite de credito debe ser mayor a cero.");
				errorValidacion.setError(true);
				
				return errorValidacion;
			}
		}
		
		if(usuario.getDireccion()==null  || usuario.getDireccion().trim().isEmpty()){
			errorValidacion.addMensaje("La direccion no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
			
		}
		if(usuario.getPassword()== null || usuario.getPassword().trim().isEmpty()){
			errorValidacion.addMensaje("El password no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		
		
		
		return errorValidacion;
	}
	
}
