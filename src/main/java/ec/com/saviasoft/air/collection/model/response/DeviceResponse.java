package ec.com.saviasoft.air.collection.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponse {

    private Integer id;

    private Integer userId;

    private String name;

    private String serialNumber;

    private String location;

    private String uid;

    private Boolean status;

    private String createdDate;

    private String updatedDate;

}
