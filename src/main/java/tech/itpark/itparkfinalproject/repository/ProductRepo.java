package tech.itpark.itparkfinalproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.itpark.itparkfinalproject.model.Product;

public interface ProductRepo extends JpaRepository<Product, String> {

    @Query("select c from Product c where c.categoryTable.id = :id")
    Page<Product> findProductByCategoryId(String id, Pageable pageable);

}
