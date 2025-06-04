package com.florian.aos.battlescrollservice.entity.battleAptitude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MagicPrayer extends BattleAptitude {

    @Column(nullable = false, name = "launch_value")
    private int launchValue;
    @Column(nullable = false)
    private String domain;
}
