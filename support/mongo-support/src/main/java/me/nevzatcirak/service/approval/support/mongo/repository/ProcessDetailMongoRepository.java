package me.nevzatcirak.service.approval.support.mongo.repository;

import me.nevzatcirak.service.approval.support.mongo.model.ProcessDetailDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 08/12/2021
 */
public interface ProcessDetailMongoRepository extends MongoRepository<ProcessDetailDocument, Long> {
    @Query(value = "{'username' : ?0, '$or':[{'active' : true},{'$or': [{'status': 1},{'status': 0}]}]}", fields = "{'processId': 1}")
    Optional<List<ProcessDetailDocument>> findProcessIdsByEligibleUsername(String username);
}

