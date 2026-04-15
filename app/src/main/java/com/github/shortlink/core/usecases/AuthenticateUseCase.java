package com.github.shortlink.core.usecases;

import com.github.shortlink.adapter.in.dto.LoginRequestDto;
import com.github.shortlink.adapter.in.dto.LoginResponseDto;
import com.github.shortlink.adapter.in.exceptions.LoginException;
import com.github.shortlink.adapter.in.exceptions.UserNotFoundException;
import com.github.shortlink.core.domain.User;
import com.github.shortlink.core.port.in.AuthenticatePortIn;
import com.github.shortlink.core.port.in.JwtPortIn;
import com.github.shortlink.core.port.out.UserRepositoryOut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUseCase implements AuthenticatePortIn {
    @Value("${jwt.expires.in}")
    private Long expiresIn;

    private final UserRepositoryOut userRepositoryOut;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtPortIn jwtPortIn;

    public AuthenticateUseCase(UserRepositoryOut userRepositoryOut, BCryptPasswordEncoder passwordEncoder, JwtPortIn jwtPortIn) {
        this.userRepositoryOut = userRepositoryOut;
        this.passwordEncoder = passwordEncoder;
        this.jwtPortIn = jwtPortIn;
    }

    @Override
    public LoginResponseDto execute(LoginRequestDto dto) {
        final User user = userRepositoryOut.findByEmail(dto.email())
                .orElseThrow(() -> new UserNotFoundException("Usuário com email: " + dto.email() + " não encontrado "));
        validatePassword(dto.password(), user.getPassword());

        final String jwt = jwtPortIn.execute(user.getUserId(), user.getEmail());

        return new LoginResponseDto(jwt, expiresIn);
    }

    private void validatePassword(String passwordRequest, String userPassword) {
        final boolean passwordValid = passwordEncoder.matches(passwordRequest, userPassword);

        if (!passwordValid) {
            throw new LoginException("Aconteceu um erro ao tentar validar o email ou senha do usuário.");
        }
    }
}
