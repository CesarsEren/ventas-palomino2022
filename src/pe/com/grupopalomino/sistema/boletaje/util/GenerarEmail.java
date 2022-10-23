package pe.com.grupopalomino.sistema.boletaje.util;

import java.io.ByteArrayOutputStream;  
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenerarEmail {
	
	private final static String FROM = "tiendavirtual@grupopalomino.com.pe";
	private final static String USERNAME = "tiendavirtual@grupopalomino.com.pe";
	private final static String PASSWORD = "gpalomino.12.3";
	/*private final static String FROM = "informesti@grupopalomino.com.pe";
	private final static String USERNAME = "informesti@grupopalomino.com.pe";
	private final static String PASSWORD = "gpalomino.12.3";*/
	private static final Log log = LogFactory.getLog(GenerarEmail.class);
	
	/*private Session getSession(String Username,String Password){
		
		Session session = null;
		
		session = Session.getDefaultInstance(getPropiedades(),new javax.mail.Authenticator() {
		
			protected PasswordAuthentication getPasswordAuthentication() {
				
				System.out.println("GENERA SESSION MAIL "+USERNAME);
				System.out.println("GENERA SESSION MAIL "+PASSWORD);
				
				return new PasswordAuthentication(Username, Password);
			}
		});
		
		return session;
	}*/
	
	
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
	
	public static void enviarCorreoAdjunto(Map<String, String> parametros, ByteArrayOutputStream stream) throws IOException{
		 
		try {
			

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			Message message = new MimeMessage(SessionMail.getSessionEmail());
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(parametros.get("to")));
			//ventatelefonica3@grupopalomino.com.pe,ventatelefonica2@grupopalomino.com.pe"
			message.addRecipients(Message.RecipientType.BCC,InternetAddress.parse("earce@grupopalomino.com.pe,desarrolladorweb@grupopalomino.com.pe"));
			message.setSubject(parametros.get("subject"));

			// El correo tiene 2 partes, la imagen a enviar, y el texto
			MimeMultipart multiparte = new MimeMultipart("related");

			// Primera parte, el html
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<table style='border-radius:4px;border:1px #dceaf5 solid' border=0 align='center'>"
								+"<tbody>"
									+"<tr>"
										+"<td>"
											+parametros.get("body")
										+ "</td>"
									+ "</tr>"
									+ "<tr><td></td></tr>"
									/*+"<tr>"
										+"<td>"
											+ "<img src=\"cid:image\">"
										+ "</td>"
									+ "</tr>"*/
								+ "</tbody>"
							+ "</table>";
			messageBodyPart.setContent(htmlText, "text/html");

			multiparte.addBodyPart(messageBodyPart);

			// Segunda parte, la imagen
			/*messageBodyPart = new MimeBodyPart();			
			URL url = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/logo.png");
			DataSource fds = new URLDataSource(url);
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");
			multiparte.addBodyPart(messageBodyPart);
			*/
			if(stream != null){
				//Se reusa el messagebodypart
				messageBodyPart = new MimeBodyPart();				
				DataSource archivo = new ByteArrayDataSource(stream.toByteArray(), "application/pdf");
				messageBodyPart.setDataHandler(new DataHandler(archivo));
				messageBodyPart.setFileName("Voucher - "+sdf1.format(new Date())+".pdf");
			}
			
			multiparte.addBodyPart(messageBodyPart);			
			messageBodyPart = new MimeBodyPart();			
			URL url1 = new URL("http://www.grupopalomino.com.pe/prevencion/Menores%20de%2014%20a%c3%b1os.pdf");
			DataSource fds1 = new URLDataSource(url1);
			messageBodyPart.setDataHandler(new DataHandler(fds1)); 
			messageBodyPart.setFileName("Menores.pdf");
			multiparte.addBodyPart(messageBodyPart);			
			
			messageBodyPart = new MimeBodyPart();			
			URL url2 = new URL("http://www.grupopalomino.com.pe/prevencion/Lineamiento_Sectorial_para_Prevencio%cc%81n_del_COVID-19_-_Mayores%20de%2065%20an%cc%83os%20.pdf");
			DataSource fds2 = new URLDataSource(url2);
			messageBodyPart.setDataHandler(new DataHandler(fds2)); 
			messageBodyPart.setFileName("Mayoresde65.pdf");
			multiparte.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();			
			URL url3 = new URL("http://www.grupopalomino.com.pe/prevencion/Lineamiento_Sectorial_para_Prevencio%cc%81n_del_COVID-19_-_Transporte_Terrestre_Ambito_Nacional_y_Regional-1.pdf");
			DataSource fds3 = new URLDataSource(url3); 
			messageBodyPart.setDataHandler(new DataHandler(fds3)); 
			messageBodyPart.setFileName("General.pdf");
			multiparte.addBodyPart(messageBodyPart);
			
			message.setContent(multiparte);			
			//Transport transport = SessionMail.getSessionEmail().getTransport("smtp");
			//transport.connect(USERNAME, PASSWORD);
			
			log.info("ENVIO DE MAIL "+ USERNAME + " : " + PASSWORD);
			log.info("MENSAJE"+message);
			log.info("sessionMail"+SessionMail.getSessionEmail().toString());
			Transport.send(message,USERNAME,PASSWORD);
			
			//SessionMail.LimpiaSession();
			
			messageBodyPart = new MimeBodyPart();
		
		} catch (MessagingException e) {
			log.info(Utils.printStackTraceToString(e));
			
		}
		
	}

	public static  Map<String, String> parametros(String username,String Ticket){
		
		Map<String, String> parametros = new HashMap<String, String>();
		
		parametros.put("to",username);
		parametros.put("subject","Confirmacion de Reserva");
		//parametros.put("header","¡Gracias por su Preferencia!");
		parametros.put("body",
		"<table style='max-width: 600px; padding: 10px; margin:0 auto; border-collapse: collapse;'>"
		+"<td style='background-color: #ecf0f1'>"
			+"<div style='color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: sans-serif'>"
				+"<h2 style='color: #e67e22; margin: 0 0 7px'>Estimado Cliente , Gracias por su preferencia.!</h2>"
					+"<p style='margin: 2px; font-size: 15px'>"
					+"Somos la empresa de Transportes Palomino, una empresa de Transportes Interprovinciales, nuestra sede principal se encuentra en la ciudad de Lima Perú.<br>"
						+"En este correo hacemos entrega de su boleto electrónico<br>"
						+"Los pasos a seguir para embarque son:</p>"
						+"<ul style='font-size: 15px;  margin: 10px 0'>"
						+"<li>Acercase al centro de Embarque con 1 hora de anticipación.</li>"
						+"<li>Presentar su boleto electrónico,impreso o en el celular</li>"
						+"<li>Llenar la declaración jurada general.</li>"
						+"<li>En caso de llevar menores de 14 años; llenar la declaración jurada para menores de 14 años.</li> "
						+"<li>En caso de llevar mayores de 65 años; llenar la declaración jurada para mayores de 65 años.</li>"
						+"</ul>"
					+"<div style='width: 100%; text-align: center'>"
					+"<div style='width: 100%; text-align: left;margin: 2px; font-size: 15px>"
					+"<p >Atentamente</p>"
					+"<p>Telefono: 2020-600 anexo 100 opcion 1</p>"
					+"<p>Celular: 997 769 498 </p>"
					+"<p>GRUPO PALOMINO</p>"
					+"<p>Email: ventatelefonica@grupopalomino.com.pe</p>"
					+"<p>Nota: No responder este mensaje ,es un mensaje automático generado por el sistema.</p>"					
					+"</div>"
					+"<div style='width: 100%;margin:20px 0; display: inline-block;text-align: center'>"
					+"<img style='padding: 0; width: 200px; margin: 5px' src='https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/logo.png'>"
					+"</div>"
					+"<div style='width: 100%; text-align: center'>"
					+"<a style='text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #009640' href='http://www.grupopalomino.com.pe/'>Ir a la página</a>"
					+"<p style='color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0;padding:15px 5px 15px 5px'>Grupo Palomino 2020</p>"
					+"</div>"					
				+"</td>"
			+"</tr>"
	+"</table>");
		/*parametros.put("body","Adjunto al presente correo,ha recibido el comprobante de reserva de viaje.<br></br>"
					  + "Agradeceremos acercarse a nuestras agencias para ser canjeado por su BOLETO de VIAJE. <br></br><br></br>"
					  +""+ticketVisa(Ticket)+"<br></br><br></br>"
					  + "¡  CON NOSOTROS! <br></br><br></br>"
					  + "Atentamente <br></br><br></br>"
					  + "GRUPO PALOMINO <br></br>"
					  + "www.grupopalomino.com.pe <br></br>"
					  + "Telefono:2020-600 anexo 100-101 <br></br>"
					  + "Email: ventatelefonica@grupopalomino.com.pe");
		
		parametros.put("body","Adjunto al presente correo,ha recibido el Boleto Electrónico de viaje.<br></br>"
				+"Agradecemos imprimir el boleto para realizar el embarque , en caso de no poder imprimirlo"
				+"Podrá pedir una copia de de Impresión  <br></br><br></br>"
				
		  //+ "Agradeceremos acercarse a nuestras agencias para ser canjeado por su BOLETO de VIAJE. <br></br><br></br>"
		  +""+ticketVisa(Ticket)+"<br></br><br></br>"
		  + "¡  CON NOSOTROS! <br></br><br></br>"
		  + "Atentamente <br></br><br></br>"
		  + "GRUPO PALOMINO <br></br>"
		  + "www.grupopalomino.com.pe <br></br>"
		  + "Telefono:2020-600 anexo 100-101 <br></br>"
		  + "Email: ventatelefonica@grupopalomino.com.pe");
		*/
		return parametros;
		
	}
	
	public static  Map<String, String> parametros2(String username,String Ticket){
		
		Map<String, String> parametros = new HashMap<String, String>();
		
		parametros.put("to",username);
		parametros.put("subject","Confirmacion de Reserva");
		parametros.put("header","¡Gracias por su Preferencia Envio de Prueba!");
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
	
	public static  Map<String, String> parametrosVentasPendientes(String username,String Ticket,String Estado,String mensaje){
		
		Map<String, String> parametros = new HashMap<String, String>();
		
		parametros.put("to",username);
		parametros.put("subject","Estado de Compra "+Estado+"");
		parametros.put("header","¡Gracias por su Preferencia!");
		parametros.put("body","Ocurrio un inconveniente en su compra, a conticuación se especifican detalles de la operación.<br></br><br></br>"
					  +""+(Ticket!=null ?  "Ticket : <b>"+Ticket+".</b> <br></br><br></br>":"")
					  + "Estado : <b>"+Estado+".</b> <br></br><br></br>"
					  + "Descripcion :<b>"+mensaje+"</b><br></br><br></br>"
					  + "Atentamente <br></br><br></br>"
					  + "GRUPO PALOMINO <br></br>"
					  + "www.grupopalomino.com.pe <br></br>"
					  + "Telefono:2020-600 anexo 100-101 <br></br>"
					  + "Email: ventatelefonica@grupopalomino.com.pe");
		
		return parametros;
		
	}
	
	private static String ticketVisa(String ticket){
		String resultado="";
		if(ticket!= null){
			resultado= "Su ticket Generado es <b>"+ticket+"</b> ";
		}
		
		return resultado;
	}
	
	
	
}
