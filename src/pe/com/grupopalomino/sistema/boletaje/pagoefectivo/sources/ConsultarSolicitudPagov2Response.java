
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarSolicitudPagov2Result" type="{http://tempuri.org/}BEWSConsultarSolicitudResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "consultarSolicitudPagov2Result"
})
@XmlRootElement(name = "ConsultarSolicitudPagov2Response")
public class ConsultarSolicitudPagov2Response {

    @XmlElement(name = "ConsultarSolicitudPagov2Result")
    protected BEWSConsultarSolicitudResponse consultarSolicitudPagov2Result;

    /**
     * Obtiene el valor de la propiedad consultarSolicitudPagov2Result.
     * 
     * @return
     *     possible object is
     *     {@link BEWSConsultarSolicitudResponse }
     *     
     */
    public BEWSConsultarSolicitudResponse getConsultarSolicitudPagov2Result() {
        return consultarSolicitudPagov2Result;
    }

    /**
     * Define el valor de la propiedad consultarSolicitudPagov2Result.
     * 
     * @param value
     *     allowed object is
     *     {@link BEWSConsultarSolicitudResponse }
     *     
     */
    public void setConsultarSolicitudPagov2Result(BEWSConsultarSolicitudResponse value) {
        this.consultarSolicitudPagov2Result = value;
    }

}
