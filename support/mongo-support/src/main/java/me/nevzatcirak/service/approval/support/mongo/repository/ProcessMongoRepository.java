package me.nevzatcirak.service.approval.support.mongo.repository;

import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public interface ProcessMongoRepository extends ReactiveMongoRepository<ProcessDocument, String> {
}
