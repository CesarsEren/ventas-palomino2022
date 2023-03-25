package pe.com.grupopalomino.sistema.boletaje.transaction.interfaces;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_PrecioProgramacion;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.ListaPreVenta;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ClientesBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Clientes_RutaPrecioBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CroquisBusBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_PasajerosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Promo_RetornoCBean;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso2Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso3Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso4Form;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasService;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesRutaPrecioService;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesRutaPrecioServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesService;
import pe.com.grupopalomino.sistema.boletaje.service.ClientesServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.DestinoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosService;
import pe.com.grupopalomino.sistema.boletaje.service.PasajerosService;
import pe.com.grupopalomino.sistema.boletaje.service.PasajerosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaService;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Promo_RetornoCService;
import pe.com.grupopalomino.sistema.boletaje.service.Promo_RetornoCServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;


public class VentaInterfaceI implements VentaInterface{ 

	
	@Override
	public boolean GeneraDatosClienteVenta(VentaPaso3Form  paso3Form) throws Exception {
		
		V_ClientesBean client = null;
		ClientesService service = new ClientesServiceI();
		int operacion= -1;
		boolean respuesta = true;
				
			if(paso3Form.getRuc() != null){
				client = service.listClientesRUC(paso3Form.getRuc().trim());
				
				if(client == null){
					client = new V_ClientesBean();
					client.setRuc(paso3Form.getRuc());
					client.setRazon(paso3Form.getRazonSocial().toUpperCase());
					client.setDireccion(".");
					client.setCredito(null);
					client.setResponsable(null);
					client.setCorreo(null);
					client.setTelefono(null);
					client.setTipo(null);
					
					operacion =	service.InertClientes(client);
					
					if(operacion == -1){
						
						respuesta = false;
					}
				}
			}
		
		return respuesta;
	}

	@Override
	public boolean GeneraDatosPasajeroventa(VentaPaso3Form  paso3Form) throws Exception {
		V_PasajerosBean pasajero = null;
		PasajerosService service = new PasajerosServiceI();
		int operacion= -1;
		boolean respuesta = true;
		
			pasajero = service.listPasajerosDNI(paso3Form.getNumeroDocumentoIdentidad().trim(),paso3Form.getComboIdentidad().substring(0,1));
				
				if(pasajero == null){
					
					//int tamanio = paso3Form.getComboIdentidad().length();
					
					pasajero= new V_PasajerosBean();
					pasajero.setCodigo(paso3Form.getComboIdentidad().substring(0,1));
					pasajero.setDNI(paso3Form.getNumeroDocumentoIdentidad());
					pasajero.setNombre(paso3Form.getNombrePasajero().toUpperCase());
					pasajero.setEdad(paso3Form.getEdad());
					pasajero.setTelefono(paso3Form.getTelefono());
					pasajero.setKilometraje(0);
					pasajero.setKilometrajeAct(0);
					
					operacion =	service.InsertPasajeros(pasajero);
					
					if(operacion == -1){
						
						respuesta = false;
					}
				}
		
		return respuesta;
	}

