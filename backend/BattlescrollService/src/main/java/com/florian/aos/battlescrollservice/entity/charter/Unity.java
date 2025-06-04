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
@DiscriminatorValue("UNITY")
public class Unity extends Charter{

    @Column(nullable = false)
    private int movement;
    @Column(nullable = false)
    private int save;
    @Column(nullable = false)
    private int control;
    @Column(nullable = false)
    private int health;
    @Column(nullable = false)
    private int points;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "faction_id")
    private Faction faction;

    @OneToMany(mappedBy = "unity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Weapon> weapons;

}
