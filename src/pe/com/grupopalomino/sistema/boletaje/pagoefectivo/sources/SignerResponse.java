
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
 *         &lt;element name="SignerResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "signerResult"
})
@XmlRootElement(name = "SignerResponse")
public class SignerResponse {

    @XmlElement(name = "SignerResult")
    protected String signerResult;

    /**
     * Obtiene el valor de la propiedad signerResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignerResult() {
        return signerResult;
    }

    /**
     * Define el valor de la propiedad signerResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignerResult(String value) {
        this.signerResult = value;
    }

}
