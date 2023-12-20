package ec.com.saviasoft.air.collection.controller;


import ec.com.saviasoft.air.collection.model.pojo.AirQualityMeasurement;
import ec.com.saviasoft.air.collection.model.response.ErrorResponse;
import ec.com.saviasoft.air.collection.service.AirQualityMeasurementService;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/air-quality-measurement")
@RequiredArgsConstructor
public class AirQualityMeasurementController {

    private final AirQualityMeasurementService airQualityMeasurementService;

    @GetMapping
    public ResponseEntity<List<AirQualityMeasurement>> getAirQualityMeasurement(
            @RequestParam(required = false) Integer userId
    ) {
        List<AirQualityMeasurement> airQualityMeasurementList;

        if (userId != null) {
            airQualityMeasurementList = airQualityMeasurementService.findByUserId(userId);
        } else {
            airQualityMeasurementList = airQualityMeasurementService.getAirQualityMeasurement();
        }

        if (Objects.nonNull(airQualityMeasurementList)) {
            return ResponseEntity.ok(airQualityMeasurementList);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }


    @GetMapping("/top5")
    public ResponseEntity<List<AirQualityMeasurement>> getTop5ByUserId(
            @RequestParam(required = false) Integer userId
    ) {
        List<AirQualityMeasurement> airQualityMeasurementList;

        if(userId != null) {
        	airQualityMeasurementList = airQualityMeasurementService.getTop5ByUserId(userId);
        } else {
        	airQualityMeasurementList = airQualityMeasurementService.getTop5();
        }

        if (Objects.nonNull(airQualityMeasurementList)) {
            return ResponseEntity.ok(airQualityMeasurementList);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @PostMapping
    public ResponseEntity<?> createAirQualityMeasurement(
            @RequestBody AirQualityMeasurement airQualityMeasurement,
            @RequestHeader("uid") String uid
    ) {
        try {
            return ResponseEntity.ok(airQualityMeasurementService.createAirQualityMeasurement(airQualityMeasurement, uid));
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage(), "Error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
