package ec.com.saviasoft.air.collection.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemConfigResponse {

    private Integer id;

    private Double maxMetano;

    private Double maxCO2;

    private Double maxPPM;
}
