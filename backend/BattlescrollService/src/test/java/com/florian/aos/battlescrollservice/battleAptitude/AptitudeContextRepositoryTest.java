package com.florian.aos.battlescrollservice.battleAptitude;

import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.charter.Charter;
import com.florian.aos.battlescrollservice.entity.charter.Faction;
import com.florian.aos.battlescrollservice.repository.battleAptitude.AptitudeContextRepository;
import com.florian.aos.battlescrollservice.repository.charter.CharterRepository;
import com.florian.aos.battlescrollservice.utils.enums.AllianceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AptitudeContextRepositoryTest {

    @Autowired
    private AptitudeContextRepository aptitudeContextRepository;
    @Autowired
    private CharterRepository charterRepository;


    @Test
    public void testFindAllByCharterNameIgnoreCase(){
        //arrange
        Charter charter = Faction.builder()
                .name("CharterTest")
                .alliance(AllianceType.ORDER)
                .build();
        charterRepository.save(charter);

        AptitudeContext aptitudeContext = AptitudeContext.builder()
                .isOptimisation(false)
                .isUniversal(false)
                .isEqualGames(true)
                .charter(charter)
                .build();
        aptitudeContextRepository.save(aptitudeContext);

        //act
        List<AptitudeContext> results = aptitudeContextRepository.findAllByCharterNameIgnoreCase("CharterTest");

        //assert
        Assertions.assertThat(results)
                .isNotEmpty()
                .hasSize(1)
                .first()
                .extracting(AptitudeContext::getCharter)
                .extracting(Charter::getName)
                .isEqualTo("CharterTest");
    }
}
