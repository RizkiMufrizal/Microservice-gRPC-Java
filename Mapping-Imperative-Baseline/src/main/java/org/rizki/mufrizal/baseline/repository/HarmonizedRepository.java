package org.rizki.mufrizal.baseline.repository;

import org.rizki.mufrizal.baseline.domain.Harmonized;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface HarmonizedRepository extends PagingAndSortingRepository<Harmonized, String> {
    Optional<Harmonized> findByBackendAndBackendCode(String backend, String backendCode);
}
