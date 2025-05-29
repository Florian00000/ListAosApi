package com.florian.aos.battlescrollservice.charter;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoPost;
import com.florian.aos.battlescrollservice.entity.Version;
import com.florian.aos.battlescrollservice.entity.charter.Faction;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.factory.CharterFactory;
import com.florian.aos.battlescrollservice.repository.VersionRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import com.florian.aos.battlescrollservice.repository.charter.FactionRepository;
import com.florian.aos.battlescrollservice.service.charter.FactionService;
import com.florian.aos.battlescrollservice.utils.enums.AllianceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class FactionServiceTest {

    private FactionService fs;
    private final CharterRepository charterRepository = Mockito.mock(CharterRepository.class);
    private CharterFactory charterFactory;
    private final VersionRepository versionRepository  = Mockito.mock(VersionRepository.class);
    private final FactionRepository factionRepository = Mockito.mock(FactionRepository.class);

    @BeforeEach
    public void setUp() {
        charterFactory = new CharterFactory();
        fs = new FactionService(charterRepository, charterFactory, versionRepository, factionRepository);
    }

    @Test
    public void GivenAddFaction_WhenVersionNotExist_ThenThrowException() {
        //arrange
        FactionDtoPost factionDtoPost = FactionDtoPost
                .builder()
                .version("x")
                .build();

        //act & assert
        Assertions.assertThrows(NotFoundException.class, () -> fs.addFaction(factionDtoPost));
    }

    @Test
    public void GivenAddFaction_WhenNameIsNull_ThenThrowException() {
        //arrange
        FactionDtoPost factionDtoPost = FactionDtoPost
                .builder()
                .version("v4")
                .alliance("death")
                .build();
        Version version = Version.builder().name("v4").build();
        Mockito.when(versionRepository.findByName("v4")).thenReturn(Optional.of(version));


        //act & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> fs.addFaction(factionDtoPost));
    }

    @Test
    public void GivenAddFaction_ThenSaveFaction(){
        //arrange
        FactionDtoPost factionDtoPost = FactionDtoPost
                .builder()
                .name("Faction Name")
                .version("v4")
                .alliance("death")
                .build();
        Version version = Version.builder().name("v4").build();
        Faction faction = Faction.builder()
                .id(1L)
                .name("Faction Name")
                .version(version)
                .alliance(AllianceType.DEATH)
                .build();

        Mockito.when(versionRepository.findByName("v4")).thenReturn(Optional.of(version));

        //act
        fs.addFaction(factionDtoPost);

        //assert
        Mockito.verify(charterRepository, Mockito.times(1)).save(Mockito.any(Faction.class));
    }

    @Test
    public void GivenGetFaction_WhenIdNotExist_ThenThrowException() {
        Assertions.assertThrows(NotFoundException.class, () -> fs.getFaction(1L));
    }

}


