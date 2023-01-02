package org.rizki.mufrizal.baseline.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.rizki.mufrizal.baseline.domain.SystemParameter;
import org.rizki.mufrizal.baseline.exception.ParameterNotFoundException;
import org.rizki.mufrizal.baseline.repository.SystemParameterRepository;
import org.rizki.mufrizal.baseline.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheManager = "cacheManagerSystemParameter", cacheNames = "ch_system_parameter")
@Log4j2
public class SystemParameterServiceImpl implements SystemParameterService {
    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Cacheable(key = "#paramName")
    @SneakyThrows
    @Override
    public SystemParameter findByParamName(String paramName) {
        return systemParameterRepository.findByParamName(paramName).orElseThrow(() -> new ParameterNotFoundException("System Parameter " + paramName + " Not Found"));
    }

    @CacheEvict(allEntries = true)
    @Override
    public void reload() {
        log.info("Success Delete All Cache System Parameter");
    }
}
