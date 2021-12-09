package me.nevzatcirak.service.approval.controller.model;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
public class StatusQueryRequest {
    private Boolean onlyWaiting;
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
