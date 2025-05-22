package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoGet;
import com.florian.aos.battlescrollservice.dto.faction.FactionDtoPost;
import com.florian.aos.battlescrollservice.service.CharterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/faction")
public class FactionController {

    private final CharterService charterService;

    public FactionController(CharterService charterService) {
        this.charterService = charterService;
    }

    @PostMapping("/add")
    public ResponseEntity<FactionDtoGet> addFaction(@RequestBody FactionDtoPost factionDtoPost) {
        return ResponseEntity.status(201).body(charterService.addFaction(factionDtoPost));
    }
}
