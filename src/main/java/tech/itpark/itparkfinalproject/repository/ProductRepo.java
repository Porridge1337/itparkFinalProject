package tech.itpark.itparkfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.itpark.itparkfinalproject.model.Product;

public interface ProductRepo extends JpaRepository<Product, String> {
}
