package com.github.shortlink.core.usecases;

import com.github.shortlink.adapter.in.dto.LoginRequestDto;
import com.github.shortlink.adapter.in.dto.LoginResponseDto;
import com.github.shortlink.core.port.in.AuthenticatePortIn;
import com.github.shortlink.core.port.out.UserRepositoryOut;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUseCase implements AuthenticatePortIn {
    private final UserRepositoryOut userRepositoryOut;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticateUseCase(UserRepositoryOut userRepositoryOut, JwtEncoder jwtEncoder, BCryptPasswordEncoder passwordEncoder) {
        this.userRepositoryOut = userRepositoryOut;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDto execute(LoginRequestDto dto) {
        // Buscar o id do usuario
        // Buscar usuario com base no id
        // gera o jwt token
        // se a senha for invalida retorna o erro com as credenciais invalidas
        return null;
    }
}
