package com.florian.aos.battlescrollservice.entity.battleAptitude;

import com.florian.aos.battlescrollservice.entity.charter.Charter;
import com.florian.aos.battlescrollservice.utils.enums.AptitudeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aptitude_context")
public class AptitudeContext {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aptitude_context")
    private Long id;

    @Column(name = "aptitude_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AptitudeType aptitudeType;
    @Column(nullable = false)
    private boolean isOptimisation;

    private int points;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_charter")
    private Charter charter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_battle_aptitude")
    private BattleAptitude battleAptitude;


}
