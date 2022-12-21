package com.productdemo.controller;

import com.productdemo.dto.ProductCategoryDTO;
import com.productdemo.dto.ProductDataDTO;
import com.productdemo.service.ProductDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ProductDataController {

    final ProductDataService productDataService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProductData(@RequestParam("userId") String userId, @RequestBody ProductDataDTO productDataDTO) {
        return new ResponseEntity<>(productDataService.addProductData(userId, productDataDTO), HttpStatus.OK);
    }

    @GetMapping("/productList")
    public ResponseEntity<?> productList(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {
        return new ResponseEntity<>(productDataService.productList(pageSize, pageNumber), HttpStatus.OK);
    }

}
