package tech.itpark.itparkfinalproject.service;

import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.model.Category;

import java.util.Optional;

public interface CategoryService {

    CategoryDto save(CategoryDto table);

    Optional<CategoryDto> findById(String id);

}
