package com.meva.finance.dto.request;

import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class CategoryRequest {

    private Integer id;
    private String description;
    private List<SubCategoryRequest> subCategoryRequests;

    public CategoryRequest() {
    }

    public CategoryRequest(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

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
