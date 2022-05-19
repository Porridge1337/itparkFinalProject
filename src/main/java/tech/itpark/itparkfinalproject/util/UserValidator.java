package tech.itpark.itparkfinalproject.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tech.itpark.itparkfinalproject.dto.security.UserDto;
import tech.itpark.itparkfinalproject.service.UserService;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService sectionService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        if (sectionService.existByLogin(userDto.getLogin())) {
            errors.rejectValue("login", "User exists", "Этот пользователь уже существует");
        }
    }
}
