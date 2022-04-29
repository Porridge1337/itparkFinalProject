package tech.itpark.itparkfinalproject.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.itpark.itparkfinalproject.dto.CategoryDto;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryPageDto {

    private List<CategoryDto> data;
    private int currentPage;
    private int totalPage;
    private boolean hasNext;
    private boolean hasPrevious;

}
