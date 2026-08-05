package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // 这里硬编码一个用户（后续可以改成从数据库查询）
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 模拟数据库中的用户（admin / 123456）
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 密码加密后存储（实际项目中应从数据库读取）
        String encodedPassword = new BCryptPasswordEncoder().encode("123456");
        return new User(username, encodedPassword, new ArrayList<>());
    }
}