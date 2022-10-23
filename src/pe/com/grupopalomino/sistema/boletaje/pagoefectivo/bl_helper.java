/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.grupopalomino.sistema.boletaje.pagoefectivo;

//import be.BEGenRequest;

/**
 *
 * @author VM-DEVELOPER
 */
public class bl_helper {

    public String convertToXml(BEGenRequest paymentRequest)
    {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
            "<SolPago>" +
                "<IdMoneda>"+paymentRequest.getMoneda().trim()+"</IdMoneda>" +
                "<Total>"+paymentRequest.getMonto().trim()+"</Total>"+
                "<MetodosPago>"+paymentRequest.getMedio_pago().trim()+"</MetodosPago>" +
                "<CodServicio>"+paymentRequest.getCod_servicio().trim()+"</CodServicio>" +
                "<Codtransaccion>"+paymentRequest.getNumero_orden().trim()+"</Codtransaccion>" +
                "<ConceptoPago>"+paymentRequest.getConcepto_pago().trim()+"</ConceptoPago>" +
                "<EmailComercio>"+paymentRequest.getEmail_comercio().trim()+"</EmailComercio>" +
                "<FechaAExpirar>"+paymentRequest.getFecha_expirar().trim()+"</FechaAExpirar>" +
                "<UsuarioNombre>"+paymentRequest.getUsuario_nombre().trim()+"</UsuarioNombre>" +
                "<UsuarioApellidos>"+paymentRequest.getUsuario_apellidos().trim()+"</UsuarioApellidos>" +
                "<UsuarioEmail>"+paymentRequest.getUsuario_email().trim()+"</UsuarioEmail>" +
                "<Detalles>" +
                    "<Detalle>" +
                        "<Cod_Origen>CT</Cod_Origen>" +
                        "<TipoOrigen>TO</TipoOrigen>" +
                        "<ConceptoPago>"+paymentRequest.getConcepto_pago().trim()+"</ConceptoPago>" +
                        "<Importe>"+paymentRequest.getMonto().trim()+"</Importe>" +
                    "</Detalle>" +
                "</Detalles>" +
            "</SolPago>";
        return xml;            
    }
    public String convertToJson(BEGenRequest paymentRequest)
    {
        String json = "{" +            
                "IdMoneda:"+paymentRequest.getMoneda().trim()+"," +
                "Total:"+paymentRequest.getMonto().trim()+"," +
                "MetodosPago:"+paymentRequest.getMedio_pago().trim()+"," +
                "CodServicio:"+paymentRequest.getCod_servicio().trim()+"," +
                "Codtransaccion:"+paymentRequest.getNumero_orden().trim()+"," +
                "ConceptoPago:"+paymentRequest.getConcepto_pago().trim()+"," +
                "EmailComercio:"+paymentRequest.getEmail_comercio().trim()+"," +
                "FechaAExpirar:"+paymentRequest.getFecha_expirar().trim()+"," +
                "UsuarioNombre:"+paymentRequest.getUsuario_nombre().trim()+"," +
                "UsuarioApellidos:"+paymentRequest.getUsuario_apellidos().trim()+"," +
                "UsuarioEmail:"+paymentRequest.getUsuario_email().trim()+"," +
                "ConceptoPago:"+paymentRequest.getConcepto_pago().trim()+"," +
                "Importe:"+paymentRequest.getMonto().trim()+"," +
                "Cod_Origen:CT," +
                "TipoOrigen:TO"+                
        "}";
        return json;            
    }
}
