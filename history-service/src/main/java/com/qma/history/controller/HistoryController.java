package com.qma.history.controller;

import com.qma.history.dto.QuantityHistoryDTO;
import com.qma.history.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("service", "history-service", "status", "UP"));
    }

    @GetMapping("/operation/{operation}")
    public ResponseEntity<List<QuantityHistoryDTO>> getHistoryByOperation(@PathVariable String operation,
                                                                          @RequestParam(required = false) String userEmail) {
        return ResponseEntity.ok(historyService.getHistoryByOperation(operation, userEmail));
    }

    @GetMapping("/type/{measurementType}")
    public ResponseEntity<List<QuantityHistoryDTO>> getHistoryByMeasurementType(@PathVariable String measurementType,
                                                                                 @RequestParam(required = false) String userEmail) {
        return ResponseEntity.ok(historyService.getHistoryByMeasurementType(measurementType, userEmail));
    }

    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getOperationCount(@PathVariable String operation,
                                                  @RequestParam(required = false) String userEmail) {
        return ResponseEntity.ok(historyService.getOperationCount(operation, userEmail));
    }

    @GetMapping("/errored")
    public ResponseEntity<List<QuantityHistoryDTO>> getErrorHistory(@RequestParam(required = false) String userEmail) {
        return ResponseEntity.ok(historyService.getErrorHistory(userEmail));
    }
}
