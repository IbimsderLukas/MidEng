# Project located in master branch!


# Overview

This exercise is intended to demonstrate the functionality and implementation of Remote Procedure Call (RPC) technology using the Open Source High Performance gRPC Framework **gRPC Frameworks** ([https://grpc.io](https://grpc.io/)). It shows that this framework can be used to develop a middleware system for connecting several services developed with different programming languages.

Document all individual implementation steps and any problems that arise in a log (Markdown). Create a GITHUB repository for this project and add the link to it in the comments.

# Theory
### 1. **gRPC Overview**  
gRPC is a cross-platform RPC framework using Protocol Buffers for serialization, supporting multiple languages for interoperability.

### 2. **RPC Life Cycle**  
1. Client sends request.  
2. Server processes and responds.  
3. Client receives response.

### 3. **Protocol Buffers Workflow**  
1. Define schema in `.proto` file.  
2. Generate code for target language.  
3. Serialize/deserialize data for communication.

### 4. **Benefits of Protocol Buffers**  
- Compact and efficient.  
- Cross-language compatibility.  
- Backward compatibility.

### 5. **When Protocol Buffers Are Not Recommended**  
- Small/simple data.  
- Text-human readability needed.  
- No cross-platform requirement.

### 6. **3 Protocol Buffers Data Types**  
- `int32` (integer).  
- `string` (text).  
- `bool` (boolean).


# Task

### Problems

I followed the tutorial until I encountered the following error: The java class HelloWorldClient that I copied from the task did not find the package HelloWorldService. What was particularly strange was that it could find some methods and not others. After I couldn't find a possible solution, I found a solution with the help of Pavle, the person sitting next to me. If you add this line to the proto file

```java
option java_package = "org.example"; 
```
This sets the location of the Libery that creates the proto file. Normally this is used to make the programme clearer so that the Libery does not end up in a strange place. In this case it is used to have a safe location for the classes to find it.

### Further problem

An unexpected error occurred after executing from the server and then from the client

![image](https://github.com/user-attachments/assets/f7611fe5-83e8-4508-8d2f-90260a21aaf5)

This is caused by a network crash of the client. One possible reason would be because the client is not shut down properly.

Solution approach:


```java
channel.shutdown()
-----------------
channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
```

In the client, `channel.shutdown()` is called to close the gRPC channel. However, it is recommended to use `channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)` instead, so that the channel is shut down correctly and all resources are released properly.

Another prevention measure is a try-catch loop in the server

```java
server.awaitTermination();
----------------------

try {
    server.awaitTermination();
} catch (InterruptedException e) {
    System.err.println("Server interrupted: " + e.getMessage());
}

```

It makes sense to improve the error handling in the server for such scenarios. gRPC provides standard logs, but additional specific exception handling mechanisms could help to minimise the error.

# Sources

https://intuting.medium.com/implement-grpc-service-using-java-gradle-7a54258b60b8

# Program

#### Client class:
```java
ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();

```

- **`ManagedChannel`:** Represents the communication channel between the client and the server.
- **`ManagedChannelBuilder.forAddress(‘localhost’, 50051)`:** Builds a channel that connects to a server on `localhost` (the local host) and port `50051`.
- **`.usePlaintext()`:** Specifies that the channel does not use encryption (TLS). This is often common in test or development environments.
- **`.build()`:** Builds the channel.

```java
HelloWorldServiceGrpc.HelloWorldServiceBlockingStub stub = HelloWorldServiceGrpc.newBlockingStub(channel);
```

- HelloWorldServiceGrpc.HelloWorldServiceBlockingStub`:** A stub is a client-side proxy object that forwards method calls to the server.
  - A **blocking stub** is used here, i.e. the method calls block the calling thread until the server responds.
- **`.newBlockingStub(channel)`:** Creates a stub that is connected to the previously created channel.

 ```java
    Hello.HelloResponse helloResponse = stub.hello(Hello.HelloRequest.newBuilder()
        .setFirstname("Max")
        .setLastname("Mustermann")
        .build());
 ```
- **`Hello.HelloRequest.newBuilder()`:** Creates a new `HelloRequest` (based on the protobuf definition).
  - **`.setFirstname(‘Max’)`:** Sets the first name in the request.
  - **`.setLastname(‘Mustermann’)`:** Sets the last name in the request.
  - **`.build()`:** Completes the request object.
- **`stub.hello(...)`:** Executes the `hello` method of the gRPC service on the server. The request is sent to the server and the response (`HelloResponse`) is returned.
- HelloResponse helloResponse`:** Saves the response from the server.

  #### Server class:
  ```java
    public void start() throws IOException {
        server = ServerBuilder.forPort(PORT)
            .addService(new HelloWorldServiceImpl())
            .build()
            .start();
} ```

- **`server = ServerBuilder.forPort(PORT)`:** Creates a server that listens on the specified port (`50051`).
- **`.addService(new HelloWorldServiceImpl())`:** Registers the gRPC service that is to process requests.
  - **`HelloWorldServiceImpl`**: An implementation of the gRPC service (based on the protobuf definition). This class processes the incoming requests.
- **`.build()`**: Builds the server with the specified settings.
- **`.start()`**: Starts the server and makes it ready to receive requests.
- **`throws IOException`:** If an error occurs when starting the server (e.g. port already occupied).

  ```java
    public void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            System.err.println("Server interrupted: " + e.getMessage());
        }
}
    ```
- **`server.awaitTermination()`:** Wartet darauf, dass der Server beendet wird.
  - Diese Methode blockiert, bis der Server entweder durch einen Systembefehl oder durch eine Ausnahme gestoppt wird.
- **Abbruchbehandlung (`catch`-Block):** Gibt eine Fehlermeldung aus, falls der Server durch eine Unterbrechung beendet wird.

#### Service Class
```java
@Override
public void hello(Hello.HelloRequest request, StreamObserver<Hello.HelloResponse> responseObserver) {

```
- **`@Override`:** Indicates that the method from the base class will be overridden.
  
- **`hello(...)`:** The implementation of the `hello` method as defined in the protobuf definition in the portofile
  
  `rpc hello(HelloRequest) returns (HelloResponse) {}`
  
- **Parameter:**
  
  - HelloRequest request`:** The request object that is sent by the client. It contains fields such as `firstname` and `lastname`.
  - **`StreamObserver<Hello.HelloResponse> responseObserver`:** An observer with which the server sends the response back to the client.

# Vote Data

added VoteData Class with VoteService Class and this methode to return the String

```java
public String getVoteDataAsJSON( String regionID) {
        VoteData data = VoteService.getVoteData(regionID);
        return data.toJSON();
    }
```


# GKv

Made a new Server Class and a new proto file and a new Client and a new Service, which follow the same exact way then explained above,
