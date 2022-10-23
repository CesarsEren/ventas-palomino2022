package pe.com.grupopalomino.sistema.boletaje.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"pe.com.grupopalomino.sistema.boletaje.action"})
public class AspectConfig {

}
