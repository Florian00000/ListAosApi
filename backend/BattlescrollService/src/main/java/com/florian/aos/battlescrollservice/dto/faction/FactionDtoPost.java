package com.florian.aos.battlescrollservice.dto.faction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FactionDtoPost {

    private String name;
    private String imagePath;
    private String version;
    private String alliance;
}
