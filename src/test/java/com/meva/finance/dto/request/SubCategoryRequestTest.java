package com.meva.finance.dto.request;

import com.meva.finance.entity.SubCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubCategoryRequestTest {

    private SubCategory subCategory;
    private SubCategoryRequest subCategoryRequest;


    @BeforeEach
    public void beforeEach() {
        startSubCategory();
    }

    @Test
    void testCheckSubCategory() {
        subCategory = subCategoryRequest.convert(new SubCategory());

        assertNotNull(subCategory);
        assertEquals(subCategory.getId(), subCategoryRequest.getId());
        assertEquals(subCategory.getDescription(), subCategoryRequest.getDescription());
    }

    @Test
    void testToStringSubCategoryRequest(){
        String toStringEsperada = "SubCategoryRequest{id=1, description='Alimentos'}";

        assertEquals(subCategoryRequest.toString(), toStringEsperada);
    }


    @Test
    void checkToString() {
        subCategory = subCategoryRequest.convert(new SubCategory());
        String toStringEsperada = "SubCategory{id=1, description='Alimentos'}";

        assertEquals(toStringEsperada, subCategory.toString());

        assertTrue(subCategoryRequest.canEqual(subCategoryRequest));
    }


    @Test
    void checkHashCode() {
        SubCategoryRequest subCategoryRequest1 = new SubCategoryRequest();
        subCategoryRequest1.setId(1);
        subCategoryRequest1.setDescription("Jogos");

        SubCategoryRequest subCategoryRequest2 = new SubCategoryRequest();
        subCategoryRequest2.setId(2);
        subCategoryRequest2.setDescription("Alimentos");


        int hashCode = subCategoryRequest1.hashCode();

        int hashCode1 = subCategoryRequest2.hashCode();

        assertNotEquals(hashCode1, hashCode);
    }


    private void startSubCategory() {
        subCategoryRequest = new SubCategoryRequest(1, "Alimentos");
    }


}