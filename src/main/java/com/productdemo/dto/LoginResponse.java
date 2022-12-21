package com.productdemo.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String userId;
    private String userName;
    private String role;
    private String token;
}
