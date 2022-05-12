package tech.itpark.itparkfinalproject.service.security.imlp;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.itpark.itparkfinalproject.repository.UserRepository;
import tech.itpark.itparkfinalproject.service.security.UserPrincipal;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).map(UserPrincipal::new)
                .orElseThrow(() -> new IllegalArgumentException("Has no user with login " + username));
    }
}
