package tech.itpark.itparkfinalproject.service;

import org.springframework.data.domain.Pageable;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;

import java.util.Optional;

public interface CategoryService {

    CategoryDto save(CategoryDto table);

    CategoryPageDto getPage(Pageable pageable);

    Optional<CategoryDto> findById(String id);

    void delete(String id);

}
