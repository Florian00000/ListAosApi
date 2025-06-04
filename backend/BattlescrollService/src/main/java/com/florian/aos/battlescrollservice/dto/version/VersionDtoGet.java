package com.florian.aos.battlescrollservice.dto.version;

import com.florian.aos.battlescrollservice.entity.Version;
import lombok.Data;

@Data
public class VersionDtoGet {

    private int id;
    private String name;

    public VersionDtoGet(Version version) {
        this.id = version.getId();
        this.name = version.getName();
    }
}
