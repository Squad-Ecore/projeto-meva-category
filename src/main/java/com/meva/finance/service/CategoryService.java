package com.meva.finance.service;

import com.meva.finance.dto.request.CategoryRequest;
import com.meva.finance.dto.request.SubCategoryRequest;
import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import com.meva.finance.exceptionCustom.custons.ValidException;
import com.meva.finance.formatString.FormatStringDescription;
import com.meva.finance.repository.CategoryRepository;
import com.meva.finance.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private FormatStringDescription formatStringDescription;

    @Autowired
    public CategoryService(
            CategoryRepository categoryRepository,
            SubCategoryRepository subCategoryRepository,
            FormatStringDescription formatStringDescription) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.formatStringDescription = formatStringDescription;
    }


    public Category save(CategoryRequest categoryRequest) throws ValidException {
        if (categoryRepository.existsById(categoryRequest.getId())) {
            throw new ValidException("id category já existe");
        }

        List<SubCategoryRequest> subCategoryRequests = categoryRequest.getSubCategoryRequests();
        if (subCategoryRequests != null && !subCategoryRequests.isEmpty()) {

            List<SubCategory> subCategories = new ArrayList<>();

            for (SubCategoryRequest subCategoryRequest : subCategoryRequests) {
                SubCategory subCategory = subCategoryRequest.convert(new SubCategory());

                formatStringDescription.removeStringPequenas(subCategory.getDescription());

                Category category = categoryRequest.convert(new Category());

                category.setSubCategories(subCategories);

                subCategory.setCategory(category);
                validSubCategory(subCategory);
//                subCategory.setCategory(category);


                return categoryRepository.save(category);
            }
        }
        throw new ValidException("Erro relacionado a Category");
    }


    private SubCategory validSubCategory(SubCategory subCategory) {
        Integer idSubCategory = subCategory.getId();

        if (Objects.isNull(idSubCategory)) {
            throw new ValidException("Id Null");
        }

        if (idSubCategory == 0 || subCategoryRepository.existsById(idSubCategory)) {

            return subCategoryRepository.save(subCategory);

        } else if (subCategory.getDescription().isEmpty()) {
            throw new ValidException("description empty");
        }
        throw new ValidException("id não encontrado");
    }
}
