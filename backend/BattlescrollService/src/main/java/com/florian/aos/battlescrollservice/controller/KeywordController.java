package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.keyword.KeywordDtoGet;
import com.florian.aos.battlescrollservice.dto.keyword.KeywordDtoPost;
import com.florian.aos.battlescrollservice.service.KeywordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping("/{keywordId}")
    public ResponseEntity<KeywordDtoGet> getKeyword(@PathVariable Long keywordId) {
        return ResponseEntity.ok(keywordService.getKeyword(keywordId));
    }

    @GetMapping
    public ResponseEntity<List<KeywordDtoGet>> getAllKeywords() {
        return ResponseEntity.ok(keywordService.getAllKeywords());
    }

    @PostMapping("/add")
    public ResponseEntity<KeywordDtoGet> addKeyword(@RequestBody KeywordDtoPost keywordDtoPost) {
        return ResponseEntity.status(201).body(keywordService.addKeyword(keywordDtoPost));
    }

    @PutMapping("/update/{keywordId}")
    public ResponseEntity<KeywordDtoGet> updateKeyword(@PathVariable Long keywordId,
                                                       @RequestBody KeywordDtoPost keywordDtoPost) {
        return ResponseEntity.ok(keywordService.updateKeyword(keywordId, keywordDtoPost));
    }

    @DeleteMapping("/delete/{keywordId}")
    public ResponseEntity<String> deleteKeyword(@PathVariable Long keywordId) {
        keywordService.deleteKeyword(keywordId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
