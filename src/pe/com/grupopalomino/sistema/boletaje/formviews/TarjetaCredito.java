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
			case 101: return descripcionError = "Operación denegada. Tarjeta vencida. Verifique los datos en su tarjeta e ingréselos correctamente.";
			case 102: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 104: return descripcionError = "Operación denegada. Operación no permitida para esta tarjeta. Contactar con la entidad emisora de su tarjeta.";
			case 106: return descripcionError = "Operación denegada. Intentos de clave secreta excedidos. Contactar con la entidad emisora de su tarjeta.";
			case 107: return descripcionError = "Operación denegada. Contactar con la entidad emisora de su tarjeta.";
			case 108: return descripcionError = "Operación denegada. Contactar con la entidad emisora de su tarjeta.";
			case 109: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 110: return descripcionError = "Operación denegada. Operación no permitida para esta tarjeta. Contactar con la entidad emisora de su tarjeta.";
			case 111: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 112: return descripcionError = "Operación denegada. Se requiere clave secreta.";
			case 116: return descripcionError = "Operación denegada. Fondos insuficientes. Contactar con entidad emisora de su tarjeta.";
			case 117: return descripcionError = "Operación denegada. Clave secreta incorrecta.";
			case 118: return descripcionError = "Operación denegada. Tarjeta inválida. Contactar con entidad emisora de su tarjeta.";
			case 119: return descripcionError = "Operación denegada. Intentos de clave secreta excedidos. Contactar con la entidad emisora de su tarjeta.";
			case 121: return descripcionError = "Operación denegada.";
			case 126: return descripcionError = "Operación denegada. Clave secreta inválida.";
			case 129: return descripcionError = "Operación denagada. Código de seguridad inválido. Contactar con entidad emisora de su tarjeta.";
			case 180: return descripcionError = "Operación denegada. Tarjeta invalida. Contactar con entidad emisora de su tarjeta.";
			case 181: return descripcionError = "Operación denegada. Tarjeta con restricciones de débito. Contactar con entidad emisora de su tarjeta.";
			case 182: return descripcionError = "Operación denagada. Tarjeta con restricciones de crédito. Contactar con entidad emisora de su tarjeta.";
			case 183: return descripcionError = "Operación denegada. Problemas de comunicación. Intente más tarde.";
			case 190: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 191: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 192: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 199: return descripcionError = "Operación denegada.";
			case 201: return descripcionError = "Operación denegada. Tarjeta vencida. Contactar con entidad emisora de su tarjeta.";
			case 202: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 204: return descripcionError = "Operación denegada. Operación no permitida para esta tarjeta. Contactar con la entidad emisora de su tarjeta.";
			case 206: return descripcionError = "Operación denegada. Intentos de clave secreta excedidos. Contactar con la entidad emisora de su tarjeta.";
			case 207: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 208: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 209: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 263: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 264: return descripcionError = "Operación denegada. Entidad emisora de la tarjeta no está disponible para realizar autentificación.";
			case 265: return descripcionError = "Operación denegada. Clave secreta del tarjetahabiente incorrecta. Contactar con entidad emisora de su tarjeta.";
			case 266: return descripcionError = "Operación denegada. Tarjeta vencida. Contactar con entidad emisora de su tarjeta.";
			case 280: return descripcionError = "Operación denegada. Clave secreta errónea. Contactar con entidad emisora de su tarjeta.";
			case 290: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 300: return descripcionError = "Operación denegada. Número de pedido de comercio duplicado. Favor no atender.";
			case 306: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 401: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 402: return descripcionError = "Operación denegada.";
			case 403: return descripcionError = "Operación denegada. Tarjeta no autenticada.";
			case 404: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 405: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 406: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 407: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 408: return descripcionError = "Operación denegada. Código de seguridad no coincide. Contactar entidad emisora de su tarjeta.";
			case 409: return descripcionError = "Operación denegada. Código de seguridad no procesado por la entidad emisora de la tarjeta.";
			case 410: return descripcionError = "Operación denegada. Código de seguridad no ingresado.";
			case 411: return descripcionError = "Operación denegada. Código de seguridad no procesado por la entidad emisora de la tarjeta.";
			case 412: return descripcionError = "Operación denegada. Código de seguridad no reconocido por la entidad emisora de la tarjeta.";
			case 413: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 414: return descripcionError = "Operación denegada.";
			case 415: return descripcionError = "Operación denegada.";
			case 416: return descripcionError = "Operación denegada.";
			case 417: return descripcionError = "Operación denegada.";
			case 418: return descripcionError = "Operación denegada.";
			case 419: return descripcionError = "Operación denegada.";
			case 420: return descripcionError = "Operación denegada. Tarjeta no es VISA.";
			case 421: return descripcionError = "Operación denegada. Contactar con entidad emisora de su tarjeta.";
			case 422: return descripcionError = "Operación denegada. El comercio no está configurado para usar este medio de pago. Contactar con el comercio.";
			case 423: return descripcionError = "Operación denegada. Se canceló el proceso de pago.";
			case 424: return descripcionError = "Operación denegada.";
			case 666: return descripcionError = "Operación denegada. Problemas de comunicación. Intente más tarde.";
			case 667: return descripcionError = "Operación denegada. Transacción sin respuesta Verified by Visa.";
			case 668: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 669: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 670: return descripcionError = "Operación denegada. Contactar con el comercio.";
			case 904: return descripcionError = "Operación denegada.";
			case 909: return descripcionError = "Operación denegada. Problemas de comunicación. Intente más tarde.";
			case 910: return descripcionError = "Operación denegada.";
			case 912: return descripcionError = "Operación denegada. Entidad emisora de la tarjeta no disponible.";
			case 913: return descripcionError = "Operación denegada.";
			case 916: return descripcionError = "Operación denegada.";
			case 928: return descripcionError = "Operación denegada.";
			case 940: return descripcionError = "Operación denegada.";
			case 941: return descripcionError = "Operación denegada.";
			case 942: return descripcionError = "Operación denegada.";
			case 943: return descripcionError = "Operación denegada.";
			case 945: return descripcionError = "Operación denegada.";
			case 946: return descripcionError = "Operación denegada. Operación de anulación en proceso.";
			case 947: return descripcionError = "Operación denegada. Problemas de comunicación. Intente más tarde.";
			case 948: return descripcionError = "Operación denegada.";
			case 949: return descripcionError = "Operación denegada.";
			case 965: return descripcionError = "Operación denegada.";
			default : return descripcionError = "Error interno del servidor.";
		}
		
	}
	
}
