
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSConsCIPConciliado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSConsCIPConciliado">
 *   &lt;complexContent>
 *     &lt;extension base="{http://tempuri.org/}BEWSConsCIP">
 *       &lt;sequence>
 *         &lt;element name="FechaConciliacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoConciliacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BEWSConsCIPConciliado", propOrder = {
    "fechaConciliacion",
    "estadoConciliacion"
})
public class BEWSConsCIPConciliado
    extends BEWSConsCIP
{

    @XmlElement(name = "FechaConciliacion")
    protected String fechaConciliacion;
    @XmlElement(name = "EstadoConciliacion")
    protected String estadoConciliacion;

    /**
     * Obtiene el valor de la propiedad fechaConciliacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaConciliacion() {
        return fechaConciliacion;
    }

    /**
     * Define el valor de la propiedad fechaConciliacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaConciliacion(String value) {
        this.fechaConciliacion = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoConciliacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoConciliacion() {
        return estadoConciliacion;
    }

    /**
     * Define el valor de la propiedad estadoConciliacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoConciliacion(String value) {
        this.estadoConciliacion = value;
    }

}
