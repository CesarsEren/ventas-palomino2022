
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSConsultarCIPRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSConsultarCIPRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CAPI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CClave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CIPS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InfoRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BEWSConsultarCIPRequest", propOrder = {
    "capi",
    "cClave",
    "cips",
    "infoRequest"
})
public class BEWSConsultarCIPRequest {

    @XmlElement(name = "CAPI")
    protected String capi;
    @XmlElement(name = "CClave")
    protected String cClave;
    @XmlElement(name = "CIPS")
    protected String cips;
    @XmlElement(name = "InfoRequest")
    protected String infoRequest;

    /**
     * Obtiene el valor de la propiedad capi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCAPI() {
        return capi;
    }

    /**
     * Define el valor de la propiedad capi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCAPI(String value) {
        this.capi = value;
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
     * Obtiene el valor de la propiedad cips.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIPS() {
        return cips;
    }

    /**
     * Define el valor de la propiedad cips.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIPS(String value) {
        this.cips = value;
    }

    /**
     * Obtiene el valor de la propiedad infoRequest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfoRequest() {
        return infoRequest;
    }

    /**
     * Define el valor de la propiedad infoRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfoRequest(String value) {
        this.infoRequest = value;
    }

}
