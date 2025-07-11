package com.florian.aos.battlescrollservice.entity.battleAptitude;

import jakarta.persistence.*;
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

    @Column(name = "launch_value")
    private int launchValue;
    private boolean isMagical;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "domain_id")
    private Domain domain;
}
