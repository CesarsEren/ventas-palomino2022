package pe.com.grupopalomino.sistema.boletaje.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
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

public class GenerarEmailInformes {

	private final static String FROM = "informestic@grupopalomino.com.pe";
	private final static String USERNAMEINFORMES = "informestic@grupopalomino.com.pe";
	private final static String PASSWORDINFORMES = "4dm1n1str4t0r%&$2023#/*;!@.-&P,";

	private static final Log log = LogFactory.getLog(GenerarEmailInformes.class);

	public static void enviarCorreoContabilidad(Map<String, String> parametros) {
		try {

			Message message = new MimeMessage(SessionMail.getSessionEmail());
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(parametros.get("to")));

			if (parametros.get("cc") != null) {

				message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(parametros.get("cc")));

			}

			message.setSubject(parametros.get("subject"));

			// El correo tiene 2 partes, la imagen a enviar, y el texto
			MimeMultipart multiparte = new MimeMultipart("related");

			// Primera parte, el html
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<table style='border=0 align='left'>" + "<tbody>" + "<tr>" + "<td>" + "</td>" + "</tr>"
					+ "<tr>" + "<td>" + "<h4 style='font-size:16px;text-align:center;'>" + parametros.get("header")
					+ "</h4>" + "</td>" + "</tr>" + "<tr>" + "<td style='text-align:left;'>" + parametros.get("body")
					+ "</td>" + "</tr>" + "<tr><td></td></tr>" + "<tr>" + "<td>" + "<img src=\"cid:image\">" + "</td>"
					+ "</tr>" + "</tbody>" + "</table>";
			messageBodyPart.setContent(htmlText, "text/html");
			multiparte.addBodyPart(messageBodyPart);

			// Segunda parte, la imagen
			messageBodyPart = new MimeBodyPart();
			URL url = new URL("http://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/logo.png");

			DataSource fds = new URLDataSource(url);

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");

			multiparte.addBodyPart(messageBodyPart);
/*
			if (stream != null) {

			}
*/
			multiparte.addBodyPart(messageBodyPart);

			message.setContent(multiparte);

			// Transport transport =
			// SessionMail.getSessionEmail().getTransport("smtp");
			// transport.connect(USERNAMEPREGUNTAS, PASSWORDPREGUNTAS);

			// log.info("ENVIO DE MAIL PREGUNTAS "+ USERNAMEPREGUNTAS + " : " +
			// PASSWORDPREGUNTAS);

			Transport.send(message, USERNAMEINFORMES, PASSWORDINFORMES);

			// SessionMail.LimpiaSession();

			messageBodyPart = new MimeBodyPart();

		} catch (MessagingException | MalformedURLException e) {
			log.info(Utils.printStackTraceToString(e));

		}
	}
	
	
	private static String ticketVisa(String ticket){
		String resultado="";
		if(ticket!= null){
			resultado= "Su ticket Generado es <b>"+ticket+"</b> ";
		}
		
		return resultado;
	}
	
	
	
	public static  Map<String, String> parametros(String username,String Ticket){
		
		Map<String, String> parametros = new HashMap<String, String>();
		
		parametros.put("to",username);
		parametros.put("subject","Confirmacion de Reserva");
		parametros.put("header","¡Gracias por su Preferencia!");
		parametros.put("body","Adjunto al presente correo,ha recibido el comprobante de reserva de viaje.<br></br>"
					  + "Agradeceremos acercarse a nuestras agencias para ser canjeado por su BOLETO de VIAJE. <br></br><br></br>"
					  +""+ticketVisa(Ticket)+"<br></br><br></br>"
					  + "¡  CON NOSOTROS! <br></br><br></br>"
					  + "Atentamente <br></br><br></br>"
					  + "GRUPO PALOMINO <br></br>"
					  + "www.grupopalomino.com.pe <br></br>"
					  + "Telefono:2020-600 anexo 100-101 <br></br>"
					  + "Email: ventatelefonica@grupopalomino.com.pe");
		
		return parametros;
		
	}
	
	
	
	
	
	
	
	
