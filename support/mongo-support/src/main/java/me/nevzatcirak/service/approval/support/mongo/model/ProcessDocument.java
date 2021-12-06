package me.nevzatcirak.service.approval.support.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Document(collection = "approval-process")
@CompoundIndexes({
        @CompoundIndex(name = "document_type_id", def = "{'type': 1, 'docId':1}"),
        @CompoundIndex(name = "id_state", def = "{'id': 1, 'states.state': 1}"),
        @CompoundIndex(name = "id_details", def = "{'id': 1, 'details.status': 1}"),

})
public class ProcessDocument implements Serializable {
    @Id
    private String id;

    @Indexed
    @Field("type")
    private String documentType;

    @Indexed(direction = IndexDirection.ASCENDING)
    @Field("docId")
    private String documentId;

    @DBRef
    @Field("states")
    private Set<ProcessStateDocument> states;

    @DBRef
    @Field("details")
    private Set<ProcessDetailDocument> details;

    public ProcessDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Set<ProcessStateDocument> getStates() {
        return states;
    }

    public void setStates(Set<ProcessStateDocument> states) {
        this.states = states;
    }

    public Set<ProcessDetailDocument> getDetails() {
        return details;
    }

    public void setDetails(Set<ProcessDetailDocument> details) {
        this.details = details;
    }
}
