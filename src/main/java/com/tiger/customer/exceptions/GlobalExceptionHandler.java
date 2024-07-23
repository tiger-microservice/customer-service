package com.tiger.customer.exceptions;

import com.tiger.common.utils.MessageUtils;
import com.tiger.customer.configs.locale.Translator;
import com.tiger.customer.dtos.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Translator translator;

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<Object>> handlingRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.responseError(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<Object>> handlingAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.responseError(HttpStatus.FORBIDDEN.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handlingMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        String enumKey = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumKey);

            var constraintViolation =
                    exception.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            attributes = constraintViolation.getConstraintDescriptor().getAttributes();
            log.info(attributes.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return ResponseEntity.badRequest()
                .body(ApiResponse.responseError(
                        errorCode.getCode(),
                        Objects.nonNull(attributes)
                                ? MessageUtils.mapAttributes(errorCode.getMessage(), attributes)
                                : errorCode.getMessage()));
    }

    @ExceptionHandler(value = BusinessLogicException.class)
    ResponseEntity<ApiResponse<Object>> handlingBusinessLogicException(BusinessLogicException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.responseError(errorCode.getCode(), translator.toMessage(errorCode.getMessage())));
    }

    @ExceptionHandler(value = AuthLogicException.class)
    ResponseEntity<ApiResponse<Object>> handlingAuthLogicException(AuthLogicException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.responseError(errorCode.getCode(), translator.toMessage(exception.getMessage())));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<Object>> handlingException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.responseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }
}
