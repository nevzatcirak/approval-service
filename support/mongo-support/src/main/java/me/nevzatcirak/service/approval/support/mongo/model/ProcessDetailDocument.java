package me.nevzatcirak.service.approval.support.mongo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Document(collection = "approval_process_detail")
@CompoundIndexes({
        @CompoundIndex(name = "detail_active_process_id", def = "{'active': 1, 'processId':1}")
})
public class ProcessDetailDocument implements Serializable {
    @Transient
    public static final String SEQUENCE_NAME = "approval_process_detail_sequence";

    @Id
    private Long id;

    @Field("processId")
    private Long processId;

    @Field("sequenceNumber")
    private Integer sequenceNumber;

    @Field("username")
    private String username;

    @Field("status")
    private Integer status;

    @Field("active")
    private Boolean active;

    public ProcessDetailDocument() {
    }

    @PersistenceConstructor
    public ProcessDetailDocument(@Value("#root.sequenceNumber ?: 1") final Integer sequenceNumber, final String username, final Integer status) {
        this.sequenceNumber = sequenceNumber;
        this.username = username;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
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

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProcessDetailDocument{" +
                "id=" + id +
                ", processId=" + processId +
                ", sequenceNumber=" + sequenceNumber +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessDetailDocument that = (ProcessDetailDocument) o;
        return id.equals(that.id) &&
                processId.equals(that.processId) &&
                sequenceNumber.equals(that.sequenceNumber) &&
                username.equals(that.username) &&
                status.equals(that.status) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, processId, sequenceNumber, username, status);
    }
}
