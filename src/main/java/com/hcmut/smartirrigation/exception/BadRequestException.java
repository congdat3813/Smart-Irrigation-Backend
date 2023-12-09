package com.hcmut.smartirrigation.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String code;

    public BadRequestException(String message) {
        super(message);
        this.code = "400";
    }

    public BadRequestException(String message, String code) {
        super(message);
        this.code = code;
    }

    public BadRequestException(String objectName, String fieldName, Object value) {
        super(objectName + " already exists with " + fieldName + ": " + value);
        this.code = "400";
    }
}
