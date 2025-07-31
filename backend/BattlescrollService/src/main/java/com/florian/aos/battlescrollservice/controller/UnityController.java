package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.unity.UnityDtoGet;
import com.florian.aos.battlescrollservice.dto.unity.UnityDtoPost;
import com.florian.aos.battlescrollservice.service.charter.UnityService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UnityDtoGet> addUnity(@Validated @RequestPart("unity") UnityDtoPost unityDtoPost,
                                                @RequestPart("image")MultipartFile image){
        return ResponseEntity.status(201).body(unityService.addUnity(unityDtoPost, image));
    }
}
