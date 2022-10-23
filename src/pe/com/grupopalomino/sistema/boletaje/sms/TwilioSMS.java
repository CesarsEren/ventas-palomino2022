package pe.com.grupopalomino.sistema.boletaje.sms;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Message;

public class TwilioSMS {

	public static final String ACCOUNT_SID = "AC32925d6c1e7c74842ae31f87b63135c6";
	public static final String AUTH_TOKEN = "eadc724f487ec8b3159163aac07bb591";
	
	public static void enviarMensajeTexto() throws TwilioRestException{
		
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Body", "Saludos desde java"));
	    params.add(new BasicNameValuePair("To", "+12345678901"));
	    params.add(new BasicNameValuePair("From", "+12345678901"));
		
	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message = messageFactory.create(params);
	    System.out.println(message.getSid());
	    
	}
	
}
