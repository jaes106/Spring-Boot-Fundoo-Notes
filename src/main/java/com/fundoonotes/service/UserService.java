package com.fundoonotes.service;

import com.fundoonotes.dto.request.*;
import com.fundoonotes.dto.response.*;

public interface UserService {
    UserResponseDto register(UserRegisterRequestDto dto);
    LoginResponseDto login(LoginRequestDto dto);
}