
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
 *         &lt;element name="ActualizarCIPMod1Result" type="{http://tempuri.org/}BEWSActualizaCIPResponseMod1" minOccurs="0"/>
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
    "actualizarCIPMod1Result"
})
@XmlRootElement(name = "ActualizarCIPMod1Response")
public class ActualizarCIPMod1Response {

    @XmlElement(name = "ActualizarCIPMod1Result")
    protected BEWSActualizaCIPResponseMod1 actualizarCIPMod1Result;

    /**
     * Obtiene el valor de la propiedad actualizarCIPMod1Result.
     * 
     * @return
     *     possible object is
     *     {@link BEWSActualizaCIPResponseMod1 }
     *     
     */
    public BEWSActualizaCIPResponseMod1 getActualizarCIPMod1Result() {
        return actualizarCIPMod1Result;
    }

    /**
     * Define el valor de la propiedad actualizarCIPMod1Result.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSActualizaCIPResponseMod1 }
     *     
     */
    public void setActualizarCIPMod1Result(BEWSActualizaCIPResponseMod1 value) {
        this.actualizarCIPMod1Result = value;
    }

}
