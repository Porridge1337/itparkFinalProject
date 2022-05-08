package tech.itpark.itparkfinalproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path picUploadDir = Paths.get("./pictures");
        String picUploadPath = picUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/pictures/**").addResourceLocations("file:/" + picUploadPath + "/");
    }
}
