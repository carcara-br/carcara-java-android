package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by denisvieira on 19/05/17.
 */

public class ContractProgressEvaluationDto {

    private final int mId;
    private final String mTitle;
    private final int mStatus;
    private final Integer mItemsNotInspected;
    private final Integer mItemsApproved;
    private final Integer mItemsDisapproved;
    private final Date mCreatedAt;

    public ContractProgressEvaluationDto(int id, String title, int status, Integer itemsNotInspected, Integer itemsApproved, Integer itemsDisapproved, Date createdAt) {
        this.mId = id;
        this.mTitle = title;
        this.mStatus = status;
        this.mItemsNotInspected = itemsNotInspected;
        this.mItemsApproved = itemsApproved;
        this.mItemsDisapproved = itemsDisapproved;
        this.mCreatedAt       = createdAt;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getStatus() {
        return mStatus;
    }

    public int getStatusString() {
        return mStatus;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public Integer getItemsNotInspected() {
        return mItemsNotInspected;
    }

    public Integer getItemsApproved() {
        return mItemsApproved;
    }

    public Integer getItemsDisapproved() {
        return mItemsDisapproved;
    }

    public Integer getTotalItems() {
        return mItemsApproved+mItemsDisapproved+mItemsNotInspected;
    }

    public String getCreatedAtString(){
        return new SimpleDateFormat("dd/MM/yy").format(new Date());
    }

    public Integer getItemsPerformed(){
        return mItemsApproved+mItemsDisapproved;
    }

    public String getItemsPerformedString(){
        return getItemsPerformed()+"";
    }

    public Integer getItemsRemaining(){
        return (getTotalItems()-getItemsPerformed());
    }

}
