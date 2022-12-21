package com.productdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductResponseDTO {

    private Integer count;
    private List<ProductDataResponseDTO> productList=new ArrayList<>();
}
