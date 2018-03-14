package br.com.stant.stant_android_fiscal.services.contractprogressevaluation.dto;

import java.util.Date;

/**
 * Created by denisvieira on 16/05/17.
 */

public class ContractProgressEvaluationResponse {

    private final int id;
    private final String title;
    private final int status;
    private final Integer items_not_inspected;
    private final Integer items_approved;
    private final Integer items_disapproved;
    private final Date created_at;

    public ContractProgressEvaluationResponse(int id, String title, int status,
                                              Integer itemsNotInspected,
                                              Integer itemsApproved,
                                              Integer itemsDisapproved,
                                              Date createdAt) {
        this.id                  = id;
        this.title               = title;
        this.status              = status;
        this.items_not_inspected = itemsNotInspected;
        this.items_approved      = itemsApproved;
        this.items_disapproved   = itemsDisapproved;
        this.created_at          =  createdAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public Integer getItemsNotInspected() {
        return items_not_inspected;
    }

    public Integer getItemsApproved() {
        return items_approved;
    }

    public Integer getItemsDisapproved() {
        return items_disapproved;
    }

    public Date getCreatedAt() {
        return created_at;
    }
}
