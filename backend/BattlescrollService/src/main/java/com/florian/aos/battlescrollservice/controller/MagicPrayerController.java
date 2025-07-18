package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.battleAptitude.MagicPrayerDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.MagicPrayerDtoPost;
import com.florian.aos.battlescrollservice.service.battleAptitude.BattleAptitudeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spells-&-prayers")
public class MagicPrayerController {

    private final BattleAptitudeService battleAptitudeService;

    public MagicPrayerController(BattleAptitudeService battleAptitudeService) {
        this.battleAptitudeService = battleAptitudeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MagicPrayerDtoGet> getMagicPrayer(@PathVariable Long id){
        return ResponseEntity.ok(battleAptitudeService.getMagicPrayer(id));
    }

    @GetMapping
    public ResponseEntity<List<MagicPrayerDtoGet>> getAllMagicPrayers(){
        return ResponseEntity.ok(battleAptitudeService.getAllMagicPrayers());
    }

    @PostMapping("/add")
    public ResponseEntity<MagicPrayerDtoGet> addMagicPrayer(@RequestBody MagicPrayerDtoPost dtoPost){
        return ResponseEntity.status(201).body(battleAptitudeService.addMagicPrayer(dtoPost));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MagicPrayerDtoGet> updateMagicPrayer(@PathVariable Long id, @RequestBody MagicPrayerDtoPost dto){
        return ResponseEntity.status(200).body(battleAptitudeService.updateMagicPrayer(id, dto));
    }
}
