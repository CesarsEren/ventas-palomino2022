package pe.com.grupopalomino.sistema.boletaje.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionServiceI;



public class ActualizaImagesQRThread implements Runnable{
	
	private Integer nro;
	private String servicio;
	private String codehash;
	private byte[] imagesBarra;
	private byte[] imagesQR;
	private V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI();
	
	private static final Log log = LogFactory.getLog(ActualizaImagesQRThread.class);
	
	
	public ActualizaImagesQRThread(Integer nro, String servicio,String codehash, byte[] imagesBarra,byte[] imagesQR) {
		
		this.nro = nro;
		this.servicio = servicio;
		this.codehash = codehash;
		this.imagesBarra = imagesBarra;
		this.imagesQR = imagesBarra;
	}
	

	@Override
	public void run() {
		
		try {
			
			System.out.println("################################## COMENZANDO THREAD DE LA IMAGEN --> NRO :"+ nro + " SERVICIO :"+ servicio +" ##################################");
			
			ventasfacturacionservice.SQL_UPDATE_VENTAS_FACTURACION_IMAGES(nro, servicio, codehash, imagesBarra, imagesQR);
			
			System.out.println("################################## TERMINANDO THREAD DE LA IMAGEN --> NRO :"+ nro + " SERVICIO :"+ servicio +" ##################################");
			
		} catch (Exception e) {
			log.info("ERROR EN EL TRY DEL THREAD --> "+ e.getMessage());
		}
		
		
	}
	
	public void setNro(Integer nro) {
		this.nro = nro;
	}
	public Integer getNro() {
		return nro;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getServicio() {
		return servicio;
	}
	public void setCodehash(String codehash) {
		this.codehash = codehash;
	}
	public String getCodehash() {
		return codehash;
	}
	public void setImagesBarra(byte[] imagesBarra) {
		this.imagesBarra = imagesBarra;
	}
	public byte[] getImagesBarra() {
		return imagesBarra;
	}
	public void setImagesQR(byte[] imagesQR) {
		this.imagesQR = imagesQR;
	}
	public byte[] getImagesQR() {
		return imagesQR;
	}

}
