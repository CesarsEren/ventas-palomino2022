package pe.com.grupopalomino.sistema.boletaje.util;

import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;

public class ValidaDatosCliente {

	private SpringSecurityClient cliente;
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	
	public ValidaDatosCliente(SpringSecurityClient cliente) {
		this.cliente = cliente;
	}
	
	public ErrorValidacion esClienteValido(){
		
		ErrorValidacion errorValidacion = new ErrorValidacion();
		errorValidacion.setError(false);
		
		try {
			if(cliente.getNombres() == null || cliente.getNombres().trim().isEmpty()){
				errorValidacion.addMensaje("Los nombres son obligatorios.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(cliente.getApellidos() == null || cliente.getApellidos().trim().isEmpty()){
				errorValidacion.addMensaje("Los apellidos son obligatorios.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()){
				errorValidacion.addMensaje("El telefono es obligatorio.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(cliente.getIdentidad() == null || cliente.getIdentidad().trim().isEmpty()){
				errorValidacion.addMensaje("La identidad es obligatotia.");
				errorValidacion.setError(true);
				return errorValidacion;  
			}
			if(!cliente.getIdentidad().trim().matches(Regex.NUMEROS)){ 
				errorValidacion.addMensaje("La identidad no es valida.");
				errorValidacion.setError(true);
				return errorValidacion;
			}else if(Integer.parseInt(cliente.getIdentidad())<0){
				errorValidacion.addMensaje("La identidad no es valida.");
				errorValidacion.setError(true);
				return errorValidacion;
				
			}else if (Integer.parseInt(cliente.getIdentidad())>2){
				errorValidacion.addMensaje("La identidad no es valida.");
				errorValidacion.setError(true);
				return errorValidacion;
				
			}
			if(cliente.getNumerodocumento() == null || cliente.getNumerodocumento().trim().isEmpty()){
				errorValidacion.addMensaje("El numero de documento es obligatiorio.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()){
				errorValidacion.addMensaje("El email es obligatorio.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(!cliente.getEmail().trim().matches(Regex.EMAIL)){
				errorValidacion.addMensaje("El email ingresado no tiene el formato correcto. (Ej. abc@mail.com)");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(!cliente.getEmail().equals(cliente.getConfirm_email())){
				errorValidacion.addMensaje("Los emails ingresados no coinciden.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(cliente.getPassword() == null || cliente.getPassword().trim().isEmpty()){
				errorValidacion.addMensaje("La clave es obligatorio.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(cliente.getPassword().trim().length() < 8){
				errorValidacion.addMensaje("La clave debe tener al menos 8 caracteres.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(!cliente.getPassword().equals(cliente.getConfirm_password())){
				errorValidacion.addMensaje("Las claves ingresadas no coinciden.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(usuarioService.obtieneClienteSMS(cliente.getEmail()) != null){
				errorValidacion.addMensaje("ya fue registrado este mail,ingrese otro por favor.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return errorValidacion;
	}
	
}
