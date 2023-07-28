package com.meva.finance.dto.response;

import com.meva.finance.entity.SubCategory;
import lombok.Data;

import java.util.List;

@Data
public class SubCategoryResponse {

    private List<SubCategory> subCategories;

    public SubCategoryResponse(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
