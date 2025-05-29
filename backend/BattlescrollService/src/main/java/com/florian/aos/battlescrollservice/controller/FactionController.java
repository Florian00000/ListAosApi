package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoGet;
import com.florian.aos.battlescrollservice.dto.faction.FactionDtoPost;
import com.florian.aos.battlescrollservice.service.charter.FactionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/faction")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
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
}
