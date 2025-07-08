package com.florian.aos.battlescrollservice.dto.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.Domain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DomainDtoGet {

    private Long id;
    private String name;
    private String description;
    private Boolean isMagicalDomain;

    private List<MagicPrayerDtoGetDom> magicPrayerList;
    private AptitudeContextDtoGet aptitudeContextDtoGet;

    public DomainDtoGet(Domain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.isMagicalDomain = domain.isMagicalDomain();
        if (domain.getDescription() != null){
            this.description = domain.getDescription();
        }
        if (domain.getMagicPrayerList() != null && !domain.getMagicPrayerList().isEmpty()){
            this.magicPrayerList = domain.getMagicPrayerList().stream().map(MagicPrayerDtoGetDom::new).toList();
        }
        this.aptitudeContextDtoGet = new AptitudeContextDtoGet(domain.getAptitudeContext());
    }
}
