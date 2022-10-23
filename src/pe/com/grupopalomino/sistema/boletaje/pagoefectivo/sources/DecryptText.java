
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="encryptText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privateKey" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "encryptText",
    "privateKey"
})
@XmlRootElement(name = "DecryptText")
public class DecryptText {

    protected String encryptText;
    protected byte[] privateKey;

    /**
     * Obtiene el valor de la propiedad encryptText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptText() {
        return encryptText;
    }

    /**
     * Define el valor de la propiedad encryptText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptText(String value) {
        this.encryptText = value;
    }

    /**
     * Obtiene el valor de la propiedad privateKey.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPrivateKey() {
        return privateKey;
    }

    /**
     * Define el valor de la propiedad privateKey.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPrivateKey(byte[] value) {
        this.privateKey = value;
    }

}
