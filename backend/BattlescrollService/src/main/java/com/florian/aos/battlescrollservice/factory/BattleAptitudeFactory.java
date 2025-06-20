package com.florian.aos.battlescrollservice.factory;

import com.florian.aos.battlescrollservice.dto.battleAptitude.AptitudeContextDtoPost;
import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoPost;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.battleAptitude.BattleAptitude;
import com.florian.aos.battlescrollservice.utils.enums.AptitudeType;
import org.springframework.stereotype.Component;


@Component
public class BattleAptitudeFactory {

    public record BattleAptitudeBundle(BattleAptitude battleAptitude, AptitudeContext aptitudeContext){};

    public BattleAptitudeBundle createBattleAptitude(BattleAptitudeDtoPost baDto) {
        if (baDto.getName() == null || baDto.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        BattleAptitude battleAptitude = BattleAptitude.builder()
                .phase(baDto.getPhase())
                .description(baDto.getDescription())
                .announcement(baDto.getAnnouncement())
                .effect(baDto.getEffect())
                .build();
        try {
            battleAptitude.setAptitudeType(AptitudeType.valueOf(baDto.getAptitudeType().toUpperCase()));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Illegal aptitude type " + baDto.getAptitudeType());
        }

        AptitudeContext aptitudeContext = AptitudeContext.builder()
                .isOptimisation(baDto.getAptitudeContext().isOptimisation())
                .isUniversal(baDto.getAptitudeContext().isUniversal())
                .isEqualGames(baDto.getAptitudeContext().isEqualGames())
                .build();
        if (baDto.getAptitudeContext().getPoints() != null){
            aptitudeContext.setPoints(baDto.getAptitudeContext().getPoints());
        }

        return new BattleAptitudeBundle(battleAptitude, aptitudeContext);
    }
}
