
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSConsCIPDetalle complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSConsCIPDetalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdDetalleOrdenPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cod_Origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Campo1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Campo2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Campo3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BEWSConsCIPDetalle", propOrder = {
    "idDetalleOrdenPago",
    "conceptoPago",
    "importe",
    "tipoOrigen",
    "codOrigen",
    "campo1",
    "campo2",
    "campo3"
})
public class BEWSConsCIPDetalle {

    @XmlElement(name = "IdDetalleOrdenPago")
    protected String idDetalleOrdenPago;
    @XmlElement(name = "ConceptoPago")
    protected String conceptoPago;
    @XmlElement(name = "Importe")
    protected String importe;
    @XmlElement(name = "Tipo_Origen")
    protected String tipoOrigen;
    @XmlElement(name = "Cod_Origen")
    protected String codOrigen;
    @XmlElement(name = "Campo1")
    protected String campo1;
    @XmlElement(name = "Campo2")
    protected String campo2;
    @XmlElement(name = "Campo3")
    protected String campo3;

    /**
     * Obtiene el valor de la propiedad idDetalleOrdenPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDetalleOrdenPago() {
        return idDetalleOrdenPago;
    }

    /**
     * Define el valor de la propiedad idDetalleOrdenPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDetalleOrdenPago(String value) {
        this.idDetalleOrdenPago = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptoPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptoPago() {
        return conceptoPago;
    }

    /**
     * Define el valor de la propiedad conceptoPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptoPago(String value) {
        this.conceptoPago = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImporte(String value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOrigen() {
        return tipoOrigen;
    }

    /**
     * Define el valor de la propiedad tipoOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOrigen(String value) {
        this.tipoOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrigen() {
        return codOrigen;
    }

    /**
     * Define el valor de la propiedad codOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrigen(String value) {
        this.codOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad campo1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampo1() {
        return campo1;
    }

    /**
     * Define el valor de la propiedad campo1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampo1(String value) {
        this.campo1 = value;
    }

    /**
     * Obtiene el valor de la propiedad campo2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampo2() {
        return campo2;
    }

    /**
     * Define el valor de la propiedad campo2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampo2(String value) {
        this.campo2 = value;
    }

    /**
     * Obtiene el valor de la propiedad campo3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampo3() {
        return campo3;
    }

    /**
     * Define el valor de la propiedad campo3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampo3(String value) {
        this.campo3 = value;
    }

}
