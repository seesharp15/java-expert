package dev.expert.interop;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class GrpcInterop {
    public static ManagedChannel channelFor(String host, int port) {
        throw new UnsupportedOperationException("TODO: build secure channel and configure keepalive");
    }

    public static String echo(ManagedChannel channel, String input) {
        throw new UnsupportedOperationException("TODO: implement gRPC stub call to Scala service");
    }
}

























































/*
ANSWER KEY (skeletal):

public static ManagedChannel channelFor(String host, int port) {
    return ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext()
        .keepAliveWithoutCalls(true)
        .build();
}

public static String echo(ManagedChannel channel, String input) {
    // Suppose EchoServiceGrpc.EchoServiceBlockingStub exists
    try {
        var stub = EchoServiceGrpc.newBlockingStub(channel);
        var response = stub.echo(Echo.Request.newBuilder().setMessage(input).build());
        return response.getMessage();
    } catch (StatusRuntimeException e) {
        throw e;
    }
}
*/
