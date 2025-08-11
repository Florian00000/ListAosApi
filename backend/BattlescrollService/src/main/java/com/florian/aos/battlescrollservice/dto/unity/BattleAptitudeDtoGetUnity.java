package com.florian.aos.battlescrollservice.dto.unity;

import com.florian.aos.battlescrollservice.entity.Keyword;
import com.florian.aos.battlescrollservice.entity.battleAptitude.BattleAptitude;
import com.florian.aos.battlescrollservice.entity.battleAptitude.MagicPrayer;
import lombok.Data;

import java.util.List;

@Data
public class BattleAptitudeDtoGetUnity {

    private Long id;
    private String name;
    private String phase;
    private String description;
    private String announcement;
    private String effect;
    private List<String> keywords;

    private int launchValue;
    private Boolean isMagical;

    private AptitudeContextDtoGetUnity aptitudeContext;

    public BattleAptitudeDtoGetUnity(BattleAptitude battleAptitude) {
        this.id = battleAptitude.getId();
        this.name = battleAptitude.getName();
        this.phase = battleAptitude.getPhase();
        this.description = battleAptitude.getDescription();
        this.announcement = battleAptitude.getAnnouncement();
        this.effect = battleAptitude.getEffect();
        this.aptitudeContext = new AptitudeContextDtoGetUnity(battleAptitude.getAptitudeContext());
        if (battleAptitude.getKeywords() != null && !battleAptitude.getKeywords().isEmpty()){
           this.keywords = battleAptitude.getKeywords().stream().map(Keyword::getName).toList();
        }
        if (battleAptitude instanceof MagicPrayer magicPrayer){
            this.launchValue = magicPrayer.getLaunchValue();
            this.isMagical = magicPrayer.isMagical();
        }
    }
}