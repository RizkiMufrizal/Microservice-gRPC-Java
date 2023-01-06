package org.rizki.mufrizal.baseline.service;

import org.rizki.mufrizal.grpc.microservice.domain.SystemParameterProto;

public interface SystemParameterService {
    SystemParameterProto findByParamName(String paramName);

    void reload();
}