	@Override
	public B_VentaBean DatosEstaticosVenta(B_VentaBean venta,VentaPaso3Form paso3Form,SpringSecurityUser usuario) throws Exception {
		
		AgenciasService agencia = new AgenciasServiceI();
		V_AgenciasBean agenciaBean= new V_AgenciasBean();
		
		venta.setEmpresa("999");
		venta.setEmpresaD("Empresa Pendiente");
		venta.setSerie("000");
		venta.setNumero("0000000");
		venta.setTipo("A");
		venta.setTipoD("Agencia Viaje");
		venta.setRetorno("0");
		venta.setAutorizo("");
		venta.setCodigo(0);
		venta.setPrecio(0);
		venta.setOtro("");
		venta.setIntermedio("");
		venta.setComida("01");		
		venta.setEstadoWeb(B_VentaBean.ESTADO_RESERVADO); 
		
		int tamanio=paso3Form.getComboIdentidad().length();
		
		if(usuario!=null){
			venta.setUsuario(usuario.getUsername().trim().toUpperCase() /*usuario.getUsuario().trim().toUpperCase()*/);
			venta.setComentario("VENTA-INTERNET "+"9999 "+(usuario.getUsername()).trim().toUpperCase());
		}else{
			venta.setUsuario("WEB-TC");
			venta.setUsuarioVisa("WEB-TC"); 
			venta.setComentario("VENTA-INTERNET-VISA "+"9999");
		}
		
		
		agenciaBean=agencia.listAgencias("000");
		 
		if(agenciaBean!=null){
			venta.setTerminal("9999");
			venta.setAgencia(agenciaBean.getCodigo());
			venta.setAgenciaD(agenciaBean.getDetalle().trim().toUpperCase());
			
		}
		
		venta.setIdentidad(paso3Form.getComboIdentidad().substring(0, 1));
		venta.setIdentidadD(paso3Form.getComboIdentidad().substring(2, tamanio));
		venta.setDNI(paso3Form.getNumeroDocumentoIdentidad());
		venta.setEdad(paso3Form.getEdad());
		venta.setTelefono(paso3Form.getTelefono());
		venta.setRuc((paso3Form.getRuc()== null ? "" : paso3Form.getRuc()));
		venta.setRazon(paso3Form.getRazonSocial() == null ? "" : paso3Form.getRazonSocial().trim().toUpperCase());
		venta.setNombre(paso3Form.getNombrePasajero().trim().toUpperCase());
		
		return venta;
	}

	
	@Override
	public B_VentaBean DatosDinamicosVenta(B_VentaBean venta, B_Correlativos correlativo, String FechaEmision,
			VentaPaso2Form paso2Form,VentaPaso3Form paso3Form,List<V_CroquisBusBean> listaCroquisBus,int minValor,SpringSecurityUser usuario, List<B_PrecioProgramacion> b_PrecioProgramacions) throws Exception {
		
		
		V_DestinosBean destino = new V_DestinosBean();
		DestinosService servicedestino = new DestinoServiceI();
		B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
		ProgramacionSalidaService serviceprogramacion = new ProgramacionSalidaServiceI();
		
		
		//DATOS DEL EMBARQUE
		
		String [] valorEmbarque = paso3Form.getComboEmbarque().split("-");
		
		//DATOS DEL DESTINO DE BAJADA
		
		String [] valorDestinoBajada = paso3Form.getComboDestinoBajada().split(",");
		
		
		venta.setNro(correlativo.getCorrelativoBol());
		venta.setSalida(paso2Form.getNroProgramacion());
		venta.setFechaEmision(FechaEmision);
		
		//DESTINOS
		destino = servicedestino.getDestinoNro(Integer.parseInt(paso2Form.getNroDestino()));
		
		if(destino != null){
			
			venta.setDestino(destino.getNro());
			venta.setDestinoD(destino.getOrigenD().trim()+"-"+destino.getDestinoD().trim());
			
		}
		
		//PROGRAMACION DE SALIDAS
		programacion = serviceprogramacion.getSalidasPrecioPromocion(paso2Form.getNroProgramacion(),"",0);
		
		V_Clientes_RutaPrecioBean beanclienteRuta = null;
		if(programacion != null){
			
			venta.setFechaViaje(programacion.getFecha());
			venta.setHoraViajeIni(programacion.getHora1());
			
			if(usuario!= null){
				
				ClientesRutaPrecioService serviceclienteruta = new ClientesRutaPrecioServiceI();
				
				beanclienteRuta = serviceclienteruta.listaClientesRutasVerificacion(usuario.getRuc(), programacion.getDestino(), programacion.getServicio());
				
			}
			
			
		//SETEAMOS EL PRECIO DESDE LA BASE DE DATOS
			
			for (V_CroquisBusBean croquisbus : listaCroquisBus) { 
				if(minValor>=1920){
					
					if(beanclienteRuta != null){
						
						venta.setPrecioAct((beanclienteRuta.getPrecio1())); 
						
					}else{
						venta.setPrecioAct((programacion.getPrecio1())); 
					}
					
					break;
				}else{
					if(paso3Form.getNumeroAsiento().trim().equals(croquisbus.getAsiento().trim())){
						if(croquisbus.getTTop()<1920){
							
							if(beanclienteRuta != null){
								venta.setPrecioAct(beanclienteRuta.getPrecio1());
								
							}else{
								venta.setPrecioAct(programacion.getPrecio1());
							}
							
							break;
						}else{
							if(beanclienteRuta != null){
								
								venta.setPrecioAct(beanclienteRuta.getPrecio2());
							}else{
								venta.setPrecioAct(programacion.getPrecio2());	
							}
							
							break;
						}
					}
				}
			}
			if(b_PrecioProgramacions != null) {
				if(!b_PrecioProgramacions.isEmpty()) {
					System.out.print("B_programacionPrecio vacío? "+b_PrecioProgramacions.isEmpty());
					
				for(B_PrecioProgramacion b_PrecioProgramacion: b_PrecioProgramacions)
				{
					if(paso3Form.getNumeroAsiento().trim().equals(b_PrecioProgramacion.getAsiento().toString().trim())) {
						venta.setPrecioAct(b_PrecioProgramacion.getPrecio());
					}
				}
				}
			} 
			
		}
		
		
		venta.setPrecio(venta.getPrecioAct()); 
		// DESTINOS DE BAJADA
		
		venta.setDestino1(valorDestinoBajada[0].trim());
		venta.setDestino1D(valorDestinoBajada[1].trim().toUpperCase());
		
		//EMBARQUES
		
		venta.setOrigen(valorEmbarque[0].trim());
		venta.setOrigenD(valorEmbarque[1].trim().toUpperCase());
		venta.setHoraViaje(valorEmbarque[2].trim());
		
		
		// NUMERO DE ASIENTO
		
		venta.setAsiento(paso3Form.getNumeroAsiento()); 
		
		return venta;
	}

