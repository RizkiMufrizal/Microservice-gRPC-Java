package org.rizki.mufrizal.baseline.service;

import org.rizki.mufrizal.grpc.microservice.domain.HarmonizedProto;

public interface HarmonizedService {
    HarmonizedProto findByBackendAndBackendCode(String backend, String backendCode);

    void reload();
}
