package com.productdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDataDTO {
    private Long productCode;
    private String productName;
    private Double productMRP;
    private Double productPrice;
    private Long productCategoryId;
    private String productTargetGRP;
    private String productDescription;
    private Character currentlyAvailable;
}