	@Override
	public B_CuentaCorrienteBean DatosEstaticosCuentaCorriente(B_CuentaCorrienteBean cuentacorriente) throws Exception {
		
		AgenciasService agencia = new AgenciasServiceI();
		
		
		cuentacorriente.setEmpresa("999");
		cuentacorriente.setEmpresaD("Empresa Pendiente");
		cuentacorriente.setDocumento("16");
		cuentacorriente.setDocumentoD("Boleto de Viaje");
		cuentacorriente.setSerie("000");
		cuentacorriente.setNumero("0000000");
		cuentacorriente.setIdDoc("FA");
		cuentacorriente.setPago(0);
		cuentacorriente.setTerminal("9999");
		cuentacorriente.setChLeTr("");
		cuentacorriente.setEntregado("N");
		cuentacorriente.setFechaEntrega(null);
		cuentacorriente.setVoucher("");
		cuentacorriente.setEstadoWeb(B_CuentaCorrienteBean.ESTADO_CONFIRMADO); 
		
		V_AgenciasBean agenciaBean= new V_AgenciasBean();
		agenciaBean = agencia.listAgencias("000");
		
		if(agenciaBean != null){
			cuentacorriente.setAgencia(agenciaBean.getCodigo());
			cuentacorriente.setAgenciaD(agenciaBean.getDetalle().trim().toUpperCase());
		}
		
		return cuentacorriente;
	}

	@Override
	public B_CuentaCorrienteBean DatosDinamicosCuentaCorriente(ListaPreVenta venta, /*B_VentaBean venta,*/B_CuentaCorrienteBean cuentacorrienteBean,/*B_Correlativos correlativo,*/VentaPaso1Form paso1Form,VentaPaso2Form paso2Form,SpringSecurityUser usuario,String IdaVuelta,String EticketVisa,String userCliente) throws Exception {
		
		
		V_DestinosBean destino = new V_DestinosBean();
		B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
		
		
		cuentacorrienteBean.setNro(venta.getNroCC() /*correlativo.getCorrelativoCtaCte()*/);
		cuentacorrienteBean.setNroDoc(venta.getNroDocCC()/*correlativo.getCorrelativoEnc()*/);
		cuentacorrienteBean.setNroAplic(venta.getNroAplic()/*correlativo.getCorrelativoEnc()*/);
		cuentacorrienteBean.setNroBol(venta.getNro()/*correlativo.getCorrelativoBol()*/);
		cuentacorrienteBean.setFechaEmision(venta.getFechaEmision()/*FechaEmision*/);
		
		if(usuario != null){
			cuentacorrienteBean.setRuc(usuario.getRuc());
			cuentacorrienteBean.setRazon(usuario.getRazonSocial().trim().toUpperCase());
			cuentacorrienteBean.setUsuario(usuario.getUsername().trim().toUpperCase());
		}else{
			cuentacorrienteBean.setUsuario("WEB-TC");
			cuentacorrienteBean.setUsuarioVisa(userCliente);
			cuentacorrienteBean.setEticketVisa(EticketVisa); 
			cuentacorrienteBean.setRuc("20341198217");
			cuentacorrienteBean.setRazon("VISANET - CIA PERUANA DE MEDIOS DE PAGO SAC");
			
		} 
		
		//DESTINOS
		
		cuentacorrienteBean.setDestino((IdaVuelta == "Y")? String.valueOf(paso1Form.getNroDestinoVUELTA()):  String.valueOf(paso1Form.getNroDestinoIDA()));
		destino =  ObtenerDatosDestinos((IdaVuelta == "Y")? paso1Form.getNroDestinoVUELTA(): paso1Form.getNroDestinoIDA());
		
		if(destino !=null){
			
			cuentacorrienteBean.setDestinoD(destino.getOrigenD().trim().toUpperCase()+"-"+destino.getDestinoD().trim().toUpperCase());
		}
		
		
		//PROGRAMACION DE SALIDAS
		
		programacion = ObtenerProgramacion(paso2Form.getNroProgramacion());
		
		if(programacion !=null){
			
			cuentacorrienteBean.setServicio(programacion.getServicio());
			cuentacorrienteBean.setServicioD(programacion.getServicioD().trim().toUpperCase());
		}
		//GRABAMOS EL PRECIO  GENERADO EN LA TRANSACCION DE LA VENTA
		
		cuentacorrienteBean.setReferencia(venta.getNombre().trim().toUpperCase()/*venta.getNombre()*/);
		cuentacorrienteBean.setMonto(venta.getPrecioAct()/*venta.getPrecioAct()*/);
		
		return cuentacorrienteBean;
	}

	@Override
	public V_DestinosBean ObtenerDatosDestinos(int nroDestino) throws Exception {
		DestinosService service = new DestinoServiceI();
		V_DestinosBean destino = new V_DestinosBean();
		
		destino = service.getDestinoNro(nroDestino);
		return destino;
	}

