package org.rizki.mufrizal.baseline.service;

import org.rizki.mufrizal.baseline.domain.Harmonized;

import java.util.Optional;

public interface HarmonizedService {
    Optional<Harmonized> findByBackendAndBackendCode(String backend, String backendCode);

    void reload();
}
