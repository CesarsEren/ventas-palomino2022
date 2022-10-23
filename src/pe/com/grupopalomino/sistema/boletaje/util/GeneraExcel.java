package pe.com.grupopalomino.sistema.boletaje.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.DVConstraint;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
//import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
//import org.springframework.ui.Model;import com.sun.javafx.collections.MappingChange.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.service.AtencionReclamosService;
import pe.com.grupopalomino.sistema.boletaje.service.AtencionReclamosServiceI;

//import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBeanTDP;

public class GeneraExcel {

	static int TOTAL_FILAS = 26;
	static int TOTAL_COLUMNAS = 7;
	static int TOTAL_COLUMNASVUELTA = 8;
	static int FILAS_CABECERA = 1;
	static String[] CABECERAS = { "\t ASIENTO", "\t TIPO DOCUMENTO", "\t DOCUMENTO", "\t NOMBRES Y APELLIDOS",
			"\t TELEFONO", "\t EDAD", "\t ESTADO" };
	static String[] CABECERASVUELTA = { "\t ASIENTO IDA", "\t TIPO DOCUMENTO", "\t DOCUMENTO", "\t NOMBRES Y APELLIDOS",
			"\t TELEFONO", "\t EDAD", "\t ASIENTO VUELTA", "\t ESTADO" };

	// static String[] ASIENTOS_DISPONIBLES = {"1", "3", "5", "4", "8", "9",
	// "6", "21", "15", "32"};
	static String[] TIPOS_DOCUMENTOS = { "D.N.I", "C.E" };
	static int TOTAL_ASIENTOS_DISPONIBLES;// = ASIENTOS_DISPONIBLES.length;

	static String[] CABECERAS_RPTE_RECLAMOS = { "Nro", "Periodo", "Empresa", "EmpresaD", "FechaReclamo",
			"FechaCaducidad", "Identidad", "IdentidadD", "DNI", "Nombres", "ApePaterno", "ApeMaterno", "Telefono",
			"Email", "Domicilio", "PadreMenor", "FechaIncidente", "Servicio", "MotivoReclamo", "Documento", "Serie",
			"Numero", "TipoReclamo", "Detalle", "Pedido", "AtencionReclamos","Atendido", "FechaAtencion",
			"ReenvioCorreo", "Id", "AgenciaD", "Usuario", "DetalleAtencion" };

	public static void main(String[] args) throws IOException, NoSuchFieldException, SecurityException {
		CrearExcelReporteReclamos();
	}

