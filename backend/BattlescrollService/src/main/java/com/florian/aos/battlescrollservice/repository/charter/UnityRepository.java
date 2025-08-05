package com.florian.aos.battlescrollservice.repository.charter;

import com.florian.aos.battlescrollservice.entity.charter.Unity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnityRepository extends CrudRepository<Unity, Long> {
    boolean existsByNameIgnoreCase(String name);
    List<Unity> findAllByFactionNameIgnoreCase(String name);
}
