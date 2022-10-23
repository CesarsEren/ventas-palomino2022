
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
 *         &lt;element name="ConsultarCIPResult" type="{http://tempuri.org/}BEWSConsultarCIPResponse" minOccurs="0"/>
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
    "consultarCIPResult"
})
@XmlRootElement(name = "ConsultarCIPResponse")
public class ConsultarCIPResponse {

    @XmlElement(name = "ConsultarCIPResult")
    protected BEWSConsultarCIPResponse consultarCIPResult;

    /**
     * Obtiene el valor de la propiedad consultarCIPResult.
     * 
     * @return
     *     possible object is
     *     {@link BEWSConsultarCIPResponse }
     *     
     */
    public BEWSConsultarCIPResponse getConsultarCIPResult() {
        return consultarCIPResult;
    }

    /**
     * Define el valor de la propiedad consultarCIPResult.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSConsultarCIPResponse }
     *     
     */
    public void setConsultarCIPResult(BEWSConsultarCIPResponse value) {
        this.consultarCIPResult = value;
    }

}