	public static void CrearExcelReporteReclamos()throws NoSuchFieldException, SecurityException {
		
		Workbook EXCEL = new HSSFWorkbook();
		Sheet HOJA1 = EXCEL.createSheet("ReporteReclamos");
		CellStyle STYLE_CABECERARPT = EXCEL.createCellStyle();
		STYLE_CABECERARPT.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		STYLE_CABECERARPT.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		STYLE_CABECERARPT.setAlignment(HorizontalAlignment.CENTER);
		STYLE_CABECERARPT.setVerticalAlignment(VerticalAlignment.CENTER);
		STYLE_CABECERARPT.setBorderBottom(BorderStyle.THIN);
		STYLE_CABECERARPT.setBorderTop(BorderStyle.THIN);
		STYLE_CABECERARPT.setBorderRight(BorderStyle.THIN);
		STYLE_CABECERARPT.setBorderLeft(BorderStyle.THIN);
		STYLE_CABECERARPT.setBottomBorderColor(IndexedColors.YELLOW.getIndex());
		Font font = EXCEL.createFont();
		font.setColor(IndexedColors.YELLOW.getIndex());
		STYLE_CABECERARPT.setFont(font);
		AtencionReclamosService servicereclamos = new AtencionReclamosServiceI();
		List<Map<String,Object>> ReclamosPorAgencia = servicereclamos.ObtenerAtencionListaReclamosRpte();
		
		String[] titulos ={"Nro","Periodo","Empresa","Fecha Registro","Tipo Documento","Documento","Nombres","Apellidos","Teléfono","Correo","Domicilio","Fecha Incidente","Tipo Servicio","Tipo Documento Empresa","Serie","Correlativo","Estado Atención","Fecha Atención","Motivo Reclamo","Agencia Registro"};
		Row fila1 = HOJA1.createRow(0); 
		for(int COLUMNA = 0;COLUMNA<titulos.length;COLUMNA++)
		{
			Cell CELDA = fila1.createCell((short) COLUMNA);
			HSSFRichTextString CONTENIDOCELDA = new HSSFRichTextString(titulos[COLUMNA]);
			CELDA.setCellStyle(STYLE_CABECERARPT);
			CELDA.setCellValue(CONTENIDOCELDA);
			HOJA1.autoSizeColumn(COLUMNA);
		}
		
		for(int FILA = 1 ; FILA<=ReclamosPorAgencia.size();FILA++)
		{
			Row row = HOJA1.createRow(FILA);
			for(int COLUMNA = 0;COLUMNA<titulos.length;COLUMNA++)
			{
				Cell CELDA = row.createCell((short) COLUMNA);
				HSSFRichTextString CONTENIDOCELDA = new HSSFRichTextString(ReclamosPorAgencia.get(FILA-1).get(titulos[COLUMNA])+"");
				CELDA.setCellValue(CONTENIDOCELDA);
				HOJA1.autoSizeColumn(COLUMNA);
			}
		}
		try {
			EXCEL.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file;
		if(Utils.isDesarrollo()){
		file = new File("/home/equipo1/reclamos/ReporteReclamos.xls");
		}else{
			file = new File("C:\\Users\\Sistemas01\\Desktop\\ReporteReclamos.xls");
		}

		try {
			FileOutputStream elFichero = new FileOutputStream(file);
			EXCEL.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void X() throws NoSuchFieldException, SecurityException {
		//org.apache.poi.ss.usermodel.Workbook
		Workbook EXCEL = new HSSFWorkbook();
		Sheet HOJA1 = EXCEL.createSheet("ReporteReclamos");
		CellStyle STYLE_CABECERARPT = EXCEL.createCellStyle();
		STYLE_CABECERARPT.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		STYLE_CABECERARPT.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		STYLE_CABECERARPT.setAlignment(HorizontalAlignment.CENTER);
		STYLE_CABECERARPT.setVerticalAlignment(VerticalAlignment.CENTER);
		STYLE_CABECERARPT.setBorderBottom(BorderStyle.THIN);
		STYLE_CABECERARPT.setBorderTop(BorderStyle.THIN);
		STYLE_CABECERARPT.setBorderRight(BorderStyle.THIN);
		STYLE_CABECERARPT.setBorderLeft(BorderStyle.THIN);
		STYLE_CABECERARPT.setBottomBorderColor(IndexedColors.YELLOW.getIndex());
		Font font = EXCEL.createFont();
		font.setColor(IndexedColors.YELLOW.getIndex());
		STYLE_CABECERARPT.setFont(font);
		
		AtencionReclamosService servicereclamos = new AtencionReclamosServiceI();
		List<B_AtencionReclamosBean> ReclamosPorAgencia = null;//servicereclamos.ObtenerAtencionListaReclamosRpte();
		Field[] fields = B_AtencionReclamosBean.class.getFields();
		Field[] fieldstemp = new Field[CABECERAS_RPTE_RECLAMOS.length]; 
		int contador = 0;
		int filaheader = 1;
		
		for (Field field: fields) {
			for (int j = 0; j < CABECERAS_RPTE_RECLAMOS.length; j++) {
				if (field.getName().equals(CABECERAS_RPTE_RECLAMOS[j])) {
					fieldstemp[contador] = field;
					contador++;
				}
			}
		}

		for (int FILA = 0; FILA < filaheader + ReclamosPorAgencia.size(); FILA++) {
			Row fila1 = HOJA1.createRow(FILA);
			for (int COLUMNA = 0; COLUMNA < CABECERAS_RPTE_RECLAMOS.length; COLUMNA++) {
				Cell CELDA = fila1.createCell((short) COLUMNA);
				if (FILA == 0) {
					HSSFRichTextString CONTENIDOCELDA = new HSSFRichTextString(fieldstemp[COLUMNA].getName());
					CELDA.setCellStyle(STYLE_CABECERARPT);
					CELDA.setCellValue(CONTENIDOCELDA);
					HOJA1.autoSizeColumn(COLUMNA);
				} else {
					B_AtencionReclamosBean iterado = ReclamosPorAgencia.get(FILA - 1); 
					try {
						Object value = fieldstemp[COLUMNA].get(iterado);
						HSSFRichTextString CONTENIDOCELDA = new HSSFRichTextString(value + "");
						CELDA.setCellValue(CONTENIDOCELDA);
						HOJA1.autoSizeColumn(COLUMNA);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				fila1.setHeight((short) 450);
			}
		}
		try {
			EXCEL.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		String sSistemaOperativo = System.getProperty("os.name");
		File file;
		if (sSistemaOperativo.contains("Windows")) {
			file = new File("C:\\Users\\Sistemas01\\Desktop\\ReporteReclamos.xls");
			System.out.println("Estoy en Windows");
		} else {
			file = new File("/home/equipo1/reclamos/ReporteReclamos.xls");
			System.out.println("Estoy en Linux");
		} 
		try {
			FileOutputStream elFichero = new FileOutputStream(file);
			EXCEL.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void CrearExcelVuelta(String[] ASIENTOS_DISPONIBLES_STR, String[] ASIENTOS_DISPONIBLES_VUELTA) {
		int total = Math.min(ASIENTOS_DISPONIBLES_STR.length, ASIENTOS_DISPONIBLES_VUELTA.length);
		TOTAL_ASIENTOS_DISPONIBLES = TOTAL_FILAS;
		Workbook EXCEL = new HSSFWorkbook();
		Sheet HOJA1 = EXCEL.createSheet("TransporteDePersonal");
		HOJA1.protectSheet("transportetdp");
		CellStyle STYLE_CABECERA = EXCEL.createCellStyle();
		STYLE_CABECERA.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		STYLE_CABECERA.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		STYLE_CABECERA.setAlignment(HorizontalAlignment.CENTER);
		STYLE_CABECERA.setVerticalAlignment(VerticalAlignment.CENTER);
		STYLE_CABECERA.setBorderBottom(BorderStyle.THIN);
		STYLE_CABECERA.setBorderTop(BorderStyle.THIN);
		STYLE_CABECERA.setBorderRight(BorderStyle.THIN);
		STYLE_CABECERA.setBorderLeft(BorderStyle.THIN);
		STYLE_CABECERA.setBottomBorderColor(IndexedColors.YELLOW.getIndex());
		Font font = EXCEL.createFont();
		font.setColor(IndexedColors.YELLOW.getIndex());
		STYLE_CABECERA.setFont(font);
		DataFormat fmt = EXCEL.createDataFormat();
		CellStyle STYLE_CUERPO = EXCEL.createCellStyle();
		STYLE_CUERPO.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		STYLE_CUERPO.setAlignment(HorizontalAlignment.CENTER);
		STYLE_CUERPO.setVerticalAlignment(VerticalAlignment.CENTER);
		STYLE_CUERPO.setLocked(false);
		STYLE_CUERPO.setBorderBottom(BorderStyle.THIN);
		STYLE_CUERPO.setBorderTop(BorderStyle.THIN);
		STYLE_CUERPO.setBorderRight(BorderStyle.THIN);
		STYLE_CUERPO.setBorderLeft(BorderStyle.THIN);
		CellStyle STYLE_CUERPOTEXT = EXCEL.createCellStyle();
		STYLE_CUERPOTEXT.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		STYLE_CUERPOTEXT.setAlignment(HorizontalAlignment.CENTER);
		STYLE_CUERPOTEXT.setVerticalAlignment(VerticalAlignment.CENTER);
		STYLE_CUERPOTEXT.setDataFormat(fmt.getFormat("@"));
		STYLE_CUERPOTEXT.setLocked(false);
		STYLE_CUERPOTEXT.setBorderBottom(BorderStyle.THIN);
		STYLE_CUERPOTEXT.setBorderTop(BorderStyle.THIN);
		STYLE_CUERPOTEXT.setBorderRight(BorderStyle.THIN);
		STYLE_CUERPOTEXT.setBorderLeft(BorderStyle.THIN);
		// ************//************//************//************//************
		CellRangeAddressList AsientosList = new CellRangeAddressList(1, TOTAL_FILAS, 0, 0);
		CellRangeAddressList AsientosListVuelta = new CellRangeAddressList(1, TOTAL_FILAS, 6, 6);
		CellRangeAddressList TipodocumentoList = new CellRangeAddressList(1, TOTAL_FILAS, 1, 1);

		for (int FILA = 0; FILA < TOTAL_FILAS + FILAS_CABECERA; FILA++) {
			Row fila1 = HOJA1.createRow(FILA);
			if (FILA == 0) {
				for (int COLUMNA = 0; COLUMNA < TOTAL_COLUMNASVUELTA; COLUMNA++) {
					Cell CELDA = fila1.createCell((short) COLUMNA);
					if (COLUMNA == 0) {
						DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(ASIENTOS_DISPONIBLES_STR);
						HSSFDataValidation dataValidation = new HSSFDataValidation(AsientosList, dvConstraint);
						dataValidation.setShowErrorBox(true);
						dataValidation.createErrorBox("Error", "Solo puedes Elegir Asientos de la Lista");
						dataValidation.setSuppressDropDownArrow(false);
						HOJA1.addValidationData(dataValidation);
					}
					if (COLUMNA == 6) {
						DVConstraint dvConstraint = DVConstraint
								.createExplicitListConstraint(ASIENTOS_DISPONIBLES_VUELTA);
						HSSFDataValidation dataValidation = new HSSFDataValidation(AsientosListVuelta, dvConstraint);
						dataValidation.setShowErrorBox(true);
						dataValidation.createErrorBox("Error", "Solo puedes Elegir Asientos de la Lista");
						dataValidation.setSuppressDropDownArrow(false);
						HOJA1.addValidationData(dataValidation);
					}

					if (COLUMNA == 1) {
						DVConstraint dvConstraint2 = DVConstraint.createExplicitListConstraint(TIPOS_DOCUMENTOS);
						HSSFDataValidation dataValidation2 = new HSSFDataValidation(TipodocumentoList, dvConstraint2);
						dataValidation2.setSuppressDropDownArrow(false);
						dataValidation2.createErrorBox("Error",
								"Solo puedes Elegir D.N.I(Documento Nacional de Identidad) o C.E (Carnet de Extranjeria)");
						HOJA1.addValidationData(dataValidation2);
					}
					HSSFRichTextString CONTENIDOCELDA = new HSSFRichTextString(CABECERASVUELTA[COLUMNA]);
					CELDA.setCellStyle(STYLE_CABECERA);
					CELDA.setCellValue(CONTENIDOCELDA);
					HOJA1.autoSizeColumn(COLUMNA);
				}
				fila1.setHeight((short) 450); // 37.50 pixeles||750
				HOJA1.setColumnWidth(0, 5000);
				HOJA1.setColumnWidth(1, 7000);
				HOJA1.setColumnWidth(2, 5000);
				HOJA1.setColumnWidth(3, 15000);
				HOJA1.setColumnWidth(4, 5000);
				HOJA1.setColumnWidth(5, 2500);
				HOJA1.setColumnWidth(6, 5000);
				HOJA1.setColumnWidth(7, 3000);
			} else {
				for (int COLUMNA = 0; COLUMNA < TOTAL_COLUMNASVUELTA; COLUMNA++) {
					Cell CELDA = fila1.createCell((short) COLUMNA);
					// HSSFRichTextString CONTENIDOCELDA = new
					// HSSFRichTextString("");
					if (COLUMNA != (TOTAL_COLUMNASVUELTA - 1) && COLUMNA != (TOTAL_COLUMNASVUELTA - 6)) {
						CELDA.setCellStyle(STYLE_CUERPO);
					} else if (COLUMNA == (TOTAL_COLUMNASVUELTA - 6)) {
						CELDA.setCellStyle(STYLE_CUERPOTEXT);
					} else if (COLUMNA == (TOTAL_COLUMNASVUELTA - 1)) {
						int ROW = FILA + 1;
						CELDA.setCellStyle(STYLE_CUERPOTEXT);
						CELDA.setCellFormula("IF(COUNTIF(A:A,A" + ROW + ")>1,\"Asiento de Ida Repetido\","
								+ "IF(COUNTIF(G:G,G" + ROW + ")>1,\"Asiento de Vuelta Repetido\"," + "IF(LEN(B" + ROW
								+ ")=0," + "\"Ingrese Elija Tipo Documento\"," + "IF(OR(AND(B" + ROW
								+ "=\"D.N.I\",LEN(C" + ROW + ")<>8),AND(B" + ROW + "=\"C.E\",LEN(C" + ROW + ")<>12))"
								+ ",\"Documento Incorrecto\"," + "IF(LEN(D" + ROW + ")=0,"
								+ "\"Ingrese Nombres y apellidos\"," + "IF(OR(LEN(E" + ROW + ")>9,LEN(E" + ROW + ")<9)"
								+ ",\"Telefono Incorrecto\"," + "IF(LEN(F" + ROW + ")=0," + "\"Ingrese la Edad\","
								+ "IF(F" + ROW + "<18," + "\"NO PUEDE SER MENOR DE EDAD\"," + "\"CORRECTO\"))))))))");
					} 
				}
			}
			try {
				EXCEL.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String sSistemaOperativo = System.getProperty("os.name");
		File file;
		if (sSistemaOperativo.contains("Windows")) {
			file = new File("C:\\Users\\Sistemas01\\Desktop\\TransporteDePersonal.xls");
			System.out.println("Estoy en Windows");
		} else {
			file = new File("/home/equipo1/archivos/TransporteDePersonal.xls");
			System.out.println("Estoy en Linux");
		}

		try {
			FileOutputStream elFichero = new FileOutputStream(file);
			EXCEL.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public static void CrearExcel(String[] ASIENTOS_DISPONIBLES_STR) {
		TOTAL_FILAS = ASIENTOS_DISPONIBLES_STR.length;
		TOTAL_ASIENTOS_DISPONIBLES = TOTAL_FILAS;
		/*
		 * String[]ASIENTOS_DISPONIBLES_STR = new
		 * String[TOTAL_ASIENTOS_DISPONIBLES]; for(int i =
		 * 0;i<TOTAL_ASIENTOS_DISPONIBLES;i++) {
		 * ASIENTOS_DISPONIBLES_STR[i]=""+ASIENTOS_DISPONIBLES[i].getAsiento();
		 * }
		 */
		Workbook EXCEL = new HSSFWorkbook();// Crear Excel
		Sheet HOJA1 = EXCEL.createSheet("TransporteDePersonal");// Crear Hoja en
																// Excel
		HOJA1.protectSheet("transportetdp");
		CellStyle STYLE_CABECERA = EXCEL.createCellStyle();
		STYLE_CABECERA.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		STYLE_CABECERA.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		STYLE_CABECERA.setAlignment(HorizontalAlignment.CENTER);
		STYLE_CABECERA.setVerticalAlignment(VerticalAlignment.CENTER);

		Font font = EXCEL.createFont();
		font.setColor(IndexedColors.YELLOW.getIndex());
		STYLE_CABECERA.setFont(font);

		DataFormat fmt = EXCEL.createDataFormat();

		CellStyle STYLE_CUERPO = EXCEL.createCellStyle();
		STYLE_CUERPO.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		STYLE_CUERPO.setAlignment(HorizontalAlignment.CENTER);
		STYLE_CUERPO.setVerticalAlignment(VerticalAlignment.CENTER);
		STYLE_CUERPO.setLocked(false);

		CellStyle STYLE_CUERPOTEXT = EXCEL.createCellStyle();
		STYLE_CUERPOTEXT.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		STYLE_CUERPOTEXT.setAlignment(HorizontalAlignment.CENTER);
		STYLE_CUERPOTEXT.setVerticalAlignment(VerticalAlignment.CENTER);
		STYLE_CUERPOTEXT.setDataFormat(fmt.getFormat("@"));
		STYLE_CUERPOTEXT.setLocked(false);
		// ************//************//************//************//************
		CellRangeAddressList AsientosList = new CellRangeAddressList(1, TOTAL_FILAS, 0, 0);
		CellRangeAddressList TipodocumentoList = new CellRangeAddressList(1, TOTAL_FILAS, 1, 1);

		for (int FILA = 0; FILA < TOTAL_FILAS + FILAS_CABECERA; FILA++) {
			Row fila1 = HOJA1.createRow(FILA);

			if (FILA == 0) {

				for (int COLUMNA = 0; COLUMNA < TOTAL_COLUMNAS; COLUMNA++) {
					Cell CELDA = fila1.createCell((short) COLUMNA);
					if (COLUMNA == 0) {
						DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(ASIENTOS_DISPONIBLES_STR);
						HSSFDataValidation dataValidation = new HSSFDataValidation(AsientosList, dvConstraint);
						dataValidation.setShowErrorBox(true);
						dataValidation.createErrorBox("Error", "Solo puedes Elegir Asientos de la Lista");
						dataValidation.setSuppressDropDownArrow(false);
						HOJA1.addValidationData(dataValidation);
					}
					if (COLUMNA == 1) {
						DVConstraint dvConstraint2 = DVConstraint.createExplicitListConstraint(TIPOS_DOCUMENTOS);
						HSSFDataValidation dataValidation2 = new HSSFDataValidation(TipodocumentoList, dvConstraint2);
						dataValidation2.setSuppressDropDownArrow(false);
						dataValidation2.createErrorBox("Error",
								"Solo puedes Elegir D.N.I(Documento Nacional de Identidad) o C.E (Carnet de Extranjeria)");
						HOJA1.addValidationData(dataValidation2);
					}
					HSSFRichTextString CONTENIDOCELDA = new HSSFRichTextString(CABECERAS[COLUMNA]);
					CELDA.setCellStyle(STYLE_CABECERA);
					CELDA.setCellValue(CONTENIDOCELDA);
					HOJA1.autoSizeColumn(COLUMNA);
				}
				fila1.setHeight((short) 750); // 37.50 pixeles
				HOJA1.setColumnWidth(0, 5000);
				HOJA1.setColumnWidth(1, 7000);
				HOJA1.setColumnWidth(2, 5000);
				HOJA1.setColumnWidth(3, 15000);
				HOJA1.setColumnWidth(4, 5000);
				HOJA1.setColumnWidth(5, 2500);
				HOJA1.setColumnWidth(6, 3000);
			} else {
				for (int COLUMNA = 0; COLUMNA < TOTAL_COLUMNAS; COLUMNA++) {
					Cell CELDA = fila1.createCell((short) COLUMNA);
					// HSSFRichTextString CONTENIDOCELDA = new
					// HSSFRichTextString("");
					if (COLUMNA != (TOTAL_COLUMNAS - 1) && COLUMNA != (TOTAL_COLUMNAS - 5)) {
						CELDA.setCellStyle(STYLE_CUERPO);
					} else if (COLUMNA == (TOTAL_COLUMNAS - 5)) {
						CELDA.setCellStyle(STYLE_CUERPOTEXT);
					} else {
						int ROW = FILA + 1;
						CELDA.setCellFormula("IF(LEN(A" + ROW + ")=0," + "\"Ingrese Asiento\"," + "IF(COUNTIF(A:A,A"
								+ ROW + ")>1,\"Asiento Repetido\"," + "IF(LEN(B" + ROW + ")=0,"
								+ "\"Ingrese Elija Tipo Documento\"," + "IF(OR(AND(B" + ROW + "=\"D.N.I\",LEN(C" + ROW
								+ ")<>8),AND(B" + ROW + "=\"C.E\",LEN(C" + ROW + ")<>12))"
								+ ",\"Documento Incorrecto\"," + "IF(LEN(D" + ROW + ")=0,"
								+ "\"Ingrese Nombres y apellidos\"," + "IF(OR(LEN(E" + ROW + ")>9,LEN(E" + ROW + ")<9)"
								+ ",\"Telefono Incorrecto\"," + "IF(LEN(F" + ROW + ")=0," + "\"Ingrese la Edad\","
								+ "IF(F" + ROW + "<18," + "\"NO PUEDE SER MENOR DE EDAD\"," + "\"CORRECTO\"))))))))");

					}
					// OR(AND(B3="D.N.I",LEN(C3)<>8),AND(B3="C.E",LEN(C3)<>12)
					// SI(CONTAR.SI(A:A,A2)>1
					// IF(COUNTIF(A:A,"+(FILA + 1)+")>1,\"Asiento Repetido\",
					// CELDA.setCellValue(CONTENIDOCELDA);
				}
			}
		}

		String sSistemaOperativo = System.getProperty("os.name");
		File file;
		if (sSistemaOperativo.contains("Windows")) {
			file = new File("C:\\Users\\Sistemas01\\Desktop\\TransporteDePersonal.xls");
			System.out.println("Estoy en Windows");
		} else {
			file = new File("/home/equipo1/archivos/TransporteDePersonal.xls");
			System.out.println("Estoy en Linux");
		}

		try {
			FileOutputStream elFichero = new FileOutputStream(file);
			EXCEL.write(elFichero);
			elFichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			EXCEL.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[]args)throws IOException { CrearExcel(new
	 * String[]{"1","2"}); }
	 */
}