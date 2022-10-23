
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfBEWSConsCIP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBEWSConsCIP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BEWSConsCIP" type="{http://tempuri.org/}BEWSConsCIP" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBEWSConsCIP", propOrder = {
    "bewsConsCIP"
})
public class ArrayOfBEWSConsCIP {

    @XmlElement(name = "BEWSConsCIP", nillable = true)
    protected List<BEWSConsCIP> bewsConsCIP;

    /**
     * Gets the value of the bewsConsCIP property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bewsConsCIP property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBEWSConsCIP().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BEWSConsCIP }
     * 
     * 
     */
    public List<BEWSConsCIP> getBEWSConsCIP() {
        if (bewsConsCIP == null) {
            bewsConsCIP = new ArrayList<BEWSConsCIP>();
        }
        return this.bewsConsCIP;
    }

}
