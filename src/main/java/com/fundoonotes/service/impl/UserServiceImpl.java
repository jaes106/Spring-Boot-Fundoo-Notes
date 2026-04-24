package com.fundoonotes.service.impl;

import com.fundoonotes.dto.request.*;
import com.fundoonotes.dto.response.*;
import com.fundoonotes.entity.User;
import com.fundoonotes.exception.*;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.service.UserService;
import com.fundoonotes.util.TokenUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    public UserServiceImpl(UserRepository userRepository, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public UserResponseDto register(UserRegisterRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User saved = userRepository.save(user);
        return new UserResponseDto(saved.getId(), saved.getFirstName(), saved.getEmail());
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = tokenUtil.generateToken(user.getId());
        return new LoginResponseDto(token, "Login successful");
    }
}