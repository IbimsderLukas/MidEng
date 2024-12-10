# Other Branch Contains the project!!!


# Beschreibung

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

![](C:\Users\lukas.dumbo\AppData\Roaming\marktext\images\2024-12-10-15-26-08-image.png)



