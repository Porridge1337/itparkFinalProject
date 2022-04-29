package tech.itpark.itparkfinalproject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tech.itpark.itparkfinalproject.service.CategoryService;
import tech.itpark.itparkfinalproject.service.ProductService;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ItParkFinalProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ItParkFinalProjectApplication.class, args);
        CategoryService service = run.getBean(CategoryService.class);
        ProductService productService = run.getBean(ProductService.class);

        System.out.println(service.findAll());

    }

}
