package com.productdemo.service;

import com.productdemo.dto.ProductDataDTO;
import com.productdemo.dto.Response;

public interface ProductDataService {

    Response addProductData(String userId, ProductDataDTO productDataDTO);

    Response productList(Integer pageSize,Integer pageNumber);
}
