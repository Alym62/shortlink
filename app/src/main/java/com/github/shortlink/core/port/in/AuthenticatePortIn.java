package com.github.shortlink.core.port.in;

import com.github.shortlink.adapter.in.dto.LoginRequestDto;
import com.github.shortlink.adapter.in.dto.LoginResponseDto;

public interface AuthenticatePortIn {
    LoginResponseDto execute(LoginRequestDto dto);
}
