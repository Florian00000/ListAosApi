package com.florian.aos.securityservice.dto.user;

import com.florian.aos.securityservice.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDtoPost {

    private String firstName;
    private String lastName;
    private String email;
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
