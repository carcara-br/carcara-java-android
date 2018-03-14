package br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto;

/**
 * Created by denisvieira on 21/05/17.
 */

public class ApproveRequest {

    private final Integer contract_progress_evaluation_id;
    private final Integer item_id;

    public ApproveRequest(Integer contractProgressEvaluationId, Integer itemId) {
        this.contract_progress_evaluation_id = contractProgressEvaluationId;
        this.item_id = itemId;
    }

    public Integer getContractProgressEvaluationId() {
        return contract_progress_evaluation_id;
    }

    public Integer getItemId() {
        return item_id;
    }
}
