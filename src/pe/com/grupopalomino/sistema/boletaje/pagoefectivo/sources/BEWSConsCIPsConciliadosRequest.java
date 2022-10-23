
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSConsCIPsConciliadosRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSConsCIPsConciliadosRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CAPI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CClave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "BEWSConsCIPsConciliadosRequest", propOrder = {
    "capi",
    "cClave",
    "fechaDesde",
    "fechaHasta",
    "tipo",
    "infoRequest"
})
public class BEWSConsCIPsConciliadosRequest {

    @XmlElement(name = "CAPI")
    protected String capi;
    @XmlElement(name = "CClave")
    protected String cClave;
    @XmlElement(name = "FechaDesde")
    protected String fechaDesde;
    @XmlElement(name = "FechaHasta")
    protected String fechaHasta;
    @XmlElement(name = "Tipo")
    protected String tipo;
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
     * Obtiene el valor de la propiedad fechaDesde.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Define el valor de la propiedad fechaDesde.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDesde(String value) {
        this.fechaDesde = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHasta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Define el valor de la propiedad fechaHasta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaHasta(String value) {
        this.fechaHasta = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
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
