package com.florian.aos.battlescrollservice.repository.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.MagicPrayer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicPrayerRepository extends CrudRepository<MagicPrayer, Long> {
}
