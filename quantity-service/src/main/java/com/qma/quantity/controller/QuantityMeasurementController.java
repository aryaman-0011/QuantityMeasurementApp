package com.qma.quantity.controller;

import com.qma.quantity.dto.QuantityInputDTO;
import com.qma.quantity.dto.QuantityMeasurementDTO;
import com.qma.quantity.service.IQuantityMeasurementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> apiIndex() {
        Map<String, Object> index = new LinkedHashMap<>();
        index.put("application", "Quantity Service");
        index.put("version", "1.1.0");
        index.put("status", "UP");
        index.put("timestamp", LocalDateTime.now().toString());
        index.put("swagger", "/swagger-ui/index.html");
        return ResponseEntity.ok(index);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("service", "quantity-service", "status", "UP"));
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> performAdd(@Valid @RequestBody QuantityInputDTO input,
                                                             @RequestHeader(value = "X-User-Email", required = false) String userEmail) {
        return ResponseEntity.ok(service.add(input.getThisQuantityDTO(), input.getThatQuantityDTO(), userEmail));
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO> performSubtract(@Valid @RequestBody QuantityInputDTO input,
                                                                  @RequestHeader(value = "X-User-Email", required = false) String userEmail) {
        return ResponseEntity.ok(service.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO(), userEmail));
    }

    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO> performDivide(@Valid @RequestBody QuantityInputDTO input,
                                                                @RequestHeader(value = "X-User-Email", required = false) String userEmail) {
        return ResponseEntity.ok(service.divide(input.getThisQuantityDTO(), input.getThatQuantityDTO(), userEmail));
    }

    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> performCompare(@Valid @RequestBody QuantityInputDTO input,
                                                                 @RequestHeader(value = "X-User-Email", required = false) String userEmail) {
        return ResponseEntity.ok(service.compare(input.getThisQuantityDTO(), input.getThatQuantityDTO(), userEmail));
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> performConvert(@Valid @RequestBody QuantityInputDTO input,
                                                                 @RequestHeader(value = "X-User-Email", required = false) String userEmail) {
        return ResponseEntity.ok(service.convert(input.getThisQuantityDTO(), input.getThatQuantityDTO().getUnit(), userEmail));
    }

    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByOperation(@PathVariable String operation,
                                                                              @RequestParam(required = false) String userEmail) {
        return ResponseEntity.ok(service.getHistoryByOperation(operation, userEmail));
    }

    @GetMapping("/history/type/{measurementType}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByMeasurementType(@PathVariable String measurementType,
                                                                                     @RequestParam(required = false) String userEmail) {
        return ResponseEntity.ok(service.getHistoryByMeasurementType(measurementType, userEmail));
    }

    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getOperationCount(@PathVariable String operation,
                                                  @RequestParam(required = false) String userEmail) {
        return ResponseEntity.ok(service.getOperationCount(operation, userEmail));
    }

    @GetMapping("/history/errored")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory(@RequestParam(required = false) String userEmail) {
        return ResponseEntity.ok(service.getErrorHistory(userEmail));
    }
}
