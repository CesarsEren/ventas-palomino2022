
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfBEWSConsCIPDetalle complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBEWSConsCIPDetalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BEWSConsCIPDetalle" type="{http://tempuri.org/}BEWSConsCIPDetalle" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBEWSConsCIPDetalle", propOrder = {
    "bewsConsCIPDetalle"
})
public class ArrayOfBEWSConsCIPDetalle {

    @XmlElement(name = "BEWSConsCIPDetalle", nillable = true)
    protected List<BEWSConsCIPDetalle> bewsConsCIPDetalle;

    /**
     * Gets the value of the bewsConsCIPDetalle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bewsConsCIPDetalle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBEWSConsCIPDetalle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BEWSConsCIPDetalle }
     * 
     * 
     */
    public List<BEWSConsCIPDetalle> getBEWSConsCIPDetalle() {
        if (bewsConsCIPDetalle == null) {
            bewsConsCIPDetalle = new ArrayList<BEWSConsCIPDetalle>();
        }
        return this.bewsConsCIPDetalle;
    }

}
