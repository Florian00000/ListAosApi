package com.florian.aos.battlescrollservice.dto.battleAptitude;

import lombok.Data;

@Data
public class DomainDtoPost {

    private String name;
    private String description;
    private Boolean isMagicalDomain;
    private AptitudeContextDtoPost aptitudeContext;
}
