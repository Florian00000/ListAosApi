package com.florian.aos.battlescrollservice.dto.faction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactionDtoPost {
    private String name;
    private String imagePath;
    private String version;
    private String alliance;
}
