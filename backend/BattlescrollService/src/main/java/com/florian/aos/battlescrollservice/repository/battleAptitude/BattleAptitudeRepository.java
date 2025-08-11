package com.florian.aos.battlescrollservice.repository.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.battleAptitude.BattleAptitude;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BattleAptitudeRepository extends CrudRepository<BattleAptitude, Long> {
    Optional<BattleAptitude> findByAptitudeContext(AptitudeContext aptitudeContext);
}
