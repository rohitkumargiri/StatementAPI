package net.cerner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Statement").apiInfo(apiInfo()).select()
				.paths(regex("/statement.*"))
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("StatementAPI Documnetation")
								   .description("Documentation for StatementAPI by using Swagger")
								   .version("1.0")
								   .build();
	}

}
