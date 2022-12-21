package com.productdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private String status;

    private String message;

    private Object response;

    public Response() {
    }
}
