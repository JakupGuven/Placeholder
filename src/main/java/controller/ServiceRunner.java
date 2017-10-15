package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@Configuration
@SpringBootApplication
@EnableWebMvc
public class ServiceRunner {
	
//	  @Bean
//	  public FilterRegistrationBean corsFilterRegistration() {
//		  FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CORSFilter());
//		  registrationBean.setName("CORS Filter");
//		  registrationBean.addUrlPatterns("/*");
//		  registrationBean.setOrder(1);
//		  return registrationBean;
//	  }
	
	  public static void main(String[] args) {
	        SpringApplication.run(ServiceRunner.class, args);
	        
	    }

}
