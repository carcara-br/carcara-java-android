package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.stant.stant_android_fiscal.UseCase;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.usecases.ApproveItemOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.usecases.DisapproveItemOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.usecases.GetItemsOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ItemOfContractProgressEvaluation;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 17/05/17.
 */

public class ContractProgressEvaluationDetailPresenter implements ContractProgressEvaluationDetailContract.Presenter {

    private final ContractProgressEvaluationDetailContract.View mContractProgressEvaluationDetailView;
    private final GetItemsOfContractProgressEvaluation mGetItemsOfContractProgressEvaluation;
    private final ApproveItemOfContractProgressEvaluation mApproveItemOfContractProgressEvaluation;
    private final DisapproveItemOfContractProgressEvaluation mDisapproveItemOfContractProgressEvaluation;

    public ContractProgressEvaluationDetailPresenter(@NonNull ContractProgressEvaluationDetailContract.View constructionSiteView ,
                                                     @NonNull GetItemsOfContractProgressEvaluation getItemsOfContractProgressEvaluation,
                                                     @NonNull ApproveItemOfContractProgressEvaluation approveItemOfContractProgressEvaluation,
                                                     @NonNull DisapproveItemOfContractProgressEvaluation disapproveItemOfContractProgressEvaluation) {
        mContractProgressEvaluationDetailView = checkNotNull(constructionSiteView);
        mGetItemsOfContractProgressEvaluation = checkNotNull(getItemsOfContractProgressEvaluation);
        mApproveItemOfContractProgressEvaluation = checkNotNull(approveItemOfContractProgressEvaluation);
        mDisapproveItemOfContractProgressEvaluation = checkNotNull(disapproveItemOfContractProgressEvaluation);

        mContractProgressEvaluationDetailView.setPresenter(this);
    }


    @Override
    public void loadItemsOfContractProgressEvaluation(int contractProgressEvaluationId) {
        mGetItemsOfContractProgressEvaluation.getItemsOfConstractProgressEvaluation(contractProgressEvaluationId, new UseCase.LoadUseCaseCallback<List<ItemOfContractProgressEvaluation>>() {
            @Override
            public void onLoaded(List<ItemOfContractProgressEvaluation> response) {
                mContractProgressEvaluationDetailView.showItemsOfContractProgressEvaluation(response);
            }

            @Override
            public void onEmptyData() {
                mContractProgressEvaluationDetailView.showNoItemsOfContractProgressEvaluation();
            }

            @Override
            public void onFailed(int errorCode) {

            }
        });
    }

    @Override
    public void approveItemOfContractProgressEvaluation(int contractProgressEvaluationId, int itemOfContractProgressEvaluationId) {
        mApproveItemOfContractProgressEvaluation.approve(itemOfContractProgressEvaluationId, contractProgressEvaluationId, new UseCase.UseCaseCallback() {
            @Override
            public void onSuccess() {
                mContractProgressEvaluationDetailView.onApproveItemOfContractProgressEvaluation();
            }

            @Override
            public void onError(String errorDescription) {
                mContractProgressEvaluationDetailView.onFailedSetStatusOfItemOfContractProgressEvaluation();
            }
        });
    }

    @Override
    public void disapproveItemOfContractProgressEvaluation(int contractProgressEvaluationId, int itemOfContractProgressEvaluationId, String observation) {
        mDisapproveItemOfContractProgressEvaluation.disapprove(itemOfContractProgressEvaluationId, contractProgressEvaluationId, observation, new UseCase.UseCaseCallback() {
            @Override
            public void onSuccess() {
                mContractProgressEvaluationDetailView.onDisapproveItemOfContractProgressEvaluation();
            }

            @Override
            public void onError(String errorDescription) {
                mContractProgressEvaluationDetailView.onFailedSetStatusOfItemOfContractProgressEvaluation();
            }
        });
    }
}
