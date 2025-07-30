package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.unity.UnityDtoGet;
import com.florian.aos.battlescrollservice.dto.unity.UnityDtoPost;
import com.florian.aos.battlescrollservice.service.charter.UnityService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/units")
public class UnityController {

    private final UnityService unityService;

    public UnityController(UnityService unityService) {
        this.unityService = unityService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<UnityDtoGet> addUnity(@Validated @RequestBody UnityDtoPost unityDtoPost){
        return ResponseEntity.status(201).body(unityService.addUnity(unityDtoPost));
    }
}
