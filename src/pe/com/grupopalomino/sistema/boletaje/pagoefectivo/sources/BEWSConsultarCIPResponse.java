
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSConsultarCIPResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSConsultarCIPResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CIPs" type="{http://tempuri.org/}ArrayOfBEWSConsCIP" minOccurs="0"/>
 *         &lt;element name="InfoResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BEWSConsultarCIPResponse", propOrder = {
    "xml",
    "estado",
    "mensaje",
    "ciPs",
    "infoResponse"
})
public class BEWSConsultarCIPResponse {

    @XmlElement(name = "XML")
    protected String xml;
    @XmlElement(name = "Estado")
    protected String estado;
    @XmlElement(name = "Mensaje")
    protected String mensaje;
    @XmlElement(name = "CIPs")
    protected ArrayOfBEWSConsCIP ciPs;
    @XmlElement(name = "InfoResponse")
    protected String infoResponse;

    /**
     * Obtiene el valor de la propiedad xml.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXML() {
        return xml;
    }

    /**
     * Define el valor de la propiedad xml.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXML(String value) {
        this.xml = value;
    }

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
     * Obtiene el valor de la propiedad ciPs.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBEWSConsCIP }
     *     
     */
    public ArrayOfBEWSConsCIP getCIPs() {
        return ciPs;
    }

    /**
     * Define el valor de la propiedad ciPs.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBEWSConsCIP }
     *     
     */
    public void setCIPs(ArrayOfBEWSConsCIP value) {
        this.ciPs = value;
    }

    /**
     * Obtiene el valor de la propiedad infoResponse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfoResponse() {
        return infoResponse;
    }

    /**
     * Define el valor de la propiedad infoResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfoResponse(String value) {
        this.infoResponse = value;
    }

}
