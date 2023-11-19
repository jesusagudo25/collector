package ec.com.saviasoft.air.collection.service;

import ec.com.saviasoft.air.collection.data.AirQualityMeasurementRepository;
import ec.com.saviasoft.air.collection.facade.DeviceFacade;
import ec.com.saviasoft.air.collection.model.pojo.AirQualityMeasurement;
import ec.com.saviasoft.air.collection.model.response.DeviceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AirQualityMeasurementService {

    @Autowired
    private AirQualityMeasurementRepository airQualityMeasurementRepository;

    @Autowired
    private DeviceFacade deviceFacade;

    public AirQualityMeasurement createAirQualityMeasurement(AirQualityMeasurement airQualityMeasurement, String uid) {

        ResponseEntity<DeviceResponse> deviceResponse = deviceFacade.getDevice(uid);

        if(deviceResponse.getStatusCode().isError()){
            throw new RuntimeException("Device not found");
        }

        //GET BODY
        DeviceResponse body = deviceResponse.getBody();

        if(body == null){
            throw new RuntimeException("Device not found");
        }

        Integer deviceID = body.getId();

        return airQualityMeasurementRepository.insert(new AirQualityMeasurement(
                airQualityMeasurement.getTimestamp(),
                airQualityMeasurement.getMethaneValue(),
                airQualityMeasurement.getMethaneAlert(),
                airQualityMeasurement.getPpmValue(),
                airQualityMeasurement.getPpmAlert(),
                airQualityMeasurement.getCo2Value(),
                airQualityMeasurement.getCo2Alert(),
                deviceID
        ));

    }
}
