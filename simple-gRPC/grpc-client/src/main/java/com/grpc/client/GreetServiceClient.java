package com.grpc.client;

import com.simple.grpc.GreetServiceGrpc;
import com.simple.grpc.GreetServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetServiceClient {

    public static void main(String[] args) {
        System.out.println("This is a simple gRPC clinet calling greet service");

        final ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5050)
                .usePlaintext()
                .build();

        new GreetServiceClient().run(channel);
    }

    private void run(final ManagedChannel channel) {

        final GreetServiceGrpc.GreetServiceBlockingStub stub = GreetServiceGrpc.newBlockingStub(channel);

        final GreetServiceOuterClass.GreetRequest request = GreetServiceOuterClass.GreetRequest
                .newBuilder()
                .setFirstName("John")
                .setLastName("Hefferman")
                .build();

        final GreetServiceOuterClass.GreetResponse response = stub.greetUser(request);

        System.out.println("Received the resppnse from Greet Service " + response.getGreeting());

    }
}
