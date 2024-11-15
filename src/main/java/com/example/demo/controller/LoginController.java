package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login") // "/login" 경로로 POST 요청을 처리하는 메서드
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String username = loginRequest.getUsername(); // 로그인 요청에서 사용자 이름을 가져옴
            String password = loginRequest.getPassword(); // 로그인 요청에서 비밀번호를 가져옴

            // 사용자 이름과 비밀번호로 인증을 시도
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            // 인증이 성공하면 SecurityContext에 인증 정보를 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 로그인 성공 메시지를 담은 응답 맵을 생성하여 반환
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
//            System.out.println(response);
            return response;
        } catch (AuthenticationException e) {
            // 인증 실패 시 예외를 발생시킴
            throw new RuntimeException("Invalid username or password");
        }
    }
}
