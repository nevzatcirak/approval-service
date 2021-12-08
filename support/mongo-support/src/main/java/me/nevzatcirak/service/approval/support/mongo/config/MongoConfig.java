package me.nevzatcirak.service.approval.support.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 08/12/2021
 */
@Configuration
@EnableMongoRepositories(basePackages = "me.nevzatcirak.service.approval.support.mongo.repository")
public class MongoConfig {
}
