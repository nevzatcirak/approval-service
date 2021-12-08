package me.nevzatcirak.service.approval.support.mongo;

import me.nevzatcirak.service.approval.support.mongo.model.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component("sequenceGenerator")
public class SequenceGenerator {
    private MongoOperations mongoOperations;

    @Autowired
    public void setMongoOperations(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Long generateIdSequence(String sequenceName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                query(where("_id").is(sequenceName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class
        );
        return !Objects.isNull(counter) ? counter.getSeq(): 1;
    }
}
