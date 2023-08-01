
package com.meva.finance.api;

import com.meva.finance.dto.request.CategoryRequest;
import com.meva.finance.dto.request.SubCategoryRequest;
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
@RequestMapping("/users")
public class Controller {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save/category")
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.saveCategory(categoryRequest);
        CategoryResponse response = new CategoryResponse(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/save/{idCategory}/subCategory")
    public ResponseEntity<SubCategory> saveSubCategory(@PathVariable Integer idCategory, @RequestBody SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = categoryService.saveSubCategory(idCategory, subCategoryRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/listSubCategory")
    public List<Category> findAllSubCategory() {

        return categoryService.findAllCategory();
    }


    @GetMapping("/getCategoryByExtract/{description}")
    public ResponseEntity<String> findByDescriptionSubCategory(@PathVariable String description) {
        String idCategory = categoryService.findIdCategoryInSubCategory(description);

        return ResponseEntity.ok("Category: " + idCategory);
    }


    @DeleteMapping("/delete/category/{idCategory}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer idCategory) {
        categoryService.deleteCategory(idCategory);

        return ResponseEntity.ok("Category Deletada");
    }

    @DeleteMapping("delete/subcategory/{idSubCategory}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable Integer idSubCategory) {
        categoryService.deleteSubCategory(idSubCategory);

        return ResponseEntity.ok("SubCategory Deletada");
    }


    @PutMapping("/update/category")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.updateCategory(categoryRequest);
        CategoryResponse response = new CategoryResponse(category);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/subcategory")
    public ResponseEntity<String> updateSubCategory(@RequestBody SubCategoryRequest subCategoryRequest) {
        categoryService.updateSubCategory(subCategoryRequest);

        return ResponseEntity.ok("SubCategory update");
    }

}
