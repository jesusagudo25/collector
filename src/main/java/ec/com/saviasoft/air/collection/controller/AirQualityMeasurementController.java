package ec.com.saviasoft.air.collection.controller;


import ec.com.saviasoft.air.collection.model.pojo.AirQualityMeasurement;
import ec.com.saviasoft.air.collection.service.AirQualityMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/air-quality-measurement")
@RequiredArgsConstructor
public class AirQualityMeasurementController {

    private final AirQualityMeasurementService airQualityMeasurementService;

    @PostMapping
    public ResponseEntity<AirQualityMeasurement> createAirQualityMeasurement(
            @RequestBody AirQualityMeasurement airQualityMeasurement,
            @RequestHeader("uid") String uid
    ) {
        return ResponseEntity.ok(airQualityMeasurementService.createAirQualityMeasurement(
                airQualityMeasurement,
                uid
        ));
    }

}
