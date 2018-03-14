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

public class ApproveItemOfContractProgressEvaluation {

    private final ItemOfContractProgressEvaluationRemoteDataSource mItemsOfConstractProgressEvaluationRemoteDataSource;

    public ApproveItemOfContractProgressEvaluation(@NonNull ItemOfContractProgressEvaluationRemoteDataSource itemOfContractProgressEvaluationRemoteDataSource) {
        mItemsOfConstractProgressEvaluationRemoteDataSource = checkNotNull(itemOfContractProgressEvaluationRemoteDataSource);
    }

    public void approve(int itemOfConstractProgressEvaluationId,
                        int contractPprogressEvaluationId,
                        UseCase.UseCaseCallback useCaseCallback) {
        mItemsOfConstractProgressEvaluationRemoteDataSource
                .approveItemOfConstractProgressEvaluation(itemOfConstractProgressEvaluationId,
                        contractPprogressEvaluationId, new ItemOfContractProgressEvaluationRemoteDataSource.SetSituationCallback() {
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
