package ec.com.saviasoft.air.collection.model.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "airQualityMeasurements")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirQualityMeasurement {

    @Id
    private ObjectId id;

    @JsonProperty("fecha-hora")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date timestamp;

    @JsonProperty("valor-methano")
    private Double methaneValue;

    @JsonProperty("alerta-methano")
    private Boolean methaneAlert;

    @JsonProperty("ppm")
    private Double ppmValue;

    @JsonProperty("alerta-ppm")
    private Boolean ppmAlert;

    @JsonProperty("co2")
    private Double co2Value;

    @JsonProperty("alerta-co2")
    private Boolean co2Alert;

    @JsonProperty("id-dispositivo")
    private Integer deviceId;

    public AirQualityMeasurement(
            Date timestamp,
            Double methaneValue,
            Boolean methaneAlert,
            Double ppmValue,
            Boolean ppmAlert,
            Double co2Value,
            Boolean co2Alert,
            Integer deviceId
    ) {
        this.timestamp = timestamp;
        this.methaneValue = methaneValue;
        this.methaneAlert = methaneAlert;
        this.ppmValue = ppmValue;
        this.ppmAlert = ppmAlert;
        this.co2Value = co2Value;
        this.co2Alert = co2Alert;
        this.deviceId = deviceId;
    }
}
