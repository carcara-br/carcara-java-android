package br.com.stant.stant_android_fiscal.domain.builders;

import java.util.Date;

import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.dto.ContractProgressEvaluationResponse;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.ItemOfContractProgressEvaluationResponse;

/**
 * Created by denisvieira on 16/05/17.
 */

public class ItemOfContractProgressEvaluationResponseBuilder {

    private int id;
    private int performedPercentage;
    private String serviceTitle;
    private String place;
    private int status;
    private Date beginAt;
    private Date endAt;


    public ItemOfContractProgressEvaluationResponse build() {
        return new ItemOfContractProgressEvaluationResponse(
                id, performedPercentage, serviceTitle, place, status, beginAt, endAt
        );
    }

    public ItemOfContractProgressEvaluationResponseBuilder id(int id) {
        this.id = id;
        return this;
    }

    public ItemOfContractProgressEvaluationResponseBuilder performedPercentage(int performedPercentage) {
        this.performedPercentage = performedPercentage;
        return this;
    }

    public ItemOfContractProgressEvaluationResponseBuilder serviceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
        return this;
    }

    public ItemOfContractProgressEvaluationResponseBuilder place(String place) {
        this.place = place;
        return this;
    }

    public ItemOfContractProgressEvaluationResponseBuilder status(int status) {
        this.status = status;
        return this;
    }

    public ItemOfContractProgressEvaluationResponseBuilder beginAt(Date beginAt) {
        this.beginAt = beginAt;
        return this;
    }

    public ItemOfContractProgressEvaluationResponseBuilder endAt(Date endAt) {
        this.endAt = endAt;
        return this;
    }
}
