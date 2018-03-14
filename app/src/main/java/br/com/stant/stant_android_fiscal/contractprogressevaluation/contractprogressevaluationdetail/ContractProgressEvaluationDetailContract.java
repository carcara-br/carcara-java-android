package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail;

import java.util.List;

import br.com.stant.stant_android_fiscal.BaseAdapters;
import br.com.stant.stant_android_fiscal.BasePresenter;
import br.com.stant.stant_android_fiscal.BaseView;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.domain.dto.ContractProgressEvaluationDto;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.dto.ItemOfContractProgressEvaluationDto;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ItemOfContractProgressEvaluation;

/**
 * Created by denisvieira on 17/05/17.
 */

public interface ContractProgressEvaluationDetailContract {

    interface View extends BaseView<Presenter> {
        void openMainMenu(android.view.View view);
        void showContractProgressEvaluation(ContractProgressEvaluation contractProgressEvaluation);
        void showItemsOfContractProgressEvaluation(List<ItemOfContractProgressEvaluation> list);
        void showNoItemsOfContractProgressEvaluation();
        void approveItemOfContractProgressEvaluation(int itemId);
        void disapproveItemOfContractProgressEvaluation(int itemId);
        void showJustifyDisapproveObservationDialog(int itemId);
        void onApproveItemOfContractProgressEvaluation();
        void onDisapproveItemOfContractProgressEvaluation();
        void onFailedSetStatusOfItemOfContractProgressEvaluation();
    }

    interface Presenter extends BasePresenter {
        void loadItemsOfContractProgressEvaluation(int contractProgressEvaluationId);
        void approveItemOfContractProgressEvaluation(int contractProgressEvaluationId, int itemOfContractProgressEvaluationId);
        void disapproveItemOfContractProgressEvaluation(int contractProgressEvaluationId, int itemOfContractProgressEvaluationId, String observation);
    }

    interface Adapter extends BaseAdapters {}
}
