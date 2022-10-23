
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BEWSConsCIP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BEWSConsCIP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdOrdenPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroOrdenPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderIdComercio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdMoneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaEmision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaCancelado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaConciliado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoConciliado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioApellidos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataAdicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Detalle" type="{http://tempuri.org/}ArrayOfBEWSConsCIPDetalle" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BEWSConsCIP", propOrder = {
    "idOrdenPago",
    "idCliente",
    "numeroOrdenPago",
    "orderIdComercio",
    "idMoneda",
    "total",
    "fechaEmision",
    "fechaCancelado",
    "fechaConciliado",
    "estadoConciliado",
    "usuarioID",
    "usuarioNombre",
    "usuarioApellidos",
    "usuarioAlias",
    "usuarioEmail",
    "dataAdicional",
    "estado",
    "detalle"
})
@XmlSeeAlso({
    BEWSConsCIPConciliado.class
})
public class BEWSConsCIP {

    @XmlElement(name = "IdOrdenPago")
    protected String idOrdenPago;
    @XmlElement(name = "IdCliente")
    protected String idCliente;
    @XmlElement(name = "NumeroOrdenPago")
    protected String numeroOrdenPago;
    @XmlElement(name = "OrderIdComercio")
    protected String orderIdComercio;
    @XmlElement(name = "IdMoneda")
    protected String idMoneda;
    @XmlElement(name = "Total")
    protected String total;
    @XmlElement(name = "FechaEmision")
    protected String fechaEmision;
    @XmlElement(name = "FechaCancelado")
    protected String fechaCancelado;
    @XmlElement(name = "FechaConciliado")
    protected String fechaConciliado;
    @XmlElement(name = "EstadoConciliado")
    protected String estadoConciliado;
    @XmlElement(name = "UsuarioID")
    protected String usuarioID;
    @XmlElement(name = "UsuarioNombre")
    protected String usuarioNombre;
    @XmlElement(name = "UsuarioApellidos")
    protected String usuarioApellidos;
    @XmlElement(name = "UsuarioAlias")
    protected String usuarioAlias;
    @XmlElement(name = "UsuarioEmail")
    protected String usuarioEmail;
    @XmlElement(name = "DataAdicional")
    protected String dataAdicional;
    @XmlElement(name = "Estado")
    protected String estado;
    @XmlElement(name = "Detalle")
    protected ArrayOfBEWSConsCIPDetalle detalle;

    /**
     * Obtiene el valor de la propiedad idOrdenPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOrdenPago() {
        return idOrdenPago;
    }

    /**
     * Define el valor de la propiedad idOrdenPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOrdenPago(String value) {
        this.idOrdenPago = value;
    }

    /**
     * Obtiene el valor de la propiedad idCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Define el valor de la propiedad idCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCliente(String value) {
        this.idCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroOrdenPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroOrdenPago() {
        return numeroOrdenPago;
    }

    /**
     * Define el valor de la propiedad numeroOrdenPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroOrdenPago(String value) {
        this.numeroOrdenPago = value;
    }

    /**
     * Obtiene el valor de la propiedad orderIdComercio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderIdComercio() {
        return orderIdComercio;
    }

    /**
     * Define el valor de la propiedad orderIdComercio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderIdComercio(String value) {
        this.orderIdComercio = value;
    }

    /**
     * Obtiene el valor de la propiedad idMoneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMoneda() {
        return idMoneda;
    }

    /**
     * Define el valor de la propiedad idMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMoneda(String value) {
        this.idMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad total.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotal() {
        return total;
    }

    /**
     * Define el valor de la propiedad total.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotal(String value) {
        this.total = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEmision.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Define el valor de la propiedad fechaEmision.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmision(String value) {
        this.fechaEmision = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCancelado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCancelado() {
        return fechaCancelado;
    }

    /**
     * Define el valor de la propiedad fechaCancelado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCancelado(String value) {
        this.fechaCancelado = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaConciliado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaConciliado() {
        return fechaConciliado;
    }

    /**
     * Define el valor de la propiedad fechaConciliado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaConciliado(String value) {
        this.fechaConciliado = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoConciliado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoConciliado() {
        return estadoConciliado;
    }

    /**
     * Define el valor de la propiedad estadoConciliado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoConciliado(String value) {
        this.estadoConciliado = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioID() {
        return usuarioID;
    }

    /**
     * Define el valor de la propiedad usuarioID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioID(String value) {
        this.usuarioID = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    /**
     * Define el valor de la propiedad usuarioNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioNombre(String value) {
        this.usuarioNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioApellidos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioApellidos() {
        return usuarioApellidos;
    }

    /**
     * Define el valor de la propiedad usuarioApellidos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioApellidos(String value) {
        this.usuarioApellidos = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioAlias.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioAlias() {
        return usuarioAlias;
    }

    /**
     * Define el valor de la propiedad usuarioAlias.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioAlias(String value) {
        this.usuarioAlias = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    /**
     * Define el valor de la propiedad usuarioEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioEmail(String value) {
        this.usuarioEmail = value;
    }

    /**
     * Obtiene el valor de la propiedad dataAdicional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataAdicional() {
        return dataAdicional;
    }

    /**
     * Define el valor de la propiedad dataAdicional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAdicional(String value) {
        this.dataAdicional = value;
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
     * Obtiene el valor de la propiedad detalle.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBEWSConsCIPDetalle }
     *     
     */
    public ArrayOfBEWSConsCIPDetalle getDetalle() {
        return detalle;
    }

    /**
     * Define el valor de la propiedad detalle.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBEWSConsCIPDetalle }
     *     
     */
    public void setDetalle(ArrayOfBEWSConsCIPDetalle value) {
        this.detalle = value;
    }

}
