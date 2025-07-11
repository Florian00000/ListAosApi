package com.florian.aos.battlescrollservice.dto.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.MagicPrayer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MagicPrayerDtoGet extends BattleAptitudeDtoGet {
    private int launchValue;
    private Boolean isMagical;

    private DomainDtoGetMagic domain;

    public MagicPrayerDtoGet(MagicPrayer magicPrayer) {
        super(magicPrayer);
        this.launchValue = magicPrayer.getLaunchValue();
        this.isMagical = magicPrayer.isMagical();
        if (magicPrayer.getDomain() != null){
            this.domain = new DomainDtoGetMagic(magicPrayer.getDomain());
        }
    }
}
