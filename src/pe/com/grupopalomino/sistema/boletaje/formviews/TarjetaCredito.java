package pe.com.grupopalomino.sistema.boletaje.formviews;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TarjetaCredito implements Serializable {
	
	private int cod_accion;
	
	public void setCod_accion(int cod_accion) {
		this.cod_accion = cod_accion;
	}
	
	public String getDescripcionError(){
		return decodificaNumeroError(cod_accion);
	}
	
	private String decodificaNumeroError(int cod_accion){
		
		String descripcionError;
		
		switch(cod_accion){
			case 101: return descripcionError = "Operaci�n denegada. Tarjeta vencida. Verifique los datos en su tarjeta e ingr�selos correctamente.";
			case 102: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 104: return descripcionError = "Operaci�n denegada. Operaci�n no permitida para esta tarjeta. Contactar con la entidad emisora de su tarjeta.";
			case 106: return descripcionError = "Operaci�n denegada. Intentos de clave secreta excedidos. Contactar con la entidad emisora de su tarjeta.";
			case 107: return descripcionError = "Operaci�n denegada. Contactar con la entidad emisora de su tarjeta.";
			case 108: return descripcionError = "Operaci�n denegada. Contactar con la entidad emisora de su tarjeta.";
			case 109: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 110: return descripcionError = "Operaci�n denegada. Operaci�n no permitida para esta tarjeta. Contactar con la entidad emisora de su tarjeta.";
			case 111: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 112: return descripcionError = "Operaci�n denegada. Se requiere clave secreta.";
			case 116: return descripcionError = "Operaci�n denegada. Fondos insuficientes. Contactar con entidad emisora de su tarjeta.";
			case 117: return descripcionError = "Operaci�n denegada. Clave secreta incorrecta.";
			case 118: return descripcionError = "Operaci�n denegada. Tarjeta inv�lida. Contactar con entidad emisora de su tarjeta.";
			case 119: return descripcionError = "Operaci�n denegada. Intentos de clave secreta excedidos. Contactar con la entidad emisora de su tarjeta.";
			case 121: return descripcionError = "Operaci�n denegada.";
			case 126: return descripcionError = "Operaci�n denegada. Clave secreta inv�lida.";
			case 129: return descripcionError = "Operaci�n denagada. C�digo de seguridad inv�lido. Contactar con entidad emisora de su tarjeta.";
			case 180: return descripcionError = "Operaci�n denegada. Tarjeta invalida. Contactar con entidad emisora de su tarjeta.";
			case 181: return descripcionError = "Operaci�n denegada. Tarjeta con restricciones de d�bito. Contactar con entidad emisora de su tarjeta.";
			case 182: return descripcionError = "Operaci�n denagada. Tarjeta con restricciones de cr�dito. Contactar con entidad emisora de su tarjeta.";
			case 183: return descripcionError = "Operaci�n denegada. Problemas de comunicaci�n. Intente m�s tarde.";
			case 190: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 191: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 192: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 199: return descripcionError = "Operaci�n denegada.";
			case 201: return descripcionError = "Operaci�n denegada. Tarjeta vencida. Contactar con entidad emisora de su tarjeta.";
			case 202: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 204: return descripcionError = "Operaci�n denegada. Operaci�n no permitida para esta tarjeta. Contactar con la entidad emisora de su tarjeta.";
			case 206: return descripcionError = "Operaci�n denegada. Intentos de clave secreta excedidos. Contactar con la entidad emisora de su tarjeta.";
			case 207: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 208: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 209: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 263: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 264: return descripcionError = "Operaci�n denegada. Entidad emisora de la tarjeta no est� disponible para realizar autentificaci�n.";
			case 265: return descripcionError = "Operaci�n denegada. Clave secreta del tarjetahabiente incorrecta. Contactar con entidad emisora de su tarjeta.";
			case 266: return descripcionError = "Operaci�n denegada. Tarjeta vencida. Contactar con entidad emisora de su tarjeta.";
			case 280: return descripcionError = "Operaci�n denegada. Clave secreta err�nea. Contactar con entidad emisora de su tarjeta.";
			case 290: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 300: return descripcionError = "Operaci�n denegada. N�mero de pedido de comercio duplicado. Favor no atender.";
			case 306: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 401: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 402: return descripcionError = "Operaci�n denegada.";
			case 403: return descripcionError = "Operaci�n denegada. Tarjeta no autenticada.";
			case 404: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 405: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 406: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 407: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 408: return descripcionError = "Operaci�n denegada. C�digo de seguridad no coincide. Contactar entidad emisora de su tarjeta.";
			case 409: return descripcionError = "Operaci�n denegada. C�digo de seguridad no procesado por la entidad emisora de la tarjeta.";
			case 410: return descripcionError = "Operaci�n denegada. C�digo de seguridad no ingresado.";
			case 411: return descripcionError = "Operaci�n denegada. C�digo de seguridad no procesado por la entidad emisora de la tarjeta.";
			case 412: return descripcionError = "Operaci�n denegada. C�digo de seguridad no reconocido por la entidad emisora de la tarjeta.";
			case 413: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 414: return descripcionError = "Operaci�n denegada.";
			case 415: return descripcionError = "Operaci�n denegada.";
			case 416: return descripcionError = "Operaci�n denegada.";
			case 417: return descripcionError = "Operaci�n denegada.";
			case 418: return descripcionError = "Operaci�n denegada.";
			case 419: return descripcionError = "Operaci�n denegada.";
			case 420: return descripcionError = "Operaci�n denegada. Tarjeta no es VISA.";
			case 421: return descripcionError = "Operaci�n denegada. Contactar con entidad emisora de su tarjeta.";
			case 422: return descripcionError = "Operaci�n denegada. El comercio no est� configurado para usar este medio de pago. Contactar con el comercio.";
			case 423: return descripcionError = "Operaci�n denegada. Se cancel� el proceso de pago.";
			case 424: return descripcionError = "Operaci�n denegada.";
			case 666: return descripcionError = "Operaci�n denegada. Problemas de comunicaci�n. Intente m�s tarde.";
			case 667: return descripcionError = "Operaci�n denegada. Transacci�n sin respuesta Verified by Visa.";
			case 668: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 669: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 670: return descripcionError = "Operaci�n denegada. Contactar con el comercio.";
			case 904: return descripcionError = "Operaci�n denegada.";
			case 909: return descripcionError = "Operaci�n denegada. Problemas de comunicaci�n. Intente m�s tarde.";
			case 910: return descripcionError = "Operaci�n denegada.";
			case 912: return descripcionError = "Operaci�n denegada. Entidad emisora de la tarjeta no disponible.";
			case 913: return descripcionError = "Operaci�n denegada.";
			case 916: return descripcionError = "Operaci�n denegada.";
			case 928: return descripcionError = "Operaci�n denegada.";
			case 940: return descripcionError = "Operaci�n denegada.";
			case 941: return descripcionError = "Operaci�n denegada.";
			case 942: return descripcionError = "Operaci�n denegada.";
			case 943: return descripcionError = "Operaci�n denegada.";
			case 945: return descripcionError = "Operaci�n denegada.";
			case 946: return descripcionError = "Operaci�n denegada. Operaci�n de anulaci�n en proceso.";
			case 947: return descripcionError = "Operaci�n denegada. Problemas de comunicaci�n. Intente m�s tarde.";
			case 948: return descripcionError = "Operaci�n denegada.";
			case 949: return descripcionError = "Operaci�n denegada.";
			case 965: return descripcionError = "Operaci�n denegada.";
			default : return descripcionError = "Error interno del servidor.";
		}
		
	}
	
}
