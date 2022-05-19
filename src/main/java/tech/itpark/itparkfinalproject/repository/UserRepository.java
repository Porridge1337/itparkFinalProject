package tech.itpark.itparkfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.itpark.itparkfinalproject.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u join fetch u.role where u.login = :login")
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);


}
