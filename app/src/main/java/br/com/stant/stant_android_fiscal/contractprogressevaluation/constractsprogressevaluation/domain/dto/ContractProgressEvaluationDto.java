package br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.domain.dto;

import java.util.Date;

/**
 * Created by denisvieira on 16/05/17.
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
        this.mCreatedAt      = createdAt;
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

    public Integer getItemsNotInspected() {
        return mItemsNotInspected;
    }

    public Integer getItemsApproved() {
        return mItemsApproved;
    }

    public Integer getItemsDisapproved() {
        return mItemsDisapproved;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public Integer getTotalItems() {
        return mItemsApproved+mItemsDisapproved+mItemsNotInspected;
    }

    public String getItemsNotInspectedString() {
        return addCharPercenter(mItemsNotInspected);
    }

    public String getItemsApprovedString() {
        return addCharPercenter(mItemsApproved);
    }

    public String getItemsDisapprovedString() {
        return addCharPercenter(mItemsDisapproved);
    }

    public String addCharPercenter(Integer value){
        return value + "%";
    }

    public Integer getApprovedPercentOfContractProgressEvaluation(){
        return mItemsApproved*100/getTotalItems();
    }

    public Integer getDisapprovedPercentOfContractProgressEvaluation(){
        return mItemsDisapproved*100/getTotalItems();
    }

    public Integer getNotInspectedPercentOfContractProgressEvaluation(){
        return mItemsNotInspected*100/getTotalItems();
    }

    public String getApprovedPercentOfContractProgressEvaluationString(){
        return addCharPercenter(getApprovedPercentOfContractProgressEvaluation());
    }

    public String getDisapprovedPercentOfContractProgressEvaluationString(){
        return addCharPercenter(getDisapprovedPercentOfContractProgressEvaluation());
    }

    public String getNotInspectedPercentOfContractProgressEvaluationString(){
        return addCharPercenter(getNotInspectedPercentOfContractProgressEvaluation());
    }

}
