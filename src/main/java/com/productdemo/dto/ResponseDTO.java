package com.productdemo.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private Long id;
    private Object object;
}
