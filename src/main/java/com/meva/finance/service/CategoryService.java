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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }


//    public Category save(CategoryRequest categoryRequest) throws ValidException {
//
//        validCategory(categoryRequest);
//
//        List<SubCategory> subCategories = new ArrayList<>();
//        List<SubCategoryRequest> subCategoryRequests = categoryRequest.getSubCategoryRequests();
//
//        Category category = categoryRequest.convert(new Category());
//        categoryRepository.save(category);
//
//
////        subCategories.add(new SubCategory());
//        if (subCategoryRequests != null && !subCategoryRequests.isEmpty()) {
//            for (SubCategoryRequest subCategoryRequest : subCategoryRequests) {
//                SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
//
////                category.setSubCategories(subCategories);
//
////                if (category.getId() == 0) {
////                    categoryRepository.save(category);
////                }
//
//                subCategory.setCategory(category);
//                validSubCategory(subCategory);
//                subCategories.add(subCategory);
//
//
//            }
//        }
//        category.setSubCategories(subCategories);
//
//        return category;
//    }


    // IMPLEMENTAR UMA LIST PARA RETORNAR AS SUBCATEGORIES COM AS DESCRIPTION

//    public Category saveCategory(CategoryRequest categoryRequest) {
//        validCategory(categoryRequest);
//
//        Category category = categoryRequest.convert(new Category());
//        category.setSubCategories(new ArrayList<>());
//
//        return categoryRepository.save(category);
//
//    }


//    public Category saveCategory(CategoryRequest categoryRequest) {
//        Category category = categoryRequest.convert(new Category());
//        categoryRepository.save(category);
//
//        List<SubCategoryRequest> subCategoryRequests = categoryRequest.getSubCategoryRequests();
//
//        if (subCategoryRequests != null && !subCategoryRequests.isEmpty()) {
//            for (SubCategoryRequest subCategoryRequest : subCategoryRequests) {
//
//                SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
//                subCategory.setDescription(subCategoryRequest.getDescription());
//                subCategory.setCategory(category);
//                category.getSubCategories().add(subCategory);
//
//                validSubCategory(subCategory);
//            }
//        }
//        return category;
//    }


    public Category saveCategory(CategoryRequest categoryRequest){
        Category category = categoryRequest.convert(new Category());

        return categoryRepository.save(category);
    }


    public SubCategory saveSubCategory(Integer idCategory, SubCategoryRequest subCategoryRequest){
        Optional<Category> optCategory = categoryRepository.findById(idCategory);
        if (optCategory.isPresent()){
            SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
            subCategory.setCategory(optCategory.get());

            return subCategoryRepository.save(subCategory);


        }
        throw new ValidException("NAO_CATEGORIZADO");

    }










    public String findIdCategoryInSubCategory(String description) throws ValidException {
        SubCategory subCategoryGetDescription = subCategoryRepository.findByDescription(description);


        if (subCategoryGetDescription != null) {
            return subCategoryGetDescription.getCategory().getDescription();
        } else {
            throw new ValidException("NAO_CATEGORIZADO");
        }
    }


    // desenvolver a category e subCategory onde salva normal juntamento com o método GET
    // O método GET vai pegar a descricao passada e vai buscar e entregar para mim no JSON


    public void validSubCategory(SubCategory subCategory) {
        Integer idSubCategory = subCategory.getId();

//        if (subCategoryRepository.existsById(idSubCategory)) {
//            throw new ValidException("id subCategory existe");
        if (subCategory.getDescription().isEmpty()) {
            throw new ValidException("description subCategory is empty");
        }

        if (Objects.isNull(idSubCategory) || idSubCategory == 0) {
            subCategoryRepository.save(subCategory);
        }
    }

}
