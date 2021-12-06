package me.nevzatcirak.service.approval.support.mongo.bridge;

import me.nevzatcirak.service.approval.api.repository.ProcessRepository;
import me.nevzatcirak.service.approval.support.mongo.converter.ProcessConverter;
import me.nevzatcirak.service.approval.support.mongo.converter.ProcessDetailConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Repository
public class ProcessMongoRepositoryBridge implements ProcessRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProcessMongoRepositoryBridge.class);

    private ProcessConverter processConverter;
    private ProcessDetailConverter detailConverter;


    @Autowired
    public void setProcessConverter(ProcessConverter converter) {
        this.processConverter = converter;
    }

    @Autowired
    public void setProcessDetailConverter(ProcessDetailConverter detailConverter) {
        this.detailConverter = detailConverter;
    }
}
