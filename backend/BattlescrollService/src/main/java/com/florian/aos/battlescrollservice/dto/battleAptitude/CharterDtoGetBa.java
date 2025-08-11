package com.florian.aos.battlescrollservice.dto.battleAptitude;

import com.florian.aos.battlescrollservice.entity.charter.Charter;
import lombok.Data;

@Data
public class CharterDtoGetBa {

    private Long id;
    private String name;

    public CharterDtoGetBa (Charter charter){
        this.id = charter.getId();
        this.name = charter.getName();
    }
}
