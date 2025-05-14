package com.florian.aos.battlescrollservice.entity.charter;

import com.florian.aos.battlescrollservice.utils.enums.AllianceType;
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
@AllArgsConstructor
@DiscriminatorValue("FACTION")
public class Faction extends Charter{

    @Column(unique=true, nullable=false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AllianceType alliance;

    @ManyToMany(mappedBy = "factions")
    private List<RenownedRegiment> renownedRegiments;
}
