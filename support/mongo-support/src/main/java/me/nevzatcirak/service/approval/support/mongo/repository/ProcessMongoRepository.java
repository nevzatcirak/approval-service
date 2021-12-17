package me.nevzatcirak.service.approval.support.mongo.repository;

import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 06/12/2021
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

    @Query(value = "{'documentType' : ?0, 'status': ?2, '$or':[{'creator': ?1},{'_id':{'$in': ?3}}]}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeStateAndUsername(String documentType,
                                                                         String username,
                                                                         int state,
                                                                         List<Long> legitProcessIds);

    @Query(value = "{'documentType' : ?0, '$or': [{'creator': ?1}, {'_id':{'$in': ?2}}]}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeAndUsername(String documentType,
                                                                    String username,
                                                                    List<Long> legitProcessIds);

    @Query(value = "{'documentType' : ?0, 'documentId':{'$in': ?2}, '$or':[{'creator': ?1},{'_id':{'$in': ?3}}]}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeIdsAndUsername(String documentType,
                                                                       String username,
                                                                       Set<String> documentIds,
                                                                       List<Long> legitProcessIds);

    @Query(value = "{'documentType' : ?0, 'status': ?2, 'documentId':{'$in': ?3}, '$or':[{'creator': ?1},{'_id':{'$in': ?4}}]}")
    Optional<Set<ProcessDocument>> findAllByDocumentTypeIdsAndUsername(String documentType,
                                                                       String username,
                                                                       int state,
                                                                       Set<String> documentIds,
                                                                       List<Long> legitProcessIds);
}
