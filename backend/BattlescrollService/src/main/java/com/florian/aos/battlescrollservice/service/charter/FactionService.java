package com.florian.aos.battlescrollservice.service.charter;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoGet;
import com.florian.aos.battlescrollservice.dto.faction.FactionDtoPost;
import com.florian.aos.battlescrollservice.entity.Version;
import com.florian.aos.battlescrollservice.entity.charter.Faction;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.factory.CharterFactory;
import com.florian.aos.battlescrollservice.repository.VersionRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import com.florian.aos.battlescrollservice.repository.charter.FactionRepository;
import com.florian.aos.battlescrollservice.service.ImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class FactionService {

    private final CharterRepository charterRepository;
    private final CharterFactory charterFactory;
    private final VersionRepository versionRepository;
    private final FactionRepository factionRepository;
    private final ImageStorageService imageStorageService;

    public FactionService(CharterRepository charterRepository,
                          CharterFactory charterFactory,
                          VersionRepository versionRepository,
                          FactionRepository factionRepository,
                          ImageStorageService imageStorageService
                          ) {
        this.charterRepository = charterRepository;
        this.charterFactory = charterFactory;
        this.versionRepository = versionRepository;
        this.factionRepository = factionRepository;
        this.imageStorageService = imageStorageService;
    }

    public FactionDtoGet getFaction(Long id){
        Faction faction = factionRepository.findById(id).orElseThrow(() -> new NotFoundException("Faction"));
        return new FactionDtoGet(faction);
    }

    public List<FactionDtoGet> getAllFactions(){
        List<Faction> factions = (List<Faction>) factionRepository.findAll();
        return factions.stream().map(FactionDtoGet::new).toList();
    }

    private Faction prepareFaction (FactionDtoPost dto){
        Version version = versionRepository.findByName(dto.getVersion())
                .orElseThrow(() -> new NotFoundException("Version " + dto.getVersion()));
        return charterFactory.fromDto(dto, version);
    }

    public FactionDtoGet addFaction(FactionDtoPost factionDtoPost){
        Faction faction = prepareFaction(factionDtoPost);
        faction = (Faction)imageStorageService.saveImageToCharter(faction);

        charterRepository.save(faction);
        return new FactionDtoGet(faction);
    }

    public FactionDtoGet addFaction(FactionDtoPost factionDtoPost, MultipartFile imageFile){
        Faction faction = prepareFaction(factionDtoPost);
        faction = (Faction)imageStorageService.saveImageToCharter(faction, imageFile);

        charterRepository.save(faction);
        return new FactionDtoGet(faction);
    }

    public FactionDtoGet updateFaction(Long id, FactionDtoPost factionDtoPost){
        Faction faction = factionRepository.findById(id).orElseThrow(() -> new NotFoundException("Faction"));
        Faction factionToUpdate = prepareFaction(factionDtoPost);

        return null;
    }




}
