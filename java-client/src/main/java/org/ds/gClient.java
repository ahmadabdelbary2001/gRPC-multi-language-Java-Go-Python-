package org.ds;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class gClient {
    static Logger logger = LoggerFactory.getLogger(gClient.class);

    public static void main(String[] args) {
        ManagedChannel additionChannel = ManagedChannelBuilder.forAddress("localhost", 2000)
                .usePlaintext()
                .build();
        ManagedChannel multiplicationChannel = ManagedChannelBuilder.forAddress("localhost", 3000)
                .usePlaintext()
                .build();

        org.ds.CalculatorGrpc.CalculatorStub additionStub = org.ds.CalculatorGrpc.newStub(additionChannel);
        org.ds.CalculatorGrpc.CalculatorStub multiplicationStub = org.ds.CalculatorGrpc.newStub(multiplicationChannel);

        Scanner scanner = new Scanner(System.in);

        boolean repeat = true;
        while (repeat) {
            System.out.println("Enter first number:");
            int number1 = scanner.nextInt();

            System.out.println("Enter second number:");
            int number2 = scanner.nextInt();

            org.ds.CalculationRequest request = org.ds.CalculationRequest.newBuilder()
                    .setNumber1(number1)
                    .setNumber2(number2)
                    .build();

            System.out.println("Addition Results Stream:");
            additionStub.addNumbers(request, new ClientResponseObserver<org.ds.CalculationRequest, org.ds.CalculationResponse>() {
                boolean responseReceived = false;

                @Override
                public void beforeStart(ClientCallStreamObserver<org.ds.CalculationRequest> requestStream) {

                }

                @Override
                public void onNext(org.ds.CalculationResponse response) {
                    if (!responseReceived) {
                        System.out.println("Addition Result: " + response.getResult());
                        responseReceived = true;
                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("Addition service unavailable or failed: " + t.getMessage());
                    logger.error("Addition RPC failed: {}", t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Addition stream completed.");
                }
            });

            System.out.println("Multiplication Results Stream:");
            multiplicationStub.multiplyNumbers(request, new ClientResponseObserver<org.ds.CalculationRequest, org.ds.CalculationResponse>() {
                boolean responseReceived = false;

                @Override
                public void beforeStart(ClientCallStreamObserver<org.ds.CalculationRequest> requestStream) {

                }

                @Override
                public void onNext(org.ds.CalculationResponse response) {
                    if (!responseReceived) {
                        System.out.println("Multiplication Result: " + response.getResult());
                        responseReceived = true;
                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("Multiplication service unavailable or failed: " + t.getMessage());
                    logger.error("Multiplication RPC failed: {}", t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Multiplication stream completed.");
                }
            });

            System.out.println("Do you want to try another calculation? (yes/no):");
            repeat = scanner.next().equalsIgnoreCase("yes");
        }

        scanner.close();
        additionChannel.shutdown();
        multiplicationChannel.shutdown();
    }
}
