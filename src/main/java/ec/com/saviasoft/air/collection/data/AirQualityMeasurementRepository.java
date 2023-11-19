package ec.com.saviasoft.air.collection.data;

import ec.com.saviasoft.air.collection.model.pojo.AirQualityMeasurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityMeasurementRepository extends MongoRepository<AirQualityMeasurement, ObjectId> {

}
