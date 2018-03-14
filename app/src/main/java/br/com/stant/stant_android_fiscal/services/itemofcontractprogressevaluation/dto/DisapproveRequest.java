package br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto;

/**
 * Created by denisvieira on 21/05/17.
 */

public class DisapproveRequest {

    private final Integer contract_progress_evaluation_id;
    private final Integer item_id;
    private final String observation;

    public DisapproveRequest(Integer contract_progress_evaluation_id, Integer item_id, String observation) {
        this.contract_progress_evaluation_id = contract_progress_evaluation_id;
        this.item_id = item_id;
        this.observation = observation;
    }

    public Integer getContractProgressEvaluationId() {
        return contract_progress_evaluation_id;
    }

    public Integer getItemId() {
        return item_id;
    }

    public String getObservation() {
        return observation;
    }

}