	@Override
	public B_ProgramacionSalidaBean ObtenerProgramacion(int nroProgramacion) throws Exception {
		// TODO Auto-generated method stub
		B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
		ProgramacionSalidaService service = new ProgramacionSalidaServiceI();
		
		programacion = service.getSalidasNro(nroProgramacion);
		
		return programacion;
	}

	@Override
	public boolean GeneraDatosClienteVentaVuelta(VentaPaso4Form paso4Form) throws Exception {
		V_ClientesBean client = null;
		ClientesService service = new ClientesServiceI();
		int operacion= -1;
		boolean respuesta = true;
				
			if(paso4Form.getRuc()!=null){
					client = service.listClientesRUC(paso4Form.getRuc().trim());
				
				if(client == null){
					client = new V_ClientesBean();
					client.setRuc(paso4Form.getRuc());
					client.setRazon(paso4Form.getRazonSocial().trim().toUpperCase());
					client.setDireccion(".");
					client.setCredito(null);
					client.setResponsable(null);
					client.setCorreo(null);
					client.setTelefono(null);
					client.setTipo(null);
					
					operacion =	service.InertClientes(client);
					
					if(operacion == -1){
						
						respuesta = false;
					}
				}
			}
		return respuesta;
	}

	@Override
	public boolean GeneraDatosPasajeroventaVuelta(VentaPaso4Form paso4Form) throws Exception {
		V_PasajerosBean pasajero = null;
		PasajerosService service = new PasajerosServiceI();
		int operacion= -1;
		boolean respuesta = true;
		
			pasajero = service.listPasajerosDNI(paso4Form.getNumeroDocumentoIdentidad().trim(),paso4Form.getComboIdentidad().substring(0,1));
				
				if(pasajero == null){
					
					pasajero= new V_PasajerosBean();
					pasajero.setCodigo(paso4Form.getComboIdentidad().substring(0,1));
					pasajero.setDNI(paso4Form.getNumeroDocumentoIdentidad());
					pasajero.setNombre(paso4Form.getNombrePasajero().trim().toUpperCase());
					pasajero.setEdad(paso4Form.getEdad());
					pasajero.setTelefono(paso4Form.getTelefono());
					pasajero.setKilometraje(0);
					pasajero.setKilometrajeAct(0);
					
					operacion =	service.InsertPasajeros(pasajero);
					
					if(operacion == -1){
						
						respuesta = false;
					}
				}
			
		return respuesta;
	}

	@Override
	public B_VentaBean DatosEstaticosVentaVuelta(B_VentaBean venta, VentaPaso4Form paso4Form,
			SpringSecurityUser usuario) throws Exception {
		
		AgenciasService agencia = new AgenciasServiceI();
		V_AgenciasBean agenciaBean= new V_AgenciasBean();
		
		venta.setEmpresa("999");
		venta.setEmpresaD("Empresa Pendiente");
		venta.setSerie("000");
		venta.setNumero("0000000");
		venta.setTipo("A");
		venta.setTipoD("Agencia Viaje");
		venta.setRetorno("0");
		venta.setAutorizo("");
		venta.setCodigo(0);
		venta.setPrecio(0);
		venta.setOtro("");
		venta.setIntermedio("");
		venta.setComida("01");
		venta.setEstadoWeb(B_VentaBean.ESTADO_RESERVADO);
		
		int tamanio=paso4Form.getComboIdentidad().length();
		
		if(usuario!=null){
			venta.setUsuario(usuario.getUsername().trim().toUpperCase());
			venta.setComentario("VENTA-INTERNET "+"9999 "+(usuario.getUsername()).trim().toUpperCase());
		}else{
			venta.setUsuarioVisa("WEB-TC"); 
			venta.setUsuario("WEB-TC");
			venta.setComentario("VENTA-INTERNET-VISA "+"9999");
		}
		
		
		agenciaBean=agencia.listAgencias("000");
		 
		if(agenciaBean!=null){
			venta.setTerminal("9999");
			venta.setAgencia(agenciaBean.getCodigo());
			venta.setAgenciaD(agenciaBean.getDetalle().trim().toUpperCase());
			
		}
		
		venta.setIdentidad(paso4Form.getComboIdentidad().substring(0, 1));
		venta.setIdentidadD(paso4Form.getComboIdentidad().substring(2, tamanio));
		venta.setDNI(paso4Form.getNumeroDocumentoIdentidad());
		venta.setEdad(paso4Form.getEdad());
		venta.setTelefono(paso4Form.getTelefono());
		venta.setRuc(paso4Form.getRuc()== null ? "" : paso4Form.getRuc());
		venta.setRazon(paso4Form.getRazonSocial()== null ? "" :paso4Form.getRazonSocial().trim().toUpperCase());
		venta.setNombre(paso4Form.getNombrePasajero().trim().toUpperCase());
		
		return venta;
	}

