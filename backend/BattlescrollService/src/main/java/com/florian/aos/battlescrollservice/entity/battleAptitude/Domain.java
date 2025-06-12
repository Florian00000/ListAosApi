package com.florian.aos.battlescrollservice.entity.battleAptitude;

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
public class Domain {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domain")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @Column(nullable = false)
    private boolean isMagicalDomain;


    @OneToMany(mappedBy = "domain")
    private List<MagicPrayer> magicPrayerList;

    @OneToOne
    @JoinColumn(name = "domain_context_id", referencedColumnName = "id_aptitude_context")
    private AptitudeContext aptitudeContext;
}
