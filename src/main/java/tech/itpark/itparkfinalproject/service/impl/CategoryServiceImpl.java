package tech.itpark.itparkfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.mapper.CategoryMapper;
import tech.itpark.itparkfinalproject.repository.CategoryRepo;
import tech.itpark.itparkfinalproject.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo repo;
    private final CategoryMapper mapper;

    @Override
    @Transactional
    public CategoryDto save(CategoryDto table) {
        return mapper.toDto(repo.save(mapper.toEntity(table)));
    }

    @Override
    public Optional<CategoryDto> findById(String id) {
        return mapper.toOptionalDto(repo.findById(id));
    }

    @Override
    public List<CategoryDto> findAll() {
        return mapper.toDtos(repo.findAll());
    }
}
