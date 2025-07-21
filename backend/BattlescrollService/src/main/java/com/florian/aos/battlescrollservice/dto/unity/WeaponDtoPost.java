package com.florian.aos.battlescrollservice.dto.unity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WeaponDtoPost {
    private String name;
    private Boolean isShootingWeapon;
    private Integer ranged;
    private Integer attacks;
    private Integer touch;
    private Integer wound;
    private Integer perforation;
    private Integer damage;

    private List<String> keywords;
}
