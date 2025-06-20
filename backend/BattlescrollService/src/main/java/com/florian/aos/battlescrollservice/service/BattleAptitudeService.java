package com.florian.aos.battlescrollservice.service;

import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoPost;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.battleAptitude.BattleAptitude;
import com.florian.aos.battlescrollservice.entity.charter.Charter;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.factory.BattleAptitudeFactory;
import com.florian.aos.battlescrollservice.repository.KeywordRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.AptitudeContextRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.BattleAptitudeRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BattleAptitudeService {

    private final AptitudeContextRepository aptitudeContextRepository;
    private final BattleAptitudeRepository battleAptitudeRepository;
    private final BattleAptitudeFactory battleAptitudeFactory;
    private final KeywordRepository keywordRepository;
    private final CharterRepository charterRepository;

    public BattleAptitudeService(AptitudeContextRepository aptitudeContextRepository,
                                 BattleAptitudeRepository battleAptitudeRepository,
                                 BattleAptitudeFactory battleAptitudeFactory,
                                 KeywordRepository keywordRepository,
                                 CharterRepository charterRepository
    ) {
        this.aptitudeContextRepository = aptitudeContextRepository;
        this.battleAptitudeRepository = battleAptitudeRepository;
        this.battleAptitudeFactory = battleAptitudeFactory;
        this.keywordRepository = keywordRepository;
        this.charterRepository = charterRepository;
    }

    //TODO tester et faire le controller
    @Transactional
    public BattleAptitudeDtoGet addBattleAptitude (BattleAptitudeDtoPost dtoPost) {
        if (dtoPost.getAptitudeContext() == null){
            throw new IllegalArgumentException("BattleAptitude must have aptitudeContext");
        }

        BattleAptitudeFactory.BattleAptitudeBundle bundle = battleAptitudeFactory.createBattleAptitude(dtoPost);
        BattleAptitude battleAptitude = bundle.battleAptitude();
        AptitudeContext aptitudeContext = bundle.aptitudeContext();

        if (dtoPost.getKeywords() != null && !dtoPost.getKeywords().isEmpty()){
            battleAptitude.setKeywords(dtoPost.getKeywords().stream()
                    .map((keyword) -> keywordRepository.findByName(keyword.toLowerCase())
                    .orElseThrow(() -> new NotFoundException("Keyword")))
                    .toList()
            );
        }

        if (!dtoPost.getAptitudeContext().isUniversal()){
            if (dtoPost.getAptitudeContext().getCharterId() != null && dtoPost.getAptitudeContext().getCharterId() != 0){
                Charter charter = charterRepository.findById(dtoPost.getAptitudeContext().getCharterId())
                        .orElseThrow(() -> new NotFoundException("Charter"));
                aptitudeContext.setCharter(charter);
            }else{
                throw new IllegalArgumentException("A non-universal battleAptitude must be linked to a charter");
            }
        }

        battleAptitude.setAptitudeContext(aptitudeContext);
        battleAptitudeRepository.save(battleAptitude);
        return new BattleAptitudeDtoGet(battleAptitude);
    }
}
