package com.meva.finance.service;

import com.meva.finance.dto.request.CategoryDto;
import com.meva.finance.dto.request.SubCategoryDto;
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
    void testSaveCategory() {

        Category category = categoryRequest.convert(new Category());

        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        Category categorys = categoryService.saveCategory(categoryRequest);

        Assertions.assertNotNull(category);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    void salvaSubCategory() {
        subCategoryRequest.setCategoryRequestId(ID);

        SubCategory subCategory = subCategoryRequest.convert(new SubCategory());
        Integer idCategory = subCategoryRequest.getCategoryRequestId();

        Mockito.when(categoryRepository.findById(idCategory)).thenReturn(categoryOpt);

        Mockito.when(subCategoryRepository.save(Mockito.any())).thenReturn(subCategory);

        SubCategory novaSub = categoryService.saveSubCategory(subCategoryRequest);

        Assertions.assertNotNull(subCategory);
        Assertions.assertEquals(subCategory.getId(), 1);

    }


    @Test
    void validaSubCategoryDescription() {
        subCategoryRequest.setCategoryRequestId(ID);
        Integer idCategory = subCategoryRequest.getCategoryRequestId();

        Mockito.when(categoryRepository.findById(idCategory)).thenReturn(Optional.empty());

        try {
            categoryService.saveSubCategory(subCategoryRequest);
        } catch (Exception ex) {
            Assertions.assertEquals(ex.getMessage(), "Category not found");
        }
    }


    @Test
    void sucessoNaBuscaDoIdCategoryPelaDescriptions() {
        String description = "Lanchonete Gerson";
        subCategoryOpt.get().setCategory(categoryOpt.get());


        Mockito.when(subCategoryRepository.findByDescription(Mockito.anyString())).thenReturn(subCategoryOpt);

        Integer buscaIdCategory = categoryService.buscaCategoryIdNaDescription(description);

        Assertions.assertNotNull(buscaIdCategory);
        Assertions.assertEquals(1, categoryOpt.get().getId());
    }

    @Test
    void retornaNaoCategorizadoIdPelaDescription() {
        Category naoCategorizado = new Category(1, "Nao_Categorizado");
        String description = "Lanchonete Gerson";

        Mockito.when(subCategoryRepository.findByDescription(Mockito.anyString())).thenReturn(Optional.empty());

        Integer buscaIdNaoCategorizado = categoryService.buscaCategoryIdNaDescription(description);

        Assertions.assertNotNull(buscaIdNaoCategorizado);
        Assertions.assertEquals(naoCategorizado.getId(), buscaIdNaoCategorizado);
    }


    private void startResources() {
        categoryRequest = new CategoryDto();
        categoryRequest.setId(ID);
        categoryRequest.setDescription(DESCRIPTION_CATEGORY);


        subCategoryRequest = new SubCategoryDto();
        subCategoryRequest.setId(ID);
        subCategoryRequest.setDescription(BAR_DO_GLEISON);

        categoryOpt = Optional.of(new Category(ID, DESCRIPTION_CATEGORY));

        subCategoryOpt = Optional.of(new SubCategory(ID, BAR_DO_GLEISON));

    }

}