package com.florian.aos.battlescrollservice.service.charter;

import com.florian.aos.battlescrollservice.dto.unity.UnityDtoGet;
import com.florian.aos.battlescrollservice.dto.unity.UnityDtoPost;
import com.florian.aos.battlescrollservice.dto.unity.UnityDtoUpdate;
import com.florian.aos.battlescrollservice.dto.unity.WeaponDtoPost;
import com.florian.aos.battlescrollservice.entity.Keyword;
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
import com.florian.aos.battlescrollservice.service.AbstractCrudService;
import com.florian.aos.battlescrollservice.service.ImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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

    public UnityDtoGet getUnity(Long id) {
        return new UnityDtoGet(getById(id));
    }

    public List<UnityDtoGet> getAllUnits(){
        return getAll().stream().map(UnityDtoGet::new).toList();
    }

    public List<UnityDtoGet> getAllUnitsByFaction(String factionName){
        List<Unity> units = unityRepository.findAllByFactionNameIgnoreCase(factionName);
        return units.stream().map(UnityDtoGet::new).toList();
    }

    @Transactional
    public UnityDtoGet addUnity(UnityDtoPost dtoPost){
        Unity unity = prepareUnity(dtoPost);
        unity = (Unity) imageStorageService.saveImageToCharter(unity);

        unity = addKeywordsToUnity(dtoPost, unity);
        addFactionToUnity(unity, dtoPost.getFactionId());

        save(unity);
        return new UnityDtoGet(unity);
    }

    @Transactional
    public UnityDtoGet addUnity(UnityDtoPost dtoPost, MultipartFile imageFile){
        Unity unity = prepareUnity(dtoPost);
        unity = (Unity) imageStorageService.saveImageToCharter(unity, imageFile);

        unity = addKeywordsToUnity(dtoPost, unity);
        addFactionToUnity(unity, dtoPost.getFactionId());

        save(unity);
        return new UnityDtoGet(unity);
    }

    private Unity prepareUnity(UnityDtoPost dtoPost){
        if (unityRepository.existsByNameIgnoreCase(dtoPost.getName())){
            throw new ResourceAlreadyExistsException("Unity name");
        }

        Version version = versionRepository.findByName(dtoPost.getVersion())
                .orElseThrow(() -> new NotFoundException("Version " + dtoPost.getVersion()));
        Unity unity = factory.fromDto(dtoPost, version);

        if (dtoPost.getWeapons() != null && !dtoPost.getWeapons().isEmpty()){
            unity = addWeaponListToUnity( unity, dtoPost.getWeapons());
        }
        return unity;
    }

    private Unity addKeywordsToUnity(UnityDtoPost dtoPost, Unity unity){
        if (dtoPost.getKeywords() != null && !dtoPost.getKeywords().isEmpty()){
            unity.setKeywords(dtoPost.getKeywords().stream()
                    .map((keyword) -> keywordRepository.findByNameIgnoreCase(keyword)
                            .orElseThrow(() -> new NotFoundException("Keyword")))
                    .toList()
            );
        }
        return unity;
    }

    private void addFactionToUnity(Unity unity, Long factionId){
        Faction faction = factionRepository.findById(factionId)
                .orElseThrow(() -> new NotFoundException("Faction"));
        unity.setFaction(faction);
    }


    private Unity addWeaponListToUnity(Unity unity, List<WeaponDtoPost> weaponDtoPostList){
        weaponDtoPostList.forEach(weaponDtoPost -> {
            Weapon weapon = factory.weaponFromDto(weaponDtoPost);

            if (weaponDtoPost.getKeywords() != null && !weaponDtoPost.getKeywords().isEmpty()){
                List<Keyword> keywords = weaponDtoPost.getKeywords().stream()
                        .map(keyword -> keywordRepository.findByNameIgnoreCase(keyword)
                                .orElseThrow(() -> new NotFoundException("Keyword " + keyword)))
                        .toList();
                weapon.setKeywords(keywords);
            }
            unity.addWeapon(weapon);
        });
        return unity;
    }


    @Transactional
    public UnityDtoGet updateUnity(Long id, UnityDtoUpdate dto){
        Unity unity = getById(id);
        unity = updateUnityFields(unity, dto);
        unity = updateUnityFieldsWithRepo(unity, dto);

        if (dto.getImagePath() != null ){
            if (unity.getImagePath() != null && !unity.getImagePath().isBlank()) {
                imageStorageService.deleteImage(unity.getImagePath());
            }
            unity.setImagePath(dto.getImagePath());
            if (!dto.getImagePath().isBlank()){
                unity = (Unity) imageStorageService.saveImageToCharter(unity);
            }
        }
        unity = save(unity);
        return new UnityDtoGet(unity);
    }

    @Transactional
    public UnityDtoGet updateUnity(Long id, UnityDtoUpdate dto, MultipartFile file){
        Unity unity = getById(id);
        unity = updateUnityFields(unity, dto);
        unity = updateUnityFieldsWithRepo(unity, dto);

        if (unity.getImagePath() != null && !unity.getImagePath().isBlank()) {
            imageStorageService.deleteImage(unity.getImagePath());
        }
        unity = (Unity) imageStorageService.saveImageToCharter(unity, file);

        unity = save(unity);
        return new UnityDtoGet(unity);
    }

    private Unity updateUnityFields(Unity unity, UnityDtoUpdate dto){
        if (dto.getName() != null && !dto.getName().isBlank()){
            if (unityRepository.existsByNameIgnoreCase(dto.getName())){
                throw new ResourceAlreadyExistsException("Unity name");
            }
            unity.setName(dto.getName());
        }
        if (dto.getMovement() != null){
            unity.setMovement(dto.getMovement());
        }
        if (dto.getSave() != null){
            unity.setSave(dto.getSave());
        }
        if (dto.getControl() != null){
            unity.setControl(dto.getControl());
        }
        if (dto.getHealth() != null){
            unity.setHealth(dto.getHealth());
        }
        if (dto.getPoints() != null){
            unity.setPoints(dto.getPoints());
        }
        return unity;
    }

    private Unity updateUnityFieldsWithRepo(Unity unity, UnityDtoUpdate dto){
        if (dto.getKeywords() != null && !dto.getKeywords().isEmpty()){
            unity.setKeywords(dto.getKeywords().stream()
                    .map((keyword) -> keywordRepository.findByNameIgnoreCase(keyword)
                            .orElseThrow(() -> new NotFoundException("Keyword " + keyword)))
                    .collect(Collectors.toList())
            );
        }
        if (dto.getVersion() != null){
            Version version = versionRepository.findByName(dto.getVersion())
                    .orElseThrow(() -> new NotFoundException("Version " + dto.getVersion()));
            unity.setVersion(version);
        }
        if (dto.getFactionId() != null){
            addFactionToUnity(unity, dto.getFactionId());
        }
        if (dto.getWeapons() != null){
            unity.getWeapons().clear();
            unity = addWeaponListToUnity(unity, dto.getWeapons());
        }
        return unity;
    }

    public boolean deleteUnity(Long id){
        Unity unity = getById(id);
        imageStorageService.deleteImage(unity.getImagePath());
        return deleteById(id);
    }
}
