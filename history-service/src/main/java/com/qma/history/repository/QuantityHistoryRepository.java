package com.qma.history.repository;

import com.qma.history.model.QuantityHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuantityHistoryRepository extends JpaRepository<QuantityHistoryEntity, Long> {
    List<QuantityHistoryEntity> findByOperation(String operation);
    List<QuantityHistoryEntity> findByOperationAndUserEmail(String operation, String userEmail);
    List<QuantityHistoryEntity> findByThisMeasurementType(String measurementType);
    List<QuantityHistoryEntity> findByThisMeasurementTypeAndUserEmail(String measurementType, String userEmail);
    List<QuantityHistoryEntity> findByErrorTrue();
    List<QuantityHistoryEntity> findByErrorTrueAndUserEmail(String userEmail);
    long countByOperationAndErrorFalse(String operation);
    long countByOperationAndErrorFalseAndUserEmail(String operation, String userEmail);
}
