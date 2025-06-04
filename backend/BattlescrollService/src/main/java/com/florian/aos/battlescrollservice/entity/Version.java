package com.florian.aos.battlescrollservice.entity;

import com.florian.aos.battlescrollservice.entity.charter.Charter;
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
@Table(uniqueConstraints = @UniqueConstraint(name = "uc_version_name", columnNames = "version_name"))
public class Version {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_version")
    private int id;

    @Column(name = "version_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "version", fetch = FetchType.LAZY)
    private List<Charter> charters;
}
