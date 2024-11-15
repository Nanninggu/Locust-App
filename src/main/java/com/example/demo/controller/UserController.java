package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.util.DateUtil;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users") // 이 컨트롤러의 기본 URL 경로를 설정합니다. localhost:8080/users
public class UserController {

    // UserService를 주입받아 사용 (주입 받아 쓴다는 의미는 가져다 쓴다는 의미로 이해하면 됨)
    private final UserService userService;

    // UserController 생성자, UserService를 주입받아 초기화합니다.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 모든 사용자 정보 조회 (리턴은 호출한 곳으로 보내는 것)
    @GetMapping
    public List<User> getAllUsers() {
        // UserService를 통해 모든 사용자 정보를 가져옵니다.
        return userService.getAllUsers();
    }

    // 사용자 생성
    @PostMapping("/create")
    public User createUser(@Parameter(description = "사용자 이름", example = "John Doe") @RequestParam(defaultValue = "John Doe") String name,
                           @Parameter(description = "사용자 이메일", example = "john.doe@example.com") @RequestParam(defaultValue = "john.doe@example.com") String email) {
        // UserService를 통해 새로운 사용자를 생성합니다.
        return userService.createUser(name, email);
    }

    // 사용자 정보 수정
    // 리소스의 고유 식별자를 나타낼 때 주로 사용됩니다. 필터링, 검색, 페이징 등의 추가적인 정보를 전달할 때 주로 사용됩니다.
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @Parameter(description = "사용자 이름", example = "Jane Doe") @RequestParam String name,
                           @Parameter(description = "사용자 이메일", example = "jane.doe@example.com") @RequestParam String email) {
        // UserService를 통해 사용자의 정보를 업데이트합니다.
        return userService.updateUser(id, name, email);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // UserService를 통해 사용자를 삭제합니다.
        userService.deleteUser(id);
    }

    // 강제로 예외를 발생시키는 메서드
    @GetMapping("/forceException")
    public String forceException() {
        // 강제로 UserNotFoundException 예외를 발생시킵니다.
        throw new UserNotFoundException("This is a forced exception for testing purposes.");
    }

    // 강제로 전역 예외를 발생시키는 메서드
    @GetMapping("/forceGlobalException")
    public String forceGlobalException() {
        // 강제로 RuntimeException 예외를 발생시킵니다.
        throw new RuntimeException("테스트를 위한 강제 글로벌 예외입니다.");
    }

    // 현재 날짜를 반환하는 API
    @GetMapping("/currentDate")
    public LocalDate getCurrentDate() {
        return DateUtil.getCurrentDate();
    }
}
