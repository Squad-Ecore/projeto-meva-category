package com.meva.finance.dto.request;

import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import lombok.Data;

@Data
public class SubCategoryRequest {

    private Integer id;
    private String description;

    public SubCategory convert(SubCategory subCategory) {

        subCategory.setId(this.id);
        subCategory.setDescription(this.description);
        subCategory.setCategory(subCategory.getCategory());

        return subCategory;
    }


}
