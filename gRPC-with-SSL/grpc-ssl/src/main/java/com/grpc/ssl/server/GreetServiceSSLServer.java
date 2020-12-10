package com.grpc.ssl.server;

import com.grpc.ssl.server.service.GreetServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.File;

public class GreetServiceSSLServer {

    public static void main(String[] args) throws Exception {
        System.out.println("starting GRPC SSL Server");
        final Server server = ServerBuilder
                .forPort(5050)
                .useTransportSecurity(
                        new File("/Users/RV0718/projects/gRPC/ssl/server.crt"),
                        new File("/Users/RV0718/projects/gRPC/ssl/server.pem")
                )
                .addService(new GreetServiceImpl())
                .build();

        server.start();
        System.out.println("SSL server started at " + server.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the SSL server");
        }));

        server.awaitTermination();
    }
}
