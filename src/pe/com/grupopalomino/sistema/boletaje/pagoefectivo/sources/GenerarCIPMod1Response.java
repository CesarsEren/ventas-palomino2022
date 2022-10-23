
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
 *         &lt;element name="GenerarCIPMod1Result" type="{http://tempuri.org/}BEWSGenCIPResponseMod1" minOccurs="0"/>
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
    "generarCIPMod1Result"
})
@XmlRootElement(name = "GenerarCIPMod1Response")
public class GenerarCIPMod1Response {

    @XmlElement(name = "GenerarCIPMod1Result")
    protected BEWSGenCIPResponseMod1 generarCIPMod1Result;

    /**
     * Obtiene el valor de la propiedad generarCIPMod1Result.
     * 
     * @return
     *     possible object is
     *     {@link BEWSGenCIPResponseMod1 }
     *     
     */
    public BEWSGenCIPResponseMod1 getGenerarCIPMod1Result() {
        return generarCIPMod1Result;
    }

    /**
     * Define el valor de la propiedad generarCIPMod1Result.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSGenCIPResponseMod1 }
     *     
     */
    public void setGenerarCIPMod1Result(BEWSGenCIPResponseMod1 value) {
        this.generarCIPMod1Result = value;
    }

}
