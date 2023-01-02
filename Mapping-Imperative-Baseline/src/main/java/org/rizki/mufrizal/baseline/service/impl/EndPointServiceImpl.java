package org.rizki.mufrizal.baseline.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.rizki.mufrizal.baseline.domain.EndPoint;
import org.rizki.mufrizal.baseline.exception.ParameterNotFoundException;
import org.rizki.mufrizal.baseline.repository.EndPointRepository;
import org.rizki.mufrizal.baseline.service.EndPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheManager = "cacheManagerEndPoint", cacheNames = "ch_end_point")
@Log4j2
public class EndPointServiceImpl implements EndPointService {
    @Autowired
    private EndPointRepository endPointRepository;

    @Cacheable(key = "#backend + '-' + #backendFunction")
    @SneakyThrows
    @Override
    public EndPoint findByBackendAndBackendFunction(String backend, String backendFunction) {
        return endPointRepository.findByBackendAndBackendFunction(backend, backendFunction).orElseThrow(() -> new ParameterNotFoundException("End Point " + backend + "-" + backendFunction + " Not Found"));
    }

    @CacheEvict(allEntries = true)
    @Override
    public void reload() {
        log.info("Success Delete All Cache End Point");
    }
}
