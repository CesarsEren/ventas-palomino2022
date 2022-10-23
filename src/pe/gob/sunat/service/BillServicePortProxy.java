package pe.gob.sunat.service;

public class BillServicePortProxy implements pe.gob.sunat.service.BillServicePort {
  private String _endpoint = null;
  private pe.gob.sunat.service.BillServicePort billServicePort = null;
  
  public BillServicePortProxy() {
    _initBillServicePortProxy();
  }
  
  public BillServicePortProxy(String endpoint) {
    _endpoint = endpoint;
    _initBillServicePortProxy();
  }
  
  private void _initBillServicePortProxy() {
    try {
      billServicePort = (new pe.gob.sunat.service.BillServicePortServiceLocator()).getBillServicePortSoap11();
      if (billServicePort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)billServicePort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)billServicePort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (billServicePort != null)
      ((javax.xml.rpc.Stub)billServicePort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pe.gob.sunat.service.BillServicePort getBillServicePort() {
    if (billServicePort == null)
      _initBillServicePortProxy();
    return billServicePort;
  }
  
  public java.lang.String sendPack(java.lang.String fileName, byte[] contentFile) throws java.rmi.RemoteException{
    if (billServicePort == null)
      _initBillServicePortProxy();
    return billServicePort.sendPack(fileName, contentFile);
  }
  
  public pe.gob.sunat.service.StatusCdrResponse getStatusCdr(java.lang.String rucComprobante, java.lang.String tipoComprobante, java.lang.String serieComprobante, java.lang.String numeroComprobante) throws java.rmi.RemoteException{
    if (billServicePort == null)
      _initBillServicePortProxy();
    return billServicePort.getStatusCdr(rucComprobante, tipoComprobante, serieComprobante, numeroComprobante);
  }
  
  public pe.gob.sunat.service.StatusResponse getStatus(java.lang.String ticket) throws java.rmi.RemoteException{
    if (billServicePort == null)
      _initBillServicePortProxy();
    return billServicePort.getStatus(ticket);
  }
  
  public byte[] sendBill(java.lang.String fileName, byte[] contentFile, java.lang.String requestId) throws java.rmi.RemoteException{
    if (billServicePort == null)
      _initBillServicePortProxy();
    return billServicePort.sendBill(fileName, contentFile, requestId);
  }
  
  public java.lang.String sendSummary(java.lang.String fileName, byte[] contentFile, java.lang.String requestId) throws java.rmi.RemoteException{
    if (billServicePort == null)
      _initBillServicePortProxy();
    return billServicePort.sendSummary(fileName, contentFile, requestId);
  }
  
  
}