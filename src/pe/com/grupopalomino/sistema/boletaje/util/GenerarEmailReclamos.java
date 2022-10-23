package pe.com.grupopalomino.sistema.boletaje.util;

import java.io.ByteArrayOutputStream; 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBean;

public class GenerarEmailReclamos {
	
	private final static String FROMRECLAMOS = "reclamos@grupopalomino.com.pe";
	private final static String USERNAMERECLAMOS = "reclamos@grupopalomino.com.pe";
	private final static String PASSWORDRECLAMOS = "gpalomino.12.3";
	private static final Log log = LogFactory.getLog(GenerarEmailReclamos.class);
		
	public static void enviarCorreoAdjuntoReclamos(Map<String, String> parametros, ByteArrayOutputStream stream) throws IOException{
		
		

		try {

			Message message = new MimeMessage(SessionMail.getSessionEmail());
			message.setFrom(new InternetAddress(FROMRECLAMOS));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(parametros.get("to")));
			
			if(parametros.get("cc")!=null){
				
				message.addRecipients(Message.RecipientType.CC,InternetAddress.parse(parametros.get("cc")));
				message.addRecipients(Message.RecipientType.BCC,InternetAddress.parse("earce@grupopalomino.com.pe,jefaturadecarga@grupopalomino.com.pe"));
				
			}
			
			message.setSubject(parametros.get("subject"));

			// El correo tiene 2 partes, la imagen a enviar, y el texto
			MimeMultipart multiparte = new MimeMultipart("related");

			// Primera parte, el html
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<table border=0 align='left'>"
								+"<tbody>"
									+"<tr>"
										+"<td>"
											+"<h4 style='font-size:14px;'>"+parametros.get("header")+"</h4>" 
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td style='text-align:right;'>"
											+parametros.get("dateheader")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("dataclient")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("nameclient")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											 +" - "+parametros.get("dirclient")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"Presente.- <br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"De nuestra consideración,<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"Por intermedio del presente le informamos que hemos recibido su "+parametros.get("textoreclamo")+",le agradecermos" 
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"realizar la verificación de los datos consignados:<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("nroreclamo")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("fechahora")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("emailclient")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("telfclient")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("tiporeclamo")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("detallebody")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("pedidoreclamo")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("pedidobody")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+ "<tr><td></td></tr>"
									+"<tr>"
										+"<td>"
											+"De acuerdo a las normas vigentes para este tipo de trámites, le informamos que en un plazo"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"máximo de 30 días calendarios estaremos dando respuesta.<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"Si tuviera alguna duda o consulta sobre el particular, agradeceremos comunicarse al correo"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"electrónico reclamos@grupopalomino.com.pe o al teléfono 620-2333 <br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("responsable")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("empresa") 
										+ "</td>"
									+ "</tr>"
								+ "</tbody>"
							+ "</table>";
			messageBodyPart.setContent(htmlText, "text/html");
			multiparte.addBodyPart(messageBodyPart);

			if(stream != null){
				//Se reusa el messagebodypart
				messageBodyPart = new MimeBodyPart();
				
				DataSource archivo = new ByteArrayDataSource(stream.toByteArray(), "application/pdf");
				messageBodyPart.setDataHandler(new DataHandler(archivo));
				messageBodyPart.setFileName("Reclamo-N°-"+parametros.get("idreclamo")+".pdf");
			}
			
			multiparte.addBodyPart(messageBodyPart);
			
			message.setContent(multiparte);

			Transport.send(message,USERNAMERECLAMOS,PASSWORDRECLAMOS);
			
			messageBodyPart = new MimeBodyPart();
		
		} catch (MessagingException e) {
			log.info(Utils.printStackTraceToString(e));
			
		}
		
	}
	
	public static void enviarCorreoAtencionReclamos(Map<String, String> parametros, ByteArrayOutputStream stream) throws IOException{
		

		try {

			Message message = new MimeMessage(SessionMail.getSessionEmail());
			message.setFrom(new InternetAddress(FROMRECLAMOS));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(parametros.get("to")));
			
			if(parametros.get("cc")!=null){
			
				message.addRecipients(Message.RecipientType.CC,InternetAddress.parse(parametros.get("cc")));
				message.addRecipients(Message.RecipientType.BCC,InternetAddress.parse("earce@grupopalomino.com.pe,jefaturadecarga@grupopalomino.com.pe"));
				
			}
			message.setSubject(parametros.get("subject"));

			// El correo tiene 2 partes, la imagen a enviar, y el texto
			MimeMultipart multiparte = new MimeMultipart("related");

			// Primera parte, el html
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<table border=0 align='left'>"
								+"<tbody>"
									+"<tr>"
										+"<td>"
											+"<h4 style='font-size:14px;'>"+parametros.get("header")+"</h4>" 
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td style='text-align:right;'>"
											+parametros.get("dateheader")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("dataclient")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("nameclient")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											 +" - "+parametros.get("dirclient")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"Presente.- <br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"De nuestra consideración,<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"Por intermedio del presente le informamos que hemos recibido su "+parametros.get("textoreclamo")+",le agradecermos" 
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"realizar la verificación de los datos consignados:<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("nroreclamo")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("fechahora")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("emailclient")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("telfclient")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("tiporeclamo")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("detallebody")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("pedidoreclamo")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("pedidobody")+"<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+ "<tr><td></td></tr>"
									+"<tr>"
										+"<td>"
											+"De acuerdo a las normas vigentes para este tipo de trámites, le informamos que en un plazo"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"máximo de 72 horas estaremos dando respuesta.<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"Si tuviera alguna duda o consulta sobre el particular, agradeceremos comunicarse al correo"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"electrónico ventatelefonica@grupopalomino.com.pe o al teléfono 997 769 498 | 993 888 999<br></br><br></br>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("responsable")
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"+"</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("empresa") 
										+ "</td>"
									+ "</tr>"
								+ "</tbody>"
							+ "</table>";
			messageBodyPart.setContent(htmlText, "text/html");
			multiparte.addBodyPart(messageBodyPart);
	
			if(stream != null){
				//Se reusa el messagebodypart
				messageBodyPart = new MimeBodyPart();
				
				DataSource archivo = new ByteArrayDataSource(stream.toByteArray(), "application/pdf");
				messageBodyPart.setDataHandler(new DataHandler(archivo));
				messageBodyPart.setFileName("Reclamo-N°-"+parametros.get("idreclamo")+".pdf");
			}
			
			multiparte.addBodyPart(messageBodyPart);
			
			message.setContent(multiparte);

			Transport.send(message,USERNAMERECLAMOS,PASSWORDRECLAMOS);
			
			messageBodyPart = new MimeBodyPart();
		
		} catch (MessagingException e) {
			log.info(Utils.printStackTraceToString(e));
			
		}
		
	}
	public static  Map<String, String> parametrosReclamoCLiente(B_ReclamosBean reclamos,String correoTo,String correoCc){
		
		
		Map<String, String> parametros = new HashMap<String, String>();
		
		try {
			
			if(correoCc!= null){
				
				parametros.put("cc",correoCc);
			}
			//correoTo
			parametros.put("to",correoTo);
			parametros.put("subject","Atencion de Reclamos N° "+reclamos.getNro()+"");
			parametros.put("header",""+reclamos.getEmpresaD()+" - REGISTRO RECLAMO N° "+reclamos.getNro()+" - "+reclamos.getAnio()+" "+"");
			parametros.put("dateheader","<b>LIMA - "+Utils.FormatoFechaVoucher(reclamos.getFechaCorreo(),"N")+"</b>");
			parametros.put("empresa","<b>"+reclamos.getEmpresaD()+"</b>");
			parametros.put("dataclient","<b>Señor(a,ita)</b><br></br>");
			parametros.put("nameclient",""+reclamos.getNombres()+" "+reclamos.getApePaterno()+" "+reclamos.getApeMaterno()+"");
			parametros.put("dirclient",""+reclamos.getDomicilio()+"");
			parametros.put("nroreclamo","<b>RECLAMO N° "+reclamos.getNro()+" - "+reclamos.getAnio()+"</b>");
			parametros.put("idreclamo",""+reclamos.getNro()+"");
			parametros.put("fechahora","<b>Fecha y Hora</b> : "+reclamos.getFechaReclamo()+" a las "+reclamos.getHora()+" HORAS.");
			parametros.put("emailclient","<b> Correo Electrónico :</b> "+reclamos.getEmail()+"");
			parametros.put("telfclient","<b>Teléfonos del Contacto:</b> "+reclamos.getTelefono()+"");
			parametros.put("tiporeclamo","<b>DETALLE </b>"+(reclamos.getTipoReclamo().trim().equals("R")? "DEL RECLAMO" : "DE LA QUEJA")+"");
			parametros.put("textoreclamo","<b>"+(reclamos.getTipoReclamo().trim().equals("R")? "RECLAMO" : "QUEJA")+"</b>");
			parametros.put("pedidoreclamo","<b>PEDIDO </b>"+(reclamos.getTipoReclamo().trim().equals("R")? "DEL RECLAMO" : "DE LA QUEJA")+"");
			parametros.put("detallebody",""+reclamos.getDetalle()+"");
			parametros.put("pedidobody",""+reclamos.getPedido()+"");
			
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		
		return parametros;
		
	}
	
	public static  Map<String, String> parametrosAtencionReclamos(B_AtencionReclamosBean reclamos,String correoTo,String correoCc){
		
		
		Map<String, String> parametros = new HashMap<String, String>();
		
		try {
			
			if(correoCc!= null){
			
				parametros.put("cc",correoCc);
			}
			
			parametros.put("to",correoTo);
			parametros.put("subject","Atencion de Reclamos N° "+reclamos.getNro()+"");
			parametros.put("header",""+reclamos.getEmpresaD()+" - REGISTRO RECLAMO N° "+reclamos.getNro()+" - "+reclamos.getAnio()+" "+"");
			parametros.put("dateheader","<b>LIMA - "+Utils.FormatoFechaVoucher(reclamos.getFechaCorreo(),"N")+"</b>");
			parametros.put("empresa","<b>"+reclamos.getEmpresaD()+"</b>");
			parametros.put("responsable","<b>"+/*reclamos.getResponsable()*/""+"</b>");
			parametros.put("dataclient","<b>Señor(a,ita)</b><br></br>");
			parametros.put("nameclient",""+reclamos.getNombres()+" "+reclamos.getApePaterno()+" "+reclamos.getApeMaterno()+"");
			parametros.put("dirclient",""+reclamos.getDomicilio()+"");
			parametros.put("nroreclamo","<b>RECLAMO N° "+reclamos.getNro()+" - "+reclamos.getAnio()+"</b>");
			parametros.put("idreclamo",""+reclamos.getNro()+"");
			parametros.put("fechahora","<b>Fecha y Hora</b> : "+reclamos.getFechaReclamo()+" a las "+reclamos.getHora()+" HORAS.");
			parametros.put("emailclient","<b> Correo Electrónico :</b> "+reclamos.getEmail()+"");
			parametros.put("telfclient","<b>Teléfonos del Contacto:</b> "+reclamos.getTelefono()+"");
			parametros.put("tiporeclamo","<b>DETALLE </b>"+(reclamos.getTipoReclamo().trim().equals("R")? "DEL RECLAMO" : "DE LA QUEJA")+"");
			parametros.put("textoreclamo","<b>"+(reclamos.getTipoReclamo().trim().equals("R")? "RECLAMO" : "QUEJA")+"</b>");
			parametros.put("pedidoreclamo","<b>PEDIDO </b>"+(reclamos.getTipoReclamo().trim().equals("R")? "DEL RECLAMO" : "DE LA QUEJA")+"");
			parametros.put("detallebody",""+reclamos.getDetalle()+"");
			parametros.put("pedidobody",""+reclamos.getPedido()+"");
			
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		
		return parametros;
		
	}
	
	
}
