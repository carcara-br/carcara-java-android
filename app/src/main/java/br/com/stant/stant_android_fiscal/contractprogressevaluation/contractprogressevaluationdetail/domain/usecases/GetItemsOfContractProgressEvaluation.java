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

public class GetItemsOfContractProgressEvaluation {

    private final ItemOfContractProgressEvaluationRemoteDataSource mItemsOfConstractProgressEvaluationRemoteDataSource;

    public GetItemsOfContractProgressEvaluation(@NonNull ItemOfContractProgressEvaluationRemoteDataSource itemOfContractProgressEvaluationRemoteDataSource) {
        mItemsOfConstractProgressEvaluationRemoteDataSource = checkNotNull(itemOfContractProgressEvaluationRemoteDataSource);
    }

    public void getItemsOfConstractProgressEvaluation(int constructionSiteId,
                                                UseCase.LoadUseCaseCallback<List<ItemOfContractProgressEvaluation>> useCaseCallback) {
        mItemsOfConstractProgressEvaluationRemoteDataSource
                .allByContractProgressEvaluation(constructionSiteId, new ItemOfContractProgressEvaluationRemoteDataSource.LoadItemsOfContractProgressEvaluationCallback() {

                    @Override
                    public void onItemsOfContractProgressEvaluationLoaded(List<ItemOfContractProgressEvaluation> itemsOfContractProgressEvaluation) {
                        useCaseCallback.onLoaded(itemsOfContractProgressEvaluation);
                    }

                    @Override
                    public void onEmptyData() {
                        useCaseCallback.onEmptyData();
                    }


                    @Override
                    public void onFailed(RemoteErrorResponse errorResponse) {
                        useCaseCallback.onFailed(errorResponse.getCode());
                    }
                });
    }
}
