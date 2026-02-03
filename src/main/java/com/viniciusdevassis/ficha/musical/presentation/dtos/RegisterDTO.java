package com.viniciusdevassis.ficha.musical.presentation.dtos;

import com.viniciusdevassis.ficha.musical.presentation.validators.annotations.EmailAvailable;
import com.viniciusdevassis.ficha.musical.presentation.validators.annotations.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterDTO {

    @NotBlank(message = "The 'name' field is required")
    private String name;

    @NotBlank(message = "The 'email' field is required")
    @EmailAvailable
    private String email;

    @NotBlank(message = "The 'password' field is required")
    @Password
    private String password;
}
