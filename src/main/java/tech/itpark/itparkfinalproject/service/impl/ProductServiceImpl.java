package tech.itpark.itparkfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.mapper.ProductMapper;
import tech.itpark.itparkfinalproject.repository.ProductRepo;
import tech.itpark.itparkfinalproject.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;
    private final ProductRepo repo;

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        return mapper.toDto(repo.save(mapper.toEntity(productDto)));
    }

    @Override
    public Optional<ProductDto> findById(String id) {
        return mapper.toOptionalDto(repo.findById(id));
    }

    @Override
    public List<ProductDto> findAll() {
        return mapper.toDtos(repo.findAll());
    }
}
