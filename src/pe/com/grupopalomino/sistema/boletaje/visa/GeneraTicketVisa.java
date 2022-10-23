package pe.com.grupopalomino.sistema.boletaje.visa;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import pe.com.grupopalomino.sistema.boletaje.util.Regex;

public class GeneraTicketVisa {

	private static final Logger log = Logger.getLogger(GeneraTicketVisa.class);
	
	 private static String callWebService(double sumaTotal) {
	        WSEticket service = new WSEticket();
	        WSEticketSoap port = service.getWSEticketSoap();
	        
	        GeneraEticket ticket = new GeneraEticket();
	        
	        StringWriter sw = new StringWriter();
	        
	        DecimalFormat decimalFormat = new DecimalFormat("#.00");
	        String sumaTotalString = decimalFormat.format(sumaTotal).toString().replace(",", ".");
	        
	        try {
	        	DocumentBuilderFactory fabrica 	= DocumentBuilderFactory.newInstance();
		        DocumentBuilder documentBuilder = fabrica.newDocumentBuilder();
				Document documento = documentBuilder.newDocument();
				documento.setXmlStandalone(true);
				
				Element e_ticket = documento.createElement("nuevo_eticket");
				documento.appendChild(e_ticket);
				
				Element parametros = documento.createElement("parametros");
				e_ticket.appendChild(parametros);

				Element canal = documento.createElement("parametro");
				canal.appendChild(documento.createTextNode("3"));
				parametros.appendChild(canal);
				
				Attr canal_id = documento.createAttribute("id");
				canal_id.setValue("CANAL");
				canal.setAttributeNode(canal_id);
				
				Element producto = documento.createElement("parametro");
				producto.appendChild(documento.createTextNode("1"));
				parametros.appendChild(producto);
				
				Attr producto_id = documento.createAttribute("id");
				producto_id.setValue("PRODUCTO");
				producto.setAttributeNode(producto_id);
				
				Element codtienda = documento.createElement("parametro");
				//codtienda.appendChild(documento.createTextNode("259672706"));
				/***************************************************************************/
				// CODIGO DE COMERCIO PARA PRODUCCION
				codtienda.appendChild(documento.createTextNode("417931320"));
				/***************************************************************************/
				parametros.appendChild(codtienda);
				
				Attr codtienda_id = documento.createAttribute("id");
				codtienda_id.setValue("CODTIENDA");
				codtienda.setAttributeNode(codtienda_id);
				
				Element numorden = documento.createElement("parametro");
				numorden.appendChild(documento.createTextNode(generaUUID()));
				parametros.appendChild(numorden);
				
				Attr numorden_id = documento.createAttribute("id");
				numorden_id.setValue("NUMORDEN");
				numorden.setAttributeNode(numorden_id);
				
				Element mount = documento.createElement("parametro");
				mount.appendChild(documento.createTextNode(sumaTotalString));
				parametros.appendChild(mount);
				
				Attr mount_id = documento.createAttribute("id");
				mount_id.setValue("MOUNT");
				mount.setAttributeNode(mount_id);
				
				Element dato_comercio = documento.createElement("parametro");
				dato_comercio.appendChild(documento.createTextNode("Dato"));
				parametros.appendChild(dato_comercio);
				
				Attr dato_comercio_id = documento.createAttribute("id");
				dato_comercio_id.setValue("DATO_COMERCIO");
				dato_comercio.setAttributeNode(dato_comercio_id);
				
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "YES");
				
				DOMSource source = new DOMSource(documento);
				StreamResult resultado = new StreamResult(sw);
				transformer.transform(source, resultado);
				
				System.out.println(sw.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        ticket.setXmlIn(sw.toString());
	        
	        String respuesta = port.generaEticket(ticket.getXmlIn());
	        log.info(respuesta);
	        
	        return respuesta;
	 }
	 
	 private static String generaUUID(){
		 String uuid = UUID.randomUUID().toString().replace("-", "").replaceAll(Regex.LETRAS, "").substring(0, 8);
		 return String.format("%09d", Integer.parseInt(uuid));
	 }
	 
	 public static String obtieneEticket(double sumaTotal){
		 
		String xmlEticketResponse = callWebService(sumaTotal);
		String eticket = "";
		 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder;  
		try {
			log.info("Transformando string a xml...");
			builder = factory.newDocumentBuilder();  
	        Document document = builder.parse( new InputSource( new StringReader( xmlEticketResponse ) ));
	        if(document.getElementsByTagName("eticket") != null){
	        	eticket = document.getElementsByTagName("campo").item(2).getTextContent();
	        }
	        else{
	        	log.info("Error al transformar ...");
	        	eticket = "ERROR";
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		
		return eticket;
	 }
	 
	 public static void main(String[] args) {
		 String ETICKET = GeneraTicketVisa.obtieneEticket(120.00);
		 
		 System.out.println(ETICKET);
		 
	}
	 
}
