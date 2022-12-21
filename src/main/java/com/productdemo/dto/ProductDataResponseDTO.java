package com.productdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDataResponseDTO {
    private String productName;
    private Double productMRP;
    private Double productPrice;
    private String productTargetGRP;
    private String productDescription;
    private Character currentlyAvailable;
    private String categoryName;
}
