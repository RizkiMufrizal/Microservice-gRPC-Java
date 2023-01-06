package org.rizki.mufrizal.baseline.controller.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.rizki.mufrizal.baseline.service.EndPointService;
import org.rizki.mufrizal.grpc.microservice.domain.EndPointProto;
import org.rizki.mufrizal.grpc.microservice.service.BackendAndBackendFunction;
import org.rizki.mufrizal.grpc.microservice.service.EndPointServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class EndPointController extends EndPointServiceGrpc.EndPointServiceImplBase {

    @Autowired
    private EndPointService endPointService;

    @Override
    public void findByBackendAndBackendFunction(BackendAndBackendFunction request, StreamObserver<EndPointProto> responseObserver) {
        EndPointProto endPointProto = endPointService.findByBackendAndBackendFunction(request.getBackend(), request.getBackendFunction());
        responseObserver.onNext(endPointProto);
        responseObserver.onCompleted();
    }
}