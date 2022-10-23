package pe.com.grupopalomino.sistema.boletaje.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =  true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SpringSecurityUserService springSecurityService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(springSecurityService).passwordEncoder(passwordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();		
		http.headers().frameOptions().sameOrigin();		
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/_lib/**").permitAll()
				.antMatchers("/**").permitAll()
			.and()
				.formLogin()
					.loginPage("/login")
						.failureUrl("/loginerror")
						.defaultSuccessUrl("/principal")
					.usernameParameter("username")
					.passwordParameter("password")
					.and()
				.logout()
					.permitAll()
					.deleteCookies("remove")
					.logoutUrl("/logout")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
					.logoutSuccessUrl("/inicio?logout")
					.invalidateHttpSession(false)
			.and()
				.exceptionHandling()
					.accessDeniedPage("/accesorestringido");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	    PasswordEncoder encoder = new BCryptPasswordEncoder();
	    return encoder;
	}
	
}
