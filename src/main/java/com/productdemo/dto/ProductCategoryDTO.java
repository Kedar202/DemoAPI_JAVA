package com.productdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDTO
{
    private String categoryName;
    private String description;
}
