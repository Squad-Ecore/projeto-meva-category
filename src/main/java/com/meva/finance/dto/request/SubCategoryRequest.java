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
    private Integer categoryRequestId;


    public SubCategoryRequest() {
    }

    public SubCategoryRequest(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public SubCategory convert(SubCategory subCategory) {
        FormatStringDescription format = new FormatStringDescription();


        subCategory.setId(this.id);

        format.removeStringPequenas(this.description);


        subCategory.setDescription(this.getDescription());

        return subCategory;
    }


    @Override
    public String toString() {
        return "SubCategoryRequest{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
