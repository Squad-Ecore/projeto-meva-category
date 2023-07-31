package com.meva.finance.dto.response;

import com.meva.finance.entity.SubCategory;
import lombok.Data;

import java.util.List;

@Data
public class SubCategoryResponse {

    private List<SubCategory> subCategories;

    private Integer id;
    private String description;

    public SubCategoryResponse(SubCategory subCategory) {
        subCategory.setId(this.id);
        subCategory.setDescription(this.description);
    }
}
