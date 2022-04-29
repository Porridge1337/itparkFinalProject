package tech.itpark.itparkfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;

    @NotNull
    private String productName;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigInteger amount;

    private String picture;

    @NotNull
    private String description;

    private CategoryDto categoryTable;
}