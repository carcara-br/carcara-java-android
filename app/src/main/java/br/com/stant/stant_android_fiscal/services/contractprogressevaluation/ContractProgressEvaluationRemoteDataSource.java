package br.com.stant.stant_android_fiscal.services.contractprogressevaluation;

import java.util.List;

import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;

/**
 * Created by denisvieira on 16/05/17.
 */

public interface ContractProgressEvaluationRemoteDataSource {

    interface LoadContractsProgressEvaluationCallback {
        void onContractsProgressEvaluationLoaded(List<ContractProgressEvaluation> constructionSites);
        void onEmptyData();
        void onFailed(RemoteErrorResponse errorResponse);
    }

    void allByConstructionSiteAndStatus(int constructionSiteId,
                                        ContractProgressEvaluationStatusEnum contractProgressEvaluationStatusEnum,
                                        final LoadContractsProgressEvaluationCallback callback);
}
