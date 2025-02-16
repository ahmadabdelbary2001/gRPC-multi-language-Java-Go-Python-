package main

import (
	"log"
	"net"

	pb "go-server/calculator/protobuf"

	"google.golang.org/grpc"
)

type server struct {
	pb.UnimplementedCalculatorServer
}

func (s *server) AddNumbers(req *pb.CalculationRequest, stream pb.Calculator_AddNumbersServer) error {
	result := req.Number1 + req.Number2
	log.Printf("Adding %v + %v = %v", req.Number1, req.Number2, result)

	err := stream.Send(&pb.CalculationResponse{Result: result})
	if err != nil {
		return err
	}

	return nil
}

func main() {
	listener, err := net.Listen("tcp", ":2000")
	if err != nil {
		log.Fatalf("Failed to listen: %v", err)
	}

	grpcServer := grpc.NewServer()
	pb.RegisterCalculatorServer(grpcServer, &server{})

	log.Println("Server listening on port 2000...")
	if err := grpcServer.Serve(listener); err != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
