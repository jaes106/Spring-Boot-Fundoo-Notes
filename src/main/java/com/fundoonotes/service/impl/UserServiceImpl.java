package com.fundoonotes.service.impl;

import com.fundoonotes.dto.request.LoginRequestDto;
import com.fundoonotes.dto.request.UserRegisterRequestDto;
import com.fundoonotes.dto.response.LoginResponseDto;
import com.fundoonotes.dto.response.UserResponseDto;
import com.fundoonotes.entity.User;
import com.fundoonotes.exception.InvalidCredentialsException;
import com.fundoonotes.exception.UserAlreadyExistsException;
import com.fundoonotes.exception.UserNotFoundException;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.service.UserService;
import com.fundoonotes.util.MessageProducer;
import com.fundoonotes.util.TokenUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;
    private final MessageProducer producer;

    public UserServiceImpl(UserRepository userRepository,
                           TokenUtil tokenUtil,
                           MessageProducer producer) {
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
        this.producer = producer;
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
        producer.send("Welcome " + saved.getEmail());
        producer.send("User registered: " + saved.getEmail());

        return new UserResponseDto(
                saved.getId(),
                saved.getFirstName(),
                saved.getEmail()
        );
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