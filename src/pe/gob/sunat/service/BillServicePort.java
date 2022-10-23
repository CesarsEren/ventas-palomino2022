/**
 * BillServicePort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pe.gob.sunat.service;

public interface BillServicePort extends java.rmi.Remote {
    public java.lang.String sendPack(java.lang.String fileName, byte[] contentFile) throws java.rmi.RemoteException;
    public pe.gob.sunat.service.StatusCdrResponse getStatusCdr(java.lang.String rucComprobante, java.lang.String tipoComprobante, java.lang.String serieComprobante, java.lang.String numeroComprobante) throws java.rmi.RemoteException;
    public pe.gob.sunat.service.StatusResponse getStatus(java.lang.String ticket) throws java.rmi.RemoteException;
    public byte[] sendBill(java.lang.String fileName, byte[] contentFile, java.lang.String requestId) throws java.rmi.RemoteException;
    public java.lang.String sendSummary(java.lang.String fileName, byte[] contentFile, java.lang.String requestId) throws java.rmi.RemoteException;
}
