package ec.com.saviasoft.air.collection.facade;

import ec.com.saviasoft.air.collection.model.response.DeviceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceFacade {

    private final RestTemplate restTemplate;

    @Value ("${saviasoft.app.entity.device.findByUid.url}")
    private String findByUidUrl;

    public ResponseEntity<DeviceResponse> getDevice(String uid) {
        try {
            return restTemplate.getForEntity(String.format(findByUidUrl, uid), DeviceResponse.class);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
