package com.meva.finance.service;

import com.meva.finance.dto.request.CategoryRequest;
import com.meva.finance.dto.request.SubCategoryRequest;
import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import com.meva.finance.exceptionCustom.custons.ValidException;
import com.meva.finance.repository.CategoryRepository;
import com.meva.finance.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Category saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequest.convert(new Category());

        return categoryRepository.save(category);
    }


    public SubCategory saveSubCategory(SubCategoryRequest subCategoryRequest) throws ValidException {
        Integer idCategory = subCategoryRequest.getCategoryRequestId();

        Optional<Category> categoryOpt = categoryRepository.findById(idCategory);

        if (!categoryOpt.isPresent()) {
            throw new ValidException("Category not found");
        }

        SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
        subCategory.setCategory(categoryOpt.get());
        validDescriptionSubCategory(subCategory);
        subCategoryRepository.save(subCategory);

        return subCategory;
    }

    public List<Category> buscaTodasCategory() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<String> deleteCategory(Integer idCategory) throws ValidException {
        Optional<Category> category = categoryRepository.findById(idCategory);

        if (category.isPresent()) {
            categoryRepository.delete(category.get());

            return ResponseEntity.ok("Category Deletada");
        } else {
            throw new ValidException("Category Not Found");
        }

    }

    public ResponseEntity<String> deleteSubCategory(Integer idSubCategory) {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(idSubCategory);

        if (subCategory.isPresent()) {
            subCategoryRepository.delete(subCategory.get());

            return ResponseEntity.ok("SubCategory Deletada");
        } else {
            throw new ValidException("SubCategory Not Found");
        }
    }

    public Category updateCategory(CategoryRequest categoryRequest) {
        Optional<Category> category = categoryRepository.findById(categoryRequest.getId());
        if (category.isPresent()) {
            Category newCategory = categoryRequest.convert(new Category());

            return categoryRepository.save(newCategory);
        }
        throw new ValidException("Category Not Found");
    }

    public SubCategory updateSubCategory(Integer idCategory, SubCategoryRequest subCategoryRequest) {
        Optional<Category> categoryOpt = categoryRepository.findById(idCategory);

        if (categoryOpt.isPresent()) {
            Optional<SubCategory> subCategory = subCategoryRepository.findById(subCategoryRequest.getId());
            if (subCategory.isPresent()) {
                SubCategory newSubCategory = subCategoryRequest.convert(new SubCategory());
                newSubCategory.setCategory(categoryOpt.get());

                return subCategoryRepository.save(newSubCategory);
            }
        }
        throw new ValidException("SubCategory Not Found");
    }


    public Category buscaCategoryAtravesSubCategory(String description) {
        Optional<SubCategory> subCategory = subCategoryRepository.findByDescription(description);

        if (subCategory != null) {
            return subCategory.get().getCategory();

        } else {
            return new Category(1, "Nao_Category");
        }
    }


    private void validDescriptionSubCategory(SubCategory subCategory) {

        if (subCategory.getDescription().isEmpty()) {
            throw new ValidException("description subCategory is empty");
        }
    }

}
