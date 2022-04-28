package tech.itpark.itparkfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.itpark.itparkfinalproject.model.Category;

public interface CategoryRepo extends JpaRepository<Category, String> {
}
