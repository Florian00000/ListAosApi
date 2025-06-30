package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoPost;
import com.florian.aos.battlescrollservice.service.BattleAptitudeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/battle-aptitudes")
public class BattleAptitudeController {

    private final BattleAptitudeService battleAptitudeService;

    public BattleAptitudeController(BattleAptitudeService battleAptitudeService) {
        this.battleAptitudeService = battleAptitudeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BattleAptitudeDtoGet> getBattleAptitude(@PathVariable Long id){
        return ResponseEntity.ok(battleAptitudeService.getBattleAptitude(id));
    }

    @GetMapping
    public ResponseEntity<List<BattleAptitudeDtoGet>> getAllBattleAptitudes(){
        return ResponseEntity.ok(battleAptitudeService.getAllBattleAptitudes());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<BattleAptitudeDtoGet> addBattleAptitude(@RequestBody BattleAptitudeDtoPost dtoPost){
        return ResponseEntity.status(201).body(battleAptitudeService.addBattleAptitude(dtoPost));
    }
}
