package pe.com.grupopalomino.sistema.boletaje.util;

import java.util.Properties;
import javax.mail.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionMail {
	
	//private static Session session = null;
	
	private static final Log log = LogFactory.getLog(SessionMail.class);
	
	public static Session getSessionEmail(){
		
		Session session = null;
	
		session = Session.getDefaultInstance(getPropiedades(),null);
		/*session = Session.getDefaultInstance(getPropiedades(),new javax.mail.Authenticator() {
		
			protected PasswordAuthentication getPasswordAuthentication() {
				
				System.out.println("GENERA SESSION MAIL "+Username);
				System.out.println("GENERA SESSION MAIL "+Password);
				
				return new PasswordAuthentication(Username, Password);
			}
		});*/
		
		
		return session;
	}

	private static Properties getPropiedades(){
	
		Properties props = new Properties();
	
		///props.put("mail.smtp.host", "172.16.10.4");
		props.put("mail.smtp.host", "mail.grupopalomino.com.pe");
		props.put("mail.smtp.socketFactory.port", "587"); 
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.ssl.trust", "mail.grupopalomino.com.pe");//"mail.grupopalomino.com.pe");
		//props.put("mail.smtp.ssl.trust", "172.16.10.4");//"mail.grupopalomino.com.pe");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.port", "587");
		props.put("mail.smtp.port", "587"); 
		
		//private final static String FROM = "tiendavirtual@grupopalomino.com.pe";
		//private final static String USERNAME = "tiendavirtual@grupopalomino.com.pe";
		//private final static String PASSWORD = "gpalomino.12.3";
		
		return props;
	}

}