	@Override
	public B_VentaBean DatosDinamicosVentaVuelta(B_VentaBean venta, B_Correlativos correlativo, String FechaEmision,
			VentaPaso2Form paso2Form, VentaPaso4Form paso4Form, List<V_CroquisBusBean> listaCroquisBus, int minValor,SpringSecurityUser usuario,
			List<B_PrecioProgramacion> b_PrecioProgramacions)
			throws Exception {
		
		
		V_DestinosBean destino = new V_DestinosBean();
		DestinosService servicedestino = new DestinoServiceI();
		B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
		ProgramacionSalidaService serviceprogramacion = new ProgramacionSalidaServiceI();
		Promo_RetornoCService servicepromociones = new Promo_RetornoCServiceI();
		
		
		//DATOS DEL EMBARQUE
		
		String [] valorEmbarque = paso4Form.getComboEmbarque().split("-");
		
		//DATOS DEL DESTINO DE BAJADA
		
		String [] valorDestinoBajada = paso4Form.getComboDestinoBajada().split(",");
		
		
		venta.setNro(correlativo.getCorrelativoBol());
		venta.setSalida(paso2Form.getNroProgramacion());
		venta.setFechaEmision(FechaEmision);
		
		//DESTINOS
		destino = servicedestino.getDestinoNro(Integer.parseInt(paso2Form.getNroDestino()));
		
		if(destino != null){
			
			venta.setDestino(destino.getNro());
			venta.setDestinoD(destino.getOrigenD().trim().toUpperCase()+"-"+destino.getDestinoD().trim().toUpperCase());
			
		}
		
		// AGREGACION PARA LAS PROMOCIONES
		V_Promo_RetornoCBean bean =  servicepromociones.SQL_SELECT_TIPO_PROMOCION_RETORNO();
		
		venta.setPromocionWeb((bean == null ? "0" : String.valueOf(bean.getWeb())));
		
		//PROGRAMACION DE SALIDAS
		programacion = serviceprogramacion.getSalidasPrecioPromocion(paso2Form.getNroProgramacion(),destino.getDestino(),(bean == null ? 0 : bean.getWeb()));
		V_Clientes_RutaPrecioBean beanclienteRuta = null;
		
		if(programacion != null){
			
			venta.setFechaViaje(programacion.getFecha());
			venta.setHoraViajeIni(programacion.getHora1());
			
			if(usuario!= null){
				
				ClientesRutaPrecioService serviceclienteruta = new ClientesRutaPrecioServiceI();
				
				beanclienteRuta = serviceclienteruta.listaClientesRutasVerificacion(usuario.getRuc(), programacion.getDestino(), programacion.getServicio());
				
			}
			
		//SETEAMOS EL PRECIO DESDE LA BASE DE DATOS
			
			for (V_CroquisBusBean croquisbus : listaCroquisBus) { 
					if(minValor>=1920){
						
						if(beanclienteRuta!=null){
							
							venta.setPrecioAct((beanclienteRuta.getPrecio1()));
						}else{
							
							venta.setPrecioAct((programacion.getPrecio1()));	
						}
						 
						break;
					}else{
						if(paso4Form.getNumeroAsiento().trim().equals(croquisbus.getAsiento().trim())){
							if(croquisbus.getTTop()<1920){
								
								if(beanclienteRuta!=null){
									
									venta.setPrecioAct(beanclienteRuta.getPrecio1());	
								}else{
									venta.setPrecioAct(programacion.getPrecio1());
									
								}
								break;
							}else{
								if(beanclienteRuta!=null){
									
									venta.setPrecioAct(beanclienteRuta.getPrecio2());	
									
								}else{
									venta.setPrecioAct(programacion.getPrecio2());	
								}
								
								break;
								
							}
						}
					}
				}
			
			 
			if(b_PrecioProgramacions != null) {
				if(!b_PrecioProgramacions.isEmpty()) {
					System.out.print("B_programacionPrecio vacío? "+b_PrecioProgramacions.isEmpty());
					
				for(B_PrecioProgramacion b_PrecioProgramacion: b_PrecioProgramacions)
				{
					if(paso4Form.getNumeroAsiento().trim().equals(b_PrecioProgramacion.getAsiento().toString().trim())) {
						venta.setPrecioAct(b_PrecioProgramacion.getPrecio());
					}
				}
				}
			} 
			}
		
		venta.setPrecio(venta.getPrecioAct());
		// DESTINOS DE BAJADA
		
		venta.setDestino1(valorDestinoBajada[0].trim());
		venta.setDestino1D(valorDestinoBajada[1].trim().toUpperCase());
		
		//EMBARQUES
		
		venta.setOrigen(valorEmbarque[0].trim().trim());
		venta.setOrigenD(valorEmbarque[1].trim().toUpperCase());
		venta.setHoraViaje(valorEmbarque[2].trim());
		
		
		// NUMERO DE ASIENTO
		
		venta.setAsiento(paso4Form.getNumeroAsiento()); 
		
		// TODO Auto-generated method stub
		
		
		return venta;
	}

