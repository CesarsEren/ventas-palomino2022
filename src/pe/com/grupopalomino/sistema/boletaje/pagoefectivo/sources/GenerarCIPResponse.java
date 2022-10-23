
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
 *         &lt;element name="GenerarCIPResult" type="{http://tempuri.org/}BEWSGenCIPResponse" minOccurs="0"/>
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
    "generarCIPResult"
})
@XmlRootElement(name = "GenerarCIPResponse")
public class GenerarCIPResponse {

    @XmlElement(name = "GenerarCIPResult")
    protected BEWSGenCIPResponse generarCIPResult;

    /**
     * Obtiene el valor de la propiedad generarCIPResult.
     * 
     * @return
     *     possible object is
     *     {@link BEWSGenCIPResponse }
     *     
     */
    public BEWSGenCIPResponse getGenerarCIPResult() {
        return generarCIPResult;
    }

    /**
     * Define el valor de la propiedad generarCIPResult.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSGenCIPResponse }
     *     
     */
    public void setGenerarCIPResult(BEWSGenCIPResponse value) {
        this.generarCIPResult = value;
    }

}
