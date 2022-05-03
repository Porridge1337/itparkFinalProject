package tech.itpark.itparkfinalproject.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.itpark.itparkfinalproject.dto.ProductDto;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductPageDto {

    private List<ProductDto> data;
    private int currentPage;
    private int totalPage;
    private boolean hasNext;
    private boolean hasPrevious;

}
