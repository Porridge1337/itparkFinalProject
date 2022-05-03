package tech.itpark.itparkfinalproject.service;

import org.springframework.data.domain.Pageable;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;
import tech.itpark.itparkfinalproject.dto.pagination.ProductPageDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    Optional<ProductDto> findById(String id);

    List<ProductDto> findAll();

    ProductPageDto getPageByCategoryId(String id, Pageable pageable);

}
