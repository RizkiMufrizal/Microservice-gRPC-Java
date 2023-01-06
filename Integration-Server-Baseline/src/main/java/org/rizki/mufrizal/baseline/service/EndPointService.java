package org.rizki.mufrizal.baseline.service;

import org.rizki.mufrizal.grpc.microservice.domain.EndPointProto;

public interface EndPointService {
    EndPointProto findByBackendAndBackendFunction(String backend, String backendFunction);

    void reload();
}
