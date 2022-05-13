package tech.itpark.itparkfinalproject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tech.itpark.itparkfinalproject.dto.security.UserDto;
import tech.itpark.itparkfinalproject.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ItParkFinalProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ItParkFinalProjectApplication.class, args);
        /*UserService userBean = run.getBean(UserService.class);
        UserDto userDto = new UserDto();
        userDto.setPassword("123");
        userDto.setLogin("123");
        userBean.save(userDto);*/
    }
}
