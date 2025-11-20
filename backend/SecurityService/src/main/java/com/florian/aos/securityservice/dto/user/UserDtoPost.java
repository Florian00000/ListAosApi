package com.florian.aos.securityservice.dto.user;

import com.florian.aos.securityservice.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserDtoPost {

    private String firstName;
    private String lastName;
    @Email(message = "Invalid email format")
    @NotBlank(message = "email is mandatory")
    private String email;
    @Size(min = 8, max = 64, message = "Invalid password length: minimum 8 characters, maximum 64.")
    @NotBlank(message = "password is mandatory")
    private String password;
    private List<String> roles;

    public User convertToUser() {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
        return user;
    }
}
