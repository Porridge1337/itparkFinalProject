package tech.itpark.itparkfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.dto.pagination.ProductPageDto;
import tech.itpark.itparkfinalproject.mapper.CategoryMapper;
import tech.itpark.itparkfinalproject.mapper.ProductMapper;
import tech.itpark.itparkfinalproject.model.Product;
import tech.itpark.itparkfinalproject.repository.CategoryRepo;
import tech.itpark.itparkfinalproject.repository.ProductRepo;
import tech.itpark.itparkfinalproject.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;
    private final CategoryMapper categoryMapper;
    private final ProductRepo repo;
    private final CategoryRepo categoryRepo;

    @Override
    public ProductPageDto getPage(Pageable pageable) {
        Page<Product> productPage = repo.findAll(pageable);
        return new ProductPageDto(mapper.toDtos(productPage.getContent()),
                productPage.getNumber(),
                productPage.getTotalPages(),
                productPage.hasNext(),
                productPage.hasPrevious());
    }

    @Override
    public ProductPageDto getPageByCategoryId(String id, Pageable pageable) {
        Page<Product> productPage = repo.findProductByCategoryId(id, pageable);

        return new ProductPageDto(mapper.toDtos(productPage.getContent()),
                productPage.getNumber(),
                productPage.getTotalPages(),
                productPage.hasNext(),
                productPage.hasPrevious());
    }

    @Override
    public List<ProductDto> findAll() {
        return mapper.toDtos(repo.findAll());
    }

    @Override
    public Optional<ProductDto> findById(String id) {
        return mapper.toOptionalDto(repo.findById(id));
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto, String categoryId) {
        Product product = mapper.toEntity(productDto);
        product.setCategoryTable(categoryRepo.findById(categoryId).orElseThrow());
        repo.save(product);
        return mapper.toDto(product);
    }

    @Override
    @Transactional
    public void delete(String id) {
        repo.deleteById(id);
    }
}
