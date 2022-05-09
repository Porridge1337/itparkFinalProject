package tech.itpark.itparkfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;
import tech.itpark.itparkfinalproject.mapper.CategoryMapper;
import tech.itpark.itparkfinalproject.model.Category;
import tech.itpark.itparkfinalproject.repository.CategoryRepo;
import tech.itpark.itparkfinalproject.service.CategoryService;
import tech.itpark.itparkfinalproject.util.PictureUtil;

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
    @SneakyThrows
    @Transactional
    public CategoryDto save(CategoryDto table, MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            PictureUtil.deletePictures(table.getId());
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            table.setPicture(fileName);
            CategoryDto categoryDto = mapper.toDto(repo.save(mapper.toEntity(table)));
            PictureUtil.uploadPicture(multipartFile, categoryDto.getId(), fileName);
            return categoryDto;
        }
        return mapper.toDto(repo.save(mapper.toEntity(table)));
    }

    @Override
    @SneakyThrows
    @Transactional
    public void delete(String id) {
        repo.deleteById(id);
        PictureUtil.deletePictures(id);
    }

}
