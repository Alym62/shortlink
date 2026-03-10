package com.github.shortlink.adapter.in;

import com.github.shortlink.adapter.in.dto.CreateUserRequestDto;
import com.github.shortlink.adapter.in.dto.CreateUserResponseDto;
import com.github.shortlink.core.domain.User;
import com.github.shortlink.core.port.in.CreateUserPortIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final CreateUserPortIn createUserPortIn;

    public UsersController(CreateUserPortIn createUserPortIn) {
        this.createUserPortIn = createUserPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto requestDto) {
        final User userCreated = createUserPortIn.execute(CreateUserRequestDto.toDomain(requestDto));
        final CreateUserResponseDto body = CreateUserResponseDto.fromDomain(userCreated);

        return ResponseEntity.created(URI.create("/")).body(body);
    }
}
