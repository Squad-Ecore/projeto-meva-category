
package com.meva.finance.api;

import com.meva.finance.dto.request.CategoryRequest;
import com.meva.finance.dto.response.CategoryResponse;
import com.meva.finance.entity.Category;
import com.meva.finance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class Controller {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.save(categoryRequest);
        CategoryResponse response = new CategoryResponse(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
