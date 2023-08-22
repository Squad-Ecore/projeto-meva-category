package com.meva.finance.dto.request;

import com.meva.finance.entity.SubCategory;
import com.meva.finance.resource.FormatStringDescription;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@Getter
@Setter
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

        subCategory.setDescription(format.removeStringPequenas(this.getDescription()));

        subCategory.setCategory(subCategory.getCategory());

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
