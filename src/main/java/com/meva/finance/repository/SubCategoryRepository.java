package com.meva.finance.repository;

import com.meva.finance.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    SubCategory findByDescription(String description);

}
