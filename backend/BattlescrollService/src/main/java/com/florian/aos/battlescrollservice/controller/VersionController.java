package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.version.VersionDtoGet;
import com.florian.aos.battlescrollservice.dto.version.VersionDtoPost;
import com.florian.aos.battlescrollservice.service.GeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    private final GeneralService generalService;

    public VersionController(GeneralService generalService) {
        this.generalService = generalService;
    }

    @PostMapping("/add")
    public ResponseEntity<VersionDtoGet> addVersion(@RequestBody VersionDtoPost versionDtoPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(generalService.addVersion(versionDtoPost));
    }

    @GetMapping("/{versionId}")
    public ResponseEntity<VersionDtoGet> getVersion(@PathVariable Integer versionId) {
        return ResponseEntity.ok(generalService.getVersion(versionId));
    }
}
