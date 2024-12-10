package org.example;


import io.grpc.stub.StreamObserver;
import model.VoteData;

public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void hello( Hello.HelloRequest request, StreamObserver<Hello.HelloResponse> responseObserver) {

        System.out.println("Handling hello endpoint: " + request.toString());

        String text = getVoteDataAsJSON("1234");
        Hello.HelloResponse response = Hello.HelloResponse.newBuilder().setText(text).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
    public String getVoteDataAsJSON( String regionID) {
        VoteData data = VoteService.getVoteData(regionID);
        return data.toJSON();
    }
}