package com.florian.aos.battlescrollservice.entity.charter;

import com.florian.aos.battlescrollservice.entity.Keyword;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weapon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_weapon")
    private Long id;

    @Column(nullable = false, name = "weapon_name")
    private String name;
    @Column(nullable = false)
    private boolean isShootingWeapon;
    private int ranged;
    @Column(nullable = false)
    private int attacks;
    @Column(nullable = false)
    private int touch;
    @Column(nullable = false)
    private int wound;
    @Column(nullable = false)
    private int perforation;
    @Column(nullable = false)
    private int damage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unity")
    private Unity unity;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "weapon_keyword",
            joinColumns = @JoinColumn(name = "id_weapon"),
            inverseJoinColumns = @JoinColumn(name = "id_keyword")
    )
    private List<Keyword> keywords;
}
