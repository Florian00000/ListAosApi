package com.florian.aos.battlescrollservice.repository.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AptitudeContextRepository extends CrudRepository<AptitudeContext, Long> {
}
