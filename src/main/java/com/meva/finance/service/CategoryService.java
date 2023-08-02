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

import javax.swing.text.html.Option;
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

    public Category saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequest.convert(new Category());

        return categoryRepository.save(category);
    }

    public SubCategory saveSubCategory(Integer idCategory, SubCategoryRequest subCategoryRequest) {
        Optional<Category> optCategory = categoryRepository.findById(idCategory);
        if (optCategory.isPresent()) {
            SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
            subCategory.setCategory(optCategory.get());

            return subCategoryRepository.save(subCategory);
        }
        throw new ValidException("NAO_CATEGORIZADO");
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(Integer idCategory) throws ValidException {
        Optional<Category> category = categoryRepository.findById(idCategory);
        try {
            category.ifPresent(category1 -> {
                categoryRepository.delete(category1);
            });
        } catch (ValidException ex) {
            throw new ValidException("Category Not Found");
        }
    }

    public void deleteSubCategory(Integer idSubCategory) {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(idSubCategory);
        try {
            subCategory.ifPresent(subCategory1 -> {
                subCategoryRepository.delete(subCategory1);
            });
        } catch (ValidException ex) {
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

        if (subCategory.getDescription().isEmpty()) {
            throw new ValidException("description subCategory is empty");
        }

        if (Objects.isNull(idSubCategory) || idSubCategory == 0) {
            subCategoryRepository.save(subCategory);
        }
    }

}
