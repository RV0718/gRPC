package com.grpc.server;

import com.grpc.server.service.GreetServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

    public static void main(String[] args) throws Exception {
        System.out.println("starting GRPC Server");
        final Server server = ServerBuilder
                .forPort(5050)
                .addService(new GreetServiceImpl())
                .build();

        server.start();
        System.out.println("server started at " + server.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }
}
