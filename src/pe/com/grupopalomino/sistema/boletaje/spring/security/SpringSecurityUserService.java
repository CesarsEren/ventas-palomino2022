package pe.com.grupopalomino.sistema.boletaje.spring.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.com.grupopalomino.sistema.boletaje.dao.V_Usuarios_WebDao;
import pe.com.grupopalomino.sistema.boletaje.dao.V_Usuarios_WebIDao;

@Service
public class SpringSecurityUserService implements UserDetailsService {

	private V_Usuarios_WebDao usuarioDAO = new V_Usuarios_WebIDao();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		try {
			if(username != null){
				if(username.contains("@")){
					SpringSecurityClient clienteSpringSecurity = usuarioDAO.loginClienteSMS(username);
					
					if(clienteSpringSecurity != null){
						clienteSpringSecurity.setCredentialsNonExpired(true);
						clienteSpringSecurity.setAccountNonExpired(true);
						clienteSpringSecurity.setAccountNonLocked(true);
						
						clienteSpringSecurity.setNivel("SMS");
						clienteSpringSecurity.setEnabled(true);
						SimpleGrantedAuthority rol = new SimpleGrantedAuthority("ROLE_SMS");
						clienteSpringSecurity.addAuthority(rol);
					}
					
					return clienteSpringSecurity;
				}
				else{
					SpringSecurityUser usuarioSpringSecurity = usuarioDAO.loginUsuario(username);
					
					if(usuarioSpringSecurity != null){
						usuarioSpringSecurity.setCredentialsNonExpired(true);
						usuarioSpringSecurity.setAccountNonExpired(true);
						usuarioSpringSecurity.setAccountNonLocked(true);
						
						usuarioSpringSecurity.setEnabled(usuarioSpringSecurity.getEstado().equals("Y") ? true : false);
						SimpleGrantedAuthority rol = new SimpleGrantedAuthority("ROLE_"+usuarioSpringSecurity.getNivel());
						usuarioSpringSecurity.addAuthority(rol);
					}
					
					return usuarioSpringSecurity;
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
