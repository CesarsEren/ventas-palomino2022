package pe.com.grupopalomino.sistema.boletaje.util;

import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso3Form;

public class ValidaDatosPasajero {
	private VentaPaso3Form ventaPaso3;
	
	
	public ValidaDatosPasajero(VentaPaso3Form ventaPaso3){
		this.ventaPaso3 = ventaPaso3;
		
	}
	
	public ErrorValidacion esPasajeroValido(){
		
		ErrorValidacion errorValidacion = new ErrorValidacion();
		errorValidacion.setError(false);
		
		if(ventaPaso3.getNumeroAsiento() == null || ventaPaso3.getNumeroAsiento().trim().isEmpty()){
			errorValidacion.addMensaje("El asiento no puede estar vacio");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(ventaPaso3.getPrecio()== null || ventaPaso3.getPrecio().trim().isEmpty()){
			errorValidacion.addMensaje("El Precio no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
			
		}
		if(ventaPaso3.getComboIdentidad().trim().equals("-1")|| ventaPaso3.getComboIdentidad().trim().isEmpty()){
			errorValidacion.addMensaje("Debe seleccionar un Tipo de documento."); 
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(ventaPaso3.getNumeroDocumentoIdentidad().trim().isEmpty()){
			errorValidacion.addMensaje("El número de documento de identidad no puede estar vacío.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(!ventaPaso3.getComboIdentidad().trim().equals("D-D.N.I.") &&
				!ventaPaso3.getComboIdentidad().trim().equals("P-Pasaporte") &&
				!ventaPaso3.getComboIdentidad().trim().equals("M-Menor Edad")){
			errorValidacion.addMensaje("El tipo de identidad ingresado no es el correcto.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(ventaPaso3.getComboIdentidad().trim().equals("D-D.N.I.")){
			if(ventaPaso3.getNumeroDocumentoIdentidad().trim().length() != 8){
				errorValidacion.addMensaje("El número de DNI debe tener 8 digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			else if(!ventaPaso3.getNumeroDocumentoIdentidad().trim().matches(Regex.NUMERO)){
				errorValidacion.addMensaje("El número de DNI solo debe tener digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
		}
		if(ventaPaso3.getComboIdentidad().trim().equals("P-PASAPORTE")){
			if(ventaPaso3.getNumeroDocumentoIdentidad().trim().length() != 12){
				errorValidacion.addMensaje("El número de pasaporte debe tener 12 digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			else if(!ventaPaso3.getNumeroDocumentoIdentidad().trim().matches(Regex.NUMERO)){
				errorValidacion.addMensaje("El número de pasaporte solo debe tener digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
		}
		if(ventaPaso3.getComboIdentidad().trim().equals("M-Menor Edad")){
			if(ventaPaso3.getNumeroDocumentoIdentidad().trim().length() < 8 || ventaPaso3.getNumeroDocumentoIdentidad().trim().length() > 12){
				errorValidacion.addMensaje("El documento para menor de edad debe tener entre 8 a 12 digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
		}
		if(ventaPaso3.getNombrePasajero() == null || ventaPaso3.getNombrePasajero().trim().isEmpty()){
			errorValidacion.addMensaje("El nombre del pasajero no puede estar vacío.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(ventaPaso3.getNombrePasajero().trim().length() > 50){
			errorValidacion.addMensaje("El nombre del pasajero no puede ser mayor a 50 caracteres.");
			errorValidacion.setError(true);
		}/*
		if(!ventaPaso3.getNombrePasajero().trim().matches(Regex.NOMBRES)){
			errorValidacion.addMensaje("El nombre no tiene el formato correcto.");
			errorValidacion.setError(true);
			return errorValidacion;
		}*/
		if(ventaPaso3.getEdad() == null || ventaPaso3.getEdad().trim().isEmpty()){
			errorValidacion.addMensaje("La edad no puede estar vacío");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(!ventaPaso3.getEdad().trim().matches(Regex.EDAD)){
			errorValidacion.addMensaje("La edad solo puede tener 2 digitos.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(Integer.parseInt(ventaPaso3.getEdad().trim()) < 18 && !ventaPaso3.getComboIdentidad().trim().equals("M-Menor Edad")){
			errorValidacion.addMensaje("Por favor seleccione como tipo de documento Menor Edad si el pasajero es menor de 18 años.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(ventaPaso3.getComboIdentidad().trim().equals("M-Menor Edad")){
			if(Integer.parseInt(ventaPaso3.getEdad().trim()) >= 18){
				errorValidacion.addMensaje("La edad para un pasajero menor de edad debe estar entre 0 a 18 años.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
		}
		
		if(!ventaPaso3.getTelefono().trim().matches(Regex.TELEFONO)){
			errorValidacion.addMensaje("El teléfono no tiene el formato correcto.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(ventaPaso3.getTelefono().trim().isEmpty()){
			errorValidacion.addMensaje("El teléfono no puede estar vacío.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		else{
			if(ventaPaso3.getTelefono().trim().length() > 15){
				errorValidacion.addMensaje("El número de telefono debe tener menos de 15 digitos.");
				errorValidacion.setError(true);
			}
		}
		if(ventaPaso3.getRuc() != null && !ventaPaso3.getRuc().isEmpty()){
			if(!(ventaPaso3.getRuc().trim().matches(Regex.NUMERO))){
				errorValidacion.addMensaje("El número de ruc solo puede tener números ");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			if(ventaPaso3.getRuc().trim().length() != 11){
				errorValidacion.addMensaje("El número de ruc debe tener 11 digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
		}
		if(ventaPaso3.getRazonSocial() != null && !ventaPaso3.getRazonSocial().trim().isEmpty()){
			if(ventaPaso3.getRazonSocial().length() > 50){
				errorValidacion.addMensaje("La razón social no puede ser mayor a 50 caracteres.");
				errorValidacion.setError(true);
			}
		}
		if(ventaPaso3.getComboEmbarque()== null  || ventaPaso3.getComboEmbarque().trim().isEmpty() || ventaPaso3.getComboEmbarque().trim().equalsIgnoreCase("-1")){
			errorValidacion.addMensaje("Debe seleccionar un Embarque");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(ventaPaso3.getComboDestinoBajada()== null || ventaPaso3.getComboDestinoBajada().trim().isEmpty()){
			errorValidacion.addMensaje("Debe seleccionar un Destino");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		return errorValidacion;
	}

}
