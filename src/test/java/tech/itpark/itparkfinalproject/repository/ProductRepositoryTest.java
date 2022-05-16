package tech.itpark.itparkfinalproject.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.itpark.itparkfinalproject.model.Category;
import tech.itpark.itparkfinalproject.model.Product;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с таблицей product_table")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @DisplayName(" должна уметь получать список продуктов")
    @Test
    public void shouldReturnCorrectAllProductList() {
        val category = new Category();
        category.setCategoryName("name");
        category.setDescription("description");
        category.setPicture("pic.jpg");
        categoryRepo.save(category);

        val product = new Product();
        product.setProductName("name");
        product.setDescription("description");
        product.setPicture("pic.jpg");
        product.setAmount(new BigInteger("1"));
        product.setPrice(new BigDecimal(11.1));
        product.setCategoryTable(category);
        productRepo.save(product);

        val products = productRepo.findAll();
        assertThat(products).isNotNull()
                .allMatch(not(s -> s.getId().isEmpty()))
                .allMatch(not(s -> s.getProductName().isEmpty()))
                .allMatch(not(s -> s.getDescription().isEmpty()))
                .allMatch(not(s -> s.getPicture().isEmpty()));
        products.forEach(System.out::println);
    }

    @DisplayName(" должна уметь получать нужный продукт по id")
    @Test
    public void shouldReturnCorrectProductById() {
        val category = new Category();
        category.setCategoryName("name");
        category.setDescription("description");
        category.setPicture("pic.jpg");
        categoryRepo.save(category);

        val product = new Product();
        product.setProductName("name");
        product.setDescription("description");
        product.setPicture("pic.jpg");
        product.setAmount(new BigInteger("1"));
        product.setPrice(new BigDecimal(11.1));
        product.setCategoryTable(category);
        productRepo.save(product);

        String id = product.getId();

        val categories = productRepo.getOne(id);
        assertThat(categories).isNotNull()
                .hasFieldOrPropertyWithValue("id", id);
    }

    @DisplayName(" Должна уметь создавать продукты, а потом загружать информацию о них")
    @Test
    public void shouldSaveAndLoadCorrectCategory() {
        val category = new Category();
        category.setCategoryName("name");
        category.setDescription("description");
        category.setPicture("pic.jpg");
        categoryRepo.save(category);

        val product = new Product();
        product.setProductName("name");
        product.setDescription("description");
        product.setPicture("pic.jpg");
        product.setAmount(new BigInteger("1"));
        product.setPrice(new BigDecimal(11.1));
        product.setCategoryTable(category);
        productRepo.save(product);

        val actualGenre = productRepo.save(product);
        assertThat(actualGenre).isEqualTo(product);
    }

    @DisplayName(" должна уметь удалять продукты")
    @Test
    public void shouldDeleteProduct() {
        val category = new Category();
        category.setCategoryName("name");
        category.setDescription("description");
        category.setPicture("pic.jpg");
        categoryRepo.save(category);

        val product = new Product();
        product.setProductName("name");
        product.setDescription("description");
        product.setPicture("pic.jpg");
        product.setAmount(new BigInteger("1"));
        product.setPrice(new BigDecimal(11.1));
        product.setCategoryTable(category);
        productRepo.save(product);

        val productCountBefore = productRepo.findAll().size();

        val newProduct = new Product();
        newProduct.setProductName("name2");
        newProduct.setDescription("description2");
        newProduct.setPicture("pic2.jpg");
        newProduct.setAmount(new BigInteger("2"));
        newProduct.setPrice(new BigDecimal(11.2));
        newProduct.setCategoryTable(category);

        val product1 = productRepo.save(newProduct);
        productRepo.delete(product1);
        val productCountAfter = categoryRepo.findAll().size();

        assertThat(productCountBefore).isEqualTo(productCountAfter);
    }

}
