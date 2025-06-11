package com.florian.aos.battlescrollservice.repository;

import com.florian.aos.battlescrollservice.entity.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long> {
}
