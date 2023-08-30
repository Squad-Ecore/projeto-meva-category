//package com.meva.finance.dto.request;
//
//import com.meva.finance.entity.Category;
//import com.meva.finance.entity.SubCategory;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CategoryRequestTest {

//

//
//    //
//    @Test
//    void testEqualsAndHashCode() {
//        CategoryDto categoryRequest1 = new CategoryDto(1, "Category A");
//        CategoryDto categoryRequest2 = new CategoryDto(1, "Category A");
//
//        assertEquals(categoryRequest1, categoryRequest2);
//        assertEquals(categoryRequest1.hashCode(), categoryRequest2.hashCode());
//    }
//
//    @Test
//    void testNotEquals(){
//        CategoryDto categoryRequest3 = new CategoryDto(1, "Category A");
//        CategoryDto categoryRequest4 = new CategoryDto(2, "Category A");
//        assertNotEquals(categoryRequest3, categoryRequest4);
//
//        CategoryDto categoryRequest5 = new CategoryDto(1, "Category A");
//        CategoryDto categoryRequest6 = new CategoryDto(1, "Category B");
//        assertNotEquals(categoryRequest5, categoryRequest6);
//
//    }
//
//
//    private void startCategoryRequest() {
//        categoryRequest = new CategoryDto();
//        categoryRequest.setId(1);
//        categoryRequest.setDescription("Alimentos");
//    }
//
//}