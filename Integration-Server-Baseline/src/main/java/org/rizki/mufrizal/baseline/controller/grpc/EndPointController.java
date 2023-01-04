package org.rizki.mufrizal.baseline.controller.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.rizki.mufrizal.baseline.domain.EndPoint;
import org.rizki.mufrizal.baseline.service.EndPointService;
import org.rizki.mufrizal.grpc.microservice.domain.EndPointProto;
import org.rizki.mufrizal.grpc.microservice.service.BackendAndBackendFunction;
import org.rizki.mufrizal.grpc.microservice.service.EndPointServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
public class EndPointController extends EndPointServiceGrpc.EndPointServiceImplBase {

    @Autowired
    private EndPointService endPointService;

    @Override
    public void findByBackendAndBackendFunction(BackendAndBackendFunction request, StreamObserver<EndPointProto> responseObserver) {
        EndPointProto endPointProto = null;
        Optional<EndPoint> optionalEndPoint = endPointService.findByBackendAndBackendFunction(request.getBackend(), request.getBackendFunction());
        if (optionalEndPoint.isPresent()) {
            endPointProto = EndPointProto.newBuilder()
                    .setId(optionalEndPoint.get().getId())
                    .setBackend(optionalEndPoint.get().getBackend())
                    .setBackendFunction(optionalEndPoint.get().getBackendFunction())
                    .setUrl(optionalEndPoint.get().getUrl())
                    .setMethod(optionalEndPoint.get().getMethod())
                    .setTimeout(optionalEndPoint.get().getTimeout())
                    .setConnectTimeout(optionalEndPoint.get().getConnectTimeout())
                    .build();
        }
        responseObserver.onNext(endPointProto);
        responseObserver.onCompleted();
    }
}