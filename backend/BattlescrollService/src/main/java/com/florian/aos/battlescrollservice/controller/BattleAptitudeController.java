package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.BattleAptitudeDtoPost;
import com.florian.aos.battlescrollservice.service.battleAptitude.BattleAptitudeService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/find-by-charter-name/{charterName}")
    public ResponseEntity<List<BattleAptitudeDtoGet>> getAllBattleAptitudesByCharterName(@PathVariable String charterName){
        return ResponseEntity.ok(battleAptitudeService.getAllBattleAptitudesByCharterName(charterName));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<BattleAptitudeDtoGet> addBattleAptitude(@RequestBody BattleAptitudeDtoPost dtoPost){
        return ResponseEntity.status(201).body(battleAptitudeService.addBattleAptitude(dtoPost));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BattleAptitudeDtoGet> updateBattleAptitude(@PathVariable Long id,
                                                                     @RequestBody BattleAptitudeDtoPost dto){
        return ResponseEntity.status(200).body(battleAptitudeService.updateBattleAptitude(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBattleAptitude(@PathVariable Long id){
        battleAptitudeService.deleteBattleAptitude(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/attach/{idBa}/charter/{idCharter}")
    public ResponseEntity<BattleAptitudeDtoGet> attachBattleAptitudeToCharter(@PathVariable Long idBa,
                                                                              @PathVariable Long idCharter){
        return ResponseEntity.ok(battleAptitudeService.addBattleAptitudeToCharter(idBa, idCharter));
    }

    @PatchMapping("/detach-to-charter/{idBa}")
    public ResponseEntity<BattleAptitudeDtoGet> detachBattleAptitudeToCharter(@PathVariable Long idBa){
        return ResponseEntity.ok(battleAptitudeService.detachBattleAptitudeToCharter(idBa));
    }
}
