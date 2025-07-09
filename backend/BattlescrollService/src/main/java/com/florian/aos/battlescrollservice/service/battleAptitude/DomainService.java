package com.florian.aos.battlescrollservice.service.battleAptitude;

import com.florian.aos.battlescrollservice.dto.battleAptitude.AptitudeContextDtoPost;
import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoPost;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.battleAptitude.Domain;
import com.florian.aos.battlescrollservice.entity.charter.Charter;
import com.florian.aos.battlescrollservice.exception.ResourceAlreadyExistsException;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.factory.BattleAptitudeFactory;
import com.florian.aos.battlescrollservice.repository.battleAptitude.AptitudeContextRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.DomainRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DomainService {

    private final DomainRepository domainRepository;
    private final BattleAptitudeFactory factory;
    private final CharterRepository charterRepository;
    private final AptitudeContextRepository aptitudeContextRepository;

    public DomainService(DomainRepository domainRepository, BattleAptitudeFactory factory,
                         CharterRepository charterRepository, AptitudeContextRepository aptitudeContextRepository) {
        this.domainRepository = domainRepository;
        this.factory = factory;
        this.charterRepository = charterRepository;
        this.aptitudeContextRepository = aptitudeContextRepository;
    }

    public DomainDtoGet getDomain (Long id){
        Domain domain = domainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Domain"));
        return new DomainDtoGet(domain);
    }

    public List<DomainDtoGet> getAllDomains (){
        List<Domain> domains = (List<Domain>) domainRepository.findAll();
        return domains.stream().map(DomainDtoGet::new).toList();
    }

    public List<DomainDtoGet> getAllDomainsByCharterName(String charterName){
        List<AptitudeContext> aptitudeContextList = aptitudeContextRepository.findAllByCharterNameIgnoreCase(charterName);
        return aptitudeContextList.stream()
                .map(domainRepository::findByAptitudeContext)
                .flatMap(Optional::stream)
                .map(DomainDtoGet::new)
                .toList();
    }

    @Transactional
    public DomainDtoGet addDomain(DomainDtoPost dtoPost){
        if (dtoPost.getAptitudeContext() == null){
            throw new IllegalArgumentException("BattleAptitude must have aptitudeContext");
        }
        if (domainRepository.existsByName(dtoPost.getName())){
            throw new ResourceAlreadyExistsException("This domain name");
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

    @Transactional
    public DomainDtoGet updateDomain(Long id, DomainDtoPost dtoPost){
        Domain domain = domainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Domain"));

        if (dtoPost.getAptitudeContext() != null) {
            if (domain.getAptitudeContext() == null) throw new NotFoundException("Context for Battle Aptitude");
            AptitudeContext aptitudeContext = domain.getAptitudeContext();

            AptitudeContextDtoPost contextDtoPost = dtoPost.getAptitudeContext();
            updateAptitudeContext(aptitudeContext, contextDtoPost);
        }

        if (dtoPost.getName() != null && !dtoPost.getName().isBlank()){
            if (domainRepository.existsByName(dtoPost.getName())){
                throw new ResourceAlreadyExistsException("This domain name");
            }
            domain.setName(dtoPost.getName());
        }
        if (dtoPost.getDescription() != null && !dtoPost.getDescription().isBlank()){
            domain.setDescription(dtoPost.getDescription());
        }
        if(dtoPost.getIsMagicalDomain() != null){
            domain.setMagicalDomain(domain.isMagicalDomain());
        }
        domainRepository.save(domain);
        return new DomainDtoGet(domain);
    }

    private void updateAptitudeContext(AptitudeContext aptitudeContext, AptitudeContextDtoPost dto){
        if (dto.getIsOptimisation() != null){
            aptitudeContext.setOptimisation(dto.getIsOptimisation());
        }
        if (dto.getIsUniversal() != null){
            aptitudeContext.setUniversal(dto.getIsUniversal());
        }
        if (dto.getIsEqualGames() != null){
            aptitudeContext.setEqualGames(dto.getIsEqualGames());
        }
        if (dto.getPoints() != null){
            aptitudeContext.setPoints(dto.getPoints());
        }
        if (dto.getCharterId() != null){
            Charter charter = charterRepository.findById(dto.getCharterId())
                    .orElseThrow(() -> new NotFoundException("Charter "+ dto.getCharterId() + " for Aptitude Context"));
            aptitudeContext.setCharter(charter);
        }
    }

    public boolean deleteDomain(Long id){
        Domain domain = domainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("domain"));
        domainRepository.delete(domain);
        return true;
    }
}
