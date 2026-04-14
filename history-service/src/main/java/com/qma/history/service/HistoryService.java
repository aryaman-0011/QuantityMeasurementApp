package com.qma.history.service;

import com.qma.history.dto.QuantityHistoryDTO;
import com.qma.history.repository.QuantityHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final QuantityHistoryRepository repository;

    public HistoryService(QuantityHistoryRepository repository) {
        this.repository = repository;
    }

    private String normalizeUserEmail(String userEmail) {
        if (userEmail == null || userEmail.isBlank()) {
            return null;
        }
        return userEmail.trim().toLowerCase();
    }

    public List<QuantityHistoryDTO> getHistoryByOperation(String operation, String userEmail) {
        String normalizedEmail = normalizeUserEmail(userEmail);
        if (normalizedEmail == null) {
            return QuantityHistoryDTO.fromEntityList(repository.findByOperation(operation.toLowerCase()));
        }
        return QuantityHistoryDTO.fromEntityList(repository.findByOperationAndUserEmail(operation.toLowerCase(), normalizedEmail));
    }

    public List<QuantityHistoryDTO> getHistoryByMeasurementType(String measurementType, String userEmail) {
        String normalizedEmail = normalizeUserEmail(userEmail);
        if (normalizedEmail == null) {
            return QuantityHistoryDTO.fromEntityList(repository.findByThisMeasurementType(measurementType));
        }
        return QuantityHistoryDTO.fromEntityList(repository.findByThisMeasurementTypeAndUserEmail(measurementType, normalizedEmail));
    }

    public long getOperationCount(String operation, String userEmail) {
        String normalizedEmail = normalizeUserEmail(userEmail);
        if (normalizedEmail == null) {
            return repository.countByOperationAndErrorFalse(operation.toLowerCase());
        }
        return repository.countByOperationAndErrorFalseAndUserEmail(operation.toLowerCase(), normalizedEmail);
    }

    public List<QuantityHistoryDTO> getErrorHistory(String userEmail) {
        String normalizedEmail = normalizeUserEmail(userEmail);
        if (normalizedEmail == null) {
            return QuantityHistoryDTO.fromEntityList(repository.findByErrorTrue());
        }
        return QuantityHistoryDTO.fromEntityList(repository.findByErrorTrueAndUserEmail(normalizedEmail));
    }
}
