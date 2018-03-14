package br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by denisvieira on 16/05/17.
 */

public class ContractProgressEvaluation implements Serializable {

    private final int id;
    private final String title;
    private final Integer status;
    private final Integer itemsNotInspected;
    private final Integer itemsApproved;
    private final Integer itemsDisapproved;
    private final Date createdAt;

    public ContractProgressEvaluation(int id, String title, Integer status, Integer itemsNotInspected, Integer itemsApproved, Integer itemsDisapproved, Date createdAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.itemsNotInspected = itemsNotInspected;
        this.itemsApproved = itemsApproved;
        this.itemsDisapproved = itemsDisapproved;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getItemsNotInspected() {
        return itemsNotInspected;
    }

    public Integer getItemsApproved() {
        return itemsApproved;
    }

    public Integer getItemsDisapproved() {
        return itemsDisapproved;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
