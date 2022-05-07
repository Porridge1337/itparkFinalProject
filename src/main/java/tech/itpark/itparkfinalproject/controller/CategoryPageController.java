package tech.itpark.itparkfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.itpark.itparkfinalproject.dto.CategoryDto;
import tech.itpark.itparkfinalproject.dto.pagination.CategoryPageDto;
import tech.itpark.itparkfinalproject.service.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequiredArgsConstructor
@Validated
public class CategoryPageController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String getCategoryPage(@PositiveOrZero @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @Positive @RequestParam(required = false, defaultValue = "5") Integer size,
                                  Model model) {
        CategoryPageDto categoryPageDto = categoryService.getPage(PageRequest.of(page-1, size));
        model.addAttribute("category", categoryPageDto);
        return "category/category";
    }

    @GetMapping("/category/create")
    public String getCategoryPageCreate() {
        return "category/createAndUpdateCategory";
    }

    @GetMapping("/category/{id}")
    public String getCategoryPageById(@PathVariable String id,
                                      Model model) {
        CategoryDto categoryDto = categoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Non existed category"));
        model.addAttribute("category", categoryDto);
        return "category/createAndUpdateCategory";
    }

    @PostMapping("/category/save")
    public String save(CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return "redirect:/categories";
    }

}
