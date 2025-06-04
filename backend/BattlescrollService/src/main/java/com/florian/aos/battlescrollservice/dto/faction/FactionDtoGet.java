package com.florian.aos.battlescrollservice.dto.faction;

import com.florian.aos.battlescrollservice.entity.charter.Faction;
import lombok.Data;

@Data
public class FactionDtoGet {

    private Long id;
    private String name;
    private String imagePath;
    private String version;
    private String alliance;

    public FactionDtoGet(Faction faction) {
        this.id = faction.getId();
        this.name = faction.getName();
        if (faction.getImagePath() != null) {
            this.imagePath = faction.getImagePath();
        }
        this.version = faction.getVersion().getName();
        this.alliance =  faction.getAlliance().toString();
    }
}
