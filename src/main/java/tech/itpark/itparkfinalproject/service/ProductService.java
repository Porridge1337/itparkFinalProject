package tech.itpark.itparkfinalproject.service;

import org.springframework.data.domain.Pageable;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.dto.pagination.ProductPageDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto save(ProductDto productDto, String categoryId);

    Optional<ProductDto> findById(String id);

    ProductPageDto getPage(Pageable pageable);

    ProductPageDto getPageByCategoryId(String id, Pageable pageable);

    List<ProductDto> findAll();

    void delete(String id);

}
