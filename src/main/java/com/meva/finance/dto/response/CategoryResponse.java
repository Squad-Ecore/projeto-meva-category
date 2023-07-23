package com.meva.finance.dto.response;

import com.meva.finance.entity.Category;
import lombok.Data;

@Data
public class CategoryResponse {

    private Integer id;
    private String description;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.description = category.getDescription();
    }
}
