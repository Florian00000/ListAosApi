package com.florian.aos.securityservice.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDtoPost {
    @NotBlank(message = "role is mandatory")
    private String role;
}
