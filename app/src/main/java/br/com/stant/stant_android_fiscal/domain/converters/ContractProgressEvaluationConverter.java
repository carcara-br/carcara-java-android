package br.com.stant.stant_android_fiscal.domain.converters;

import java.util.ArrayList;
import java.util.List;

import br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.domain.dto.ContractProgressEvaluationDto;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.dto.ContractProgressEvaluationResponse;

/**
 * Created by denisvieira on 16/05/17.
 */

public class ContractProgressEvaluationConverter {


    public static List<ContractProgressEvaluation> convertResponsesToEntities(List<ContractProgressEvaluationResponse> contractProgressEvaluationResponses) {
        if(contractProgressEvaluationResponses == null) return new ArrayList<>();

        List<ContractProgressEvaluation> contractsProgressEvaluation = new ArrayList<>();

        for (ContractProgressEvaluationResponse response : contractProgressEvaluationResponses) {
            ContractProgressEvaluation contractProgressEvaluation = convertResponseToEntity(response);
            if(contractProgressEvaluation!= null)
                contractsProgressEvaluation.add(contractProgressEvaluation);
        }
        return contractsProgressEvaluation;
    }

    public static ContractProgressEvaluation convertResponseToEntity(ContractProgressEvaluationResponse response) {
        if(response == null) return null;

        return new ContractProgressEvaluation(response.getId(), response.getTitle(), response.getStatus(),
            response.getItemsNotInspected(), response.getItemsApproved(), response.getItemsDisapproved(),response.getCreatedAt()
        );
    }

}
