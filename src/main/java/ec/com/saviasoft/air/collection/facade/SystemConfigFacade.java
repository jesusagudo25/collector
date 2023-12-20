package ec.com.saviasoft.air.collection.facade;

import ec.com.saviasoft.air.collection.model.response.SystemConfigResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemConfigFacade {

    private final RestTemplate restTemplate;

    @Value("${saviasoft.app.entity.system-config.get.url}")
    private String getSystemConfigUrl;

    public ResponseEntity<SystemConfigResponse> getSystemConfig() {
        try {
            return restTemplate.getForEntity(getSystemConfigUrl, SystemConfigResponse.class);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
