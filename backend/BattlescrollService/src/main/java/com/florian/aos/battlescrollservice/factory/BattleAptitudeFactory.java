package com.florian.aos.battlescrollservice.factory;

import com.florian.aos.battlescrollservice.dto.battleAptitude.AptitudeContextDtoPost;
import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoPost;
import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoPost;
import com.florian.aos.battlescrollservice.dto.battleAptitude.MagicPrayerDtoPost;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.battleAptitude.BattleAptitude;
import com.florian.aos.battlescrollservice.entity.battleAptitude.Domain;
import com.florian.aos.battlescrollservice.entity.battleAptitude.MagicPrayer;
import com.florian.aos.battlescrollservice.utils.enums.AptitudeType;
import org.springframework.stereotype.Component;


@Component
public class BattleAptitudeFactory {

    public record BattleAptitudeBundle(BattleAptitude battleAptitude, AptitudeContext aptitudeContext){};
    public record DomainBundle(Domain domain, AptitudeContext aptitudeContext){};

    public BattleAptitudeBundle createBattleAptitude(BattleAptitudeDtoPost baDto) {
        if (baDto.getName() == null || baDto.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        BattleAptitude battleAptitude = BattleAptitude.builder()
                .name(baDto.getName())
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

        AptitudeContext aptitudeContext = createAptitudeContext(baDto.getAptitudeContext());

        return new BattleAptitudeBundle(battleAptitude, aptitudeContext);
    }

    public BattleAptitudeBundle createMagicPrayer(MagicPrayerDtoPost dtoPost){
        if (dtoPost.getName() == null || dtoPost.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (dtoPost.getIsMagical() == null){
            throw new IllegalArgumentException("isMagical cannot be null or empty");
        }

        MagicPrayer magicPrayer = MagicPrayer.builder()
                .name(dtoPost.getName())
                .phase(dtoPost.getPhase())
                .description(dtoPost.getDescription())
                .announcement(dtoPost.getAnnouncement())
                .effect(dtoPost.getEffect())
                .launchValue(dtoPost.getLaunchValue())
                .isMagical(dtoPost.getIsMagical())
                .build();
        try {
            magicPrayer.setAptitudeType(AptitudeType.valueOf(dtoPost.getAptitudeType().toUpperCase()));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Illegal aptitude type " + dtoPost.getAptitudeType());
        }
        AptitudeContext aptitudeContext = createAptitudeContext(dtoPost.getAptitudeContext());
        return new BattleAptitudeBundle(magicPrayer, aptitudeContext);
    }

    private AptitudeContext createAptitudeContext(AptitudeContextDtoPost dto){
        AptitudeContext aptitudeContext = AptitudeContext.builder()
                .isOptimisation(dto.getIsOptimisation())
                .isUniversal(dto.getIsUniversal())
                .isEqualGames(dto.getIsEqualGames())
                .build();
        if (dto.getPoints() != null){
            aptitudeContext.setPoints(dto.getPoints());
        }
        return aptitudeContext;
    }

    public DomainBundle createDomain(DomainDtoPost dto) {
        if (dto.getName() == null || dto.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        Domain domain = Domain.builder()
                .name(dto.getName())
                .isMagicalDomain(dto.getIsMagicalDomain())
                .build();
        if (dto.getDescription() != null && !dto.getDescription().isBlank()){
            domain.setDescription(dto.getDescription());
        }

        AptitudeContext aptitudeContext = createAptitudeContext(dto.getAptitudeContext());
        return new DomainBundle(domain, aptitudeContext);
    }
}
