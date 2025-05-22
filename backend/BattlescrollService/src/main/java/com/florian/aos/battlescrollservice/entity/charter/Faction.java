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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AllianceType alliance;

    @OneToMany(mappedBy = "faction")
    private List<Unity> unityList;

    @ManyToMany(mappedBy = "factions")
    private List<RenownedRegiment> renownedRegiments;
}
