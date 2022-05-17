package tech.itpark.itparkfinalproject.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;
import tech.itpark.itparkfinalproject.repository.CategoryRepo;
import tech.itpark.itparkfinalproject.service.CategoryService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DisplayName("Кнтроллер для работы с категориями должен ")
@WebMvcTest(CategoryPageController.class)
public class CategoryPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    @DisplayName("уметь получать список категорий")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void categoryShouldReturnData() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId("111");
        productDto.setProductName("Milk");
        productDto.setAmount(new BigInteger("1"));
        productDto.setPrice(new BigDecimal("11.1"));
        productDto.setPicture("tst.jpg");
        productDto.setDescription("111111");

        CategoryDto category = new CategoryDto();
        category.setId("123");
        category.setCategoryName("name");
        category.setPicture("pic.jpg");
        category.setDescription("description");
        category.setProductTable(Set.of(productDto));

        List<CategoryDto> categoryDtoList = List.of(category);

        CategoryPageDto pageDto = new CategoryPageDto(categoryDtoList, 0, 1,
                false, false);

        Mockito.when(categoryService.getPage(PageRequest.of(0, 5))).thenReturn(pageDto);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("уметь получать категорию по его id для обновления")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void categoryShouldReturnDataForUpdatingById() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId("111");
        productDto.setProductName("Milk");
        productDto.setAmount(new BigInteger("1"));
        productDto.setPrice(new BigDecimal("11.1"));
        productDto.setPicture("tst.jpg");
        productDto.setDescription("111111");

        CategoryDto category = new CategoryDto();
        category.setId("123");
        category.setCategoryName("name");
        category.setPicture("pic.jpg");
        category.setDescription("description");
        category.setProductTable(Set.of(productDto));

        Mockito.when(categoryService.findById(category.getId())).thenReturn(Optional.of(category));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/category/" + category.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("уметь получать страницу для создания или обновления данных")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void categoryShouldReturnCreateAndUpdateCategoryPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/category/create"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
