package me.nevzatcirak.service.approval.controller.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
@ApiModel("Process Query Request Model")
public class QueryRequest {
    @ApiModelProperty("Filters only waiting status process")
    private Boolean onlyWaiting;
    @ApiModelProperty("Filters according to these ids. If it is empty, Query will work on all document.")
    private Set<String> documentIds;

    public Boolean isOnlyWaiting() {
        return onlyWaiting;
    }

    public void setOnlyWaiting(Boolean onlyWaiting) {
        this.onlyWaiting = onlyWaiting;
    }

    public Set<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(Set<String> documentIds) {
        this.documentIds = documentIds;
    }
}
