package com.florian.aos.battlescrollservice.dto.keyword;

import com.florian.aos.battlescrollservice.entity.Keyword;
import lombok.Data;

@Data
public class KeywordDtoGet {

    private Long id;
    private String name;

    public KeywordDtoGet(Keyword k) {
        this.id = k.getId();
        this.name = k.getName();
    }
}
