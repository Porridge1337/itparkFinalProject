package tech.itpark.itparkfinalproject.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.itpark.itparkfinalproject.dto.ResultDto;
import tech.itpark.itparkfinalproject.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        categoryService.delete(id);
        return ResponseEntity.ok(new ResultDto());
    }

}
