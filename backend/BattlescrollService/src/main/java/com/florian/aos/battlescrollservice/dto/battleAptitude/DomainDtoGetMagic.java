package com.florian.aos.battlescrollservice.dto.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.Domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainDtoGetMagic {
    private Long id;
    private String name;
    private String description;
    private Boolean isMagicalDomain;
    private AptitudeContextDtoGet aptitudeContextDtoGet;

    public DomainDtoGetMagic(Domain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.isMagicalDomain = domain.isMagicalDomain();
        if (domain.getDescription() != null){
            this.description = domain.getDescription();
        }
        this.aptitudeContextDtoGet = new AptitudeContextDtoGet(domain.getAptitudeContext());
    }
}
