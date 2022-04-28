package tech.itpark.itparkfinalproject;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.service.CategoryService;

import java.math.BigInteger;

@SpringBootApplication
@RequiredArgsConstructor
public class ItParkFinalProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ItParkFinalProjectApplication.class, args);
       /* CategoryService service = run.getBean(CategoryService.class);

        CategoryDto dto = new CategoryDto();
        dto.setAmount(new BigInteger("1"));
        dto.setCategoryName("1");
        dto.setDescription("1");

        service.save(dto);*/

    }

}
