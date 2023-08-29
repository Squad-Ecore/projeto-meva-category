package com.meva.finance.dto.request;

import com.meva.finance.entity.SubCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubCategoryRequestTest {

    private SubCategory subCategory;
    private SubCategoryDto subCategoryRequest;


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
        SubCategoryDto subCategoryRequest1 = new SubCategoryDto();
        subCategoryRequest1.setId(1);
        subCategoryRequest1.setDescription("Jogos");

        SubCategoryDto subCategoryRequest2 = new SubCategoryDto();
        subCategoryRequest2.setId(2);
        subCategoryRequest2.setDescription("Alimentos");


        int hashCode = subCategoryRequest1.hashCode();

        int hashCode1 = subCategoryRequest2.hashCode();

        assertNotEquals(hashCode1, hashCode);
    }


    private void startSubCategory() {
        subCategoryRequest = new SubCategoryDto(1, "Alimentos");
    }


}