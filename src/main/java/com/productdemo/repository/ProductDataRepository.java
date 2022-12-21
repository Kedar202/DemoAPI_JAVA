package com.productdemo.repository;

import com.productdemo.entity.ProductData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, Long> {

    @Query(value = "SELECT * FROM productdb.product_data order by id asc",nativeQuery = true)
    Page<ProductData> findRecords(Pageable pageable);
}
