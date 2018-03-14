package br.com.stant.stant_android_fiscal.domain.converters;

import java.util.ArrayList;
import java.util.List;

import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ItemOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.ItemOfContractProgressEvaluationResponse;

/**
 * Created by denisvieira on 19/05/17.
 */

public class ItemOfContractProgressEvaluationConverter {


    public static List<ItemOfContractProgressEvaluation> convertResponsesToEntities(List<ItemOfContractProgressEvaluationResponse> itemsOfContractProgressEvaluationResponse) {
        if(itemsOfContractProgressEvaluationResponse == null) return new ArrayList<>();

        List<ItemOfContractProgressEvaluation> contractsProgressEvaluation = new ArrayList<>();

        for (ItemOfContractProgressEvaluationResponse response : itemsOfContractProgressEvaluationResponse) {
            ItemOfContractProgressEvaluation itemOfContractProgressEvaluation = convertResponseToEntity(response);
            if(itemOfContractProgressEvaluation!= null)
                contractsProgressEvaluation.add(itemOfContractProgressEvaluation);
        }
        return contractsProgressEvaluation;
    }

    public static ItemOfContractProgressEvaluation convertResponseToEntity(ItemOfContractProgressEvaluationResponse response) {
        if(response == null) return null;

        return new ItemOfContractProgressEvaluation(response.getId(), response.getPerformedPercentage(), response.getServiceTitle(),
                response.getPlace(), response.getStatus(), response.getBeginAt(),response.getEndAt()
        );
    }
}
