package org.rizki.mufrizal.baseline.repository;

import org.rizki.mufrizal.baseline.domain.SystemParameter;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SystemParameterRepository extends PagingAndSortingRepository<SystemParameter, String> {
    Optional<SystemParameter> findByParamName(String paramName);
}