	@Override
	public B_CuentaCorrienteBean DatosDinamicosCuentaCorrienteVuelta(ListaPreVenta venta,/*B_VentaBean venta,*/
			B_CuentaCorrienteBean cuentacorrienteBean,/* B_Correlativos correlativo, */VentaPaso1Form paso1Form,
			VentaPaso2Form paso2Form, SpringSecurityUser usuario,
			String IdaVuelta,String EticketVisa,String userCliente) throws Exception {
		
		
		V_DestinosBean destino = new V_DestinosBean();
		B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
		
		
		cuentacorrienteBean.setNro(venta.getNroCC()/*correlativo.getCorrelativoCtaCte()*/);
		cuentacorrienteBean.setNroDoc(venta.getNroDocCC()/*correlativo.getCorrelativoEnc()*/);
		cuentacorrienteBean.setNroAplic(venta.getNroAplic()/*correlativo.getCorrelativoEnc()*/);
		cuentacorrienteBean.setNroBol(venta.getNro()/*correlativo.getCorrelativoBol()*/);
		cuentacorrienteBean.setFechaEmision(venta.getFechaEmision()/*FechaEmision*/);
		
		if(usuario != null){
			cuentacorrienteBean.setRuc(usuario.getRuc());
			cuentacorrienteBean.setRazon(usuario.getRazonSocial().trim().toUpperCase());
			cuentacorrienteBean.setUsuario(usuario.getUsername().trim().toUpperCase());
		}else{
			cuentacorrienteBean.setUsuario("WEB-TC"); 
			cuentacorrienteBean.setUsuarioVisa(userCliente);
			cuentacorrienteBean.setEticketVisa(EticketVisa); 
			cuentacorrienteBean.setRuc("20341198217");
			cuentacorrienteBean.setRazon("VISANET - CIA PERUANA DE MEDIOS DE PAGO SAC");
			
		}
		
		//DESTINOS
		
		cuentacorrienteBean.setDestino((IdaVuelta == "Y")? String.valueOf(paso1Form.getNroDestinoVUELTA()):  String.valueOf(paso1Form.getNroDestinoIDA()));
		destino =  ObtenerDatosDestinos((IdaVuelta == "Y")? paso1Form.getNroDestinoVUELTA(): paso1Form.getNroDestinoIDA());
		
		if(destino !=null){
			
			cuentacorrienteBean.setDestinoD(destino.getOrigenD().trim().toUpperCase()+"-"+destino.getDestinoD().trim().toUpperCase());
		}
		
		
		//PROGRAMACION DE SALIDAS
		
		programacion = ObtenerProgramacion(paso2Form.getNroProgramacion());
		
		if(programacion !=null){
			cuentacorrienteBean.setServicio(programacion.getServicio());
			cuentacorrienteBean.setServicioD(programacion.getServicioD().trim().toUpperCase());
		}
		//GRABAMOS EL PRECIO  GENERADO EN LA TRANSACCION DE LA VENTA
		
		cuentacorrienteBean.setReferencia(venta.getNombre().trim().toUpperCase()/*venta.getNombre()*/);
		cuentacorrienteBean.setMonto(venta.getPrecioAct()/*venta.getPrecioAct()*/);
		
		return cuentacorrienteBean;
	}

	@Override
	public boolean VerificaDisponibilidadAsientoVuelta(VentaPaso2Form paso2Form, VentaPaso4Form paso4Form)
			throws Exception {
		VentaBoletaService serviceboletaje = new VentaBoletaServiceI();
		boolean Disponible = true;
		
		
		B_VentaBean ventaAuxiliar = serviceboletaje.getVentaNroAsientoOcupado(paso2Form.getNroProgramacion(), paso4Form.getNumeroAsiento());
	
		if(ventaAuxiliar != null){
			Disponible = false;
		}
		
		return Disponible;
	}
	
	@Override
	public boolean VerificaDisponibilidadAsientoIda(VentaPaso2Form paso2Form, VentaPaso3Form paso3Form) throws Exception {
		
		VentaBoletaService serviceboletaje = new VentaBoletaServiceI();
		boolean Disponible = true;
		
		
		B_VentaBean ventaAuxiliar = serviceboletaje.getVentaNroAsientoOcupado(paso2Form.getNroProgramacion(), paso3Form.getNumeroAsiento());
	
		if(ventaAuxiliar != null){
			Disponible = false;
		}
		
		return Disponible;
	}

