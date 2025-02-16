# PolyCalc - A gRPC Multi-Language Calculator Service

## Overview
PolyCalc is a distributed calculator service implemented using gRPC and Protocol Buffers across three languages. The project features a Java client that communicates with two separate servers—a Go server (handling addition) and a Python server (handling multiplication). This setup demonstrates cross-language interoperability using a common gRPC API.

## Technical Stack
- **Languages:** Java, Go, Python
- **Communication:** gRPC, Protocol Buffers
- **Design Patterns:** Stream Observer Pattern

## Key Features
- **Cross-Language RPC**:
  - ➕ **Go-based Addition Service** (Port 2000)
  - ✖️ **Python-based Multiplication Service** (Port 3000)
  - 🇯 **Java Client Interface** with interactive console
- **Streaming Architecture**:
  - Server-side streaming responses
  - Async result handling
  - Error streaming with SLF4J logging
- **Protocol Buffers**:
  - Unified `calculator.proto` definition
  - Auto-generated stubs for all languages
  - Compact binary serialization

## Project Structure
```bash
PolyCalc/
├── java-client/               # Java Client Implementation
│   ├── src/main/java/org/ds/
│   │   └── gClient.java       # Client logic
│   ├── src/main/proto/
│   │   └── calculator.proto   # Service contracts
│   └── pom.xml                # Dependency management
│
├── go-server/                 # Go Addition Service
│   ├── main.go                # Server implementation
│   └── calculator.proto       # Protobuf definition
│
└── python-server/             # Python Multiplication Service
    ├── main.py                # Server implementation
    └── calculator.proto       # Protobuf definition
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

## Build & Run

1. **Build the Java Client**
   ```bash
   mvn clean install
   ```

2. **Generate gRPC for Go (Addition Service)**
   ```bash
   cd go-server
   protoc --go_out=. --go-grpc_out=. calculator.proto
   go run main.go
   ```

3. **Generate gRPC for Python (Multiplication Service)**
   ```bash
   cd python-server
   python -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. calculator.proto
   python main.py
   ```
