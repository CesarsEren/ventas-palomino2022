package pe.com.grupopalomino.sistema.boletaje.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ActualizarEncryptador {
	public static void main(String[] args) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String x = encoder.encode("704010");
		System.out.println(x);
		if(x.equals("$2a$10$oyguybkZapIYqoZbJmm7.uYgkgmmlxbNrYvx6Ww1IeB70eu9O.xJC "))
		{
			System.out.println("CONTRASEÑA CORRECTA");
		}
		else
		{
			System.out.println("FAKE");
		}
		
		//bean.setPassword(encoder.encode(bean.getPassword()));
    }
}
