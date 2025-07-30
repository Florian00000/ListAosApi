package com.florian.aos.battlescrollservice.service.charter;

import com.florian.aos.battlescrollservice.dto.unity.UnityDtoGet;
import com.florian.aos.battlescrollservice.dto.unity.UnityDtoPost;
import com.florian.aos.battlescrollservice.entity.Version;
import com.florian.aos.battlescrollservice.entity.charter.Faction;
import com.florian.aos.battlescrollservice.entity.charter.Unity;
import com.florian.aos.battlescrollservice.entity.charter.Weapon;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.exception.ResourceAlreadyExistsException;
import com.florian.aos.battlescrollservice.factory.CharterFactory;
import com.florian.aos.battlescrollservice.repository.KeywordRepository;
import com.florian.aos.battlescrollservice.repository.VersionRepository;
import com.florian.aos.battlescrollservice.repository.charter.FactionRepository;
import com.florian.aos.battlescrollservice.repository.charter.UnityRepository;
import com.florian.aos.battlescrollservice.repository.charter.WeaponRepository;
import com.florian.aos.battlescrollservice.service.AbstractCrudService;
import com.florian.aos.battlescrollservice.service.ImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnityService extends AbstractCrudService<Unity, Long> {

    private final UnityRepository unityRepository;
    private final CharterFactory factory;
    private final KeywordRepository keywordRepository;
    private final ImageStorageService imageStorageService;
    private final VersionRepository versionRepository;
    private final FactionRepository factionRepository;

    public UnityService(UnityRepository unityRepository, CharterFactory factory, KeywordRepository keywordRepository,
                        ImageStorageService imageStorageService, VersionRepository versionRepository,
                        FactionRepository factionRepository) {
        super(unityRepository, "Unity");
        this.unityRepository = unityRepository;
        this.factory = factory;
        this.keywordRepository = keywordRepository;
        this.imageStorageService = imageStorageService;
        this.versionRepository = versionRepository;
        this.factionRepository = factionRepository;
    }

    @Transactional
    public UnityDtoGet addUnity(UnityDtoPost dtoPost){
        if (unityRepository.existsByNameIgnoreCase(dtoPost.getName())){
            throw new ResourceAlreadyExistsException("Unity name");
        }

        Version version = versionRepository.findByName(dtoPost.getVersion())
                .orElseThrow(() -> new NotFoundException("Version " + dtoPost.getVersion()));
        CharterFactory.UnityBundle bundle = factory.fromDto(dtoPost, version);
        Unity unity = bundle.unity();
        List<Weapon> weaponList = bundle.weapons();
        weaponList.forEach(unity::addWeapon);

        unity = (Unity) imageStorageService.saveImageToCharter(unity);

        if (dtoPost.getKeywords() != null && !dtoPost.getKeywords().isEmpty()){
            unity.setKeywords(dtoPost.getKeywords().stream()
                    .map((keyword) -> keywordRepository.findByNameIgnoreCase(keyword)
                            .orElseThrow(() -> new NotFoundException("Keyword")))
                    .toList()
            );
        }

        Faction faction = factionRepository.findById(dtoPost.getFactionId())
                .orElseThrow(() -> new NotFoundException("Faction"));
        unity.setFaction(faction);

        save(unity);
        return new UnityDtoGet(unity);
    }
}
