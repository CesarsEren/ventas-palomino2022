package pe.com.grupopalomino.sistema.boletaje.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static final String NOMBRES = "[A-Za-z\u00f1\u00d1 &äëïöüáéíóúÁÉÍÓÚÄËÏÖÜ'-.]{2,50}";
	public static final String RAZONSOCIAL = "[A-Za-z\u00f1\u00d1 äëïöüáéíóúÁÉÍÓÚÄËÏÖÜ&'-.]{2,50}";
	public static final String ASIENTOS_VALIDOS = "[1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]";
	
	public static final String NUMERO = "\\d{8,15}";
	public static final String NUMEROS = "\\d";
	public static final String LETRAS = "\\D";
	public static final String EDAD = "\\d{1,2}";
	public static final String CREDITO = "^\\d+\\.\\d{2}$";
	
	public static final String TELEFONO = "[0-9]+";
	public static final String EMAIL = "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$";
	public static final String FECHA = "^((0?[1-9]|[12][0-9]|3[01])(0?[1-9]|1[012])(20)\\d\\d)$";
	
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(FECHA);
		Matcher matcher = pattern.matcher("05/08/2016");
		
		if(!matcher.matches()){
			System.out.println("yeah");
		}
		
		System.out.println("05/08/2016".trim().replace("/", "").matches(FECHA));
	}
}
	