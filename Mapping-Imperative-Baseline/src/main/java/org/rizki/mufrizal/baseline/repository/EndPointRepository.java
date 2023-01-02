package org.rizki.mufrizal.baseline.repository;

import org.rizki.mufrizal.baseline.domain.EndPoint;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EndPointRepository extends PagingAndSortingRepository<EndPoint, String> {
    Optional<EndPoint> findByBackendAndBackendFunction(String backend, String backendFunction);
}
