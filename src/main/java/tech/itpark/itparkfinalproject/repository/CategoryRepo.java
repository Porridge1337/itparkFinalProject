package tech.itpark.itparkfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.itpark.itparkfinalproject.model.Category;
import tech.itpark.itparkfinalproject.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, String> {
    @Query("select c from Category c join fetch c.productTable where c.id = :id")
    Optional<Category> findById(String id);

}
