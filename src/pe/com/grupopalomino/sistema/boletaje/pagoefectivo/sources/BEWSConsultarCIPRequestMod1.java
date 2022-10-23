
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSConsultarCIPRequestMod1 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSConsultarCIPRequestMod1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodServ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Firma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "BEWSConsultarCIPRequestMod1", propOrder = {
    "codServ",
    "firma",
    "cips",
    "infoRequest"
})
public class BEWSConsultarCIPRequestMod1 {

    @XmlElement(name = "CodServ")
    protected String codServ;
    @XmlElement(name = "Firma")
    protected String firma;
    @XmlElement(name = "CIPS")
    protected String cips;
    @XmlElement(name = "InfoRequest")
    protected String infoRequest;

    /**
     * Obtiene el valor de la propiedad codServ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodServ() {
        return codServ;
    }

    /**
     * Define el valor de la propiedad codServ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodServ(String value) {
        this.codServ = value;
    }

    /**
     * Obtiene el valor de la propiedad firma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirma() {
        return firma;
    }

    /**
     * Define el valor de la propiedad firma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirma(String value) {
        this.firma = value;
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
