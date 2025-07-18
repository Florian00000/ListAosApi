package com.florian.aos.battlescrollservice.service.battleAptitude;

import com.florian.aos.battlescrollservice.dto.battleAptitude.*;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.battleAptitude.BattleAptitude;
import com.florian.aos.battlescrollservice.entity.battleAptitude.Domain;
import com.florian.aos.battlescrollservice.entity.battleAptitude.MagicPrayer;
import com.florian.aos.battlescrollservice.entity.charter.Charter;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.factory.BattleAptitudeFactory;
import com.florian.aos.battlescrollservice.repository.KeywordRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.AptitudeContextRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.BattleAptitudeRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.DomainRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.MagicPrayerRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import com.florian.aos.battlescrollservice.utils.enums.AptitudeType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BattleAptitudeService {

    private final AptitudeContextRepository aptitudeContextRepository;
    private final BattleAptitudeRepository battleAptitudeRepository;
    private final BattleAptitudeFactory battleAptitudeFactory;
    private final KeywordRepository keywordRepository;
    private final CharterRepository charterRepository;
    private final DomainRepository domainRepository;
    private final MagicPrayerRepository magicPrayerRepository;


    public BattleAptitudeService(AptitudeContextRepository aptitudeContextRepository,
                                 BattleAptitudeRepository battleAptitudeRepository,
                                 BattleAptitudeFactory battleAptitudeFactory,
                                 KeywordRepository keywordRepository,
                                 CharterRepository charterRepository, DomainRepository domainRepository, MagicPrayerRepository magicPrayerRepository
    ) {
        this.aptitudeContextRepository = aptitudeContextRepository;
        this.battleAptitudeRepository = battleAptitudeRepository;
        this.battleAptitudeFactory = battleAptitudeFactory;
        this.keywordRepository = keywordRepository;
        this.charterRepository = charterRepository;
        this.domainRepository = domainRepository;
        this.magicPrayerRepository = magicPrayerRepository;
    }

    //====================================== BattleAptitude ======================================

    public BattleAptitudeDtoGet getBattleAptitude(Long id){
        BattleAptitude battleAptitude = battleAptitudeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Battle Aptitude"));
        return new BattleAptitudeDtoGet(battleAptitude);
    }

    public List<BattleAptitudeDtoGet> getAllBattleAptitudes(){
        List<BattleAptitude> battleAptitudes = (List<BattleAptitude>) battleAptitudeRepository.findAll();
        return battleAptitudes.stream().map(BattleAptitudeDtoGet::new).toList();
    }

    public List<BattleAptitudeDtoGet> getAllBattleAptitudesByCharterName(String charterName){
        List<AptitudeContext> aptitudeContextList = aptitudeContextRepository.findAllByCharterNameIgnoreCase(charterName);
        return aptitudeContextList.stream()
                .map(battleAptitudeRepository::findByAptitudeContext)
                .flatMap(Optional::stream)
                .map(BattleAptitudeDtoGet::new)
                .toList();
    }

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
                    .map((keyword) -> keywordRepository.findByNameIgnoreCase(keyword)
                    .orElseThrow(() -> new NotFoundException("Keyword")))
                    .toList()
            );
        }

        if (!dtoPost.getAptitudeContext().getIsUniversal()){
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

    @Transactional
    public BattleAptitudeDtoGet updateBattleAptitude(Long id, BattleAptitudeDtoPost baDtoPost){
        BattleAptitude battleAptitude = battleAptitudeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Battle Aptitude"));

        if (baDtoPost.getAptitudeContext() != null) {
            if (battleAptitude.getAptitudeContext() == null) throw new NotFoundException("Context for Battle Aptitude");
            AptitudeContext aptitudeContext = battleAptitude.getAptitudeContext();

            AptitudeContextDtoPost contextDtoPost = baDtoPost.getAptitudeContext();
            updateAptitudeContext(aptitudeContext, contextDtoPost);
        }

        if (baDtoPost.getName() != null && !baDtoPost.getName().isBlank()){
            battleAptitude.setName(baDtoPost.getName());
        }
        if (baDtoPost.getAptitudeType() != null && !baDtoPost.getAptitudeType().isBlank()){
            try {
                battleAptitude.setAptitudeType(AptitudeType.valueOf(baDtoPost.getAptitudeType().toUpperCase()));
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Illegal aptitude type " + baDtoPost.getAptitudeType());
            }
        }
        if (baDtoPost.getPhase() != null && !baDtoPost.getPhase().isBlank()){
            battleAptitude.setPhase(baDtoPost.getPhase());
        }
        if (baDtoPost.getDescription() != null && !baDtoPost.getDescription().isBlank()){
            battleAptitude.setDescription(baDtoPost.getDescription());
        }
        if (baDtoPost.getAnnouncement() != null && !baDtoPost.getAnnouncement().isBlank()){
            battleAptitude.setAnnouncement(baDtoPost.getAnnouncement());
        }
        if (baDtoPost.getEffect() != null && !baDtoPost.getEffect().isBlank()){
            battleAptitude.setEffect(baDtoPost.getEffect());
        }
        if (baDtoPost.getKeywords() != null){
            battleAptitude.setKeywords(
                    baDtoPost.getKeywords().stream()
                            .map(keyword -> keywordRepository.findByNameIgnoreCase(keyword)
                                    .orElseThrow(() -> new NotFoundException("keyword")))
                            .toList()
            );
        }
        battleAptitudeRepository.save(battleAptitude);
        return new BattleAptitudeDtoGet(battleAptitude);
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

    public boolean deleteBattleAptitude(Long id){
        BattleAptitude battleAptitude = battleAptitudeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("battle aptitude"));
        battleAptitudeRepository.delete(battleAptitude);
        return true;
    }

    //====================================== MagicPrayer ======================================
    public MagicPrayerDtoGet getMagicPrayer(Long id){
        MagicPrayer magicPrayer = magicPrayerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Magic Prayer"));
        return new MagicPrayerDtoGet(magicPrayer);
    }

    public List<MagicPrayerDtoGet> getAllMagicPrayers(){
        List<MagicPrayer> magicPrayers = (List<MagicPrayer>) magicPrayerRepository.findAll();
        return magicPrayers.stream().map(MagicPrayerDtoGet::new).toList();
    }

    @Transactional
    public MagicPrayerDtoGet addMagicPrayer(MagicPrayerDtoPost dtoPost) {
        if (dtoPost.getAptitudeContext() == null){
            throw new IllegalArgumentException("MagicPrayer must have aptitudeContext");
        }

        BattleAptitudeFactory.BattleAptitudeBundle bundle = battleAptitudeFactory.createMagicPrayer(dtoPost);
        MagicPrayer magicPrayer = (MagicPrayer) bundle.battleAptitude();
        AptitudeContext aptitudeContext = bundle.aptitudeContext();

        if (dtoPost.getKeywords() != null && !dtoPost.getKeywords().isEmpty()){
            magicPrayer.setKeywords(dtoPost.getKeywords().stream()
                    .map((keyword) -> keywordRepository.findByNameIgnoreCase(keyword.toLowerCase())
                            .orElseThrow(() -> new NotFoundException("Keyword " + keyword)))
                    .toList()
            );
        }
        if (!dtoPost.getAptitudeContext().getIsUniversal()){
            if (dtoPost.getAptitudeContext().getCharterId() != null){
                Charter charter = charterRepository.findById(dtoPost.getAptitudeContext().getCharterId())
                        .orElseThrow(() -> new NotFoundException("Charter"));
                aptitudeContext.setCharter(charter);
            }else{
                throw new IllegalArgumentException("A non-universal MagicPrayer must be linked to a charter");
            }
        }
        if (dtoPost.getDomainId() != null ){
            Domain domain = domainRepository.findById(dtoPost.getDomainId())
                    .orElseThrow(() -> new NotFoundException("Domain"));
            if (domain.isMagicalDomain() != dtoPost.getIsMagical()){
                throw new IllegalArgumentException("A spell must be attached to a magic domain and a prayer to a prayer domain (isMagical and isMagicalDomain).");
            }
            magicPrayer.setDomain(domain);
        }
        magicPrayer.setAptitudeContext(aptitudeContext);
        battleAptitudeRepository.save(magicPrayer);
        return new MagicPrayerDtoGet(magicPrayer);
    }

    @Transactional
    public MagicPrayerDtoGet updateMagicPrayer(Long id, MagicPrayerDtoPost maDtoPost){
        MagicPrayer magicPrayer = magicPrayerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("MagicPrayer"));

        if (maDtoPost.getAptitudeContext() != null) {
            if (magicPrayer.getAptitudeContext() == null) throw new NotFoundException("Context for Magic Prayer");
            AptitudeContext aptitudeContext = magicPrayer.getAptitudeContext();

            AptitudeContextDtoPost contextDtoPost = maDtoPost.getAptitudeContext();
            updateAptitudeContext(aptitudeContext, contextDtoPost);
        }

        updateMagicPrayerFields(magicPrayer, maDtoPost);

        if (maDtoPost.getAptitudeContext() != null && maDtoPost.getAptitudeContext().getCharterId() != null) {
            if (maDtoPost.getAptitudeContext().getIsUniversal()){
                throw new IllegalArgumentException("A universal MagicPrayer cannot be linked to a charter");
            } else {
                Charter charter = charterRepository.findById(maDtoPost.getAptitudeContext().getCharterId())
                        .orElseThrow(() -> new NotFoundException("Charter"));
                magicPrayer.getAptitudeContext().setCharter(charter);
            }
        }

        if (maDtoPost.getDomainId() != null){
            if (maDtoPost.getIsMagical() == null) {
                throw new IllegalArgumentException("isMagical cannot be null for update domain");
            }
            Domain domain = domainRepository.findById(maDtoPost.getDomainId())
                    .orElseThrow(() -> new NotFoundException("Domain"));
            if (domain.isMagicalDomain() != maDtoPost.getIsMagical()){
                throw new IllegalArgumentException("A spell must be attached to a magic domain and a prayer to a prayer domain (isMagical and isMagicalDomain).");
            }
            magicPrayer.setDomain(domain);
        }

        magicPrayerRepository.save(magicPrayer);
        return new MagicPrayerDtoGet(magicPrayer);
    }

    private void updateMagicPrayerFields(MagicPrayer magicPrayer, MagicPrayerDtoPost maDtoPost){
        if (maDtoPost.getName() != null && !maDtoPost.getName().isBlank()){
            magicPrayer.setName(maDtoPost.getName());
        }
        if (maDtoPost.getAptitudeType() != null && !maDtoPost.getAptitudeType().isBlank()){
            try {
                magicPrayer.setAptitudeType(AptitudeType.valueOf(maDtoPost.getAptitudeType().toUpperCase()));
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Illegal aptitude type " + maDtoPost.getAptitudeType());
            }
        }
        if (maDtoPost.getPhase() != null && !maDtoPost.getPhase().isBlank()){
            magicPrayer.setPhase(maDtoPost.getPhase());
        }
        if (maDtoPost.getDescription() != null && !maDtoPost.getDescription().isBlank()){
            magicPrayer.setDescription(maDtoPost.getDescription());
        }
        if (maDtoPost.getAnnouncement() != null && !maDtoPost.getAnnouncement().isBlank()){
            magicPrayer.setAnnouncement(maDtoPost.getAnnouncement());
        }
        if (maDtoPost.getEffect() != null && !maDtoPost.getEffect().isBlank()){
            magicPrayer.setEffect(maDtoPost.getEffect());
        }
        if (maDtoPost.getIsMagical() != null){
            magicPrayer.setMagical(maDtoPost.getIsMagical());
        }
        if (maDtoPost.getLaunchValue() != null){
            magicPrayer.setLaunchValue(maDtoPost.getLaunchValue());
        }
        if (maDtoPost.getKeywords() != null){
            magicPrayer.setKeywords(
                    maDtoPost.getKeywords().stream()
                            .map(keyword -> keywordRepository.findByNameIgnoreCase(keyword)
                                    .orElseThrow(() -> new NotFoundException("keyword")))
                            .toList()
            );
        }
    }

}
