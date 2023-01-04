package org.rizki.mufrizal.baseline.service;

import org.rizki.mufrizal.baseline.domain.EndPoint;

import java.util.Optional;

public interface EndPointService {
    Optional<EndPoint> findByBackendAndBackendFunction(String backend, String backendFunction);

    void reload();
}
