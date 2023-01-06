package org.rizki.mufrizal.baseline.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.rizki.mufrizal.baseline.domain.SystemParameter;
import org.rizki.mufrizal.baseline.repository.SystemParameterRepository;
import org.rizki.mufrizal.baseline.service.SystemParameterService;
import org.rizki.mufrizal.grpc.microservice.domain.SystemParameterProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "ch_system_parameter")
@Log4j2
public class SystemParameterServiceImpl implements SystemParameterService {
    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Cacheable(key = "#paramName")
    @SneakyThrows
    @Override
    public SystemParameterProto findByParamName(String paramName) {
        SystemParameterProto systemParameterProto = null;
        Optional<SystemParameter> systemParameterOptional = systemParameterRepository.findByParamName(paramName);
        if (systemParameterOptional.isPresent()) {
            systemParameterProto = SystemParameterProto.newBuilder()
                    .setParamName(systemParameterOptional.get().getParamName())
                    .setDescription(systemParameterOptional.get().getDescription())
                    .setParamValue(systemParameterOptional.get().getParamValue())
                    .build();
        }
        return systemParameterProto;
    }

    @CacheEvict(allEntries = true)
    @Override
    public void reload() {
        log.info("Success Delete All Cache System Parameter");
    }
}
