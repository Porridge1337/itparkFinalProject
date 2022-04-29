package tech.itpark.itparkfinalproject.service;

import tech.itpark.itparkfinalproject.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    Optional<ProductDto> findById(String id);

    List<ProductDto> findAll();

}
