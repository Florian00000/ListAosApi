package com.florian.aos.battlescrollservice.service.battleAptitude;

import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoPost;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.battleAptitude.Domain;
import com.florian.aos.battlescrollservice.entity.charter.Charter;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.factory.BattleAptitudeFactory;
import com.florian.aos.battlescrollservice.repository.battleAptitude.DomainRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomainService {

    private final DomainRepository domainRepository;
    private final BattleAptitudeFactory factory;
    private final CharterRepository charterRepository;

    public DomainService(DomainRepository domainRepository, BattleAptitudeFactory factory,
                         CharterRepository charterRepository) {
        this.domainRepository = domainRepository;
        this.factory = factory;
        this.charterRepository = charterRepository;
    }

    @Transactional
    public DomainDtoGet addDomain(DomainDtoPost dtoPost){
        if (dtoPost.getAptitudeContext() == null){
            throw new IllegalArgumentException("BattleAptitude must have aptitudeContext");
        }
        BattleAptitudeFactory.DomainBundle bundle = factory.createDomain(dtoPost);
        Domain domain = bundle.domain();
        AptitudeContext aptitudeContext = bundle.aptitudeContext();

        if (!dtoPost.getAptitudeContext().getIsUniversal()){
            if (dtoPost.getAptitudeContext().getCharterId() != null && dtoPost.getAptitudeContext().getCharterId() != 0){
                Charter charter = charterRepository.findById(dtoPost.getAptitudeContext().getCharterId())
                        .orElseThrow(() -> new NotFoundException("Charter"));
                aptitudeContext.setCharter(charter);
            }else{
                throw new IllegalArgumentException("A non-universal domain must be linked to a charter");
            }
        }

        domain.setAptitudeContext(aptitudeContext);
        domainRepository.save(domain);
        return new DomainDtoGet(domain);
    }
}
