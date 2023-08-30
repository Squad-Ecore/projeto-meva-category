package com.meva.finance.api;

import com.meva.finance.dto.request.CategoryDto;
import com.meva.finance.dto.request.SubCategoryDto;
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

import java.util.ArrayList;
import java.util.List;
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


    private CategoryDto categoryRequest;
    private SubCategoryDto subCategoryRequest;
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
        Mockito.when(categoryService.saveSubCategory(subCategoryRequest)).thenReturn(subCategory);

        ResponseEntity<SubCategory> response = controller.saveSubCategory(subCategoryRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    void testListCategory() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());

        Mockito.when(categoryService.buscaTodasCategory()).thenReturn(categoryList);

        List<Category> response = controller.buscaTodasCategory();

        Assertions.assertEquals(categoryList, response);
    }

    @Test
    void sucessoNaBuscaIdCategoryPelaDescription() {
        subCategoryOpt.get().setCategory(categoryOpt.get());

        Mockito.when(categoryService.buscaCategoryIdNaDescription(Mockito.anyString())).thenReturn(subCategoryOpt.get().getCategory().getId());

        ResponseEntity<?> idCategory = controller.buscaDescriptionSubCategory(subCategoryOpt.get().getDescription());

        Assertions.assertNotNull(idCategory);
        Assertions.assertEquals(idCategory.getStatusCode(), HttpStatus.OK);
    }


    private void startResources() {
        categoryRequest = new CategoryDto();
        categoryRequest.setId(ID);
        categoryRequest.setDescription(ALIMENTOS);

        subCategoryRequest = new SubCategoryDto();
        subCategoryRequest.setId(ID);
        subCategoryRequest.setDescription(BAR_DO_GERSON);

        categoryOpt = Optional.of(new Category(ID, ALIMENTOS));

        subCategoryOpt = Optional.of(new SubCategory(ID, BAR_DO_GERSON));


    }

}