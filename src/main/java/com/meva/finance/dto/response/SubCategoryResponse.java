package com.meva.finance.dto.response;

import com.meva.finance.entity.SubCategory;
import lombok.Data;

import java.util.List;

@Data
public class SubCategoryResponse {

    private List<SubCategory> subCategories;

}
