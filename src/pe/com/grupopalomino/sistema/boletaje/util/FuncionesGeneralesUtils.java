package pe.com.grupopalomino.sistema.boletaje.util;



import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasService;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.FuncionesGeneralesService;
import pe.com.grupopalomino.sistema.boletaje.service.FuncionesGeneralesServiceI;

public  class FuncionesGeneralesUtils {
	
	 static FuncionesGeneralesService service= new FuncionesGeneralesServiceI();
	 static AgenciasService serviceagencia= new AgenciasServiceI();
	
	
	public static String F_ObtenerFechaServidor() throws Exception{
		
		try{
				String FechaServer="";
				
				FechaServer=service.FechaServidor();
				return FechaServer;
		}catch(Exception ex){
			throw  ex;
		}
		
	}
	
	public static String F_Separador_Embarques(String cadena,String tipo){
		
		try{
			String resultado="";
			if( cadena!=null &&  !cadena.trim().equals("")){
				
				String separadores="-";
				String[]palabras=cadena.split(separadores);
				if(tipo=="D"){
					resultado = palabras[0];
				}
				if(tipo=="H"){
					resultado = palabras[1];
				}
				return resultado.trim();
			}else{
				return cadena;
			}
		}catch(Exception ex){
			
			throw  ex;
		}
		
	}
	
  public static String [] F_Separador_Reporte(String cadena,String separador){
		
		try{
			String [] resultado = null;
			
			if(cadena!=null && ! cadena.trim().equals("")){
				
				resultado=cadena.split(separador);
			}
			return resultado;
		}catch(Exception ex){
			
			throw  ex;
		}
		
	}
	
	public static V_AgenciasBean F_Agencias(String Codigo) throws Exception{
		 
		try {
			 
			V_AgenciasBean bean= new V_AgenciasBean();
			
			if(Codigo!=null && ! Codigo.trim().equals("")){
				bean=serviceagencia.listAgencias(Codigo);
			}else{
				bean = null;
			}
			return bean;
			
		}catch(Exception ex){
			throw ex;
			
		}
		
	}
	

}


