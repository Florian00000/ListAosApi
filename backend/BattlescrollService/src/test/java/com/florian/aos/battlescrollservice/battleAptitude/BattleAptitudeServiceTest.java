package com.florian.aos.battlescrollservice.battleAptitude;

import com.florian.aos.battlescrollservice.dto.battleAptitude.AptitudeContextDtoPost;
import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoPost;
import com.florian.aos.battlescrollservice.entity.Keyword;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.factory.BattleAptitudeFactory;
import com.florian.aos.battlescrollservice.repository.KeywordRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.AptitudeContextRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.BattleAptitudeRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.DomainRepository;
import com.florian.aos.battlescrollservice.repository.battleAptitude.MagicPrayerRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import com.florian.aos.battlescrollservice.service.battleAptitude.BattleAptitudeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class BattleAptitudeServiceTest {

    private BattleAptitudeFactory battleAptitudeFactory;
    private final AptitudeContextRepository aptitudeContextRepository = Mockito.mock(AptitudeContextRepository.class);
    private final BattleAptitudeRepository battleAptitudeRepository = Mockito.mock(BattleAptitudeRepository.class);
    private final KeywordRepository keywordRepository =  Mockito.mock(KeywordRepository.class);
    private final CharterRepository charterRepository = Mockito.mock(CharterRepository.class);
    private BattleAptitudeService service ;
    private final DomainRepository domainRepository = Mockito.mock(DomainRepository.class);
    private final MagicPrayerRepository magicPrayerRepository = Mockito.mock(MagicPrayerRepository.class);

    @BeforeEach
    public void setUp(){
        battleAptitudeFactory = new BattleAptitudeFactory();
        service = new BattleAptitudeService(aptitudeContextRepository, battleAptitudeRepository,
                battleAptitudeFactory, keywordRepository, charterRepository, domainRepository, magicPrayerRepository
                );
    }

    @Test
    public void GivenAddBattleAptitude_WhenNoAptitudeContext_ThenThrowException(){
        //arrange
        BattleAptitudeDtoPost dtoPost = BattleAptitudeDtoPost.builder().build();

        //act & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addBattleAptitude(dtoPost));
    }

    @Test
    public void GivenAddBattleAptitude_WhenNameIsNull_ThenThrowException(){
        //arrange
        AptitudeContextDtoPost contextDtoPost = AptitudeContextDtoPost.builder().build();
        BattleAptitudeDtoPost baDtoPost = BattleAptitudeDtoPost.builder()
                .aptitudeContext(contextDtoPost)
                .keywords(List.of("keyword")).build();

        //act & assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> service.addBattleAptitude(baDtoPost));
    }

    @Test
    public void GivenAddBattleAptitude_WhenKeywordNotExist_ThenTrowException(){
        //arrange
        AptitudeContextDtoPost contextDtoPost = AptitudeContextDtoPost.builder()
                .isOptimisation(true)
                .isUniversal(true)
                .isEqualGames(true)
                .build();
        BattleAptitudeDtoPost baDtoPost = BattleAptitudeDtoPost.builder()
                .name("na")
                .phase("passif")
                .description("description")
                .announcement("announcement")
                .effect("effect")
                .aptitudeType("artefact")
                .aptitudeContext(contextDtoPost)
                .keywords(List.of("keyword")).build();
        Mockito.when(keywordRepository.findByNameIgnoreCase("keyword")).thenReturn(Optional.empty());

        //act & assert
        Assertions.assertThrows(NotFoundException.class, () -> service.addBattleAptitude(baDtoPost));
    }

    @Test
    public void GivenAddBattleAptitude_WhenIsNotUniversalAndNoCharterId_ThenThrowException(){
        //arrange
        AptitudeContextDtoPost contextDtoPost = AptitudeContextDtoPost.builder()
                .isOptimisation(true)
                .isUniversal(false)
                .isEqualGames(true)
                .build();
        BattleAptitudeDtoPost baDtoPost = BattleAptitudeDtoPost.builder()
                .name("na")
                .phase("passif")
                .description("description")
                .announcement("announcement")
                .effect("effect")
                .aptitudeType("artefact")
                .aptitudeContext(contextDtoPost)
                .keywords(List.of("keyword")).build();
        Mockito.when(keywordRepository.findByNameIgnoreCase("keyword")).thenReturn(Optional.of(Keyword.builder().build()));


        //act & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addBattleAptitude(baDtoPost));
    }

    @Test
    public void GivenAddBattleAptitude_WhenIsNotUniversalAndCharterNotExist_ThenThrowException(){
        //arrange
        AptitudeContextDtoPost contextDtoPost = AptitudeContextDtoPost.builder()
                .isOptimisation(true)
                .isUniversal(false)
                .isEqualGames(true)
                .charterId(1L)
                .build();
        BattleAptitudeDtoPost baDtoPost = BattleAptitudeDtoPost.builder()
                .name("na")
                .phase("passif")
                .description("description")
                .announcement("announcement")
                .effect("effect")
                .aptitudeType("artefact")
                .aptitudeContext(contextDtoPost)
                .keywords(List.of("keyword")).build();
        Mockito.when(keywordRepository.findByNameIgnoreCase("keyword")).thenReturn(Optional.of(Keyword.builder().build()));
        Mockito.when(charterRepository.findById(1L)).thenReturn(Optional.empty());

        //act & assert
        Assertions.assertThrows(NotFoundException.class, () -> service.addBattleAptitude(baDtoPost));
    }
}