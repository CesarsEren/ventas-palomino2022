
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
 *         &lt;element name="SolicitarPagoResult" type="{http://tempuri.org/}BEWSSolicitarResponse" minOccurs="0"/>
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
    "solicitarPagoResult"
})
@XmlRootElement(name = "SolicitarPagoResponse")
public class SolicitarPagoResponse {

    @XmlElement(name = "SolicitarPagoResult")
    protected BEWSSolicitarResponse solicitarPagoResult;

    /**
     * Obtiene el valor de la propiedad solicitarPagoResult.
     * 
     * @return
     *     possible object is
     *     {@link BEWSSolicitarResponse }
     *     
     */
    public BEWSSolicitarResponse getSolicitarPagoResult() {
        return solicitarPagoResult;
    }

    /**
     * Define el valor de la propiedad solicitarPagoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSSolicitarResponse }
     *     
     */
    public void setSolicitarPagoResult(BEWSSolicitarResponse value) {
        this.solicitarPagoResult = value;
    }

}
