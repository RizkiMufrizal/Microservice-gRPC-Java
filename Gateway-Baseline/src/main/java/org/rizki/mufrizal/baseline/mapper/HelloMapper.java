package org.rizki.mufrizal.baseline.mapper;

import lombok.SneakyThrows;
import org.rizki.mufrizal.baseline.grpcclient.IntegrationServerGrpcClient;
import org.rizki.mufrizal.baseline.mapper.harmonized.HarmonizedMapper;
import org.rizki.mufrizal.baseline.mapper.object.client.request.HelloClientRequest;
import org.rizki.mufrizal.baseline.mapper.object.client.response.HelloClientResponse;
import org.rizki.mufrizal.baseline.mapper.object.server.request.HelloServerRequest;
import org.rizki.mufrizal.baseline.mapper.object.server.response.GeneralServerResponse;
import org.rizki.mufrizal.baseline.mapper.object.server.response.HelloServerResponse;
import org.rizki.mufrizal.grpc.microservice.domain.HarmonizedProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloMapper {

    @Autowired
    private HarmonizedMapper harmonizedMapper;

    @Autowired
    private IntegrationServerGrpcClient integrationServerGrpcClient;

    @SneakyThrows
    public HelloClientRequest toHelloClientRequest(HelloServerRequest helloServerRequest) {
        return HelloClientRequest.builder()
                .hash(integrationServerGrpcClient.findByParamName("BACKEND_KEY").getParamValue())
                .message(helloServerRequest.getMessage())
                .referenceNumber(helloServerRequest.getReferenceNumber())
                .build();
    }

    public GeneralServerResponse toHelloServerResponse(String backend, HelloClientResponse helloClientResponse) {
        Object object = harmonizedMapper.getHarmonized(backend, helloClientResponse.getCode());
        if (object instanceof HarmonizedProto harmonized) {
            return harmonizedMapper.successHarmonized(
                    HelloServerResponse.builder()
                            .referenceNumber(helloClientResponse.getReferenceNumber())
                            .message(helloClientResponse.getMessage())
                            .code(harmonized.getHarmonizedCode())
                            .description(harmonized.getHarmonizedDescription())
                            .build(),
                    harmonized.getHarmonizedHttpStatus());
        }
        return (GeneralServerResponse) object;
    }
}
