package tech.itpark.itparkfinalproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;
import tech.itpark.itparkfinalproject.mapper.CategoryMapper;
import tech.itpark.itparkfinalproject.model.Category;
import tech.itpark.itparkfinalproject.repository.CategoryRepo;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Сервист категорий должен ")
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper mapper;
    @MockBean
    private CategoryRepo repo;

    @Test
    @DisplayName("ументь выдавать страницу с данными")
    public void testGetPage() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("123");
        categoryDto.setCategoryName("Category");
        categoryDto.setPicture("pic.jpg");
        categoryDto.setDescription("contains products");

        Page<Category> categoryPage = new PageImpl(List.of(mapper.toEntity(categoryDto)));
        CategoryPageDto categoryPageDto = new CategoryPageDto(List.of(categoryDto),
                0,
                1,
                false,
                false);

        when(repo.findAll(PageRequest.of(0, 1))).thenReturn(categoryPage);
        Assertions.assertEquals(categoryPageDto.getData(), categoryService.getPage(PageRequest.of(0, 1)).getData());
    }

    @Test
    @DisplayName("ументь находить категорию по id")
    public void testFindCategoryById() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("123");
        categoryDto.setCategoryName("Category");
        categoryDto.setPicture("pic.jpg");
        categoryDto.setDescription("contains products");

        when(repo.findById("123")).thenReturn(Optional.of(mapper.toEntity(categoryDto)));
        Assertions.assertEquals(categoryService.findById("123"), Optional.of(categoryDto));
    }

    @Test
    @DisplayName("ументь удалять запись")
    public void testDeleteCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("123");
        categoryDto.setCategoryName("Category");
        categoryDto.setPicture("pic.jpg");
        categoryDto.setDescription("contains products");

        when(repo.findById("123")).thenReturn(Optional.of(mapper.toEntity(categoryDto)));
        when(repo.existsById(categoryDto.getId())).thenReturn(false);
        Assertions.assertFalse(repo.existsById(categoryDto.getId()));
    }

    @Test
    @DisplayName("ументь сохранять и обновлять запись")
    public void testSaveAndUpdateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("123");
        categoryDto.setCategoryName("Category");
        categoryDto.setPicture("pic.jpg");
        categoryDto.setDescription("contains products");
        MockMultipartFile firstFile = new MockMultipartFile("test", (byte[]) null);
        when(repo.save(mapper.toEntity(categoryDto))).thenReturn(mapper.toEntity(categoryDto));
        Assertions.assertEquals(categoryService.save(categoryDto, firstFile), categoryDto);
    }
}
