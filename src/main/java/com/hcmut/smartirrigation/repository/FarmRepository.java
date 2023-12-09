package com.hcmut.smartirrigation.repository;

import com.hcmut.smartirrigation.model.entity.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FarmRepository extends JpaRepository<Farm, UUID> {

    @Query(value = """
            select * from farm where lower(farm_name) like concat('%', lower(?1), '%')
            and user_id = ?2
            """, nativeQuery = true)
    Page<Farm> getPage(String keyword, UUID userId, Pageable pageable);
}
