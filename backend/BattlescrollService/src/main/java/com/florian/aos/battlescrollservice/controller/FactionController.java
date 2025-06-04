package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoGet;
import com.florian.aos.battlescrollservice.dto.faction.FactionDtoPost;
import com.florian.aos.battlescrollservice.service.charter.FactionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/factions")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping("/{factionId}")
    public ResponseEntity<FactionDtoGet> getFaction(@PathVariable Long factionId) {
        return ResponseEntity.ok(factionService.getFaction(factionId));
    }

    @GetMapping
    public ResponseEntity<List<FactionDtoGet>> getAllFactions() {
        return ResponseEntity.ok(factionService.getAllFactions());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<FactionDtoGet> addFaction(@RequestBody FactionDtoPost factionDtoPost) {
        return ResponseEntity.status(201).body(factionService.addFaction(factionDtoPost));
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FactionDtoGet> addFaction(
            @RequestPart("faction") FactionDtoPost factionDtoPost,
            @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(201).body(factionService.addFaction(factionDtoPost, image));
    }

    @PutMapping("/update/{factionId}")
    public ResponseEntity<FactionDtoGet> updateFaction(@PathVariable Long factionId,
                                                       @RequestBody FactionDtoPost factionDtoPost) {
        return ResponseEntity.status(200).body(factionService.updateFaction(factionId, factionDtoPost));
    }

    @PutMapping(value = "/update/{factionId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FactionDtoGet> updateFaction(@PathVariable Long factionId,
                                                       @RequestPart("faction") FactionDtoPost factionDtoPost,
                                                       @RequestPart("image") MultipartFile image){
        return ResponseEntity.status(200).body(factionService.updateFaction(factionId, factionDtoPost, image));
    }
}
