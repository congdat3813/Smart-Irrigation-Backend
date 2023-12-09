package com.hcmut.smartirrigation.repository;

import com.hcmut.smartirrigation.model.entity.IrrigationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IrrigationModelRepository extends JpaRepository<IrrigationModel, UUID> {
}
