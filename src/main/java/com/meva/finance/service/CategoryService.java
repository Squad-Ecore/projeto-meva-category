package com.meva.finance.service;

import com.meva.finance.dto.request.CategoryRequest;
import com.meva.finance.dto.request.SubCategoryRequest;
import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import com.meva.finance.exceptionCustom.custons.ValidException;
import com.meva.finance.repository.CategoryRepository;
import com.meva.finance.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }


    public Category save(CategoryRequest categoryRequest) throws ValidException {
        validCategory(categoryRequest);


        List<SubCategoryRequest> subCategoryRequests = categoryRequest.getSubCategoryRequests();
        if (subCategoryRequests != null && !subCategoryRequests.isEmpty()) {

            List<SubCategory> subCategories = new ArrayList<>();

            for (SubCategoryRequest subCategoryRequest : subCategoryRequests) {

                //
                SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
                //

                Category category = categoryRequest.convert(new Category());

                validSubCategory(subCategory);

                category.setSubCategories(subCategories);

                subCategory.setCategory(category);
                categoryRepository.save(category);


//                subCategory.setCategory(category);


//                return categoryRepository.save(category);
                return category;
            }
        }
        throw new ValidException("Erro relacionado a Category");
    }


    // IMPLEMENTAR UMA LIST PARA RETORNAR AS SUBCATEGORIES COM AS DESCRIPTION


    private void validCategory(CategoryRequest categoryRequest) {
        Integer idCategory = categoryRequest.getId();

        if (Objects.isNull(idCategory)) {
            throw new ValidException("id category Null");
        }
        if (categoryRequest.getDescription().isEmpty()) {
            throw new ValidException("Description category vazia");
        }
    }

    private SubCategory validSubCategory(SubCategory subCategory) {
        Integer idSubCategory = subCategory.getId();

        if (Objects.isNull(idSubCategory)) {
            throw new ValidException("Id Null");
        }

        if (idSubCategory == 0) {

            return subCategoryRepository.save(subCategory);

        } else if (subCategory.getDescription().isEmpty()) {
            throw new ValidException("description empty");
        }
        throw new ValidException("id não encontrado");
    }

    

    // desenvolver a category e subCategory onde salva normal juntamento com o método GET
    // O método GET vai pegar a descricao passada e vai buscar e entregar para mim no JSON

}
