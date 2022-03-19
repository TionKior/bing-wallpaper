package com.tionkior.wallpaper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author : TionKior
 * @date : 2022/3/11 19:46
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tionkior.wallpaper.controller"))
                .build();
    }

    public ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("史晟贤", "http://www.tionkior.com", "1423304936@qq.com");
        return new ApiInfo(
                "史晟贤的Wallpaper文档",
                "WallpaperAPI文档",
                "v1.0",
                "http://www.tionkior.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

}
