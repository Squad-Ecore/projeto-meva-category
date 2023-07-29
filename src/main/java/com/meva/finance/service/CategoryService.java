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
//        if (categoryRepository.findById(categoryRequest.getId()).isPresent()) {
//            throw new ValidException("Category já existe no banco de dados");
//        }
        validCategory(categoryRequest);
        Category category = categoryRequest.convert(new Category());

        categoryRepository.save(category);

        List<SubCategoryRequest> subCategoryRequests = categoryRequest.getSubCategoryRequests();
        if (subCategoryRequests != null && !subCategoryRequests.isEmpty()) {

            List<SubCategory> subCategories = new ArrayList<>();

            category.setSubCategories(subCategories);

//            if (category.getId() == 0) {
//                categoryRepository.save(category);
//            }


            for (SubCategoryRequest subCategoryRequest : subCategoryRequests) {
                SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
                subCategory.setCategory(category);

                if (subCategory.getId() != null || subCategory.getId() > 0) {

                    SubCategory existSubCategory = subCategoryRepository.findById(subCategory.getId()).orElse(null);
                    if (existSubCategory != null) {
                        existSubCategory.setDescription(subCategory.getDescription());
                        subCategory = existSubCategory;
                    }
                }

                validSubCategory(subCategory);
                subCategories.add(subCategory);
            }
            category.setSubCategories(subCategories);
        }
        return category;
//        throw new ValidException("Erro relacionado a Category");
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

        if (Objects.isNull(idSubCategory) || idSubCategory == 0) {
//            throw new ValidException("Id Null");
            subCategoryRepository.save(subCategory);
        }
        if (subCategoryRepository.existsById(idSubCategory)) {
            throw new ValidException("id Sub_category já existe");
        } else if (subCategory.getDescription().isEmpty()) {
            throw new ValidException("description empty");
        }

        return subCategory;
    }


    // desenvolver a category e subCategory onde salva normal juntamento com o método GET
    // O método GET vai pegar a descricao passada e vai buscar e entregar para mim no JSON

}
