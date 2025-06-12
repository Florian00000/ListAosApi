package com.florian.aos.battlescrollservice.entity.battleAptitude;

import com.florian.aos.battlescrollservice.entity.charter.Charter;
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

    @Column(nullable = false)
    private boolean isOptimisation;
    @Column(nullable = false)
    private boolean isUniversal;
    @Column(nullable = false)
    private boolean isEqualGames;
    private int points;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "charter_id")
    private Charter charter;

    @OneToOne(mappedBy = "aptitudeContext")
    private BattleAptitude battleAptitude;

    @OneToOne(mappedBy = "aptitudeContext")
    private Domain domain;
}
