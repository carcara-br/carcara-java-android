package br.com.stant.stant_android_fiscal.domain.builders;

import java.util.Date;

import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.dto.ContractProgressEvaluationResponse;
import br.com.stant.stant_android_fiscal.util.GuidGenerator;

/**
 * Created by denisvieira on 16/05/17.
 */

public class ContractProgressEvaluationResponseBuilder {

    private int mId;
    private String mTitle;
    private Integer mStatus;
    private Integer mItemsNotInspected;
    private Integer mItemsApproved;
    private Integer mItemsDisapproved;
    private Date mCreatedAt;

    public ContractProgressEvaluationResponseBuilder() {
        mId                 = 0;
        mTitle              = "Fake Title";
        mStatus             = ContractProgressEvaluationStatusEnum.PROGRESS.getValue();
        mItemsNotInspected  = 10;
        mItemsApproved      = 3;
        mItemsDisapproved   = 2;
    }

    public ContractProgressEvaluationResponse build() {
        return new ContractProgressEvaluationResponse(
            mId, mTitle, mStatus, mItemsNotInspected, mItemsApproved, mItemsDisapproved, mCreatedAt
        );
    }

    public ContractProgressEvaluationResponseBuilder id(int id) {
        this.mId = id;
        return this;
    }

    public ContractProgressEvaluationResponseBuilder title(String title) {
        this.mTitle = title;
        return this;
    }

    public ContractProgressEvaluationResponseBuilder status(Integer status) {
        this.mStatus = status;
        return this;
    }

    public ContractProgressEvaluationResponseBuilder itemsNotInspected(Integer itemsNotInspected) {
        this.mItemsNotInspected = itemsNotInspected;
        return this;
    }

    public ContractProgressEvaluationResponseBuilder itemsApproved(Integer itemsApproved) {
        this.mItemsApproved = itemsApproved;
        return this;
    }

    public ContractProgressEvaluationResponseBuilder itemsDisapproved(Integer itemsDisapproved) {
        this.mItemsDisapproved = itemsDisapproved;
        return this;
    }

    public ContractProgressEvaluationResponseBuilder createAt(Date createdAt) {
        this.mCreatedAt = createdAt;
        return this;
    }


}
