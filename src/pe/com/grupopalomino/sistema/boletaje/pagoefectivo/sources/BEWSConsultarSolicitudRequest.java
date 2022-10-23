
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSConsultarSolicitudRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSConsultarSolicitudRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cServ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CClave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BEWSConsultarSolicitudRequest", propOrder = {
    "cServ",
    "cClave",
    "xml"
})
public class BEWSConsultarSolicitudRequest {

    protected String cServ;
    @XmlElement(name = "CClave")
    protected String cClave;
    @XmlElement(name = "Xml")
    protected String xml;

    /**
     * Obtiene el valor de la propiedad cServ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCServ() {
        return cServ;
    }

    /**
     * Define el valor de la propiedad cServ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCServ(String value) {
        this.cServ = value;
    }

    /**
     * Obtiene el valor de la propiedad cClave.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClave() {
        return cClave;
    }

    /**
     * Define el valor de la propiedad cClave.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCClave(String value) {
        this.cClave = value;
    }

    /**
     * Obtiene el valor de la propiedad xml.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXml() {
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
    public void setXml(String value) {
        this.xml = value;
    }

}
