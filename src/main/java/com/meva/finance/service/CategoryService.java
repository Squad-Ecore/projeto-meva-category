package com.meva.finance.service;

import com.meva.finance.dto.request.CategoryDto;
import com.meva.finance.dto.request.SubCategoryDto;
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

    public Category saveCategory(CategoryDto categoryDto) {
        Category category = categoryDto.convert(new Category());

        String nomeCategoryMinusculo = category.getDescription();
        nomeCategoryMinusculo = nomeCategoryMinusculo.toLowerCase();


        category.setDescription(nomeCategoryMinusculo);

        return categoryRepository.save(category);
    }


    public SubCategory saveSubCategory(SubCategoryDto subCategoryDto) throws ValidException {
        Integer idCategory = subCategoryDto.getCategoryRequestId();

        Optional<Category> categoryOpt = categoryRepository.findById(idCategory);

        if (!categoryOpt.isPresent()) {
            throw new ValidException("Category not found");
        }

        String descriptionMinusculo = subCategoryDto.getDescription();
        descriptionMinusculo = descriptionMinusculo.toLowerCase();

        subCategoryDto.setDescription(descriptionMinusculo);

        SubCategory subCategory = subCategoryDto.convert(new SubCategory());
        subCategory.setCategory(categoryOpt.get());
        validDescriptionSubCategory(subCategory);
        subCategoryRepository.save(subCategory);

        return subCategory;
    }

    public List<Category> buscaTodasCategory() {
        return categoryRepository.findAll();
    }


    private void validDescriptionSubCategory(SubCategory subCategory) {
        String description = subCategory.getDescription();

        if (description.isEmpty() || description.length() < 3) {
            throw new ValidException("description vazio ou menor que 3");
        }
    }


    public Integer buscaCategoryIdNaDescription(String description) {
        description.toLowerCase();
        String[] palavras = description.split("\\s+");

        for (String palavra : palavras) {
            if (palavra.length() >= 3) {

                Optional<SubCategory> subOpt = subCategoryRepository.findByDescription(palavra);
                if (subOpt.isPresent()) {
                    return subOpt.get().getCategory().getId();
                }
            }
        }
        return new Category(1, "Nao_Categorizado").getId();
    }


}
