package vn.tiger.customer.controllers.grpcs;


import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.tiger.customer.entities.Customer;
import vn.tiger.customer.exceptions.CustomerNotFoundException;
import vn.tiger.customer.proto.CustomerRequest;
import vn.tiger.customer.proto.CustomerResponse;
import vn.tiger.customer.proto.CustomerServiceGrpc;
import vn.tiger.customer.services.CustomerService;
import vn.tiger.customer.utils.DataUtil;
import vn.tiger.customer.utils.DateUtils;

import java.util.UUID;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class CustomerGRpc extends CustomerServiceGrpc.CustomerServiceImplBase {

    final CustomerService service;

    @Override
    public void getCustomerInfo(CustomerRequest request, StreamObserver<CustomerResponse> responseObserver) {
        try {
            Customer customer = (Customer)service.getProfile(UUID.fromString(request.getCustomerId()));
            CustomerResponse response = CustomerResponse.newBuilder()
                    .setFullName(DataUtil.returnString(customer.getFullName()))
                    .setPhoneNumber(DataUtil.returnString(customer.getPhoneNumber()))
                    .setEmail(DataUtil.returnString(customer.getEmail()))
                    .setAddress(DataUtil.returnString(customer.getAddress()))
                    .setDepartmentId(DataUtil.returnString(customer.getDepartmentId()))
                    .setDob(DataUtil.returnString(DateUtils.localDateToYYYYMMDD(customer.getDob())))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("[getCustomerInfo] error {}", e.getMessage(), e);
            throw new CustomerNotFoundException(e.getMessage());
        }
    }
}
