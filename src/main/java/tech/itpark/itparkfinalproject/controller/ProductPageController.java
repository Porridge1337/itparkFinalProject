package tech.itpark.itparkfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.dto.pagination.ProductPageDto;
import tech.itpark.itparkfinalproject.service.ProductService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequiredArgsConstructor
public class ProductPageController {

    private final ProductService productService;

    @GetMapping("/categories/products")
    public String getProductPage(@PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer page,
                                 @Positive @RequestParam(required = false, defaultValue = "5") Integer size,
                                 Model model){
        ProductPageDto pageDto = productService.getPage(PageRequest.of(page, size));
        model.addAttribute("products", pageDto);
        return "product/product";
    }

    @GetMapping("/categories/products/create")
    public String getProductPageCreate(){
        return "product/createAndUpdateProduct";
    }

    @GetMapping("/category/{categoryName}/{idCategory}/{product}/{idProduct}")
    public String getProductPage(@PathVariable String categoryName,
                                 @PathVariable String idCategory,
                                 @PathVariable String product,
                                 @PathVariable String idProduct,
                                 Model model){
        ProductDto productDto = productService.findById(idProduct)
                .orElseThrow(() -> new IllegalArgumentException("Non existed product"));
        model.addAttribute("product", productDto);
        return "product/createAndUpdateProduct";
    }

}
