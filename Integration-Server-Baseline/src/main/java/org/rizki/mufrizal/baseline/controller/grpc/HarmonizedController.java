package org.rizki.mufrizal.baseline.controller.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.rizki.mufrizal.baseline.service.HarmonizedService;
import org.rizki.mufrizal.grpc.microservice.domain.HarmonizedProto;
import org.rizki.mufrizal.grpc.microservice.service.BackendAndBackendCode;
import org.rizki.mufrizal.grpc.microservice.service.HarmonizedServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class HarmonizedController extends HarmonizedServiceGrpc.HarmonizedServiceImplBase {

    @Autowired
    private HarmonizedService harmonizedService;

    @Override
    public void findByBackendAndBackendCode(BackendAndBackendCode request, StreamObserver<HarmonizedProto> responseObserver) {
        HarmonizedProto harmonizedProto = harmonizedService.findByBackendAndBackendCode(request.getBackend(), request.getBackendCode());
        responseObserver.onNext(harmonizedProto);
        responseObserver.onCompleted();
    }
}