package org.rizki.mufrizal.baseline.service;

import org.rizki.mufrizal.baseline.domain.EndPoint;

public interface EndPointService {
    EndPoint findByBackendAndBackendFunction(String backend, String backendFunction);

    void reload();
}
