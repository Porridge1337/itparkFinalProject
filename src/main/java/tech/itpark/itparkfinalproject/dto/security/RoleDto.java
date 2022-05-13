package tech.itpark.itparkfinalproject.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private long id;

    @NotNull
    @NotBlank
    private String name;

    private Set<UserDto> user;

}
