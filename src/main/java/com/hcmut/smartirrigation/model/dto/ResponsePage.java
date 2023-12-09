package com.hcmut.smartirrigation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePage<T> {
    private List<T> data;
    private long totalElement;
    private long page;
    private long size;
}
