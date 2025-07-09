package com.florian.aos.battlescrollservice.repository.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.battleAptitude.Domain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomainRepository extends CrudRepository<Domain, Long> {
    boolean existsByName(String name);
    Optional<Domain> findByAptitudeContext(AptitudeContext aptitudeContext);
}
