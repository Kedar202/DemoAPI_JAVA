package com.productdemo.repository;

import com.productdemo.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {


    @Query("SELECT u.categoryName from ProductCategory u where u.categoryName= :categoryName")
    Optional<String> findByCategoryName(String categoryName);
}
