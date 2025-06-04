package com.florian.aos.battlescrollservice.service;

import com.florian.aos.battlescrollservice.dto.version.VersionDtoGet;
import com.florian.aos.battlescrollservice.dto.version.VersionDtoPost;
import com.florian.aos.battlescrollservice.entity.Version;
import com.florian.aos.battlescrollservice.exception.NotFoundException;
import com.florian.aos.battlescrollservice.repository.VersionRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {

    private final VersionRepository versionRepository;

    public GeneralService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    //============================= Version =============================

    public VersionDtoGet addVersion(VersionDtoPost versionDtoPost) {
        Version version = Version
                .builder()
                .name(versionDtoPost.getName())
                .build();
        versionRepository.save(version);

        return new VersionDtoGet(version);
    }

    public VersionDtoGet getVersion(int id) {
        Version version = versionRepository.findById(id).orElseThrow(() -> new NotFoundException("Version"));
        return new VersionDtoGet(version);
    }
}
