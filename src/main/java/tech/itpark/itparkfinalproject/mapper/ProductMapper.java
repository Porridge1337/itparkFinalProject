package tech.itpark.itparkfinalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "productName", source = "entity.productName"),
            @Mapping(target = "price", source = "entity.price"),
            @Mapping(target = "amount", source = "entity.amount"),
            @Mapping(target = "picture", source = "entity.picture"),
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "categoryTable", source = "entity.categoryTable", ignore = true)
    })
    ProductDto toDto(Product entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "productName", source = "dto.productName"),
            @Mapping(target = "price", source = "dto.price"),
            @Mapping(target = "amount", source = "dto.amount"),
            @Mapping(target = "picture", source = "dto.picture"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "categoryTable", source = "dto.categoryTable", ignore = true)
    })
    Product toEntity(ProductDto dto);

   /* @Named("productTableDtoList")
    default Set<ProductDto> toProductTableDto(Set<Product>source){
        return source.stream()
                .map(this::toDto)
                .peek(dto -> dto.setCategoryTable(dto.getCategoryTable()))
                .collect(Collectors.toSet());
    }
    @Named("productTableEntityList")
    default Set<Product> toProductTableEntity(Set<ProductDto>source){
        return source.stream()
                .map(this::toEntity)
                .peek(entity -> entity.setCategoryTable(entity.getCategoryTable()))
                .collect(Collectors.toSet());
    }*/


    default Optional<ProductDto> toOptionalDto(Optional<Product> entity) {
        return entity.map(this::toDto);
    }

    default List<ProductDto> toDtos(List<Product> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

}
