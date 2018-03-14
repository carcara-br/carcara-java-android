package br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.domain.usecases;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.stant.stant_android_fiscal.UseCase;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.ContractProgressEvaluationRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 16/05/17.
 */

public class GetContractsProgressEvaluation {

    private final ContractProgressEvaluationRemoteDataSource mConstractsProgressEvaluationRemoteDataSource;

    public GetContractsProgressEvaluation(@NonNull ContractProgressEvaluationRemoteDataSource constractsProgressEvaluationRemoteDataSource) {
        mConstractsProgressEvaluationRemoteDataSource = checkNotNull(constractsProgressEvaluationRemoteDataSource);
    }

    public void getConstractsProgressEvaluation(int constructionSiteId,
                                                ContractProgressEvaluationStatusEnum contractProgressEvaluationStatusEnum,
                                                UseCase.LoadUseCaseCallback<List<ContractProgressEvaluation>> useCaseCallback) {
        mConstractsProgressEvaluationRemoteDataSource
                .allByConstructionSiteAndStatus(constructionSiteId, contractProgressEvaluationStatusEnum, new ContractProgressEvaluationRemoteDataSource.LoadContractsProgressEvaluationCallback() {
                    @Override
                    public void onContractsProgressEvaluationLoaded(List<ContractProgressEvaluation> constractsProgressEvaluations) {
                        useCaseCallback.onLoaded(constractsProgressEvaluations);
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
