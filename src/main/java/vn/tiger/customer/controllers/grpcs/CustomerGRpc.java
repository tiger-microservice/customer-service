package vn.tiger.customer.controllers.grpcs;


import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.tiger.customer.CustomerRequest;
import vn.tiger.customer.CustomerResponse;
import vn.tiger.customer.CustomerServiceGrpc;
import vn.tiger.customer.entities.Customer;
import vn.tiger.customer.services.CustomerService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class CustomerGRpc extends CustomerServiceGrpc.CustomerServiceImplBase {

    final CustomerService service;

    @Override
    public void getCustomerInfo(CustomerRequest request, StreamObserver<CustomerResponse> responseObserver) {
        Customer customer = (Customer)service.getProfile(UUID.fromString(request.getCustomerId()));
        CustomerResponse response = CustomerResponse.newBuilder()
                .setFullName(customer.getFullName())
                .setPhoneNumber(customer.getPhoneNumber())
                .setEmail(customer.getEmail())
                .setAddress(customer.getAddress())
                .setDepartmentId(customer.getDepartmentId() == null ? null : customer.getDepartmentId().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
