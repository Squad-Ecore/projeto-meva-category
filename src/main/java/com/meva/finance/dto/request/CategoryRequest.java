package com.meva.finance.dto.request;

import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryRequest {

    private Integer id;
    private String description;
    private List<SubCategoryRequest> subCategoryRequests;

    public Category convert(Category category) {
        category.setId(this.id);
        category.setDescription(this.description);

        return category;
    }

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
