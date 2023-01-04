package org.rizki.mufrizal.baseline.service;

import org.rizki.mufrizal.baseline.domain.SystemParameter;

import java.util.Optional;

public interface SystemParameterService {
    Optional<SystemParameter> findByParamName(String paramName);

    void reload();
}
