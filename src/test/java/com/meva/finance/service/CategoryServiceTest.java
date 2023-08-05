package com.meva.finance.service;

import com.meva.finance.dto.request.CategoryRequest;
import com.meva.finance.dto.request.SubCategoryRequest;
import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import com.meva.finance.exceptionCustom.custons.ValidException;
import com.meva.finance.repository.CategoryRepository;
import com.meva.finance.repository.SubCategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {


    public static final String DESCRIPTION_CATEGORY = "Alimentos";
    public static final String BAR_DO_GLEISON = "Bar do gleison";
    public static final int ID = 1;
    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private SubCategoryRepository subCategoryRepository;


    private CategoryRequest categoryRequest;
    private SubCategoryRequest subCategoryRequest;
    private Optional<Category> categoryOpt;
    private Optional<SubCategory> subCategoryOpt;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        startResources();
    }

    @Test
    void testSaveCategory() {

        Category category = categoryRequest.convert(new Category());

        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        Category categorys = categoryService.saveCategory(categoryRequest);

        Assertions.assertNotNull(category);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    void testThrowExceptionSaveSubCategory() throws ValidException {

        Mockito.when(categoryRepository.findById(categoryRequest.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ValidException.class, () -> {
            categoryService.saveSubCategory(categoryRequest.getId(), subCategoryRequest);
        });
    }


    @Test
    void testSaveSubCategory() {
        Mockito.when(categoryRepository.findById(categoryRequest.getId())).thenReturn(categoryOpt);

        SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
        subCategory.setCategory(categoryOpt.get());
        Mockito.when(subCategoryRepository.save(Mockito.any(SubCategory.class))).thenReturn(subCategory);

        SubCategory subCategory1 = categoryService.saveSubCategory(categoryRequest.getId(), subCategoryRequest);

        Assertions.assertNotNull(subCategory1);
    }


    @Test
    void testListCategory() {
        Category category = categoryRequest.convert(new Category());

        List<Category> categories = new ArrayList<>();

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.findAllCategory();

        Assertions.assertEquals(categories, result);
    }


    @Test
    void testSuccessDeleteCategory() {
        Mockito.when(categoryRepository.findById(categoryRequest.getId())).thenReturn(categoryOpt);
        Category category = categoryRequest.convert(new Category());

        categoryService.deleteCategory(categoryRequest.getId());

        Mockito.verify(categoryRepository).delete(category);
    }

    @Test
    void testThrowExceptionDeleteCategory() throws ValidException {
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(ValidException.class, () -> {
            categoryService.deleteCategory(ID);
        });
    }


    @Test
    void testSuccessDeleteSubCategory() {
        Mockito.when(subCategoryRepository.findById(ID)).thenReturn(subCategoryOpt);

        categoryService.deleteSubCategory(ID);

        Mockito.verify(subCategoryRepository).delete(subCategoryOpt.get());
    }

    @Test
    void testThrowExceptionDeleteSubCategory() {
        Mockito.when(subCategoryRepository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(ValidException.class, () -> {
            categoryService.deleteSubCategory(ID);
        });
    }


    private void startResources() {
        categoryRequest = new CategoryRequest();
        categoryRequest.setId(ID);
        categoryRequest.setDescription(DESCRIPTION_CATEGORY);


        subCategoryRequest = new SubCategoryRequest();
        subCategoryRequest.setId(ID);
        subCategoryRequest.setDescription(BAR_DO_GLEISON);

        categoryOpt = Optional.of(new Category(ID, DESCRIPTION_CATEGORY));

        subCategoryOpt = Optional.of(new SubCategory(ID, BAR_DO_GLEISON));

    }

}