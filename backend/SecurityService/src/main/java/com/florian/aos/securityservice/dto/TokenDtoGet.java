package com.florian.aos.securityservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenDtoGet {
    private String token;
}
