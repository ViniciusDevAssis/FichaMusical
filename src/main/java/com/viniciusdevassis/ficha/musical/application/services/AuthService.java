package com.viniciusdevassis.ficha.musical.application.services;

import com.viniciusdevassis.ficha.musical.domain.entities.User;
import com.viniciusdevassis.ficha.musical.infrastructure.repositories.UserRepository;
import com.viniciusdevassis.ficha.musical.presentation.dtos.RegisterDTO;
import com.viniciusdevassis.ficha.musical.presentation.dtos.ResponseDTO;
import com.viniciusdevassis.ficha.musical.presentation.mappers.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional
    public ResponseDTO register(RegisterDTO dto) {
        User User = mapper.registerDTOToUser(dto);
        User savedUser = repository.save(User);
        return mapper.userToResponseDTO(savedUser);
    }

    public UUID getUserIdFromToken() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User) principal).getId();
        } else {
            return UUID.fromString(principal.toString());
        }
    }
}
