package br.com.seg.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.seg.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(customOpenAPI());
	}
	
	public ApiInfo customOpenAPI() {
	    return new ApiInfo(
	    	      "Segurança", 
	    	      "APIs de segurança", 
	    	      "API Ceci", 
	    	      "", 
	    	      new Contact("Cecilia Alves","", "cecialveslima@gmail.com"), 
	    	      "", 
	    	      "",
	    	      Collections.emptyList());
	    	}
	
}
