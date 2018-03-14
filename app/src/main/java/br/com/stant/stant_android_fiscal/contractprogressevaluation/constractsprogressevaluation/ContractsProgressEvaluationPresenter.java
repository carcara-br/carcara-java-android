package br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.stant.stant_android_fiscal.UseCase;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.domain.usecases.GetContractsProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 11/05/17.
 */

public class ContractsProgressEvaluationPresenter  implements ContractsProgressEvaluationContract.Presenter {


    private final ContractsProgressEvaluationContract.View mConstracsProgressEvaluationView;
    private final GetContractsProgressEvaluation mGetContractsProgressEvaluation;

    public ContractsProgressEvaluationPresenter(@NonNull ContractsProgressEvaluationContract.View constracsProgressEvaluationView ,
                                                @NonNull GetContractsProgressEvaluation getContractsProgressEvaluation) {
        mConstracsProgressEvaluationView = checkNotNull(constracsProgressEvaluationView);
        mGetContractsProgressEvaluation  = checkNotNull(getContractsProgressEvaluation);

        mConstracsProgressEvaluationView.setPresenter(this);
    }

    @Override
    public void loadConstractsProgressEvaluation(int constructionSiteId, ContractProgressEvaluationStatusEnum contractProgressEvaluationStatusEnum) {
        mGetContractsProgressEvaluation.getConstractsProgressEvaluation(constructionSiteId, contractProgressEvaluationStatusEnum, new UseCase.LoadUseCaseCallback<List<ContractProgressEvaluation>>() {
            @Override
            public void onLoaded(List<ContractProgressEvaluation> response) {
                mConstracsProgressEvaluationView.showContractsProgressEvaluation(response);
            }

            @Override
            public void onEmptyData() {
                mConstracsProgressEvaluationView.showNoContractsProgressEvaluation();
            }

            @Override
            public void onFailed(int errorCode) {

            }
        });
    }
}
