package com.florian.aos.battlescrollservice.entity.battleAptitude;

import com.florian.aos.battlescrollservice.entity.Keyword;
import com.florian.aos.battlescrollservice.utils.enums.AptitudeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BattleAptitude {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_battle_aptitude")
    protected Long id;

    @Column(nullable = false)
    protected String name;
    @Column(name = "aptitude_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AptitudeType aptitudeType;
    @Column(nullable = false)
    protected String phase;
    @Column(nullable = false)
    protected String description;
    @Column(nullable = false)
    protected String announcement;
    @Column(nullable = false)
    protected String effect;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "aptitude_keyword",
            joinColumns = @JoinColumn(name = "id_aptitude"),
            inverseJoinColumns = @JoinColumn(name = "id_keyword")
    )
    protected List<Keyword> keywords;

    @OneToOne
    @JoinColumn(name = "aptitude_context_id" , referencedColumnName = "id_aptitude_context")
    private AptitudeContext aptitudeContext;
}
