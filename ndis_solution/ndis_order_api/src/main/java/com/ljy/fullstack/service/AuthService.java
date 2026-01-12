package com.ljy.fullstack.service;

import com.ljy.fullstack.pojo.User;
import com.ljy.fullstack.pojo.dto.LoginRequestDto;
import com.ljy.fullstack.pojo.dto.LoginResponseDto;
import com.ljy.fullstack.pojo.dto.RegisterRequestDto;
import com.ljy.fullstack.pojo.exception.InvalidCredentialsException;
import com.ljy.fullstack.pojo.exception.ResourceNotFoundException;
import com.ljy.fullstack.pojo.jwt.JwtUtils;
import com.ljy.fullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect credentials");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return new LoginResponseDto(token);
    }

    public boolean register(RegisterRequestDto registerRequestDto) {
        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setUserName(registerRequestDto.getEmail());
        user.setFullName(registerRequestDto.getFullName());
        user.setRole(User.Role.CUSTOMER);

        userRepository.save(user);
        return true;
    }
}
