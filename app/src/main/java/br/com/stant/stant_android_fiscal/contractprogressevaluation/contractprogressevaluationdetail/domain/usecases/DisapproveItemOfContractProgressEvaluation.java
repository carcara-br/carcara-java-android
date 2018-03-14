package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.usecases;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.stant.stant_android_fiscal.UseCase;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ItemOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.ItemOfContractProgressEvaluationRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 17/05/17.
 */

public class DisapproveItemOfContractProgressEvaluation {

    private final ItemOfContractProgressEvaluationRemoteDataSource mItemsOfConstractProgressEvaluationRemoteDataSource;

    public DisapproveItemOfContractProgressEvaluation(@NonNull ItemOfContractProgressEvaluationRemoteDataSource itemOfContractProgressEvaluationRemoteDataSource) {
        mItemsOfConstractProgressEvaluationRemoteDataSource = checkNotNull(itemOfContractProgressEvaluationRemoteDataSource);
    }

    public void disapprove(int itemOfConstractProgressEvaluationId,
                           int contractProgressEvaluationId,
                           String observation,
                           UseCase.UseCaseCallback useCaseCallback) {

        mItemsOfConstractProgressEvaluationRemoteDataSource
                .disapproveItemOfConstractProgressEvaluation(itemOfConstractProgressEvaluationId,
                        contractProgressEvaluationId,
                        observation,
                        new ItemOfContractProgressEvaluationRemoteDataSource.SetSituationCallback() {
                            @Override
                            public void onSuccess() {
                                useCaseCallback.onSuccess();
                            }

                            @Override
                            public void onError(RemoteErrorResponse errorResponse) {
                                useCaseCallback.onError(errorResponse.getDetailMessage());
                            }
                        });
    }
}
