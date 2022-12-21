package com.productdemo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDataDTO {

    private String userId;
    private String userName;
    private String role;
    private String password;
}
