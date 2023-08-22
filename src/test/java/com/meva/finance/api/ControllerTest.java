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
        Mockito.when(categoryService.saveSubCategory(subCategoryRequest)).thenReturn(subCategory);

        ResponseEntity<SubCategory> response = controller.saveSubCategory(subCategoryRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

//    @Test
//    void testThrowExceptionSubCategory() {
//        Mockito.when(categoryService.saveSubCategory(0, subCategoryRequest)).thenThrow(new ValidException("NAO_CATEGORIZADO"));
//
//        try {
//            controller.saveSubCategory(0, subCategoryRequest);
//            Assertions.fail("teste fail");
//        } catch (Exception ex) {
//            Assertions.assertEquals(ex.getMessage(), "NAO_CATEGORIZADO");
//        }
//    }

    @Test
    void testListCategory() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());

        Mockito.when(categoryService.buscaTodasCategory()).thenReturn(categoryList);

        List<Category> response = controller.buscaTodasCategory();

        Assertions.assertEquals(categoryList, response);
    }


    @Test
    void testSuccessDescriptionCategoryInSubCategory() {
        Category category = new Category(1, "Alimentos");
        SubCategory subCategory = new SubCategory(1, "lanchoneteGerson");
        subCategory.setCategory(category);

        Mockito.when(categoryService.buscaIdCategoryNaDescriptionSubCategory(subCategory.getDescription())).thenReturn(subCategory.getCategory().getDescription());

        ResponseEntity<String> resultDescription = controller.buscaDescriptionSubCategory(subCategory.getDescription());

        Assertions.assertNotNull(resultDescription);
        Assertions.assertEquals("Category: " + category.getDescription(), resultDescription.getBody());
        Assertions.assertEquals(resultDescription.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testCheckDescriptionSubCategoryThrownException() {
        Category category = new Category(1, "Alimentos");
        SubCategory subCategory = new SubCategory(1, "lanchoneteGerson");
        subCategory.setCategory(category);

        Mockito.when(categoryService.buscaIdCategoryNaDescriptionSubCategory(Mockito.anyString())).thenThrow(new ValidException("NAO_CATEGORIZADO"));

        Assertions.assertThrows(ValidException.class, () -> {
            controller.buscaDescriptionSubCategory(Mockito.anyString());
        });
    }

    @Test
    void testSuccessDeleteCategory() {
        Mockito.when(categoryService.deleteCategory(ID)).thenReturn(ResponseEntity.ok("Category Deletada"));

        ResponseEntity<String> result = controller.deleteCategory(ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(result.getBody(), "Category Deletada");
    }

    @Test
    void testCheckThrowExceptionCategoryNotFound() {
        Mockito.when(categoryService.deleteCategory(Mockito.anyInt())).thenThrow(new ValidException("NAO_CATEGORIZADO"));

        Assertions.assertThrows(ValidException.class, () -> {
            controller.deleteCategory(Mockito.anyInt());
        });
    }

    @Test
    void testSuccessDeleteSubCategory() {
        Mockito.when(categoryService.deleteSubCategory(ID)).thenReturn(ResponseEntity.ok("SubCategory Deletada"));

        ResponseEntity<String> result = controller.deleteSubCategory(ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(result.getBody(), "SubCategory Deletada");
    }

    @Test
    void testUpdateCategorySuccess() {
        Mockito.when(categoryService.updateCategory(categoryRequest)).thenReturn(categoryOpt.get());

        ResponseEntity<CategoryResponse> response = controller.updateCategory(categoryRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testChechCategoryThorwException() {
        Mockito.when(categoryService.updateCategory(Mockito.any())).thenThrow(new ValidException("Category Not Found"));

        Assertions.assertThrows(ValidException.class, () -> {
            controller.updateCategory(categoryRequest);
        });
    }

    @Test
    void testSuccessUpdateSubCategory() {
        Mockito.when(categoryService.updateSubCategory(ID, subCategoryRequest)).thenReturn(subCategoryOpt.get());

        ResponseEntity<String> response = controller.updateSubCategory(ID, subCategoryRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getBody(), "SubCategory update");
    }

    @Test
    void testCheckUpdateSubCategoryThrowException() {
        Mockito.when(categoryService.updateSubCategory(null, subCategoryRequest)).thenThrow(new ValidException("SubCategory Not Found"));

        Assertions.assertThrows(ValidException.class, () -> {
            controller.updateSubCategory(null, subCategoryRequest);
        });
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