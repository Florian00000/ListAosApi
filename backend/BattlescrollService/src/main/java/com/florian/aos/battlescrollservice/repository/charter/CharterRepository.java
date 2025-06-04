package com.florian.aos.battlescrollservice.repository.charter;

import com.florian.aos.battlescrollservice.entity.charter.Charter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharterRepository extends CrudRepository<Charter, Long> {
}
