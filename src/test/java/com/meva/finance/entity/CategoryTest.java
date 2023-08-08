package com.meva.finance.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertEquals(1, category.getId());
        assertEquals("Alimentos", category.getDescription());
    }


    @Test
    void testCategoryHashCode() {
        Category category1 = new Category();
        category1.setId(1);
        category1.setDescription("Alimentos");

        int hashCodeCategory = category.hashCode();
        int hashCodeCategory1 = category1.hashCode();

        Assertions.assertTrue(category.equals(category));
        assertEquals(hashCodeCategory, hashCodeCategory1);
    }

    @Test
    void testCategoryToString() {
        String toStringEsperada = "Category{id=1, description='Alimentos'}";
        String toStringAtual = category.toString();

        assertEquals(toStringEsperada, category.toString());
    }

    @Test
    void testCategoryListSubCategory() {
        Assertions.assertTrue(category.getSubCategories().isEmpty());

        SubCategory subCategory = new SubCategory();
        subCategories.add(subCategory);

        category.setSubCategories(subCategories);
        assertEquals(1, category.getSubCategories().size());
    }


    @Test
    void testEquals(){
        // Cenário 1: Teste de igualdade
        Category category1 = new Category(1, "Category A");
        Category category2 = new Category(1, "Category A");
        assertEquals(category1, category2);

        // Cenário 2: Teste de diferença de id
        Category category3 = new Category(1, "Category A");
        Category category4 = new Category(2, "Category A");
        assertNotEquals(category3, category4);

        // Cenário 3: Teste de diferença de description
        Category category5 = new Category(1, "Category A");
        Category category6 = new Category(1, "Category B");
        assertNotEquals(category5, category6);

        // Cenário 4: Teste com outro tipo de objeto
        Category category7 = new Category(1, "Category A");
        String someString = "Not a Category object";
        assertNotEquals(category7, someString);
    }


    private void startCetegory() {
        category = new Category();
        category.setId(1);
        category.setDescription("Alimentos");

        List<SubCategory> subCategories = new ArrayList<>();

        category.setSubCategories(subCategories);
    }


}