	@Override
	public double VerificaPrecioActual(List<V_CroquisBusBean> listaCroquisBus,VentaPaso2Form paso2Form, VentaPaso3Form paso3Form, int minValor,SpringSecurityUser usuario)
			throws Exception {
		//PROGRAMACION DE SALIDAS
		
		B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
		ProgramacionSalidaService serviceprogramacion = new ProgramacionSalidaServiceI();
		double PrecioActual = 0.0;
		double Auxiliar = 0.0;
				programacion = serviceprogramacion.getSalidasPrecioPromocion(paso2Form.getNroProgramacion(),"",0);
				
				if(programacion != null){
					
				//OBTENEMOS EL PRECIO DESDE LA BASE DE DATOS
					
					V_Clientes_RutaPrecioBean beanclienteRuta = null;
					ClientesRutaPrecioService serviceclienteruta = new ClientesRutaPrecioServiceI();
					beanclienteRuta = serviceclienteruta.listaClientesRutasVerificacion(usuario.getRuc(), programacion.getDestino(), programacion.getServicio());
					
					for (V_CroquisBusBean croquisbus : listaCroquisBus) { 
						if(minValor>=1920){
							if(beanclienteRuta != null){
								PrecioActual = beanclienteRuta.getPrecio1();
							}else{
								PrecioActual = programacion.getPrecio1();	
							}
							
							//venta.setPrecioAct((programacion.getPrecio1())); 
							break;
						}else{
							if(paso3Form.getNumeroAsiento().trim().equals(croquisbus.getAsiento().trim())){
								if(croquisbus.getTTop()<1920){
									if(beanclienteRuta != null){
										PrecioActual = beanclienteRuta.getPrecio1();
									}else{
										PrecioActual = programacion.getPrecio1();
									}
									//venta.setPrecioAct(programacion.getPrecio1());
									break;
								}else{
									if(beanclienteRuta != null){
										PrecioActual =  beanclienteRuta.getPrecio2();
									}else{
										PrecioActual =  programacion.getPrecio2();
									}
									//venta.setPrecioAct(programacion.getPrecio2());
									break;
								}
							}
						}
					}
					
					
						if(Double.parseDouble(paso3Form.getPrecio())< PrecioActual){
							Auxiliar = PrecioActual ;
							return Auxiliar;
						}
					
				}
		return Auxiliar;
	}

	@Override
	public double VerificaPrecioActualVuelta(List<V_CroquisBusBean> listaCroquisBus, VentaPaso2Form paso2Form,VentaPaso4Form paso4Form, int minValor,SpringSecurityUser usuario) throws Exception {
		
		
				V_DestinosBean destino = new V_DestinosBean();
				DestinosService servicedestino = new DestinoServiceI();
				B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
				ProgramacionSalidaService serviceprogramacion = new ProgramacionSalidaServiceI();
				Promo_RetornoCService servicepromociones = new Promo_RetornoCServiceI();
				double PrecioActual = 0.0;
				double Auxiliar = 0.0;
		
				// AGREGACION PARA LAS PROMOCIONES
				V_Promo_RetornoCBean bean =  servicepromociones.SQL_SELECT_TIPO_PROMOCION_RETORNO();
				
				//DESTINOS
				destino = servicedestino.getDestinoNro(Integer.parseInt(paso2Form.getNroDestino()));
		
		
				programacion = serviceprogramacion.getSalidasPrecioPromocion(paso2Form.getNroProgramacion(),destino.getDestino(),(bean == null ? 0 : bean.getWeb()));
				
				if(programacion != null){
					
				//OBTENEMOS EL PRECIO DESDE LA BASE DE DATOS
					
					V_Clientes_RutaPrecioBean beanclienteRuta = null;
					ClientesRutaPrecioService serviceclienteruta = new ClientesRutaPrecioServiceI();
					beanclienteRuta = serviceclienteruta.listaClientesRutasVerificacion(usuario.getRuc(), programacion.getDestino(), programacion.getServicio());
					
					
					for (V_CroquisBusBean croquisbus : listaCroquisBus) { 
						if(minValor>=1920){
							if(beanclienteRuta!=null){
								PrecioActual = beanclienteRuta.getPrecio1();
							}else{
								PrecioActual = programacion.getPrecio1();
							}
							//venta.setPrecioAct((programacion.getPrecio1())); 
							break;
						}else{
							if(paso4Form.getNumeroAsiento().trim().equals(croquisbus.getAsiento().trim())){
								if(croquisbus.getTTop()<1920){
									if(beanclienteRuta!=null){
										PrecioActual = beanclienteRuta.getPrecio1();	
									}else{
										PrecioActual = programacion.getPrecio1();
									}
									//venta.setPrecioAct(programacion.getPrecio1());
									break;
								}else{
									if(beanclienteRuta!=null){
										PrecioActual =  beanclienteRuta.getPrecio2();
									}else{
										PrecioActual =  programacion.getPrecio2();
									}
									//venta.setPrecioAct(programacion.getPrecio2());
									break;
								}
							}
						}
					}
					
					
						if(Double.parseDouble(paso4Form.getPrecio())< PrecioActual){
							Auxiliar = PrecioActual ;
							return Auxiliar;
						}
					
				}
		return Auxiliar;
	}

