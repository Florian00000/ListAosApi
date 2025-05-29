package com.florian.aos.battlescrollservice.service.charter;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoGet;
import com.florian.aos.battlescrollservice.dto.faction.FactionDtoPost;
import com.florian.aos.battlescrollservice.entity.Version;
import com.florian.aos.battlescrollservice.entity.charter.Faction;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.factory.CharterFactory;
import com.florian.aos.battlescrollservice.repository.VersionRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import org.springframework.stereotype.Service;

@Service
public class FactionService {

    private final CharterRepository charterRepository;
    private final CharterFactory charterFactory;
    private final VersionRepository versionRepository;

    public FactionService(CharterRepository charterRepository, CharterFactory charterFactory, VersionRepository versionRepository) {
        this.charterRepository = charterRepository;
        this.charterFactory = charterFactory;
        this.versionRepository = versionRepository;
    }

    //============================= Faction =============================
    public FactionDtoGet addFaction(FactionDtoPost factionDtoPost){
        Version version = versionRepository.findByName(factionDtoPost.getVersion())
                .orElseThrow(() -> new NotFoundException("Version" + factionDtoPost.getVersion()));
        Faction faction = charterFactory.fromDto(factionDtoPost, version);
        charterRepository.save(faction);
        return new FactionDtoGet(faction);
    }
}
