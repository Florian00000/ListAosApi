package com.florian.aos.battlescrollservice.repository;

import com.florian.aos.battlescrollservice.entity.Version;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VersionRepository extends CrudRepository<Version, Integer> {
    Optional<Version> findByName(String name);
}
