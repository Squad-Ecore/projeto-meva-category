package com.meva.finance.dto.request;

import com.meva.finance.entity.Category;
import lombok.Data;

@Data
public class CategoryRequest {

    private Integer id;
    private String description;

    public Category convert(Category category) {
        this.id = category.getId();
        this.description = category.getDescription();

        return category;
    }
}
