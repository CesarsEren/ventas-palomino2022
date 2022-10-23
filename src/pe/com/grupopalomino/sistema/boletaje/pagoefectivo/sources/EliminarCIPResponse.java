
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
 *         &lt;element name="EliminarCIPResult" type="{http://tempuri.org/}BEWSElimCIPResponse" minOccurs="0"/>
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
    "eliminarCIPResult"
})
@XmlRootElement(name = "EliminarCIPResponse")
public class EliminarCIPResponse {

    @XmlElement(name = "EliminarCIPResult")
    protected BEWSElimCIPResponse eliminarCIPResult;

    /**
     * Obtiene el valor de la propiedad eliminarCIPResult.
     * 
     * @return
     *     possible object is
     *     {@link BEWSElimCIPResponse }
     *     
     */
    public BEWSElimCIPResponse getEliminarCIPResult() {
        return eliminarCIPResult;
    }

    /**
     * Define el valor de la propiedad eliminarCIPResult.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSElimCIPResponse }
     *     
     */
    public void setEliminarCIPResult(BEWSElimCIPResponse value) {
        this.eliminarCIPResult = value;
    }

}
