package com.github.shortlink.adapter.in;

import com.github.shortlink.adapter.in.dto.LoginRequestDto;
import com.github.shortlink.adapter.in.dto.LoginResponseDto;
import com.github.shortlink.core.port.in.AuthenticatePortIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth")
public class AuthController {
    private final AuthenticatePortIn authenticatePortIn;

    public AuthController(AuthenticatePortIn authenticatePortIn) {
        this.authenticatePortIn = authenticatePortIn;
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponseDto> getToken(@RequestBody LoginRequestDto dto) {
        final LoginResponseDto token = authenticatePortIn.execute(dto);
        return ResponseEntity.ok().body(token);
    }
}
