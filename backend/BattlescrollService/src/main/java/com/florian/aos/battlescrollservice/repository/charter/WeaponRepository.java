package com.florian.aos.battlescrollservice.repository.charter;

import com.florian.aos.battlescrollservice.entity.charter.Weapon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends CrudRepository<Weapon, Long> {
}
