package pe.com.grupopalomino.sistema.boletaje.util;

import java.text.DecimalFormat; 
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.security.core.context.SecurityContextHolder;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBeanDetalle;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBeanDetalle;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;


@SuppressWarnings({"deprecation"})
@ParentPackage(value = "BoletajePalomino03")
public class FuncionesPDF  {

	//private Map<String, Object> session;
	private static String user;
	private static String direccion;
	private static String nombre;
	
	
	
	
	static Calendar calendario = new GregorianCalendar(); 
	
	public static PdfPTable F_Reporte_Ventas( PdfPTable table,String origenvoucher,String nroembarquevoucher,String serviciovoucher,
			String pasajerovoucher,String dnivoucher,int nrovoucher,String asientovoucher,
			String embarquevoucher,String fechavoucher,String horavoucher,String reimprimir,String fechaemisionvoucher,double montovoucher,
			String Ruc,String Razon,String HoraCompra,String tipoVoucher,String medioPago,String emisor,String numeroTarjeta,String numeroOperacion,String Usuario,String DestinoRutaD) throws Exception{
			
			
			String[] splitorigen;
			splitorigen = Utils.F_Separador_Reporte(origenvoucher,"-");
			String Direccion = "";
			String servicios = "";
			BaseColor myColor = WebColors.getRGBColor("#00913D");
			String FechaProgramacion = Utils.FormatoFechaVoucher(fechavoucher,reimprimir);
		
			
			V_AgenciasBean bean= new V_AgenciasBean();
			
			bean =	FuncionesGeneralesUtils.F_Agencias(nroembarquevoucher);
			
			if(bean!=null){
			
			Direccion=bean.getDireccion();
			}
			
			String[]splitservicio = Utils.F_Separador_Reporte(serviciovoucher, " ");
			if(splitservicio!=null){
			servicios= splitservicio[0];
			}
			
			
			
			Paragraph vacio = new Paragraph("          ", new Font(FontFamily.HELVETICA,7,Font.BOLD,BaseColor.BLACK));
			vacio.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacionombres= new Paragraph("     ", new Font(FontFamily.HELVETICA,9,Font.BOLD,BaseColor.BLACK));
			vacionombres.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacio11= new Paragraph("     ", new Font(FontFamily.HELVETICA,1,Font.BOLD,BaseColor.BLACK));
			vacio11.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacioblancoinicial = new Paragraph("      ", new Font(FontFamily.HELVETICA,0.001f,Font.NORMAL,BaseColor.BLACK));
			vacioblancoinicial.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacioblancoembarque = new Paragraph("      ", new Font(FontFamily.HELVETICA,0.01f,Font.NORMAL,BaseColor.BLACK));
			vacioblancoembarque.setAlignment(Element.ALIGN_LEFT);
			
			
			
			Paragraph vacioblanco = new Paragraph("      ", new Font(FontFamily.HELVETICA,8,Font.BOLD,BaseColor.BLACK));
			vacioblanco.setAlignment(Element.ALIGN_LEFT);
			
			
			Paragraph vacioblancoEmbarque = new Paragraph("      ", new Font(FontFamily.HELVETICA,2,Font.BOLD,BaseColor.BLACK));
			vacioblancoEmbarque.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacioblanco1 = new Paragraph("       ", new Font(FontFamily.HELVETICA,5,Font.BOLD,BaseColor.BLACK));
			vacioblanco1.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacioblancoauxiliar = new Paragraph("      ", new Font(FontFamily.HELVETICA,4,Font.BOLD,BaseColor.BLACK));
			vacioblancoauxiliar.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacioblancoCompra = new Paragraph("      ", new Font(FontFamily.HELVETICA,5,Font.BOLD,BaseColor.BLACK));
			vacioblancoCompra.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacioblancoauxiliar1 = new Paragraph(" ", new Font(FontFamily.HELVETICA,1,Font.NORMAL,BaseColor.BLACK));
			vacioblancoauxiliar1.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacioverde = new Paragraph("        ", new Font(FontFamily.HELVETICA,10,Font.BOLD,myColor));
			vacioverde.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacioauxiliar = new Paragraph("XX", new Font(FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK));
			vacioauxiliar.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacio1 = new Paragraph(" "+" ", new Font(FontFamily.HELVETICA,7,Font.BOLD,BaseColor.BLACK));
			vacio1.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vacio2 = new Paragraph(" "+" ", new Font(FontFamily.HELVETICA,3,Font.BOLD,BaseColor.BLACK));
			vacio2.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vaciofooter = new Paragraph(" ", new Font(FontFamily.HELVETICA,50,Font.BOLD,BaseColor.BLACK));
			vaciofooter.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vaciofooterauxiliar = new Paragraph(" ", new Font(FontFamily.HELVETICA,19,Font.BOLD,BaseColor.BLACK));
			vaciofooter.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph vaciofooter1 = new Paragraph(" ", new Font(FontFamily.HELVETICA,42,Font.BOLD,BaseColor.BLACK));
			vaciofooter1.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph texto = new Paragraph("texto", new Font(FontFamily.HELVETICA,7,Font.BOLD,BaseColor.BLACK));
			texto.setAlignment(Element.ALIGN_LEFT);
			
			
			//IF PARA LA HORA DE VIAJE DE LA PROGRAMACION
			if(Integer.parseInt(horavoucher.substring(0,2))>11){
				horavoucher=horavoucher+" PM";
			}else{
				horavoucher=horavoucher+" AM";
			}
			
			String RucUsuario = "";
			String RazonUsuario = "";
			String NombreUsuario = "";
			
			if(tipoVoucher.trim().equals("Tarjeta")){
				
				user = "               "+medioPago;
				RucUsuario = "     "+emisor;
				RazonUsuario = "                            "+numeroOperacion;
				direccion = "                       "+numeroTarjeta;
				NombreUsuario = "";
				
			}else{
				
				SpringSecurityUser usuario = null;
				
				UsuariosWebService serviceusuario = new UsuariosWebServiceI();
				usuario = serviceusuario.limiteCreditoUsuario(Usuario.trim());
				
				if(usuario != null){
					if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringSecurityUser){
						user = usuario.getUsername().trim().toUpperCase();
						nombre = usuario.getNombres().trim().toUpperCase();
						direccion = usuario.getDireccion().toUpperCase();
						RucUsuario = usuario.getRuc().trim();
						RazonUsuario = usuario.getRazonSocial().toUpperCase();
						NombreUsuario = usuario.getNombres()+" "+usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno();
					}
				}
				
			}
		
			BaseFont BebasNeueRegular = BaseFont.createFont(FuncionesPDF.class.getResource("BebasNeueRegular.ttf").toString(), BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			
			BaseFont BebasNeueBook = BaseFont.createFont(FuncionesPDF.class.getResource("BebasNeueBook.ttf").toString(), BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			
			
			Font FontRegular = new Font(BebasNeueRegular, 13f, Font.BOLD);
			Font FontBook = new Font(BebasNeueBook, 11f, Font.NORMAL);
			Font FontBookEmbarque = new Font(BebasNeueBook, 8f, Font.NORMAL);
			Font FontBookInfoCompra = new Font(BebasNeueBook, 8f, Font.NORMAL);
			Font FontRegularViaje = new Font(BebasNeueRegular, 22f, Font.BOLD,BaseColor.WHITE);
			Font FontRegularFecha = new Font(BebasNeueRegular, 17f, Font.BOLD,myColor);
			Font FontRegularRuta = new Font(BebasNeueRegular, 10f, Font.BOLD);
			Font FontRegularRutaD = new Font(BebasNeueRegular, 10f, Font.BOLD,myColor);
			Font FontRegularHora = new Font(BebasNeueRegular, 27f, Font.BOLD,myColor);
			
			
			Paragraph pasajero = new Paragraph(pasajerovoucher.toUpperCase(), new Font(FontRegular));
			pasajero.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph dni = new Paragraph(dnivoucher, new Font(FontBook));
			dni.setAlignment(Element.ALIGN_LEFT);
			
			
			Paragraph ruc = new Paragraph(Ruc, new Font(FontBook));
			dni.setAlignment(Element.ALIGN_LEFT);
			
			
			Paragraph razon = new Paragraph(Razon, new Font(FontBook));
			dni.setAlignment(Element.ALIGN_LEFT);
			
			
			Paragraph rucusuario = new Paragraph(RucUsuario, new Font(FontBookInfoCompra));
			dni.setAlignment(Element.ALIGN_LEFT);
			
			
			Paragraph razonusuario = new Paragraph(RazonUsuario, new Font(FontBookInfoCompra));
			dni.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph nombreusuario = new Paragraph(NombreUsuario, new Font(FontBookInfoCompra));
			dni.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph guion = new Paragraph("-", new Font(FontRegularViaje));
			guion.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph ticket = new Paragraph(String.valueOf(nrovoucher), new Font(FontBook));
			ticket.setAlignment(Element.ALIGN_JUSTIFIED_ALL); 
			
			Paragraph destino = new Paragraph((splitorigen !=null)? splitorigen[0] : "" , new Font(FontRegular));
			destino.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph destino1 = new Paragraph((splitorigen!=null)? splitorigen[1]: "", new Font(FontRegular));
			destino1.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph viaje = new Paragraph((splitorigen !=null)? splitorigen[0].substring(0,3) : "", new Font(FontRegularViaje));
			viaje.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph viaje1 = new Paragraph( (splitorigen!=null)? splitorigen[1].substring(0, 3): "", new Font(FontRegularViaje));
			viaje1.setAlignment(Element.ALIGN_LEFT);
			       
			Paragraph servicio = new Paragraph(servicios, new Font(FontRegularViaje));
			servicio.setAlignment(Element.ALIGN_RIGHT);
			
			Paragraph asiento = new Paragraph(asientovoucher, new Font(FontRegularViaje));
			asiento.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph puntoembarque = new Paragraph("TERMINAL "+embarquevoucher+": "+((Direccion).trim()), new Font(FontBookEmbarque));
			puntoembarque.setAlignment(Element.ALIGN_RIGHT);
			
			Paragraph fecha = new Paragraph(FechaProgramacion, new Font(FontRegularFecha));
			fecha.setAlignment(Element.ALIGN_RIGHT);
			
			Paragraph rutaBusParagraph = new Paragraph("RUTA DEL BUS :", new Font(FontRegularRuta));
			rutaBusParagraph.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph rutaBusParagraphDescripcion = new Paragraph(DestinoRutaD, new Font(FontRegularRutaD));
			rutaBusParagraphDescripcion.setAlignment(Element.ALIGN_RIGHT);
			
			Paragraph hora = new Paragraph(horavoucher, new Font(FontRegularHora));
			
			Paragraph usu = new Paragraph(user, new Font(FontBookInfoCompra));
			
			Paragraph direccionusuario = new Paragraph(direccion, new Font(FontBookInfoCompra));
			
			Paragraph mediocompra = new Paragraph("AGENCIA VIRTUAL", new Font(FontBookInfoCompra));
			
			Paragraph horacompra = new Paragraph(HoraCompra, new Font(FontBookInfoCompra));
			
			Paragraph fechaemision = new Paragraph(fechaemisionvoucher, new Font(FontBookInfoCompra));
			
			Paragraph monto = new Paragraph(String.valueOf(montovoucher)+" SOLES", new Font(FontBookInfoCompra));
			
			
			table.setWidthPercentage(94);//antes 94
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.getDefaultCell().setFixedHeight(20);
			
			
			PdfPCell filavacia0 = new PdfPCell(vacioblancoinicial);
			filavacia0.setColspan(35);
			filavacia0.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia0);
			
			
			//__________________________________________________________________
			
			PdfPCell celdapasajero = new PdfPCell(pasajero);
			celdapasajero.setColspan(20);
			celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdapasajero);
			
