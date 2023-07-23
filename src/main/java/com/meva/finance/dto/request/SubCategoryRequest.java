package com.meva.finance.dto.request;

import com.meva.finance.entity.SubCategory;
import lombok.Data;

@Data
public class SubCategoryRequest {

    private Integer id;
    private String description;

    public SubCategory convert(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.description = subCategory.getDescription();

        return subCategory;
    }


}
