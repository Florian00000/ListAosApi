package com.florian.aos.battlescrollservice.dto.faction;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactionDtoPost {
    @NotBlank(message = "name is mandatory")
    private String name;
    private String imagePath;
    @NotBlank(message = "version is mandatory")
    private String version;
    @NotBlank(message = "alliance is mandatory")
    private String alliance;
}
