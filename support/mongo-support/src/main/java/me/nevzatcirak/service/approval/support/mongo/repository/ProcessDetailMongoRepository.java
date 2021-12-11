package me.nevzatcirak.service.approval.support.mongo.repository;

import me.nevzatcirak.service.approval.support.mongo.model.ProcessDetailDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 08/12/2021
 */
public interface ProcessDetailMongoRepository extends MongoRepository<ProcessDetailDocument, Long> {
}
