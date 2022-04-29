package tech.itpark.itparkfinalproject.service;

import tech.itpark.itparkfinalproject.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryDto save(CategoryDto table);

    Optional<CategoryDto> findById(String id);

    List<CategoryDto> findAll();

}
