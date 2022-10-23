
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DecryptTextResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "decryptTextResult"
})
@XmlRootElement(name = "DecryptTextResponse")
public class DecryptTextResponse {

    @XmlElement(name = "DecryptTextResult")
    protected String decryptTextResult;

    /**
     * Obtiene el valor de la propiedad decryptTextResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecryptTextResult() {
        return decryptTextResult;
    }

    /**
     * Define el valor de la propiedad decryptTextResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecryptTextResult(String value) {
        this.decryptTextResult = value;
    }

}
