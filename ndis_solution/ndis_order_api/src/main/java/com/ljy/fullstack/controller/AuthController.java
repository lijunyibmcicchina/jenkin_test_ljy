package com.ljy.fullstack.controller;

import com.ljy.fullstack.pojo.dto.LoginRequestDto;
import com.ljy.fullstack.pojo.dto.LoginResponseDto;
import com.ljy.fullstack.pojo.dto.RegisterRequestDto;
import com.ljy.fullstack.pojo.http.ResponseMessage;
import com.ljy.fullstack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<String>> register(@RequestBody RegisterRequestDto registerRequestDto) {
        boolean success = authService.register(registerRequestDto);

        if (success) {
            ResponseMessage<String> responseMessage = ResponseMessage.success("Registration successful");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.error(400, "Registration failed"));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(loginResponseDto));
    }
}
