package com.florian.aos.battlescrollservice.service;

import com.florian.aos.battlescrollservice.dto.keyword.KeywordDtoGet;
import com.florian.aos.battlescrollservice.dto.keyword.KeywordDtoPost;
import com.florian.aos.battlescrollservice.entity.Keyword;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.repository.KeywordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public KeywordDtoGet getKeyword(Long id) {
        Keyword keyword = keywordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Keyword"));
        return new KeywordDtoGet(keyword);
    }

    public List<KeywordDtoGet> getAllKeywords() {
        List<Keyword> keywords = (List<Keyword>) keywordRepository.findAll();
        return keywords.stream().map(KeywordDtoGet::new).toList();
    }

    public KeywordDtoGet addKeyword(KeywordDtoPost keywordDtoPost){
        Keyword keyword = Keyword.builder().name(keywordDtoPost.getName()).build();
        keywordRepository.save(keyword);
        return new KeywordDtoGet(keyword);
    }

    public KeywordDtoGet updateKeyword(Long id, KeywordDtoPost keywordDtoPost){
        Keyword keyword = keywordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Keyword"));
        keyword.setName(keywordDtoPost.getName());
        keywordRepository.save(keyword);
        return new KeywordDtoGet(keyword);
    }

    public boolean deleteKeyword(Long id){
        Keyword keyword = keywordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Keyword"));
        keywordRepository.delete(keyword);
        return true;
    }
}
