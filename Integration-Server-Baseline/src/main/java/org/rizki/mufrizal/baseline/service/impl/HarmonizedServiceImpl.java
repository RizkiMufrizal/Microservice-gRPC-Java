package org.rizki.mufrizal.baseline.service.impl;

import lombok.extern.log4j.Log4j2;
import org.rizki.mufrizal.baseline.domain.Harmonized;
import org.rizki.mufrizal.baseline.repository.HarmonizedRepository;
import org.rizki.mufrizal.baseline.service.HarmonizedService;
import org.rizki.mufrizal.grpc.microservice.domain.HarmonizedProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "ch_harmonized")
@Log4j2
public class HarmonizedServiceImpl implements HarmonizedService {
    @Autowired
    private HarmonizedRepository harmonizedRepository;

    @Cacheable(key = "#backend + '-' + #backendCode")
    @Override
    public HarmonizedProto findByBackendAndBackendCode(String backend, String backendCode) {
        HarmonizedProto harmonizedProto = null;
        Optional<Harmonized> harmonizedOptional = harmonizedRepository.findByBackendAndBackendCode(backend, backendCode);
        if (harmonizedOptional.isPresent()) {
            harmonizedProto = HarmonizedProto.newBuilder()
                    .setId(harmonizedOptional.get().getId())
                    .setBackend(harmonizedOptional.get().getBackend())
                    .setBackendCode(harmonizedOptional.get().getBackendCode())
                    .setBackendDescription(harmonizedOptional.get().getBackendDescription())
                    .setHarmonizedCode(harmonizedOptional.get().getHarmonizedCode())
                    .setHarmonizedDescription(harmonizedOptional.get().getHarmonizedDescription())
                    .setHarmonizedHttpStatus(harmonizedOptional.get().getHarmonizedHttpStatus())
                    .setIsError(harmonizedOptional.get().getIsError())
                    .build();
        }
        return harmonizedProto;
    }

    @CacheEvict(allEntries = true)
    @Override
    public void reload() {
        log.info("Success Delete All Cache Harmonized");
    }
}
