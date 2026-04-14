package com.qma.quantity.repository;

import com.qma.quantity.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
    List<QuantityMeasurementEntity> findByOperation(String operation);
    List<QuantityMeasurementEntity> findByOperationAndUserEmail(String operation, String userEmail);
    List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);
    List<QuantityMeasurementEntity> findByThisMeasurementTypeAndUserEmail(String measurementType, String userEmail);
    List<QuantityMeasurementEntity> findByErrorTrue();
    List<QuantityMeasurementEntity> findByErrorTrueAndUserEmail(String userEmail);
    long countByOperationAndErrorFalse(String operation);
    long countByOperationAndErrorFalseAndUserEmail(String operation, String userEmail);
}
