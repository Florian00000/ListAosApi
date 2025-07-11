package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.battleAptitude.MagicPrayerDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.MagicPrayerDtoPost;
import com.florian.aos.battlescrollservice.service.battleAptitude.BattleAptitudeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spells-&-prayers")
public class MagicPrayerController {

    private final BattleAptitudeService battleAptitudeService;

    public MagicPrayerController(BattleAptitudeService battleAptitudeService) {
        this.battleAptitudeService = battleAptitudeService;
    }

    @PostMapping("/add")
    public ResponseEntity<MagicPrayerDtoGet> addMagicPrayer(@RequestBody MagicPrayerDtoPost dtoPost){
        return ResponseEntity.status(201).body(battleAptitudeService.addMagicPrayer(dtoPost));
    }
}
