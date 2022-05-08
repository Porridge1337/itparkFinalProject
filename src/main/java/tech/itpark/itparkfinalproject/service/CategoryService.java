package tech.itpark.itparkfinalproject.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;

import java.util.Optional;

public interface CategoryService {

    CategoryDto save(CategoryDto table, MultipartFile multipartFile);

    CategoryPageDto getPage(Pageable pageable);

    Optional<CategoryDto> findById(String id);

    void delete(String id);

}
