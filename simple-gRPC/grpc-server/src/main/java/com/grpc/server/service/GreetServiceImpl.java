package com.grpc.server.service;

import com.simple.grpc.GreetServiceGrpc;
import com.simple.grpc.GreetServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greetUser(final GreetServiceOuterClass.GreetRequest request, final StreamObserver<GreetServiceOuterClass.GreetResponse> responseObserver) {

        final GreetServiceOuterClass.GreetResponse response = GreetServiceOuterClass.GreetResponse
                .newBuilder()
                .setGreeting("Hello this response is from GRPC Server " + request.getFirstName() + "  " + request.getLastName())
                .build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
