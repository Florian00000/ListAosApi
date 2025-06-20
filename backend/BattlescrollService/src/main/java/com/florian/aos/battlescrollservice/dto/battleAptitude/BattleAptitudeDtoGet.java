package com.florian.aos.battlescrollservice.dto.battleAptitude;

import com.florian.aos.battlescrollservice.entity.Keyword;
import com.florian.aos.battlescrollservice.entity.battleAptitude.BattleAptitude;
import lombok.Data;

import java.util.List;

@Data
public class BattleAptitudeDtoGet {

    private Long id;
    private String name;
    private String phase;
    private String description;
    private String announcement;
    private String effect;
    private List<String> keywords;

    private AptitudeContextDtoGet aptitudeContext;

    public BattleAptitudeDtoGet (BattleAptitude battleAptitude) {
        this.id = battleAptitude.getId();
        this.name = battleAptitude.getName();
        this.phase = battleAptitude.getPhase();
        this.description = battleAptitude.getDescription();
        this.announcement = battleAptitude.getAnnouncement();
        this.effect = battleAptitude.getEffect();
        this.aptitudeContext = new AptitudeContextDtoGet(battleAptitude.getAptitudeContext());
        if (battleAptitude.getKeywords() != null && !battleAptitude.getKeywords().isEmpty()){
           this.keywords = battleAptitude.getKeywords().stream().map(Keyword::getName).toList();
        }
    }
}