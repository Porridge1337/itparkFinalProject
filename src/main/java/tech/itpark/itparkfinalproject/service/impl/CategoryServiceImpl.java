package tech.itpark.itparkfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;
import tech.itpark.itparkfinalproject.mapper.CategoryMapper;
import tech.itpark.itparkfinalproject.model.Category;
import tech.itpark.itparkfinalproject.repository.CategoryRepo;
import tech.itpark.itparkfinalproject.service.CategoryService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo repo;
    private final CategoryMapper mapper;

    @Override
    public CategoryPageDto getPage(Pageable pageable) {
        Page<Category> categoryPage = repo.findAll(pageable);
        return new CategoryPageDto(mapper.toDtos(categoryPage.getContent()),
                categoryPage.getNumber(),
                categoryPage.getTotalPages(),
                categoryPage.hasNext(),
                categoryPage.hasPrevious());
    }

    @Override
    public Optional<CategoryDto> findById(String id) {
        return mapper.toOptionalDto(repo.findById(id));
    }

    @Override
    @Transactional
    public CategoryDto save(CategoryDto table) {
        return mapper.toDto(repo.save(mapper.toEntity(table)));
    }

    @Override
    @Transactional
    public void delete(String id) {
        repo.deleteById(id);
    }

}
