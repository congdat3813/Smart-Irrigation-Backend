package com.hcmut.smartirrigation.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "irrigation_model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IrrigationModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String modelName;
    private String modelSource;
    @Column(length = 2000)
    private String description;
}
