# PolyCalc - A gRPC Multi-Language Calculator Service

## Overview
PolyCalc is a distributed calculator service implemented using gRPC and Protocol Buffers across three languages. The project features a Java client that communicates with two separate servers—a Go server (handling addition) and a Python server (handling multiplication). This setup demonstrates cross-language interoperability using a common gRPC API.

## Technical Stack
- **Languages:** Java, Go, Python
- **Communication:** gRPC, Protocol Buffers
- **Design Patterns:** Stream Observer Pattern
- **Other:** Java Virtual Threads (where applicable)

## Project Structure
```
PolyCalc/ ├── java-client/ │ ├── src/main/java/org/ds/gClient.java # Java client implementation │ ├── src/main/proto/calculator.proto # gRPC service definition │ └── pom.xml # Maven build configuration ├── go-server/ │ ├── main.go # Go server implementation (Addition service) │ └── calculator.proto # gRPC service definition for Go └── python-server/ ├── main.py # Python server implementation (Multiplication service) └── calculator.proto # gRPC service definition for Python
```


## Installation

### Prerequisites
- **Java 17 or higher** (client)
- **Go** (server)
- **Python 3.7 or higher** (server)
- **Maven** (for Java build)
- **gRPC tools / protoc** (for generating code from .proto files)

### Clone the Repository
```bash
git clone https://github.com/ahmadabdelbary2001/gRPC-multi-language-Java-Go-Python.git
```

