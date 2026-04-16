package com.github.shortlink.adapter.in;

import com.github.shortlink.adapter.in.dto.CreateUserRequestDto;
import com.github.shortlink.adapter.in.dto.CreateUserResponseDto;
import com.github.shortlink.adapter.out.mappers.UserMapper;
import com.github.shortlink.core.domain.User;
import com.github.shortlink.core.port.in.CreateUserPortIn;
import com.github.shortlink.core.port.in.DeleteUserPortIn;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final CreateUserPortIn createUserPortIn;
    private final DeleteUserPortIn deleteUserPortIn;

    public UsersController(CreateUserPortIn createUserPortIn, DeleteUserPortIn deleteUserPortIn) {
        this.createUserPortIn = createUserPortIn;
        this.deleteUserPortIn = deleteUserPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto requestDto) {
        final User userCreated = createUserPortIn.execute(UserMapper.dtoRequestToDomain(requestDto));
        final CreateUserResponseDto body = UserMapper.dtoResponseFromDomain(userCreated);

        return ResponseEntity.created(URI.create("/")).body(body);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(JwtAuthenticationToken token) {
        final String userId = token.getTokenAttributes().get("sub").toString();
        deleteUserPortIn.execute(UUID.fromString(userId));

        return ResponseEntity.noContent().build();
    }
}
