
package com.meva.finance.api;

import com.meva.finance.dto.request.CategoryDto;
import com.meva.finance.dto.request.SubCategoryDto;
import com.meva.finance.dto.response.CategoryResponse;
import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import com.meva.finance.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class Controller {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save/category")
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryDto categoryRequest) {
        Category category = categoryService.saveCategory(categoryRequest);
        CategoryResponse response = new CategoryResponse(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/save/subCategory")
    public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategoryDto subCategoryRequest) {
        SubCategory subCategory = categoryService.saveSubCategory(subCategoryRequest);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/listCategory")
    public List<Category> buscaTodasCategory() {
        return categoryService.buscaTodasCategory();
    }


    @GetMapping("/getCategoryByExtract/{description}")
    public ResponseEntity<?> buscaDescriptionSubCategory(@PathVariable String description) {

        Integer idCategory = categoryService.buscaCategoryIdNaDescription(description);

        return ResponseEntity.ok(idCategory);
    }


}
