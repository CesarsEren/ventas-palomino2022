package pe.com.grupopalomino.sistema.boletaje.util;

import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasWebBean;

public class ValidaDatosAgenciaWeb {

	private V_AgenciasWebBean agenciaWeb;
	
	public ValidaDatosAgenciaWeb(V_AgenciasWebBean agenciaWeb) {
		this.agenciaWeb = agenciaWeb;
	}
	
	public ErrorValidacion esAgenciaWebValida(){
		
		ErrorValidacion errorValidacion = new ErrorValidacion();
		errorValidacion.setError(false);
		
		if(agenciaWeb.getLimiteCredito() == 0 || String.valueOf(agenciaWeb.getLimiteCredito()).trim().isEmpty()){
			errorValidacion.addMensaje("El limite de credito no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!  String.valueOf(agenciaWeb.getLimiteCredito()).trim().matches(Regex.CREDITO)){
			errorValidacion.addMensaje("El limite de credito no tiene el formato correcto (Ej.: 12.24).");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if((agenciaWeb.getLimiteCredito()) < 10){
			errorValidacion.addMensaje("El limite de credito debe ser mayor a 10");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(agenciaWeb.getRazonSocial() == null || agenciaWeb.getRazonSocial().trim().isEmpty()){
			errorValidacion.addMensaje("La razon social no puede estar vacia.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(agenciaWeb.getRazonSocial().length() > 150){
			errorValidacion.addMensaje("La razon social no puede ser mayor a 150 caracteres.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(agenciaWeb.getRuc() == null || agenciaWeb.getRuc().trim().isEmpty()){
			errorValidacion.addMensaje("El ruc no puede estar vacío.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!agenciaWeb.getRuc().trim().matches(Regex.NUMERO)){
			errorValidacion.addMensaje("El ruc solo puede tener digitos.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(agenciaWeb.getRuc().trim().length() != 11){
			errorValidacion.addMensaje("El ruc debe tener 11 digitos.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}

		if(agenciaWeb.getCorreo() == null || agenciaWeb.getCorreo().trim().isEmpty()){
			errorValidacion.addMensaje("El correo no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!agenciaWeb.getCorreo().trim().matches(Regex.EMAIL)){
			errorValidacion.addMensaje("El correo no tiene un formato correcto.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(agenciaWeb.getCorreo().length() > 200){
			errorValidacion.addMensaje("El correo no puede tener mas de 200 caracteres.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(agenciaWeb.getTelefono() == null || agenciaWeb.getTelefono().trim().isEmpty()){
			errorValidacion.addMensaje("El telefono no puede estar vacio.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		if(!agenciaWeb.getTelefono().trim().matches(Regex.TELEFONO)){
			errorValidacion.addMensaje("El telefono no tiene el formato correcto.");
			errorValidacion.setError(true);
			
			return errorValidacion;
		}
		
		return errorValidacion;
	}
	
}
