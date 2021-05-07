package com.everis.account.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) //Esta notación sirve para poder documentar todas las notaciones realizadas
public class SwaggerConfig {
	
	  public static final Contact DEFAULT_CONTACT = new Contact("Yanpieer Romero", "www.everis.com", "yromeros@everis.com");
	  public static final ApiInfo DEFAULT = new ApiInfo("Everis Api Documentation", "Everis Api Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
	
	@Bean
	public Docket api() {
		return new Docket( DocumentationType.SWAGGER_2 )
				.apiInfo(DEFAULT);
	}
	
	
}
