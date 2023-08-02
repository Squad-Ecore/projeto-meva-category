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

        subCategory.setId(this.id);

        subCategory.setDescription(formatDescription(this.description));

        subCategory.setCategory(subCategory.getCategory());

        return subCategory;
    }

    public String formatDescription(String description) {
        return FormatStringDescription.removeStringPequenas(this.description);
    }


    @Override
    public String toString() {
        return "SubCategoryRequest{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
