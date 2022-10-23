
package pe.com.grupopalomino.sistema.boletaje.visarespuesta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xmlIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xmlIn"
})
@XmlRootElement(name = "ConsultaEticket")
public class ConsultaEticket {

    protected String xmlIn;

    /**
     * Gets the value of the xmlIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlIn() {
        return xmlIn;
    }

    /**
     * Sets the value of the xmlIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlIn(String value) {
        this.xmlIn = value;
    }

}
