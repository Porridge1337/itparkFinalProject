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
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;
import tech.itpark.itparkfinalproject.dto.pagination.ProductPageDto;
import tech.itpark.itparkfinalproject.mapper.CategoryMapper;
import tech.itpark.itparkfinalproject.mapper.ProductMapper;
import tech.itpark.itparkfinalproject.model.Category;
import tech.itpark.itparkfinalproject.model.Product;
import tech.itpark.itparkfinalproject.repository.CategoryRepo;
import tech.itpark.itparkfinalproject.repository.ProductRepo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Сервист продуктов должен ")
public class ProductServiceTest {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductMapper mapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @MockBean
    private ProductRepo repo;
    @MockBean
    private CategoryRepo categoryRepo;

    @Test
    @DisplayName("ументь выдавать страницу с данными")
    public void testGetPage() {
        ProductDto productDto = new ProductDto();
        productDto.setId("123");
        productDto.setProductName("Product");
        productDto.setDescription("Some product");
        productDto.setPrice(new BigDecimal(11.1));
        productDto.setAmount(new BigInteger("1"));
        productDto.setPicture("pic.jpg");

        Page<Product> categoryPage = new PageImpl(List.of(mapper.toEntity(productDto)));
        ProductPageDto categoryPageDto = new ProductPageDto(List.of(productDto),
                0,
                1,
                false,
                false);

        when(repo.findAll(PageRequest.of(0, 1))).thenReturn(categoryPage);
        Assertions.assertEquals(categoryPageDto.getData(), service.getPage(PageRequest.of(0, 1)).getData());
    }

    @Test
    @DisplayName("ументь выдавать страницу с данными по id категории")
    public void testGetPageByCategoryId() {
        ProductDto productDto = new ProductDto();
        productDto.setId("123");
        productDto.setProductName("Product");
        productDto.setDescription("Some product");
        productDto.setPrice(new BigDecimal(11.1));
        productDto.setAmount(new BigInteger("1"));
        productDto.setPicture("pic.jpg");
        productDto.setCategoryTable(null);

        Page<Product> categoryPage = new PageImpl(List.of(mapper.toEntity(productDto)));
        ProductPageDto categoryPageDto = new ProductPageDto(List.of(productDto),
                0,
                1,
                false,
                false);

        when(repo.findProductByCategoryId("123",
                PageRequest.of(0, 1))).thenReturn(categoryPage);
        Assertions.assertEquals(categoryPageDto.getData(),
                service.getPageByCategoryId("123", PageRequest.of(0, 1)).getData());
    }

    @Test
    @DisplayName("ументь находить продукт по id")
    public void testFindProductById() {
        ProductDto productDto = new ProductDto();
        productDto.setId("123");
        productDto.setProductName("Product");
        productDto.setDescription("Some product");
        productDto.setPrice(new BigDecimal(11.1));
        productDto.setAmount(new BigInteger("1"));
        productDto.setPicture("pic.jpg");

        when(repo.findById("123")).thenReturn(Optional.of(mapper.toEntity(productDto)));
        Assertions.assertEquals(service.findById("123"), Optional.of(productDto));
    }

    @Test
    @DisplayName("ументь находить все продукты")
    public void testFindProducts() {
        Product productDto = new Product();
        productDto.setId("123");
        productDto.setProductName("Product");
        productDto.setDescription("Some product");
        productDto.setPrice(new BigDecimal(11.1));
        productDto.setAmount(new BigInteger("1"));
        productDto.setPicture("pic.jpg");

        Product productDto2 = new Product();
        productDto.setId("456");
        productDto.setProductName("Product2");
        productDto.setDescription("Some product2");
        productDto.setPrice(new BigDecimal(11.2));
        productDto.setAmount(new BigInteger("2"));
        productDto.setPicture("pic2.jpg");

        List<Product> productDtoList = List.of(productDto, productDto2);

        when(repo.findAll()).thenReturn(productDtoList);
        Assertions.assertEquals(service.findAll(), mapper.toDtos(productDtoList));
    }

    @Test
    @DisplayName("ументь сохранять и обновлять запись")
    public void testSaveAndUpdateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setId("123");
        productDto.setProductName("Product");
        productDto.setDescription("Some product");
        productDto.setPrice(new BigDecimal(11.1));
        productDto.setAmount(new BigInteger("1"));
        productDto.setPicture("pic.jpg");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("123");
        categoryDto.setCategoryName("Category");
        categoryDto.setPicture("pic.jpg");
        categoryDto.setDescription("contains products");
        categoryDto.setProductTable(Set.of(productDto));


        MockMultipartFile firstFile = new MockMultipartFile("test", (byte[]) null);

        when(categoryRepo.findById(categoryDto.getId())).thenReturn(Optional.of(categoryMapper.toEntity(categoryDto)));
        when(repo.save(mapper.toEntity(productDto))).thenReturn(mapper.toEntity(productDto));
        Assertions.assertEquals(service.save(productDto, categoryDto.getId(), firstFile), productDto);
    }

    @Test
    @DisplayName("уметь удалять запись")
    public void testDeleteProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setId("123");
        productDto.setProductName("Product");
        productDto.setDescription("Some product");
        productDto.setPrice(new BigDecimal(11.1));
        productDto.setAmount(new BigInteger("1"));
        productDto.setPicture("pic.jpg");

        when(repo.findById("123")).thenReturn(Optional.of(mapper.toEntity(productDto)));
        when(repo.existsById(productDto.getId())).thenReturn(false);
        Assertions.assertFalse(repo.existsById(productDto.getId()));

    }

}
