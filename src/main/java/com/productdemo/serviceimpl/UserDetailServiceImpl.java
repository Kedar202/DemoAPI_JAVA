package com.productdemo.serviceimpl;

import com.productdemo.entity.MemberData;
import com.productdemo.repository.MemberDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customUserService")
public class UserDetailServiceImpl implements UserDetailsService {

    public static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private MemberDataRepository memberDataRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("inside loadUserByUsername method");
        Optional<MemberData> entity = memberDataRepository.findOneByEmailIgnoreCase(username);
        MemberData memberData = null;
        if (entity.isPresent()) {
            memberData = entity.get();
        }
        if (memberData == null) {
            throw new UsernameNotFoundException("", new Throwable("Invalid Creds"));
        }
        UserDetails user = User.withUsername(memberData.getUserId()).password(memberData.getPassword())
                .authorities("EMPLOYEE", "ADMIN").build();
        return user;
    }
}