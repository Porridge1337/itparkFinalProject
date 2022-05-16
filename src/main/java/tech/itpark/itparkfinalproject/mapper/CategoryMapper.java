package tech.itpark.itparkfinalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(uses = ProductMapper.class)
public interface CategoryMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "categoryName", source = "entity.categoryName"),
            @Mapping(target = "picture", source = "entity.picture"),
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "productTable", source = "entity.productTable")
    })
    CategoryDto toDto(Category entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "categoryName", source = "dto.categoryName"),
            @Mapping(target = "picture", source = "dto.picture"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "productTable", source = "dto.productTable")
    })
    Category toEntity(CategoryDto dto);

    default Optional<CategoryDto> toOptionalDto(Optional<Category> entity) {
        return entity.map(this::toDto);
    }

    default List<CategoryDto> toDtos(List<Category> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

}
