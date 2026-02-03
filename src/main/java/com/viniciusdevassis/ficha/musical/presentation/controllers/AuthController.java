package com.viniciusdevassis.ficha.musical.presentation.controllers;

import com.viniciusdevassis.ficha.musical.application.services.AuthService;
import com.viniciusdevassis.ficha.musical.domain.entities.User;
import com.viniciusdevassis.ficha.musical.infrastructure.repositories.UserRepository;
import com.viniciusdevassis.ficha.musical.infrastructure.security.TokenService;
import com.viniciusdevassis.ficha.musical.presentation.dtos.LoginDTO;
import com.viniciusdevassis.ficha.musical.presentation.dtos.RegisterDTO;
import com.viniciusdevassis.ficha.musical.presentation.dtos.ResponseDTO;
import com.viniciusdevassis.ficha.musical.presentation.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO body) {
        User User = this.repository.findByEmail(body.email()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        if (encoder.matches(body.password(), User.getPassword())) {
            String token = this.tokenService.generateToken(User);
            return ResponseEntity.ok(new ResponseDTO(User.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(
            @Valid @RequestBody RegisterDTO body
    ) {
        body.setPassword(encoder.encode(body.getPassword()));
        ResponseDTO User = service.register(body);
        User registeredUser = mapper.responseDTOToUser(User);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/Users/{slug}")
                .buildAndExpand(registeredUser.getId()).toUri();
        String token = this.tokenService.generateToken(registeredUser);
        ResponseDTO response = new ResponseDTO(registeredUser.getName(), token);
        return ResponseEntity.created(uri).body(response);
    }
}
