package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class prueba {
public static void main(String[] args) {
    try {
        URL url = new URL("http://localhost:8090/Servicios_Palomino_Oficial/Palomino/cuppones/71807093");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            if (output.contains("Cuppon Correcto")) {
            	System.out.println("Correcto");
            }
        }
        conn.disconnect();
    } catch (MalformedURLException e) {
    } catch (IOException e) {
    }   
    }

}
