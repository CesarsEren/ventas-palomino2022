
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
 *         &lt;element name="EliminarCIPMod1Result" type="{http://tempuri.org/}BEWSElimCIPResponseMod1" minOccurs="0"/>
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
    "eliminarCIPMod1Result"
})
@XmlRootElement(name = "EliminarCIPMod1Response")
public class EliminarCIPMod1Response {

    @XmlElement(name = "EliminarCIPMod1Result")
    protected BEWSElimCIPResponseMod1 eliminarCIPMod1Result;

    /**
     * Obtiene el valor de la propiedad eliminarCIPMod1Result.
     * 
     * @return
     *     possible object is
     *     {@link BEWSElimCIPResponseMod1 }
     *     
     */
    public BEWSElimCIPResponseMod1 getEliminarCIPMod1Result() {
        return eliminarCIPMod1Result;
    }

    /**
     * Define el valor de la propiedad eliminarCIPMod1Result.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSElimCIPResponseMod1 }
     *     
     */
    public void setEliminarCIPMod1Result(BEWSElimCIPResponseMod1 value) {
        this.eliminarCIPMod1Result = value;
    }

}
