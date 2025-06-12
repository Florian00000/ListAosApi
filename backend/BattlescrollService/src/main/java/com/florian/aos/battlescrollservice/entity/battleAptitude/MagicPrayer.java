package com.florian.aos.battlescrollservice.entity.battleAptitude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;
}