	@Override
	public B_CuentaCorrienteBean DatosDinamicosCuentaCorrientePagoEfectivo(B_VentaBean venta,
			B_CuentaCorrienteBean cuentacorrienteBean, B_Correlativos correlativo) throws Exception {
		// TODO Auto-generated method stub
		
		V_DestinosBean destino = new V_DestinosBean();
		B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
		
		
		cuentacorrienteBean.setNro(correlativo.getCorrelativoCtaCte());
		cuentacorrienteBean.setNroDoc(correlativo.getCorrelativoEnc());
		cuentacorrienteBean.setNroAplic(correlativo.getCorrelativoEnc());
		cuentacorrienteBean.setNroBol(venta.getNro());
		cuentacorrienteBean.setFechaEmision(venta.getFechaEmision());
		
		//if(usuario != null){
	//		cuentacorrienteBean.setRuc(usuario.getRuc());
	//		cuentacorrienteBean.setRazon(usuario.getRazonSocial().trim().toUpperCase());
	//		cuentacorrienteBean.setUsuario(usuario.getUsername().trim().toUpperCase());
	//	}else{
			cuentacorrienteBean.setUsuario("WEB-TC"); 
			cuentacorrienteBean.setUsuarioVisa(venta.getUsuarioVisa());
			//cuentacorrienteBean.setEticketVisa(EticketVisa); 
			cuentacorrienteBean.setRuc("20464993879");
			cuentacorrienteBean.setRazon("ORBIS VENTURES S.A.C");
			cuentacorrienteBean.setEticketVisa(venta.getEticketVisa()); 
			cuentacorrienteBean.setEticket(venta.getEticket());
			
		//} 
		
		//DESTINOS
		
		cuentacorrienteBean.setDestino(String.valueOf(venta.getDestino()));//   (IdaVuelta == "Y")? String.valueOf(paso1Form.getNroDestinoVUELTA()):  String.valueOf(paso1Form.getNroDestinoIDA()));
		destino =  ObtenerDatosDestinos(venta.getDestino());
		
		if(destino !=null){
			
			cuentacorrienteBean.setDestinoD(destino.getOrigenD().trim().toUpperCase()+"-"+destino.getDestinoD().trim().toUpperCase());
		}
		
		
		//PROGRAMACION DE SALIDAS
		
		programacion = ObtenerProgramacion(venta.getSalida());
		
		if(programacion !=null){
			
			cuentacorrienteBean.setServicio(programacion.getServicio());
			cuentacorrienteBean.setServicioD(programacion.getServicioD().trim().toUpperCase());
		}
		//GRABAMOS EL PRECIO  GENERADO EN LA TRANSACCION DE LA VENTA
		
		cuentacorrienteBean.setReferencia(venta.getNombre().trim().toUpperCase()/*venta.getNombre()*/);
		cuentacorrienteBean.setMonto(venta.getPrecioAct()/*venta.getPrecioAct()*/);
		
		return cuentacorrienteBean;
		
		
	}

	@Override
	public B_CuentaCorrienteBean DatosDinamicosCuentaCorrienteVisa(B_VentaBean venta,
			B_CuentaCorrienteBean cuentacorrienteBean, B_Correlativos correlativo, String EticketVisa)throws Exception {
		
		V_DestinosBean destino = new V_DestinosBean();
		B_ProgramacionSalidaBean programacion = new B_ProgramacionSalidaBean();
		
		cuentacorrienteBean.setNro(correlativo.getCorrelativoCtaCte());
		cuentacorrienteBean.setNroDoc(correlativo.getCorrelativoEnc());
		cuentacorrienteBean.setNroAplic(correlativo.getCorrelativoEnc());
		cuentacorrienteBean.setNroBol(venta.getNro());
		cuentacorrienteBean.setFechaEmision(venta.getFechaEmision());
		cuentacorrienteBean.setUsuario("WEB-TC"); 
		cuentacorrienteBean.setUsuarioVisa(venta.getUsuarioVisa());
		cuentacorrienteBean.setEticketVisa(EticketVisa); 
		cuentacorrienteBean.setRuc("20341198217");
		cuentacorrienteBean.setRazon("VISANET - CIA PERUANA DE MEDIOS DE PAGO SAC");
		cuentacorrienteBean.setEticket(venta.getEticket());
		
		//DESTINOS
		
		cuentacorrienteBean.setDestino(String.valueOf(venta.getDestino()));
		destino =  ObtenerDatosDestinos(venta.getDestino());
		
		
		if(destino !=null){
			
			cuentacorrienteBean.setDestinoD(destino.getOrigenD().trim().toUpperCase()+"-"+destino.getDestinoD().trim().toUpperCase());
		}
		
		
		//PROGRAMACION DE SALIDAS
		
		programacion = ObtenerProgramacion(venta.getSalida());
		
		if(programacion !=null){
			
			cuentacorrienteBean.setServicio(programacion.getServicio());
			cuentacorrienteBean.setServicioD(programacion.getServicioD().trim().toUpperCase());
		}
		//GRABAMOS EL PRECIO  GENERADO EN LA TRANSACCION DE LA VENTA
		
		cuentacorrienteBean.setReferencia(venta.getNombre().trim().toUpperCase()/*venta.getNombre()*/);
		cuentacorrienteBean.setMonto(venta.getPrecioAct()/*venta.getPrecioAct()*/);
		
		return cuentacorrienteBean;
	}

}
