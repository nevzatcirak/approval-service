package me.nevzatcirak.service.approval.controller.model;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
public class StatusQueryRequest {
    private String type;
    private Set<String> idList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getIdList() {
        return idList;
    }

    public void setIdList(Set<String> idList) {
        this.idList = idList;
    }
}
