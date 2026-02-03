package com.viniciusdevassis.ficha.musical.presentation.validators.annotations;



import com.viniciusdevassis.ficha.musical.presentation.validators.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "The password must be at least 8 characters long, containing at least" +
            "one uppercase letter, one lowercase letter, one digit, and one special character.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
