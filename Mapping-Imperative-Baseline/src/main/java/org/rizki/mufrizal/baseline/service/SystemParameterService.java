package org.rizki.mufrizal.baseline.service;

import org.rizki.mufrizal.baseline.domain.SystemParameter;
import org.rizki.mufrizal.baseline.exception.ParameterNotFoundException;

public interface SystemParameterService {
    SystemParameter findByParamName(String paramName) throws ParameterNotFoundException;

    void reload();
}
