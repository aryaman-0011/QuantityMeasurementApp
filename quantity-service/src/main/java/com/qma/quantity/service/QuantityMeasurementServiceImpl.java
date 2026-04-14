package com.qma.quantity.service;

import com.qma.quantity.dto.QuantityDTO;
import com.qma.quantity.dto.QuantityMeasurementDTO;
import com.qma.quantity.exception.QuantityMeasurementException;
import com.qma.quantity.model.OperationType;
import com.qma.quantity.model.QuantityMeasurementEntity;
import com.qma.quantity.quantity.Quantity;
import com.qma.quantity.repository.QuantityMeasurementRepository;
import com.qma.quantity.unit.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    private Quantity<? extends IMeasurable> convertDtoToModel(QuantityDTO dto) {
        String type = dto.getNormalisedMeasurementType();
        String unit = dto.getUnit().toUpperCase();

        return switch (type) {
            case "LENGTH" -> new Quantity<>(dto.getValue(), LengthUnit.valueOf(unit));
            case "WEIGHT" -> new Quantity<>(dto.getValue(), WeightUnit.valueOf(unit));
            case "VOLUME" -> new Quantity<>(dto.getValue(), VolumeUnit.valueOf(unit));
            case "TEMPERATURE" -> new Quantity<>(dto.getValue(), TemperatureUnit.valueOf(unit));
            default -> throw new QuantityMeasurementException("Invalid measurement type: " + dto.getMeasurementType());
        };
    }

    private IMeasurable resolveTargetUnit(String normalisedType, String targetUnitName) {
        String unit = targetUnitName.toUpperCase();
        return switch (normalisedType) {
            case "LENGTH" -> LengthUnit.valueOf(unit);
            case "WEIGHT" -> WeightUnit.valueOf(unit);
            case "VOLUME" -> VolumeUnit.valueOf(unit);
            case "TEMPERATURE" -> TemperatureUnit.valueOf(unit);
            default -> throw new QuantityMeasurementException("Invalid conversion type: " + normalisedType);
        };
    }

    private QuantityMeasurementDTO buildAndSave(QuantityDTO q1, QuantityDTO q2, String operation,
                                                Double resultValue, String resultUnit,
                                                String resultMeasurementType, String resultString,
                                                String userEmail) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                q1.getValue(), q1.getUnit(), q1.getMeasurementType(),
                q2 != null ? q2.getValue() : null,
                q2 != null ? q2.getUnit() : null,
                q2 != null ? q2.getMeasurementType() : null,
                operation, resultValue, resultUnit, resultMeasurementType, resultString,
                normalizeUserEmail(userEmail), false, null
        );

        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    private QuantityMeasurementDTO buildAndSaveError(QuantityDTO q1, QuantityDTO q2,
                                                     String operation, String errorMessage,
                                                     String userEmail) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                q1.getValue(), q1.getUnit(), q1.getMeasurementType(),
                q2 != null ? q2.getValue() : null,
                q2 != null ? q2.getUnit() : null,
                q2 != null ? q2.getMeasurementType() : null,
                operation, null, null, null, null,
                normalizeUserEmail(userEmail), true, errorMessage
        );

        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    private String normalizeUserEmail(String userEmail) {
        if (userEmail == null || userEmail.isBlank()) {
            return null;
        }
        return userEmail.trim().toLowerCase();
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity<IMeasurable> qty1 = (Quantity<IMeasurable>) convertDtoToModel(q1);
            Quantity<IMeasurable> qty2 = (Quantity<IMeasurable>) convertDtoToModel(q2);
            Quantity<?> result = qty1.add(qty2);

            return buildAndSave(q1, q2, OperationType.ADD.getDisplayName(),
                    result.getValue(), result.getUnit().toString(),
                    q1.getMeasurementType(), result.toString(), userEmail);

        } catch (Exception e) {
            QuantityMeasurementDTO errDto = buildAndSaveError(q1, q2, OperationType.ADD.getDisplayName(),
                    "add Error: " + e.getMessage(), userEmail);
            throw new QuantityMeasurementException(errDto.getErrorMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity<IMeasurable> qty1 = (Quantity<IMeasurable>) convertDtoToModel(q1);
            Quantity<IMeasurable> qty2 = (Quantity<IMeasurable>) convertDtoToModel(q2);
            Quantity<?> result = qty1.subtract(qty2);

            return buildAndSave(q1, q2, OperationType.SUBTRACT.getDisplayName(),
                    result.getValue(), result.getUnit().toString(),
                    q1.getMeasurementType(), result.toString(), userEmail);

        } catch (Exception e) {
            QuantityMeasurementDTO errDto = buildAndSaveError(q1, q2, OperationType.SUBTRACT.getDisplayName(),
                    "subtract Error: " + e.getMessage(), userEmail);
            throw new QuantityMeasurementException(errDto.getErrorMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity<IMeasurable> qty1 = (Quantity<IMeasurable>) convertDtoToModel(q1);
            Quantity<IMeasurable> qty2 = (Quantity<IMeasurable>) convertDtoToModel(q2);
            double result = qty1.divide(qty2);

            return buildAndSave(q1, q2, OperationType.DIVIDE.getDisplayName(),
                    result, "RATIO", null, String.valueOf(result), userEmail);

        } catch (Exception e) {
            QuantityMeasurementDTO errDto = buildAndSaveError(q1, q2, OperationType.DIVIDE.getDisplayName(),
                    "divide Error: " + e.getMessage(), userEmail);
            throw new QuantityMeasurementException(errDto.getErrorMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO convert(QuantityDTO q, String targetUnit, String userEmail) {
        try {
            Quantity<IMeasurable> qty = (Quantity<IMeasurable>) convertDtoToModel(q);
            IMeasurable target = resolveTargetUnit(q.getNormalisedMeasurementType(), targetUnit);
            Quantity<?> converted = qty.convertTo(target);

            QuantityDTO targetDto = new QuantityDTO(0.0, targetUnit, q.getMeasurementType());

            return buildAndSave(q, targetDto, OperationType.CONVERT.getDisplayName(),
                    converted.getValue(), converted.getUnit().toString(),
                    q.getMeasurementType(), converted.toString(), userEmail);

        } catch (Exception e) {
            QuantityMeasurementDTO errDto = buildAndSaveError(q, null, OperationType.CONVERT.getDisplayName(),
                    "convert Error: " + e.getMessage(), userEmail);
            throw new QuantityMeasurementException(errDto.getErrorMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity<? extends IMeasurable> qty1 = convertDtoToModel(q1);
            Quantity<? extends IMeasurable> qty2 = convertDtoToModel(q2);

            double base1 = qty1.getUnit().convertToBaseUnit(qty1.getValue());
            double base2 = qty2.getUnit().convertToBaseUnit(qty2.getValue());
            boolean result = Math.abs(base1 - base2) < 0.0001;

            return buildAndSave(q1, q2, OperationType.COMPARE.getDisplayName(),
                    null, null, null, String.valueOf(result), userEmail);

        } catch (Exception e) {
            QuantityMeasurementDTO errDto = buildAndSaveError(q1, q2, OperationType.COMPARE.getDisplayName(),
                    "compare Error: " + e.getMessage(), userEmail);
            throw new QuantityMeasurementException(errDto.getErrorMessage());
        }
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByOperation(String operation, String userEmail) {
        String normalizedEmail = normalizeUserEmail(userEmail);
        if (normalizedEmail == null) {
            return QuantityMeasurementDTO.fromEntityList(repository.findByOperation(operation.toLowerCase()));
        }
        return QuantityMeasurementDTO.fromEntityList(repository.findByOperationAndUserEmail(operation.toLowerCase(), normalizedEmail));
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType, String userEmail) {
        String normalizedEmail = normalizeUserEmail(userEmail);
        if (normalizedEmail == null) {
            return QuantityMeasurementDTO.fromEntityList(repository.findByThisMeasurementType(measurementType));
        }
        return QuantityMeasurementDTO.fromEntityList(repository.findByThisMeasurementTypeAndUserEmail(measurementType, normalizedEmail));
    }

    @Override
    public long getOperationCount(String operation, String userEmail) {
        String normalizedEmail = normalizeUserEmail(userEmail);
        if (normalizedEmail == null) {
            return repository.countByOperationAndErrorFalse(operation.toLowerCase());
        }
        return repository.countByOperationAndErrorFalseAndUserEmail(operation.toLowerCase(), normalizedEmail);
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory(String userEmail) {
        String normalizedEmail = normalizeUserEmail(userEmail);
        if (normalizedEmail == null) {
            return QuantityMeasurementDTO.fromEntityList(repository.findByErrorTrue());
        }
        return QuantityMeasurementDTO.fromEntityList(repository.findByErrorTrueAndUserEmail(normalizedEmail));
    }
}
