package org.rizki.mufrizal.baseline.mapper.harmonized;

import org.rizki.mufrizal.baseline.grpcclient.IntegrationServerGrpcClient;
import org.rizki.mufrizal.baseline.helper.Constant;
import org.rizki.mufrizal.baseline.mapper.object.server.response.GeneralServerResponse;
import org.rizki.mufrizal.grpc.microservice.domain.HarmonizedProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HarmonizedMapper {

    @Autowired
    private IntegrationServerGrpcClient integrationServerGrpcClient;

    public Object getHarmonized(String backend, String backendCode) {
        Optional<HarmonizedProto> harmonized = integrationServerGrpcClient.findByBackendAndBackendCode(backend, backendCode);
        if (harmonized.isPresent()) {
            if (!harmonized.get().getIsError()) {
                return harmonized.get();
            }
            return this.errorHarmonized(harmonized.get());
        }
        return this.defaultHarmonized();
    }

    private GeneralServerResponse defaultHarmonized() {
        return GeneralServerResponse.builder()
                .body(GeneralHarmonizedResponse.builder()
                        .code(Constant.DEFAULT_HARMONIZED_CODE)
                        .description(Constant.DEFAULT_HARMONIZED_DESC)
                        .build()
                )
                .httpStatus(Constant.DEFAULT_HARMONIZED_HTTP_STATUS)
                .build();
    }

    private GeneralServerResponse errorHarmonized(HarmonizedProto harmonized) {
        return GeneralServerResponse.builder()
                .body(GeneralHarmonizedResponse.builder()
                        .code(harmonized.getHarmonizedCode())
                        .description(harmonized.getHarmonizedDescription())
                        .build()
                )
                .httpStatus(harmonized.getHarmonizedHttpStatus())
                .build();
    }

    public GeneralServerResponse successHarmonized(Object object, Integer httpStatus) {
        return GeneralServerResponse.builder()
                .body(object)
                .httpStatus(httpStatus)
                .build();
    }
}
