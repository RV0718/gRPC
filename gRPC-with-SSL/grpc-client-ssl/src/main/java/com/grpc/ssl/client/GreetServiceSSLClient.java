package com.grpc.ssl.client;

import com.grpc.ssl.GreetServiceGrpc;
import com.grpc.ssl.GreetServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import javax.net.ssl.SSLException;
import java.io.File;

public class GreetServiceSSLClient {

    public static void main(String[] args) {
        System.out.println("This is a SSL gRPC client calling greet service");

        ManagedChannel secureChannel = null;
        try {
            secureChannel = NettyChannelBuilder
                    .forAddress("localhost", 5050)
                    .sslContext(GrpcSslContexts.forClient().trustManager(new File("/Users/RV0718/projects/gRPC/ssl/ca.crt")).build())
                    .build();
        } catch (SSLException e) {
            e.printStackTrace();
        }

        new GreetServiceSSLClient().run(secureChannel);
    }

    private void run(final ManagedChannel channel) {

        final GreetServiceGrpc.GreetServiceBlockingStub stub = GreetServiceGrpc.newBlockingStub(channel);

        final GreetServiceOuterClass.GreetRequest request = GreetServiceOuterClass.GreetRequest
                .newBuilder()
                .setFirstName("John")
                .setLastName("Hefferman")
                .build();

        final GreetServiceOuterClass.GreetResponse response = stub.greetUser(request);

        System.out.println("Received the response from Greet Service " + response.getGreeting());

    }
}
