package com.meva.finance.dto.response;

import com.meva.finance.entity.SubCategory;
import lombok.Data;

@Data
public class SubCategoryResponse {

    private Integer id;
    private String description;

    public SubCategoryResponse(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.description = subCategory.getDescription();
    }
}
