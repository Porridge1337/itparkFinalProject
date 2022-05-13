package tech.itpark.itparkfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;

    @NotNull
    @NotBlank(message = "имя продукта не должно быть пустым")
    private String productName;

    @NotNull(message = "указанная цена не должна быть пустой")
    @DecimalMin(value = "0.0", message = "цена не должна быть ниже 0")
    private BigDecimal price;

    @NotNull(message = "указанное колл-во не должно быть пустым")
    @Min(value = 0, message = "колличество товара на складе не должно быть меньше нуля")
    private BigInteger amount;

    private String picture;

    @NotNull
    private String description;

    private CategoryDto categoryTable;

    public String getProductImage() {
        if (productName == null || picture == null) {
            return null;
        } else {
            return "/pictures/" + id + "/" + picture;
        }
    }

    public String getIdStr() {
        {
            return String.format("product_%s", id);
        }
    }
}
