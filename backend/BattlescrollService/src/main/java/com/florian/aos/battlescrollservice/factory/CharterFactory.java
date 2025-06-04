package com.florian.aos.battlescrollservice.factory;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoPost;
import com.florian.aos.battlescrollservice.entity.Version;
import com.florian.aos.battlescrollservice.entity.charter.Faction;
import com.florian.aos.battlescrollservice.utils.enums.AllianceType;
import org.springframework.stereotype.Component;

@Component
public class CharterFactory {

    public Faction fromDto(FactionDtoPost dto, Version version){
        if (dto.getName() == null || dto.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        Faction faction = Faction
                .builder()
                .name(dto.getName())
                .version(version)
                .build();

        try {
            faction.setAlliance(AllianceType.valueOf(dto.getAlliance().toUpperCase()));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Illegal alliance type " + dto.getAlliance());
        }
        return faction;
    }


}
