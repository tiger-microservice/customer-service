package vn.tiger.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    private final String message;
    public CustomerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
