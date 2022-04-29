package tech.itpark.itparkfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.itpark.itparkfinalproject.model.Product;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, String> {

   /* @Query("select p from Product p join fetch p.categoryTable where p.id = :id")
    Optional<Product> findById(String id);*/

}
