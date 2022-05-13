package tech.itpark.itparkfinalproject.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.itpark.itparkfinalproject.model.type.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;

    @NotNull
    @NotBlank(message = "укажите пожалуйста логин")
    private String login;

    @NotNull
    @NotBlank(message = "укажите пожалуйста пароль")
    private String password;

    private Status status;

    private Set<RoleDto> role;

}
