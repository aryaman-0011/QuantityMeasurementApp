package com.qma.quantity.service;

import com.qma.quantity.dto.QuantityDTO;
import com.qma.quantity.dto.QuantityMeasurementDTO;

import java.util.List;

public interface IQuantityMeasurementService {
    QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2, String userEmail);
    QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2, String userEmail);
    QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2, String userEmail);
    QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2, String userEmail);
    QuantityMeasurementDTO convert(QuantityDTO q, String targetUnit, String userEmail);
    List<QuantityMeasurementDTO> getHistoryByOperation(String operation, String userEmail);
    List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType, String userEmail);
    long getOperationCount(String operation, String userEmail);
    List<QuantityMeasurementDTO> getErrorHistory(String userEmail);
}
