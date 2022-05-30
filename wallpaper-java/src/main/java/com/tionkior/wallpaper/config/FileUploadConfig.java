package com.tionkior.wallpaper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        //String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\fallback\\";
        //String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\";
        ////static/upload/**是对应resource下工程目录
        ////registry.addResourceHandler("/img/fallback/**").addResourceLocations("file:"+path);
        //registry.addResourceHandler("/img/**").addResourceLocations("file:"+path);


        String os = System.getProperty("os.name");
        String path2 = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\bingwallpaper\\";
        if (os.toLowerCase().startsWith("win")) {
            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\bingwallpaper\\";
            registry.addResourceHandler("/uploads/**").
                    addResourceLocations("file:" + path);
            registry.addResourceHandler("/images/bingwallpaper/**")
                    .addResourceLocations("file:" + path2);
        } else {//linux和mac系统 可以根据逻辑再做处理

            registry.addResourceHandler("/uploads/**").
                    addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                            + "uploads" + System.getProperty("file.separator"));

            registry.addResourceHandler("/img/**").
                    addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img" +
                            System.getProperty("file.separator"));
        }

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


    }

}
