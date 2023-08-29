package com.meva.finance.dto.request;

import com.meva.finance.entity.SubCategory;
import com.meva.finance.repository.SubCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SubCategoryDto {

    private Integer id;
    private String description;
    private Integer categoryRequestId;

    public SubCategory convert(SubCategory subCategory) {
        subCategory.setId(this.id);

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
