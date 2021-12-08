package me.nevzatcirak.service.approval.support.mongo.repository;

import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public interface ProcessMongoRepository extends MongoRepository<ProcessDocument, String> {
    Optional<ProcessDocument> findByDocumentIdAndDocumentType(String documentId, String documentType);
}
