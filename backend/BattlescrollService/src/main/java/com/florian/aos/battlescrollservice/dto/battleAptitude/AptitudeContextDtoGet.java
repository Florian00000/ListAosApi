package com.florian.aos.battlescrollservice.dto.battleAptitude;


import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class AptitudeContextDtoGet {

    private Long id;

    private boolean isOptimisation;
    private boolean isUniversal;
    private boolean isEqualGames;
    private int points;

    private CharterDtoGetBa charter;

    public AptitudeContextDtoGet (AptitudeContext aptitudeContext) {
        this.id = aptitudeContext.getId();
        this.isOptimisation = aptitudeContext.isOptimisation();
        this.isUniversal = aptitudeContext.isUniversal();
        this.isEqualGames = aptitudeContext.isEqualGames();
        this.points = aptitudeContext.getPoints();
        if (aptitudeContext.getCharter() != null) {
            this.charter = new CharterDtoGetBa(aptitudeContext.getCharter());
        }
    }
}