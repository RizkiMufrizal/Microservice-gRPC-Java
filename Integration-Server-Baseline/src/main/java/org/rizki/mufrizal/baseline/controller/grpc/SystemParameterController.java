package org.rizki.mufrizal.baseline.controller.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.rizki.mufrizal.baseline.service.SystemParameterService;
import org.rizki.mufrizal.grpc.microservice.domain.SystemParameterProto;
import org.rizki.mufrizal.grpc.microservice.service.ParamName;
import org.rizki.mufrizal.grpc.microservice.service.SystemParameterServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class SystemParameterController extends SystemParameterServiceGrpc.SystemParameterServiceImplBase {

    @Autowired
    private SystemParameterService systemParameterService;

    @Override
    public void findByParamName(ParamName request, StreamObserver<SystemParameterProto> responseObserver) {
        SystemParameterProto systemParameterProto = systemParameterService.findByParamName(request.getParamName());
        responseObserver.onNext(systemParameterProto);
        responseObserver.onCompleted();
    }
}
