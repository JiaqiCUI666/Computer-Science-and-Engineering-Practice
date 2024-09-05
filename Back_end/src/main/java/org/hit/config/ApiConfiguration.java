package org.hit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class ApiConfiguration {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        String groupName="0.01版本";
        Docket docket=new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("Project API By 卢文豪")
                        .description("# 这里记录服务端所有的接口的入参，出参等等信息")
                        .contact(new Contact("卢文豪",null,"luwenhao369@icloud.com"))
                        .version("0.01")
                        .build())
                //分组名称
                .groupName(groupName)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("org.hit.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


}

