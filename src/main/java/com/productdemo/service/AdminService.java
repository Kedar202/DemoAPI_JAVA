package com.productdemo.service;

import com.productdemo.dto.MemberDataDTO;
import com.productdemo.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<?> getLogin(MemberDataDTO memberDataDTO);
}
