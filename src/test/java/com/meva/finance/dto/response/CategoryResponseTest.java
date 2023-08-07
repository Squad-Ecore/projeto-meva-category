package com.meva.finance.dto.response;

import com.meva.finance.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryResponseTest {

    private CategoryResponse categoryResponse;
    private Category category;

    @BeforeEach
    public void beforeEach() {
        startCategoryResponse();
    }

    @Test
    void testCategoryResponse() {
        CategoryResponse response = new CategoryResponse(category);

        assertEquals(response.getId(), category.getId());
        assertEquals(response.getDescription(), category.getDescription());
    }

    @Test
    void testHashCode() {
        int hashCode1 = categoryResponse.hashCode();
        int hashCodeCategory = category.hashCode();

        assertNotEquals(hashCode1, hashCodeCategory);
    }

    @Test
    void testToString() {
        String toStringEsperada = "CategoryResponse{id=1, description='Alimentos'}";
        String toStringAtual = categoryResponse.toString();

        assertEquals(toStringEsperada, toStringAtual);
    }

    @Test
    void checkCategoryResponse() {
        CategoryResponse response = new CategoryResponse();
        response.setId(1);
        response.setDescription("Alimentos");

        assertEquals(response.getId(), categoryResponse.getId());
        assertEquals(response.getDescription(), categoryResponse.getDescription());
        assertTrue(response.canEqual(categoryResponse));
    }

    private void startCategoryResponse() {
        categoryResponse = new CategoryResponse();
        categoryResponse.setId(1);
        categoryResponse.setDescription("Alimentos");

        category = new Category(1, "Alimentos");

    }

}