			PdfPCell celdavacia = new PdfPCell(vacionombres);
			celdavacia.setColspan(15);
			celdavacia.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia);
			
			
			PdfPCell celdavacia0 = new PdfPCell(vacio11);
			celdavacia0.setColspan(20);
			celdavacia0.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia0);
			
			PdfPCell celdavacia1 = new PdfPCell(vacio11);
			celdavacia1.setColspan(15);
			celdavacia1.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia1);
			
			
			PdfPCell celdavacia2 = new PdfPCell(vacio);
			celdavacia2.setColspan(2);
			celdavacia2.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia2);
			
			PdfPCell celdadni = new PdfPCell(dni);
			celdadni.setColspan(33);
			celdadni.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdadni);
			
			
			PdfPCell celdavacia3 = new PdfPCell(vacio1);
			celdavacia3.setColspan(2);
			celdavacia3.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia3);
			
			PdfPCell celdaruc = new PdfPCell(ruc);//  ESPACIO DONDE VA IR EL RUC
			celdaruc.setColspan(15);
			celdaruc.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaruc);
			
			
			PdfPCell celdavacia4 = new PdfPCell(vacio1);
			celdavacia4.setColspan(18);
			celdavacia4.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia4);
			
			
			int tamanioceldaTicket = 15;
			int tamanioceldaDestino=0;
			int tamanioceldaDestin1=0;
			
			
			if((destino.getContent().trim().equals("PUERTO MALDONADO"))){
				
				//	tamanioceldaTicket = 1;
				tamanioceldaDestino = 9;
				tamanioceldaDestin1 = 7;
			}/*else{
				
				//tamanioceldaTicket = 3;
				tamanioceldaDestino = 8;
				tamanioceldaDestin1 = 4;
				
			}*/
			if(destino.getContent().trim().equals("LIMA")|| destino.getContent().trim().equals("CUSCO")){ 
				
				tamanioceldaTicket = 16;
				tamanioceldaDestino = 8;
				tamanioceldaDestin1 = 7;
				
			}if(destino1.getContent().trim().equals("LIMA")|| destino1.getContent().trim().equals("CUSCO")){ 
				
				tamanioceldaTicket = 16;
				tamanioceldaDestino = 7;
				tamanioceldaDestin1 = 8;
				
			}if(destino.getContent().trim().equals("ANDAHUAYLAS")){
				
				//tamanioceldaTicket = 4;
				tamanioceldaDestino = 7;
				tamanioceldaDestin1 = 9;
			}
			if(destino1.getContent().trim().equals("ANDAHUAYLAS")){
				
				//tamanioceldaTicket = 5;
				tamanioceldaDestino = 9;
				tamanioceldaDestin1 = 7;
			}
			if(destino.getContent().trim().equals("ABANCAY")){
				
				//tamanioceldaTicket = 5;
				tamanioceldaDestino = 9;
				tamanioceldaDestin1 = 7;
			}
			
			
			if(destino1.getContent().trim().equals("PUERTO MALDONADO")){
				
				tamanioceldaTicket = 16;
				//tamanioceldaDestino = 6;
				//tamanioceldaDestin1 = 8;
				tamanioceldaDestino = 7;
				tamanioceldaDestin1 = 8;
				destino.setAlignment(Element.ALIGN_RIGHT);
			}/*else{
				tamanioceldaDestin1 = 8;
				
			}*/
			
			if(tamanioceldaDestino==0 || tamanioceldaDestin1==0){
				
				tamanioceldaDestino = 9;
				tamanioceldaDestin1 = 8;
			}
			
			PdfPCell celdavacia6 = new PdfPCell(vacioblancoauxiliar); 
			celdavacia6.setColspan(4);//ANTES 19
			celdavacia6.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia6);
			
			
			PdfPCell celdarazon = new PdfPCell(razon); 
			celdarazon.setColspan(tamanioceldaTicket);//ANTES 19
			celdarazon.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdarazon);
			
			PdfPCell celdadestino = new PdfPCell(destino);
			celdadestino.setColspan(tamanioceldaDestino);//ANTES 8
			celdadestino.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdadestino);
			
			
			PdfPCell celdadestino1 = new PdfPCell(destino1);
			celdadestino1.setColspan(tamanioceldaDestin1);//ANTES
			celdadestino1.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdadestino1);
			
			PdfPCell filavacia03 = new PdfPCell(vacioblanco);
			filavacia03.setColspan(35);
			filavacia03.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia03);
			
			
			PdfPCell celdavacia9 = new PdfPCell(vacioblanco1);
			celdavacia9.setColspan(22);
			celdavacia9.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia9);
			
			PdfPCell celdafechaformato = new PdfPCell(fecha);
			celdafechaformato.setColspan(13);
			celdafechaformato.setRowspan(2);
			celdafechaformato.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdafechaformato);
			
			PdfPCell celdavacia10 = new PdfPCell(vacioblanco1);
			celdavacia10.setColspan(22);
			celdavacia10.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia10);
		
			PdfPCell celdaorigen = new PdfPCell(viaje);
			celdaorigen.setColspan(4);
			celdaorigen.setRowspan(2);
			celdaorigen.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaorigen);
			
			PdfPCell celdaguion = new PdfPCell(guion); 
			celdaguion.setColspan(1);
			celdaguion.setRowspan(2);
			celdaguion.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaguion);
			
			
			PdfPCell celdaorigen1 = new PdfPCell(viaje1);
			celdaorigen1.setColspan(4);
			celdaorigen1.setRowspan(2);
			celdaorigen1.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaorigen1);
			
			
			PdfPCell celdaservicio = new PdfPCell(servicio);
			celdaservicio.setColspan(6);
			celdaservicio.setRowspan(2);
			celdaservicio.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaservicio);
			
			
			PdfPCell celdasiento = new PdfPCell(asiento);
			celdasiento.setColspan(3);
			celdasiento.setRowspan(2);
			celdasiento.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdasiento);
			
			
			PdfPCell celdavacia11 = new PdfPCell(vacioblanco);
			celdavacia11.setColspan(4);
			celdavacia11.setRowspan(2);
			celdavacia11.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia11);
			
			
			PdfPCell celdahora = new PdfPCell(hora);
			celdahora.setColspan(13);
			celdahora.setRowspan(3);
			celdahora.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdahora);
			
		
			PdfPCell celdavacia12 = new PdfPCell(vacioblanco);
			celdavacia12.setColspan(22);
			celdavacia12.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia12);
			
			PdfPCell celdavacia13 = new PdfPCell(vacioblancoauxiliar);
			celdavacia13.setColspan(28);
			celdavacia13.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia13);
			
			
			PdfPCell celdavacia14 = new PdfPCell(vacioblancoauxiliar);
			celdavacia14.setColspan(4);
			celdavacia14.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia14);
			
			
			PdfPCell celdaticket = new PdfPCell(ticket);
			celdaticket.setColspan(3);
			celdaticket.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaticket);
			
	//___________________________________________________________________
			
			PdfPCell filavaciaembarque = new PdfPCell(vacioblancoembarque);
			filavaciaembarque.setColspan(35);
			filavaciaembarque.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavaciaembarque);  
			
			
			PdfPCell celdavacia16 = new PdfPCell(vacio);
			celdavacia16.setColspan(5);
			celdavacia16.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia16);
			
			PdfPCell celdaembarque = new PdfPCell(puntoembarque);
			celdaembarque.setColspan(22);
			celdaembarque.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaembarque);
			
			
			PdfPCell celdavacia17 = new PdfPCell(vacio1);
			celdavacia17.setColspan(8);
			celdavacia17.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia17);
		
			PdfPCell filavacia05 = new PdfPCell(vacioblanco);
			filavacia05.setColspan(35);
			filavacia05.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia05);
			
			/** TODO: CAMBIO PARA MOSTRAR LA RUTA DEL BUS***/
			PdfPCell filarutaBus = new PdfPCell(rutaBusParagraph);
			filarutaBus.setColspan(5);
			filarutaBus.setBorder(Rectangle.NO_BORDER);
			table.addCell(filarutaBus);
			
			PdfPCell filarutaBusDescripcion = new PdfPCell(rutaBusParagraphDescripcion);
			filarutaBusDescripcion.setColspan(30);
			filarutaBusDescripcion.setBorder(Rectangle.NO_BORDER);
			table.addCell(filarutaBusDescripcion);
			
			PdfPCell filavacia07 = new PdfPCell(vaciofooter);
			filavacia07.setColspan(35);
			filavacia07.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia07);
			
			
			PdfPCell filavacia08 = new PdfPCell(vaciofooter);
			filavacia08.setColspan(35);
			filavacia08.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia08);
			
			PdfPCell filavacia09 = new PdfPCell(vaciofooter);
			filavacia09.setColspan(35);
			filavacia09.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia09);
			
			PdfPCell filavacia10 = new PdfPCell(vacio2);
			filavacia10.setColspan(35);
			filavacia10.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia10);
			
			PdfPCell filavacia11 = new PdfPCell(vacio2);
			filavacia11.setColspan(35);
			filavacia11.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia11);
			
			PdfPCell filavacia12 = new PdfPCell(vacio2);
			filavacia12.setColspan(35);
			filavacia12.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia12);
			
			PdfPCell filavacia13 = new PdfPCell(vacio2);
			filavacia13.setColspan(35);
			filavacia13.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia13);
			
			PdfPCell filavacia14 = new PdfPCell(vacio2);
			filavacia14.setColspan(35);
			filavacia14.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia14);
			
			PdfPCell filavacia15 = new PdfPCell(vacio2);
			filavacia15.setColspan(35);
			filavacia15.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia15);
			
			PdfPCell filavacia16 = new PdfPCell(vacio2);
			filavacia16.setColspan(35);
			filavacia16.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia16);
			
			PdfPCell filavacia17 = new PdfPCell(vacioblanco);
			filavacia17.setColspan(35);
			filavacia17.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia17);
			
			PdfPCell filavacia18 = new PdfPCell(vacioblanco);
			filavacia18.setColspan(35);
			filavacia18.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia18);
			
			PdfPCell filavacia19 = new PdfPCell(vacioblanco);
			filavacia19.setColspan(35);
			filavacia19.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia19);
			
			PdfPCell filavacia20 = new PdfPCell(vacioblanco);
			filavacia20.setColspan(35);
			filavacia20.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia20);
			
			
			PdfPCell filavacia21 = new PdfPCell(vacioblanco);
			filavacia21.setColspan(35);
			filavacia21.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia21);
			
			PdfPCell celdavacia18 = new PdfPCell(vacio2);
			celdavacia18.setColspan(12);
			celdavacia18.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia18);
			
			
			PdfPCell celdavacia19 = new PdfPCell(vacioblanco);
			celdavacia19.setColspan(12);
			celdavacia19.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia19);
			
			PdfPCell celdavacia20 = new PdfPCell(vacioblanco);
			celdavacia20.setColspan(11);
			celdavacia20.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia20);
			
			PdfPCell celdavacia21 = new PdfPCell(vacio1);
			celdavacia21.setColspan(12);
			celdavacia21.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia21);
			
			PdfPCell celdavacia22 = new PdfPCell(vacioblanco);
			celdavacia22.setColspan(12);
			celdavacia22.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia22);
			
			PdfPCell celdavacia23 = new PdfPCell(vacioblanco);
			celdavacia23.setColspan(11);
			celdavacia23.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia23);
			
			
			PdfPCell celdavacia24 = new PdfPCell(vacio2);
			celdavacia24.setColspan(12);
			celdavacia24.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia24);
			
			PdfPCell celdavacia25 = new PdfPCell(vacioblanco);
			celdavacia25.setColspan(12);
			celdavacia25.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia25);
			
			PdfPCell celdavacia26 = new PdfPCell(vacioblanco);
			celdavacia26.setColspan(11);
			celdavacia26.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia26);
			
			PdfPCell celdavacia27 = new PdfPCell(vacio1);
			celdavacia27.setColspan(24);
			celdavacia27.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia27);
		
			
			PdfPCell celdavacia28 = new PdfPCell(vacioblanco);
			celdavacia28.setColspan(11);
			celdavacia28.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia28);
			
			
			PdfPCell filavacia22 = new PdfPCell(vaciofooterauxiliar);
			filavacia22.setColspan(35);
			filavacia22.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia22);
			
			PdfPCell filavacia23 = new PdfPCell(vacioblanco);
			filavacia23.setColspan(35);
			filavacia23.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia23);
			
			PdfPCell filavacia24 = new PdfPCell(vacioblancoCompra);
			filavacia24.setColspan(35);
			filavacia24.setBorder(Rectangle.NO_BORDER);
			table.addCell(filavacia24); 
			
			
		
