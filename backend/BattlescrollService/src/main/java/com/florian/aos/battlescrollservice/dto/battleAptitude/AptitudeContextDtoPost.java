package com.florian.aos.battlescrollservice.dto.battleAptitude;

import lombok.Data;

@Data
public class AptitudeContextDtoPost {

    private boolean isOptimisation;
    private boolean isUniversal;
    private boolean isEqualGames;
    private Integer points;
    private Long charterId;
}
