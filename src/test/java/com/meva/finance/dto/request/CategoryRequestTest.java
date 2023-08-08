package com.meva.finance.dto.request;

import com.meva.finance.entity.Category;
import com.meva.finance.entity.SubCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRequestTest {

    private CategoryRequest categoryRequest;
    private Category category;

    @BeforeEach
    public void beforeEach() {
        startCategoryRequest();
    }


    @Test
    void convertCategory() {
        category = categoryRequest.convert(new Category());
        Assertions.assertNotNull(category);

        Assertions.assertEquals(category.getId(), categoryRequest.getId());
        Assertions.assertEquals(category.getDescription(), categoryRequest.getDescription());

        assertTrue(categoryRequest.canEqual(categoryRequest));
    }

    @Test
    void testCategoryRequestToString() {
        String toStringEsperada = "CategoryRequest{id=1, description='Alimentos'}";
        String toStringAtual = categoryRequest.toString();

        assertEquals(toStringEsperada, categoryRequest.toString());
    }

    @Test
    void testComparacao() {
        CategoryRequest categoryRequest1 = new CategoryRequest();
        categoryRequest1.setId(2);
        categoryRequest1.setDescription("Jogos");


        assertFalse(categoryRequest.equals(categoryRequest1));
    }

    @Test
    void testSubCategoryList() {
        List<SubCategoryRequest> list1 = new ArrayList<>();
        list1.add(new SubCategoryRequest(1, "Alimentos"));
        list1.add(new SubCategoryRequest(2, "Jogos"));

        List<SubCategoryRequest> list2 = new ArrayList<>();
        list2.add(new SubCategoryRequest(3, "Lojas"));
        list2.add(new SubCategoryRequest(4, "Moveis"));

        int hashCodeList1 = list1.hashCode();
        int hashCodeList2 = list2.hashCode();

        assertNotEquals(hashCodeList1, hashCodeList2);
    }

    @Test
    void checkConstructor() {
        Category category1 = new Category(1, "Alimentos");
        SubCategory subCategory = new SubCategory(1, "BarGerson");
        subCategory.setCategory(category);

        category1 = categoryRequest.convert(new Category());

        assertEquals(category1.getDescription(), categoryRequest.getDescription());
        assertTrue(category1.equals(category1));

    }

    @Test
    void testEqualsAndHashCode() {
        CategoryRequest categoryRequest1 = new CategoryRequest(1, "Category A");
        CategoryRequest categoryRequest2 = new CategoryRequest(1, "Category A");

        assertEquals(categoryRequest1, categoryRequest2);
        assertEquals(categoryRequest1.hashCode(), categoryRequest2.hashCode());
    }

    @Test
    void testNotEquals(){
        CategoryRequest categoryRequest3 = new CategoryRequest(1, "Category A");
        CategoryRequest categoryRequest4 = new CategoryRequest(2, "Category A");
        assertNotEquals(categoryRequest3, categoryRequest4);

        CategoryRequest categoryRequest5 = new CategoryRequest(1, "Category A");
        CategoryRequest categoryRequest6 = new CategoryRequest(1, "Category B");
        assertNotEquals(categoryRequest5, categoryRequest6);

    }


    private void startCategoryRequest() {
        categoryRequest = new CategoryRequest();
        categoryRequest.setId(1);
        categoryRequest.setDescription("Alimentos");
    }

}