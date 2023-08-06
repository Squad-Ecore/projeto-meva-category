package com.meva.finance.api;

import com.meva.finance.dto.request.CategoryRequest;
import com.meva.finance.dto.request.SubCategoryRequest;
import com.meva.finance.dto.response.CategoryResponse;
import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import com.meva.finance.exceptionCustom.custons.ValidException;
import com.meva.finance.repository.CategoryRepository;
import com.meva.finance.repository.SubCategoryRepository;
import com.meva.finance.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ControllerTest {


    public static final String ALIMENTOS = "Alimentos";
    public static final int ID = 1;
    public static final String BAR_DO_GERSON = "Bar do Gerson";
    @InjectMocks
    private Controller controller;
    @Mock
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
    void testPOSTsaveSuccessCategory() {

        Category category = categoryRequest.convert(new Category());
        Mockito.when(categoryService.saveCategory(categoryRequest)).thenReturn(category);

        ResponseEntity<CategoryResponse> response = controller.save(categoryRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void testSaveSuccessSubCategory() {

        SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
        Mockito.when(categoryService.saveSubCategory(ID, subCategoryRequest)).thenReturn(subCategory);

        ResponseEntity<SubCategory> response = controller.saveSubCategory(ID, subCategoryRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testThrowExceptionSubCategory() {
        Mockito.when(categoryService.saveSubCategory(0, subCategoryRequest)).thenThrow(new ValidException("NAO_CATEGORIZADO"));

        try {
            controller.saveSubCategory(0, subCategoryRequest);
            Assertions.fail("teste ..");
        } catch (Exception ex) {
            Assertions.assertEquals(ex.getMessage(), "NAO_CATEGORIZADO");
        }
    }

    @Test
    void testListCategory(){



    }


    private void startResources() {
        categoryRequest = new CategoryRequest();
        categoryRequest.setId(ID);
        categoryRequest.setDescription(ALIMENTOS);

        subCategoryRequest = new SubCategoryRequest();
        subCategoryRequest.setId(ID);
        subCategoryRequest.setDescription(BAR_DO_GERSON);

        categoryOpt = Optional.of(new Category(ID, ALIMENTOS));

        subCategoryOpt = Optional.of(new SubCategory(ID, BAR_DO_GERSON));


    }

}