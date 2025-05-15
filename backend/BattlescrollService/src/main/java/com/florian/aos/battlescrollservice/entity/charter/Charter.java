package com.florian.aos.battlescrollservice.entity.charter;

import com.florian.aos.battlescrollservice.entity.Keyword;
import com.florian.aos.battlescrollservice.entity.Version;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
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
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_charter")

public abstract class Charter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_charter")
    protected Long id;

    @Column(nullable = false)
    protected String name;
    @Column(name = "image_path")
    protected String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_name")
    protected Version version;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "charter_keyword",
            joinColumns = @JoinColumn(name = "id_charter"),
            inverseJoinColumns = @JoinColumn(name = "id_keyword")
    )
    protected List<Keyword> keywords;

    @OneToMany(mappedBy = "charter")
    protected List<AptitudeContext> aptitudeContextList;
}
