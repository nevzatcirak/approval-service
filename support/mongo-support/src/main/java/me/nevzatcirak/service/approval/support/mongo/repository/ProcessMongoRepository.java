package me.nevzatcirak.service.approval.support.mongo.repository;

import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public interface ProcessMongoRepository extends MongoRepository<ProcessDocument, String> {
    @Query(value = "{'documentId' : ?0, 'documentType' : ?1}")
    Optional<ProcessDocument> findByDocumentIdAndDocumentType(String documentId, String documentType);

    @Query(value = "{'documentType' : ?0, 'documentId' : {'$in' : ?1}}", fields = "{'id':1, 'documentType':1, " +
            "'documentId':1, 'status':1}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeAndIdList(String documentType, Set<String> documentIds);

    @Query(value = "{'documentType' : ?0, 'status': ?1}", fields = "{'id':1, 'documentType':1, " +
            "'documentId':1, 'status':1}")
    Optional<Set<ProcessDocument>> findAllByFilteringStateAndDocument(String documentType, int state);

    //Optional<ProcessDocument> findNextApprover(String processId);
}
