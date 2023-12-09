package com.hcmut.smartirrigation.model.dto.farm;

import lombok.Data;

import java.util.UUID;

@Data
public class FarmCreateDTO {
    private String name;
    private UUID location_id;
    private UUID plant_id;
    private String soilType;
    private double acreage;
}
