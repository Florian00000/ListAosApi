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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.florian.aos.battlescrollservice.utils.StaticMethods.saveImage;
import static com.florian.aos.battlescrollservice.utils.StaticMethods.saveImageBase64;

@Service
public class FactionService {

    private final CharterRepository charterRepository;
    private final CharterFactory charterFactory;
    private final VersionRepository versionRepository;
    private final FactionRepository factionRepository;

    public FactionService(CharterRepository charterRepository,
                          CharterFactory charterFactory,
                          VersionRepository versionRepository,
                          FactionRepository factionRepository
                          ) {
        this.charterRepository = charterRepository;
        this.charterFactory = charterFactory;
        this.versionRepository = versionRepository;
        this.factionRepository = factionRepository;
    }

    private Faction prepareFaction (FactionDtoPost dto){
        Version version = versionRepository.findByName(dto.getVersion())
                .orElseThrow(() -> new NotFoundException("Version " + dto.getVersion()));
        return charterFactory.fromDto(dto, version);
    }

    public FactionDtoGet addFaction(FactionDtoPost factionDtoPost){
        Faction faction = prepareFaction(factionDtoPost);

        if (factionDtoPost.getImagePath() != null) {
            try {
                String imagePath = saveImageBase64(factionDtoPost.getImagePath());
                faction.setImagePath(imagePath);
            }catch (IOException e){
                throw new IllegalArgumentException("Image path could not be saved");
            }
        }
        charterRepository.save(faction);
        return new FactionDtoGet(faction);
    }

    public FactionDtoGet addFaction(FactionDtoPost factionDtoPost, MultipartFile imageFile){
        Faction faction = prepareFaction(factionDtoPost);
        try {
            String imagePath = saveImage(imageFile);
            faction.setImagePath(imagePath);
        }catch (IOException e){
            throw new IllegalArgumentException("Image path could not be saved");
        }

        charterRepository.save(faction);
        return new FactionDtoGet(faction);
    }

    public FactionDtoGet getFaction(Long id){
        Faction faction = factionRepository.findById(id).orElseThrow(() -> new NotFoundException("Faction"));
        return new FactionDtoGet(faction);
    }

    public List<FactionDtoGet> getAllFactions(){
        List<Faction> factions = (List<Faction>) factionRepository.findAll();
        return factions.stream().map(FactionDtoGet::new).toList();
    }
}
