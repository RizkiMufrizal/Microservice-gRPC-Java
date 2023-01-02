package org.rizki.mufrizal.baseline.mapper;

import lombok.SneakyThrows;
import org.rizki.mufrizal.baseline.domain.Harmonized;
import org.rizki.mufrizal.baseline.mapper.harmonized.HarmonizedMapper;
import org.rizki.mufrizal.baseline.mapper.object.client.request.HelloClientRequest;
import org.rizki.mufrizal.baseline.mapper.object.client.response.HelloClientResponse;
import org.rizki.mufrizal.baseline.mapper.object.server.request.HelloServerRequest;
import org.rizki.mufrizal.baseline.mapper.object.server.response.GeneralServerResponse;
import org.rizki.mufrizal.baseline.mapper.object.server.response.HelloServerResponse;
import org.rizki.mufrizal.baseline.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloMapper {

    @Autowired
    private HarmonizedMapper harmonizedMapper;

    @Autowired
    private SystemParameterService systemParameterService;

    @SneakyThrows
    public HelloClientRequest toHelloClientRequest(HelloServerRequest helloServerRequest) {
        return HelloClientRequest.builder()
                .hash(systemParameterService.findByParamName("BACKEND_KEY").getParamValue())
                .message(helloServerRequest.getMessage())
                .referenceNumber(helloServerRequest.getReferenceNumber())
                .build();
    }

    public GeneralServerResponse toHelloServerResponse(String backend, HelloClientResponse helloClientResponse) {
        Object object = harmonizedMapper.getHarmonized(backend, helloClientResponse.getCode());
        if (object instanceof Harmonized harmonized) {
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