////////////////////////////////////////////////////////////////////////////			
			
			PdfPCell celdavacia29 = new PdfPCell(vacioblanco);
			celdavacia29.setColspan(7);
			celdavacia29.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia29);
			
			
			PdfPCell celdausuario = new PdfPCell(usu);
			celdausuario.setColspan(17);
			celdausuario.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdausuario);
			
			PdfPCell celdafechaemision = new PdfPCell(fechaemision);
			celdafechaemision.setColspan(11);
			celdafechaemision.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdafechaemision);  
			
////////////////////////////////////////////////////////////////////////////
			
			PdfPCell celdavacia30 = new PdfPCell(vacioblanco);
			celdavacia30.setColspan(7);
			celdavacia30.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia30);
			
			PdfPCell celdarucusuario = new PdfPCell(rucusuario);
			celdarucusuario.setColspan(17);
			celdarucusuario.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdarucusuario);
			
			PdfPCell celdahoracompra = new PdfPCell(horacompra);
			celdahoracompra.setColspan(11);
			celdahoracompra.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdahoracompra);    
			
			
////////////////////////////////////////////////////////////////////////////
			
			
			PdfPCell celdavacia31 = new PdfPCell(vacioblanco);
			celdavacia31.setColspan(8);
			celdavacia31.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia31);
			
			
			PdfPCell celdarazonusuario = new PdfPCell(razonusuario);
			celdarazonusuario.setColspan(15);
			celdarazonusuario.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdarazonusuario);
			
			
			PdfPCell celdamediocompra = new PdfPCell(mediocompra);
			celdamediocompra.setColspan(12);
			celdamediocompra.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdamediocompra);   
			
			
			
//////////////////////////////////////////////////////////////////////////
			
			
			PdfPCell celdavacia32 = new PdfPCell(vacioblanco);
			celdavacia32.setColspan(8);
			celdavacia32.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia32);
			
			PdfPCell celdadireccion = new PdfPCell(direccionusuario);
			celdadireccion.setColspan(15);
			celdadireccion.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdadireccion);
			
			
			PdfPCell razonvacio = new PdfPCell(vacioblancoauxiliar1);
			razonvacio.setColspan(12);
			razonvacio.setBorder(Rectangle.NO_BORDER);
			table.addCell(razonvacio);   
