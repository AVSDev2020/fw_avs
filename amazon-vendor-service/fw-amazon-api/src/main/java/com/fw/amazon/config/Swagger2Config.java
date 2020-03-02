package com.fw.amazon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
	
	public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.fw.amazon.controller";
	
	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;
	
	@Bean
	public Docket createRestApi() {		
		return new Docket(DocumentationType.SWAGGER_2)		
				.apiInfo(apiInfo())				
				.enable(swaggerEnabled)
				.select()				
				.apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("FW Amazon Vendor Service Integration")
				.description("Amazon Vendor Service Integration Api")				
				.version("1.0.0")
				.build();
	}
}
