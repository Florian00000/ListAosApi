package com.florian.aos.battlescrollservice.dto.battleAptitude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AptitudeContextDtoPost {

    private Boolean isOptimisation;
    private Boolean isUniversal;
    private Boolean isEqualGames;
    private Integer points;
    private Long charterId;
}
