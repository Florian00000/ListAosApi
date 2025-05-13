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

public class Charter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_charter")
    private int id;

    @Column(nullable = false)
    private String name;
    private String imagePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "version_name")
    private Version version;
}
