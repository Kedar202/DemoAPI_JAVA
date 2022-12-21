package com.productdemo.serviceimpl;

import com.productdemo.dto.MemberDataDTO;
import com.productdemo.dto.ResponseDTO;
import com.productdemo.entity.MemberData;
import com.productdemo.repository.MemberDataRepository;
import com.productdemo.service.AdminService;
import com.productdemo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private MemberDataRepository memberDataRepository;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Override
    public ResponseEntity<?> getLogin(MemberDataDTO memberDataDTO) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> mapResponse = new HashMap<String, Object>();
        Map<String, Object> mapResponseUserData = new HashMap<String, Object>();
        try {
            authenticate(memberDataDTO.getUserId(), memberDataDTO.getPassword());

            final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(memberDataDTO.getUserId());
            final String token = jwtTokenUtil.generateToken(userDetails);
            mapResponse.put("token", token);
            Optional<MemberData> memberData = memberDataRepository.findOneByEmailIgnoreCase(userDetails.getUsername());

            if (memberData.isPresent()) {
                MemberData memberData1 = memberData.get();
                mapResponseUserData.put("id", memberData1.getId().toString());
                mapResponseUserData.put("userId", memberData1.getUserId());
                mapResponseUserData.put("role", memberData1.getRole());
                mapResponseUserData.put("Username", memberData1.getUserName());
                mapResponseUserData.put("token", token);
                map.put("status", "200");
                map.put("message", "login successFully");
                map.put("data", mapResponseUserData);

            }
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("INVALID_CREDENTIALS")) {
                map.put("RESPONSE_STATUS", "400");
                map.put("RESPONSE_MESSAGE", "LOGIN_INVALID_CREDENTIALS");
                map.put("RESPONSE_DATA", new ArrayList<>());
            }
        }
        map.put("RESPONSE_STATUS", "400");
        map.put("RESPONSE_MESSAGE", "LOGIN_INVALID_CREDENTIALS");
        map.put("RESPONSE_DATA", new ArrayList<>());
        return ResponseEntity.ok(map);
    }


    private void authenticate(String name, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("LOGIN_INVALID_CREDENTIALS", e);
        }

    }
}
