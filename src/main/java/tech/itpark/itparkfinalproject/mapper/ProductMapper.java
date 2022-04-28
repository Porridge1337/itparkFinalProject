package tech.itpark.itparkfinalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.model.Product;

import java.util.Optional;

@Mapper
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "productName", source = "entity.productName"),
            @Mapping(target = "price", source = "entity.price"),
            @Mapping(target = "amount", source = "entity.amount"),
            @Mapping(target = "picture", source = "entity.picture"),
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "categoryTable", source = "entity.categoryTable")
    })
    ProductDto toDto(Product entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "productName", source = "dto.productName"),
            @Mapping(target = "price", source = "dto.price"),
            @Mapping(target = "amount", source = "dto.amount"),
            @Mapping(target = "picture", source = "dto.picture"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "categoryTable", source = "dto.categoryTable")
    })
    Product toEntity(ProductDto dto);

    default Optional<ProductDto> toOptionalDto(Optional<Product> entity) {
        return entity.map(this::toDto);
    }

}
