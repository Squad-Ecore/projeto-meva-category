package com.meva.finance.repository;

import com.meva.finance.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    Optional<SubCategory> findByDescription(String description);


    @Query("SELECT s FROM SubCategory s WHERE LOWER(s.description) LIKE %:likePattern%")
    Optional<SubCategory> findByNameLike(@Param("likePattern") String likePattern);



}
