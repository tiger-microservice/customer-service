package vn.tiger.customer.utils;


import java.util.Objects;

public final class DataUtil {

    private static final String GRPC_UNDEFINED_VALUE = "grpc_undefined";

    public static Object returnValue(Object value) {
        if (Objects.isNull(value)) {
            return GRPC_UNDEFINED_VALUE;
        }
        return value;
    }

    public static String returnString(Object value) {
        return returnValue(value) + "";
    }
}
