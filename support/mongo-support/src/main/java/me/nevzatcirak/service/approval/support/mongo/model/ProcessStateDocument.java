package me.nevzatcirak.service.approval.support.mongo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Document(collection = "approval-process-state")
public class ProcessStateDocument implements Serializable {
    @Id
    private String id;

    @Field("state_index")
    private Integer index;

    @Field("state")
    private String state;

    public ProcessStateDocument() {
    }

    @PersistenceConstructor
    public ProcessStateDocument(@Value("#root.state_index ?: 1") final Integer index, final String state) {
        this.index = index;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
