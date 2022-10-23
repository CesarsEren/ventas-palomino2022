package pe.com.grupopalomino.sistema.boletaje.visarespuesta;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class ConsultaEticketVisa {
	
	public static final String RESPUESTA_APROBADA = "1";
	public static final String RESPUESTA_DENEGADA = "2";
	
	public static String callWebService(String nroTicket){
		
		WSConsultaEticket service = new WSConsultaEticket();
		WSConsultaEticketSoap port = service.getWSConsultaEticketSoap();
		
		StringWriter sw = new StringWriter();
		
		try {
			DocumentBuilderFactory fabrica 	= DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = fabrica.newDocumentBuilder();
			Document documento = documentBuilder.newDocument();
			documento.setXmlStandalone(true);
			
			Element consulta_ticket = documento.createElement("consulta_eticket");
			documento.appendChild(consulta_ticket);
		
			Element parametros = documento.createElement("parametros");
			consulta_ticket.appendChild(parametros);
			
			Element codtienda = documento.createElement("parametro");
			//codtienda.appendChild(documento.createTextNode("259672706"));
			/*********************************************************************************/
			// EL CODIGO DE COMERCIO DE PRODUCCION
			codtienda.appendChild(documento.createTextNode("417931320"));
			/*********************************************************************************/
			
			parametros.appendChild(codtienda);
			
			Attr codtienda_id = documento.createAttribute("id");
			codtienda_id.setValue("CODTIENDA");
			codtienda.setAttributeNode(codtienda_id);
			
			Element e_eticket = documento.createElement("parametro");
			e_eticket.appendChild(documento.createTextNode(nroTicket));
			parametros.appendChild(e_eticket);
			
			Attr e_eticket_id = documento.createAttribute("id");
			e_eticket_id.setValue("ETICKET");
			e_eticket.setAttributeNode(e_eticket_id);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "YES");
			
			DOMSource source = new DOMSource(documento);
			StreamResult resultado = new StreamResult(sw);
			transformer.transform(source, resultado);
			
			System.out.println(sw.toString());
		// 4072 2102 9053 6663 Fecha de expiración: 03/2018 ccv2: 377
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		String respuesta = port.consultaEticket(sw.toString());
		
		return respuesta;
	}
	
	public static Map<String, String> obtieneRespuestaVisa(String nroTicket){
		
		String xmlEticketResponse = callWebService(nroTicket);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder; 
		
		Map<String, String> resultados = new HashMap<String, String>();
		
		try {
			builder = factory.newDocumentBuilder();  
	        Document document = builder.parse( new InputSource( new StringReader( xmlEticketResponse ) ));
	        
	        resultados.put("pedidoID", document.getElementsByTagName("pedido").item(0).getAttributes().getNamedItem("id").getNodeValue());
	        resultados.put("eticket", document.getElementsByTagName("pedido").item(0).getAttributes().getNamedItem("eticket").getNodeValue());
	        resultados.put("respuesta", document.getElementsByTagName("campo").item(0).getTextContent());
	        resultados.put("estado", document.getElementsByTagName("campo").item(1).getTextContent());
	        resultados.put("cod_tienda", document.getElementsByTagName("campo").item(2).getTextContent());
	        resultados.put("nordent", document.getElementsByTagName("campo").item(3).getTextContent());
	        resultados.put("cod_accion", document.getElementsByTagName("campo").item(4).getTextContent());
	        resultados.put("pan", document.getElementsByTagName("campo").item(5).getTextContent());
	        resultados.put("nombre_th", document.getElementsByTagName("campo").item(6).getTextContent());
	        resultados.put("ori_tarjeta", document.getElementsByTagName("campo").item(7).getTextContent());
	        resultados.put("nom_emisor", document.getElementsByTagName("campo").item(8).getTextContent());
	        resultados.put("eci", document.getElementsByTagName("campo").item(9).getTextContent());
	        resultados.put("cod_autoriza", document.getElementsByTagName("campo").item(10).getTextContent());
	        resultados.put("id_unico", document.getElementsByTagName("campo").item(11).getTextContent());
	        resultados.put("fechayhora_tx", document.getElementsByTagName("campo").item(12).getTextContent());
	        resultados.put("fechayhora_deposito", document.getElementsByTagName("campo").item(13).getTextContent());
	        resultados.put("dato_comercio", document.getElementsByTagName("campo").item(14).getTextContent());
	        resultados.put("dsc_cod_accion", document.getElementsByTagName("campo").item(15).getTextContent());
	        resultados.put("nrocuotas", document.getElementsByTagName("campo").item(16).getTextContent());
	        resultados.put("impcuotaaprox", document.getElementsByTagName("campo").item(17).getTextContent());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultados;
	}
	
}
