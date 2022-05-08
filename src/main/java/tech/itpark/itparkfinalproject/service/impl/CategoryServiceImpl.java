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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            table.setPicture(fileName);
            uploadPicture(multipartFile, table.getCategoryName(), fileName);
        }
        return mapper.toDto(repo.save(mapper.toEntity(table)));
    }

    @Override
    @Transactional
    public void delete(String id) {
        repo.deleteById(id);
    }

    private void uploadPicture(MultipartFile multipartFile, String categoryName, String fileName) throws IOException {
        String uploadDir = "./pictures/" + categoryName;
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }
    }
}
