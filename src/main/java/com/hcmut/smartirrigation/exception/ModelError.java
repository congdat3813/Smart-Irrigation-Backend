package com.hcmut.smartirrigation.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelError {
    private String code;
    private String message;
}
