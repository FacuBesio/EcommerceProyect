package com.springBoot.ecommerce;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcewebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:img/");
        
    /*El m�todo addResourceHandler("/img/**") define el patr�n de las URL que se utilizaran para 
      acceder al contenido, el patr�n "/img/**" nos permitir� manejar todo tipo de URL. Por otra 
      parte el m�todo addResourceLocations("/public/") establece la ubicaci�n de los recursos.
      */
    }
}
