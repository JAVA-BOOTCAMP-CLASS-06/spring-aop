package ar.com.sicos.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"ar.com.sicos"})
@EnableAspectJAutoProxy
public class AppConfig {

}
