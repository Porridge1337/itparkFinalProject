package tech.itpark.itparkfinalproject.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.itpark.itparkfinalproject.model.Category;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с таблицей category_table")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepo categoryRepo;

    @DisplayName(" должна уметь получать список категорий")
    @Test
    public void shouldReturnCorrectAllGenreList() {
        Category category = new Category();
        category.setCategoryName("name");
        category.setDescription("description");
        category.setPicture("pic.jpg");
        categoryRepo.save(category);

        val categories = categoryRepo.findAll();
        assertThat(categories).isNotNull().hasSize(1)
                .allMatch(not(s -> s.getId().isEmpty()))
                .allMatch(not(s -> s.getCategoryName().isEmpty()))
                .allMatch(not(s -> s.getDescription().isEmpty()))
                .allMatch(not(s -> s.getPicture().isEmpty()));
        categories.forEach(System.out::println);
    }

    @DisplayName(" должна уметь получать нужную категорию по id")
    @Test
    public void shouldReturnCorrectGenreById() {
        Category category = new Category();
        category.setCategoryName("name");
        category.setDescription("description");
        category.setPicture("pic.jpg");
        categoryRepo.save(category);
        String id = category.getId();

        val categories = categoryRepo.getOne(id);
        assertThat(categories).isNotNull()
                .hasFieldOrPropertyWithValue("id", id);
    }

    @DisplayName(" Должна уметь создавать категории, а потом загружать информацию о нем")
    @Test
    public void shouldSaveAndLoadCorrectGenre() {
        val expectedCategory = new Category();
        expectedCategory.setCategoryName("tst");
        expectedCategory.setPicture("pic.jpg");
        expectedCategory.setDescription("descirption");
        val actualGenre = categoryRepo.save(expectedCategory);
        assertThat(actualGenre).isEqualTo(expectedCategory);
    }

    @DisplayName(" должна уметь удалять категорию")
    @Test
    public void shouldDeleteGenre() {
        Category category = new Category();
        category.setCategoryName("name");
        category.setDescription("description");
        category.setPicture("pic.jpg");
        categoryRepo.save(category);

        val categoryCountBefore = categoryRepo.findAll().size();
        val newCategory = new Category();
        newCategory.setCategoryName("name2");
        newCategory.setDescription("description2");
        newCategory.setPicture("pic2.jpg");

        val genre = categoryRepo.save(newCategory);
        categoryRepo.delete(genre);
        val categoryCountAfter = categoryRepo.findAll().size();

        assertThat(categoryCountBefore).isEqualTo(categoryCountAfter);
    }
}
