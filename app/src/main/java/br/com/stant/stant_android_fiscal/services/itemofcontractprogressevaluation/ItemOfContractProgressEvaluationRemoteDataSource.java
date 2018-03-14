package br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation;

import java.util.List;

import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ItemOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;

/**
 * Created by denisvieira on 16/05/17.
 */

public interface ItemOfContractProgressEvaluationRemoteDataSource {

    interface LoadItemsOfContractProgressEvaluationCallback {
        void onItemsOfContractProgressEvaluationLoaded(List<ItemOfContractProgressEvaluation> itemsOfContractProgressEvaluation);
        void onEmptyData();
        void onFailed(RemoteErrorResponse errorResponse);
    }

    interface SetSituationCallback {
        void onSuccess();
        void onError(RemoteErrorResponse errorResponse);
    }

    void allByContractProgressEvaluation(int contractProgressEvaluationId,
                                        final LoadItemsOfContractProgressEvaluationCallback callback);

    void disapproveItemOfConstractProgressEvaluation(int itemOfConstractProgressEvaluationId,
                                                     int contractProgressEvaluationId,
                                                     String observation,
                                                     final SetSituationCallback callback);

    void approveItemOfConstractProgressEvaluation(int itemOfConstractProgressEvaluationId,
                                                  int contractProgressEvaluationId,
                                                  final SetSituationCallback callback);
}
