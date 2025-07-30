package com.florian.aos.battlescrollservice.dto.unity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UnityDtoPost {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String imagePath;
    @NotBlank(message = "Version is mandatory")
    private String version;
    @NotNull(message = "movement must not be null")
    private Integer movement;
    @NotNull(message = "save must not be null")
    private Integer save;
    @NotNull(message = "control must not be null")
    private Integer control;
    @NotNull(message = "health must not be null")
    private Integer health;
    @NotNull(message = "points must not be null")
    private Integer points;

    private List<String> keywords;

    @NotNull(message = "factionId must not be null")
    private Long factionId;
    @Valid
    private List<WeaponDtoPost> weapons;
}
