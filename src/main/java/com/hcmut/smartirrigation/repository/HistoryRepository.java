package com.hcmut.smartirrigation.repository;

import com.hcmut.smartirrigation.model.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<History, UUID> {
}
