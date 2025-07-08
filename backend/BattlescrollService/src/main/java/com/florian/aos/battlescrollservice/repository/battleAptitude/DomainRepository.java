package com.florian.aos.battlescrollservice.repository.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.Domain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends CrudRepository<Domain, Long> {
}
