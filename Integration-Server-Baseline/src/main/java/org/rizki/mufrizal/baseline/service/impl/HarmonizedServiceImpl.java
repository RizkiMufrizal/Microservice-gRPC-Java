package org.rizki.mufrizal.baseline.service.impl;

import lombok.extern.log4j.Log4j2;
import org.rizki.mufrizal.baseline.domain.Harmonized;
import org.rizki.mufrizal.baseline.repository.HarmonizedRepository;
import org.rizki.mufrizal.baseline.service.HarmonizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheManager = "cacheManagerHarmonized", cacheNames = "ch_harmonized")
@Log4j2
public class HarmonizedServiceImpl implements HarmonizedService {
    @Autowired
    private HarmonizedRepository harmonizedRepository;

    @Cacheable(key = "#backend + '-' + #backendCode")
    @Override
    public Optional<Harmonized> findByBackendAndBackendCode(String backend, String backendCode) {
        return harmonizedRepository.findByBackendAndBackendCode(backend, backendCode);
    }

    @CacheEvict(allEntries = true)
    @Override
    public void reload() {
        log.info("Success Delete All Cache Harmonized");
    }
}
