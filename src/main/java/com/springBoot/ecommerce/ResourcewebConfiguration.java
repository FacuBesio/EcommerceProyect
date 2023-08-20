package com.springBoot.ecommerce;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcewebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:img/");
        
    /*El método addResourceHandler("/img/**") define el patrón de las URL que se utilizaran para 
      acceder al contenido, el patrón "/img/**" nos permitirá manejar todo tipo de URL. Por otra 
      parte el método addResourceLocations("/public/") establece la ubicación de los recursos.
      */
    }
}
