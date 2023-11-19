package ec.com.saviasoft.air.collection.facade;

import ec.com.saviasoft.air.collection.model.response.DeviceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceFacade {

    private final RestTemplate restTemplate;

    public ResponseEntity<DeviceResponse> getDevice(String uid) {
        try {
            String url = "http://localhost:8090/api/v1/devices/byUid";
            return restTemplate.getForEntity(url + "/" + uid, DeviceResponse.class);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
