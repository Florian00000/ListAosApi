package com.florian.aos.battlescrollservice.entity.charter;

import com.florian.aos.battlescrollservice.entity.Version;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "version_name")
    protected Version version;
}
