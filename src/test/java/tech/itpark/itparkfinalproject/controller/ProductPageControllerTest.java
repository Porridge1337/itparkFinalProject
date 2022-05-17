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
import tech.itpark.itparkfinalproject.dto.pagination.ProductPageDto;
import tech.itpark.itparkfinalproject.service.ProductService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@DisplayName("Кнтроллер для работы с продуктами должен ")
@WebMvcTest(ProductPageController.class)
public class ProductPageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    @DisplayName("уметь получать список продуктов")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void categoryShouldReturnData() throws Exception {
        CategoryDto category = new CategoryDto();
        category.setId("123");
        category.setCategoryName("name");
        category.setPicture("pic.jpg");
        category.setDescription("description");

        ProductDto productDto = new ProductDto();
        productDto.setId("111");
        productDto.setProductName("Milk");
        productDto.setAmount(new BigInteger("1"));
        productDto.setPrice(new BigDecimal("11.1"));
        productDto.setPicture("tst.jpg");
        productDto.setDescription("111111");
        productDto.setCategoryTable(category);

        List<ProductDto> productDtoList = List.of(productDto);
        ProductPageDto pageDto = new ProductPageDto(productDtoList, 0, 1,
                false, false);

        Mockito.when(productService.getPageByCategoryId(category.getId(), PageRequest.of(0, 5))).thenReturn(pageDto);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/category/" + category.getCategoryName() + "/" +
                        category.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("уметь получать страницу для создания или обновления данных")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void categoryShouldReturnCreateAndUpdateCategoryPage() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/category/product/858493986/create"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
