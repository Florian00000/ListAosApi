package com.florian.aos.battlescrollservice.dto.unity;

import jakarta.validation.Valid;

import lombok.Data;

import java.util.List;

@Data
public class UnityDtoUpdate {
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
    @Valid
    private List<WeaponDtoPost> weapons;
}
