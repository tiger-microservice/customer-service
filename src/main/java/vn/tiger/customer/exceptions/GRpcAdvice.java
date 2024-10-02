package vn.tiger.customer.exceptions;


import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GRpcAdvice {

    @GrpcExceptionHandler(CustomerNotFoundException.class)
    public StatusRuntimeException handleAccountNotFoundException(CustomerNotFoundException accountNotFoundException) {
        Metadata metadata = new Metadata();
//        Metadata.Key<DatabaseError> databaseError = ProtoUtils.keyForProto(DatabaseError.getDefaultInstance());
//        metadata.put(databaseError, DatabaseError.newBuilder().setMessage("Database Error- Connection Refused.")
//                .setErrorType("CONNECTION_REFUSED").build());
        return Status.NOT_FOUND.withDescription("The requested Account Number cannot be found.")
                .asRuntimeException(metadata);
    }
}
