package pe.com.grupopalomino.sistema.boletaje.util;

import java.io.ByteArrayOutputStream;   
import java.io.IOException;
import java.net.URL;
import java.util.Map;
//import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenerarEmailPreguntas {
	
	/*private final static String FROMPREGUNTAS = "atencioncliente@grupopalomino.com.pe";
	private final static String USERNAMEPREGUNTAS = "atencioncliente@grupopalomino.com.pe";*/
	private final static String FROMPREGUNTAS = "ventatelefonica@grupopalomino.com.pe";
	private final static String USERNAMEPREGUNTAS = "ventatelefonica@grupopalomino.com.pe";
	//private final static String PASSWORDPREGUNTAS = "gpalomino.12.3";
	private final static String PASSWORDPREGUNTAS = "palomino";
	private static final Log log = LogFactory.getLog(GenerarEmailPreguntas.class);
	
	
	/*private static Properties getPropiedades(){
		
		Properties props = new Properties();

		props.put("mail.smtp.host", "mail.grupopalomino.com.pe");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.ssl.trust", "mail.grupopalomino.com.pe");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		return props;
		
	}*/

	public static void enviarCorreoPreguntasFrecuentes(Map<String, String> parametros, ByteArrayOutputStream stream) throws IOException{
		
		/*Properties props = getPropiedades();
		
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				
				System.out.println("GENERA MAIL PREGUNTAS "+USERNAMEPREGUNTAS);
				System.out.println("GENERA MAIL PREGUNTAS "+PASSWORDPREGUNTAS);
				
				return new PasswordAuthentication(USERNAMEPREGUNTAS, PASSWORDPREGUNTAS);
			}
		});*/

		try {

			Message message = new MimeMessage(SessionMail.getSessionEmail());
			message.setFrom(new InternetAddress(FROMPREGUNTAS));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(parametros.get("to")));
			
			if(parametros.get("cc")!=null){
				
				message.addRecipients(Message.RecipientType.CC,InternetAddress.parse(parametros.get("cc")));
				
			}
			
			message.setSubject(parametros.get("subject"));

			// El correo tiene 2 partes, la imagen a enviar, y el texto
			MimeMultipart multiparte = new MimeMultipart("related");

			// Primera parte, el html
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<table style='border=0 align='left'>"
					+"<tbody>"
						+"<tr>"
							+"<td>"+"</td>"
						+ "</tr>"
						+"<tr>"
							+"<td>"
								+"<h4 style='font-size:16px;text-align:center;'>"+parametros.get("header")+"</h4>" 
							+ "</td>"
						+ "</tr>"
						+"<tr>"
							+"<td style='text-align:left;'>"
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
				
			}
			
			multiparte.addBodyPart(messageBodyPart);
			
			message.setContent(multiparte);
			
			//Transport transport = SessionMail.getSessionEmail().getTransport("smtp");
			//transport.connect(USERNAMEPREGUNTAS, PASSWORDPREGUNTAS);

			//log.info("ENVIO DE MAIL PREGUNTAS "+ USERNAMEPREGUNTAS + " : " + PASSWORDPREGUNTAS);
			
			
			Transport.send(message,USERNAMEPREGUNTAS,PASSWORDPREGUNTAS);
			
			//SessionMail.LimpiaSession();
			
			messageBodyPart = new MimeBodyPart();
		
		} catch (MessagingException e) {
			log.info(Utils.printStackTraceToString(e));
			
		}
	}
	
}
