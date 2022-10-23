package pe.com.grupopalomino.sistema.boletaje.util;

import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBean;


public class ValidaDatosReclamos {
	
	private B_ReclamosBean reclamos;
	
	public ValidaDatosReclamos(B_ReclamosBean reclamos){
		this.reclamos = reclamos;
		
	}
	
	public ErrorValidacion ValidaReclamos(){
		
		ErrorValidacion errorValidacion = new ErrorValidacion();
		errorValidacion.setError(false);
		
		
		if(reclamos.getEmpresa()== null || reclamos.getEmpresa().trim().isEmpty()){
			errorValidacion.addMensaje("Por favor seleccione una empresa.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getIdentidad()== null || reclamos.getIdentidad().trim().isEmpty()){
			errorValidacion.addMensaje("Por favor seleccione un Documento de Identidad.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getIdentidad().trim().equals("D")){
			if(reclamos.getDNI().trim().length() != 8){
				errorValidacion.addMensaje("El número de DNI debe tener 8 digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			else if(!reclamos.getDNI().trim().matches(Regex.NUMERO)){
				errorValidacion.addMensaje("El número de DNI solo debe tener digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			
		}
		
		if(reclamos.getIdentidad().trim().equals("M")){
			if(reclamos.getDNI().trim().length() < 8 || reclamos.getDNI().trim().length()> 12){
				errorValidacion.addMensaje("El documento para menor de edad debe tener entre 8 a 12 digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			else if(!reclamos.getDNI().trim().matches(Regex.NUMERO)){
				errorValidacion.addMensaje("El número de DNI solo debe tener digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			
		}
		
		if(reclamos.getIdentidad().trim().equals("P")){
			if(reclamos.getDNI().trim().length() != 12){
				errorValidacion.addMensaje("El número de pasaporte debe tener 12 digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			else if(!reclamos.getDNI().trim().matches(Regex.NUMERO)){
				errorValidacion.addMensaje("El número de DNI solo debe tener digitos.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			
		}
		
		
		if(reclamos.getDNI()== null || reclamos.getDNI().trim().isEmpty()){
			errorValidacion.addMensaje("El numero de documento de identidad no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getNombres()== null || reclamos.getNombres().trim().isEmpty()){
			errorValidacion.addMensaje("El Nombre no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(!reclamos.getNombres().trim().matches(Regex.NOMBRES)){
			errorValidacion.addMensaje("El Nombre no tiene el formato correcto.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		
		if(reclamos.getApePaterno()== null || reclamos.getApePaterno().trim().isEmpty()){
			errorValidacion.addMensaje("El Apellido Paterno no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(!reclamos.getApePaterno().trim().matches(Regex.NOMBRES)){
			errorValidacion.addMensaje("El Apellido Paterno no tiene el formato correcto.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		
		if(reclamos.getApeMaterno()== null || reclamos.getApeMaterno().trim().isEmpty()){
			errorValidacion.addMensaje("El Apellido Materno no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(!reclamos.getApeMaterno().trim().matches(Regex.NOMBRES)){
			errorValidacion.addMensaje("El Apellido Materno no tiene el formato correcto.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getTelefono()== null || reclamos.getTelefono().trim().isEmpty()){
			errorValidacion.addMensaje("El numero de Teléfono no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		else if(!reclamos.getTelefono().trim().matches(Regex.TELEFONO)){
			errorValidacion.addMensaje("El número de Teléfono solo debe tener digitos.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getEmail()== null || reclamos.getEmail().trim().isEmpty()){
			errorValidacion.addMensaje("El email  no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(!reclamos.getEmail().trim().matches(Regex.EMAIL)){
			errorValidacion.addMensaje("El email ingresado no tiene el formato correcto. (Ej. abc@mail.com)");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		
		if(reclamos.getDomicilio()== null || reclamos.getDomicilio().trim().isEmpty()){
			errorValidacion.addMensaje("El domicilio  no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getIdentidad().trim().equals("M")){ 
			if(reclamos.getPadreMenor()== null || reclamos.getPadreMenor().trim().isEmpty()){
				errorValidacion.addMensaje("El nombre del Padre/Madre del menor no puede estar vacio.");
				errorValidacion.setError(true);
				return errorValidacion;
			}
			
		}
		
		if(reclamos.getFechaIncidente() == null || reclamos.getFechaIncidente().trim().isEmpty()){
			errorValidacion.addMensaje("La fecha del incidente no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getServicio() == null || reclamos.getServicio().trim().isEmpty()){
			errorValidacion.addMensaje("Por favor seleccione servicio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getMotivoReclamo()== null || reclamos.getMotivoReclamo().trim().isEmpty()){
			errorValidacion.addMensaje("Por favor seleccione un motivo de reclamo.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getDocumento()== null || reclamos.getDocumento().trim().isEmpty()){
			errorValidacion.addMensaje("Por favor seleccione un Documento.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(reclamos.getSerie()== null || reclamos.getSerie().trim().isEmpty()){
			errorValidacion.addMensaje("La serie del documento no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(reclamos.getNumero()== null || reclamos.getNumero().trim().isEmpty()){
			errorValidacion.addMensaje("El número del documento no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(reclamos.getTipoReclamo()== null || reclamos.getTipoReclamo().trim().isEmpty()){
			errorValidacion.addMensaje("Por favor seleccione un tipo de reclamo.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		
		if(reclamos.getDetalle()== null || reclamos.getDetalle().trim().isEmpty()){
			errorValidacion.addMensaje("El detalle no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		if(reclamos.getPedido()== null || reclamos.getPedido().trim().isEmpty()){
			errorValidacion.addMensaje("El pedido no puede estar vacio.");
			errorValidacion.setError(true);
			return errorValidacion;
		}
		return errorValidacion;
	}

}