////////////////////////////////////////////////////////////////////////
			
			PdfPCell celdavacia33 = new PdfPCell(vacioblanco);
			celdavacia33.setColspan(7);
			celdavacia33.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdavacia33);
			
			
			PdfPCell celdanombreusuario = new PdfPCell(nombreusuario);
			celdanombreusuario.setColspan(15);
			celdanombreusuario.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdanombreusuario);
			
			PdfPCell celdamonto = new PdfPCell(monto);
			celdamonto.setColspan(13);
			celdamonto.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdamonto);    
	
		
			return table;
			
			}
	
	
	
	public static PdfPTable F_Reporte_EstadoCuenta(PdfPTable table,List<B_CuentaCorrienteBean> ListaCuenta, Font FuenteCabecera ,Font FuenteDetalle,String OcultarDetalle,String UsuarioAdmin,double porcentajeComision) throws Exception{
			 
			
			table.setWidthPercentage(100);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setFixedHeight(20);
			
			
			PdfPCell cabecerafechaemision = new PdfPCell(new Paragraph("FECHA DE EMISIÓN",new Font(FuenteCabecera)));
			cabecerafechaemision.setHorizontalAlignment(Element.ALIGN_CENTER);
			//celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(cabecerafechaemision); 
			
			PdfPCell cabecerafechaviaje = new PdfPCell(new Paragraph("FECHA DE VIAJE",new Font(FuenteCabecera)));
			cabecerafechaviaje.setHorizontalAlignment(Element.ALIGN_CENTER);
			//celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(cabecerafechaviaje);
			
			PdfPCell cabeceravoucher = new PdfPCell(new Paragraph("N° VOUCHER",new Font(FuenteCabecera)));
			cabeceravoucher.setHorizontalAlignment(Element.ALIGN_CENTER);
			//celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(cabeceravoucher); 
			
			PdfPCell cabecerapasajero = new PdfPCell(new Paragraph("PASAJERO",new Font(FuenteCabecera)));
			cabecerapasajero.setHorizontalAlignment(Element.ALIGN_CENTER);
			//celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(cabecerapasajero); 
			
			
			PdfPCell cabeceraorigen = new PdfPCell(new Paragraph("ORIGEN",new Font(FuenteCabecera)));
			cabeceraorigen.setHorizontalAlignment(Element.ALIGN_CENTER);
			//celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(cabeceraorigen); 
			
			 
			PdfPCell cabeceradestino = new PdfPCell(new Paragraph("DESTINO",new Font(FuenteCabecera)));
			cabeceradestino.setHorizontalAlignment(Element.ALIGN_CENTER);
			//celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(cabeceradestino);
			
			PdfPCell cabeceraservicio = new PdfPCell(new Paragraph("SERVICIO",new Font(FuenteCabecera)));
			cabeceraservicio.setHorizontalAlignment(Element.ALIGN_CENTER);
			//celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(cabeceraservicio);
			
			
			PdfPCell cabeceratotal = new PdfPCell(new Paragraph("TOTAL",new Font(FuenteCabecera)));
			cabeceratotal.setHorizontalAlignment(Element.ALIGN_CENTER);
			//celdapasajero.setBorder(Rectangle.NO_BORDER);
			table.addCell(cabeceratotal);
			
			double Total= 0.0;
			DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
		    simbolo.setDecimalSeparator('.');
		    simbolo.setGroupingSeparator(',');
		    DecimalFormat formato = new DecimalFormat("###,###.##",simbolo);
			if(ListaCuenta.size()>0){
				
				for(int i=0;i<=ListaCuenta.size()-1;i++){
					
					PdfPCell celdavoucher = new PdfPCell(new Paragraph(ListaCuenta.get(i).getFechaEmision(),new Font(FuenteDetalle)));
					celdavoucher.setHorizontalAlignment(Element.ALIGN_CENTER);
					celdavoucher.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavoucher); 
					
					
					PdfPCell celdafechaemision = new PdfPCell(new Paragraph(ListaCuenta.get(i).getFechaViaje(),new Font(FuenteDetalle)));
					celdafechaemision.setHorizontalAlignment(Element.ALIGN_CENTER);
					celdafechaemision.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdafechaemision);
					
					
					PdfPCell celdadestino = new PdfPCell(new Paragraph(ListaCuenta.get(i).getVoucher(),new Font(FuenteDetalle)));
					celdadestino.setHorizontalAlignment(Element.ALIGN_CENTER);
					celdadestino.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdadestino);
					
					
					PdfPCell celdatotal = new PdfPCell(new Paragraph(ListaCuenta.get(i).getNombre(),new Font(FuenteDetalle)));
					celdatotal.setHorizontalAlignment(Element.ALIGN_CENTER);
					celdatotal.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdatotal);
					
					      
					PdfPCell celdapago = new PdfPCell(new Paragraph(ListaCuenta.get(i).getOrigenD(),new Font(FuenteDetalle)));
					celdapago.setHorizontalAlignment(Element.ALIGN_CENTER);
					celdapago.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdapago);
					
					
					PdfPCell celdasaldo = new PdfPCell(new Paragraph(ListaCuenta.get(i).getDestinoD(),new Font(FuenteDetalle)));
					celdasaldo.setHorizontalAlignment(Element.ALIGN_CENTER);
					celdasaldo.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdasaldo);
					
					PdfPCell celdaservicio = new PdfPCell(new Paragraph(ListaCuenta.get(i).getServicioD(),new Font(FuenteDetalle)));
					celdaservicio.setHorizontalAlignment(Element.ALIGN_CENTER);
					celdaservicio.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdaservicio);
					
					
					PdfPCell celdatotales = new PdfPCell(new Paragraph((ListaCuenta.get(i).getSaldo()==0 ? "S/. 0.0":"S/. "+ ListaCuenta.get(i).getSaldo()) ,new Font(FuenteDetalle)));
					celdatotales.setHorizontalAlignment(Element.ALIGN_CENTER);
					celdatotales.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdatotales);
					
					Total = Total + ((ListaCuenta.get(i).getSaldo()==0)? 0.0 :ListaCuenta.get(i).getSaldo());
					
				}
				
				
				double comisionNeta,igv,comisionN,deposito;
				
		/*		comisionNeta = Total*0.1;
				igv = (Total*0.1)-(Total*0.1/1.18);
				comisionN = comisionNeta - igv;
				deposito = Total - comisionNeta; */
				
				comisionNeta = Math.rint(((Total * porcentajeComision) / 100)*100)/100;
				igv = Math.rint((((Total * porcentajeComision)/100)-(((Total * porcentajeComision)/100)/1.18))*100)/100;
				comisionN = Math.rint((comisionNeta - igv)*100)/100;
				deposito = Math.rint((Total - comisionNeta)*100)/100;
				
				PdfPCell celdavacia = new PdfPCell(new Paragraph("  ",new Font(FontFamily.TIMES_ROMAN,7,Font.NORMAL,BaseColor.BLACK)));
				celdavacia.setColspan(6);
				celdavacia.setBorder(Rectangle.NO_BORDER);
				table.addCell(celdavacia);
				
				Paragraph total= new Paragraph("VENTA TOTAL :",new Font(FuenteDetalle));
				total.setAlignment(Element.ALIGN_RIGHT);
				
				PdfPCell celdavacia1 = new PdfPCell(total);
				celdavacia1.setColspan(1);
				celdavacia.setBorder(Rectangle.NO_BORDER);
				celdavacia1.setHorizontalAlignment(Element.ALIGN_RIGHT);;
				//celdapasajero.setBorder(Rectangle.NO_BORDER);
				table.addCell(celdavacia1);
				
				
				PdfPCell celdaSum = new PdfPCell(new Paragraph("S/. "+formato.format(Total)+" SOLES",new Font(FuenteDetalle)));
				table.addCell(celdaSum);
				
				if (OcultarDetalle.trim().equals("N")){
					
					PdfPCell celdavacia2 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
					celdavacia2.setColspan(8);
					celdavacia2.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavacia2);
					
					PdfPCell celdavacia02 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
					celdavacia02.setColspan(8);
					celdavacia02.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavacia02);
					
					
					PdfPCell celdavacia3 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
					celdavacia3.setColspan(4);
					celdavacia3.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavacia3);
					
					
					PdfPCell cabeceratotalventa = new PdfPCell(new Paragraph("VENTA TOTAL ",new Font(FuenteDetalle)));
					cabeceratotalventa.setColspan(1);
					//cabeceratotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(cabeceratotalventa);
					
					PdfPCell celdatotalventa = new PdfPCell(new Paragraph("S/."+formato.format(Total),new Font(FuenteDetalle)));
					celdatotalventa.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdatotalventa);
					
					PdfPCell celdavacia4 = new PdfPCell(new Paragraph("",new Font(FuenteDetalle)));
					celdavacia4.setColspan(2);
					celdavacia4.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavacia4);
					
					PdfPCell celdavacia5 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
					celdavacia5.setColspan(4);
					celdavacia5.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavacia5);
					
					
					PdfPCell cabeceracomision = new PdfPCell(new Paragraph("COMISIÓN NETA",new Font(FuenteDetalle)));
					cabeceracomision.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(cabeceracomision);
					
					PdfPCell celdacomision = new PdfPCell(new Paragraph("S/."+formato.format(comisionNeta),new Font(FuenteDetalle)));
					celdacomision.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdacomision);
					
					PdfPCell mensajecomision = new PdfPCell(new Paragraph(" 10%",new Font(FuenteDetalle)));
					mensajecomision.setColspan(2);
					mensajecomision.setBorder(Rectangle.NO_BORDER);
					table.addCell(mensajecomision);
					
					PdfPCell celdavacia6 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
					celdavacia6.setColspan(4);
					celdavacia6.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavacia6);
					
					PdfPCell cabeceraigv = new PdfPCell(new Paragraph("I.G.V",new Font(FuenteDetalle)));
					cabeceraigv.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(cabeceraigv);
					
					PdfPCell celdaigv = new PdfPCell(new Paragraph("S/."+formato.format(igv),new Font(FuenteDetalle)));
					celdaigv.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdaigv);
					
					PdfPCell mensajeigv= new PdfPCell(new Paragraph("(I.G.V DE COMISION)",new Font(FuenteDetalle)));
					mensajeigv.setColspan(2);
					mensajeigv.setBorder(Rectangle.NO_BORDER);
					table.addCell(mensajeigv);
					
					PdfPCell celdavacia7 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
					celdavacia7.setColspan(4);
					celdavacia7.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavacia7);
					
					
					PdfPCell cabeceracomisionn = new PdfPCell(new Paragraph("COMISIÓN N",new Font(FuenteDetalle)));
					cabeceracomisionn.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(cabeceracomisionn);
					
					PdfPCell celdacomisionn = new PdfPCell(new Paragraph("S/."+formato.format(comisionN),new Font(FuenteDetalle)));
					celdacomisionn.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdacomisionn);
					
					PdfPCell mensajecomisionn= new PdfPCell(new Paragraph("(COMISION)",new Font(FuenteDetalle)));
					mensajecomisionn.setColspan(2);
					mensajecomisionn.setBorder(Rectangle.NO_BORDER);
					table.addCell(mensajecomisionn);
					 
					
					PdfPCell celdavacia8 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
					celdavacia8.setColspan(4);
					celdavacia8.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdavacia8);
					
					
					PdfPCell cabeceradeposito = new PdfPCell(new Paragraph("TOTAL  DEPÓSITO",new Font(FuenteDetalle)));
					cabeceradeposito.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(cabeceradeposito);
					
					PdfPCell celdadeposito = new PdfPCell(new Paragraph("S/."+formato.format(deposito),new Font(FuenteDetalle)));
					celdadeposito.setColspan(1);
					//celdatotalventa.setBorder(Rectangle.NO_BORDER);
					table.addCell(celdadeposito);
					
					PdfPCell mensajedeposito= new PdfPCell(new Paragraph("(TOTAL A DEPOSITAR A NUESTRA CUENTA)",new Font(FuenteDetalle)));
					mensajedeposito.setColspan(2);
					mensajedeposito.setBorder(Rectangle.NO_BORDER);
					table.addCell(mensajedeposito);
					
				}
				
				
			}
		
			return table;
			
			}

