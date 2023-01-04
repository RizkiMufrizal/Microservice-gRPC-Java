package org.rizki.mufrizal.baseline.controller.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.rizki.mufrizal.baseline.domain.Harmonized;
import org.rizki.mufrizal.baseline.service.HarmonizedService;
import org.rizki.mufrizal.grpc.microservice.domain.HarmonizedProto;
import org.rizki.mufrizal.grpc.microservice.service.BackendAndBackendCode;
import org.rizki.mufrizal.grpc.microservice.service.HarmonizedServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
public class HarmonizedController extends HarmonizedServiceGrpc.HarmonizedServiceImplBase {

    @Autowired
    private HarmonizedService harmonizedService;

    @Override
    public void findByBackendAndBackendCode(BackendAndBackendCode request, StreamObserver<HarmonizedProto> responseObserver) {
        HarmonizedProto harmonizedProto = null;
        Optional<Harmonized> harmonizedOptional = harmonizedService.findByBackendAndBackendCode(request.getBackend(), request.getBackendCode());
        if (harmonizedOptional.isPresent()) {
            harmonizedProto = HarmonizedProto.newBuilder()
                    .setId(harmonizedOptional.get().getId())
                    .setBackend(harmonizedOptional.get().getBackend())
                    .setBackendCode(harmonizedOptional.get().getBackendCode())
                    .setBackendDescription(harmonizedOptional.get().getBackendDescription())
                    .setHarmonizedCode(harmonizedOptional.get().getHarmonizedCode())
                    .setHarmonizedDescription(harmonizedOptional.get().getHarmonizedDescription())
                    .setHarmonizedHttpStatus(harmonizedOptional.get().getHarmonizedHttpStatus())
                    .setIsError(harmonizedOptional.get().getIsError())
                    .build();
        }
        responseObserver.onNext(harmonizedProto);
        responseObserver.onCompleted();
    }
}