package com.meva.finance.dto.request;

import com.meva.finance.entity.SubCategory;
import com.meva.finance.resource.FormatStringDescription;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SubCategoryRequest {

    private Integer id;
    private String description;

    public SubCategoryRequest(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public SubCategory convert(SubCategory subCategory) {
        FormatStringDescription formatStringDescription = new FormatStringDescription();

        subCategory.setId(this.id);

        subCategory.setDescription(formatStringDescription.removeStringPequenas(this.description));

        subCategory.setDescription(subCategory.getDescription());

        subCategory.setCategory(subCategory.getCategory());

        return subCategory;
    }

}
