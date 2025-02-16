import grpc
from concurrent import futures
import time
import sys

sys.path.insert(0, '../calculator/protobuf')
import calculator_pb2
import calculator_pb2_grpc

class CalculatorService(calculator_pb2_grpc.CalculatorServicer):
    def MultiplyNumbers(self, request, context):
        result = request.number1 * request.number2
        print(f"Multiplying {request.number1} * {request.number2} = {result}")

        yield calculator_pb2.CalculationResponse(result=result)

    def AddNumbers(self, request, context):
        result = request.number1 + request.number2
        print(f"Adding {request.number1} + {request.number2} = {result}")

        yield calculator_pb2.CalculationResponse(result=result)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    calculator_pb2_grpc.add_CalculatorServicer_to_server(CalculatorService(), server)
    server.add_insecure_port("[::]:3000")
    print("Server starting on port 3000...")
    server.start()
    try:
        while True:
            time.sleep(86400)
    except KeyboardInterrupt:
        server.stop(0)

if __name__ == "__main__":
    serve()
