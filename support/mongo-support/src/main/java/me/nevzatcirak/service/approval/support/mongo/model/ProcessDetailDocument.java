package me.nevzatcirak.service.approval.support.mongo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Document(collection = "approval-process-detail")
public class ProcessDetailDocument implements Serializable {
    @Id
    private String id;

    @Field("sequenceNumber")
    private Integer sequenceNumber;

    @Field("username")
    private String username;

    @Field("status")
    private Integer status;

    public ProcessDetailDocument() {
    }

    @PersistenceConstructor
    public ProcessDetailDocument(@Value("#root.seqNo ?: 1") final Integer sequenceNumber, final String username, final Integer status) {
        this.sequenceNumber = sequenceNumber;
        this.username = username;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProcessDetailDocument{" +
                "id='" + id + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
