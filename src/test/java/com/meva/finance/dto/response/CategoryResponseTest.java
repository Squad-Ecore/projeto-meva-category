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

    @Test
    void testEquals() {
        // Cenário 1: Teste de igualdade
        CategoryResponse response1 = new CategoryResponse();
        response1.setId(1);
        response1.setDescription("Category A");

        CategoryResponse response2 = new CategoryResponse();
        response2.setId(1);
        response2.setDescription("Category A");
        assertEquals(response1, response2);

        // Cenário 2: Teste de diferença de id
        CategoryResponse response3 = new CategoryResponse();
        response3.setId(1);
        response3.setDescription("Category A");

        CategoryResponse response4 = new CategoryResponse();
        response4.setId(2);
        response4.setDescription("Category A");

        assertNotEquals(response3, response4);
    }


    private void startCategoryResponse() {
        categoryResponse = new CategoryResponse();
        categoryResponse.setId(1);
        categoryResponse.setDescription("Alimentos");

        category = new Category(1, "Alimentos");

    }

}