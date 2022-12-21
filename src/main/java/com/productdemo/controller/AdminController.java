package com.productdemo.controller;

import com.productdemo.dto.MemberDataDTO;
import com.productdemo.dto.ResponseDTO;
import com.productdemo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "https://32ee-2405-201-2022-c00f-61f9-6388-aba2-33f7.in.ngrok.io")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody MemberDataDTO memberDataDTO) {
        ResponseEntity<?> responseDTO = adminService.getLogin(memberDataDTO);
        return responseDTO;
    }

}
