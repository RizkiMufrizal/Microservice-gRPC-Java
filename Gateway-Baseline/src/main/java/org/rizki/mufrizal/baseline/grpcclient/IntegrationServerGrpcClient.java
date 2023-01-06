package org.rizki.mufrizal.baseline.grpcclient;

import lombok.SneakyThrows;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.rizki.mufrizal.baseline.exception.ParameterNotFoundException;
import org.rizki.mufrizal.grpc.microservice.domain.EndPointProto;
import org.rizki.mufrizal.grpc.microservice.domain.HarmonizedProto;
import org.rizki.mufrizal.grpc.microservice.domain.SystemParameterProto;
import org.rizki.mufrizal.grpc.microservice.service.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IntegrationServerGrpcClient {

    @GrpcClient("integration-server-grpc")
    private EndPointServiceGrpc.EndPointServiceBlockingStub endPointServiceBlockingStub;

    @GrpcClient("integration-server-grpc")
    private HarmonizedServiceGrpc.HarmonizedServiceBlockingStub harmonizedServiceBlockingStub;

    @GrpcClient("integration-server-grpc")
    private SystemParameterServiceGrpc.SystemParameterServiceBlockingStub systemParameterServiceBlockingStub;

    @SneakyThrows
    @Cacheable(key = "#backend + '-' + #backendFunction", cacheNames = "ch_end_point")
    public EndPointProto findByBackendAndBackendFunction(String backend, String backendFunction) {
        BackendAndBackendFunction backendAndBackendFunction = BackendAndBackendFunction.newBuilder()
                .setBackend(backend)
                .setBackendFunction(backendFunction)
                .build();
        EndPointProto endPointProto = endPointServiceBlockingStub.findByBackendAndBackendFunction(backendAndBackendFunction);
        if (endPointProto != null) {
            return endPointProto;
        }

        throw new ParameterNotFoundException("End Point " + backend + "-" + backendFunction + " Not Found");
    }

    @Cacheable(key = "#backend + '-' + #backendCode", cacheNames = "ch_harmonized")
    public Optional<HarmonizedProto> findByBackendAndBackendCode(String backend, String backendCode) {
        BackendAndBackendCode backendAndBackendCode = BackendAndBackendCode.newBuilder()
                .setBackend(backend)
                .setBackendCode(backendCode)
                .build();
        HarmonizedProto harmonizedProto = harmonizedServiceBlockingStub.findByBackendAndBackendCode(backendAndBackendCode);
        return harmonizedProto != null ? Optional.of(harmonizedProto) : Optional.empty();
    }

    @SneakyThrows
    @Cacheable(key = "#paramName", cacheNames = "ch_system_parameter")
    public SystemParameterProto findByParamName(String paramName) {
        ParamName paramNameObject = ParamName.newBuilder()
                .setParamName(paramName)
                .build();
        SystemParameterProto systemParameterProto = systemParameterServiceBlockingStub.findByParamName(paramNameObject);
        if (systemParameterProto != null) {
            return systemParameterProto;
        }
        throw new ParameterNotFoundException("System Parameter " + paramName + " Not Found");
    }
}