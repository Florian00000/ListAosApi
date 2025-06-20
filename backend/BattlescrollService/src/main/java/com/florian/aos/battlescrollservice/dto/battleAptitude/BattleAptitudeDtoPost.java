package com.florian.aos.battlescrollservice.dto.battleAptitude;

import lombok.Data;

import java.util.List;

@Data
public class BattleAptitudeDtoPost {

    private String name;
    private String aptitudeType;
    private String phase;
    private String description;
    private String announcement;
    private String effect;

    private List<String> keywords;

    private AptitudeContextDtoPost aptitudeContext;
}
