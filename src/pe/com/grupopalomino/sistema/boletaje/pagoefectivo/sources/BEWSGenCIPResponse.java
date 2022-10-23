
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSGenCIPResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSGenCIPResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InformacionCIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BEWSGenCIPResponse", propOrder = {
    "estado",
    "cip",
    "mensaje",
    "informacionCIP"
})
public class BEWSGenCIPResponse {

    @XmlElement(name = "Estado")
    protected String estado;
    @XmlElement(name = "CIP")
    protected String cip;
    @XmlElement(name = "Mensaje")
    protected String mensaje;
    @XmlElement(name = "InformacionCIP")
    protected String informacionCIP;

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad cip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIP() {
        return cip;
    }

    /**
     * Define el valor de la propiedad cip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIP(String value) {
        this.cip = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad informacionCIP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformacionCIP() {
        return informacionCIP;
    }

    /**
     * Define el valor de la propiedad informacionCIP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformacionCIP(String value) {
        this.informacionCIP = value;
    }

}
