
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfBEWSConsCIPConciliado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBEWSConsCIPConciliado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BEWSConsCIPConciliado" type="{http://tempuri.org/}BEWSConsCIPConciliado" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBEWSConsCIPConciliado", propOrder = {
    "bewsConsCIPConciliado"
})
public class ArrayOfBEWSConsCIPConciliado {

    @XmlElement(name = "BEWSConsCIPConciliado", nillable = true)
    protected List<BEWSConsCIPConciliado> bewsConsCIPConciliado;

    /**
     * Gets the value of the bewsConsCIPConciliado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bewsConsCIPConciliado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBEWSConsCIPConciliado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BEWSConsCIPConciliado }
     * 
     * 
     */
    public List<BEWSConsCIPConciliado> getBEWSConsCIPConciliado() {
        if (bewsConsCIPConciliado == null) {
            bewsConsCIPConciliado = new ArrayList<BEWSConsCIPConciliado>();
        }
        return this.bewsConsCIPConciliado;
    }

}