public static PdfPTable ReporteAtencionReclamos (PdfPTable table,B_AtencionReclamosBean reclamos,Font FuenteCabeceraEmpresa,Font FuenteCabecera ,Font FuenteDetalle,B_AtencionReclamosBeanDetalle detalle){
		
		BaseColor myColor = WebColors.getRGBColor("#FEEE00");
		BaseColor myColorempresa = WebColors.getRGBColor("#009045");
		
		Font FuenteColumna = new Font(FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
		
		table.setWidthPercentage(110); // 100
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setFixedHeight(50);// 20
		
		PdfPCell cabeceraempresa = new PdfPCell(new Paragraph("LIBRO DE RECLAMACIONES - "+ reclamos.getEmpresaD(),new Font(FuenteCabeceraEmpresa)));
		cabeceraempresa.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceraempresa.setBackgroundColor(myColorempresa);
		cabeceraempresa.setColspan(12);
		table.addCell(cabeceraempresa); 
		
		PdfPCell cabeceratitulo = new PdfPCell(new Paragraph("LIBRO DE RECLAMACIONES",new Font(FuenteDetalle)));
		cabeceratitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceratitulo.setColspan(6);
		table.addCell(cabeceratitulo); 
		
		PdfPCell cabeceranroreclamo = new PdfPCell(new Paragraph("HOJA DE RECLAMACIÓN N° "+ reclamos.getNro(),new Font(FuenteDetalle)));
		cabeceranroreclamo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceranroreclamo.setColspan(6);
		cabeceranroreclamo.setRowspan(2);
		table.addCell(cabeceranroreclamo); 
		
		PdfPCell cabeceravoucher = new PdfPCell(new Paragraph("Fecha Reclamo",new Font(FuenteColumna)));
		cabeceravoucher.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceravoucher.setColspan(3);
		table.addCell(cabeceravoucher); 
		
		PdfPCell cabeceradia = new PdfPCell(new Paragraph(reclamos.getFechaReclamo(),new Font(FuenteDetalle)));
		cabeceradia.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceradia.setColspan(3);
		table.addCell(cabeceradia); 
		
		PdfPCell cabeceraconsumidor = new PdfPCell(new Paragraph("1. IDENTIFICACIÓN DEL CONSUMIDOR DEL RECLAMANTE",new Font(FuenteCabecera)));
		cabeceraconsumidor.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraconsumidor.setBackgroundColor(myColor);
		cabeceraconsumidor.setColspan(12);
		table.addCell(cabeceraconsumidor);
		
		
		PdfPCell cabeceradni = new PdfPCell(new Paragraph("DNI/CE :",new Font(FuenteColumna)));
		cabeceradni.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradni.setColspan(2);
		table.addCell(cabeceradni);
		
		PdfPCell contenidodni = new PdfPCell(new Paragraph(reclamos.getDNI(),new Font(FuenteDetalle)));
		contenidodni.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidodni.setColspan(4);
		table.addCell(contenidodni);
		
		
		PdfPCell cabeceratelefono = new PdfPCell(new Paragraph("Teléfono :",new Font(FuenteColumna)));
		cabeceratelefono.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceratelefono.setColspan(2);
		table.addCell(cabeceratelefono);
		
		PdfPCell contenidotelefono = new PdfPCell(new Paragraph(reclamos.getTelefono(),new Font(FuenteDetalle)));
		contenidotelefono.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidotelefono.setColspan(4);
		table.addCell(contenidotelefono);
		
		
		PdfPCell cabeceraemail = new PdfPCell(new Paragraph("E-mail :",new Font(FuenteColumna)));
		cabeceraemail.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraemail.setColspan(2);
		table.addCell(cabeceraemail);
		
		PdfPCell contenidoemail = new PdfPCell(new Paragraph(reclamos.getEmail(),new Font(FuenteDetalle)));
		contenidoemail.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoemail.setColspan(10);
		table.addCell(contenidoemail);
		
		PdfPCell cabeceranombre = new PdfPCell(new Paragraph("Nombres :",new Font(FuenteColumna)));
		cabeceranombre.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceranombre.setColspan(2);
		table.addCell(cabeceranombre);
		
		PdfPCell contenidonombre = new PdfPCell(new Paragraph(reclamos.getNombres(),new Font(FuenteDetalle)));
		contenidonombre.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidonombre.setColspan(10);
		table.addCell(contenidonombre);
		
		PdfPCell cabeceraapepaterno = new PdfPCell(new Paragraph("Ape. Paterno :",new Font(FuenteColumna)));
		cabeceraapepaterno.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraapepaterno.setColspan(2);
		table.addCell(cabeceraapepaterno);
		
		PdfPCell contenidoapepaterno = new PdfPCell(new Paragraph(reclamos.getApePaterno(),new Font(FuenteDetalle)));
		contenidoapepaterno.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoapepaterno.setColspan(4);
		table.addCell(contenidoapepaterno);
		
		PdfPCell cabeceramaterno = new PdfPCell(new Paragraph("Ape. Materno :",new Font(FuenteColumna)));
		cabeceramaterno.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceramaterno.setColspan(2);
		table.addCell(cabeceramaterno);
		
		PdfPCell contenidomaterno = new PdfPCell(new Paragraph(reclamos.getApeMaterno(),new Font(FuenteDetalle)));
		contenidomaterno.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidomaterno.setColspan(4);
		table.addCell(contenidomaterno);
		
		
		PdfPCell cabecerapadremadre = new PdfPCell(new Paragraph("Padre o Madre :",new Font(FuenteColumna)));
		cabecerapadremadre.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerapadremadre.setColspan(2);
		table.addCell(cabecerapadremadre);
		
		PdfPCell contenidopadremadre = new PdfPCell(new Paragraph(reclamos.getPadreMenor() ,new Font(FuenteDetalle)));
		contenidopadremadre.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidopadremadre.setColspan(10);
		table.addCell(contenidopadremadre);
		
		PdfPCell cabeceradomicilio = new PdfPCell(new Paragraph("Domicilio :",new Font(FuenteColumna)));
		cabeceradomicilio.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradomicilio.setColspan(2);
		table.addCell(cabeceradomicilio);
		
		PdfPCell contenidodomicilio = new PdfPCell(new Paragraph(reclamos.getDomicilio(),new Font(FuenteDetalle)));
		contenidodomicilio.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidodomicilio.setColspan(10);
		
		table.addCell(contenidodomicilio);
		
		PdfPCell cabecerabiencontratado = new PdfPCell(new Paragraph("2. IDENTIFICACIÓN DEL BIEN CONTRATADO",new Font(FuenteCabecera)));
		cabecerabiencontratado.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerabiencontratado.setColspan(12);
		cabecerabiencontratado.setBackgroundColor(myColor);
		table.addCell(cabecerabiencontratado);
		
		PdfPCell cabeceraservicio = new PdfPCell(new Paragraph("Servicio :",new Font(FuenteColumna)));
		cabeceraservicio.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraservicio.setColspan(2);
		table.addCell(cabeceraservicio);
		
		PdfPCell cabeceradetallereclamo = new PdfPCell(new Paragraph(reclamos.getServicioD(),new Font(FuenteDetalle)));
		cabeceradetallereclamo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradetallereclamo.setColspan(10);
		table.addCell(cabeceradetallereclamo);
		
		PdfPCell cabeceraMotivo = new PdfPCell(new Paragraph("Motivo :",new Font(FuenteColumna)));
		cabeceraMotivo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraMotivo.setColspan(2);
		table.addCell(cabeceraMotivo);
		
		PdfPCell contenidomotivo = new PdfPCell(new Paragraph(reclamos.getMotivoReclamoD(),new Font(FuenteDetalle)));
		contenidomotivo.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidomotivo.setColspan(10);
		table.addCell(contenidomotivo);
		
		PdfPCell cabeceradetalle = new PdfPCell(new Paragraph("3. DETALLE DE RECLAMACIÓN",new Font(FuenteCabecera)));
		cabeceradetalle.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradetalle.setColspan(8);
		cabeceradetalle.setBackgroundColor(myColor);
		table.addCell(cabeceradetalle);
		
		PdfPCell cabecerareclamo = new PdfPCell(new Paragraph("Reclamo :      "+(reclamos.getTipoReclamo().trim().equals("R")? "X" : ""),new Font(FuenteColumna)));
		cabecerareclamo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerareclamo.setColspan(2);
		table.addCell(cabecerareclamo);
		
		PdfPCell cabeceraqueja = new PdfPCell(new Paragraph("Queja :      	"+(reclamos.getTipoReclamo().trim().equals("Q")? "X" : ""),new Font(FuenteColumna)));
		cabeceraqueja.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraqueja.setColspan(2);
		table.addCell(cabeceraqueja);
		
		PdfPCell cabeceraTipoDocumento = new PdfPCell(new Paragraph("Documento:",new Font(FuenteColumna)));
		cabeceraTipoDocumento.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraTipoDocumento.setColspan(2);
		table.addCell(cabeceraTipoDocumento);
		
		PdfPCell contenidoTipoDocumento = new PdfPCell(new Paragraph(detalle.getDocumentoD(),new Font(FuenteDetalle)));
		contenidoTipoDocumento.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoTipoDocumento.setColspan(6);
		table.addCell(contenidoTipoDocumento);
		
		PdfPCell cabeceraNro = new PdfPCell(new Paragraph("N°:",new Font(FuenteColumna)));
		cabeceraNro.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraNro.setColspan(2);
		table.addCell(cabeceraNro);
		
		PdfPCell contenidoNro = new PdfPCell(new Paragraph(detalle.getSerie()+" - "+detalle.getNumero(),new Font(FuenteDetalle)));
		contenidoNro.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoNro.setColspan(2);
		table.addCell(contenidoNro);
	
		PdfPCell cabeceraFechaEmision = new PdfPCell(new Paragraph("Fecha Emision:",new Font(FuenteColumna)));
		cabeceraFechaEmision.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraFechaEmision.setColspan(2);
		table.addCell(cabeceraFechaEmision);
		
		PdfPCell contenidoFechaEmision = new PdfPCell(new Paragraph(detalle.getFechaEmision(),new Font(FuenteDetalle)));
		contenidoFechaEmision.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoFechaEmision.setColspan(2);
		table.addCell(contenidoFechaEmision);
		
		PdfPCell cabeceraDestino = new PdfPCell(new Paragraph("Destino:",new Font(FuenteColumna)));
		cabeceraDestino.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraDestino.setColspan(2);
		table.addCell(cabeceraDestino);
		
		PdfPCell contenidoDestino = new PdfPCell(new Paragraph(detalle.getDestinoD(),new Font(FuenteDetalle)));
		contenidoDestino.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoDestino.setColspan(6);
		table.addCell(contenidoDestino);
		
		PdfPCell cabeceraAgencia = new PdfPCell(new Paragraph("Agencia:",new Font(FuenteColumna)));
		cabeceraAgencia.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraAgencia.setColspan(2);
		table.addCell(cabeceraAgencia);
		
		PdfPCell contenidoAgencia = new PdfPCell(new Paragraph(detalle.getAgenciaD(),new Font(FuenteDetalle)));
		contenidoAgencia.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoAgencia.setColspan(10);
		table.addCell(contenidoAgencia);
		
		PdfPCell cabeceraquedetalle = new PdfPCell(new Paragraph("Detalle:",new Font(FuenteColumna)));
		cabeceraquedetalle.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraquedetalle.setColspan(6);
		table.addCell(cabeceraquedetalle);
		
		PdfPCell cabecerapedido = new PdfPCell(new Paragraph("Pedido:",new Font(FuenteColumna)));
		cabecerapedido.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerapedido.setColspan(6);
		table.addCell(cabecerapedido);
		
		PdfPCell cabeceradetallefilas = new PdfPCell(new Paragraph(reclamos.getDetalle(),new Font(FuenteDetalle)));
		cabeceradetallefilas.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradetallefilas.setColspan(6);
		cabeceradetallefilas.setRowspan(2);
		table.addCell(cabeceradetallefilas);
		
		PdfPCell cabeceradetallepedido = new PdfPCell(new Paragraph(reclamos.getPedido(),new Font(FuenteDetalle)));
		cabeceradetallepedido.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradetallepedido.setColspan(6);
		cabeceradetallepedido.setRowspan(2);
		table.addCell(cabeceradetallepedido);
		
		PdfPCell cabeceraacciones = new PdfPCell(new Paragraph("4. OBSERVACIONES Y ACCIONES PERMITIDAS",new Font(FuenteCabecera)));
		cabeceraacciones.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraacciones.setColspan(12);
		cabeceraacciones.setBackgroundColor(myColor);
		table.addCell(cabeceraacciones);
		
		PdfPCell cabecerareclamo4 = new PdfPCell(new Paragraph("Reclamo: ",new Font(FuenteColumna)));
		cabecerareclamo4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerareclamo4.setColspan(6);
		table.addCell(cabecerareclamo4);
		
		PdfPCell queja = new PdfPCell(new Paragraph("Queja :",new Font(FuenteColumna)));
		queja.setHorizontalAlignment(Element.ALIGN_LEFT);
		queja.setColspan(6);
		table.addCell(queja);
		
		PdfPCell cabeceraaccionesdetalle = new PdfPCell(new Paragraph("Disconformidad relacionada a los productos o servicios",new Font(FuenteDetalle)));
		cabeceraaccionesdetalle.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraaccionesdetalle.setColspan(6);
		cabeceraaccionesdetalle.setRowspan(2);
		table.addCell(cabeceraaccionesdetalle);
		
		PdfPCell contenidoqueja = new PdfPCell(new Paragraph("Disconformidad no relacionada a los productos o servicios;o,malestar o descontento"
				+ "respecto a la atención del cliente al público",new Font(FuenteDetalle)));
		contenidoqueja.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoqueja.setColspan(6);
		contenidoqueja.setRowspan(2);
		table.addCell(contenidoqueja);
		
		if(reclamos.getOperacion()!= null &&  reclamos.getOperacion().trim().equals("1")){
			
			PdfPCell cabeceradetalleAtencion = new PdfPCell(new Paragraph("5. DETALLE DE LA ATENCIÓN",new Font(FuenteCabecera)));
			cabeceradetalleAtencion.setHorizontalAlignment(Element.ALIGN_LEFT);
			cabeceradetalleAtencion.setColspan(12);
			cabeceradetalleAtencion.setBackgroundColor(myColor);
			table.addCell(cabeceradetalleAtencion);
			
			PdfPCell cabeceraAtencion = new PdfPCell(new Paragraph((reclamos.getDetalleAtencion()== null || reclamos.getDetalleAtencion().trim().isEmpty()?"  ": reclamos.getDetalleAtencion()),new Font(FuenteDetalle)));
			cabeceraAtencion.setHorizontalAlignment(Element.ALIGN_LEFT);
			cabeceraAtencion.setColspan(12);
			cabeceraAtencion.setRowspan(2);
			table.addCell(cabeceraAtencion);
		}
		
		
		return table;
	
	}
public static PdfPTable TotalesAdminAgente (PdfPTable table,double Total,Font FuenteCabecera ,Font FuenteDetalle,double porcentajeComision){
		
		
		//double Total= 0.0;
		DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
	    simbolo.setDecimalSeparator('.');
	    simbolo.setGroupingSeparator(',');
	    DecimalFormat formato = new DecimalFormat("###,###.##",simbolo);
	    
	    
	    double comisionNeta,igv,comisionN,deposito;
		
	/*	comisionNeta = Total*0.1;
		igv = (Total*0.1)-(Total*0.1/1.18);
		comisionN = comisionNeta - igv;
		deposito = Total - comisionNeta; */
	    
	    comisionNeta = Math.rint(((Total * porcentajeComision) / 100)*100)/100;
		igv = Math.rint((((Total * porcentajeComision)/100)-(((Total * porcentajeComision)/100)/1.18))*100)/100;
		comisionN = Math.rint((comisionNeta - igv)*100)/100;
		deposito = Math.rint((Total - comisionNeta)*100)/100; 
		
		PdfPCell celdavacia2 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
		celdavacia2.setColspan(8);
		celdavacia2.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdavacia2);
		
		PdfPCell celdavacia02 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
		celdavacia02.setColspan(8);
		celdavacia02.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdavacia02);
		
		
		PdfPCell celdavacia3 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
		celdavacia3.setColspan(4);
		celdavacia3.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdavacia3);
		
		
		PdfPCell cabeceratotalventa = new PdfPCell(new Paragraph("VENTA TOTAL ",new Font(FuenteDetalle)));
		cabeceratotalventa.setColspan(1);
		//cabeceratotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceratotalventa);
		
		PdfPCell celdatotalventa = new PdfPCell(new Paragraph("S/."+formato.format(Total),new Font(FuenteDetalle)));
		celdatotalventa.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdatotalventa);
		
		PdfPCell celdavacia4 = new PdfPCell(new Paragraph("",new Font(FuenteDetalle)));
		celdavacia4.setColspan(2);
		celdavacia4.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdavacia4);
		
		PdfPCell celdavacia5 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
		celdavacia5.setColspan(4);
		celdavacia5.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdavacia5);
		
		
		PdfPCell cabeceracomision = new PdfPCell(new Paragraph("COMISIÓN NETA",new Font(FuenteDetalle)));
		cabeceracomision.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceracomision);
		
		PdfPCell celdacomision = new PdfPCell(new Paragraph("S/."+formato.format(comisionNeta),new Font(FuenteDetalle)));
		celdacomision.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdacomision);
		
		PdfPCell mensajecomision = new PdfPCell(new Paragraph(" 10%",new Font(FuenteDetalle)));
		mensajecomision.setColspan(2);
		mensajecomision.setBorder(Rectangle.NO_BORDER);
		table.addCell(mensajecomision);
		
		PdfPCell celdavacia6 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
		celdavacia6.setColspan(4);
		celdavacia6.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdavacia6);
		
		PdfPCell cabeceraigv = new PdfPCell(new Paragraph("I.G.V",new Font(FuenteDetalle)));
		cabeceraigv.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceraigv);
		
		PdfPCell celdaigv = new PdfPCell(new Paragraph("S/."+formato.format(igv),new Font(FuenteDetalle)));
		celdaigv.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaigv);
		
		PdfPCell mensajeigv= new PdfPCell(new Paragraph("(I.G.V DE COMISION)",new Font(FuenteDetalle)));
		mensajeigv.setColspan(2);
		mensajeigv.setBorder(Rectangle.NO_BORDER);
		table.addCell(mensajeigv);
		
		PdfPCell celdavacia7 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
		celdavacia7.setColspan(4);
		celdavacia7.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdavacia7);
		
		
		PdfPCell cabeceracomisionn = new PdfPCell(new Paragraph("COMISIÓN N",new Font(FuenteDetalle)));
		cabeceracomisionn.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceracomisionn);
		
		PdfPCell celdacomisionn = new PdfPCell(new Paragraph("S/."+formato.format(comisionN),new Font(FuenteDetalle)));
		celdacomisionn.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdacomisionn);
		
		PdfPCell mensajecomisionn= new PdfPCell(new Paragraph("(COMISION)",new Font(FuenteDetalle)));
		mensajecomisionn.setColspan(2);
		mensajecomisionn.setBorder(Rectangle.NO_BORDER);
		table.addCell(mensajecomisionn);
		 
		
		PdfPCell celdavacia8 = new PdfPCell(new Paragraph("  ",new Font(FuenteDetalle)));
		celdavacia8.setColspan(4);
		celdavacia8.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdavacia8);
		
		
		PdfPCell cabeceradeposito = new PdfPCell(new Paragraph("TOTAL  DEPÓSITO",new Font(FuenteDetalle)));
		cabeceradeposito.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceradeposito);
		
		PdfPCell celdadeposito = new PdfPCell(new Paragraph("S/."+formato.format(deposito),new Font(FuenteDetalle)));
		celdadeposito.setColspan(1);
		//celdatotalventa.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdadeposito);
		
		PdfPCell mensajedeposito= new PdfPCell(new Paragraph("(TOTAL A DEPOSITAR A NUESTRA CUENTA)",new Font(FuenteDetalle)));
		mensajedeposito.setColspan(2);
		mensajedeposito.setBorder(Rectangle.NO_BORDER);
		table.addCell(mensajedeposito);
		
		return table;
		
	}

	public static PdfPTable ReporteReclamos (PdfPTable table,B_ReclamosBean reclamos,Font FuenteCabeceraEmpresa,Font FuenteCabecera ,Font FuenteDetalle,B_ReclamosBeanDetalle detalle){
		
		BaseColor myColor = WebColors.getRGBColor("#FEEE00");
		BaseColor myColorempresa = WebColors.getRGBColor("#009045");
		
		Font FuenteColumna = new Font(FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
		
		table.setWidthPercentage(110); // 100
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setFixedHeight(50);// 20
		
		PdfPCell cabeceraempresa = new PdfPCell(new Paragraph("LIBRO DE RECLAMACIONES - "+ reclamos.getEmpresaD(),new Font(FuenteCabeceraEmpresa)));
		cabeceraempresa.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceraempresa.setBackgroundColor(myColorempresa);
		cabeceraempresa.setColspan(12);
		table.addCell(cabeceraempresa); 
		
		PdfPCell cabeceratitulo = new PdfPCell(new Paragraph("LIBRO DE RECLAMACIONES",new Font(FuenteDetalle)));
		cabeceratitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceratitulo.setColspan(6);
		table.addCell(cabeceratitulo); 
		
		PdfPCell cabeceranroreclamo = new PdfPCell(new Paragraph("HOJA DE RECLAMACIÓN N° "+ reclamos.getNro(),new Font(FuenteDetalle)));
		cabeceranroreclamo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceranroreclamo.setColspan(6);
		cabeceranroreclamo.setRowspan(2);
		table.addCell(cabeceranroreclamo); 
		
		PdfPCell cabeceravoucher = new PdfPCell(new Paragraph("Fecha Reclamo",new Font(FuenteColumna)));
		cabeceravoucher.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceravoucher.setColspan(3);
		table.addCell(cabeceravoucher); 
		
		PdfPCell cabeceradia = new PdfPCell(new Paragraph(reclamos.getFechaReclamo(),new Font(FuenteDetalle)));
		cabeceradia.setHorizontalAlignment(Element.ALIGN_CENTER);
		cabeceradia.setColspan(3);
		table.addCell(cabeceradia); 
		
		PdfPCell cabeceraconsumidor = new PdfPCell(new Paragraph("1. IDENTIFICACIÓN DEL CONSUMIDOR DEL RECLAMANTE",new Font(FuenteCabecera)));
		cabeceraconsumidor.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraconsumidor.setBackgroundColor(myColor);
		cabeceraconsumidor.setColspan(12);
		table.addCell(cabeceraconsumidor);
		
		
		PdfPCell cabeceradni = new PdfPCell(new Paragraph("DNI/CE :",new Font(FuenteColumna)));
		cabeceradni.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradni.setColspan(2);
		table.addCell(cabeceradni);
		
		PdfPCell contenidodni = new PdfPCell(new Paragraph(reclamos.getDNI(),new Font(FuenteDetalle)));
		contenidodni.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidodni.setColspan(4);
		table.addCell(contenidodni);
		
		
		PdfPCell cabeceratelefono = new PdfPCell(new Paragraph("Teléfono :",new Font(FuenteColumna)));
		cabeceratelefono.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceratelefono.setColspan(2);
		table.addCell(cabeceratelefono);
		
		PdfPCell contenidotelefono = new PdfPCell(new Paragraph(reclamos.getTelefono(),new Font(FuenteDetalle)));
		contenidotelefono.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidotelefono.setColspan(4);
		table.addCell(contenidotelefono);
		
		
		PdfPCell cabeceraemail = new PdfPCell(new Paragraph("E-mail :",new Font(FuenteColumna)));
		cabeceraemail.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraemail.setColspan(2);
		table.addCell(cabeceraemail);
		
		PdfPCell contenidoemail = new PdfPCell(new Paragraph(reclamos.getEmail(),new Font(FuenteDetalle)));
		contenidoemail.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoemail.setColspan(10);
		table.addCell(contenidoemail);
		
		PdfPCell cabeceranombre = new PdfPCell(new Paragraph("Nombres :",new Font(FuenteColumna)));
		cabeceranombre.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceranombre.setColspan(2);
		table.addCell(cabeceranombre);
		
		PdfPCell contenidonombre = new PdfPCell(new Paragraph(reclamos.getNombres(),new Font(FuenteDetalle)));
		contenidonombre.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidonombre.setColspan(10);
		table.addCell(contenidonombre);
		
		PdfPCell cabeceraapepaterno = new PdfPCell(new Paragraph("Ape. Paterno :",new Font(FuenteColumna)));
		cabeceraapepaterno.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraapepaterno.setColspan(2);
		table.addCell(cabeceraapepaterno);
		
		PdfPCell contenidoapepaterno = new PdfPCell(new Paragraph(reclamos.getApePaterno(),new Font(FuenteDetalle)));
		contenidoapepaterno.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoapepaterno.setColspan(4);
		table.addCell(contenidoapepaterno);
		
		PdfPCell cabeceramaterno = new PdfPCell(new Paragraph("Ape. Materno :",new Font(FuenteColumna)));
		cabeceramaterno.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceramaterno.setColspan(2);
		table.addCell(cabeceramaterno);
		
		PdfPCell contenidomaterno = new PdfPCell(new Paragraph(reclamos.getApeMaterno(),new Font(FuenteDetalle)));
		contenidomaterno.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidomaterno.setColspan(4);
		table.addCell(contenidomaterno);
		
		
		PdfPCell cabecerapadremadre = new PdfPCell(new Paragraph("Padre o Madre :",new Font(FuenteColumna)));
		cabecerapadremadre.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerapadremadre.setColspan(2);
		table.addCell(cabecerapadremadre);
		
		PdfPCell contenidopadremadre = new PdfPCell(new Paragraph(reclamos.getPadreMenor() ,new Font(FuenteDetalle)));
		contenidopadremadre.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidopadremadre.setColspan(10);
		table.addCell(contenidopadremadre);
		
		PdfPCell cabeceradomicilio = new PdfPCell(new Paragraph("Domicilio :",new Font(FuenteColumna)));
		cabeceradomicilio.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradomicilio.setColspan(2);
		table.addCell(cabeceradomicilio);
		
		PdfPCell contenidodomicilio = new PdfPCell(new Paragraph(reclamos.getDomicilio(),new Font(FuenteDetalle)));
		contenidodomicilio.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidodomicilio.setColspan(10);
		
		table.addCell(contenidodomicilio);
		
		PdfPCell cabecerabiencontratado = new PdfPCell(new Paragraph("2. IDENTIFICACIÓN DEL BIEN CONTRATADO",new Font(FuenteCabecera)));
		cabecerabiencontratado.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerabiencontratado.setColspan(12);
		cabecerabiencontratado.setBackgroundColor(myColor);
		table.addCell(cabecerabiencontratado);
		
		PdfPCell cabeceraservicio = new PdfPCell(new Paragraph("Servicio :",new Font(FuenteColumna)));
		cabeceraservicio.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraservicio.setColspan(2);
		table.addCell(cabeceraservicio);
		
		PdfPCell cabeceradetallereclamo = new PdfPCell(new Paragraph(reclamos.getServicioD(),new Font(FuenteDetalle)));
		cabeceradetallereclamo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradetallereclamo.setColspan(10);
		table.addCell(cabeceradetallereclamo);
		
		PdfPCell cabeceraMotivo = new PdfPCell(new Paragraph("Motivo :",new Font(FuenteColumna)));
		cabeceraMotivo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraMotivo.setColspan(2);
		table.addCell(cabeceraMotivo);
		
		PdfPCell contenidomotivo = new PdfPCell(new Paragraph(reclamos.getMotivoReclamoD(),new Font(FuenteDetalle)));
		contenidomotivo.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidomotivo.setColspan(10);
		table.addCell(contenidomotivo);
		
		PdfPCell cabeceradetalle = new PdfPCell(new Paragraph("3. DETALLE DE RECLAMACIÓN",new Font(FuenteCabecera)));
		cabeceradetalle.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradetalle.setColspan(8);
		cabeceradetalle.setBackgroundColor(myColor);
		table.addCell(cabeceradetalle);
		
		PdfPCell cabecerareclamo = new PdfPCell(new Paragraph("Reclamo :      "+(reclamos.getTipoReclamo().trim().equals("R")? "X" : ""),new Font(FuenteColumna)));
		cabecerareclamo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerareclamo.setColspan(2);
		table.addCell(cabecerareclamo);
		
		PdfPCell cabeceraqueja = new PdfPCell(new Paragraph("Queja :      	"+(reclamos.getTipoReclamo().trim().equals("Q")? "X" : ""),new Font(FuenteColumna)));
		cabeceraqueja.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraqueja.setColspan(2);
		table.addCell(cabeceraqueja);
		
		PdfPCell cabeceraTipoDocumento = new PdfPCell(new Paragraph("Documento:",new Font(FuenteColumna)));
		cabeceraTipoDocumento.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraTipoDocumento.setColspan(2);
		table.addCell(cabeceraTipoDocumento);
		
		PdfPCell contenidoTipoDocumento = new PdfPCell(new Paragraph(detalle.getDocumentoD(),new Font(FuenteDetalle)));
		contenidoTipoDocumento.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoTipoDocumento.setColspan(6);
		table.addCell(contenidoTipoDocumento);
		
		PdfPCell cabeceraNro = new PdfPCell(new Paragraph("N°:",new Font(FuenteColumna)));
		cabeceraNro.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraNro.setColspan(2);
		table.addCell(cabeceraNro);
		
		PdfPCell contenidoNro = new PdfPCell(new Paragraph(detalle.getSerie()+" - "+detalle.getNumero(),new Font(FuenteDetalle)));
		contenidoNro.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoNro.setColspan(2);
		table.addCell(contenidoNro);
	
		PdfPCell cabeceraFechaEmision = new PdfPCell(new Paragraph("Fecha Emision:",new Font(FuenteColumna)));
		cabeceraFechaEmision.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraFechaEmision.setColspan(2);
		table.addCell(cabeceraFechaEmision);
		
		PdfPCell contenidoFechaEmision = new PdfPCell(new Paragraph(detalle.getFechaEmision(),new Font(FuenteDetalle)));
		contenidoFechaEmision.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoFechaEmision.setColspan(2);
		table.addCell(contenidoFechaEmision);
		
		PdfPCell cabeceraDestino = new PdfPCell(new Paragraph("Destino:",new Font(FuenteColumna)));
		cabeceraDestino.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraDestino.setColspan(2);
		table.addCell(cabeceraDestino);
		
		PdfPCell contenidoDestino = new PdfPCell(new Paragraph(detalle.getDestinoD(),new Font(FuenteDetalle)));
		contenidoDestino.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoDestino.setColspan(6);
		table.addCell(contenidoDestino);
		
		PdfPCell cabeceraAgencia = new PdfPCell(new Paragraph("Agencia:",new Font(FuenteColumna)));
		cabeceraAgencia.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraAgencia.setColspan(2);
		table.addCell(cabeceraAgencia);
		
		PdfPCell contenidoAgencia = new PdfPCell(new Paragraph(detalle.getAgenciaD(),new Font(FuenteDetalle)));
		contenidoAgencia.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoAgencia.setColspan(10);
		table.addCell(contenidoAgencia);
		
		PdfPCell cabeceraquedetalle = new PdfPCell(new Paragraph("Detalle:",new Font(FuenteColumna)));
		cabeceraquedetalle.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraquedetalle.setColspan(6);
		table.addCell(cabeceraquedetalle);
		
		PdfPCell cabecerapedido = new PdfPCell(new Paragraph("Pedido:",new Font(FuenteColumna)));
		cabecerapedido.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerapedido.setColspan(6);
		table.addCell(cabecerapedido);
		
		PdfPCell cabeceradetallefilas = new PdfPCell(new Paragraph(reclamos.getDetalle(),new Font(FuenteDetalle)));
		cabeceradetallefilas.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradetallefilas.setColspan(6);
		cabeceradetallefilas.setRowspan(2);
		table.addCell(cabeceradetallefilas);
		
		PdfPCell cabeceradetallepedido = new PdfPCell(new Paragraph(reclamos.getPedido(),new Font(FuenteDetalle)));
		cabeceradetallepedido.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceradetallepedido.setColspan(6);
		cabeceradetallepedido.setRowspan(2);
		table.addCell(cabeceradetallepedido);
		
		PdfPCell cabeceraacciones = new PdfPCell(new Paragraph("4. OBSERVACIONES Y ACCIONES PERMITIDAS",new Font(FuenteCabecera)));
		cabeceraacciones.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraacciones.setColspan(12);
		cabeceraacciones.setBackgroundColor(myColor);
		table.addCell(cabeceraacciones);
		
		PdfPCell cabecerareclamo4 = new PdfPCell(new Paragraph("Reclamo: ",new Font(FuenteColumna)));
		cabecerareclamo4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabecerareclamo4.setColspan(6);
		table.addCell(cabecerareclamo4);
		
		PdfPCell queja = new PdfPCell(new Paragraph("Queja :",new Font(FuenteColumna)));
		queja.setHorizontalAlignment(Element.ALIGN_LEFT);
		queja.setColspan(6);
		table.addCell(queja);
		
		PdfPCell cabeceraaccionesdetalle = new PdfPCell(new Paragraph("Disconformidad relacionada a los productos o servicios",new Font(FuenteDetalle)));
		cabeceraaccionesdetalle.setHorizontalAlignment(Element.ALIGN_LEFT);
		cabeceraaccionesdetalle.setColspan(6);
		cabeceraaccionesdetalle.setRowspan(2);
		table.addCell(cabeceraaccionesdetalle);
		
		PdfPCell contenidoqueja = new PdfPCell(new Paragraph("Disconformidad no relacionada a los productos o servicios;o,malestar o descontento"
				+ "respecto a la atención del cliente al público",new Font(FuenteDetalle)));
		contenidoqueja.setHorizontalAlignment(Element.ALIGN_LEFT);
		contenidoqueja.setColspan(6);
		contenidoqueja.setRowspan(2);
		table.addCell(contenidoqueja);
		
		
		return table;
	
	}
	
public static PdfPTable F_Reporte_AtencionReclamos(PdfPTable table,List<B_AtencionReclamosBean> reclamos, Font FuenteCabecera ,Font FuenteDetalle) throws Exception{
		 
		
		table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setFixedHeight(20);
		
		
		PdfPCell cabecerafechaemision = new PdfPCell(new Paragraph("NRO.",new Font(FuenteCabecera)));
		cabecerafechaemision.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabecerafechaemision); 
		
		PdfPCell cabecerafechaviaje = new PdfPCell(new Paragraph("EMPRESA",new Font(FuenteCabecera)));
		cabecerafechaviaje.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabecerafechaviaje);
		
		PdfPCell cabeceravoucher = new PdfPCell(new Paragraph("FECHA RECLAMO",new Font(FuenteCabecera)));
		cabeceravoucher.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceravoucher); 
		
		PdfPCell cabecerapasajero = new PdfPCell(new Paragraph("RECLAMANTE",new Font(FuenteCabecera)));
		cabecerapasajero.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabecerapasajero); 
		
		
		PdfPCell cabeceraorigen = new PdfPCell(new Paragraph("FECHA INCIDENTE",new Font(FuenteCabecera)));
		cabeceraorigen.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceraorigen); 
		
		 
		PdfPCell cabeceradestino = new PdfPCell(new Paragraph("SERVICIO",new Font(FuenteCabecera)));
		cabeceradestino.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceradestino);
		
		PdfPCell cabeceraservicio = new PdfPCell(new Paragraph("MOTIVO",new Font(FuenteCabecera)));
		cabeceraservicio.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceraservicio);
		
		PdfPCell cabeceratiporeclamo = new PdfPCell(new Paragraph("TIPO",new Font(FuenteCabecera)));
		cabeceratiporeclamo.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceratiporeclamo);
		
		
		PdfPCell cabeceratotal = new PdfPCell(new Paragraph("DOCUMENTO",new Font(FuenteCabecera)));
		cabeceratotal.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceratotal);
		
		PdfPCell cabeceraatendido = new PdfPCell(new Paragraph("ATENDIDO",new Font(FuenteCabecera)));
		cabeceraatendido.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceraatendido);
		
		
		PdfPCell cabecerafechaatencion = new PdfPCell(new Paragraph("FECHA ATENCIÓN",new Font(FuenteCabecera)));
		cabecerafechaatencion.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabecerafechaatencion);
		
		PdfPCell cabeceradetalle = new PdfPCell(new Paragraph("DETALLE",new Font(FuenteCabecera)));
		cabeceradetalle.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceradetalle);
		
		PdfPCell cabeceraUsuario = new PdfPCell(new Paragraph("USUARIO",new Font(FuenteCabecera)));
		cabeceraUsuario.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceraUsuario);
		
		PdfPCell cabeceraAgencia = new PdfPCell(new Paragraph("AGENCIA",new Font(FuenteCabecera)));
		cabeceraAgencia.setHorizontalAlignment(Element.ALIGN_CENTER);
		//celdapasajero.setBorder(Rectangle.NO_BORDER);
		table.addCell(cabeceraAgencia);
		
		
		
		for (B_AtencionReclamosBean lista : reclamos) {
			
			
			PdfPCell celdaNro = new PdfPCell(new Paragraph(""+lista.getNro(),new Font(FuenteDetalle)));
			celdaNro.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaNro.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaNro); 
			
			PdfPCell celdaEmpresa = new PdfPCell(new Paragraph(lista.getEmpresaD(),new Font(FuenteDetalle)));
			celdaEmpresa.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaEmpresa.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaEmpresa); 
			
			PdfPCell celdaFechaReclamo = new PdfPCell(new Paragraph(lista.getFechaReclamo(),new Font(FuenteDetalle)));
			celdaFechaReclamo.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaFechaReclamo.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaFechaReclamo); 
			
			PdfPCell celdaReclamante = new PdfPCell(new Paragraph(lista.getNombres()+" "+lista.getApePaterno(),new Font(FuenteDetalle)));
			celdaReclamante.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaReclamante.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaReclamante); 
			
			PdfPCell celdaFechaIncidente = new PdfPCell(new Paragraph(lista.getFechaIncidente(),new Font(FuenteDetalle)));
			celdaFechaIncidente.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaFechaIncidente.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaFechaIncidente); 
			
			PdfPCell celdaServicio = new PdfPCell(new Paragraph(lista.getServicioD(),new Font(FuenteDetalle)));
			celdaServicio.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaServicio.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaServicio); 
			
			PdfPCell celdaMotivoReclamo = new PdfPCell(new Paragraph(lista.getMotivoReclamoD(),new Font(FuenteDetalle)));
			celdaMotivoReclamo.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaMotivoReclamo.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaMotivoReclamo); 
			
			PdfPCell celdaTipo = new PdfPCell(new Paragraph(lista.getTipoReclamoD(),new Font(FuenteDetalle)));
			celdaTipo.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaTipo.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaTipo); 
			
			PdfPCell celdaDocumento = new PdfPCell(new Paragraph(lista.getSerie()+"-"+lista.getNumero(),new Font(FuenteDetalle)));
			celdaDocumento.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaDocumento.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaDocumento); 
			
			PdfPCell celdaAtendido = new PdfPCell(new Paragraph(lista.getAtendido(),new Font(FuenteDetalle)));
			celdaAtendido.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaAtendido.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaAtendido); 
			
			PdfPCell celdaFechaAtencion = new PdfPCell(new Paragraph(lista.getFechaAtencion(),new Font(FuenteDetalle)));
			celdaFechaAtencion.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaFechaAtencion.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaFechaAtencion); 
			
			PdfPCell celdaDetalleAtencion = new PdfPCell(new Paragraph(lista.getDetalleAtencion(),new Font(FuenteDetalle)));
			celdaDetalleAtencion.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaDetalleAtencion.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaDetalleAtencion); 
			
			PdfPCell celdaUsuario = new PdfPCell(new Paragraph(lista.getUsuario(),new Font(FuenteDetalle)));
			celdaUsuario.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaUsuario.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaUsuario);
			
			PdfPCell celdaAgencia = new PdfPCell(new Paragraph(lista.getAgenciaD(),new Font(FuenteDetalle)));
			celdaAgencia.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaAgencia.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaAgencia);
			
			
		}
	
		return table;
		
		}

	public String getUser() {
		return user;
	}

	@SuppressWarnings("static-access")
	public void setUser(String user) {
		this.user = user;
	}

	public String getDireccion() {
		return direccion;
	}

	@SuppressWarnings("static-access")
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	@SuppressWarnings("static-access")
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}




