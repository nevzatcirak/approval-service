package me.nevzatcirak.service.approval.support.mongo.repository;

import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public interface ProcessMongoRepository extends MongoRepository<ProcessDocument, Long> {
    @Query(value = "{'documentId' : ?0, 'documentType' : ?1}")
    Optional<ProcessDocument> findByDocumentIdAndDocumentType(String documentId, String documentType);

    @Query(value = "{'documentType' : ?0, 'documentId' : {'$in' : ?1}}", sort = "{'_id' : 1}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeAndIdList(String documentType, Set<String> documentIds);

    @Query(value = "{'documentType' : ?0, 'status': ?1, 'documentId' : {'$in' : ?2}}", sort = "{'_id' : 1}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeStatusAndIdList(String documentType, int state, Set<String> documentIds);

    @Query(value = "{'documentType' : ?0, 'status': ?1}")
    Optional<Set<ProcessDocument>> findAllByFilteringStateAndDocument(String documentType, int state);

    @Query(value = "{'documentType' : ?0}")
    Optional<Set<ProcessDocument>> findAllByDocumentType(String documentType);

    @Query(value = "{'documentType' : ?0, '_id':{'$in': ?1}}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeAndProcessIds(String documentType,
                                                                      List<Long> legitProcessIds);

    @Query(value = "{'documentType' : ?0, 'documentId':{'$in': ?1}, '_id':{'$in': ?2}}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeIdsAndProcessIds(String documentType,
                                                                         Set<String> documentIds,
                                                                         List<Long> legitProcessIds);
}
