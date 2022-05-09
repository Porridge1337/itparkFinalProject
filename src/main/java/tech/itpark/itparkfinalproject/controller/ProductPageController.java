package tech.itpark.itparkfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.itparkfinalproject.dto.ProductDto;
import tech.itpark.itparkfinalproject.dto.pagination.ProductPageDto;
import tech.itpark.itparkfinalproject.service.ProductService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequiredArgsConstructor
public class ProductPageController {

    private final ProductService productService;

    @GetMapping("/category/{categoryName}/{idCategory}")
    public String getProductsByCategory(@PathVariable String categoryName,
                                        @PathVariable String idCategory,
                                        @PositiveOrZero @RequestParam(required = false, defaultValue = "1") Integer page,
                                        @Positive @RequestParam(required = false, defaultValue = "5") Integer size,
                                        Model model) {
        ProductPageDto pageDto = productService.getPageByCategoryId(idCategory, PageRequest.of(page - 1, size));
        model.addAttribute("products", pageDto);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("idCategory", idCategory);
        return "/product/product";
    }

    @GetMapping("/category/{categoryName}/{idCategory}/create")
    public String getProductPageCreate(@PathVariable String categoryName,
                                       @PathVariable String idCategory,
                                       Model model) {
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("idCategory", idCategory);
        return "product/createAndUpdateProduct";
    }

    @GetMapping("/category/{categoryName}/{idCategory}/{product}/{idProduct}")
    public String getProductPage(@PathVariable String categoryName,
                                 @PathVariable String idCategory,
                                 @PathVariable String product,
                                 @PathVariable String idProduct,
                                 Model model) {
        ProductDto productDto = productService.findById(idProduct)
                .orElseThrow(() -> new IllegalArgumentException("Non existed product"));
        model.addAttribute("product", productDto);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("idCategory", idCategory);
        return "product/createAndUpdateProduct";
    }

    @PostMapping("/category/{categoryName}/{idCategory}/save")
    public String save(ProductDto productDto,
                       @PathVariable String categoryName,
                       @PathVariable String idCategory,
                       @RequestParam("fileImage")MultipartFile multipartFile) {
        productService.save(productDto, idCategory, multipartFile);
        return "redirect:/category/{categoryName}/{idCategory}";
    }

}
