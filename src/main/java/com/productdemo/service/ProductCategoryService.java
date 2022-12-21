package com.productdemo.service;

import com.productdemo.dto.ProductCategoryDTO;
import com.productdemo.dto.Response;

public interface ProductCategoryService {

    Response addCategory(String userId, ProductCategoryDTO productCategoryDTO);

    Response getCategoryList();
}
