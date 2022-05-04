package tech.itpark.itparkfinalproject.service;

import org.springframework.data.domain.Pageable;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.dto.pagination.ProductPageDto;

import java.util.Optional;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    Optional<ProductDto> findById(String id);

    ProductPageDto getPage(Pageable pageable);

    ProductPageDto getPageByCategoryId(String id, Pageable pageable);

}
