package tech.itpark.itparkfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.itpark.itparkfinalproject.dto.security.UserDto;
import tech.itpark.itparkfinalproject.mapper.RoleMapper;
import tech.itpark.itparkfinalproject.mapper.UserMapper;
import tech.itpark.itparkfinalproject.model.type.Status;
import tech.itpark.itparkfinalproject.repository.RoleRepository;
import tech.itpark.itparkfinalproject.repository.UserRepository;
import tech.itpark.itparkfinalproject.service.UserService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        String encrypt = passwordEncoder.encode(userDto.getPassword());
        userDto.setStatus(Status.OK);
        userDto.setRole(roleMapper.toDtos(roleRepository.findByName("USER")).
                stream().collect(Collectors.toSet()));
        userDto.setPassword(encrypt);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }
}
