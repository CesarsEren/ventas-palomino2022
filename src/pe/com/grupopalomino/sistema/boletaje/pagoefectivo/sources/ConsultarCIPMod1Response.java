
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
 *         &lt;element name="ConsultarCIPMod1Result" type="{http://tempuri.org/}BEWSConsultarCIPResponseMod1" minOccurs="0"/>
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
    "consultarCIPMod1Result"
})
@XmlRootElement(name = "ConsultarCIPMod1Response")
public class ConsultarCIPMod1Response {

    @XmlElement(name = "ConsultarCIPMod1Result")
    protected BEWSConsultarCIPResponseMod1 consultarCIPMod1Result;

    /**
     * Obtiene el valor de la propiedad consultarCIPMod1Result.
     * 
     * @return
     *     possible object is
     *     {@link BEWSConsultarCIPResponseMod1 }
     *     
     */
    public BEWSConsultarCIPResponseMod1 getConsultarCIPMod1Result() {
        return consultarCIPMod1Result;
    }

    /**
     * Define el valor de la propiedad consultarCIPMod1Result.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSConsultarCIPResponseMod1 }
     *     
     */
    public void setConsultarCIPMod1Result(BEWSConsultarCIPResponseMod1 value) {
        this.consultarCIPMod1Result = value;
    }

}