public static void enviarCorreoAdjunto(Map<String, String> parametros, ByteArrayOutputStream stream) throws IOException{
		
		/*Properties props = getPropiedades();
		
		
		
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
		
			
			protected PasswordAuthentication getPasswordAuthentication() {
				
				System.out.println("GENERA MAIL "+USERNAME);
				System.out.println("GENERA MAIL "+PASSWORD);
				
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});*/

		try {
			

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			Message message = new MimeMessage(SessionMail.getSessionEmail());
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(parametros.get("to")));
			message.setSubject(parametros.get("subject"));

			// El correo tiene 2 partes, la imagen a enviar, y el texto
			MimeMultipart multiparte = new MimeMultipart("related");

			// Primera parte, el html
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<table style='border-radius:4px;border:1px #dceaf5 solid' border=0 align='center'>"
								+"<tbody>"
									+"<tr>"
										+"<td>"
											+"Estimado Cliente:" 
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"<h4 style='font-size:16px;'>"+parametros.get("header")+"</h4>" 
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("body")
										+ "</td>"
									+ "</tr>"
									+ "<tr><td></td></tr>"
									+"<tr>"
										+"<td>"
											+ "<img src=\"cid:image\">"
										+ "</td>"
									+ "</tr>"
								+ "</tbody>"
							+ "</table>";
			messageBodyPart.setContent(htmlText, "text/html");

			multiparte.addBodyPart(messageBodyPart);

			// Segunda parte, la imagen
			messageBodyPart = new MimeBodyPart();
			URL url = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/logo.png");

			DataSource fds = new URLDataSource(url);

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");

			multiparte.addBodyPart(messageBodyPart);
			
			if(stream != null){
				//Se reusa el messagebodypart
				messageBodyPart = new MimeBodyPart();
				
				DataSource archivo = new ByteArrayDataSource(stream.toByteArray(), "application/pdf");
				messageBodyPart.setDataHandler(new DataHandler(archivo));
				messageBodyPart.setFileName("Voucher - "+sdf1.format(new Date())+".pdf");
			}
			
			multiparte.addBodyPart(messageBodyPart);
			
			message.setContent(multiparte);
			
			//Transport transport = SessionMail.getSessionEmail().getTransport("smtp");
			//transport.connect(USERNAME, PASSWORD);
			
			log.info("ENVIO DE MAIL "+ USERNAMEINFORMES + " : " + PASSWORDINFORMES);
			log.info("MENSAJE"+message);
			log.info("sessionMail"+SessionMail.getSessionEmail().toString());
			Transport.send(message,USERNAMEINFORMES,PASSWORDINFORMES);
			
			//SessionMail.LimpiaSession();
			
			messageBodyPart = new MimeBodyPart();
		
		} catch (MessagingException e) {
			log.info(Utils.printStackTraceToString(e));
			
		}
		
	}
	
	
public static void enviarCorreoAdjunto2(Map<String, String> parametros, ByteArrayOutputStream stream) throws IOException{
		 

		try {
			
			//InternetAddress direcciones = new InternetAddress()
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			Message message = new MimeMessage(SessionMail.getSessionEmail());
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(parametros.get("to")));
			message.setSubject(parametros.get("subject"));
			if (parametros.get("cc") != null) {

				message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(parametros.get("cc")));

			}
			// El correo tiene 2 partes, la imagen a enviar, y el texto
			MimeMultipart multiparte = new MimeMultipart("related");

			// Primera parte, el html
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<table style='border-radius:4px;border:1px #dceaf5 solid' border=0 align='center'>"
								+"<tbody>"
									+"<tr>"
										+"<td>"
											+"Estimado:" 
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+"<h4 style='font-size:16px;'>"+parametros.get("header")+"</h4>" 
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+"<td>"
											+parametros.get("body")
										+ "</td>"
									+ "</tr>"
									+ "<tr><td></td></tr>"
									+"<tr>"
										+"<td>"
											+ "<img src=\"cid:image\">"
										+ "</td>"
									+ "</tr>"
								+ "</tbody>"
							+ "</table>";
			messageBodyPart.setContent(htmlText, "text/html");

			multiparte.addBodyPart(messageBodyPart);

			// Segunda parte, la imagen
			messageBodyPart = new MimeBodyPart();
			URL url = new URL("http://ventas.grupopalomino.com.pe:8080/ventas/_lib/img/logo.png");

			DataSource fds = new URLDataSource(url);

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");

			multiparte.addBodyPart(messageBodyPart);
			
			if(stream != null){
				//Se reusa el messagebodypart
				messageBodyPart = new MimeBodyPart();
				
				DataSource archivo = new ByteArrayDataSource(stream.toByteArray(), "application/pdf");
				messageBodyPart.setDataHandler(new DataHandler(archivo));
				messageBodyPart.setFileName("Voucher - "+sdf1.format(new Date())+".pdf");
			}
			
			multiparte.addBodyPart(messageBodyPart);
			
			message.setContent(multiparte);
			
			//Transport transport = SessionMail.getSessionEmail().getTransport("smtp");
			//transport.connect(USERNAME, PASSWORD);
			
			log.info("ENVIO DE MAIL "+ USERNAMEINFORMES + " : " + PASSWORDINFORMES);

			Transport.send(message,USERNAMEINFORMES,PASSWORDINFORMES);
			/*private final static String FROM = "informesti@grupopalomino.com.pe";
			private final static String USERNAMEINFORMES = "informesti@grupopalomino.com.pe";
			private final static String PASSWORDINFORMES = "gpalomino.12.3";*/
			//SessionMail.LimpiaSession();
			
			messageBodyPart = new MimeBodyPart();
		
		} catch (MessagingException e) {
			log.info(Utils.printStackTraceToString(e));
			
		}
		
	}

}
