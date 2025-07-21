package com.florian.aos.battlescrollservice.dto.unity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UnityDtoPost {
    private String name;
    private String imagePath;
    private String version;
    private Integer movement;
    private Integer save;
    private Integer control;
    private Integer health;
    private Integer points;

    private List<String> keywords;

    private Long factionId;
    private List<WeaponDtoPost> weapons;
}
