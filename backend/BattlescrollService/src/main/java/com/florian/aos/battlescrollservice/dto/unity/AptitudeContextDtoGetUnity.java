package com.florian.aos.battlescrollservice.dto.unity;


import com.florian.aos.battlescrollservice.dto.battleAptitude.CharterDtoGetBa;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import lombok.Data;

@Data
public class AptitudeContextDtoGetUnity {

    private Long id;

    private boolean isOptimisation;
    private boolean isUniversal;
    private boolean isEqualGames;
    private int points;


    public AptitudeContextDtoGetUnity(AptitudeContext aptitudeContext) {
        this.id = aptitudeContext.getId();
        this.isOptimisation = aptitudeContext.isOptimisation();
        this.isUniversal = aptitudeContext.isUniversal();
        this.isEqualGames = aptitudeContext.isEqualGames();
        this.points = aptitudeContext.getPoints();
    }
}