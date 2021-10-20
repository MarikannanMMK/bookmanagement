package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(
				 new ApiInfo("Book Management System", 
						 "This API Makes You Walk Through Different EndPoints",
						 "1.0.0",
						 "mmk@gmail.com",
						 "Marikannan MMK", 
						 "All Rights Reserved",
						 "Open Source License.")
				 );
	}

}
