package com.productdemo.controller;

import com.productdemo.dto.ProductCategoryDTO;
import com.productdemo.entity.ProductCategory;
import com.productdemo.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ProductCategoryController {
    final ProductCategoryService productCategoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestParam("userId") String userId, @RequestBody ProductCategoryDTO productCategoryDTO) {
        return new ResponseEntity<>(productCategoryService.addCategory(userId, productCategoryDTO), HttpStatus.OK);
    }


    @GetMapping("/getCategoryList")
    public ResponseEntity<?> getCategoryList() {
        return new ResponseEntity<>(productCategoryService.getCategoryList(),HttpStatus.OK);
    }
}
