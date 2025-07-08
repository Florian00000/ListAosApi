package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoPost;
import com.florian.aos.battlescrollservice.service.battleAptitude.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/domains")
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @PostMapping("/add")
    public ResponseEntity<DomainDtoGet> addDomain(@RequestBody DomainDtoPost domainDtoPost){
        return ResponseEntity.status(201).body(domainService.addDomain(domainDtoPost));
    }
}
