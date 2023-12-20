package ec.com.saviasoft.air.collection.data;

import ec.com.saviasoft.air.collection.model.pojo.AirQualityMeasurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface AirQualityMeasurementRepository extends MongoRepository<AirQualityMeasurement, ObjectId> {

    List<AirQualityMeasurement> findByUserId(Integer userId);

    @Query(value = "{ 'userId': ?0 }", sort = "{ 'methaneValue': -1, 'ppmValue': -1, 'co2Value': -1 }")
    List<AirQualityMeasurement> findTop5ByUserIdOrderByMethaneValueDescPpmValueDescCo2ValueDesc(Integer userId, Pageable pageable);

    @Query(value = "{ }", sort = "{ 'methaneValue': -1, 'ppmValue': -1, 'co2Value': -1 }")
    List<AirQualityMeasurement> findTop5ByOrderByMethaneValueDescPpmValueDescCo2ValueDesc(Pageable pageable);

}
