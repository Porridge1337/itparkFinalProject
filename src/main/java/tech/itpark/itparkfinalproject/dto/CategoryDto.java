package tech.itpark.itparkfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String id;

    @NotNull
    @NotBlank
    private String categoryName;

    private BigInteger amount;

    private String picture;

    @NotNull
    private String description;

    private Set<ProductDto> productTable;

    public String getProductImagePath() {
        if (id == null || picture == null) {
            return null;
        } else {
            return "/pictures/" + id + "/" + picture;
        }
    }

    public String getIdStr() {
        {
            return String.format("category_%s", id);
        }
    }

}
