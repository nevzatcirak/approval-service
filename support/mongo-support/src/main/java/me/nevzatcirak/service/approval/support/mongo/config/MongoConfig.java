package me.nevzatcirak.service.approval.support.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 08/12/2021
 */
@Configuration
@EnableMongoRepositories(basePackages = "me.nevzatcirak.service.approval.support.mongo.repository")
public class MongoConfig {
}
