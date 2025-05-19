package com.florian.aos.battlescrollservice.dto.version;

import com.florian.aos.battlescrollservice.entity.Version;
import lombok.Data;

@Data
public class VersionDtoPost {
    private String name;

    public Version convertToVersion(){
        Version version = Version
                .builder()
                .name(name)
                .build();
        return version;
    }
}


