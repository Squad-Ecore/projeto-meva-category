package com.meva.finance.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CategoryTest {

    private Category category;
    private List<SubCategory> subCategories = new ArrayList<>();


    @BeforeEach
    public void beforeEach() {
        startCetegory();
    }

    @Test
    void testCategory() {

        Assertions.assertNotNull(category);
        Assertions.assertEquals(1, category.getId());
        Assertions.assertEquals("Alimentos", category.getDescription());
    }


    @Test
    void testCategoryHashCode() {
        Category category1 = new Category();
        category1.setId(1);
        category1.setDescription("Alimentos");

        int hashCodeCategory = category.hashCode();
        int hashCodeCategory1 = category1.hashCode();

        Assertions.assertTrue(category.equals(category));
        Assertions.assertEquals(hashCodeCategory, hashCodeCategory1);
    }

    @Test
    void testCategoryToString() {
        String toStringEsperada = "Category{id=1, description='Alimentos'}";
        String toStringAtual = category.toString();

        Assertions.assertEquals(toStringEsperada, category.toString());
    }

    @Test
    void testCategoryListSubCategory() {
        Assertions.assertTrue(category.getSubCategories().isEmpty());

        SubCategory subCategory = new SubCategory();
        subCategories.add(subCategory);

        category.setSubCategories(subCategories);
        Assertions.assertEquals(1, category.getSubCategories().size());


    }


    private void startCetegory() {
        category = new Category();
        category.setId(1);
        category.setDescription("Alimentos");

        List<SubCategory> subCategories = new ArrayList<>();

        category.setSubCategories(subCategories);
    }


}