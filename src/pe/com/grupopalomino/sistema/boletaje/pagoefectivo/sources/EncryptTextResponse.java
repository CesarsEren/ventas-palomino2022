
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
 *         &lt;element name="EncryptTextResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "encryptTextResult"
})
@XmlRootElement(name = "EncryptTextResponse")
public class EncryptTextResponse {

    @XmlElement(name = "EncryptTextResult")
    protected String encryptTextResult;

    /**
     * Obtiene el valor de la propiedad encryptTextResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptTextResult() {
        return encryptTextResult;
    }

    /**
     * Define el valor de la propiedad encryptTextResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptTextResult(String value) {
        this.encryptTextResult = value;
    }

}
