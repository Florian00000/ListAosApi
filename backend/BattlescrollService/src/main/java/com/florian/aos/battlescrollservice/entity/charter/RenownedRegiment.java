package com.florian.aos.battlescrollservice.entity.charter;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@DiscriminatorValue("RENOWNED_REGIMENT")
public class RenownedRegiment extends Charter {

    private int points;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean isArmyOfRenown;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "renowned_faction",
            joinColumns = @JoinColumn(name = "id_renowned"),
            inverseJoinColumns = @JoinColumn(name = "id_faction")
    )
    private List<Faction> factions;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "organize",
            joinColumns = @JoinColumn(name = "id_renowned"),
            inverseJoinColumns = @JoinColumn(name = "id_unity")
    )
    private List<Unity> units;
}
