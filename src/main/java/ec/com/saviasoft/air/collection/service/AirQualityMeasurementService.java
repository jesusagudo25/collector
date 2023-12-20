package ec.com.saviasoft.air.collection.service;

import ec.com.saviasoft.air.collection.data.AirQualityMeasurementRepository;
import ec.com.saviasoft.air.collection.facade.DeviceFacade;
import ec.com.saviasoft.air.collection.facade.SystemConfigFacade;
import ec.com.saviasoft.air.collection.model.pojo.AirQualityMeasurement;
import ec.com.saviasoft.air.collection.model.response.DeviceResponse;
import ec.com.saviasoft.air.collection.model.response.SystemConfigResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AirQualityMeasurementService {

    @Autowired
    private AirQualityMeasurementRepository airQualityMeasurementRepository;

    @Autowired
    private DeviceFacade deviceFacade;

    @Autowired
    private SystemConfigFacade systemConfigFacade;

    public List<AirQualityMeasurement> findByUserId(Integer userId) {
        return airQualityMeasurementRepository.findByUserId(userId);
    }

    public List<AirQualityMeasurement> getAirQualityMeasurement() {
        return airQualityMeasurementRepository.findAll();
    }

    public List<AirQualityMeasurement> getTop5ByUserId(Integer userId) {
        Pageable topFive = PageRequest.of(0, 5);
        return airQualityMeasurementRepository.findTop5ByUserIdOrderByMethaneValueDescPpmValueDescCo2ValueDesc(userId, topFive);
    }

    public List<AirQualityMeasurement> getTop5() {
        Pageable topFive = PageRequest.of(0, 5);
        return airQualityMeasurementRepository.findTop5ByOrderByMethaneValueDescPpmValueDescCo2ValueDesc(topFive);
    }

    public AirQualityMeasurement createAirQualityMeasurement(AirQualityMeasurement airQualityMeasurement, String uid) {

        ResponseEntity<DeviceResponse> deviceResponse = deviceFacade.getDevice(uid);
        ResponseEntity<SystemConfigResponse> systemConfigResponse = systemConfigFacade.getSystemConfig();

        if(deviceResponse.getStatusCode().isError()){
            throw new RuntimeException("El dispositivo no existe");
        }

        if(systemConfigResponse.getStatusCode().isError()){
            throw new RuntimeException("La configuracion del sistema no existe");
        }

        //GET BODY
        DeviceResponse body = deviceResponse.getBody();
        SystemConfigResponse systemConfigBody = systemConfigResponse.getBody();

        if(body == null){
            throw new RuntimeException("El dispositivo no existe");
        }

        if(systemConfigBody == null){
            throw new RuntimeException("La configuracion del sistema no existe");
        }

        Integer deviceID = body.getId();
        String deviceName = body.getName();
        Integer userID = body.getUserId();
        String userName = body.getUserName();

        boolean methaneAlert = false;
        boolean ppmAlert = false;
        boolean co2Alert = false;

        if(airQualityMeasurement.getMethaneValue() > systemConfigBody.getMaxMetano()){
            methaneAlert = true;
        }

        if(airQualityMeasurement.getPpmValue() > systemConfigBody.getMaxPPM()){
            ppmAlert = true;
        }

        if(airQualityMeasurement.getCo2Value() > systemConfigBody.getMaxCO2()){
            co2Alert = true;
        }

        return airQualityMeasurementRepository.insert(new AirQualityMeasurement(
                airQualityMeasurement.getTimestamp(),
                airQualityMeasurement.getMethaneValue(),
                methaneAlert,
                airQualityMeasurement.getPpmValue(),
                ppmAlert,
                airQualityMeasurement.getCo2Value(),
                co2Alert,
                deviceID,
                deviceName,
                userID,
                userName
        ));

    }
}
