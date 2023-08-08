package com.meva.finance.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubCategoryTest {

    @Test
    void testEquals() {
        SubCategory subCategory3 = new SubCategory(1, "SubCategory A");
        SubCategory subCategory4 = new SubCategory(2, "SubCategory A");
        assertNotEquals(subCategory3, subCategory4);
    }

    @Test
    void testHashCode() {
        Category category = new Category(1, "Category A");

        SubCategory subCategory1 = new SubCategory(1, "SubCategory A");
        subCategory1.setCategory(category);

        SubCategory subCategory2 = new SubCategory(1, "SubCategory A");
        subCategory2.setCategory(category);

        assertEquals(subCategory1, subCategory2);
        assertEquals(subCategory1.hashCode(), subCategory2.hashCode());
    }

    @Test
    void testCanEquals(){
        SubCategory subCategory1 = new SubCategory(1, "SubCategory A");
        SubCategory subCategory2 = new SubCategory(1, "SubCategory A");

        assertTrue(subCategory1.canEqual(subCategory2));
    }

}