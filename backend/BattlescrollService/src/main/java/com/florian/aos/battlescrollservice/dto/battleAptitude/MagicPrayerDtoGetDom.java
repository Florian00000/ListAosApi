package com.florian.aos.battlescrollservice.dto.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.MagicPrayer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MagicPrayerDtoGetDom extends BattleAptitudeDtoGet{
    private Integer launchValue;

    public MagicPrayerDtoGetDom(MagicPrayer magicPrayer) {
        super(magicPrayer);
        this.launchValue = magicPrayer.getLaunchValue();
    }
}
