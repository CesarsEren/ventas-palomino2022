package pe.com.grupopalomino.sistema.boletaje.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.zxing.oned.Code128Writer;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.bcel.internal.generic.LLOAD;
import com.sun.prism.Graphics;
import com.visural.common.ThreadContextObjectInputStream;

//import net.sourceforge.jbarcodebean.*;
//import net.sourceforge.jbarcodebean.model.Code128;
//import net.sourceforge.jbarcodebean.model.Ean8;
import pe.com.grupopalomino.sistema.boletaje.action.FacturacionDescargaAction;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.schedule.EliminaVentasNoConfirmadas;

public class FuncionesFacturacionPDF {
	private static final Log log = LogFactory.getLog(FuncionesFacturacionPDF.class);

	public static Date sumarRestarDiasFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de días a añadir, o restar en caso de días<0
		return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}

	public static PdfPTable F_Facturacion_Electronica_Encomiendas_Ticket(PdfPTable table,
			V_Varios_FacturacionBean facturacionEmpresa, Map<String, Object> mapaVentas,
			V_Ventas_FacturacionBean ventaImages) {

		Font fuente = new Font(FontFamily.HELVETICA);
		fuente.setSize((float) 6.5);

		Font fuenteBold = new Font(FontFamily.HELVETICA);
		fuenteBold.setSize((float) 6.5);
		fuenteBold.setStyle(Font.BOLD);

		Font fuenteBoldDocumento = new Font(FontFamily.HELVETICA);
		fuenteBoldDocumento.setSize((float) 13);
		fuenteBoldDocumento.setStyle(Font.BOLD);

		PdfPCell celdaEmpresa = new PdfPCell(new Paragraph(facturacionEmpresa.getEmpresaD().toString(), fuenteBold));
		celdaEmpresa.setColspan(4);
		celdaEmpresa.setBorder(Rectangle.NO_BORDER);
		celdaEmpresa.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaEmpresa);

		PdfPCell celdaEmpresaDireccion = new PdfPCell(
				new Paragraph(facturacionEmpresa.getDireccion().toString(), fuente));
		celdaEmpresaDireccion.setColspan(4);
		celdaEmpresaDireccion.setBorder(Rectangle.NO_BORDER);
		celdaEmpresaDireccion.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaEmpresaDireccion);

		Paragraph paragrapfRuc = new Paragraph("RUC :  " + facturacionEmpresa.getRuc(), fuenteBold);
		paragrapfRuc.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaruc = new PdfPCell(paragrapfRuc);
		celdaruc.setColspan(4);
		celdaruc.setBorder(Rectangle.NO_BORDER);
		celdaruc.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaruc);

		Paragraph paragrapfTipoDocumento = new Paragraph(mapaVentas.get("TipoDocumentoD").toString(), fuenteBold);
		paragrapfTipoDocumento.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTipoDocumento = new PdfPCell(paragrapfTipoDocumento);
		celdaTipoDocumento.setColspan(4);
		celdaTipoDocumento.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTipoDocumento.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaTipoDocumento);

		Paragraph paragrapfVacio = new Paragraph(" ", fuente);
		paragrapfVacio.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio = new PdfPCell(paragrapfVacio);
		celdaVacio.setColspan(4);
		celdaVacio.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaVacio.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaVacio);

		Paragraph paragrapfDocumento = new Paragraph(mapaVentas.get("DocumentoElectronico").toString(),
				fuenteBoldDocumento);
		paragrapfDocumento.setAlignment(Element.ALIGN_CENTER);

		PdfPCell celdadocumento = new PdfPCell(paragrapfDocumento);
		celdadocumento.setColspan(4);
		celdadocumento.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdadocumento.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdadocumento);

		Paragraph paragrapfVacio1 = new Paragraph(" ", fuente);
		paragrapfVacio1.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio1 = new PdfPCell(paragrapfVacio1);
		celdaVacio1.setColspan(4);
		celdaVacio1.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaVacio1.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaVacio1);

		/*
		log.info(mapaVentas.get("Pago").toString());

		log.info(mapaVentas.get("Pago").toString().equals("CREDITO"));*/

		// AGREGADO POR JCHC

		if (mapaVentas.get("NroOrdenRef") != null && mapaVentas.get("NroOrdenRef").toString().trim().length() > 5) {

			Paragraph paragrapfFechaEmision = new Paragraph(
					"Fecha Emision:  " + mapaVentas.get("FechaEmisionD").toString(), fuente);
			paragrapfFechaEmision.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaFechaEmision = new PdfPCell(paragrapfFechaEmision);
			celdaFechaEmision.setColspan(4);
			celdaFechaEmision.setHorizontalAlignment(Element.ALIGN_LEFT);
			// celdaFechaEmision.setBorder(Rectangle.BOTTOM);
			// celdaFechaEmision.setBorderWidthBottom(1);
			celdaFechaEmision.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaFechaEmision);


			if (mapaVentas.get("Pago").toString().equals("CREDITO")) {

				Date fechaDate = null;
				// Date fechaDate = null;
				String lafecha = mapaVentas.get("FechaEmisionD").toString().replace("00:00:00.000", "");
				Date nvalfec;
				String valnuevafec = "";
				try {
					fechaDate = new SimpleDateFormat("dd/MM/yyyy").parse(lafecha);
					nvalfec = sumarRestarDiasFecha(fechaDate, 45);
					DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
					valnuevafec = dateformat.format(nvalfec);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String fecha_vencimiento = mapaVentas.get("FechaVencimiento").toString();
				Paragraph paragrapfFechaNueva= null;				
				if(fecha_vencimiento == null|| fecha_vencimiento.isEmpty()) {
					  paragrapfFechaNueva = new Paragraph("Fecha Vencimiento :  " + valnuevafec, fuente);
				}else {
					 DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
				        DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
				        String formattedDate = fecha_vencimiento;
				        Date date;
						try {
							date = originalFormat.parse(fecha_vencimiento);
							    formattedDate = targetFormat.format(date); 
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				      
				        
						paragrapfFechaNueva = new Paragraph("Fecha Vencimiento :  " + formattedDate, fuente);
						paragrapfFechaNueva.setAlignment(Element.ALIGN_JUSTIFIED);
				}
			
				paragrapfFechaNueva.setAlignment(Element.ALIGN_JUSTIFIED);

				PdfPCell celdaFechaNueva = new PdfPCell(paragrapfFechaNueva);
				celdaFechaNueva.setColspan(4);
				celdaFechaNueva.setHorizontalAlignment(Element.ALIGN_LEFT);
				// celdaFechaNueva.setBorder(Rectangle.BOTTOM);
				celdaFechaNueva.setBorderWidthBottom(0);
				table.addCell(celdaFechaNueva);

			}

			Paragraph paragrapfNroOrdenReferencia = new Paragraph(
					"Referencia :  " + mapaVentas.get("NroOrdenRef").toString(), fuente);
			paragrapfNroOrdenReferencia.setAlignment(Element.ALIGN_JUSTIFIED);
			PdfPCell celdaNroOrdenReferencia = new PdfPCell(paragrapfNroOrdenReferencia);
			celdaNroOrdenReferencia.setColspan(4);
			celdaNroOrdenReferencia.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaNroOrdenReferencia.setBorder(Rectangle.BOTTOM);
			celdaNroOrdenReferencia.setBorderWidthBottom(1);
			table.addCell(celdaNroOrdenReferencia);

		} else {

			Paragraph paragrapfFechaEmision = new Paragraph(
					"Fecha Emision:  " + mapaVentas.get("FechaEmisionD").toString(), fuente);
			paragrapfFechaEmision.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaFechaEmision = new PdfPCell(paragrapfFechaEmision);
			celdaFechaEmision.setColspan(4);
			celdaFechaEmision.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaFechaEmision.setBorder(Rectangle.BOTTOM);
			celdaFechaEmision.setBorderWidthBottom(1);
			celdaFechaEmision.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaFechaEmision);

			if (mapaVentas.get("Pago").toString().equals("CREDITO")) {

				Date fechaDate = null;
				// Date fechaDate = null;
				String lafecha = mapaVentas.get("FechaEmisionD").toString().replace("00:00:00.000", "");
				Date nvalfec;
				String valnuevafec = "";

				try {
					fechaDate = new SimpleDateFormat("dd/MM/yyyy").parse(lafecha);
					nvalfec = sumarRestarDiasFecha(fechaDate, 45);
					DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
					valnuevafec = dateformat.format(nvalfec);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String fecha_vencimiento = mapaVentas.get("FechaVencimiento").toString();
				Paragraph paragrapfFechaNueva= null;
				if(fecha_vencimiento == null|| fecha_vencimiento.isEmpty()) {
					  paragrapfFechaNueva = new Paragraph("Fecha Vencimiento :  " + valnuevafec, fuente);
					paragrapfFechaNueva.setAlignment(Element.ALIGN_JUSTIFIED);
				}else {
					 
			        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
			        DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
			        String formattedDate = fecha_vencimiento;
			        Date date;
					try {
						date = originalFormat.parse(fecha_vencimiento);
						    formattedDate = targetFormat.format(date); 
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      
			        
					paragrapfFechaNueva = new Paragraph("Fecha Vencimiento :  " + formattedDate, fuente);
					paragrapfFechaNueva.setAlignment(Element.ALIGN_JUSTIFIED);
				} 

				PdfPCell celdaFechaNueva = new PdfPCell(paragrapfFechaNueva);
				celdaFechaNueva.setColspan(4);
				celdaFechaNueva.setHorizontalAlignment(Element.ALIGN_LEFT);
				celdaFechaNueva.setBorder(Rectangle.NO_BORDER);
				celdaFechaNueva.setBorderWidthBottom(1);
				table.addCell(celdaFechaNueva);

			}

		}
		//

		Paragraph paragrapfDestino = new Paragraph("Destino:   " + mapaVentas.get("DestinoD").toString(), fuenteBold);
		paragrapfDestino.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDestino = new PdfPCell(paragrapfDestino);
		celdaDestino.setColspan(4);
		celdaDestino.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaDestino.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaDestino);

		String ladetra;
		DecimalFormat formato1 = new DecimalFormat("#.00");
		ladetra = String.valueOf(formato1.format(Float.parseFloat(mapaVentas.get("Total").toString())));

		if (mapaVentas.get("TipoDocumento").equals("01")) {
			if (mapaVentas.get("SerieElectronica").equals("F390")) {
				formato1 = new DecimalFormat("#.00");
				ladetra = String.valueOf(formato1.format(Float.parseFloat(mapaVentas.get("Total").toString())
						- (Float.parseFloat(mapaVentas.get("Total").toString()) * Float.parseFloat("0.10"))));
			} else {
				formato1 = new DecimalFormat("#.00");
				ladetra = String.valueOf(formato1.format(Float.parseFloat(mapaVentas.get("Total").toString())
						- (Float.parseFloat(mapaVentas.get("Total").toString()) * Float.parseFloat("0.04"))));
			}
		}

		long dias_de_vencimiento = 0;
		String fecha_vencimiento = mapaVentas.get("FechaVencimiento").toString();
		String fecha_emision = mapaVentas.get("FechaEmision").toString();
		Date date_vencimiento= null;
		Date date_emision  = null;
		try {
			  date_vencimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_vencimiento.trim());
			  date_emision = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_emision.trim());
			
			 long diff = date_vencimiento.getTime() - date_emision.getTime();

		        TimeUnit time = TimeUnit.DAYS;
		        dias_de_vencimiento = time.convert(diff, TimeUnit.MILLISECONDS);
			
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}

		String cadenaFpago = "CREDITO";
		String ValorOperacion = "";
		String ValorCuota = "Cuota 001 (Neto) : " + ladetra + " ("+dias_de_vencimiento+" Días).";

		if (!mapaVentas.get("Pago").toString().equals("CREDITO")) {
			cadenaFpago = "CONTADO";
			ValorOperacion = mapaVentas.get("Pago").toString();
			ValorCuota = "Neto : " + ladetra;
		}

		Paragraph paragrapTCuota = new Paragraph(ValorCuota, fuente);
		paragrapTCuota.setAlignment(Element.ALIGN_LEFT);
		PdfPCell celdaCuota = new PdfPCell(paragrapTCuota);
		celdaCuota.setColspan(6);
		celdaCuota.setBorder(Rectangle.NO_BORDER);
		celdaCuota.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaCuota);

		Paragraph paragrapToperacion = new Paragraph("Operación:   " + ValorOperacion, fuente);
		paragrapToperacion.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaOperacion = new PdfPCell(paragrapToperacion);
		celdaOperacion.setColspan(6);
		celdaOperacion.setBorder(Rectangle.NO_BORDER);
		celdaOperacion.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaOperacion);

		// Paragraph paragrapfTipoPago = new Paragraph("F.Pago: " +
		// mapaVentas.get("Pago").toString(), fuente);
		Paragraph paragrapfTipoPago = new Paragraph("F.Pago:   " + cadenaFpago, fuente);
		paragrapfTipoPago.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTipoPago = new PdfPCell(paragrapfTipoPago);
		celdaTipoPago.setColspan(4);
		celdaTipoPago.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaTipoPago.setBorder(Rectangle.BOTTOM);
		celdaTipoPago.setBorderWidthBottom(1);
		table.addCell(celdaTipoPago);

		Paragraph paragrapfTextoRemitente = new Paragraph("Cliente/Remitente", fuente);
		paragrapfTextoRemitente.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTextoRemitente = new PdfPCell(paragrapfTextoRemitente);
		celdaTextoRemitente.setColspan(4);
		celdaTextoRemitente.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTextoRemitente.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaTextoRemitente);

		Paragraph paragrapfDocumentoRemitente = new Paragraph(
				(mapaVentas.get("TipoDocumento").equals("03") ? "DNI:   " + mapaVentas.get("DNI").toString()
						: "RUC:   " + mapaVentas.get("Ruc").toString()),
				fuente);
		paragrapfDocumentoRemitente.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDocumentoRemitente = new PdfPCell(paragrapfDocumentoRemitente);
		celdaDocumentoRemitente.setColspan(4);
		celdaDocumentoRemitente.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaDocumentoRemitente.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaDocumentoRemitente);

		Paragraph paragrapfNombreRemitente = new Paragraph(
				(mapaVentas.get("TipoDocumento").equals("03") ? mapaVentas.get("Nombre").toString()
						: mapaVentas.get("Razon").toString()),
				fuente);
		paragrapfNombreRemitente.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaRemitente = new PdfPCell(paragrapfNombreRemitente);
		celdaRemitente.setColspan(4);
		celdaRemitente.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaRemitente.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaRemitente);

		if (mapaVentas.get("TipoDocumento").equals("01")) {

			Paragraph paragrapfNombreRemitenteDir = new Paragraph(mapaVentas.get("Direccion").toString(), fuente);
			paragrapfNombreRemitenteDir.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaRemitenteDir = new PdfPCell(paragrapfNombreRemitenteDir);
			celdaRemitenteDir.setColspan(4);
			celdaRemitenteDir.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaRemitenteDir.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaRemitenteDir);
		}

		Paragraph paragrapfTextoConsignado = new Paragraph("Consignado", fuente);
		paragrapfTextoConsignado.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTextoConsignado = new PdfPCell(paragrapfTextoConsignado);
		celdaTextoConsignado.setColspan(4);
		celdaTextoConsignado.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTextoConsignado.setBorder(Rectangle.TOP);
		table.addCell(celdaTextoConsignado);

		Paragraph paragrapfNombreConsignado = new Paragraph(mapaVentas.get("Consignado").toString(), fuente);
		paragrapfNombreConsignado.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaConsignado = new PdfPCell(paragrapfNombreConsignado);
		celdaConsignado.setColspan(4);
		celdaConsignado.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaConsignado.setBorder(Rectangle.BOTTOM);
		celdaConsignado.setBorderWidthBottom(1);
		table.addCell(celdaConsignado);

		Paragraph paragrapfTextoDetalle = new Paragraph("CANT.", fuente);
		paragrapfTextoDetalle.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDetalle = new PdfPCell(paragrapfTextoDetalle);
		celdaDetalle.setColspan(1);
		celdaDetalle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaDetalle.setBorder(Rectangle.BOTTOM);
		celdaDetalle.setBorder(Rectangle.RIGHT);
		celdaDetalle.setBorderWidthBottom(1);
		celdaDetalle.setBorderWidthRight(1);
		table.addCell(celdaDetalle);

		Paragraph paragrapfTextoDetalle1 = new Paragraph("DESCRIPCION", fuente);
		paragrapfTextoDetalle1.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDetalle1 = new PdfPCell(paragrapfTextoDetalle1);
		celdaDetalle1.setColspan(2);
		celdaDetalle1.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaDetalle1.setBorder(Rectangle.BOTTOM);
		celdaDetalle1.setBorder(Rectangle.RIGHT);
		celdaDetalle1.setBorderWidthBottom(1);
		celdaDetalle1.setBorderWidthRight(1);
		table.addCell(celdaDetalle1);

		Paragraph paragrapfTextoDetalle2 = new Paragraph("TOTAL", fuente);
		paragrapfTextoDetalle2.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDetalle2 = new PdfPCell(paragrapfTextoDetalle2);
		celdaDetalle2.setColspan(1);
		celdaDetalle2.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaDetalle2.setBorder(Rectangle.BOTTOM);
		celdaDetalle2.setBorderWidthBottom(1);
		table.addCell(celdaDetalle2);

		if (!mapaVentas.get("Cantidad1").toString().isEmpty() && !mapaVentas.get("Descripcion1").toString().isEmpty()) {

			Paragraph paragrapfCantidad1 = new Paragraph(mapaVentas.get("Cantidad1").toString(), fuente);
			paragrapfCantidad1.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaCantidad1 = new PdfPCell(paragrapfCantidad1);
			celdaCantidad1.setColspan(1);
			celdaCantidad1.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaCantidad1.setBorder(Rectangle.NO_BORDER);

			Paragraph paragrapfDescripcion1 = new Paragraph(mapaVentas.get("Descripcion1").toString(), fuente);
			paragrapfDescripcion1.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaDescripcion1 = new PdfPCell(paragrapfDescripcion1);
			celdaDescripcion1.setColspan(2);
			celdaDescripcion1.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaDescripcion1.setBorder(Rectangle.NO_BORDER);

			Paragraph paragrapfTotal1 = new Paragraph(
					(mapaVentas.get("TipoDocumento").equals("01") ? mapaVentas.get("TotalSinIGV").toString()
							: mapaVentas.get("Total").toString()),
					fuente);
			paragrapfTotal1.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaTotal1 = new PdfPCell(paragrapfTotal1);
			celdaTotal1.setColspan(1);
			celdaTotal1.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaTotal1.setBorder(Rectangle.NO_BORDER);

			if (true/*
					 * mapaVentas.get("Cantidad2").toString().isEmpty() &&
					 * mapaVentas.get("Descripcion2").toString().isEmpty()
					 */) {
				if (true/*
						 * mapaVentas.get("Cantidad3").toString().isEmpty() &&
						 * mapaVentas.get("Descripcion3").toString().isEmpty()
						 */) {

					/*
					 * celdaCantidad1.setBorder(Rectangle.BOTTOM);
					 * celdaDescripcion1.setBorder(Rectangle.BOTTOM);
					 * celdaTotal1.setBorder(Rectangle.BOTTOM);
					 * celdaCantidad1.setBorderWidthBottom(1);
					 * celdaDescripcion1.setBorderWidthBottom(1);
					 * celdaTotal1.setBorderWidthBottom(1);
					 */
				}
			}
			table.addCell(celdaCantidad1);
			table.addCell(celdaDescripcion1);
			table.addCell(celdaTotal1);

		}

		if (!mapaVentas.get("Cantidad2").toString().isEmpty() && !mapaVentas.get("Descripcion2").toString().isEmpty()) {

			Paragraph paragrapfCantidad2 = new Paragraph(mapaVentas.get("Cantidad2").toString(), fuente);
			paragrapfCantidad2.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaCantidad2 = new PdfPCell(paragrapfCantidad2);
			celdaCantidad2.setColspan(1);
			celdaCantidad2.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaCantidad2.setBorder(Rectangle.NO_BORDER);

			Paragraph paragrapfDescripcion2 = new Paragraph(mapaVentas.get("Descripcion2").toString(), fuente);
			paragrapfDescripcion2.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaDescripcion2 = new PdfPCell(paragrapfDescripcion2);
			celdaDescripcion2.setColspan(2);
			celdaDescripcion2.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaDescripcion2.setBorder(Rectangle.NO_BORDER);

			Paragraph paragrapfTotal2 = new Paragraph("", fuente);
			paragrapfTotal2.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaTotal2 = new PdfPCell(paragrapfTotal2);
			celdaTotal2.setColspan(1);
			celdaTotal2.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaTotal2.setBorder(Rectangle.NO_BORDER);

			if (true/*
					 * mapaVentas.get("Cantidad3").toString().isEmpty() &&
					 * mapaVentas.get("Descripcion3").toString().isEmpty()
					 */) {
				/*
				 * celdaCantidad2.setBorder(Rectangle.BOTTOM);
				 * celdaDescripcion2.setBorder(Rectangle.BOTTOM);
				 * celdaTotal2.setBorder(Rectangle.BOTTOM);
				 * celdaCantidad2.setBorderWidthBottom(1);
				 * celdaDescripcion2.setBorderWidthBottom(1);
				 * celdaTotal2.setBorderWidthBottom(1);
				 */
			}
			table.addCell(celdaCantidad2);
			table.addCell(celdaDescripcion2);
			table.addCell(celdaTotal2);

		}

		if (!mapaVentas.get("Cantidad3").toString().isEmpty() && !mapaVentas.get("Descripcion3").toString().isEmpty()) {

			Paragraph paragrapfCantidad3 = new Paragraph(mapaVentas.get("Cantidad3").toString(), fuente);
			paragrapfCantidad3.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaCantidad3 = new PdfPCell(paragrapfCantidad3);
			celdaCantidad3.setColspan(1);
			celdaCantidad3.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaCantidad3.setBorder(Rectangle.NO_BORDER);

			Paragraph paragrapfDescripcion3 = new Paragraph(mapaVentas.get("Descripcion3").toString(), fuente);
			paragrapfDescripcion3.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaDescripcion3 = new PdfPCell(paragrapfDescripcion3);
			celdaDescripcion3.setColspan(2);
			celdaDescripcion3.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaDescripcion3.setBorder(Rectangle.NO_BORDER);

			Paragraph paragrapfTotal3 = new Paragraph("", fuente);
			paragrapfTotal3.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaTotal3 = new PdfPCell(paragrapfTotal3);
			celdaTotal3.setColspan(1);
			celdaTotal3.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaTotal3.setBorder(Rectangle.NO_BORDER);

			if (true/*
					 * !mapaVentas.get("Cantidad2").toString().isEmpty() &&
					 * !mapaVentas.get("Descripcion2").toString().isEmpty()
					 */) {
				/*
				 * celdaCantidad3.setBorder(Rectangle.BOTTOM);
				 * celdaDescripcion3.setBorder(Rectangle.BOTTOM);
				 * celdaTotal3.setBorder(Rectangle.BOTTOM);
				 * celdaCantidad3.setBorderWidthBottom(1);
				 * celdaDescripcion3.setBorderWidthBottom(1);
				 * celdaTotal3.setBorderWidthBottom(1);
				 */
			}
			table.addCell(celdaCantidad3);
			table.addCell(celdaDescripcion3);
			table.addCell(celdaTotal3);
		}

		Paragraph paragrapfKilos = new Paragraph("KG: " + mapaVentas.get("Kilos").toString(), fuente);
		paragrapfKilos.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaKilos = new PdfPCell(paragrapfKilos);
		celdaKilos.setColspan(4);
		celdaKilos.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaKilos.setBorder(Rectangle.BOTTOM);
		celdaKilos.setBorderWidthBottom(1);
		table.addCell(celdaKilos);

		if (mapaVentas.get("TipoDocumento").toString().equals("01")) {

			Paragraph paragrapfTextoOperacionGravada = new Paragraph("OPERACIÓN GRAVADA:", fuente);
			paragrapfTextoOperacionGravada.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaTextoOperacionGravada = new PdfPCell(paragrapfTextoOperacionGravada);
			celdaTextoOperacionGravada.setColspan(3);
			celdaTextoOperacionGravada.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaTextoOperacionGravada.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaTextoOperacionGravada);

			Paragraph paragrapfOperacionGravadaTotal = new Paragraph(mapaVentas.get("TotalSinIGV").toString(), fuente);
			paragrapfOperacionGravadaTotal.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaOperacionGravadaTotal = new PdfPCell(paragrapfOperacionGravadaTotal);
			celdaOperacionGravadaTotal.setColspan(1);
			celdaOperacionGravadaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaOperacionGravadaTotal.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaOperacionGravadaTotal);

			Paragraph paragrapfTextoIGV = new Paragraph("I.G.V.:", fuente);
			paragrapfTextoIGV.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaTextoIGV = new PdfPCell(paragrapfTextoIGV);
			celdaTextoIGV.setColspan(3);
			celdaTextoIGV.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaTextoIGV.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaTextoIGV);

			Paragraph paragrapfIGV = new Paragraph(mapaVentas.get("IGV").toString(), fuente);
			paragrapfIGV.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPCell celdaIGV = new PdfPCell(paragrapfIGV);
			celdaIGV.setColspan(1);
			celdaIGV.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaIGV.setBorder(Rectangle.NO_BORDER);
			table.addCell(celdaIGV);

		}

		Paragraph paragrapfTextoTotal = new Paragraph("TOTAL (S/): ", fuenteBold);
		paragrapfTextoTotal.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTextoTotal = new PdfPCell(paragrapfTextoTotal);
		celdaTextoTotal.setColspan(3);
		celdaTextoTotal.setHorizontalAlignment(Element.ALIGN_LEFT);

		Paragraph paragrapfTotal = new Paragraph(mapaVentas.get("Total").toString(), fuenteBold);
		paragrapfTotal.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTotal = new PdfPCell(paragrapfTotal);
		celdaTotal.setColspan(1);
		celdaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);

		if (mapaVentas.get("TipoDocumento").toString().equals("01")) {
			celdaTextoTotal.setBorder(Rectangle.NO_BORDER);
			celdaTotal.setBorder(Rectangle.NO_BORDER);
		} else {
			celdaTextoTotal.setBorder(Rectangle.BOTTOM);
			celdaTextoTotal.setBorderWidthTop(1);
			celdaTotal.setBorder(Rectangle.BOTTOM);
			celdaTotal.setBorderWidthTop(1);
		}
		table.addCell(celdaTextoTotal);
		table.addCell(celdaTotal);

		Paragraph paragrapfNombreTotal = new Paragraph("SON : " + mapaVentas.get("MontoLetras").toString(), fuente);
		paragrapfNombreTotal.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaNombreTotal = new PdfPCell(paragrapfNombreTotal);
		celdaNombreTotal.setColspan(4);
		celdaNombreTotal.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaNombreTotal.setBorder(Rectangle.BOTTOM);
		celdaNombreTotal.setBorderWidthBottom(1);
		table.addCell(celdaNombreTotal);

		Paragraph paragrapfAgencia = new Paragraph("Agencia: " + mapaVentas.get("AgenciaD").toString(), fuente);
		paragrapfAgencia.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaAgencia = new PdfPCell(paragrapfAgencia);
		celdaAgencia.setColspan(2);
		celdaAgencia.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaAgencia.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaAgencia);

		Paragraph paragrapfFechaImp = new Paragraph("Fec.Imp: " + mapaVentas.get("FechaImpresion").toString(), fuente);
		paragrapfFechaImp.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaFechaImp = new PdfPCell(paragrapfFechaImp);
		celdaFechaImp.setColspan(2);
		celdaFechaImp.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaFechaImp.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaFechaImp);

		Paragraph paragrapfUsuario = new Paragraph("Usuario: " + mapaVentas.get("Usuario").toString(), fuente);
		paragrapfUsuario.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaUsuario = new PdfPCell(paragrapfUsuario);
		celdaUsuario.setColspan(2);
		celdaUsuario.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaUsuario.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaUsuario);

		Paragraph paragrapfHoraImp = new Paragraph("Hor.Imp: " + mapaVentas.get("HoraImpresion").toString(), fuente);
		paragrapfHoraImp.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaHoraImp = new PdfPCell(paragrapfHoraImp);
		celdaHoraImp.setColspan(2);
		celdaHoraImp.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaHoraImp.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaHoraImp);

		Paragraph paragrapfVacio3 = new Paragraph(" ", fuente);
		paragrapfVacio3.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio3 = new PdfPCell(paragrapfVacio3);
		celdaVacio3.setColspan(4);
		celdaVacio3.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaVacio3.setBorder(Rectangle.TOP);
		table.addCell(celdaVacio3);

		Paragraph paragrapfRepresentacion = new Paragraph("Representación impresa del Documento Electrónico,"
				+ "esta puede ser consultada en www.grupopalomino.com.pe", fuente);
		paragrapfRepresentacion.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaRepresentacion = new PdfPCell(paragrapfRepresentacion);
		celdaRepresentacion.setColspan(4);
		celdaRepresentacion.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaRepresentacion.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaRepresentacion);

		Paragraph paragrapfVacio4 = new Paragraph(" ", fuente);
		paragrapfVacio4.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio4 = new PdfPCell(paragrapfVacio3);
		celdaVacio4.setColspan(4);
		celdaVacio4.setBorder(Rectangle.NO_BORDER);
		celdaVacio4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacio4);
		String poliza = "2003-7525524";

		if (facturacionEmpresa.getEmpresa().toString().equals("002")) {
			poliza = "2003-7525605";
		}

		Paragraph paragrapfCondiciones = new Paragraph(
				"Al recibir el presente DOCUMENTO,acepto todos los términos y condiciones"
						+ " del contrato del servicio de transporte detallado en el letrero,banner y/o panel a la vista"
						+ " ubicados en el counter de ventas al momento de la compra, incluido que el pasajero viaja asegurado"
						+ " mediante una poliza " + poliza
						+ " de seguro(SOAT) contratada con la compañia  de seguros Rimac."
						+ "todo lo mencionado tambien se encuentran publicados en la página web: www.grupopalomino.com.pe",
				fuente);
		paragrapfCondiciones.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaCondiciones = new PdfPCell(paragrapfCondiciones);
		celdaCondiciones.setColspan(4);
		celdaCondiciones.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaCondiciones.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaCondiciones);

		Paragraph paragrapfVacio5 = new Paragraph(" ", fuente);
		paragrapfVacio5.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio5 = new PdfPCell(paragrapfVacio3);
		celdaVacio5.setColspan(4);
		celdaVacio5.setBorder(Rectangle.NO_BORDER);
		celdaVacio5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacio5);

		PdfPCell celdaCodigoHash = new PdfPCell(
				new Paragraph((ventaImages.getCodigohash() == null ? "" : ventaImages.getCodigohash()), fuenteBold));
		celdaCodigoHash.setColspan(4);
		celdaCodigoHash.setBorder(Rectangle.NO_BORDER);
		celdaCodigoHash.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaCodigoHash);

		Paragraph paragrapfVacio6 = new Paragraph(" ", fuente);
		paragrapfVacio6.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio6 = new PdfPCell(paragrapfVacio3);
		celdaVacio6.setColspan(4);
		celdaVacio6.setBorder(Rectangle.NO_BORDER);
		celdaVacio6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacio6);

		Image images = null;
		PdfPTable tableBarras = null;

		try {

			tableBarras = new PdfPTable(3);

			float[] medidaCeldas = { 0.15f, 1.1f, 0.15f };
			tableBarras.setWidths(medidaCeldas);
			StringBuilder texto = new StringBuilder();
			texto.append(mapaVentas.get("Empresa").toString() + "'"); // texto.append();
			String cadena = mapaVentas.get("DocumentoElectronico").toString();
			texto.append(cadena.toString());

			BarcodeQRCode barcodeQRCode2 = new BarcodeQRCode(texto.toString(), 1000, 1000, null);
			images = barcodeQRCode2.getImage();
			/*
			 * images = Image.getInstance( (ventaImages.getImageQR() != null ?
			 * ventaImages.getImageQR() : ventaImages.getImage()));
			 */
		} catch (BadElementException e) {
			e.printStackTrace();

		} /*
			 * catch (MalformedURLException e) { e.printStackTrace();
			 * 
			 * } catch (IOException e) { e.printStackTrace(); }
			 */ catch (DocumentException e) {
			e.printStackTrace();
		}

		PdfPCell celdaBarra1 = new PdfPCell();
		celdaBarra1.setColspan(1);
		celdaBarra1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaBarra1.setBorder(Rectangle.NO_BORDER);
		tableBarras.addCell(celdaBarra1);

		PdfPCell celdaBarra = new PdfPCell();
		celdaBarra.setFixedHeight(95f);
		celdaBarra.addElement(images);
		celdaBarra.setColspan(1);
		celdaBarra.setBorder(Rectangle.NO_BORDER);
		celdaBarra.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableBarras.addCell(celdaBarra);

		PdfPCell celdaBarra2 = new PdfPCell();
		celdaBarra2.setColspan(1);
		celdaBarra2.setBorder(Rectangle.NO_BORDER);
		celdaBarra2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableBarras.addCell(celdaBarra2);

		PdfPCell celdaBarraVacia = new PdfPCell(tableBarras);
		celdaBarraVacia.setColspan(4);
		celdaBarraVacia.setBorder(Rectangle.NO_BORDER);
		celdaBarraVacia.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaBarraVacia);

		return table;

	}

	private byte[] getBarcodeImg(String cod) throws Exception {

		Barcode128 code128 = new Barcode128();
		code128.setCode(cod);
		java.awt.Image img = code128.createAwtImage(Color.BLACK, Color.WHITE);

		BufferedImage outImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		outImage.getGraphics().drawImage(img, 0, 0, null);
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
		ImageIO.write(outImage, "jpeg", bytesOut);
		bytesOut.flush();
		byte[] jpgImageData = bytesOut.toByteArray();
		// String path = ctx.getRealPath("WebContent/img/barcodes")+ File.separator +
		// cod+".jpg";
		//
		// System.out.println(path);
		//
		// FileOutputStream fos = new FileOutputStream(path);
		// fos.write( jpgImageData);
		// fos.flush();
		// fos.close();
		//
		// Image image = Image.getInstance(jpgImageData);
		return jpgImageData;

	}

	public static PdfPTable F_Facturacion_Electronica_Pasajes_Ticket(PdfPTable table,
			V_Varios_FacturacionBean facturacionEmpresa, Map<String, Object> mapaVentas,
			V_Ventas_FacturacionBean ventaImages) throws Exception {

		V_AgenciasBean bean = new V_AgenciasBean();
		String DireccionEmbarque = "";

		bean = FuncionesGeneralesUtils.F_Agencias(mapaVentas.get("Origen").toString());

		boolean mostrarembarque;

		if (bean != null) {
			mostrarembarque = true;
			DireccionEmbarque = bean.getDireccion();
		} else {
			mostrarembarque = false;
		}

		String ladetra;
		DecimalFormat formato1 = new DecimalFormat("#.00");
		// ladetra=String.valueOf(formato1.format(Float.parseFloat(mapaVentas.get("Total").toString())
		// - (Float.parseFloat(mapaVentas.get("Total").toString()) *
		// Float.parseFloat("0.10"))));
		ladetra = String.valueOf(formato1.format(Float.parseFloat(mapaVentas.get("Total").toString())));

		if (mapaVentas.get("TipoDocumento").equals("01")) {
			ladetra = String.valueOf(formato1.format(Float.parseFloat(mapaVentas.get("Total").toString())
					- (Float.parseFloat(mapaVentas.get("Total").toString()) * Float.parseFloat("0.10"))));
		}

		Font fuente = new Font(FontFamily.HELVETICA);
		fuente.setSize((float) 6.5);

		Font fuenteBold = new Font(FontFamily.HELVETICA);
		fuenteBold.setSize((float) 6.5);
		fuenteBold.setStyle(Font.BOLD);

		Font fuenteBoldDocumento = new Font(FontFamily.HELVETICA);
		fuenteBoldDocumento.setSize((float) 13);
		fuenteBoldDocumento.setStyle(Font.BOLD);

		PdfPCell celdalogo = new PdfPCell(new Paragraph(facturacionEmpresa.getEmpresaD(), fuenteBold));
		celdalogo.setColspan(4);
		celdalogo.setBorder(Rectangle.NO_BORDER);
		celdalogo.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdalogo);

		Paragraph paragrapfDireccionEmpresa = new Paragraph(facturacionEmpresa.getDireccion(), fuente);
		paragrapfDireccionEmpresa.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDireccionEmpresa = new PdfPCell(paragrapfDireccionEmpresa);
		celdaDireccionEmpresa.setColspan(4);
		celdaDireccionEmpresa.setBorder(Rectangle.NO_BORDER);
		celdaDireccionEmpresa.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaDireccionEmpresa);

		Paragraph paragrapfRuc = new Paragraph("RUC :    " + facturacionEmpresa.getRuc(), fuenteBold);
		paragrapfRuc.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaruc = new PdfPCell(paragrapfRuc);
		celdaruc.setColspan(4);
		celdaruc.setBorder(Rectangle.NO_BORDER);
		celdaruc.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaruc);

		Paragraph paragrapfTipoDocumento = new Paragraph(mapaVentas.get("TipoDocumentoD").toString(), fuenteBold);
		paragrapfTipoDocumento.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTipoDocumento = new PdfPCell(paragrapfTipoDocumento);
		celdaTipoDocumento.setColspan(4);
		celdaTipoDocumento.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTipoDocumento.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaTipoDocumento);

		Paragraph paragrapfVacio = new Paragraph(" ", fuente);
		paragrapfVacio.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio = new PdfPCell(paragrapfVacio);
		celdaVacio.setColspan(4);
		celdaVacio.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaVacio.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaVacio);

		Paragraph paragrapfComprobante = new Paragraph(mapaVentas.get("DocumentoElectronico").toString(),
				fuenteBoldDocumento);
		paragrapfComprobante.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdadoComprobante = new PdfPCell(paragrapfComprobante);
		celdadoComprobante.setColspan(4);
		celdadoComprobante.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdadoComprobante.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdadoComprobante);

		Paragraph paragrapfVacio1 = new Paragraph(" ", fuente);
		paragrapfVacio1.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio1 = new PdfPCell(paragrapfVacio1);
		celdaVacio1.setColspan(4);
		celdaVacio1.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaVacio1.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaVacio1);

		Paragraph paragrapfDocumento = new Paragraph(
				(mapaVentas.get("TipoDocumento").equals("01") ? "RUC :     " + mapaVentas.get("Ruc").toString()
						: "DNI :     " + mapaVentas.get("DNI").toString()),
				fuente);
		paragrapfDocumento.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDocumento = new PdfPCell(paragrapfDocumento);
		celdaDocumento.setColspan(4);
		celdaDocumento.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaDocumento.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaDocumento);

		Paragraph paragrapfCliente = new Paragraph((mapaVentas.get("TipoDocumento").toString().equals("01")
				? "RAZON SOCIAL :     " + mapaVentas.get("Razon").toString()
				: "NOMBRE :     " + mapaVentas.get("Nombre").toString()), fuente);
		paragrapfCliente.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaCliente = new PdfPCell(paragrapfCliente);
		celdaCliente.setColspan(4);
		celdaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaCliente.setBorder(Rectangle.BOTTOM);
		celdaCliente.setBorderWidthBottom(1);
		table.addCell(celdaCliente);

		PdfPTable tableDetalle = new PdfPTable(4);
		tableDetalle.setWidthPercentage(100f);

		float[] medidaCeldas = { 0.55f, 0.45f, 0.85f, 0.35f };

		try {

			tableDetalle.setWidths(medidaCeldas);

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Paragraph paragrapfTextoDetalle = new Paragraph("#", fuente);
		paragrapfTextoDetalle.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDetalle = new PdfPCell(paragrapfTextoDetalle);
		celdaDetalle.setColspan(1);
		celdaDetalle.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaDetalle.setBorder(Rectangle.BOTTOM);
		celdaDetalle.setBorder(Rectangle.RIGHT);
		celdaDetalle.setBorderWidthBottom(1);
		celdaDetalle.setBorderWidthRight(1);
		tableDetalle.addCell(celdaDetalle);

		Paragraph paragrapfTextoDetalle1 = new Paragraph("DETALLE", fuente);
		paragrapfTextoDetalle1.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDetalle1 = new PdfPCell(paragrapfTextoDetalle1);
		celdaDetalle1.setColspan(2);
		celdaDetalle1.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaDetalle1.setBorder(Rectangle.BOTTOM);
		celdaDetalle1.setBorder(Rectangle.RIGHT);
		celdaDetalle1.setBorderWidthBottom(1);
		celdaDetalle1.setBorderWidthRight(1);
		tableDetalle.addCell(celdaDetalle1);

		Paragraph paragrapfTextoDetalle2 = new Paragraph("TOTAL", fuente);
		paragrapfTextoDetalle2.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDetalle2 = new PdfPCell(paragrapfTextoDetalle2);
		celdaDetalle2.setColspan(1);
		celdaDetalle2.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaDetalle2.setBorder(Rectangle.BOTTOM);
		celdaDetalle2.setBorderWidthBottom(1);
		tableDetalle.addCell(celdaDetalle2);

		Paragraph paragrapfCantidad1 = new Paragraph("1", fuente);
		paragrapfCantidad1.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaCantidad1 = new PdfPCell(paragrapfCantidad1);
		celdaCantidad1.setColspan(1);
		celdaCantidad1.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaCantidad1.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaCantidad1);

		Paragraph paragrapfDescripcion1 = new Paragraph("SERVICIO DE TRANSPORTE EN LA", fuente);
		paragrapfDescripcion1.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaDescripcion1 = new PdfPCell(paragrapfDescripcion1);
		celdaDescripcion1.setColspan(2);
		celdaDescripcion1.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaDescripcion1.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDescripcion1);

		Paragraph paragrapfTotal1 = new Paragraph(mapaVentas.get("Total").toString(), fuente);
		paragrapfTotal1.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTotal1 = new PdfPCell(paragrapfTotal1);
		celdaTotal1.setColspan(1);
		celdaTotal1.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTotal1.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaTotal1);

		Paragraph paragrapfDetalleVacio = new Paragraph(" ", fuente);
		paragrapfDetalleVacio.setAlignment(Element.ALIGN_RIGHT);

		PdfPCell celdaDetalleVacio = new PdfPCell(paragrapfDetalleVacio);
		celdaDetalleVacio.setColspan(1);
		celdaDetalleVacio.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaDetalleVacio.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDetalleVacio);

		Paragraph paragrapfRutaTexto = new Paragraph("RUTA :", fuente);
		paragrapfRutaTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaRutaTexto = new PdfPCell(paragrapfRutaTexto);
		celdaRutaTexto.setColspan(1);
		celdaRutaTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaRutaTexto.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaRutaTexto);

		Paragraph paragrapfRuta = new Paragraph(mapaVentas.get("DestinoD").toString(), fuente);
		paragrapfRuta.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaRuta = new PdfPCell(paragrapfRuta);
		celdaRuta.setColspan(2);
		celdaRuta.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaRuta.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaRuta);

		Paragraph paragrapfDetalleVacio1 = new Paragraph(" ", fuente);
		paragrapfDetalleVacio1.setAlignment(Element.ALIGN_RIGHT);

		PdfPCell celdaDetalleVacio1 = new PdfPCell(paragrapfDetalleVacio1);
		celdaDetalleVacio1.setColspan(1);
		celdaDetalleVacio1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaDetalleVacio1.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDetalleVacio1);

		Paragraph paragrapfAsientoTexto = new Paragraph("ASIENTO :", fuente);
		paragrapfAsientoTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaAsientoTextto = new PdfPCell(paragrapfAsientoTexto);
		celdaAsientoTextto.setColspan(1);
		celdaAsientoTextto.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaAsientoTextto.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaAsientoTextto);

		Paragraph paragrapfAsiento = new Paragraph(mapaVentas.get("Asiento").toString(), fuente);
		paragrapfAsiento.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaAsiento = new PdfPCell(paragrapfAsiento);
		celdaAsiento.setColspan(2);
		celdaAsiento.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaAsiento.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaAsiento);

		Paragraph paragrapfDetalleVacio2 = new Paragraph(" ", fuente);
		paragrapfDetalleVacio2.setAlignment(Element.ALIGN_RIGHT);

		PdfPCell celdaDetalleVacio2 = new PdfPCell(paragrapfDetalleVacio2);
		celdaDetalleVacio2.setColspan(1);
		celdaDetalleVacio2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaDetalleVacio2.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDetalleVacio2);

		Paragraph paragrapfPasajeroTexto = new Paragraph("PAX :", fuente);
		paragrapfPasajeroTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaPasajeroTexto = new PdfPCell(paragrapfPasajeroTexto);
		celdaPasajeroTexto.setColspan(1);
		celdaPasajeroTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaPasajeroTexto.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaPasajeroTexto);

		Paragraph paragrapfPasajero = new Paragraph(mapaVentas.get("Nombre").toString(), fuente);
		paragrapfPasajero.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaPasajero = new PdfPCell(paragrapfPasajero);
		celdaPasajero.setColspan(2);
		celdaPasajero.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaPasajero.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaPasajero);

		Paragraph paragrapfDetalleVacio3 = new Paragraph(" ", fuente);
		paragrapfDetalleVacio3.setAlignment(Element.ALIGN_RIGHT);

		PdfPCell celdaDetalleVacio3 = new PdfPCell(paragrapfDetalleVacio3);
		celdaDetalleVacio3.setColspan(1);
		celdaDetalleVacio3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaDetalleVacio3.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDetalleVacio3);

		Paragraph paragrapfDniTexto = new Paragraph("DNI :", fuente);
		paragrapfDniTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaDniTexto = new PdfPCell(paragrapfDniTexto);
		celdaDniTexto.setColspan(1);
		celdaDniTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaDniTexto.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDniTexto);

		Paragraph paragrapfDNI = new Paragraph(mapaVentas.get("DNI").toString(), fuente);
		paragrapfDNI.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaDNI = new PdfPCell(paragrapfDNI);
		celdaDNI.setColspan(2);
		celdaDNI.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaDNI.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDNI);

		Paragraph paragrapfDetalleVacio4 = new Paragraph(" ", fuente);
		paragrapfDetalleVacio4.setAlignment(Element.ALIGN_RIGHT);

		PdfPCell celdaDetalleVacio4 = new PdfPCell(paragrapfDetalleVacio4);
		celdaDetalleVacio4.setColspan(1);
		celdaDetalleVacio4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaDetalleVacio4.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDetalleVacio4);

		// Paragraph paragrapfPagoTexto = new Paragraph("F. PAGO :", fuente);
		// paragrapfPagoTexto.setAlignment(Element.ALIGN_LEFT);

		// PdfPCell celdaPagoTexto = new PdfPCell(paragrapfPagoTexto);
		// celdaPagoTexto.setColspan(1);
		// celdaPagoTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		// celdaPagoTexto.setBorder(Rectangle.NO_BORDER);
		// tableDetalle.addCell(celdaPagoTexto);

		// Paragraph paragrapfPago = new Paragraph(mapaVentas.get("Pago").toString(),
		// fuente);
		// paragrapfPago.setAlignment(Element.ALIGN_LEFT);

		// PdfPCell celdaPago = new PdfPCell(paragrapfPago);
		// celdaPago.setColspan(2);
		// celdaPago.setHorizontalAlignment(Element.ALIGN_LEFT);
		// celdaPago.setBorder(Rectangle.NO_BORDER);
		// tableDetalle.addCell(celdaPago);
		/*
		 * Paragraph paragrapfDetalleVacio5 = new Paragraph(" ", fuente);
		 * paragrapfDetalleVacio5.setAlignment(Element.ALIGN_RIGHT);
		 * 
		 * PdfPCell celdaDetalleVacio5 = new PdfPCell(paragrapfDetalleVacio5);
		 * celdaDetalleVacio5.setColspan(1);
		 * celdaDetalleVacio5.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 * celdaDetalleVacio5.setBorder(Rectangle.NO_BORDER);
		 * tableDetalle.addCell(celdaDetalleVacio5);
		 */

		Paragraph paragrapfViajeTexto = new Paragraph("F.VIAJE :", fuente);
		paragrapfViajeTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaViajeTexto = new PdfPCell(paragrapfViajeTexto);
		celdaViajeTexto.setColspan(1);
		celdaViajeTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaViajeTexto.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaViajeTexto);

		Paragraph paragrapfViaje = new Paragraph(mapaVentas.get("FechaViaje").toString(), fuente);
		paragrapfViaje.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaViaje = new PdfPCell(paragrapfViaje);
		celdaViaje.setColspan(2);
		celdaViaje.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaViaje.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaViaje);

		Paragraph paragrapfDetalleVacio6 = new Paragraph(" ", fuente);
		paragrapfDetalleVacio6.setAlignment(Element.ALIGN_RIGHT);

		PdfPCell celdaDetalleVacio6 = new PdfPCell(paragrapfDetalleVacio6);
		celdaDetalleVacio6.setColspan(1);
		celdaDetalleVacio6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaDetalleVacio6.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDetalleVacio6);

		Paragraph paragrapfHoraTexto = new Paragraph("H.VIAJE :", fuente);
		paragrapfHoraTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaHoraTexto = new PdfPCell(paragrapfHoraTexto);
		celdaHoraTexto.setColspan(1);
		celdaHoraTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaHoraTexto.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaHoraTexto);

		Paragraph paragrapfHora = new Paragraph(mapaVentas.get("HoraViaje").toString(), fuente);
		paragrapfHora.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaHora = new PdfPCell(paragrapfHora);
		celdaHora.setColspan(2);
		celdaHora.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaHora.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaHora);

		Paragraph paragrapfDetalleVacio7 = new Paragraph(" ", fuente);
		paragrapfDetalleVacio7.setAlignment(Element.ALIGN_RIGHT);

		PdfPCell celdaDetalleVacio7 = new PdfPCell(paragrapfDetalleVacio7);
		celdaDetalleVacio7.setColspan(1);
		celdaDetalleVacio7.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaDetalleVacio7.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaDetalleVacio7);

		Paragraph paragrapfAgenciaTexto = new Paragraph("COMPRA EN:", fuente);
		paragrapfAgenciaTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaAgenciaTexto = new PdfPCell(paragrapfAgenciaTexto);
		celdaAgenciaTexto.setColspan(1);
		celdaAgenciaTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaAgenciaTexto.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaAgenciaTexto);

		Paragraph paragrapfAgencia = new Paragraph(mapaVentas.get("AgenciaD").toString(), fuente);
		paragrapfAgencia.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaAgencia = new PdfPCell(paragrapfAgencia);
		celdaAgencia.setColspan(2);
		celdaAgencia.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaAgencia.setBorder(Rectangle.NO_BORDER);
		tableDetalle.addCell(celdaAgencia);

		/****/

		if (mostrarembarque) {

			Paragraph paragrapfDetalleVacio8 = new Paragraph(" ", fuente);
			paragrapfDetalleVacio8.setAlignment(Element.ALIGN_RIGHT);

			PdfPCell celdaDetalleVacio8 = new PdfPCell(paragrapfDetalleVacio8);
			celdaDetalleVacio8.setColspan(1);
			celdaDetalleVacio8.setHorizontalAlignment(Element.ALIGN_RIGHT);
			celdaDetalleVacio8.setBorder(Rectangle.NO_BORDER);
			tableDetalle.addCell(celdaDetalleVacio8);

			Paragraph paragrapfAgenciaEmbTexto = new Paragraph("EMBARQUE EN:", fuente);
			paragrapfAgenciaEmbTexto.setAlignment(Element.ALIGN_LEFT);

			PdfPCell celdaAgenciaEmbTexto = new PdfPCell(paragrapfAgenciaEmbTexto);
			celdaAgenciaEmbTexto.setColspan(1);
			celdaAgenciaEmbTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaAgenciaEmbTexto.setBorder(Rectangle.NO_BORDER);
			tableDetalle.addCell(celdaAgenciaEmbTexto);

			Paragraph paragrapfAgenciaEmb = new Paragraph(mapaVentas.get("OrigenD").toString(), fuente);
			paragrapfAgenciaEmb.setAlignment(Element.ALIGN_LEFT);

			PdfPCell celdaAgenciaEmb = new PdfPCell(paragrapfAgenciaEmb);
			celdaAgenciaEmb.setColspan(2);
			celdaAgenciaEmb.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaAgenciaEmb.setBorder(Rectangle.NO_BORDER);
			tableDetalle.addCell(celdaAgenciaEmb);

			Paragraph paragrapfDetalleVacio9 = new Paragraph(" ", fuente);
			paragrapfDetalleVacio9.setAlignment(Element.ALIGN_RIGHT);

			PdfPCell celdaDetalleVacio9 = new PdfPCell(paragrapfDetalleVacio9);
			celdaDetalleVacio9.setColspan(1);
			celdaDetalleVacio9.setHorizontalAlignment(Element.ALIGN_RIGHT);
			celdaDetalleVacio9.setBorder(Rectangle.NO_BORDER);
			tableDetalle.addCell(celdaDetalleVacio9);

			Paragraph paragrapfAgenciaEmbTexto1 = new Paragraph("LUGAR:", fuente);
			paragrapfAgenciaEmbTexto1.setAlignment(Element.ALIGN_LEFT);

			PdfPCell celdaAgenciaEmbTexto1 = new PdfPCell(paragrapfAgenciaEmbTexto1);
			celdaAgenciaEmbTexto1.setColspan(1);
			celdaAgenciaEmbTexto1.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaAgenciaEmbTexto1.setBorder(Rectangle.NO_BORDER);
			tableDetalle.addCell(celdaAgenciaEmbTexto1);

			Paragraph paragrapfAgenciaEmb1 = new Paragraph(DireccionEmbarque, fuente);
			paragrapfAgenciaEmb1.setAlignment(Element.ALIGN_LEFT);

			PdfPCell celdaAgenciaEmb1 = new PdfPCell(paragrapfAgenciaEmb1);
			celdaAgenciaEmb1.setColspan(2);
			celdaAgenciaEmb1.setHorizontalAlignment(Element.ALIGN_LEFT);
			celdaAgenciaEmb1.setBorder(Rectangle.NO_BORDER);
			tableDetalle.addCell(celdaAgenciaEmb1);

		}
		/****/
		PdfPCell celdaDetalles = new PdfPCell();
		celdaDetalles.addElement(tableDetalle);
		celdaDetalles.setColspan(4);
		celdaDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaDetalles.setBorder(Rectangle.BOTTOM);
		celdaDetalles.setBorderWidthBottom(1);
		table.addCell(celdaDetalles);

		// CULMINANDO DE AGREGAR LA TABLA DE DETALLE
		// *********************************

		Paragraph paragrapfTextoOperacionGravada = new Paragraph("OPERACIÓN NO GRAVADA:", fuente);
		paragrapfTextoOperacionGravada.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTextoOperacionGravada = new PdfPCell(paragrapfTextoOperacionGravada);
		celdaTextoOperacionGravada.setColspan(3);
		celdaTextoOperacionGravada.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaTextoOperacionGravada.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaTextoOperacionGravada);

		Paragraph paragrapfOperacionGravadaTotal = new Paragraph(mapaVentas.get("Total").toString(), fuente);
		paragrapfOperacionGravadaTotal.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaOperacionGravadaTotal = new PdfPCell(paragrapfOperacionGravadaTotal);
		celdaOperacionGravadaTotal.setColspan(1);
		celdaOperacionGravadaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaOperacionGravadaTotal.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaOperacionGravadaTotal);

		Paragraph paragrapfTextoIGV = new Paragraph("I.G.V.:", fuente);
		paragrapfTextoIGV.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTextoIGV = new PdfPCell(paragrapfTextoIGV);
		celdaTextoIGV.setColspan(3);
		celdaTextoIGV.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaTextoIGV.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaTextoIGV);

		Paragraph paragrapfIGV = new Paragraph(mapaVentas.get("IGV").toString(), fuente);
		paragrapfIGV.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaIGV = new PdfPCell(paragrapfIGV);
		celdaIGV.setColspan(1);
		celdaIGV.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaIGV.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaIGV);

		Paragraph paragrapfTextoTotal = new Paragraph("TOTAL (S/): ", fuenteBold);
		paragrapfTextoTotal.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTextoTotal = new PdfPCell(paragrapfTextoTotal);
		celdaTextoTotal.setColspan(3);
		celdaTextoTotal.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaTextoTotal.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaTextoTotal);

		Paragraph paragrapfTotal = new Paragraph(mapaVentas.get("Total").toString(), fuenteBold);
		paragrapfTotal.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTotal = new PdfPCell(paragrapfTotal);
		celdaTotal.setColspan(1);
		celdaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTotal.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaTotal);

		Paragraph paragrapfNombreTotal = new Paragraph(mapaVentas.get("MontoLetras").toString(), fuente);
		paragrapfNombreTotal.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaNombreTotal = new PdfPCell(paragrapfNombreTotal);
		celdaNombreTotal.setColspan(4);
		celdaNombreTotal.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaNombreTotal.setBorder(Rectangle.BOTTOM);
		celdaNombreTotal.setBorderWidthBottom(1);
		table.addCell(celdaNombreTotal);

		Paragraph paragrapfFechaEmision = new Paragraph("FECHA EMISION: " + mapaVentas.get("FechaEmisionD").toString(),
				fuente);
		paragrapfFechaEmision.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaFechaEmision = new PdfPCell(paragrapfFechaEmision);
		celdaFechaEmision.setColspan(4);
		celdaFechaEmision.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaFechaEmision.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaFechaEmision);

		Paragraph paragrapfFechaNueva;

		if (mapaVentas.get("Pago").toString().equals("CREDITO")) {
			/*
			 * Date fechaDate = null; Date nvalfec; try { fechaDate = new
			 * SimpleDateFormat("yyyy-MM-dd").parse(mapaVentas.get("FechaEmisionD").toString
			 * ()); } catch (ParseException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } nvalfec=sumarRestarDiasFecha(fechaDate,45);*
			 * DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); String
			 * valnuevafec=dateformat.format(nvalfec);
			 */
			String ValorCuota = "Cuota 001 : " + ladetra + " (45 Días).";

			paragrapfFechaNueva = new Paragraph("F. PAGO : CREDITO \n Cuota 001 (Neto): " + ladetra
					+ " (45 Días). \n" /* + " Fecha Vencimiento :  " + valnuevafec */, fuente);
			paragrapfFechaNueva.setAlignment(Element.ALIGN_JUSTIFIED);

		} else {
			paragrapfFechaNueva = new Paragraph("F. PAGO : CONTADO \n (Neto) : " + ladetra, fuente);
			paragrapfFechaNueva.setAlignment(Element.ALIGN_JUSTIFIED);
		}

		PdfPCell celdaFechaNueva = new PdfPCell(paragrapfFechaNueva);
		celdaFechaNueva.setColspan(4);
		celdaFechaNueva.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaFechaNueva.setBorder(Rectangle.BOTTOM);
		celdaFechaNueva.setBorderWidthBottom(1);
		table.addCell(celdaFechaNueva);

		Paragraph paragrapfUsuario = new Paragraph("USUARIO: " + mapaVentas.get("Usuario").toString(), fuente);
		paragrapfUsuario.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaUsuario = new PdfPCell(paragrapfUsuario);
		celdaUsuario.setColspan(4);
		celdaUsuario.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaUsuario.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaUsuario);

		Paragraph paragrapfHoraImp = new Paragraph("HORA IMPRES: " + mapaVentas.get("HoraImpresion").toString(),
				fuente);
		paragrapfHoraImp.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaHoraImp = new PdfPCell(paragrapfHoraImp);
		celdaHoraImp.setColspan(4);
		celdaHoraImp.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaHoraImp.setBorder(Rectangle.BOTTOM);
		celdaHoraImp.setBorderWidthBottom(1);
		table.addCell(celdaHoraImp);

		PdfPCell celdaVacia = new PdfPCell(new Paragraph(" "));
		celdaVacia.setColspan(4);
		celdaVacia.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaVacia);

		Paragraph paragrapfRepresentacion = new Paragraph("Representación impresa del Documento Electrónico,"
				+ "esta puede ser consultada en www.grupopalomino.com.pe", fuente);
		paragrapfRepresentacion.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaRepresentacion = new PdfPCell(paragrapfRepresentacion);
		celdaRepresentacion.setColspan(4);
		celdaRepresentacion.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaRepresentacion.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaRepresentacion);

		Paragraph paragrapfVacio4 = new Paragraph(" ", fuente);
		paragrapfVacio4.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio4 = new PdfPCell(paragrapfVacio4);
		celdaVacio4.setColspan(4);
		celdaVacio4.setBorder(Rectangle.NO_BORDER);
		celdaVacio4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacio4);

		String poliza = "2003-7525524";

		if (facturacionEmpresa.getEmpresa().toString().equals("002")) {
			poliza = "2003-7525605";
		}

		Paragraph paragrapfCondiciones = new Paragraph(
				"Al recibir el presente DOCUMENTO,acepto todos los términos y condiciones"
						+ " del contrato del servicio de transporte detallado en el letrero,banner y/o panel a la vista"
						+ " ubicados en el counter de ventas al momento de la compra, incluido que el pasajero viaja asegurado"
						+ " mediante una poliza " + poliza
						+ " de seguro(SOAT) contratada con la compañia  de seguros Rimac."
						+ "todo lo mencionado tambien se encuentran publicados en la página web: www.grupopalomino.com.pe",
				fuente);
		paragrapfCondiciones.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaCondiciones = new PdfPCell(paragrapfCondiciones);
		celdaCondiciones.setColspan(4);
		celdaCondiciones.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaCondiciones.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaCondiciones);

		Paragraph paragrapfVacio5 = new Paragraph(" ", fuente);
		paragrapfVacio5.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio5 = new PdfPCell(paragrapfVacio5);
		celdaVacio5.setColspan(4);
		celdaVacio5.setBorder(Rectangle.NO_BORDER);
		celdaVacio5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacio5);

		PdfPCell celdaCodigoHash = new PdfPCell(
				new Paragraph((ventaImages.getCodigohash() == null ? "" : ventaImages.getCodigohash()), fuenteBold));
		celdaCodigoHash.setColspan(4);
		celdaCodigoHash.setBorder(Rectangle.NO_BORDER);
		celdaCodigoHash.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaCodigoHash);

		Paragraph paragrapfVacio6 = new Paragraph(" ", fuente);
		paragrapfVacio6.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio6 = new PdfPCell(paragrapfVacio6);
		celdaVacio6.setColspan(4);
		celdaVacio6.setBorder(Rectangle.NO_BORDER);
		celdaVacio6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacio6);

		// Image images = null;
		Image images = null;
		Image images2 = null;
		BufferedImage imagecb = null;
		PdfPTable tableBarras = null;
		PdfPTable tableBarras1 = null;

		try {
			tableBarras = new PdfPTable(3);
			float[] medidaCeldasBarra = { 0.15f, 1.1f, 0.15f };
			tableBarras.setWidths(medidaCeldasBarra);

			tableBarras1 = new PdfPTable(3);
			tableBarras1.setWidths(medidaCeldasBarra);

			StringBuilder texto = new StringBuilder();
			texto.append(mapaVentas.get("Empresa").toString() + "'"); // texto.append();
			String cadena = mapaVentas.get("DocumentoElectronico").toString();
			texto.append(cadena.toString());

			// nuevo intento

			/*
			 * BarcodePDF417 barcode = new BarcodePDF417(); ByteArrayInputStream inputStream
			 * = null; barcode.setText(texto.toString()); ByteArrayOutputStream bytesOut =
			 * new ByteArrayOutputStream(); img = barcode.createAwtImage(Color.BLACK,
			 * Color.WHITE); BufferedImage outImage = new BufferedImage(img.getWidth(null),
			 * img.getHeight(null), BufferedImage.TYPE_INT_RGB); barcode.setErrorLevel(5);
			 * barcode.setLenCodewords(BarcodePDF417.PDF417_FORCE_BINARY);
			 * outImage.getGraphics().drawImage(img, 0, 0, null); ImageIO.write(outImage,
			 * "png", bytesOut); bytesOut.flush(); bytesOut.close(); byte[] pngImageData =
			 * bytesOut.toByteArray(); inputStream = new ByteArrayInputStream(pngImageData);
			 * Image.getInstance(inputStream.toString());
			 */

			// Fin intento

			try {

				Barcode128 code128 = new Barcode128();
				code128.setCode(texto.toString());
				java.awt.Image img = code128.createAwtImage(Color.BLACK, Color.WHITE);

				BufferedImage outImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
						BufferedImage.TYPE_INT_RGB);
				outImage.getGraphics().drawImage(img, 0, 0, null);
				ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
				ImageIO.write(outImage, "jpeg", bytesOut);
				bytesOut.flush();
				byte[] jpgImageData = bytesOut.toByteArray();

				images = Image.getInstance(jpgImageData);
				images.scalePercent(100, 100);

				System.out.println(
						"GENERAR CODIGOBARRAS EAN --> NRO: " + mapaVentas.get("DocumentoElectronico").toString());

				BarcodeQRCode barcodeQRCode2 = new BarcodeQRCode(texto.toString(), 1000, 1000, null);
				images2 = barcodeQRCode2.getImage();

			} catch (Exception e) {
				System.out.println(
						"ERROR AL GENERAR CODIGOBARRAS --> NRO: " + mapaVentas.get("DocumentoElectronico").toString());
				images = Image.getInstance(
						(ventaImages.getImageQR() != null ? ventaImages.getImageQR() : ventaImages.getImage()));
				e.printStackTrace();
			}

		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		PdfPCell celdaBarra1 = new PdfPCell();
		celdaBarra1.setColspan(1);
		celdaBarra1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaBarra1.setBorder(Rectangle.NO_BORDER);
		tableBarras.addCell(celdaBarra1);

		PdfPCell celdaBarra = new PdfPCell();
		celdaBarra.setFixedHeight(85f);
		celdaBarra.addElement(images2);
		celdaBarra.setColspan(1);
		celdaBarra.setBorder(Rectangle.NO_BORDER);
		celdaBarra.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableBarras.addCell(celdaBarra);

		PdfPCell celdaBarraQr = new PdfPCell();
		celdaBarraQr.setFixedHeight(105f);
		celdaBarraQr.setRotation(90);
		celdaBarraQr.addElement(images);
		celdaBarraQr.setColspan(1);
		celdaBarraQr.setBorder(Rectangle.NO_BORDER);
		celdaBarraQr.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaBarraQr.setPaddingTop(Element.ALIGN_TOP);
		tableBarras.addCell(celdaBarraQr);

		PdfPCell celdaBarra2 = new PdfPCell();
		celdaBarra2.setColspan(1);
		celdaBarra2.setBorder(Rectangle.NO_BORDER);
		celdaBarra2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableBarras.addCell(celdaBarra2);

		PdfPCell celdaBarraVacia = new PdfPCell(tableBarras);
		celdaBarraVacia.setColspan(4);
		celdaBarraVacia.setBorder(Rectangle.NO_BORDER);
		celdaBarraVacia.setHorizontalAlignment(Element.ALIGN_BOTTOM);
		table.addCell(celdaBarraVacia);

		// Image images1 = null;
		// PdfPTable tableBarras1 = null;
		/*
		 * try { tableBarras1 = new PdfPTable(3); float[] medidaCeldasBarra = { 0.15f,
		 * 1.1f, 0.15f }; tableBarras1.setWidths(medidaCeldasBarra);
		 * 
		 * images1 = Image.getInstance(ventaImages.getImagenCB());
		 * images1.scaleAbsolute(100, 100); } catch (BadElementException e) {
		 * e.printStackTrace(); } catch (DocumentException e) { e.printStackTrace(); }
		 * catch (MalformedURLException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */

		/*
		 * PdfPCell celdaBarra11 = new PdfPCell(); celdaBarra11.setColspan(1);
		 * celdaBarra11.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 * celdaBarra11.setBorder(Rectangle.NO_BORDER);
		 * tableBarras1.addCell(celdaBarra11);
		 * 
		 * PdfPCell celdaBarra111 = new PdfPCell(); celdaBarra111.setFixedHeight(95f);
		 * celdaBarra111.addElement(images); celdaBarra111.setColspan(1);
		 * celdaBarra111.setBorder(Rectangle.NO_BORDER);
		 * celdaBarra111.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 * tableBarras1.addCell(celdaBarra111);
		 * 
		 * PdfPCell celdaBarra12 = new PdfPCell(); celdaBarra12.setColspan(1);
		 * celdaBarra12.setBorder(Rectangle.NO_BORDER);
		 * celdaBarra12.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 * tableBarras1.addCell(celdaBarra12);
		 * 
		 * 
		 * PdfPCell celdaBarra1Vacia = new PdfPCell(tableBarras1);
		 * celdaBarra1Vacia.setColspan(4);
		 * celdaBarra1Vacia.setBorder(Rectangle.NO_BORDER);
		 * celdaBarra1Vacia.setHorizontalAlignment(Element.ALIGN_CENTER);
		 * table.addCell(celdaBarra1Vacia);
		 */

		return table;
	}

	/*
	 * String boleto = mapaVentas.get("DocumentoElectronico").toString(); String
	 * _boleto[]=boleto.split("-");
	 * 
	 * StringBuilder texto = new StringBuilder();
	 * texto.append(mapaVentas.get("DNI").toString()); //texto.append();
	 * texto.append(mapaVentas.get("DNI").toString());
	 * texto.append(mapaVentas.get("DNI").toString());
	 * texto.append(mapaVentas.get("DNI").toString()); texto.append("71807093|");
	 * texto.append("15221033|"); texto.append("B950|"); texto.append("0000033|");
	 * texto.append("3520436"); BarcodeQRCode barcodeQRCode2 = new
	 * BarcodeQRCode(texto.toString(), 1000, 1000, null); images =
	 * barcodeQRCode2.getImage(); images.scaleAbsolute(100, 100);
	 */

	public static PdfPTable F_Facturacion_Electronica_FormatoGrande(PdfPTable table,
			V_Varios_FacturacionBean facturacionEmpresa, Map<String, Object> mapaVentas,
			V_Ventas_FacturacionBean ventaImages, Image logo) {

		Font fuente = new Font(FontFamily.HELVETICA);
		fuente.setSize((float) 6.5);

		Font fuenteBold = new Font(FontFamily.HELVETICA);
		fuenteBold.setSize((float) 6.5);
		fuenteBold.setStyle(Font.BOLD);

		Font fuenteBoldDocumento = new Font(FontFamily.HELVETICA);
		fuenteBoldDocumento.setSize((float) 11);
		fuenteBoldDocumento.setStyle(Font.BOLD);

		PdfPCell celdaLogo = new PdfPCell();
		celdaLogo.addElement(logo);
		celdaLogo.setColspan(4);
		celdaLogo.setHorizontalAlignment(Element.ALIGN_LEFT);
		celdaLogo.setBorder(Rectangle.NO_BORDER);
		table.addCell(celdaLogo);

		PdfPTable tableDocumento = new PdfPTable(1);
		tableDocumento.setWidthPercentage(95f);

		Paragraph paragrapfEmisorRuc = new Paragraph("RUC:" + facturacionEmpresa.getRuc(), fuenteBoldDocumento);
		paragrapfEmisorRuc.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaEmisorRuc = new PdfPCell(paragrapfEmisorRuc);
		celdaEmisorRuc.setColspan(1);
		celdaEmisorRuc.setBorder(Rectangle.TOP);
		celdaEmisorRuc.setBorder(Rectangle.LEFT);
		celdaEmisorRuc.setBorder(Rectangle.RIGHT);
		celdaEmisorRuc.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDocumento.addCell(celdaEmisorRuc);

		Paragraph paragrapfEmisorDocumento = new Paragraph(mapaVentas.get("TipoDocumentoD").toString(),
				fuenteBoldDocumento);
		paragrapfEmisorDocumento.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaEmisorDocumento = new PdfPCell(paragrapfEmisorDocumento);
		celdaEmisorDocumento.setColspan(1);
		celdaEmisorDocumento.setBorder(Rectangle.LEFT);
		celdaEmisorDocumento.setBorder(Rectangle.RIGHT);
		celdaEmisorDocumento.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDocumento.addCell(celdaEmisorDocumento);

		Paragraph paragrapfEmisorSerieNumero = new Paragraph(mapaVentas.get("DocumentoElectronico").toString(),
				fuenteBoldDocumento);
		paragrapfEmisorSerieNumero.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaEmisorSerieNumero = new PdfPCell(paragrapfEmisorSerieNumero);
		celdaEmisorSerieNumero.setColspan(1);
		celdaEmisorSerieNumero.setBorder(Rectangle.BOTTOM);
		celdaEmisorSerieNumero.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableDocumento.addCell(celdaEmisorSerieNumero);

		PdfPCell celdaEmisor = new PdfPCell(tableDocumento);
		celdaEmisor.setColspan(3);
		celdaEmisor.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaEmisor);

		Paragraph paragrapfEmpresa = new Paragraph(facturacionEmpresa.getEmpresaD(), fuenteBold);
		paragrapfEmpresa.setAlignment(Element.ALIGN_MIDDLE);

		PdfPCell celdaEmpresa = new PdfPCell(paragrapfEmpresa);
		celdaEmpresa.setColspan(7);
		celdaEmpresa.setBorder(Rectangle.NO_BORDER);
		celdaEmpresa.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(celdaEmpresa);

		Paragraph paragrapfEmpresaDireccion = new Paragraph(facturacionEmpresa.getDireccion(), fuenteBold);
		paragrapfEmpresaDireccion.setAlignment(Element.ALIGN_MIDDLE);

		PdfPCell celdaEmpresaDireccion = new PdfPCell(paragrapfEmpresaDireccion);
		celdaEmpresaDireccion.setColspan(7);
		celdaEmpresaDireccion.setBorder(Rectangle.NO_BORDER);
		celdaEmpresaDireccion.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(celdaEmpresaDireccion);

		Paragraph paragrapfVacio0 = new Paragraph(" ", fuenteBold);
		paragrapfVacio0.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaVacio0 = new PdfPCell(paragrapfVacio0);
		celdaVacio0.setColspan(7);
		celdaVacio0.setBorder(Rectangle.NO_BORDER);
		celdaVacio0.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaVacio0);

		Paragraph paragrapfSenoresTexto = new Paragraph("Señores :", fuenteBold);
		paragrapfSenoresTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaSenoresTexto = new PdfPCell(paragrapfSenoresTexto);
		celdaSenoresTexto.setColspan(1);
		celdaSenoresTexto.setBorder(Rectangle.NO_BORDER);
		celdaSenoresTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaSenoresTexto);

		Paragraph paragrapfSenores = new Paragraph(mapaVentas.get("Razon").toString(), fuenteBold);
		paragrapfSenores.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaSenores = new PdfPCell(paragrapfSenores);
		celdaSenores.setColspan(4);
		celdaSenores.setBorder(Rectangle.NO_BORDER);
		celdaSenores.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaSenores);

		Paragraph paragrapfFechaEmisionTexto = new Paragraph("Fecha Emisión :", fuenteBold);
		paragrapfFechaEmisionTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaFechaEmisionTexto = new PdfPCell(paragrapfFechaEmisionTexto);
		celdaFechaEmisionTexto.setColspan(1);
		celdaFechaEmisionTexto.setBorder(Rectangle.NO_BORDER);
		celdaFechaEmisionTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaFechaEmisionTexto);

		Paragraph paragrapfFechaEmision = new Paragraph(mapaVentas.get("FechaEmisionD").toString(), fuenteBold);
		paragrapfFechaEmision.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaFechaEmision = new PdfPCell(paragrapfFechaEmision);
		celdaFechaEmision.setColspan(2);
		celdaFechaEmision.setBorder(Rectangle.NO_BORDER);
		celdaFechaEmision.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaFechaEmision);

		Paragraph paragrapfRucTexto = new Paragraph("RUC :", fuenteBold);
		paragrapfRucTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaRucTexto = new PdfPCell(paragrapfRucTexto);
		celdaRucTexto.setColspan(1);
		celdaRucTexto.setBorder(Rectangle.NO_BORDER);
		celdaRucTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaRucTexto);

		Paragraph paragrapfRuc = new Paragraph(mapaVentas.get("Ruc").toString(), fuenteBold);
		paragrapfRuc.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaRuc = new PdfPCell(paragrapfRuc);
		celdaRuc.setColspan(4);
		celdaRuc.setBorder(Rectangle.NO_BORDER);
		celdaRuc.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaRuc);

		Paragraph paragrapfMonedaTexto = new Paragraph("Moneda :", fuenteBold);
		paragrapfMonedaTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaMonedaTexto = new PdfPCell(paragrapfMonedaTexto);
		celdaMonedaTexto.setColspan(1);
		celdaMonedaTexto.setBorder(Rectangle.NO_BORDER);
		celdaMonedaTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaMonedaTexto);

		Paragraph paragrapfMoneda = new Paragraph(mapaVentas.get("Moneda").toString(), fuenteBold);
		paragrapfMoneda.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaMoneda = new PdfPCell(paragrapfMoneda);
		celdaMoneda.setColspan(2);
		celdaMoneda.setBorder(Rectangle.NO_BORDER);
		celdaMoneda.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaMoneda);

		// *************************

		Paragraph paragrapfDireccionTexto = new Paragraph("Dirección :", fuenteBold);
		paragrapfDireccionTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaDireccionTexto = new PdfPCell(paragrapfDireccionTexto);
		celdaDireccionTexto.setColspan(1);
		celdaDireccionTexto.setBorder(Rectangle.NO_BORDER);
		celdaDireccionTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaDireccionTexto);

		Paragraph paragrapfDireccion = new Paragraph(mapaVentas.get("Direccion").toString(), fuenteBold);
		paragrapfDireccion.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaDireccion = new PdfPCell(paragrapfDireccion);
		celdaDireccion.setColspan(6);
		celdaDireccion.setBorder(Rectangle.NO_BORDER);
		celdaDireccion.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaDireccion);

		// *************************

		String ValorDinamico = "";
		String ValorDinamico1 = "";
		String ValorOperacion = "";

		if (mapaVentas.get("Servicio").toString().equals("C")) {
			ValorDinamico = mapaVentas.get("Guia").toString();

			String cadenaFpago = "CREDITO";

			if (!mapaVentas.get("Pago").toString().equals("CREDITO")) {
				cadenaFpago = "CONTADO";
				ValorOperacion = mapaVentas.get("Pago").toString();
			}
			ValorDinamico1 = cadenaFpago;
		} else if (mapaVentas.get("Servicio").toString().equals("N")
				|| mapaVentas.get("Servicio").toString().equals("T")) {
			ValorDinamico = mapaVentas.get("DocumentoElectronicoAplicar").toString();
			ValorDinamico1 = mapaVentas.get("MotivoD").toString();
		}

		Paragraph paragrapfDocumentoTexto = new Paragraph(
				(mapaVentas.get("Servicio").toString().equals("N") || mapaVentas.get("Servicio").toString().equals("T"))
						? "Factura Electrónica :"
						: "Guia :",
				fuenteBold);
		paragrapfDocumentoTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaDocumentoTexto = new PdfPCell(paragrapfDocumentoTexto);
		celdaDocumentoTexto.setColspan(1);
		celdaDocumentoTexto.setBorder(Rectangle.NO_BORDER);
		celdaDocumentoTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaDocumentoTexto);

		Paragraph paragrapfDocumento = new Paragraph(ValorDinamico, fuenteBold);
		paragrapfDocumento.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaDocumento = new PdfPCell(paragrapfDocumento);
		celdaDocumento.setColspan(6);
		celdaDocumento.setBorder(Rectangle.NO_BORDER);
		celdaDocumento.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaDocumento);

		// *************************

		Paragraph paragrapfMotivoTexto = new Paragraph(
				(mapaVentas.get("Servicio").toString().equals("N") || mapaVentas.get("Servicio").toString().equals("T"))
						? "Motivo :"
						: "F.Pago :",
				fuenteBold);
		paragrapfMotivoTexto.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaMotivoTexto = new PdfPCell(paragrapfMotivoTexto);
		celdaMotivoTexto.setColspan(1);
		celdaMotivoTexto.setBorder(Rectangle.NO_BORDER);
		celdaMotivoTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaMotivoTexto);

		Paragraph paragrapfMotivo = new Paragraph(ValorDinamico1, fuenteBold);
		paragrapfMotivo.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaMotivo = new PdfPCell(paragrapfMotivo);
		celdaMotivo.setColspan(6);
		celdaMotivo.setBorder(Rectangle.NO_BORDER);
		celdaMotivo.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaMotivo);

		Paragraph paragrapToperacion = new Paragraph(ValorOperacion, fuenteBold);
		paragrapToperacion.setAlignment(Element.ALIGN_LEFT);

		PdfPCell celdaOperacion = new PdfPCell(paragrapfMotivo);
		celdaOperacion.setColspan(6);
		celdaOperacion.setBorder(Rectangle.NO_BORDER);
		celdaOperacion.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaOperacion);

		Paragraph paragrapfVacio2 = new Paragraph(" ", fuenteBold);
		paragrapfVacio2.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio2 = new PdfPCell(paragrapfVacio2);
		celdaVacio2.setColspan(7);
		celdaVacio2.setBorder(Rectangle.NO_BORDER);
		celdaVacio2.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaVacio2);

		// CABECERA DE LA DATA

		Paragraph paragrapfCantidad = new Paragraph("CANTIDAD", fuenteBold);
		paragrapfCantidad.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaCantidad = new PdfPCell(paragrapfCantidad);
		celdaCantidad.setColspan(1);
		celdaCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaCantidad);

		Paragraph paragrapfDescripcion = new Paragraph("DESCRIPCION", fuenteBold);
		paragrapfDescripcion.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDescripcion = new PdfPCell(paragrapfDescripcion);
		celdaDescripcion.setColspan(3);
		celdaDescripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaDescripcion);

		Paragraph paragrapfUM = new Paragraph("U.M.", fuenteBold);
		paragrapfUM.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaUM = new PdfPCell(paragrapfUM);
		celdaUM.setColspan(1);
		celdaUM.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaUM);

		Paragraph paragrapfPrecio = new Paragraph("PRECIO UNITARIO", fuenteBold);
		paragrapfPrecio.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaPrecio = new PdfPCell(paragrapfPrecio);
		celdaPrecio.setColspan(1);
		celdaPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaPrecio);

		Paragraph paragrapfValorVenta = new Paragraph("VALOR VENTA", fuenteBold);
		paragrapfValorVenta.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaValorVenta = new PdfPCell(paragrapfValorVenta);
		celdaValorVenta.setColspan(1);
		celdaValorVenta.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaValorVenta);

		// COMENZANDO A INSERTAR LA DATA

		Paragraph paragrapfCantidadData = new Paragraph(mapaVentas.get("Cantidad1").toString(), fuenteBold);
		paragrapfCantidadData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaCantidadData = new PdfPCell(paragrapfCantidadData);
		celdaCantidadData.setColspan(1);
		celdaCantidadData.setMinimumHeight(100f);
		celdaCantidadData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaCantidadData);

		Paragraph paragrapfDescripcionData = new Paragraph(mapaVentas.get("Descripcion").toString(), fuenteBold);
		paragrapfDescripcionData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDescripcionData = new PdfPCell(paragrapfDescripcionData);
		celdaDescripcionData.setColspan(3);
		celdaDescripcionData.setMinimumHeight(100f);
		celdaDescripcionData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaDescripcionData);

		Paragraph paragrapfUMData = new Paragraph("UND", fuenteBold);
		paragrapfUMData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaUMData = new PdfPCell(paragrapfUMData);
		celdaUMData.setColspan(1);
		celdaUMData.setMinimumHeight(100f);
		celdaUMData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaUMData);

		Paragraph paragrapfPrecioData = new Paragraph(mapaVentas.get("PrecioUnitario").toString(), fuenteBold);
		paragrapfPrecioData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaPrecioData = new PdfPCell(paragrapfPrecioData);
		celdaPrecioData.setColspan(1);
		celdaPrecioData.setMinimumHeight(100f);
		celdaPrecioData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaPrecioData);

		Paragraph paragrapfValorVentaData = new Paragraph(mapaVentas.get("TotalSinIGV").toString(), fuenteBold);
		paragrapfValorVentaData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaValorVentaData = new PdfPCell(paragrapfValorVentaData);
		celdaValorVentaData.setColspan(1);
		celdaValorVentaData.setMinimumHeight(100f);
		celdaValorVentaData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaValorVentaData);

		Paragraph paragrapfVacio3 = new Paragraph(" ", fuenteBold);
		paragrapfVacio3.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio3 = new PdfPCell(paragrapfVacio3);
		celdaVacio3.setColspan(7);
		celdaVacio3.setBorder(Rectangle.NO_BORDER);
		celdaVacio3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacio3);

		// *********

		Paragraph paragrapfOperacionGravadaTexto = new Paragraph("OPERACION GRAVADA", fuenteBold);
		paragrapfOperacionGravadaTexto.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaOperacionGravada = new PdfPCell(paragrapfOperacionGravadaTexto);
		celdaOperacionGravada.setColspan(1);
		celdaOperacionGravada.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaOperacionGravada);

		Paragraph paragrapfOperacionGratuitaTexto = new Paragraph("OPERACION GRATUITA", fuenteBold);
		paragrapfOperacionGratuitaTexto.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaOperacionGratuita = new PdfPCell(paragrapfOperacionGratuitaTexto);
		celdaOperacionGratuita.setColspan(1);
		celdaOperacionGratuita.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaOperacionGratuita);

		Paragraph paragrapfOperacionExoneradaTexto = new Paragraph("U.M.", fuenteBold);
		paragrapfOperacionExoneradaTexto.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaOperacionExonerada = new PdfPCell(paragrapfOperacionExoneradaTexto);
		celdaOperacionExonerada.setColspan(1);
		celdaOperacionExonerada.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaOperacionExonerada);

		Paragraph paragrapfDescuentoTotalTexto = new Paragraph("DESCUENTO TOTAL", fuenteBold);
		paragrapfDescuentoTotalTexto.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDescuentoTotal = new PdfPCell(paragrapfDescuentoTotalTexto);
		celdaDescuentoTotal.setColspan(1);
		celdaDescuentoTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaDescuentoTotal);

		Paragraph paragrapfTotalIGV = new Paragraph("TOTAL IGV (18%)", fuenteBold);
		paragrapfTotalIGV.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTotalIGV = new PdfPCell(paragrapfTotalIGV);
		celdaTotalIGV.setColspan(1);
		celdaTotalIGV.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaTotalIGV);

		Paragraph paragrapfTotalVenta = new Paragraph("TOTAL DE VENTA", fuenteBold);
		paragrapfTotalVenta.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTotalVenta = new PdfPCell(paragrapfTotalVenta);
		celdaTotalVenta.setColspan(2);
		celdaTotalVenta.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaTotalVenta);

		// ************

		Paragraph paragrapfOperacionGravadaTextoData = new Paragraph(
				(mapaVentas.get("Moneda").toString().equals("SOLES") ? "S/ " : "$ ")
						+ mapaVentas.get("TotalSinIGV").toString(),
				fuenteBold);
		paragrapfOperacionGravadaTextoData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaOperacionGravadaData = new PdfPCell(paragrapfOperacionGravadaTextoData);
		celdaOperacionGravadaData.setColspan(1);
		celdaOperacionGravadaData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaOperacionGravadaData);

		Paragraph paragrapfOperacionGratuitaTextoData = new Paragraph(
				(mapaVentas.get("Moneda").toString().equals("SOLES") ? "S/ 0.0 " : "$ 0.0"), fuenteBold);
		paragrapfOperacionGratuitaTextoData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaOperacionGratuitaData = new PdfPCell(paragrapfOperacionGratuitaTextoData);
		celdaOperacionGratuitaData.setColspan(1);
		celdaOperacionGratuitaData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaOperacionGratuitaData);

		Paragraph paragrapfOperacionExoneradaTextoData = new Paragraph(
				(mapaVentas.get("Moneda").toString().equals("SOLES") ? "S/ 0.0 " : "$ 0.0"), fuenteBold);
		paragrapfOperacionExoneradaTextoData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaOperacionExoneradaData = new PdfPCell(paragrapfOperacionExoneradaTextoData);
		celdaOperacionExoneradaData.setColspan(1);
		celdaOperacionExoneradaData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaOperacionExoneradaData);

		Paragraph paragrapfDescuentoTotalTextoData = new Paragraph(
				(mapaVentas.get("Moneda").toString().equals("SOLES") ? "S/ 0.0 " : "$ 0.0"), fuenteBold);
		paragrapfDescuentoTotalTextoData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaDescuentoTotalData = new PdfPCell(paragrapfDescuentoTotalTextoData);
		celdaDescuentoTotalData.setColspan(1);
		celdaDescuentoTotalData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaDescuentoTotalData);

		Paragraph paragrapfTotalIGVData = new Paragraph(
				(mapaVentas.get("Moneda").toString().equals("SOLES") ? "S/ " : "$ ") + mapaVentas.get("IGV").toString(),
				fuenteBold);
		paragrapfTotalIGVData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTotalIGVData = new PdfPCell(paragrapfTotalIGVData);
		celdaTotalIGVData.setColspan(1);
		celdaTotalIGVData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaTotalIGVData);

		Paragraph paragrapfTotalVentaData = new Paragraph(
				(mapaVentas.get("Moneda").toString().equals("SOLES") ? "S/ " : "$ ")
						+ mapaVentas.get("Total").toString(),
				fuenteBold);
		paragrapfTotalVentaData.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaTotalVentaData = new PdfPCell(paragrapfTotalVentaData);
		celdaTotalVentaData.setColspan(2);
		celdaTotalVentaData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaTotalVentaData);

		// ************

		Paragraph paragrapfVacio4 = new Paragraph(" ", fuenteBold);
		paragrapfVacio4.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaVacio4 = new PdfPCell(paragrapfVacio4);
		celdaVacio4.setColspan(7);
		celdaVacio4.setBorder(Rectangle.NO_BORDER);
		celdaVacio4.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaVacio4);

		Paragraph paragrapfMontoLetras = new Paragraph("SON :" + mapaVentas.get("MontoLetras").toString(), fuenteBold);
		paragrapfMontoLetras.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell celdaMontoLetras = new PdfPCell(paragrapfMontoLetras);
		celdaMontoLetras.setColspan(7);
		celdaMontoLetras.setBorder(Rectangle.NO_BORDER);
		celdaMontoLetras.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaMontoLetras);

		Image images = null;
		BufferedImage imagecb = null;

		PdfPTable tableBarras = null;

		try {

			tableBarras = new PdfPTable(3);

			float[] medidaCeldasBarra = { 0.15f, 1.1f, 0.15f };
			tableBarras.setWidths(medidaCeldasBarra);

			StringBuilder texto = new StringBuilder();
			texto.append(mapaVentas.get("Empresa").toString() + "'"); // texto.append();
			String cadena = mapaVentas.get("DocumentoElectronico").toString();
			texto.append(cadena.toString());

			BarcodeQRCode barcodeQRCode2 = new BarcodeQRCode(texto.toString(), 1000, 1000, null);
			images = barcodeQRCode2.getImage();

			/*
			 * images = Image.getInstance( (ventaImages.getImageQR() != null ?
			 * ventaImages.getImageQR() : ventaImages.getImage()));
			 */
		} catch (BadElementException e) {
			e.printStackTrace();

		} /*
			 * catch (MalformedURLException e) { e.printStackTrace();
			 * 
			 * } catch (IOException e) { e.printStackTrace(); }
			 */ catch (DocumentException e) {
			e.printStackTrace();
		}

		PdfPCell celdaBarra1 = new PdfPCell();
		celdaBarra1.setColspan(1);
		celdaBarra1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		celdaBarra1.setBorder(Rectangle.NO_BORDER);
		tableBarras.addCell(celdaBarra1);

		PdfPCell celdaBarra = new PdfPCell();
		celdaBarra.setFixedHeight(95f);
		celdaBarra.addElement(images);
		celdaBarra.setColspan(1);
		celdaBarra.setBorder(Rectangle.NO_BORDER);
		celdaBarra.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableBarras.addCell(celdaBarra);

		PdfPCell celdaBarra2 = new PdfPCell();
		celdaBarra2.setColspan(1);
		celdaBarra2.setBorder(Rectangle.NO_BORDER);
		celdaBarra2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableBarras.addCell(celdaBarra2);

		PdfPCell celdaVacia6 = new PdfPCell(new Paragraph(" "));
		celdaVacia6.setColspan(7);
		celdaVacia6.setBorder(Rectangle.NO_BORDER);
		celdaVacia6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacia6);

		PdfPCell celdaRpresentacion = new PdfPCell(
				new Paragraph("Representación impresa del Documento Electronico, esta puede ser consultado \n"
						+ " en www.grupopalomino.com.pe", fuenteBold));
		celdaRpresentacion.setColspan(7);
		celdaRpresentacion.setBorder(Rectangle.NO_BORDER);
		celdaRpresentacion.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaRpresentacion);

		PdfPCell celdaVacia4 = new PdfPCell(new Paragraph(" "));
		celdaVacia4.setColspan(3);
		celdaVacia4.setBorder(Rectangle.NO_BORDER);
		celdaVacia4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacia4);

		PdfPCell celdaCodigoHas = new PdfPCell(
				new Paragraph((ventaImages.getCodigohash() == null ? "" : ventaImages.getCodigohash()), fuenteBold));
		celdaCodigoHas.setColspan(4);
		celdaCodigoHas.setBorder(Rectangle.NO_BORDER);
		celdaCodigoHas.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(celdaCodigoHas);

		PdfPCell celdaVacia5 = new PdfPCell(new Paragraph(" "));
		celdaVacia5.setColspan(2);
		celdaVacia5.setBorder(Rectangle.NO_BORDER);
		celdaVacia5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaVacia5);

		PdfPCell celdaBarraVacia = new PdfPCell(tableBarras);
		celdaBarraVacia.setColspan(5);
		celdaBarraVacia.setBorder(Rectangle.NO_BORDER);
		celdaBarraVacia.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celdaBarraVacia);

		return table;

	}

}
