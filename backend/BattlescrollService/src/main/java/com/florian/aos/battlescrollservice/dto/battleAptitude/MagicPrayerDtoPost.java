package com.florian.aos.battlescrollservice.dto.battleAptitude;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MagicPrayerDtoPost {

    private String name;
    private String aptitudeType;
    private String phase;
    private String description;
    private String announcement;
    private String effect;
    private Integer launchValue;
    private Boolean isMagical;

    private List<String> keywords;

    private AptitudeContextDtoPost aptitudeContext;
    private Long domainId;
}
