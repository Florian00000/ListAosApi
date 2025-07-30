package com.florian.aos.battlescrollservice.dto.unity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WeaponDtoPost {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "isShootingWeapon must not be null")
    private Boolean isShootingWeapon;
    private Integer ranged;
    @NotNull(message = "attacks must not be null")
    private Integer attacks;
    @NotNull(message = "touch must not be null")
    private Integer touch;
    @NotNull(message = "wound must not be null")
    private Integer wound;
    @NotNull(message = "perforation must not be null")
    private Integer perforation;
    @NotNull(message = "damage must not be null")
    private Integer damage;

    private List<String> keywords;
}
