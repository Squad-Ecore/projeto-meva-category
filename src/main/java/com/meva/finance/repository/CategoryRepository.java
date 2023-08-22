package com.meva.finance.repository;

import com.meva.finance.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @EntityGraph(attributePaths = "subCategories")
    List<Category> findAll();


//    Category buscaDescriptionCategory();

}
