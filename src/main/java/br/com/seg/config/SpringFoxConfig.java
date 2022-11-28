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
				.paths(PathSelectors.ant("/api/**/**"))
				.build()
				.useDefaultResponseMessages(false)
//		        .securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
//		        .securityContexts(Arrays.asList(securityContext()))				
				.apiInfo(customOpenAPI());
	}
	
//	private SecurityContext securityContext() {
//	    return SecurityContext.builder()
//	        .securityReferences(defaultAuth())
//	        .build();
//	}
//	
//	List<SecurityReference> defaultAuth() {
//	    AuthorizationScope authorizationScope
//	        = new AuthorizationScope("ADMIN", "accessEverything");
//	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//	    authorizationScopes[0] = authorizationScope;
//	    return Arrays.asList(
//	        new SecurityReference("Token Access", authorizationScopes));
//	}
	
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
