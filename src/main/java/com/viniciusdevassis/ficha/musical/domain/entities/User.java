package com.viniciusdevassis.ficha.musical.domain.entities;

import com.viniciusdevassis.ficha.musical.domain.enums.Role;
import com.viniciusdevassis.ficha.musical.domain.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    public static User newUser(String name, String email, String password) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.STUDENT);
        user.setStatus(Status.ACTIVE);
        return user;
    }
}
