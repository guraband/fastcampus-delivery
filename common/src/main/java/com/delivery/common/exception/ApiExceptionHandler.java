package com.delivery.common.exception;

import com.delivery.common.dto.CommonResponse;
import com.delivery.common.status.ErrorStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(value = Integer.MIN_VALUE)   // 최우선 적용
public class ApiExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<CommonResponse<Object>> exception(ApiException e) {
        log.error("", e);

        return ResponseEntity.status(e.getErrorStatusCode().getHttpStatusCode())
                .body(
                        CommonResponse.error(e.getErrorStatusCode(), e.getErrorMessage())
                );
    }

    @ExceptionHandler(value = PropertyValueException.class)
    public ResponseEntity<CommonResponse<Object>> propertyValueException(Exception e) {
        log.error("", e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        CommonResponse.error(ErrorStatusCode.BAD_REQUEST, e.getMessage())
                );
    }
}
