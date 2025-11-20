package com.florian.aos.securityservice.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDto {

    @Email(message = "Invalid email format")
    @NotBlank(message = "email is mandatory")
    private String email;
    @Size(min = 8, max = 64, message = "Invalid password length: minimum 8 characters, maximum 64.")
    @NotBlank(message = "password is mandatory")
    private String password;
}
