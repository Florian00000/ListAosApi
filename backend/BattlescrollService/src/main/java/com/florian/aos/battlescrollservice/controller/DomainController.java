package com.florian.aos.battlescrollservice.controller;

import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoGet;
import com.florian.aos.battlescrollservice.dto.battleAptitude.DomainDtoPost;
import com.florian.aos.battlescrollservice.service.battleAptitude.DomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domains")
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomainDtoGet> getDomain(@PathVariable Long id){
        return ResponseEntity.ok(domainService.getDomain(id));
    }

    @GetMapping
    public ResponseEntity<List<DomainDtoGet>> getAllDomains(){
        return ResponseEntity.ok(domainService.getAllDomains());
    }

    @GetMapping("/find-by-charter-name/{charterName}")
    public ResponseEntity<List<DomainDtoGet>> getAllDomainsByCharterName(@PathVariable String charterName){
        return ResponseEntity.ok(domainService.getAllDomainsByCharterName(charterName));
    }

    @PostMapping("/add")
    public ResponseEntity<DomainDtoGet> addDomain(@RequestBody DomainDtoPost domainDtoPost){
        return ResponseEntity.status(201).body(domainService.addDomain(domainDtoPost));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DomainDtoGet> updateDomain(@PathVariable Long id, @RequestBody DomainDtoPost dto){
        return ResponseEntity.status(200).body(domainService.updateDomain(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDomain(@PathVariable Long id){
        domainService.deleteDomain(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
